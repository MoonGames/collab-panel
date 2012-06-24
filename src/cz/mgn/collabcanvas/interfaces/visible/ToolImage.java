/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.visible;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 *   @author indy
 */
public interface ToolImage {

    /**
     *  vrati souradnici relativniho umisteni vzhledem k nescalovanymu obrazku
     * (levy horni roh)
     */
    public Point getRelativeLocatoin();

    /**
     *  vrati obrazek toolu v originalni velikosti
     */
    public BufferedImage getToolImage();

    /**
     *  vrati jestli podporuje scalovani obrazku toolu
     */
    public boolean isScalingSupported();

    /**
     *  vrati scalovany obrazek toolu
     */
    public BufferedImage getScaledToolImage(float scale);
}
