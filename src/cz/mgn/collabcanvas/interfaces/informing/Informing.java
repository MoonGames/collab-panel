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
package cz.mgn.collabcanvas.interfaces.informing;

import java.util.Set;

/**
 * 
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Informing {

    /**
     * get all current info listeners
     */
    public Set<InfoListener> getInfoListeners();

    /**
     * add new info listener
     *
     * @param listener info listener listens on changes on canvas, should
     * be used only for information (example: showing user current mouse
     * location)
     */
    public void addInfoListener(InfoListener listener);

    /**
     * try remove info listener
     *
     * @return true if this listener was in listeners list
     */
    public boolean removeInfoListener(InfoListener listener);
}
