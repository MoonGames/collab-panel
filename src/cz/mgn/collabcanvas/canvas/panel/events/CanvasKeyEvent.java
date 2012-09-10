/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.panel.events;

import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelKeyEvent;
import java.util.Set;

/**
 *
 * @author indy
 */
public class CanvasKeyEvent implements CollabPanelKeyEvent {

    protected int eventType;
    protected int keyCode;
    protected char keyChar;
    protected Set<Integer> pressedKeys;

    public CanvasKeyEvent(int eventType, int keyCode, char keyChar, Set<Integer> pressedKeys) {
        this.eventType = eventType;
        this.keyCode = keyCode;
        this.keyChar = keyChar;
        this.pressedKeys = pressedKeys;
    }

    @Override
    public int getKeyCode() {
        return keyCode;
    }

    @Override
    public Set<Integer> getPressedKeyCodes() {
        return pressedKeys;
    }

    @Override
    public int getEventType() {
        return eventType;
    }

    @Override
    public char getKeyChar() {
        return keyChar;
    }
}
