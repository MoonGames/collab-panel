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
public interface ToolCursor {

    public static final int LOCATION_MODE_CENTER = 0;
    public static final int LOCATION_MODE_UP_LEFT_CORNER = 1;

    /**
     * vrati souradnici relativniho umisteni cursoru vuci mysi
     */
    public Point getRelativeLocatoin();

    /**
     * vrati mode relativni lokace kurzoru (LOCATION_MODE_CENTER, ...)
     */
    public int getLocationMode();

    /**
     * vrati obrazek kurzoru v originalni velikosti
     */
    public BufferedImage getCursorImage();

    /**
     * vrati jestli podporuje scalovani kurzoru
     */
    public boolean isScalingSupported();

    /**
     * vrati scalovany obrazek kurzoru
     */
    public BufferedImage getScaledCursorImage(float scale);
}
