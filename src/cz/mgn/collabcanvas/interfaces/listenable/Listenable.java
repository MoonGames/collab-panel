/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.listenable;

import java.util.Set;

/**
 *
 *   @author indy
 */
public interface Listenable {

    /**
     * vrati seznam soucasnych posluchacu udalosti
     */
    public Set<CollabPanelListener> getListeners();

    /**
     * prida novy posluchac udalosti
     */
    public void addListener(CollabPanelListener listener);

    /**
     * smaze posluchace a vrati true v pripade ze takovy neexistuje vrati false
     */
    public boolean removeListener(CollabPanelListener listener);
}
