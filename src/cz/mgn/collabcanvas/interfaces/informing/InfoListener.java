/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.informing;

/**
 *
 *   @author indy
 */
public interface InfoListener {

    /**
     * informuje o zmene zoomu a preda novou hodnotu zoomu
     */
    public void zoomChanged(float zoom);

    /**
     * informuje o nove poloze mysi, hodnoty -1 = mys je mimo kreslici oblast
     */
    public void mouseMoved(float mouseX, float mouseY);
}
