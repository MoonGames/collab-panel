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

package cz.mgn.collabcanvas.factory;

import cz.mgn.collabcanvas.canvas.CollabCanvas;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator;

public class CollabCanvasFactory {

    /**
     * vytvori sitovy CollabCanvas
     */
    public static CollabCanvas createNetworkCollabCanvas(NetworkIDGenerator idGenerator, int canvasID) {
        if (idGenerator == null) {
            throw new NullPointerException("Network ID generator cant be null!");
        }
        return new CollabCanvas(true, idGenerator, canvasID);
    }

    public static CollabCanvas createLocalCollabCanvas(int canvasID) {
        return new CollabCanvas(false, null, canvasID);
    }
}
