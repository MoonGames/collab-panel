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
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CanvasKeyEvent implements CollabPanelKeyEvent {

    protected int eventType;
    protected KeyCode keyCode;
    protected char keyChar;
    protected ArrayList<CollabPanelKeyEvent.KeyCode> pressedKeys;

    public CanvasKeyEvent(int eventType, int awtKeyCode, char keyChar,
            ArrayList<CollabPanelKeyEvent.KeyCode> pressedKeys) {
        this.eventType = eventType;
        this.keyCode = awtKeyCodeToCollabKeyCode(awtKeyCode);
        this.keyChar = keyChar;
        this.pressedKeys = pressedKeys;
    }

    public static KeyCode awtKeyCodeToCollabKeyCode(int awtKeyCode) {
        KeyCode code = null;

        switch (awtKeyCode) {
            case KeyEvent.VK_ENTER:
                code = CollabPanelKeyEvent.KeyCode.CODE_ENTER;
                break;
            case KeyEvent.VK_BACK_SPACE:
                code = CollabPanelKeyEvent.KeyCode.CODE_BACK_SPACE;
                break;
            case KeyEvent.VK_TAB:
                code = CollabPanelKeyEvent.KeyCode.CODE_TAB;
                break;
            case KeyEvent.VK_CANCEL:
                code = CollabPanelKeyEvent.KeyCode.CODE_CANCEL;
                break;
            case KeyEvent.VK_CLEAR:
                code = CollabPanelKeyEvent.KeyCode.CODE_CLEAR;
                break;
            case KeyEvent.VK_SHIFT:
                code = CollabPanelKeyEvent.KeyCode.CODE_SHIFT;
                break;
            case KeyEvent.VK_CONTROL:
                code = CollabPanelKeyEvent.KeyCode.CODE_CONTROL;
                break;
            case KeyEvent.VK_ALT:
                code = CollabPanelKeyEvent.KeyCode.CODE_ALT;
                break;
            case KeyEvent.VK_PAUSE:
                code = CollabPanelKeyEvent.KeyCode.CODE_PAUSE;
                break;
            case KeyEvent.VK_CAPS_LOCK:
                code = CollabPanelKeyEvent.KeyCode.CODE_CAPS_LOCK;
                break;
            case KeyEvent.VK_ESCAPE:
                code = CollabPanelKeyEvent.KeyCode.CODE_ESCAPE;
                break;
            case KeyEvent.VK_SPACE:
                code = CollabPanelKeyEvent.KeyCode.CODE_SPACE;
                break;
            case KeyEvent.VK_PAGE_UP:
                code = CollabPanelKeyEvent.KeyCode.CODE_PAGE_UP;
                break;
            case KeyEvent.VK_PAGE_DOWN:
                code = CollabPanelKeyEvent.KeyCode.CODE_PAGE_DOWN;
                break;
            case KeyEvent.VK_END:
                code = CollabPanelKeyEvent.KeyCode.CODE_END;
                break;
            case KeyEvent.VK_HOME:
                code = CollabPanelKeyEvent.KeyCode.CODE_HOME;
                break;
            case KeyEvent.VK_LEFT:
                code = CollabPanelKeyEvent.KeyCode.CODE_LEFT;
                break;
            case KeyEvent.VK_UP:
                code = CollabPanelKeyEvent.KeyCode.CODE_UP;
                break;
            case KeyEvent.VK_RIGHT:
                code = CollabPanelKeyEvent.KeyCode.CODE_RIGHT;
                break;
            case KeyEvent.VK_DOWN:
                code = CollabPanelKeyEvent.KeyCode.CODE_DOWN;
                break;
            case KeyEvent.VK_COMMA:
                code = CollabPanelKeyEvent.KeyCode.CODE_COMMA;
                break;
            case KeyEvent.VK_MINUS:
                code = CollabPanelKeyEvent.KeyCode.CODE_MINUS;
                break;
            case KeyEvent.VK_PERIOD:
                code = CollabPanelKeyEvent.KeyCode.CODE_PERIOD;
                break;
            case KeyEvent.VK_SLASH:
                code = CollabPanelKeyEvent.KeyCode.CODE_SLASH;
                break;
            case KeyEvent.VK_0:
                code = CollabPanelKeyEvent.KeyCode.CODE_0;
                break;
            case KeyEvent.VK_1:
                code = CollabPanelKeyEvent.KeyCode.CODE_1;
                break;
            case KeyEvent.VK_2:
                code = CollabPanelKeyEvent.KeyCode.CODE_2;
                break;
            case KeyEvent.VK_3:
                code = CollabPanelKeyEvent.KeyCode.CODE_3;
                break;
            case KeyEvent.VK_4:
                code = CollabPanelKeyEvent.KeyCode.CODE_4;
                break;
            case KeyEvent.VK_5:
                code = CollabPanelKeyEvent.KeyCode.CODE_5;
                break;
            case KeyEvent.VK_6:
                code = CollabPanelKeyEvent.KeyCode.CODE_6;
                break;
            case KeyEvent.VK_7:
                code = CollabPanelKeyEvent.KeyCode.CODE_7;
                break;
            case KeyEvent.VK_8:
                code = CollabPanelKeyEvent.KeyCode.CODE_8;
                break;
            case KeyEvent.VK_9:
                code = CollabPanelKeyEvent.KeyCode.CODE_9;
                break;
            case KeyEvent.VK_SEMICOLON:
                code = CollabPanelKeyEvent.KeyCode.CODE_SEMICOLON;
                break;
            case KeyEvent.VK_EQUALS:
                code = CollabPanelKeyEvent.KeyCode.CODE_EQUALS;
                break;
            case KeyEvent.VK_A:
                code = CollabPanelKeyEvent.KeyCode.CODE_A;
                break;
            case KeyEvent.VK_B:
                code = CollabPanelKeyEvent.KeyCode.CODE_B;
                break;
            case KeyEvent.VK_C:
                code = CollabPanelKeyEvent.KeyCode.CODE_C;
                break;
            case KeyEvent.VK_D:
                code = CollabPanelKeyEvent.KeyCode.CODE_D;
                break;
            case KeyEvent.VK_E:
                code = CollabPanelKeyEvent.KeyCode.CODE_E;
                break;
            case KeyEvent.VK_F:
                code = CollabPanelKeyEvent.KeyCode.CODE_F;
                break;
            case KeyEvent.VK_G:
                code = CollabPanelKeyEvent.KeyCode.CODE_G;
                break;
            case KeyEvent.VK_H:
                code = CollabPanelKeyEvent.KeyCode.CODE_H;
                break;
            case KeyEvent.VK_I:
                code = CollabPanelKeyEvent.KeyCode.CODE_I;
                break;
            case KeyEvent.VK_J:
                code = CollabPanelKeyEvent.KeyCode.CODE_J;
                break;
            case KeyEvent.VK_K:
                code = CollabPanelKeyEvent.KeyCode.CODE_K;
                break;
            case KeyEvent.VK_L:
                code = CollabPanelKeyEvent.KeyCode.CODE_L;
                break;
            case KeyEvent.VK_M:
                code = CollabPanelKeyEvent.KeyCode.CODE_M;
                break;
            case KeyEvent.VK_N:
                code = CollabPanelKeyEvent.KeyCode.CODE_N;
                break;
            case KeyEvent.VK_O:
                code = CollabPanelKeyEvent.KeyCode.CODE_O;
                break;
            case KeyEvent.VK_P:
                code = CollabPanelKeyEvent.KeyCode.CODE_P;
                break;
            case KeyEvent.VK_Q:
                code = CollabPanelKeyEvent.KeyCode.CODE_Q;
                break;
            case KeyEvent.VK_R:
                code = CollabPanelKeyEvent.KeyCode.CODE_R;
                break;
            case KeyEvent.VK_S:
                code = CollabPanelKeyEvent.KeyCode.CODE_S;
                break;
            case KeyEvent.VK_T:
                code = CollabPanelKeyEvent.KeyCode.CODE_T;
                break;
            case KeyEvent.VK_U:
                code = CollabPanelKeyEvent.KeyCode.CODE_U;
                break;
            case KeyEvent.VK_V:
                code = CollabPanelKeyEvent.KeyCode.CODE_V;
                break;
            case KeyEvent.VK_W:
                code = CollabPanelKeyEvent.KeyCode.CODE_W;
                break;
            case KeyEvent.VK_X:
                code = CollabPanelKeyEvent.KeyCode.CODE_X;
                break;
            case KeyEvent.VK_Y:
                code = CollabPanelKeyEvent.KeyCode.CODE_Y;
                break;
            case KeyEvent.VK_Z:
                code = CollabPanelKeyEvent.KeyCode.CODE_Z;
                break;
            case KeyEvent.VK_OPEN_BRACKET:
                code = CollabPanelKeyEvent.KeyCode.CODE_OPEN_BRACKET;
                break;
            case KeyEvent.VK_BACK_SLASH:
                code = CollabPanelKeyEvent.KeyCode.CODE_BACK_SLASH;
                break;
            case KeyEvent.VK_CLOSE_BRACKET:
                code = CollabPanelKeyEvent.KeyCode.CODE_CLOSE_BRACKET;
                break;
            case KeyEvent.VK_NUMPAD0:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD0;
                break;
            case KeyEvent.VK_NUMPAD1:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD1;
                break;
            case KeyEvent.VK_NUMPAD2:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD2;
                break;
            case KeyEvent.VK_NUMPAD3:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD3;
                break;
            case KeyEvent.VK_NUMPAD4:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD4;
                break;
            case KeyEvent.VK_NUMPAD5:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD5;
                break;
            case KeyEvent.VK_NUMPAD6:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD6;
                break;
            case KeyEvent.VK_NUMPAD7:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD7;
                break;
            case KeyEvent.VK_NUMPAD8:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD8;
                break;
            case KeyEvent.VK_NUMPAD9:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMPAD9;
                break;
            case KeyEvent.VK_MULTIPLY:
                code = CollabPanelKeyEvent.KeyCode.CODE_MULTIPLY;
                break;
            case KeyEvent.VK_ADD:
                code = CollabPanelKeyEvent.KeyCode.CODE_ADD;
                break;
            case KeyEvent.VK_SEPARATOR:
                code = CollabPanelKeyEvent.KeyCode.CODE_SEPARATOR;
                break;
            case KeyEvent.VK_SUBTRACT:
                code = CollabPanelKeyEvent.KeyCode.CODE_SUBTRACT;
                break;
            case KeyEvent.VK_DECIMAL:
                code = CollabPanelKeyEvent.KeyCode.CODE_DECIMAL;
                break;
            case KeyEvent.VK_DIVIDE:
                code = CollabPanelKeyEvent.KeyCode.CODE_DIVIDE;
                break;
            case KeyEvent.VK_NUM_LOCK:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUM_LOCK;
                break;
            case KeyEvent.VK_SCROLL_LOCK:
                code = CollabPanelKeyEvent.KeyCode.CODE_SCROLL_LOCK;
                break;
            case KeyEvent.VK_F1:
                code = CollabPanelKeyEvent.KeyCode.CODE_F1;
                break;
            case KeyEvent.VK_F2:
                code = CollabPanelKeyEvent.KeyCode.CODE_F2;
                break;
            case KeyEvent.VK_F3:
                code = CollabPanelKeyEvent.KeyCode.CODE_F3;
                break;
            case KeyEvent.VK_F4:
                code = CollabPanelKeyEvent.KeyCode.CODE_F4;
                break;
            case KeyEvent.VK_F5:
                code = CollabPanelKeyEvent.KeyCode.CODE_F5;
                break;
            case KeyEvent.VK_F6:
                code = CollabPanelKeyEvent.KeyCode.CODE_F6;
                break;
            case KeyEvent.VK_F7:
                code = CollabPanelKeyEvent.KeyCode.CODE_F7;
                break;
            case KeyEvent.VK_F8:
                code = CollabPanelKeyEvent.KeyCode.CODE_F8;
                break;
            case KeyEvent.VK_F9:
                code = CollabPanelKeyEvent.KeyCode.CODE_F9;
                break;
            case KeyEvent.VK_F10:
                code = CollabPanelKeyEvent.KeyCode.CODE_F10;
                break;
            case KeyEvent.VK_F11:
                code = CollabPanelKeyEvent.KeyCode.CODE_F11;
                break;
            case KeyEvent.VK_F12:
                code = CollabPanelKeyEvent.KeyCode.CODE_F12;
                break;
            case KeyEvent.VK_F13:
                code = CollabPanelKeyEvent.KeyCode.CODE_F13;
                break;
            case KeyEvent.VK_F14:
                code = CollabPanelKeyEvent.KeyCode.CODE_F14;
                break;
            case KeyEvent.VK_F15:
                code = CollabPanelKeyEvent.KeyCode.CODE_F15;
                break;
            case KeyEvent.VK_F16:
                code = CollabPanelKeyEvent.KeyCode.CODE_F16;
                break;
            case KeyEvent.VK_F17:
                code = CollabPanelKeyEvent.KeyCode.CODE_F17;
                break;
            case KeyEvent.VK_F18:
                code = CollabPanelKeyEvent.KeyCode.CODE_F18;
                break;
            case KeyEvent.VK_F19:
                code = CollabPanelKeyEvent.KeyCode.CODE_F19;
                break;
            case KeyEvent.VK_F20:
                code = CollabPanelKeyEvent.KeyCode.CODE_F20;
                break;
            case KeyEvent.VK_F21:
                code = CollabPanelKeyEvent.KeyCode.CODE_F21;
                break;
            case KeyEvent.VK_F22:
                code = CollabPanelKeyEvent.KeyCode.CODE_F22;
                break;
            case KeyEvent.VK_F23:
                code = CollabPanelKeyEvent.KeyCode.CODE_F23;
                break;
            case KeyEvent.VK_F24:
                code = CollabPanelKeyEvent.KeyCode.CODE_F24;
                break;
            case KeyEvent.VK_PRINTSCREEN:
                code = CollabPanelKeyEvent.KeyCode.CODE_PRINTSCREEN;
                break;
            case KeyEvent.VK_INSERT:
                code = CollabPanelKeyEvent.KeyCode.CODE_INSERT;
                break;
            case KeyEvent.VK_HELP:
                code = CollabPanelKeyEvent.KeyCode.CODE_HELP;
                break;
            case KeyEvent.VK_META:
                code = CollabPanelKeyEvent.KeyCode.CODE_META;
                break;
            case KeyEvent.VK_BACK_QUOTE:
                code = CollabPanelKeyEvent.KeyCode.CODE_BACK_QUOTE;
                break;
            case KeyEvent.VK_QUOTE:
                code = CollabPanelKeyEvent.KeyCode.CODE_QUOTE;
                break;
            case KeyEvent.VK_KP_UP:
                code = CollabPanelKeyEvent.KeyCode.CODE_KP_UP;
                break;
            case KeyEvent.VK_KP_DOWN:
                code = CollabPanelKeyEvent.KeyCode.CODE_KP_DOWN;
                break;
            case KeyEvent.VK_KP_LEFT:
                code = CollabPanelKeyEvent.KeyCode.CODE_KP_LEFT;
                break;
            case KeyEvent.VK_KP_RIGHT:
                code = CollabPanelKeyEvent.KeyCode.CODE_KP_RIGHT;
                break;
            case KeyEvent.VK_DEAD_GRAVE:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_GRAVE;
                break;
            case KeyEvent.VK_DEAD_ACUTE:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_ACUTE;
                break;
            case KeyEvent.VK_DEAD_CIRCUMFLEX:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_CIRCUMFLEX;
                break;
            case KeyEvent.VK_DEAD_TILDE:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_TILDE;
                break;
            case KeyEvent.VK_DEAD_MACRON:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_MACRON;
                break;
            case KeyEvent.VK_DEAD_BREVE:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_BREVE;
                break;
            case KeyEvent.VK_DEAD_ABOVEDOT:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_ABOVEDOT;
                break;
            case KeyEvent.VK_DEAD_DIAERESIS:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_DIAERESIS;
                break;
            case KeyEvent.VK_DEAD_ABOVERING:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_ABOVERING;
                break;
            case KeyEvent.VK_DEAD_DOUBLEACUTE:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_DOUBLEACUTE;
                break;
            case KeyEvent.VK_DEAD_CARON:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_CARON;
                break;
            case KeyEvent.VK_DEAD_CEDILLA:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_CEDILLA;
                break;
            case KeyEvent.VK_DEAD_OGONEK:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_OGONEK;
                break;
            case KeyEvent.VK_DEAD_IOTA:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_IOTA;
                break;
            case KeyEvent.VK_DEAD_VOICED_SOUND:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_VOICED_SOUND;
                break;
            case KeyEvent.VK_DEAD_SEMIVOICED_SOUND:
                code = CollabPanelKeyEvent.KeyCode.CODE_DEAD_SEMIVOICED_SOUND;
                break;
            case KeyEvent.VK_AMPERSAND:
                code = CollabPanelKeyEvent.KeyCode.CODE_AMPERSAND;
                break;
            case KeyEvent.VK_ASTERISK:
                code = CollabPanelKeyEvent.KeyCode.CODE_ASTERISK;
                break;
            case KeyEvent.VK_QUOTEDBL:
                code = CollabPanelKeyEvent.KeyCode.CODE_QUOTEDBL;
                break;
            case KeyEvent.VK_LESS:
                code = CollabPanelKeyEvent.KeyCode.CODE_LESS;
                break;
            case KeyEvent.VK_GREATER:
                code = CollabPanelKeyEvent.KeyCode.CODE_GREATER;
                break;
            case KeyEvent.VK_BRACELEFT:
                code = CollabPanelKeyEvent.KeyCode.CODE_BRACELEFT;
                break;
            case KeyEvent.VK_BRACERIGHT:
                code = CollabPanelKeyEvent.KeyCode.CODE_BRACERIGHT;
                break;
            case KeyEvent.VK_AT:
                code = CollabPanelKeyEvent.KeyCode.CODE_AT;
                break;
            case KeyEvent.VK_COLON:
                code = CollabPanelKeyEvent.KeyCode.CODE_COLON;
                break;
            case KeyEvent.VK_CIRCUMFLEX:
                code = CollabPanelKeyEvent.KeyCode.CODE_CIRCUMFLEX;
                break;
            case KeyEvent.VK_DOLLAR:
                code = CollabPanelKeyEvent.KeyCode.CODE_DOLLAR;
                break;
            case KeyEvent.VK_EURO_SIGN:
                code = CollabPanelKeyEvent.KeyCode.CODE_EURO_SIGN;
                break;
            case KeyEvent.VK_EXCLAMATION_MARK:
                code = CollabPanelKeyEvent.KeyCode.CODE_EXCLAMATION_MARK;
                break;
            case KeyEvent.VK_INVERTED_EXCLAMATION_MARK:
                code = CollabPanelKeyEvent.KeyCode.CODE_INVERTED_EXCLAMATION_MARK;
                break;
            case KeyEvent.VK_LEFT_PARENTHESIS:
                code = CollabPanelKeyEvent.KeyCode.CODE_LEFT_PARENTHESIS;
                break;
            case KeyEvent.VK_NUMBER_SIGN:
                code = CollabPanelKeyEvent.KeyCode.CODE_NUMBER_SIGN;
                break;
            case KeyEvent.VK_PLUS:
                code = CollabPanelKeyEvent.KeyCode.CODE_PLUS;
                break;
            case KeyEvent.VK_RIGHT_PARENTHESIS:
                code = CollabPanelKeyEvent.KeyCode.CODE_RIGHT_PARENTHESIS;
                break;
            case KeyEvent.VK_UNDERSCORE:
                code = CollabPanelKeyEvent.KeyCode.CODE_UNDERSCORE;
                break;
            case KeyEvent.VK_WINDOWS:
                code = CollabPanelKeyEvent.KeyCode.CODE_WINDOWS;
                break;
            case KeyEvent.VK_CONTEXT_MENU:
                code = CollabPanelKeyEvent.KeyCode.CODE_CONTEXT_MENU;
                break;
            case KeyEvent.VK_FINAL:
                code = CollabPanelKeyEvent.KeyCode.CODE_FINAL;
                break;
            case KeyEvent.VK_CONVERT:
                code = CollabPanelKeyEvent.KeyCode.CODE_CONVERT;
                break;
            case KeyEvent.VK_NONCONVERT:
                code = CollabPanelKeyEvent.KeyCode.CODE_NONCONVERT;
                break;
            case KeyEvent.VK_ACCEPT:
                code = CollabPanelKeyEvent.KeyCode.CODE_ACCEPT;
                break;
            case KeyEvent.VK_MODECHANGE:
                code = CollabPanelKeyEvent.KeyCode.CODE_MODECHANGE;
                break;
            case KeyEvent.VK_KANA:
                code = CollabPanelKeyEvent.KeyCode.CODE_KANA;
                break;
            case KeyEvent.VK_KANJI:
                code = CollabPanelKeyEvent.KeyCode.CODE_KANJI;
                break;
            case KeyEvent.VK_ALPHANUMERIC:
                code = CollabPanelKeyEvent.KeyCode.CODE_ALPHANUMERIC;
                break;
            case KeyEvent.VK_KATAKANA:
                code = CollabPanelKeyEvent.KeyCode.CODE_KATAKANA;
                break;
            case KeyEvent.VK_HIRAGANA:
                code = CollabPanelKeyEvent.KeyCode.CODE_HIRAGANA;
                break;
            case KeyEvent.VK_FULL_WIDTH:
                code = CollabPanelKeyEvent.KeyCode.CODE_FULL_WIDTH;
                break;
            case KeyEvent.VK_HALF_WIDTH:
                code = CollabPanelKeyEvent.KeyCode.CODE_HALF_WIDTH;
                break;
            case KeyEvent.VK_ROMAN_CHARACTERS:
                code = CollabPanelKeyEvent.KeyCode.CODE_ROMAN_CHARACTERS;
                break;
            case KeyEvent.VK_ALL_CANDIDATES:
                code = CollabPanelKeyEvent.KeyCode.CODE_ALL_CANDIDATES;
                break;
            case KeyEvent.VK_PREVIOUS_CANDIDATE:
                code = CollabPanelKeyEvent.KeyCode.CODE_PREVIOUS_CANDIDATE;
                break;
            case KeyEvent.VK_CODE_INPUT:
                code = CollabPanelKeyEvent.KeyCode.CODE_CODE_INPUT;
                break;
            case KeyEvent.VK_JAPANESE_KATAKANA:
                code = CollabPanelKeyEvent.KeyCode.CODE_JAPANESE_KATAKANA;
                break;
            case KeyEvent.VK_JAPANESE_HIRAGANA:
                code = CollabPanelKeyEvent.KeyCode.CODE_JAPANESE_HIRAGANA;
                break;
            case KeyEvent.VK_JAPANESE_ROMAN:
                code = CollabPanelKeyEvent.KeyCode.CODE_JAPANESE_ROMAN;
                break;
            case KeyEvent.VK_KANA_LOCK:
                code = CollabPanelKeyEvent.KeyCode.CODE_KANA_LOCK;
                break;
            case KeyEvent.VK_INPUT_METHOD_ON_OFF:
                code = CollabPanelKeyEvent.KeyCode.CODE_INPUT_METHOD_ON_OFF;
                break;
            case KeyEvent.VK_CUT:
                code = CollabPanelKeyEvent.KeyCode.CODE_CUT;
                break;
            case KeyEvent.VK_COPY:
                code = CollabPanelKeyEvent.KeyCode.CODE_COPY;
                break;
            case KeyEvent.VK_PASTE:
                code = CollabPanelKeyEvent.KeyCode.CODE_PASTE;
                break;
            case KeyEvent.VK_UNDO:
                code = CollabPanelKeyEvent.KeyCode.CODE_UNDO;
                break;
            case KeyEvent.VK_AGAIN:
                code = CollabPanelKeyEvent.KeyCode.CODE_AGAIN;
                break;
            case KeyEvent.VK_FIND:
                code = CollabPanelKeyEvent.KeyCode.CODE_FIND;
                break;
            case KeyEvent.VK_PROPS:
                code = CollabPanelKeyEvent.KeyCode.CODE_PROPS;
                break;
            case KeyEvent.VK_STOP:
                code = CollabPanelKeyEvent.KeyCode.CODE_STOP;
                break;
            case KeyEvent.VK_COMPOSE:
                code = CollabPanelKeyEvent.KeyCode.CODE_COMPOSE;
                break;
            case KeyEvent.VK_ALT_GRAPH:
                code = CollabPanelKeyEvent.KeyCode.CODE_ALT_GRAPH;
                break;
            case KeyEvent.VK_BEGIN:
                code = CollabPanelKeyEvent.KeyCode.CODE_BEGIN;
                break;
        }
        return code;
    }

    @Override
    public KeyCode getKeyCode() {
        return keyCode;
    }

    @Override
    public ArrayList<CollabPanelKeyEvent.KeyCode> getPressedKeyCodes() {
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
