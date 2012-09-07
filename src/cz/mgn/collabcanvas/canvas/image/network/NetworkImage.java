/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image.network;

import cz.mgn.collabcanvas.canvas.image.imageprocessing.ImageProcessor;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkListener;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author indy
 */
public class NetworkImage {

    protected static final int BLOCK_SIZE = 80;
    protected static final long EDITS_TIME_OUT = 2000l;
    //
    protected NetworkIDGenerator idGenerator;
    protected int layerID;
    protected int canvasID;
    protected NetworkListener listener;
    protected ArrayList<Edit> edits = new ArrayList<Edit>();
    protected BufferedImage imageAdd = null;
    protected BufferedImage imageRemove = null;
    protected boolean anyChange = false;
    protected boolean[][] changesAdd = null;
    protected boolean[][] changesRemove = null;
    protected BufferedImage layerImageCopy = null;
    protected boolean isLayerImageInvalid = true;

    public NetworkImage(NetworkListener listener, NetworkIDGenerator idGenerator, int layerID, int canvasID) {
        this.listener = listener;
        this.layerID = layerID;
        this.canvasID = canvasID;
        this.idGenerator = idGenerator;
    }

    protected void controlTimeOutEdits() {
        long time = System.currentTimeMillis();
        for (int i = 0; i < edits.size(); i++) {
            Edit edit = edits.get(i);
            if ((time - edit.getTime()) > EDITS_TIME_OUT) {
                edits.remove(i);
                i--;
                listener.unreachedUpdateRemoved(edit);
            }
        }
    }

    protected void makeEdits() {
        anyChange = false;
        long time = System.currentTimeMillis();
        BufferedImage adds = new BufferedImage(imageAdd.getWidth(), imageAdd.getHeight(), imageAdd.getType());
        imageAdd.copyData(adds.getRaster());
        BufferedImage removes = new BufferedImage(imageRemove.getWidth(), imageRemove.getHeight(), imageRemove.getType());
        imageRemove.copyData(removes.getRaster());

        imageRemove = new BufferedImage(imageRemove.getWidth(), imageRemove.getHeight(), imageRemove.getType());
        imageAdd = new BufferedImage(imageAdd.getWidth(), imageAdd.getHeight(), imageAdd.getType());

        ArrayList<Edit> editsl = new ArrayList<Edit>();
        for (int x = 0; x < changesAdd.length; x++) {
            for (int y = 0; y < changesAdd[0].length; y++) {
                if (changesAdd[x][y]) {
                    int xr = x * BLOCK_SIZE;
                    int yr = y * BLOCK_SIZE;
                    BufferedImage editBox = createEditBox(xr, yr, BLOCK_SIZE, BLOCK_SIZE, adds);
                    editsl.add(new Edit(idGenerator.generateNextID(), layerID, canvasID, true, new Point(xr, yr), editBox, time));
                    changesAdd[x][y] = false;
                }
            }
        }

        for (int x = 0; x < changesRemove.length; x++) {
            for (int y = 0; y < changesRemove[0].length; y++) {
                if (changesRemove[x][y]) {
                    int xr = x * BLOCK_SIZE;
                    int yr = y * BLOCK_SIZE;
                    BufferedImage editBox = createEditBox(xr, yr, BLOCK_SIZE, BLOCK_SIZE, removes);
                    editsl.add(new Edit(idGenerator.generateNextID(), layerID, canvasID, false, new Point(xr, yr), editBox, time));
                    changesRemove[x][y] = false;
                }
            }
        }

        edits.addAll(editsl);
        for (Edit edit : editsl) {
            listener.sendUpdate(edit);
        }
    }

    protected BufferedImage createEditBox(int x, int y, int width, int height, BufferedImage source) {
        int x2 = Math.min(x + width, source.getWidth());
        int y2 = Math.min(y + height, source.getHeight());
        width = Math.max(x2 - x, 1);
        height = Math.max(y2 - y, 1);

        BufferedImage result = new BufferedImage(width, height, source.getType());
        for (int xx = x; xx < x2; xx++) {
            for (int yy = y; yy < y2; yy++) {
                int pixel = source.getRGB(xx, yy);
                result.setRGB(xx - x, yy - y, pixel);
            }
        }
        return result;
    }

