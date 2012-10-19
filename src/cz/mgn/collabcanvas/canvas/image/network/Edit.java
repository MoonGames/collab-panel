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
