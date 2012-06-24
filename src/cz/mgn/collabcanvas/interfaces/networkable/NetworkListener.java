/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.networkable;

/**
 *
 *    @author indy
 */
public interface NetworkListener {

    /**
     * zadost o odeslani updatu
     */
    public void sendUpdate(NetworkUpdate update);

    /**
     * informuje o tom ze update ktery se stale nevratil ze site byl kvuli
     * expire time nebo mnozstvi smazan z pameti
     */
    public void unreachedUpdateRemoved(NetworkUpdate update);
}
