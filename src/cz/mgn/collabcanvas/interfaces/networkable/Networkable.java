/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.networkable;

import java.util.Set;

/**
 *
 *    @author indy
 */
public interface Networkable {

    /**
     *   vrati seznam soucasnych posluchacu udalosti
     */
    public Set<NetworkListener> getListeners();

    /**
     *   prida novy posluchac udalosti
     */
    public void addListener(NetworkListener listener);

    /**
     *   smaze posluchace a vrati true v pripade ze takovy neexistuje vrati false
     */
    public boolean removeListener(NetworkListener listener);

    /**
     *  informuje o obdrzeni a zakresli novy update
     */
    public void updateReceived(NetworkUpdate update);
}
