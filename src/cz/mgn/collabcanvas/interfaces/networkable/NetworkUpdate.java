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
package cz.mgn.collabcanvas.interfaces.networkable;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Update of canvas which is distributed online (by network).
 *
 * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface NetworkUpdate {

    /**
     * Returns unique ID of update.
     */
    public int getUpdateID();

    /**
     * Returns ID of layer which is updated with this update.
     *
     * @see cz.mgn.collabcanvas.interfaces.paintable.Paintable
     */
    public int getUpdateLayerID();

    /**
     * Returns ID of canvas which contains updated layer.
     */
    public int getUpdateCanvasID();

    /**
     * Returns if update (image) is aditive or substractive.
     *
     * @return true if change is aditive
     *
     * @see cz.mgn.collabcanvas.interfaces.paintable.PaintImage#isAddChange()
     */
    public boolean isAdd();

    /**
     * Returns this update coordinates relative to up-left corner of updated
     * canvas in not scaled size.
     */
    public Point getUpdateCoordinates();

    /**
     * Returns update image.
     *
     * @return update image
     *
     * @see cz.mgn.collabcanvas.interfaces.paintable.PaintImage#getImage()
     */
    public BufferedImage getUpdateImage();
}
