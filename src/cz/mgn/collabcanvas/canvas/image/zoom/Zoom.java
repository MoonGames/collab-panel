/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image.zoom;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public class Zoom {

    protected static final float ZOOM_MAX = 10f;
    protected static final float ZOOM_MIN = 0.1f;
    protected float zoom = 1f;

    /**
     * souradnici pred zoomem pretvori na souradnici po zoomu
     */
    public int transformCoordinate(int coordinate) {
        return (int) (coordinate * zoom);
    }

    /**
     * souradnici po zoomu pretvori na souradnici pred zoomem
     */
    public int detransformCoordinat(int coordinate) {
        return (int) (coordinate / zoom);
    }

    public void paintZoomed(BufferedImage from, BufferedImage to, Rectangle update) {
        Graphics2D g = (Graphics2D) to.getGraphics();
        g.scale(zoom, zoom);
        int x2 = update.x + update.width;
        int y2 = update.y + update.height;
        g.drawImage(to, update.x, update.y, x2, y2, update.x, update.y, x2, y2, null);
        g.dispose();
    }

    public float getZoom() {
        return zoom;
    }

    public float trySetZoom(float zoom) {
        if (zoom > ZOOM_MAX) {
            zoom = ZOOM_MAX;
        }
        if (zoom < ZOOM_MIN) {
            zoom = ZOOM_MIN;
        }
        this.zoom = zoom;
        return zoom;
    }
}
