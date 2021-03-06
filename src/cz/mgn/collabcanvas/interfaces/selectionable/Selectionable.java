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
package cz.mgn.collabcanvas.interfaces.selectionable;

import java.awt.image.BufferedImage;

/**
 * <p>This interface allows work with selection on canvas.</p>
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Selectionable {

    /**
     * Returns image representing current selection. Alpha channel represents
     * value of selection (pixel for pixel). Size of alpha on pixel determines
     * value of selection (255 alpha (maximal) means 100% of selection)
     *
     * @see
     * cz.mgn.collabcanvas.interfaces.selectionable.SelectionUpdate#getUpdateImage()
     */
    public BufferedImage getSelectionImage();

    /**
     * Select all.
     */
    public void selectAll();

    /**
     * Update current selection by selection update.
     *
     * @see cz.mgn.collabcanvas.interfaces.selectionable.SelectionUpdate
     */
    public void select(SelectionUpdate selectoinUpdate);

    /**
     * Returns if is selected all by selectAll() call.
     *
     * @see
     * cz.mgn.collabcanvas.interfaces.selectionable.Selectionable#selectAll()
     */
    public boolean isSelectedAll();
}
