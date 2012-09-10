/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.panel.events;

import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelKeyEvent;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComponent;

/**
 *
 * @author indy
 */
public class CanvasKeyHandler implements KeyListener, MouseListener {

    //listeners
    protected Set<CollabPanelListener> panelListeners = new TreeSet<CollabPanelListener>();
    /**
     * currently pressed keys
     */
    protected Set<Integer> pressedKeys = new TreeSet<Integer>();

    public CanvasKeyHandler(Set<CollabPanelListener> panelListeners) {
        this.panelListeners = panelListeners;
    }

    protected Set<Integer> getPressedKeysCopy() {
        Set<Integer> pressedKeys = new TreeSet<Integer>();
        pressedKeys.addAll(this.pressedKeys);
        return pressedKeys;
    }

    protected void informaAll(CanvasKeyEvent event) {
        synchronized (panelListeners) {
            for (CollabPanelListener listener : panelListeners) {
                listener.keyEvent(event);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        informaAll(new CanvasKeyEvent(CollabPanelKeyEvent.EVENT_TYPE_TYPED, e.getKeyCode(), e.getKeyChar(), getPressedKeysCopy()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        informaAll(new CanvasKeyEvent(CollabPanelKeyEvent.EVENT_TYPE_PRESSED, e.getKeyCode(), e.getKeyChar(), getPressedKeysCopy()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
        informaAll(new CanvasKeyEvent(CollabPanelKeyEvent.EVENT_TYPE_RELEASED, e.getKeyCode(), e.getKeyChar(), getPressedKeysCopy()));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JComponent component = (JComponent) e.getSource();
        component.setFocusable(true);
        component.requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
