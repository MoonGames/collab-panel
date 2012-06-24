/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.utils.dirty;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author indy
 */
public abstract class Dirty {

    protected ArrayList<Rectangle> dirtyRectangles = new ArrayList<Rectangle>();

    /**
     * vrati seznam zmenenych ploch
     */
    public ArrayList<Rectangle> getDirtyRectangles() {
        return dirtyRectangles;
    }

    public abstract void addDirty(Rectangle rect);

    public void addDirty(Dirty dirty) {
        ArrayList<Rectangle> rects = dirty.getDirtyRectangles();
        for (Rectangle rect : rects) {
            addDirty(rect);
        }
    }
}
