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
package cz.mgn.collabcanvas.interfaces.listenable;

import java.util.Set;

/**
 * <p>This interface is used for control user events over canvas (like mouse
 * motion, key events...).</p>
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Listenable {

    /**
     * Returns list of event listeners.
     *
     * @see cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener
     */
    public Set<CollabPanelListener> getListeners();

    /**
     * Add new event listener to listeners list.
     *
     * @see cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener
     */
    public void addListener(CollabPanelListener listener);

    /**
     * Removes event listener from listeners list.
     *
     * @return returns false if this event listener wasn't in listeners list
     *
     * @see cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener
     */
    public boolean removeListener(CollabPanelListener listener);
}
