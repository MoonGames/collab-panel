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
 * <p>Tool cursor is image like mouse cursor, but it's represent tool which with
 * will be painted (or something similar). Tool cursor is painted under mouse
 * cursor and differently of mouse cursor is zoomed with canvas.</p>
 *
 * @see cz.mgn.collabcanvas.interfaces.visible.Visible
 * @see cz.mgn.collabcanvas.interfaces.zoomable.Zoomable
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface ToolCursor {

    /**
     * Center of tool cursor will be on same position as mouse point.
     */
    public static final int LOCATION_MODE_CENTER = 0;
    /**
     * Up left corner of tool cursor will be on same position as mouse point.
     */
    public static final int LOCATION_MODE_UP_LEFT_CORNER = 1;

    /**
     * Returns relative shift of cursor stick point relative to mouse point.
     *
     * @return cursor position shift
     */
    public Point getRelativeLocatoin();

    /**
     * Returns mode of cursor positioning. Cursor is position is based on mouse
     * position. There are several positioning modes.
     *
     * @see #LOCATION_MODE_CENTER
     * @see #LOCATION_MODE_UP_LEFT_CORNER
     *
     * @return cursor positioning mod
     */
    public int getLocationMode();

    /**
     * Returns not scaled cursor image.
     *
     * @return image of cursor
     */
    public BufferedImage getCursorImage();

    /**
     * Returns if this cursor providing own scaling or if it should be scaled by
     * canvas internal algorithms.
     *
     * @return true if own scaling is supported
     *
     * @see cz.mgn.collabcanvas.interfaces.zoomable.Zoomable
     */
    public boolean isScalingSupported();

    /**
     * Returns scaled cursor image. Will be called only if scaling is supported.
     *
     * @param scale size of scale. This value is same as zoom.
     *
     * @return scaled cursor image
     *
     * @see #isScalingSupported()
     * @see cz.mgn.collabcanvas.interfaces.zoomable.Zoomable
     */
    public BufferedImage getScaledCursorImage(float scale);
}
