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
package cz.mgn.collabcanvas.interfaces.networkable;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 *      @author indy
 */
public interface NetworkUpdate {

    /**
     * vrati ID updatu
     */
    public int getUpdateID();

    /**
     * vrati ID vrstvy kterou updatuje
     */
    public int getUpdateLayerID();

    /**
     * vrati ID canvasu, ktery updatuje
     */
    public int getUpdateCanvasID();

    /**
     *    jestli je update pridavaci (v opacne pripade odebiraci)
     */
    public boolean isAdd();

    /**
     *   vrati souradnice updatu (umisteni leveho horniho rohu)
     */
    public Point getUpdateCoordinates();

    /**
     *    vrati obrazek updatu (v pripade ze je pridavaci se ma prikreslit, v
     * pripade ze odebiraci se ma smazat mnostvi udavane alphou v kazdem pixelu
     * (dstAlphaUpdated = dstAlpha * (1 - srcAlpha))
     */
    public BufferedImage getUpdateImage();
}
