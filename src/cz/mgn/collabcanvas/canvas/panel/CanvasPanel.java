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

package cz.mgn.collabcanvas.canvas.panel;

import cz.mgn.collabcanvas.canvas.image.CanvasImage;
import cz.mgn.collabcanvas.canvas.image.CanvasImageChangeListener;
import cz.mgn.collabcanvas.canvas.panel.events.CanvasKeyHandler;
import cz.mgn.collabcanvas.canvas.panel.events.CanvasMouseEvent;
import cz.mgn.collabcanvas.canvas.panel.justcanvas.JustCanvas;
import cz.mgn.collabcanvas.interfaces.informing.Informing;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelMouseEvent;
import cz.mgn.collabcanvas.interfaces.listenable.Listenable;
import cz.mgn.collabcanvas.interfaces.visible.Visible;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CanvasPanel extends JPanel implements Listenable, CanvasImageChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {

    protected CanvasImage canvasImage;
    protected JScrollPane scrollPane;
    protected JustCanvas canvas;
    //listeners
    protected Set<CollabPanelListener> panelListeners = new TreeSet<CollabPanelListener>();
    protected CanvasKeyHandler keyHandler;

    public CanvasPanel(CanvasImage canvasImage) {
        this.canvasImage = canvasImage;
        init();
    }

    private void init() {
        keyHandler = new CanvasKeyHandler(panelListeners);

        setLayout(new BorderLayout());
        canvas = new JustCanvas(canvasImage);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);

        canvas.addKeyListener(keyHandler);
        canvas.addMouseListener(keyHandler);

        scrollPane = new JScrollPane(canvas);
        add(scrollPane, BorderLayout.CENTER);
    }

    public Visible getVisible() {
        return canvas;
    }

    public Informing getInforming() {
        return canvas;
    }

    public Listenable getListenable() {
        return this;
    }

    @Override
    public Set<CollabPanelListener> getListeners() {
        synchronized (this) {
            return panelListeners;
        }
    }

    @Override
    public void addListener(CollabPanelListener listener) {
        synchronized (this) {
            panelListeners.add(listener);
        }
    }

    @Override
    public boolean removeListener(CollabPanelListener listener) {
        synchronized (this) {
            return panelListeners.remove(listener);
        }
    }

    @Override
    public void change(Rectangle rect) {
        canvas.change(rect);
    }

    @Override
    public void selectionChange() {
        canvas.selectionChange();
    }

    @Override
    public void imageResized(int width, int height) {
        canvas.imageResized(width, height);
    }

    @Override
    public void zoomChanged(float zoom) {
        canvas.zoomChanged(zoom);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        synchronized (this) {
            if (e.getSource() == canvas) {
                CanvasMouseEvent cme = new CanvasMouseEvent(e.isAltDown(), e.isShiftDown(), e.isControlDown(),
                        canvas.translateMousePoint(e.getPoint()), CollabPanelMouseEvent.TYPE_CLICK, e.getButton(), 0);
                for (CollabPanelListener listener : panelListeners) {
                    listener.mouseEvent(cme);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        synchronized (this) {
            if (e.getSource() == canvas) {
                CanvasMouseEvent cme = new CanvasMouseEvent(e.isAltDown(), e.isShiftDown(), e.isControlDown(),
                        canvas.translateMousePoint(e.getPoint()), CollabPanelMouseEvent.TYPE_PRESS, e.getButton(), 0);
                for (CollabPanelListener listener : panelListeners) {
                    listener.mouseEvent(cme);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        synchronized (this) {
            if (e.getSource() == canvas) {
                CanvasMouseEvent cme = new CanvasMouseEvent(e.isAltDown(), e.isShiftDown(), e.isControlDown(),
                        canvas.translateMousePoint(e.getPoint()), CollabPanelMouseEvent.TYPE_RELEASE, e.getButton(), 0);
                for (CollabPanelListener listener : panelListeners) {
                    listener.mouseEvent(cme);
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        synchronized (this) {
            if (e.getSource() == canvas) {
                CanvasMouseEvent cme = new CanvasMouseEvent(e.isAltDown(), e.isShiftDown(), e.isControlDown(),
                        canvas.translateMousePoint(e.getPoint()), CollabPanelMouseEvent.TYPE_DRAG, e.getButton(), 0);
                for (CollabPanelListener listener : panelListeners) {
                    listener.mouseEvent(cme);
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        synchronized (this) {
            if (e.getSource() == canvas) {
                CanvasMouseEvent cme = new CanvasMouseEvent(e.isAltDown(), e.isShiftDown(), e.isControlDown(),
                        canvas.translateMousePoint(e.getPoint()), CollabPanelMouseEvent.TYPE_MOVE, e.getButton(), 0);
                for (CollabPanelListener listener : panelListeners) {
                    listener.mouseEvent(cme);
                }
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        synchronized (this) {
            if (e.getSource() == canvas) {
                CanvasMouseEvent cme = new CanvasMouseEvent(e.isAltDown(), e.isShiftDown(), e.isControlDown(),
                        canvas.translateMousePoint(e.getPoint()), CollabPanelMouseEvent.TYPE_WHEEL, e.getButton(), e.getWheelRotation());
                for (CollabPanelListener listener : panelListeners) {
                    listener.mouseEvent(cme);
                }
            }
        }
    }
}
