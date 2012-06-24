/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image.network;

import cz.mgn.collabcanvas.interfaces.networkable.NetworkUpdate;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public class Edit implements NetworkUpdate {

    protected int updateID;
    protected int layerID;
    protected int canvasID;
    protected boolean add;
    protected Point coordinates;
    protected BufferedImage image;
    protected long time;

    public Edit(int updateID, int layerID, int canvasID, boolean add, Point coordinates, BufferedImage image, long time) {
        this.updateID = updateID;
        this.layerID = layerID;
        this.canvasID = canvasID;
        this.add = add;
        this.coordinates = coordinates;
        this.image = image;
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int getUpdateID() {
        return updateID;
    }

    @Override
    public int getUpdateLayerID() {
        return layerID;
    }

    @Override
    public int getUpdateCanvasID() {
        return canvasID;
    }

    @Override
    public boolean isAdd() {
        return add;
    }

    @Override
    public Point getUpdateCoordinates() {
        return coordinates;
    }

    @Override
    public BufferedImage getUpdateImage() {
        return image;
    }
}
