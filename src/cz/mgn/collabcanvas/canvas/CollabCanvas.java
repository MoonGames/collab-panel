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
 * <p>Main and central class of canvas.</p>
 *
 * <p>Canvas can be started in two modes. First is offline mode, it's just
 * simple painting canvas. Second mode is network mode, it's means that
 * everything is painted to temporary area, then it's sent to some interface for
 * online distribution and when data is received from online area through some
 * interface it's painted permanently.</p>
 *
 * <p><strong>Important:</strong> call destroy after end of usage of canvas.</p>
 *
 * @see #destroy()
 * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CollabCanvas implements Informing {

    protected boolean network = false;
    protected int canvasID = 0;
    protected CanvasImage canvasImage;
    protected CanvasPanel canvasPanel;

    /**
     * Do not create instance directly use Collab canvas factory.
     *
     * @param networkMode if canvas may start in network mode
     * @param idGenerator ID generator
     * @param canvasID this canvas ID, should be unique in application
     *
     * @see cz.mgn.collabcanvas.factory.CollabCanvasFactory
     * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable
     * @see cz.mgn.collabcanvas.interfaces.networkable.NetworkIDGenerator
     */
    public CollabCanvas(boolean networkMode, NetworkIDGenerator idGenerator, int canvasID) {
        this.network = networkMode;
        this.canvasID = canvasID;
        canvasImage = new CanvasImage(network, idGenerator, canvasID);
        canvasPanel = new CanvasPanel(canvasImage);
        canvasImage.setChangeListener(canvasPanel);
    }

    /**
     * Returns SWING component of canvas.
     *
     * @see javax.swing.JComponent
     */
    public JComponent getCanvasComponent() {
        return canvasPanel;
    }

    /**
     * Returns informing interface.
     *
     * @see cz.mgn.collabcanvas.interfaces.informing.Informing
     */
    public Informing getInforming() {
        return this;
    }

    /**
     * Returns listenable interface.
     *
     * @see cz.mgn.collabcanvas.interfaces.listenable.Listenable
     */
    public Listenable getListenable() {
        return canvasPanel;
    }

    /**
     * If canvas is started in network mode returns network interface, otherwise
     * returns null.
     *
     * @see cz.mgn.collabcanvas.interfaces.networkable.Networkable
     */
    public Networkable getNetworkable() {
        return network ? canvasImage : null;
    }

    /**
     * Returns paint interface.
     *
     * @see cz.mgn.collabcanvas.interfaces.paintable.Paintable
     */
    public Paintable getPaintable() {
        return canvasImage;
    }

    /**
     * Returns selection interface.
     *
     * @see cz.mgn.collabcanvas.interfaces.selectionable.Selectionable
     */
    public Selectionable getSelectionable() {
        return canvasImage;
    }

    /**
     * Returns visible things interface.
     *
     * @see cz.mgn.collabcanvas.interfaces.visible.Visible
     */
    public Visible getVisible() {
        return canvasPanel.getVisible();
    }

    /**
     * Returns zoom interface.
     *
     * @see cz.mgn.collabcanvas.interfaces.zoomable.Zoomable
     */
    public Zoomable getZoomable() {
        return canvasImage;
    }

    /**
     * Destroy canvas, must be called to end all canvas threads.
     */
    public void destroy() {
        canvasImage.destroy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<InfoListener> getInfoListeners() {
        return canvasImage.getInfoListeners();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addInfoListener(InfoListener listener) {
        canvasImage.addInfoListener(listener);
        canvasPanel.getInforming().addInfoListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeInfoListener(InfoListener listener) {
        canvasImage.removeInfoListener(listener);
        return canvasPanel.getInforming().removeInfoListener(listener);
    }
}
