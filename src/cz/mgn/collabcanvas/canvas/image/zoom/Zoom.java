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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image.zoom;

import java.awt.AlphaComposite;
import java.awt.Color;
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
        g.setComposite(AlphaComposite.Clear);
        g.setColor(Color.WHITE);
        g.fillRect(update.x, update.y, update.width, update.height);
        g.setComposite(AlphaComposite.SrcOver);
        g.drawImage(from, update.x, update.y, x2, y2, update.x, update.y, x2, y2, null);
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
