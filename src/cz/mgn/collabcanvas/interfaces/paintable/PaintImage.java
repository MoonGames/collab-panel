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
package cz.mgn.collabcanvas.interfaces.paintable;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>Image for paint on canvas.</p>
 *
 * <p>This image can be painted on canvas multiple. Every apply is specified by
 * apply point.</p>
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface PaintImage {

    /**
     * Returns all apply points in specified order. Point is relative
     * coordinates left-up corner of paint image on canvas in not scaled size.
     */
    public ArrayList<Point> getApplyPoints();

    /**
     * <p>Returns paint image.</p>
     *
     * <p>In case of substractive image. Alpha channel of this image determines
     * how much of color will be earsed (
     * <code>{At} = ({Ac} * (255 - {Au}) / (255 * 255))</code>, where At is
     * target alpha, Ac is current alpha and Au is alpha in this image)</p>
     */
    public BufferedImage getImage();

    /**
     * Returns if this image is additive or substractive.
     *
     * @see #getImage()
     */
    public boolean isAddChange();
}
