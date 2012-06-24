/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.visible;

/**
 *
 *   @author indy
 */
public interface Visible {

    /**
     * nastavi kurzor mysi
     */
    public void setMouseCursor(MouseCursor mouseCursor);

    /**
     * nastavi kurzor toolu
     */
    public void setToolCursor(ToolCursor toolCursor);

    /**
     * nastavi obrazek toolu
     */
    public void setToolImage(ToolImage toolImage);
}
