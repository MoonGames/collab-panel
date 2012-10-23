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

package cz.mgn.collabcanvas.canvas.utils.dirty;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public abstract class Dirty {

    protected ArrayList<Rectangle> dirtyRectangles = new ArrayList<Rectangle>();

    /**
     * vrati seznam zmenenych ploch
     */
    public ArrayList<Rectangle> getDirtyRectangles() {
        return dirtyRectangles;
    }

    public abstract void addDirty(Rectangle rect);

    public void addDirty(Dirty dirty) {
        ArrayList<Rectangle> rects = dirty.getDirtyRectangles();
        for (Rectangle rect : rects) {
            addDirty(rect);
        }
    }
}
