/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.zoomable;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 *    @author indy
 */
public interface Zoomable {

    /**
     *  vrati zoom jako nasobek zakladni velikost (pred zoomem)
     */
    public float getZoom();

    /**
     *  pokusi se nastavit zoom a vrati skutecny zoom po provedeni akce
     */
    public float setZoom(float zoom);

    /**
     *  pokusi se pricist (zaporna odecist) k zoomu hodnotu, a vrati aktualni
     * zoom po provedeni akce
     */
    public float addToZoom(float zoom);

    /**
     * vrati transformovanou souradnici zoomem
     */
    public int transformCoordinate(int coordinate);

    /**
     * vrati transformovany bod zoomem
     */
    public Point transformPoint(Point point);

    /**
     * vrati transformovany obdelnik zoomem
     */
    public Rectangle transformRectangle(Rectangle rectangle);
}
