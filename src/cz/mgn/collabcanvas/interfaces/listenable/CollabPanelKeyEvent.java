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

package cz.mgn.collabcanvas.interfaces.listenable;

import java.awt.event.KeyEvent;
import java.util.Set;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface CollabPanelKeyEvent {

    // key event types
    public static final int EVENT_TYPE_PRESSED = 0;
    public static final int EVENT_TYPE_RELEASED = 1;
    public static final int EVENT_TYPE_TYPED = 2;
    // key codes
    public static final int KEY_CODE_ENTER = KeyEvent.VK_ENTER;
    public static final int KEY_CODE_BACK_SPACE = KeyEvent.VK_BACK_SPACE;
    public static final int KEY_CODE_TAB = KeyEvent.VK_TAB;
    public static final int KEY_CODE_CANCEL = KeyEvent.VK_CANCEL;
    public static final int KEY_CODE_CLEAR = KeyEvent.VK_CLEAR;
    public static final int KEY_CODE_SHIFT = KeyEvent.VK_SHIFT;
    public static final int KEY_CODE_CONTROL = KeyEvent.VK_CONTROL;
    public static final int KEY_CODE_ALT = KeyEvent.VK_ALT;
    public static final int KEY_CODE_PAUSE = KeyEvent.VK_PAUSE;
    public static final int KEY_CODE_CAPS_LOCK = KeyEvent.VK_CAPS_LOCK;
    public static final int KEY_CODE_ESCAPE = KeyEvent.VK_ESCAPE;
    public static final int KEY_CODE_SPACE = KeyEvent.VK_SPACE;
    public static final int KEY_CODE_PAGE_UP = KeyEvent.VK_PAGE_UP;
    public static final int KEY_CODE_PAGE_DOWN = KeyEvent.VK_PAGE_DOWN;
    public static final int KEY_CODE_END = KeyEvent.VK_END;
    public static final int KEY_CODE_HOME = KeyEvent.VK_HOME;
    //
    public static final int KEY_CODE_LEFT = KeyEvent.VK_LEFT;
    public static final int KEY_CODE_UP = KeyEvent.VK_UP;
    public static final int KEY_CODE_RIGHT = KeyEvent.VK_RIGHT;
    public static final int KEY_CODE_DOWN = KeyEvent.VK_DOWN;
    public static final int KEY_CODE_COMMA = KeyEvent.VK_COMMA;
    public static final int KEY_CODE_MINUS = KeyEvent.VK_MINUS;
    public static final int KEY_CODE_PERIOD = KeyEvent.VK_PERIOD;
    public static final int KEY_CODE_SLASH = KeyEvent.VK_SLASH;
    //
    /**
     * VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39)
     */
    public static final int KEY_CODE_0 = KeyEvent.VK_0;
    public static final int KEY_CODE_1 = KeyEvent.VK_1;
    public static final int KEY_CODE_2 = KeyEvent.VK_2;
    public static final int KEY_CODE_3 = KeyEvent.VK_3;
    public static final int KEY_CODE_4 = KeyEvent.VK_4;
    public static final int KEY_CODE_5 = KeyEvent.VK_5;
    public static final int KEY_CODE_6 = KeyEvent.VK_6;
    public static final int KEY_CODE_7 = KeyEvent.VK_7;
    public static final int KEY_CODE_8 = KeyEvent.VK_8;
    public static final int KEY_CODE_9 = KeyEvent.VK_9;
    //
    public static final int KEY_CODE_SEMICOLON = KeyEvent.VK_SEMICOLON;
    public static final int KEY_CODE_EQUALS = KeyEvent.VK_EQUALS;
    //
    public static final int KEY_CODE_A = KeyEvent.VK_A;
    public static final int KEY_CODE_B = KeyEvent.VK_B;
    public static final int KEY_CODE_C = KeyEvent.VK_C;
    public static final int KEY_CODE_D = KeyEvent.VK_D;
    public static final int KEY_CODE_E = KeyEvent.VK_E;
    public static final int KEY_CODE_F = KeyEvent.VK_F;
    public static final int KEY_CODE_G = KeyEvent.VK_G;
    public static final int KEY_CODE_H = KeyEvent.VK_H;
    public static final int KEY_CODE_I = KeyEvent.VK_I;
    public static final int KEY_CODE_J = KeyEvent.VK_J;
    public static final int KEY_CODE_K = KeyEvent.VK_K;
    public static final int KEY_CODE_L = KeyEvent.VK_L;
    public static final int KEY_CODE_M = KeyEvent.VK_M;
    public static final int KEY_CODE_N = KeyEvent.VK_N;
    public static final int KEY_CODE_O = KeyEvent.VK_O;
    public static final int KEY_CODE_P = KeyEvent.VK_P;
    public static final int KEY_CODE_Q = KeyEvent.VK_Q;
    public static final int KEY_CODE_R = KeyEvent.VK_R;
    public static final int KEY_CODE_S = KeyEvent.VK_S;
    public static final int KEY_CODE_T = KeyEvent.VK_T;
    public static final int KEY_CODE_U = KeyEvent.VK_U;
    public static final int KEY_CODE_V = KeyEvent.VK_V;
    public static final int KEY_CODE_W = KeyEvent.VK_W;
    public static final int KEY_CODE_X = KeyEvent.VK_X;
    public static final int KEY_CODE_Y = KeyEvent.VK_Y;
    public static final int KEY_CODE_Z = KeyEvent.VK_Z;
    //
    public static final int KEY_CODE_OPEN_BRACKET = KeyEvent.VK_OPEN_BRACKET;
    public static final int KEY_CODE_BACK_SLASH = KeyEvent.VK_BACK_SLASH;
    public static final int KEY_CODE_CLOSE_BRACKET = KeyEvent.VK_CLOSE_BRACKET;
    public static final int KEY_CODE_NUMPAD0 = KeyEvent.VK_NUMPAD0;
    public static final int KEY_CODE_NUMPAD1 = KeyEvent.VK_NUMPAD1;
    public static final int KEY_CODE_NUMPAD2 = KeyEvent.VK_NUMPAD2;
    public static final int KEY_CODE_NUMPAD3 = KeyEvent.VK_NUMPAD3;
    public static final int KEY_CODE_NUMPAD4 = KeyEvent.VK_NUMPAD4;
    public static final int KEY_CODE_NUMPAD5 = KeyEvent.VK_NUMPAD5;
    public static final int KEY_CODE_NUMPAD6 = KeyEvent.VK_NUMPAD6;
    public static final int KEY_CODE_NUMPAD7 = KeyEvent.VK_NUMPAD7;
    public static final int KEY_CODE_NUMPAD8 = KeyEvent.VK_NUMPAD8;
    public static final int KEY_CODE_NUMPAD9 = KeyEvent.VK_NUMPAD9;
    public static final int KEY_CODE_MULTIPLY = KeyEvent.VK_MULTIPLY;
    public static final int KEY_CODE_ADD = KeyEvent.VK_ADD;
    public static final int KEY_CODE_SEPARATER = KeyEvent.VK_SEPARATER;
    public static final int KEY_CODE_SEPARATOR = KEY_CODE_SEPARATER;
    public static final int KEY_CODE_SUBTRACT = KeyEvent.VK_SUBTRACT;
    public static final int KEY_CODE_DECIMAL = KeyEvent.VK_DECIMAL;
    public static final int KEY_CODE_DIVIDE = KeyEvent.VK_DIVIDE;
    public static final int KEY_CODE_DELETE = KeyEvent.VK_DELETE;
    public static final int KEY_CODE_NUM_LOCK = KeyEvent.VK_NUM_LOCK;
    public static final int KEY_CODE_SCROLL_LOCK = KeyEvent.VK_SCROLL_LOCK;
    public static final int KEY_CODE_F1 = KeyEvent.VK_F1;
    public static final int KEY_CODE_F2 = KeyEvent.VK_F2;
    public static final int KEY_CODE_F3 = KeyEvent.VK_F3;
    public static final int KEY_CODE_F4 = KeyEvent.VK_F4;
    public static final int KEY_CODE_F5 = KeyEvent.VK_F5;
    public static final int KEY_CODE_F6 = KeyEvent.VK_F6;
    public static final int KEY_CODE_F7 = KeyEvent.VK_F7;
    public static final int KEY_CODE_F8 = KeyEvent.VK_F8;
    public static final int KEY_CODE_F9 = KeyEvent.VK_F9;
    public static final int KEY_CODE_F10 = KeyEvent.VK_F10;
    public static final int KEY_CODE_F11 = KeyEvent.VK_F11;
    public static final int KEY_CODE_F12 = KeyEvent.VK_F12;

    /**
     * returns type of key event
     */
    public int getEventType();

    /**
     * returns action source key code
     */
    public int getKeyCode();

    /**
     * returns pressed keys codes (in time when event occurs)
     */
    public Set<Integer> getPressedKeyCodes();

    public char getKeyChar();
}
