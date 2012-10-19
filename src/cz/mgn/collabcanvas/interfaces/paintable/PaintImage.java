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
package cz.mgn.collabcanvas.interfaces.paintable;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 *   @author indy
 */
public interface PaintImage {

    /**
     * vrati seznam bodu, kde se ma zmena aplikovat
     */
    public ArrayList<Point> getApplyPoints();

    /**
     * vrati obrazek zmeny (v pripade odebirani velikost alpha kanalu urcuje
     * mnozstvi odebrani (alphaDst * (1 - aplhaSrc)))
     */
    public BufferedImage getImage();

    /**
     * vrati jestli se se ma zmena pridat nebo odebrat
     */
    public boolean isAddChange();
}
