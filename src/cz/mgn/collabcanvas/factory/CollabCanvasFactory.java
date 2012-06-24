/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.factory;

import cz.mgn.collabcanvas.canvas.CollabCanvas;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator;

/**
 *
 * @author indy
 */
public class CollabCanvasFactory {

    /**
     * vytvori sitovy CollabCanvas
     */
    public static CollabCanvas createNetworkCollabCanvas(NetworkIDGenerator idGenerator, int canvasID) {
        return new CollabCanvas(true, idGenerator, canvasID);
    }

    public static CollabCanvas createLocalCollabCanvas(int canvasID) {
        return new CollabCanvas(false, null, canvasID);
    }
}
