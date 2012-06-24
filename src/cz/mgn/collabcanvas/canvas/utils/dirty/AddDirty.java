/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.utils.dirty;

import java.awt.Rectangle;

/**
 *
 * @author indy
 */
public class AddDirty extends Dirty {

    @Override
    public void addDirty(Rectangle rect) {
        dirtyRectangles.add(rect);
    }
}
