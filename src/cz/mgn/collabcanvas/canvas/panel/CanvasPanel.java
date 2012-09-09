/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.panel;

import cz.mgn.collabcanvas.canvas.image.CanvasImage;
import cz.mgn.collabcanvas.canvas.image.CanvasImageChangeListener;
import cz.mgn.collabcanvas.canvas.panel.events.CanvasMouseEvent;
import cz.mgn.collabcanvas.canvas.panel.justcanvas.JustCanvas;
import cz.mgn.collabcanvas.interfaces.informing.InfoListener;
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
 * @author indy
 */
public class CanvasPanel extends JPanel implements Listenable, CanvasImageChangeListener, MouseListener, MouseMotionListener, MouseWheelListener {

    protected CanvasImage canvasImage;
    protected JScrollPane scrollPane;
    protected JustCanvas canvas;
    //listeners
    protected Set<CollabPanelListener> panelListeners = new TreeSet<CollabPanelListener>();

    public CanvasPanel(CanvasImage canvasImage) {
        this.canvasImage = canvasImage;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        canvas = new JustCanvas(canvasImage);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
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
