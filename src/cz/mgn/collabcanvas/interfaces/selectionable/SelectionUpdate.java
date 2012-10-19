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
package cz.mgn.collabcanvas.interfaces.selectionable;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
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
