/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image;

import java.awt.Rectangle;

/**
 *
 * @author indy
 */
public interface CanvasImageChangeListener {

    /**
     * zmena v uz zoomovanych souradnicich
     */
    public void change(Rectangle rect);

    public void selectionChange();

    public void imageResized(int width, int height);

    public void zoomChanged(float zoom);
}
