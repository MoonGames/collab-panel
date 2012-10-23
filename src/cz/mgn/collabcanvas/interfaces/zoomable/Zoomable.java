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

package cz.mgn.collabcanvas.interfaces.zoomable;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * This interface is used for working with canvas zoom.
 *
 * zoom - it's float number defining zoom size. By this number is multiplied
 * normal canvas size. For example, if this number is 0.75, canvas is zoomed to
 * 75% (it's size is 75% of normal size)
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Zoomable {

    /**
     * Get current canvas zoom.
     *
     * @return current zoom
     */
    public float getZoom();

    /**
     * Try to set canvas zoom and returns zooming result. If zoom value is over
     * available interval, dire value will be set.
     *
     * @param zoom try to zoom to this value
     *
     * @return zooming result (current zoom)
     */
    public float setZoom(float zoom);

    /**
     * Try to add value to current zoom (currentZoom = currentZoom + value). If
     * value is negative, current zoom will be smaller after this operation. If
     * current zoom plus this value is out of available interval, dire value
     * will be set.
     *
     * @param zoom value to add
     *
     * @return zooming result (current zoom)
     */
    public float addToZoom(float zoom);

    /**
     *
     * Transform coordinate by current zoom (coordinate will be multiplied by
     * current zoom: coordinate * currentZoom).
     *
     * @return transformed coordinate
     */
    public int transformCoordinate(int coordinate);

    /**
     * Transform point by current zoom (coordinates will be multiplied by
     * current zoom).
     *
     * @return transformed point
     */
    public Point transformPoint(Point point);

    /**
     * Transform rectangle by current zoom (coordinates and size will be
     * multiplied by current zoom).
     *
     * @return transformed rectangle
     */
    public Rectangle transformRectangle(Rectangle rectangle);
}
