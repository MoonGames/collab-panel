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

import java.util.ArrayList;

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

    public static enum KeyCode {
        CODE_ENTER, CODE_BACK_SPACE, CODE_TAB, CODE_CANCEL, CODE_CLEAR,
        CODE_SHIFT, CODE_CONTROL, CODE_ALT, CODE_PAUSE, CODE_CAPS_LOCK,
        CODE_ESCAPE, CODE_SPACE, CODE_PAGE_UP, CODE_PAGE_DOWN, CODE_END,
        CODE_HOME, CODE_LEFT, CODE_UP, CODE_RIGHT, CODE_DOWN, CODE_COMMA,
        CODE_MINUS, CODE_PERIOD, CODE_SLASH, CODE_0, CODE_1, CODE_2, CODE_3,
        CODE_4, CODE_5, CODE_6, CODE_7, CODE_8, CODE_9, CODE_SEMICOLON,
        CODE_EQUALS, CODE_A, CODE_B, CODE_C, CODE_D, CODE_E, CODE_F, CODE_G,
        CODE_H, CODE_I, CODE_J, CODE_K, CODE_L, CODE_M, CODE_N, CODE_O, CODE_P,
        CODE_Q, CODE_R, CODE_S, CODE_T, CODE_U, CODE_V, CODE_W, CODE_X, CODE_Y,
        CODE_Z, CODE_OPEN_BRACKET, CODE_BACK_SLASH, CODE_CLOSE_BRACKET,
        CODE_NUMPAD0, CODE_NUMPAD1, CODE_NUMPAD2, CODE_NUMPAD3, CODE_NUMPAD4,
        CODE_NUMPAD5, CODE_NUMPAD6, CODE_NUMPAD7, CODE_NUMPAD8, CODE_NUMPAD9,
        CODE_MULTIPLY, CODE_ADD, CODE_SEPARATOR, CODE_SUBTRACT,
        CODE_DECIMAL, CODE_DIVIDE, CODE_NUM_LOCK, CODE_SCROLL_LOCK, CODE_F1,
        CODE_F2, CODE_F3, CODE_F4, CODE_F5, CODE_F6, CODE_F7, CODE_F8, CODE_F9,
        CODE_F10, CODE_F11, CODE_F12, CODE_F13, CODE_F14, CODE_F15, CODE_F16,
        CODE_F17, CODE_F18, CODE_F19, CODE_F20, CODE_F21, CODE_F22, CODE_F23,
        CODE_F24, CODE_PRINTSCREEN, CODE_INSERT, CODE_HELP, CODE_META,
        CODE_BACK_QUOTE, CODE_QUOTE, CODE_KP_UP, CODE_KP_DOWN, CODE_KP_LEFT,
        CODE_KP_RIGHT, CODE_DEAD_GRAVE, CODE_DEAD_ACUTE, CODE_DEAD_CIRCUMFLEX,
        CODE_DEAD_TILDE, CODE_DEAD_MACRON, CODE_DEAD_BREVE, CODE_DEAD_ABOVEDOT,
        CODE_DEAD_DIAERESIS, CODE_DEAD_ABOVERING, CODE_DEAD_DOUBLEACUTE,
        CODE_DEAD_CARON, CODE_DEAD_CEDILLA, CODE_DEAD_OGONEK, CODE_DEAD_IOTA,
        CODE_DEAD_VOICED_SOUND, CODE_DEAD_SEMIVOICED_SOUND, CODE_AMPERSAND,
        CODE_ASTERISK, CODE_QUOTEDBL, CODE_LESS, CODE_GREATER, CODE_BRACELEFT,
        CODE_BRACERIGHT, CODE_AT, CODE_COLON, CODE_CIRCUMFLEX, CODE_DOLLAR,
        CODE_EURO_SIGN, CODE_EXCLAMATION_MARK, CODE_INVERTED_EXCLAMATION_MARK,
        CODE_LEFT_PARENTHESIS, CODE_NUMBER_SIGN, CODE_PLUS,
        CODE_RIGHT_PARENTHESIS, CODE_UNDERSCORE, CODE_WINDOWS, CODE_CONTEXT_MENU,
        CODE_FINAL, CODE_CONVERT, CODE_NONCONVERT, CODE_ACCEPT, CODE_MODECHANGE,
        CODE_KANA, CODE_KANJI, CODE_ALPHANUMERIC, CODE_KATAKANA, CODE_HIRAGANA,
        CODE_FULL_WIDTH, CODE_HALF_WIDTH, CODE_ROMAN_CHARACTERS,
        CODE_ALL_CANDIDATES, CODE_PREVIOUS_CANDIDATE, CODE_CODE_INPUT,
        CODE_JAPANESE_KATAKANA, CODE_JAPANESE_HIRAGANA, CODE_JAPANESE_ROMAN,
        CODE_KANA_LOCK, CODE_INPUT_METHOD_ON_OFF, CODE_CUT, CODE_COPY,
        CODE_PASTE, CODE_UNDO, CODE_AGAIN, CODE_FIND, CODE_PROPS, CODE_STOP,
        CODE_COMPOSE, CODE_ALT_GRAPH, CODE_BEGIN;
    }


    /**
     * Returns type of key event.
     *
     * @see #EVENT_TYPE_PRESSED
     * @see #EVENT_TYPE_RELEASED
     * @see #EVENT_TYPE_TYPED
     */
    public int getEventType();

    /**
     * Returns action source key code.
     */
    public KeyCode getKeyCode();

    /**
     * Returns pressed keys codes (which keys was pressed when event occurs).
     */
    public ArrayList<CollabPanelKeyEvent.KeyCode> getPressedKeyCodes();

    /**
     * Returns character representing event source key.
     */
    public char getKeyChar();
}
