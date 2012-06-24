/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.paintable;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 *   @author indy
 */
public interface PaintImage {

    /**
     * vrati seznam bodu, kde se ma zmena aplikovat
     */
    public ArrayList<Point> getApplyPoints();

    /**
     * vrati obrazek zmeny (v pripade odebirani velikost alpha kanalu urcuje
     * mnozstvi odebrani (alphaDst * (1 - aplhaSrc)))
     */
    public BufferedImage getImage();

    /**
     * vrati jestli se se ma zmena pridat nebo odebrat
     */
    public boolean isAddChange();
}
