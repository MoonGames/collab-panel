/*
 * Collab desktop - Software for shared drawing via internet in real-time
 * Copyright (C) 2012 Martin Indra <aktive@seznam.cz>
 *
 * This file is part of Collab desktop.
 *
 * Collab desktop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Collab desktop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Collab desktop.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.mgn.collabcanvas.canvas.image;

import cz.mgn.collabcanvas.canvas.image.imageprocessing.ImageProcessor;
import cz.mgn.collabcanvas.canvas.image.layers.Layer;
import cz.mgn.collabcanvas.canvas.image.network.NetworkImage;
import cz.mgn.collabcanvas.canvas.image.selection.Selection;
import cz.mgn.collabcanvas.canvas.image.zoom.Zoom;
import cz.mgn.collabcanvas.canvas.utils.dirty.UnionDirty;
import cz.mgn.collabcanvas.interfaces.informing.InfoListener;
import cz.mgn.collabcanvas.interfaces.informing.Informing;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkListener;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkUpdate;
import cz.mgn.collabcanvas.interfaces.networkable.Networkable;
import cz.mgn.collabcanvas.interfaces.paintable.PaintData;
import cz.mgn.collabcanvas.interfaces.paintable.PaintImage;
import cz.mgn.collabcanvas.interfaces.paintable.Paintable;
import cz.mgn.collabcanvas.interfaces.selectionable.SelectionUpdate;
import cz.mgn.collabcanvas.interfaces.selectionable.Selectionable;
import cz.mgn.collabcanvas.interfaces.zoomable.Zoomable;
import cz.mgn.collabcanvas.utils.ThreadBlocker;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CanvasImage implements Runnable, Networkable, Paintable, Zoomable,
        Informing, Selectionable {

    protected boolean network;
    protected int canvasID;
    protected CanvasImageChangeListener changeListener;
    // image
    protected BufferedImage normalImage = null;
    protected BufferedImage zoomedImage = null;
    // resolution
    protected int width = 1;
    protected int height = 1;
    //zoom
    protected Zoom zoom = new Zoom();
    //layers
    protected ArrayList<Layer> layers = new ArrayList<Layer>();
    protected Layer selectedLayer = null;
    protected int lastLayerID = 0;
    // selection
    protected Selection selection = new Selection();
    //info
    protected Set<InfoListener> infoListeners = new HashSet<InfoListener>();
    //network part
    protected volatile boolean running = false;
    protected Set<NetworkListener> networkListeners;
    protected NetworkIDGenerator idGenerator;

    public CanvasImage(boolean network, NetworkIDGenerator idGenerator,
            int canvasID) {
        this.network = network;
        this.canvasID = canvasID;
        setResolution(width, height);
        if (network) {
            this.idGenerator = idGenerator;
            networkListeners = new HashSet<NetworkListener>();
            new Thread(this).start();
        }
    }

    public void setChangeListener(CanvasImageChangeListener changeListener) {
        this.changeListener = changeListener;
    }

    public BufferedImage getNormalImage() {
        return normalImage;
    }

    public BufferedImage getZoomedImage() {
        return zoomedImage;
    }

    public Selection getSelection() {
        return selection;
    }

    protected void reconstructImage(Rectangle update) {
        BufferedImage normalReconstruction = reconstructNormalImage(update);
        Graphics2D g = (Graphics2D) normalImage.getGraphics();
        g.setComposite(AlphaComposite.Src);
        g.drawImage(normalReconstruction, update.x, update.y, null);
        g.dispose();
        reconstructZoomedImage(update);
    }

    protected void reconstructZoomedImage(Rectangle update) {
        zoom.paintZoomed(normalImage, zoomedImage, update);
    }

    protected BufferedImage reconstructNormalImage(Rectangle update) {
        BufferedImage reconstruction = new BufferedImage(update.width,
                update.height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) reconstruction.getGraphics();
        int x2 = update.x + update.width;
        int y2 = update.y + update.height;
        synchronized (layers) {
            for (Layer layer : layers) {
                if (layer.getOpaqueness() > 0) {
                    g.setComposite(AlphaComposite.getInstance(
                            AlphaComposite.SRC_OVER, layer.getOpaqueness()));
                    BufferedImage img = layer.getImage();
                    if (network) {
                        img = layer.getNetworkImage().addLocalChanges(img, update.x,
                                update.y, update.width, update.height);
                    }
                    g.drawImage(img, 0, 0, update.width, update.height,
                            update.x, update.y, x2, y2, null);
                }
            }
        }
        g.dispose();
        return reconstruction;
    }

    protected BufferedImage reconstructNormalImageLayer(Rectangle update,
            Layer layer) {
        BufferedImage reconstruction = new BufferedImage(update.width,
                update.height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) reconstruction.getGraphics();
        int x2 = update.x + update.width;
        int y2 = update.y + update.height;
        if (layer.getOpaqueness() > 0) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    layer.getOpaqueness()));
            BufferedImage img = layer.getImage();
            img = layer.getNetworkImage().addLocalChanges(img, update.x,
                    update.y, update.width, update.height);
            g.drawImage(img, 0, 0, update.width, update.height,
                    update.x, update.y, x2, y2, null);
        }
        g.dispose();
        return reconstruction;
    }

    protected void informAboutUpdate(Rectangle rect) {
        if (changeListener != null) {
            rect = transformRectangle(rect);
            changeListener.change(rect);
        }
    }

    protected void informAboutSize() {
        if (changeListener != null) {
            Point size = transformPoint(new Point(width, height));
            changeListener.imageResized(size.x, size.y);
        }
    }

    protected Layer createLayer(int layerID) {
        if (network) {
            return new Layer(layerID, width, height,
                    new NetworkImage(new NetworkListener() {
                        @Override
                        public void sendUpdate(NetworkUpdate update) {
                            for (NetworkListener listener : networkListeners) {
                                listener.sendUpdate(update);
                            }
                        }

                        @Override
                        public void unreachedUpdateRemoved(NetworkUpdate update) {
                            for (NetworkListener listener : networkListeners) {
                                listener.unreachedUpdateRemoved(update);
                            }
                        }
                    }, idGenerator, layerID, canvasID));
        }
        return new Layer(layerID, width, height);
    }

    /**
     * vrati pozici vrtstvy v seznamu vrstev podle ID
     */
    protected int getLayerIndexByID(int layerID) {
        synchronized (layers) {
            for (int i = 0; i < layers.size(); i++) {
                if (layers.get(i).getID() == layerID) {
                    return i;
                }
            }
        }
        return -1;
    }

    protected Layer getLayerByID(int layerID) {
        synchronized (layers) {
            for (Layer layer : layers) {
                if (layer.getID() == layerID) {
                    return layer;
                }
            }
        }
        return null;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            ThreadBlocker.getInstance().block();
            synchronized (layers) {
                for (Layer layer : layers) {
                    layer.getNetworkImage().update();
                }
            }
            ThreadBlocker.getInstance().unblock();
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(CanvasImage.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }
    }

    public void destroy() {
        running = false;
        infoListeners.clear();
        if (network) {
            networkListeners.clear();
        }
    }

    @Override
    public Set<NetworkListener> getListeners() {
        return networkListeners;
    }

    @Override
    public void addListener(NetworkListener listener) {
        synchronized (networkListeners) {
            networkListeners.add(listener);
        }
    }

    @Override
    public boolean removeListener(NetworkListener listener) {
        synchronized (networkListeners) {
            return networkListeners.remove(listener);
        }
    }

    @Override
    public void updateReceived(NetworkUpdate update) {
        ThreadBlocker.getInstance().block();
        Layer layer = getLayerByID(update.getUpdateLayerID());
        if (layer != null) {
            int x = update.getUpdateCoordinates().x;
            int y = update.getUpdateCoordinates().y;
            BufferedImage layerImage = layer.getImage();
            BufferedImage updateImage = update.getUpdateImage();
            int w = updateImage.getWidth();
            int h = updateImage.getHeight();
            if (update.isAdd()) {
                ImageProcessor.addToImage(layerImage, updateImage, x, y, x,
                        y, w, h);
            } else {
                ImageProcessor.
                        removeFromImage(layerImage, updateImage, x, y, x, y,
                                w, h);
            }
            layer.getNetworkImage().updateReceived(update.getUpdateID());
            Rectangle dirty = new Rectangle(update.getUpdateCoordinates(),
                    new Dimension(update.getUpdateImage().getWidth(),
                            update.getUpdateImage().getHeight()));
            reconstructImage(dirty);
            informAboutUpdate(dirty);
        }
        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public void paint(PaintData paintData) {
        ThreadBlocker.getInstance().block();
        if (selectedLayer != null) {
            ArrayList<PaintImage> paintImages = paintData.getPaintImages();
            UnionDirty dirty = new UnionDirty();
            for (PaintImage paintImage : paintImages) {
                BufferedImage image = paintImage.getImage();
                ArrayList<Point> applyPoints = paintImage.getApplyPoints();
                boolean add = paintImage.isAddChange();
                for (Point applyPoint : applyPoints) {
                    BufferedImage pImage = selection.filterPaint(
                            applyPoint.x, applyPoint.y, image);
                    dirty.addDirty(new Rectangle(applyPoint.x, applyPoint.y,
                            image.getWidth(), image.getHeight()));
                    if (network) {
                        if (add) {
                            selectedLayer.getNetworkImage().paintAdd(pImage,
                                    applyPoint.x, applyPoint.y);
                        } else {
                            selectedLayer.getNetworkImage().paintRemove(
                                    pImage, applyPoint.x, applyPoint.y);
                        }
                    } else {
                        if (add) {
                            ImageProcessor.
                                    paintAdd(selectedLayer.getImage(),
                                            pImage, applyPoint.x, applyPoint.y);
                        } else {
                            ImageProcessor.paintRemove(selectedLayer.
                                    getImage(), pImage, applyPoint.x,
                                    applyPoint.y);
                        }
                    }
                }
            }
            reconstructImage(dirty.getSingleRectangle());
            informAboutUpdate(dirty.getSingleRectangle());
        }
        ThreadBlocker.getInstance().block();
    }

    @Override
    public void selectLayer(int layerID) {
        selectedLayer = getLayerByID(layerID);
    }

    @Override
    public void setLayersOrder(int[] order, boolean remove) {
        ThreadBlocker.getInstance().block();
        synchronized (layers) {
            ArrayList<Layer> layersNew = new ArrayList<Layer>();
            for (int i = 0; i < order.length; i++) {
                Layer add = getLayerByID(order[i]);
                if (add == null) {
                    add = createLayer(order[i]);
                }
                layersNew.add(add);
            }
            if (remove) {
                layers.clear();
                layers.addAll(layersNew);
            } else {
                ArrayList<Layer> layersNew2 = new ArrayList<Layer>();
                layersNew2.addAll(layers);
                layers.clear();
                layers.addAll(layersNew);
                layersNew2.removeAll(layers);
                layers.addAll(layersNew2);
            }
        }
        if (selectedLayer != null) {
            selectedLayer = getLayerByID(selectedLayer.getID());
        }
        Rectangle rect = new Rectangle(0, 0, width, height);
        reconstructImage(rect);
        informAboutUpdate(rect);
        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public int[] getLayersOrder() {
        synchronized (layers) {
            int[] order = new int[layers.size()];
            for (int i = 0; i < order.length; i++) {
                order[i] = layers.get(i).getID();
            }
            return order;
        }
    }

    @Override
    public void setLayerVisibility(int layerID, boolean visible) {
        ThreadBlocker.getInstance().block();
        Layer layer = getLayerByID(layerID);
        float opaqueness = visible ? 1 : 0;
        if (layer != null && layer.getOpaqueness() != opaqueness) {
            layer.setOpaqueness(opaqueness);
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructImage(rect);
            informAboutUpdate(rect);
        }
        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public int getLayerVisibility(int layerID) {
        Layer layer = getLayerByID(layerID);
        if (layer == null) {
            return Paintable.LAYER_VISIBLITY_DOESNT_EXIST;
        }
        return layer.getOpaqueness() > 0 ? Paintable.LAYER_VISIBLITY_VISIBLE
                : Paintable.LAYER_VISIBLITY_INVISIBLE;
    }

    @Override
    public void setLayerOpaqueness(int layerID, float opaqueness) {
        ThreadBlocker.getInstance().block();
        Layer layer = getLayerByID(layerID);
        if (layer != null && layer.getOpaqueness() != opaqueness) {
            layer.setOpaqueness(opaqueness);
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructImage(rect);
            informAboutUpdate(rect);
        }
        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public float getLayerOpaqueness(int layerID) {
        Layer layer = getLayerByID(layerID);
        if (layer == null) {
            return -1;
        }
        return layer.getOpaqueness();
    }

    @Override
    public int addLayer() {
        if (network) {
            return -1;
        }
        Layer layer = createLayer(++lastLayerID);
        synchronized (layers) {
            layers.add(layer);
        }
        return layer.getID();
    }

    @Override
    public boolean removeLayer(int layerID) {
        boolean result = false;
        if (!network) {
            ThreadBlocker.getInstance().block();

            int index = getLayerIndexByID(layerID);
            synchronized (layers) {
                layers.remove(index);
            }
            selectedLayer = getLayerByID(selectedLayer.getID());
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructImage(rect);
            informAboutUpdate(rect);
            result = index >= 0;

            ThreadBlocker.getInstance().unblock();
        }

        return result;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setResolution(int width, int height) {
        ThreadBlocker.getInstance().block();

        this.width = width;
        this.height = height;
        selection.setResolution(width, height);
        normalImage = new BufferedImage(width, height,
                BufferedImage.TYPE_4BYTE_ABGR);
        Point size = transformPoint(new Point(width, height));
        zoomedImage = new BufferedImage(size.x, size.y,
                BufferedImage.TYPE_4BYTE_ABGR);
        synchronized (layers) {
            for (Layer layer : layers) {
                layer.setResolution(width, height);
            }
        }
        Rectangle rect = new Rectangle(0, 0, width, height);
        reconstructImage(rect);
        informAboutSize();
        informAboutUpdate(rect);

        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public BufferedImage getSelectedLayerImage(Rectangle rect) {
        BufferedImage result = null;

        if (selectedLayer != null) {
            ThreadBlocker.getInstance().block();

            if (rect == null) {
                rect = new Rectangle(0, 0, getWidth(), getHeight());
            }
            result = reconstructNormalImageLayer(rect, selectedLayer);

            ThreadBlocker.getInstance().unblock();
        }

        return result;
    }

    @Override
    public BufferedImage getImage(Rectangle rect) {
        ThreadBlocker.getInstance().block();

        if (rect == null) {
            rect = new Rectangle(0, 0, getWidth(), getHeight());
        }
        BufferedImage image = new BufferedImage(rect.width, rect.height,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.drawImage(normalImage, 0, 0, rect.width, rect.height,
                rect.x, rect.y, rect.x + rect.width, rect.y + rect.height,
                null);
        g.dispose();

        ThreadBlocker.getInstance().unblock();

        return image;
    }

    @Override
    public float getZoom() {
        return zoom.getZoom();
    }

    @Override
    public float setZoom(float zoom) {
        ThreadBlocker.getInstance().block();

        float z = this.zoom.getZoom();
        this.zoom.trySetZoom(zoom);
        if (z != this.zoom.getZoom()) {
            Point size = transformPoint(new Point(width, height));
            zoomedImage = new BufferedImage(size.x, size.y,
                    BufferedImage.TYPE_4BYTE_ABGR);
            selection.setOutlineZoom(zoom);
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructZoomedImage(rect);
            informAboutSize();
            informAboutUpdate(rect);
            for (InfoListener infoListener : infoListeners) {
                infoListener.zoomChanged(this.zoom.getZoom());
            }
            changeListener.zoomChanged(zoom);
        }

        ThreadBlocker.getInstance().unblock();

        return this.zoom.getZoom();
    }

    @Override
    public float addToZoom(float zoom) {
        return setZoom(this.zoom.getZoom() + zoom);
    }

    @Override
    public int transformCoordinate(int coordinate) {
        return zoom.transformCoordinate(coordinate);
    }

    @Override
    public Point transformPoint(Point point) {
        return new Point(transformCoordinate(point.x),
                transformCoordinate(point.y));
    }

    @Override
    public Rectangle transformRectangle(Rectangle rectangle) {
        return new Rectangle(transformCoordinate(rectangle.x),
                transformCoordinate(rectangle.y),
                transformCoordinate(rectangle.width),
                transformCoordinate(rectangle.height));
    }

    @Override
    public Set<InfoListener> getInfoListeners() {
        return infoListeners;
    }

    @Override
    public void addInfoListener(InfoListener listener) {
        synchronized (infoListeners) {
            infoListeners.add(listener);
        }
    }

    @Override
    public boolean removeInfoListener(InfoListener listener) {
        synchronized (infoListeners) {
            return infoListeners.remove(listener);
        }
    }

    @Override
    public BufferedImage getImageSelection() {
        ThreadBlocker.getInstance().block();

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_4BYTE_ABGR);
        normalImage.copyData(image.getRaster());
        BufferedImage result = selection.filterPaint(0, 0, image);

        ThreadBlocker.getInstance().unblock();

        return result;
    }

    @Override
    public BufferedImage getSelectedLayerImageSelection() {
        BufferedImage result = null;

        if (selectedLayer != null) {
            ThreadBlocker.getInstance().block();

            BufferedImage img = reconstructNormalImageLayer(new Rectangle(0, 0,
                    width, height), selectedLayer);
            result = selection.filterPaint(0, 0, img);

            ThreadBlocker.getInstance().unblock();
        }

        return result;
    }

    @Override
    public BufferedImage getSelectionImage() {
        ThreadBlocker.getInstance().block();

        BufferedImage src = selection.getSelectionFilterImage();
        BufferedImage dst = new BufferedImage(src.getWidth(), src.
                getHeight(), src.getType());
        src.copyData(dst.getRaster());

        ThreadBlocker.getInstance().unblock();

        return dst;
    }

    @Override
    public void selectAll() {
        ThreadBlocker.getInstance().block();
        selection.update(null);
        changeListener.selectionChange();
        //FIXME: repaint?
        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public void select(SelectionUpdate selectoinUpdate) {
        ThreadBlocker.getInstance().block();
        selection.update(selectoinUpdate);
        changeListener.selectionChange();
        //FIXME: repaint?
        ThreadBlocker.getInstance().unblock();
    }

    @Override
    public boolean isSelectedAll() {
        return selection.isSelectedAll();
    }
}
