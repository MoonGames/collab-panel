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

/**
 * Factory for canvas, this class is the best place for creating new canvas
 * (new instance).
 *
 * @see cz.mgn.collabcanvas.canvas.CollabCanvas
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CollabCanvasFactory {

    /**
     * Creates new canvas in online (network) mode.
     *
     * @param idGenerator generator for ID's
     * @param canvasID unique canvas ID
     *
     * @return new online canvas
     *
     * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable
     * @see cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator
     * @see cz.mgn.collabcanvas.canvas.CollabCanvas
     */
    public static CollabCanvas createNetworkCollabCanvas(NetworkIDGenerator idGenerator, int canvasID) {
        if (idGenerator == null) {
            throw new NullPointerException("Network ID generator cant be null!");
        }
        return new CollabCanvas(true, idGenerator, canvasID);
    }

    /**
     * Creates new canvas in offline mode.
     *
     * @param canvasID unique canvas ID
     *
     * @return new offline canvas
     *
     * @see cz.mgn.collabcanvas.canvas.CollabCanvas
     */
    public static CollabCanvas createLocalCollabCanvas(int canvasID) {
        return new CollabCanvas(false, null, canvasID);
    }
}
