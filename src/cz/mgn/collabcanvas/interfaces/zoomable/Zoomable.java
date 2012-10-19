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
package cz.mgn.collabcanvas.interfaces.zoomable;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 *    @author indy
 */
public interface Zoomable {

    /**
     *  vrati zoom jako nasobek zakladni velikost (pred zoomem)
     */
    public float getZoom();

    /**
     *  pokusi se nastavit zoom a vrati skutecny zoom po provedeni akce
     */
    public float setZoom(float zoom);

    /**
     *  pokusi se pricist (zaporna odecist) k zoomu hodnotu, a vrati aktualni
     * zoom po provedeni akce
     */
    public float addToZoom(float zoom);

    /**
     * vrati transformovanou souradnici zoomem
     */
    public int transformCoordinate(int coordinate);

    /**
     * vrati transformovany bod zoomem
     */
    public Point transformPoint(Point point);

    /**
     * vrati transformovany obdelnik zoomem
     */
    public Rectangle transformRectangle(Rectangle rectangle);
}
