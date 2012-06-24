/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.informing;

import java.util.Set;

/**
 *
 *   @author indy
 */
public interface Informing {

    /**
     *  vrati seznam soucasnych posluchacu udalosti
     */
    public Set<InfoListener> getInfoListeners();

    /**
     *  prida novy posluchac udalosti
     */
    public void addInfoListener(InfoListener listener);

    /**
     *  smaze posluchace a vrati true v pripade ze takovy neexistuje vrati false
     */
    public boolean removeInfoListener(InfoListener listener);
}