    protected void addToImage(BufferedImage edit, int x, int y, int width, int height) {
        ImageProcessor.addToImage(edit, imageAdd, 0, 0, x, y, width, height);
    }

    protected void removeFromImage(BufferedImage edit, int x, int y, int width, int height) {
        ImageProcessor.removeFromImage(edit, imageRemove, 0, 0, x, y, width, height);
    }

    protected void editsToImage(BufferedImage editImage, int x, int y, int width, int height) {
        for (int i = 0; i < edits.size(); i++) {
            Edit edit = edits.get(i);
            boolean a = (edit.getUpdateCoordinates().x + edit.getUpdateImage().getWidth()) > x;
            boolean b = edit.getUpdateCoordinates().x < (x + width);
            boolean c = (edit.getUpdateCoordinates().y + edit.getUpdateImage().getHeight()) > y;
            boolean d = edit.getUpdateCoordinates().y < (y + height);
            if (a && b && c && d) {
                if (edit.isAdd()) {
                    ImageProcessor.addToImage(editImage, edit.getUpdateImage(), edit.getUpdateCoordinates().x, edit.getUpdateCoordinates().y, x, y, width, height);
                } else {
                    ImageProcessor.removeFromImage(editImage, edit.getUpdateImage(), edit.getUpdateCoordinates().x, edit.getUpdateCoordinates().y, x, y, width, height);
                }
            }
        }

    }

    protected void changesArrayUpdate(boolean[][] array, int x, int y, int width, int height) {
        int x2 = Math.min(width + x, array.length * BLOCK_SIZE);
        int y2 = Math.min(height + y, array[0].length * BLOCK_SIZE);

        x = Math.max(x / BLOCK_SIZE, 0);
        y = Math.max(y / BLOCK_SIZE, 0);
        x2 = (x2 - 1) / BLOCK_SIZE;
        y2 = (y2 - 1) / BLOCK_SIZE;
        for (int xx = x; xx <= x2; xx++) {
            for (int yy = y; yy <= y2; yy++) {
                array[xx][yy] = true;
            }
        }
    }

    public void setResolution(int width, int height) {
        if (imageAdd != null) {
            makeEdits();
        }
        imageRemove = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        imageAdd = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        int w = width / BLOCK_SIZE + 1;
        int h = height / BLOCK_SIZE + 1;
        changesRemove = new boolean[w][h];
        changesAdd = new boolean[w][h];
        layerImageCopy = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
    }

    public void paintRemove(BufferedImage remove, int x, int y) {
        synchronized (this) {
            anyChange = true;
            ImageProcessor.paintRemove(imageRemove, remove, x, y);
            ImageProcessor.removeFromImage(layerImageCopy, remove, x, y);
            changesArrayUpdate(changesRemove, x, y, remove.getWidth(), remove.getHeight());
        }
    }

    public void paintAdd(BufferedImage add, int x, int y) {
        synchronized (this) {
            anyChange = true;
            ImageProcessor.paintAdd(imageAdd, add, x, y);
            ImageProcessor.addToImage(layerImageCopy, add, x, y);
            changesArrayUpdate(changesAdd, x, y, add.getWidth(), add.getHeight());
        }
    }

    public BufferedImage addLocalChanges(BufferedImage source, int x, int y, int width, int height) {
        synchronized (this) {
            if (isLayerImageInvalid) {
                isLayerImageInvalid = false;
                layerImageCopy = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
                source.copyData(layerImageCopy.getRaster());
                editsToImage(layerImageCopy, x, y, width, height);
                if (anyChange) {
                    addToImage(layerImageCopy, x, y, width, height);
                    removeFromImage(layerImageCopy, x, y, width, height);
                }
            }
            return layerImageCopy;
        }
    }

    public void invalidate() {
        synchronized (this) {
            isLayerImageInvalid = true;
        }
    }

    public void updateReceived(int updateID) {
        invalidate();
        synchronized (this) {
            for (int i = 0; i < edits.size(); i++) {
                if (edits.get(i).getUpdateID() == updateID) {
                    edits.remove(i);
                    return;
                }
            }
        }
    }

    public void update() {
        controlTimeOutEdits();
        if (anyChange) {
            makeEdits();
        }
    }
}
