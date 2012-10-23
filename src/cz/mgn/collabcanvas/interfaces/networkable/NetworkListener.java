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

/**
 *
 *    @author Martin Indra <aktive@seznam.cz>
 */
public interface NetworkListener {

    /**
     * zadost o odeslani updatu
     */
    public void sendUpdate(NetworkUpdate update);

    /**
     * informuje o tom ze update ktery se stale nevratil ze site byl kvuli
     * expire time nebo mnozstvi smazan z pameti
     */
    public void unreachedUpdateRemoved(NetworkUpdate update);
}
