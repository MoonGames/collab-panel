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
package cz.mgn.collabcanvas.interfaces.visible;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * <p>Tool image can be used like preview of something which will be painted.
 * It's image painted under mouse cursor and tool cursor, it's moving with mouse
 * and it's scaling with canvas.</p>
 *
 * @see cz.mgn.collabcanvas.interfaces.visible.Visible
 * @see cz.mgn.collabcanvas.interfaces.zoomable.Zoomable
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface ToolImage {

    /**
     * Returns coordinates of left up corner of image relative to mouse pointer.
     * This coordinates is scaled with canvas.
     */
    public Point getRelativeLocatoin();

    /**
     * Return tool image in original size.
     */
    public BufferedImage getToolImage();

    /**
     * Returns if this tool image can scale self (by self algorithm) if not will
     * be scaled by canvas algorithm.
     */
    public boolean isScalingSupported();

    /**
     * Returns scaled image. Can be called only if scaling is supported in this
     * tool imaga.
     *
     * @param scale scale size (same value as zoom)
     *
     * @see #isScalingSupported()
     * @see cz.mgn.collabcanvas.interfaces.zoomable.Zoomable
     */
    public BufferedImage getScaledToolImage(float scale);
}
