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

package cz.mgn.collabcanvas.canvas.image.layers;

import cz.mgn.collabcanvas.canvas.image.network.NetworkImage;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class Layer {

    protected int id;
    protected float opaqueness = 1f;
    protected BufferedImage image;
    protected NetworkImage networkImage = null;

    public Layer(int id, int width, int height) {
        this.id = id;
        setResolution(width, height);
    }

    public Layer(int id, int width, int height, NetworkImage networkImage) {
        this.id = id;
        this.networkImage = networkImage;
        setResolution(width, height);
    }

    public NetworkImage getNetworkImage() {
        return networkImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getID() {
        return id;
    }

    public float getOpaqueness() {
        return opaqueness;
    }

    public void setOpaqueness(float opaqueness) {
        this.opaqueness = opaqueness;
    }

    public void setResolution(int width, int height) {
        BufferedImage nImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        if (image != null) {
            Graphics g = nImage.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
        }
        image = nImage;
        if (networkImage != null) {
            networkImage.setResolution(width, height);
        }
    }
}
