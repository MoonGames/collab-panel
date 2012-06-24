/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.networkable;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 *      @author indy
 */
public interface NetworkUpdate {

    /**
     * vrati ID updatu
     */
    public int getUpdateID();

    /**
     * vrati ID vrstvy kterou updatuje
     */
    public int getUpdateLayerID();

    /**
     * vrati ID canvasu, ktery updatuje
     */
    public int getUpdateCanvasID();

    /**
     *    jestli je update pridavaci (v opacne pripade odebiraci)
     */
    public boolean isAdd();

    /**
     *   vrati souradnice updatu (umisteni leveho horniho rohu)
     */
    public Point getUpdateCoordinates();

    /**
     *    vrati obrazek updatu (v pripade ze je pridavaci se ma prikreslit, v
     * pripade ze odebiraci se ma smazat mnostvi udavane alphou v kazdem pixelu
     * (dstAlphaUpdated = dstAlpha * (1 - srcAlpha))
     */
    public BufferedImage getUpdateImage();
}
