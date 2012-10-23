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

package cz.mgn.collabcanvas.interfaces.selectionable;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface SelectionUpdate {

    /**
     * replace current selections by this
     */
    public static final int MODE_REPLACE = 0;
    /**
     * set union operation
     */
    public static final int MODE_UNIOIN = 1;
    /**
     * relative complement operation (DESTINATION \ SOURCE)
     */
    public static final int MODE_REMOVE = 2;
    /**
     * sets intersection operation
     */
    public static final int MODE_INTERSECTION = 3;
    /**
     * set symmetric difference operatoin
     */
    public static final int MODE_XOR = 4;

    /**
     * vrati mode updatu MODE_NEW, MODE_ADD, ...
     */
    public int getUpdateMode();

    /**
     * vrati souradnice umisteni updatu na obrazku
     */
    public Point getUpdateCoordinates();

    /**
     * vrati velikost aplikace <0, 1>
     */
    public float getApplyAmount();

    /**
     * vrati obrazek selekce
     */
    public BufferedImage getUpdateImage();
}
