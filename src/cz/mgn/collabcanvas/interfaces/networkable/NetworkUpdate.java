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
 *
 *      @author Martin Indra <aktive@seznam.cz>
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
