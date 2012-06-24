/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas;

import cz.mgn.collabcanvas.canvas.image.CanvasImage;
import cz.mgn.collabcanvas.canvas.panel.CanvasPanel;
import cz.mgn.collabcanvas.interfaces.informing.InfoListener;
import cz.mgn.collabcanvas.interfaces.informing.Informing;
import cz.mgn.collabcanvas.interfaces.listenable.Listenable;
import cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator;
import cz.mgn.collabcanvas.interfaces.networkable.Networkable;
import cz.mgn.collabcanvas.interfaces.paintable.Paintable;
import cz.mgn.collabcanvas.interfaces.selectionable.Selectionable;
import cz.mgn.collabcanvas.interfaces.visible.Visible;
import cz.mgn.collabcanvas.interfaces.zoomable.Zoomable;
import java.util.Set;
import javax.swing.JComponent;

/**
 *
 * @author indy
 */
public class CollabCanvas implements Informing {

    protected boolean network = false;
    protected int canvasID = 0;
    protected CanvasImage canvasImage;
    protected CanvasPanel canvasPanel;

    public CollabCanvas(boolean networkMode, NetworkIDGenerator idGenerator, int canvasID) {
        this.network = networkMode;
        this.canvasID = canvasID;
        canvasImage = new CanvasImage(network, idGenerator, canvasID);
        canvasPanel = new CanvasPanel(canvasImage);
        canvasImage.setChangeListener(canvasPanel);
    }

    public JComponent getCanvasComponent() {
        return canvasPanel;
    }

    public Informing getInforming() {
        return this;
    }

    public Listenable getListenable() {
        return canvasPanel;
    }

    /**
     * return null if it's not int network mode
     */
    public Networkable getNetworkable() {
        return network ? canvasImage : null;
    }

    public Paintable getPaintable() {
        return canvasImage;
    }

    public Selectionable getSelectionable() {
        return canvasImage;
    }

    public Visible getVisible() {
        return canvasPanel.getVisible();
    }

    public Zoomable getZoomable() {
        return canvasImage;
    }
    
    public void destroy() {
        canvasImage.destroy();
    }

    public void destroy() {
        canvasImage.destroy();
    }


    @Override
    public Set<InfoListener> getInfoListeners() {
        return canvasImage.getInfoListeners();
    }

    @Override
    public void addInfoListener(InfoListener listener) {
        canvasImage.addInfoListener(listener);
        canvasPanel.getInforming().addInfoListener(listener);
    }

    @Override
    public boolean removeInfoListener(InfoListener listener) {
        canvasImage.removeInfoListener(listener);
        return canvasPanel.getInforming().removeInfoListener(listener);
    }
}
