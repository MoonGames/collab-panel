/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image;

import cz.mgn.collabcanvas.canvas.image.imageprocessing.ImageProcessor;
import cz.mgn.collabcanvas.canvas.image.layers.Layer;
import cz.mgn.collabcanvas.canvas.image.network.NetworkImage;
import cz.mgn.collabcanvas.canvas.image.selection.Selection;
import cz.mgn.collabcanvas.canvas.image.zoom.Zoom;
import cz.mgn.collabcanvas.canvas.utils.dirty.Dirty;
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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author indy
 */
public class CanvasImage implements Runnable, Networkable, Paintable, Zoomable, Informing, Selectionable {

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
    protected Set<InfoListener> infoListeners = new TreeSet<InfoListener>();
    //network part
    protected volatile boolean running = false;
    protected Set<NetworkListener> listeners;
    protected NetworkIDGenerator idGenerator;

    public CanvasImage(boolean network, NetworkIDGenerator idGenerator, int canvasID) {
        this.network = network;
        this.canvasID = canvasID;
        setResolution(width, height);
        if (network) {
            this.idGenerator = idGenerator;
            listeners = new TreeSet<NetworkListener>();
            new Thread(this).start();
        }
    }

    public void setChangeListener(CanvasImageChangeListener changeListener) {
        synchronized (this) {
            this.changeListener = changeListener;
        }
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
        g.setColor(Color.WHITE);
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(update.x, update.y, update.width, update.height);
        g.setComposite(AlphaComposite.SrcOver);
        g.drawImage(normalReconstruction, update.x, update.y, null);
        g.dispose();
        reconstructZoomedImage(update);
    }

    protected void reconstructZoomedImage(Rectangle update) {
        zoom.paintZoomed(normalImage, zoomedImage, update);
    }

