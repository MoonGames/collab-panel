/*
 * Collab canvas - Java framework for graphics software enabling offline and shared
 * painting
 * Copyright (C) 2012 Martin Indra <aktive@seznam.cz>
 *
 * This file is part of Collab canvas.
 *
 * Collab canvas is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Collab canvas is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Collab canvas.  If not, see <http://www.gnu.org/licenses/>.
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
 * @author Martin Indra <aktive@seznam.cz>
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
