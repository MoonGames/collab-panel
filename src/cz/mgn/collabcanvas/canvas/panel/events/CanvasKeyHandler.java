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

package cz.mgn.collabcanvas.canvas.panel.events;

import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelKeyEvent;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CanvasKeyHandler implements KeyListener, MouseListener {

    //listeners
    protected Set<CollabPanelListener> panelListeners =
            new HashSet<CollabPanelListener>();
    /**
     * currently pressed keys
     */
    protected ArrayList<CollabPanelKeyEvent.KeyCode> pressedKeys =
            new ArrayList<CollabPanelKeyEvent.KeyCode>();

    public CanvasKeyHandler(Set<CollabPanelListener> panelListeners) {
        this.panelListeners = panelListeners;
    }

    protected ArrayList<CollabPanelKeyEvent.KeyCode> getPressedKeysCopy() {
        ArrayList<CollabPanelKeyEvent.KeyCode> pressedKeys =
                new ArrayList<CollabPanelKeyEvent.KeyCode>();
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
        CollabPanelKeyEvent.KeyCode keyCode = CanvasKeyEvent.
                awtKeyCodeToCollabKeyCode(e.getKeyCode());
        if (!pressedKeys.contains(keyCode)) {
            pressedKeys.add(keyCode);
        }
        informaAll(new CanvasKeyEvent(CollabPanelKeyEvent.EVENT_TYPE_PRESSED, e.getKeyCode(), e.getKeyChar(), getPressedKeysCopy()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(CanvasKeyEvent.awtKeyCodeToCollabKeyCode(e.
                getKeyCode()));
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
