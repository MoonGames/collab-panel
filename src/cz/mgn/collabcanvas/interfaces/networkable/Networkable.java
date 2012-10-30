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

import java.util.Set;

/**
 * <p>This interface is for manipulating with online (network) part of canvas.
 * Can be used only if canvas is turned on in online mode.</p>
 *
 * <code><pre>
 * ONLINE PAINTING DIAGRAM:
 *
 * step 1       step 2                      step 3
 *   |           / \           removes from   | received
 *   | paint      | give for   temporary      | from online
 *   | something  | online     -------------- | area
 *  \ /           | process   |              \ /
 *  ----------------          |      ----------------
 * | Temporary area | <-------      | Permanent area |
 *  ----------------                 ----------------
 * </pre></code>
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Networkable {

    /**
     * Returns list of network listeners.
     *
     * @see cz.mgn.collabcanvas.interfaces.networkable.NetworkListener
     */
    public Set<NetworkListener> getListeners();

    /**
     * Add new network listener.
     *
     * @see cz.mgn.collabcanvas.interfaces.networkable.NetworkListener
     */
    public void addListener(NetworkListener listener);

    /**
     * Removes network listener from network listeners list.
     *
     * @return true if this network listener was in network listeners list
     *
     * @see cz.mgn.collabcanvas.interfaces.networkable.NetworkListener
     */
    public boolean removeListener(NetworkListener listener);

    /**
     * Inform canvas about update receive from online space.
     *
     * @see cz.mgn.collabcanvas.interfaces.networkable.NetworkUpdate
     */
    public void updateReceived(NetworkUpdate update);
}
