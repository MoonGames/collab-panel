/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.selectionable;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public interface SelectionUpdate {

    /**
     * replace current selections by this
     */
    public static final int MODE_REPLACE = 0;
    /**
     * set union operation
     */
    public static final int MODE_UNIOIN = 1;
    /**
     * relative complement operation (DESTINATION \ SOURCE)
     */
    public static final int MODE_REMOVE = 2;
    /**
     * sets intersection operation
     */
    public static final int MODE_INTERSECTION = 3;
    /**
     * set symmetric difference operatoin
     */
    public static final int MODE_XOR = 4;

    /**
     * vrati mode updatu MODE_NEW, MODE_ADD, ...
     */
    public int getUpdateMode();

    /**
     * vrati souradnice umisteni updatu na obrazku
     */
    public Point getUpdateCoordinates();

    /**
     * vrati velikost aplikace <0, 1>
     */
    public float getApplyAmount();

    /**
     * vrati obrazek selekce
     */
    public BufferedImage getUpdateImage();
}
