/*
 * Collab desktop - Software for shared drawing via internet in real-time
 * Copyright (C) 2012 Martin Indra <aktive@seznam.cz>
 *
 * This file is part of Collab desktop.
 *
 * Collab desktop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Collab desktop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Collab desktop.  If not, see <http://www.gnu.org/licenses/>.
 */

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
