/*
 * Collab canvas - Java framework for graphics software enabling offline and shared
 * painting
 * Copyright (C) 2012 Martin Indra <aktive@seznam.cz>
 *
 * This file is part of Collab canvas.
 *
 * Collab canvas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Collab canvas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Collab canvas.  If not, see <http://www.gnu.org/licenses/>.
 */

package cz.mgn.collabcanvas.canvas.image.network;

import cz.mgn.collabcanvas.interfaces.networkable.NetworkUpdate;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
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