    protected BufferedImage reconstructNormalImage(Rectangle update) {
        BufferedImage reconstruction = new BufferedImage(update.width, update.height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) reconstruction.getGraphics();
        int x2 = update.x + update.width;
        int y2 = update.y + update.height;
        for (Layer layer : layers) {
            if (layer.getOpaqueness() > 0) {
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layer.getOpaqueness()));
                BufferedImage img = layer.getImage();
                img = layer.getNetworkImage().addLocalChanges(img, update.x, update.y, update.width, update.height);
                g.drawImage(img, 0, 0, update.width, update.height,
                        update.x, update.y, x2, y2, null);
            }
        }
        g.dispose();
        return reconstruction;
    }

    protected BufferedImage reconstructNormalImageLayer(Rectangle update, Layer layer) {
        BufferedImage reconstruction = new BufferedImage(update.width, update.height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) reconstruction.getGraphics();
        int x2 = update.x + update.width;
        int y2 = update.y + update.height;
        if (layer.getOpaqueness() > 0) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layer.getOpaqueness()));
            BufferedImage img = layer.getImage();
            img = layer.getNetworkImage().addLocalChanges(img, update.x, update.y, update.width, update.height);
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
            return new Layer(layerID, width, height, new NetworkImage(new NetworkListener() {

                @Override
                public void sendUpdate(NetworkUpdate update) {
                    for (NetworkListener listener : listeners) {
                        listener.sendUpdate(update);
                    }
                }

                @Override
                public void unreachedUpdateRemoved(NetworkUpdate update) {
                    for (NetworkListener listener : listeners) {
                        listener.unreachedUpdateRemoved(update);
                    }
                }
            }, idGenerator, layerID, canvasID, width, height));
        }
        return new Layer(layerID, width, height);
    }

    /**
     * vrati pozici vrtstvy v seznamu vrstev podle ID
     */
    protected int getLayerIndexByID(int layerID) {
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).getID() == layerID) {
                return i;
            }
        }
        return -1;
    }

    protected Layer getLayerByID(int layerID) {
        for (Layer layer : layers) {
            if (layer.getID() == layerID) {
                return layer;
            }
        }
        return null;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            synchronized (this) {
                for (Layer layer : layers) {
                    layer.getNetworkImage().update();
                }
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(CanvasImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void destroy() {
        synchronized (this) {
            running = false;
        }
    }

    @Override
    public Set<NetworkListener> getListeners() {
        synchronized (this) {
            return listeners;
        }
    }

    @Override
    public void addListener(NetworkListener listener) {
        synchronized (this) {
            listeners.add(listener);
        }
    }

    @Override
    public boolean removeListener(NetworkListener listener) {
        synchronized (this) {
            return listeners.remove(listener);
        }
    }

    @Override
    public void updateReceived(NetworkUpdate update) {
        synchronized (this) {
            Layer layer = getLayerByID(update.getUpdateLayerID());
            if (layer != null) {
                int x = update.getUpdateCoordinates().x;
                int y = update.getUpdateCoordinates().y;
                BufferedImage layerImage = layer.getImage();
                BufferedImage updateImage = update.getUpdateImage();
                int w = updateImage.getWidth();
                int h = updateImage.getHeight();
                if (update.isAdd()) {
                    ImageProcessor.addToImage(layerImage, updateImage, x, y, x, y, w, h);
                } else {
                    ImageProcessor.removeFromImage(layerImage, updateImage, x, y, x, y, w, h);
                }
                layer.getNetworkImage().updateReceived(update.getUpdateID());
                informAboutUpdate(new Rectangle(update.getUpdateCoordinates(), new Dimension(update.getUpdateImage().getWidth(), update.getUpdateImage().getHeight())));
            }
        }
    }

    @Override
    public void paint(PaintData paintData) {
        synchronized (this) {
            if (selectedLayer != null) {
                ArrayList<PaintImage> paintImages = paintData.getPaintImages();
                Dirty dirty = new UnionDirty();
                for (PaintImage paintImage : paintImages) {
                    BufferedImage image = paintImage.getImage();
                    ArrayList<Point> applyPoints = paintImage.getApplyPoints();
                    boolean add = paintImage.isAddChange();
                    for (Point applyPoint : applyPoints) {
                        BufferedImage pImage = selection.filterPaint(applyPoint.x, applyPoint.y, image);
                        dirty.addDirty(new Rectangle(applyPoint.x, applyPoint.y, image.getWidth(), image.getHeight()));
                        if (network) {
                            if (add) {
                                selectedLayer.getNetworkImage().paintAdd(pImage, applyPoint.x, applyPoint.y);
                            } else {
                                selectedLayer.getNetworkImage().paintRemove(pImage, applyPoint.x, applyPoint.y);
                            }
                        } else {
                            if (add) {
                                ImageProcessor.paintAdd(selectedLayer.getImage(), pImage, applyPoint.x, applyPoint.y);
                            } else {
                                ImageProcessor.paintRemove(selectedLayer.getImage(), pImage, applyPoint.x, applyPoint.y);
                            }

                        }
                    }
                }
            }
        }
    }

    @Override
    public void selectLayer(int layerID) {
        synchronized (this) {
            selectedLayer = getLayerByID(layerID);
        }
    }

    @Override
    public void setLayersOrder(int[] order, boolean remove) {
        synchronized (this) {
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

            selectedLayer = getLayerByID(selectedLayer.getID());
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructImage(rect);
            informAboutUpdate(rect);
        }
    }

    @Override
    public int[] getLayersOrder() {
        synchronized (this) {
            int[] order = new int[layers.size()];
            for (int i = 0; i < order.length; i++) {
                order[i] = layers.get(i).getID();
            }
            return order;
        }
    }

    @Override
    public void setLayerVisibility(int layerID, boolean visible) {
        synchronized (this) {
            Layer layer = getLayerByID(layerID);
            float opaqueness = visible ? 1 : 0;
            if (layer != null && layer.getOpaqueness() != opaqueness) {
                layer.setOpaqueness(opaqueness);
                Rectangle rect = new Rectangle(0, 0, width, height);
                reconstructImage(rect);
                informAboutUpdate(rect);
            }
        }
    }

    @Override
    public int getLayerVisibility(int layerID) {
        synchronized (this) {
            Layer layer = getLayerByID(layerID);
            if (layer == null) {
                return -1;
            }
            return layer.getOpaqueness() > 0 ? 1 : 0;
        }
    }

    @Override
    public void setLayerOpaqueness(int layerID, float opaqueness) {
        synchronized (this) {
            Layer layer = getLayerByID(layerID);
            if (layer != null && layer.getOpaqueness() != opaqueness) {
                layer.setOpaqueness(opaqueness);
                Rectangle rect = new Rectangle(0, 0, width, height);
                reconstructImage(rect);
                informAboutUpdate(rect);
            }
        }
    }

    @Override
    public float getLayerOpaqueness(int layerID) {
        synchronized (this) {
            Layer layer = getLayerByID(layerID);
            if (layer == null) {
                return -1;
            }
            return layer.getOpaqueness();
        }
    }

    @Override
    public int addLayer() {
        synchronized (this) {
            if (network) {
                return -1;
            }
            Layer layer = createLayer(++lastLayerID);
            layers.add(layer);
            return layer.getID();
        }
    }

    @Override
    public boolean removeLayer(int layerID) {
        synchronized (this) {
            if (network) {
                return false;
            }
            int index = getLayerIndexByID(layerID);
            layers.remove(index);
            selectedLayer = getLayerByID(selectedLayer.getID());
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructImage(rect);
            informAboutUpdate(rect);
            return index >= 0;
        }
    }

    @Override
    public int getWidth() {
        synchronized (this) {
            return width;
        }
    }

    @Override
    public int getHeight() {
        synchronized (this) {
            return height;
        }
    }

    @Override
    public void setResolution(int width, int height) {
        synchronized (this) {
            this.width = width;
            this.height = height;
            selection.setResolution(width, height);
            normalImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            Point size = transformPoint(new Point(width, height));
            zoomedImage = new BufferedImage(size.x, size.y, BufferedImage.TYPE_4BYTE_ABGR);
            for (Layer layer : layers) {
                layer.setResolution(width, height);
            }
            Rectangle rect = new Rectangle(0, 0, width, height);
            reconstructImage(rect);
            informAboutSize();
            informAboutUpdate(rect);
        }
    }

    @Override
    public BufferedImage getSelectedLayerImage(Rectangle rect) {
        synchronized (this) {
            if (selectedLayer == null) {
                return null;
            }
            if (rect == null) {
                rect = new Rectangle(0, 0, getWidth(), getHeight());
            }
            return reconstructNormalImageLayer(rect, selectedLayer);
        }
    }

    @Override
    public BufferedImage getImage(Rectangle rect) {
        synchronized (this) {
            if (rect == null) {
                rect = new Rectangle(0, 0, getWidth(), getHeight());
            }
            BufferedImage image = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.drawImage(normalImage, 0, 0, rect.width, rect.height,
                    rect.x, rect.y, rect.x + rect.width, rect.y + rect.height, null);
            g.dispose();
            return image;
        }
    }

    @Override
    public float getZoom() {
        synchronized (this) {
            return zoom.getZoom();
        }
    }

    @Override
    public float setZoom(float zoom) {
        synchronized (this) {
            float z = this.zoom.getZoom();
            this.zoom.trySetZoom(zoom);
            if (z != this.zoom.getZoom()) {
                Point size = transformPoint(new Point(width, height));
                zoomedImage = new BufferedImage(size.x, size.y, BufferedImage.TYPE_4BYTE_ABGR);
                Rectangle rect = new Rectangle(0, 0, width, height);
                reconstructZoomedImage(rect);
                informAboutSize();
                informAboutUpdate(rect);
                for (InfoListener infoListener : infoListeners) {
                    infoListener.zoomChanged(this.zoom.getZoom());
                }
                changeListener.zoomChanged(zoom);
            }
            return this.zoom.getZoom();
        }
    }

    @Override
    public float addToZoom(float zoom) {
        return setZoom(this.zoom.getZoom() + zoom);
    }

    @Override
    public int transformCoordinate(int coordinate) {
        synchronized (this) {
            return zoom.transformCoordinate(coordinate);
        }
    }

    @Override
    public Point transformPoint(Point point) {
        synchronized (this) {
            return new Point(transformCoordinate(point.x), transformCoordinate(point.y));
        }
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
        synchronized (this) {
            return infoListeners;
        }
    }

    @Override
    public void addInfoListener(InfoListener listener) {
        synchronized (this) {
            infoListeners.add(listener);
        }
    }

    @Override
    public boolean removeInfoListener(InfoListener listener) {
        synchronized (this) {
            return infoListeners.remove(listener);
        }
    }

    @Override
    public BufferedImage getImageSelection() {
        synchronized (this) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            normalImage.copyData(image.getRaster());
            return selection.filterPaint(0, 0, image);
        }
    }

    @Override
    public BufferedImage getSelectedLayerImageSelection() {
        synchronized (this) {
            if (selectedLayer == null) {
                return null;
            }
            BufferedImage img = reconstructNormalImageLayer(new Rectangle(0, 0, width, height), selectedLayer);
            return selection.filterPaint(0, 0, img);
        }
    }

    @Override
    public BufferedImage getSelectionImage() {
        synchronized (this) {
            BufferedImage src = selection.getSelectionFilterImage();
            BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), src.getType());
            src.copyData(dst.getRaster());
            return dst;
        }
    }

    @Override
    public void selectAll() {
        synchronized (this) {
            selection.update(null);
            changeListener.selectionChange();
        }
    }

    @Override
    public void select(SelectionUpdate selectoinUpdate) {
        synchronized (this) {
            selection.update(selectoinUpdate);
            changeListener.selectionChange();
        }
    }
}
