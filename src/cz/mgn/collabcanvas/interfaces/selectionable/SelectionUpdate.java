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

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * <p>This class has informations about update of selection (like union current
 * selection with square with size ...).</p>
 *
 * @see cz.mgn.collabcanvas.interfaces.selectionable.Selectionable
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface SelectionUpdate {

    /**
     * <p>Replace current selections by this (result of select will be area
     * contained in this object).</p>
     *
     * <p> Examples are below. <br>* - canvas margin,<br>- - free space,<br>a -
     * only in current selection,<br>b - only in selection update area,<br>x -
     * together in current selection and selection update </p>
     *
     * <code><pre>
     * ********
     * *-axb--*
     * *-axb--*
     * ********
     * </pre></code>
     *
     * <p>Selection result, # - selected area</p>
     *
     * <code><pre>
     * ********
     * *--##--*
     * *--##--*
     * ********
     * </pre></code>
     */
    public static final int MODE_REPLACE = 0;
    /**
     * <p> Union current selection with this selection (result of select will be
     * area containing all pixels in this and current selection, operator + is
     * used).</p>
     *
     * <p> Examples are below. <br>* - canvas margin,<br>- - free space,<br>a -
     * only in current selection,<br>b - only in selection update area,<br>x -
     * together in current selection and selection update </p>
     *
     * <code><pre>
     * ********
     * *-axb--*
     * *-axb--*
     * ********
     * </pre></code>
     *
     * <p>Selection result, # - selected area</p>
     *
     * <code><pre>
     * ********
     * *-###--*
     * *-###--*
     * ********
     * </pre></code>
     */
    public static final int MODE_UNIOIN = 1;
    /**
     * Remove this selection from current selection (result will be selection
     * containing only pixels which is in current selection but not in this
     * selection, operator relative complement (\) is used).
     *
     * <p> Examples are below. <br>* - canvas margin,<br>- - free space,<br>a -
     * only in current selection,<br>b - only in selection update area,<br>x -
     * together in current selection and selection update </p>
     *
     * <code><pre>
     * ********
     * *-axb--*
     * *-axb--*
     * ********
     * </pre></code>
     *
     * <p>Selection result, # - selected area</p>
     *
     * <code><pre>
     * ********
     * *-#----*
     * *-#----*
     * ********
     * </pre></code>
     */
    public static final int MODE_REMOVE = 2;
    /**
     * Intersect this selection with current selection (result will be selection
     * with pixels which is contained both in this and current selection).
     *
     * <code><pre>
     * ********
     * *-axb--*
     * *-axb--*
     * ********
     * </pre></code>
     *
     * <p>Selection result, # - selected area</p>
     *
     * <code><pre>
     * ********
     * *--#---*
     * *--#---*
     * ********
     * </pre></code>
     */
    public static final int MODE_INTERSECTION = 3;
    /**
     * XOR this selection with current selection (result will be selection
     * containing pixel which is contained in current selection or this
     * selection but not both, symmetric difference operation will be used).
     *
     *
     * <code><pre>
     * ********
     * *-axb--*
     * *-axb--*
     * ********
     * </pre></code>
     *
     * <p>Selection result, # - selected area</p>
     *
     * <code><pre>
     * ********
     * *-#-#--*
     * *-#-#--*
     * ********
     * </pre></code>
     */
    public static final int MODE_XOR = 4;

    /**
     * Returns mode of update.
     *
     * @see #MODE_REPLACE
     * @see #MODE_INTERSECTION
     * @see #MODE_REMOVE
     * @see #MODE_UNIOIN
     * @see #MODE_XOR
     */
    public int getUpdateMode();

    /**
     * Returns coordinates of selection update, relative to up left corner of
     * canvas in not scaled size.
     *
     * @return Point representing coordinates
     */
    public Point getUpdateCoordinates();

    /**
     * <p>Return apply factor. It's number between 0 and 1 (includes 1). One
     * mean 100% apply, zero 0%.</p>
     *
     * <p>If result of selection is 50% selected area, thats mean that
     * everything will be painted to this area will be half-transparent.</p>
     */
    public float getApplyAmount();

    /**
     * <p>Returns selection image.</p>
     *
     * <p>Alpha channel in this image representing amount of selection (pixel to
     * pixel).</p>
     *
     * <p>If pixel has alpha value 255 (maximal) it's means 100% of pixel will
     * be selected. If pixel has alpha value 0 it's means 0% of pixel will be
     * selected. Generally percentage of pixel selection is equal to
     * <code>({alpha value} * 100) / 255</code>.</p>
     *
     * @see #getApplyAmount() 
     */
    public BufferedImage getUpdateImage();
}
