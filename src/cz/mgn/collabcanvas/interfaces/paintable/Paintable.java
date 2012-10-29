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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * <p>This interface is used for raster painting on canvas and manipulating with
 * layers.</p>
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Paintable {

    public static final int LAYER_VISIBLITY_DOESNT_EXIST = -1;
    public static final int LAYER_VISIBLITY_INVISIBLE = 0;
    public static final int LAYER_VISIBLITY_VISIBLE = 1;

    /**
     * Paint to currently selected layer.
     *
     * @param paintData what will be painted
     *
     * @see cz.mgn.collabcanvas.interfaces.paintable.PaintData
     */
    public void paint(PaintData paintData);

    /**
     * Select layer with specified ID, if layer with this id doesn't exist
     * nothing happends.
     *
     * @param layerID ID of layer
     */
    public void selectLayer(int layerID);

    /**
     * <p>Sets layers order, add not existing layers and optionally removes
     * layers which is not contained in order array. If removing is turned off,
     * layers which are not contained in order parameter are filed on end of
     * order.</p>
     *
     * <p>Example when option remove is turned off: Layers before calling this
     * method are [1, 2, 3, 4, 5]. Order parameter of method contains this
     * layers [2, 1, 4, 7]. And layers after calling this method are [2o, 1o,
     * 4o, 7n, 3o, 5o], where "o" suffix means that layer is not currently
     * created and "n" means that is new layer.</p>
     *
     * <p>Example when option remove is turned on: Layers before calling this
     * method are [1, 2, 3, 4, 5]. Order parameter of method contains this
     * layers [2, 1, 4, 7]. And layers after calling this method are [2o, 1o,
     * 4o, 7n], where "o" suffix means that layer is not currently created and
     * "n" means that is new layer.</p>
     *
     * @param remove says if removing layers is turned on
     *
     * @see #getLayersOrder()
     */
    public void setLayersOrder(int[] order, boolean remove);

    /**
     * Returns all layers IDs in his order. First layer (in array on index 0) is
     * painted under all other layers (it's painted first).
     */
    public int[] getLayersOrder();

    /**
     * nastavi jestli je vrstva viditelna
     */
    public void setLayerVisibility(int layerID, boolean visible);

    /**
     * Returns if layer is visible.
     *
     * @see #LAYER_VISIBLITY_DOESNT_EXIST
     * @see #LAYER_VISIBLITY_INVISIBLE
     * @see #LAYER_VISIBLITY_VISIBLE
     */
    public int getLayerVisibility(int layerID);

    /**
     * Sets opaqueness of layer.
     *
     * @param layerID ID of layer
     * @param opaqueness new opaqueness of layer
     *
     * @see #getLayerOpaqueness(int)
     */
    public void setLayerOpaqueness(int layerID, float opaqueness);

    /**
     * Returns opaqueness of layer. It's float number between 0 and 1 (including
     * both 0 and 1). This number multiplied by 100 is percentage of opaqueness
     * of layer (if layer is totally transparent returns 0, if it's
     * half-transparent it returns 0.5 and if it's totally opaque it's returns
     * 1).
     *
     * @param layerID ID of layer
     *
     * @return returns -1 if layer with this ID doesn't exists otherwise layer
     * opaqueness
     *
     * @see #getLayersOrder()
     */
    public float getLayerOpaqueness(int layerID);

    /**
     * <p>Create and add new layer to canvas. Layer will be add on top of all
     * layers.</p>
     *
     * <p>Method is functional only if canvas is in network mode.</p>
     *
     * @return ID of new layer, -1 if layer wasn't created (network mode)
     *
     * @see cz.mgn.collabcanvas.canvas.CollabCanvas CollabCanvas - see network
     * mode documentation
     * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable Networkable -
     * see network mode documentation
     */
    public int addLayer();

    /**
     * <p>Removes layer.</p>
     *
     * <p>Method is functional only if canvas is in network mode.</p>
     *
     * @param layerID ID of layer to deletion
     *
     * @return true if layer was correctly deleted, false if layer with this ID
     * doesn't exist or if canvas is in network mode
     *
     * @see cz.mgn.collabcanvas.canvas.CollabCanvas CollabCanvas - see network
     * mode documentation
     * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable Networkable -
     * see network mode documentation
     */
    public boolean removeLayer(int layerID);

    /**
     * Returns not scaled width of canvas.
     */
    public int getWidth();

    /**
     * Returns not scaled height of canvas.
     */
    public int getHeight();

    /**
     * <p>Change canvas resolution. Old canvas is painted to left up corner in
     * new canvas, so right and bottom margin can be cut out or there can be
     * empty area. Below is example.</p>
     *
     * <code><pre>
     * RESOLUTION IS DIMINISHED (bigger -> smaller):
     *
     * BEFORE:
     *
     *  -------------
     * |             |
     * | S C R E E N |
     * |             |
     * |  image...   |
     *  -------------
     *
     * AFTER:
     *
     *  -------
     * |       |
     * | S C R |
     * |       |
     *  -------
     *
     * </pre></code>
     *
     * <code><pre>
     * RESOLUTION IS AUGMENTED (smaller -> bigger):
     *
     * BEFORE:
     *
     *  -------------
     * |             |
     * | S C R E E N |
     * |             |
     * |  image...   |
     *  -------------
     *
     * AFTER (* is transparency):
     *
     *  ------------------
     * |             *****|
     * | S C R E E N *****|
     * |             *****|
     * |  image...   *****|
     * |******************|
     *  ------------------
     *
     * </pre></code>
     */
    public void setResolution(int width, int height);

    /**
     * Returns (part) of selected layer as image.
     *
     * @param rect rectangle specifying part of image, null is equal to all
     * image
     *
     * @return image of (part) layer
     */
    public BufferedImage getSelectedLayerImage(Rectangle rect);

    /**
     * Returns (part of) canvas represented as image.
     *
     * @param rect rectangle specifying part of image, null is equal to all
     * image
     *
     * @return image of (part) canvas
     */
    public BufferedImage getImage(Rectangle rect);

    /**
     * <p>Returns selected part of canvas, remaining (not selected) area will be
     * transparent in image. Example is below.</p>
     *
     * <code><pre>
     * CANVAS IMAGE:
     * # - selected area:
     *
     *  -------------
     * |     ##      |
     * | ######      |
     * | ######      |
     * |             |
     *  -------------
     *
     * RETURNED IMAGE:
     * x - pixels copyed from canvas
     * * - transparency
     *
     *  -------------
     * |*****xx******|
     * |*xxxxxx******|
     * |*xxxxxx******|
     * |*************|
     *  -------------
     * </pre></code>
     *
     * @see cz.mgn.collabcanvas.interfaces.selectionable.Selectionable
     */
    public BufferedImage getImageSelection();

    /**
     * Returns selected part of currently selected layer.
     *
     * @see #getImageSelection()
     * @see cz.mgn.collabcanvas.interfaces.selectionable.Selectionable
     */
    public BufferedImage getSelectedLayerImageSelection();
}
