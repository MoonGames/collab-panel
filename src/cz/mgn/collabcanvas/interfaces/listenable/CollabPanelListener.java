/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.listenable;

/**
 *
 *    @author indy
 */
public interface CollabPanelListener {

    /**
     *  informace o nastale udalosti klavesnice
     */
    public void keyEvent(CollabPanelKeyEvent keyEvent);

    /**
     *  informace o nastale udalosti mysi
     */
    public void mouseEvent(CollabPanelMouseEvent mouseEvent);
}
