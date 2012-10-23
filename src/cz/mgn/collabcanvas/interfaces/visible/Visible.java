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
package cz.mgn.collabcanvas.interfaces.visible;

/**
 * This interface is used for working with dynamic visible parts over canvas.
 * Like mouse cursor, previews etc.
 *
 * When canvas is painted first is painted canvas image and over it it painted
 * thing which is possible work by this interface. Over canvas image is painted
 * this things (in this order): tool image, tool cursor, mouse cursor
 *
 * mouse cursor: it's painted over all and it's not painted by canvas, but by
 * system (it's uses standard system API for mouse cursors)
 *
 * tool cursor: under mouse cursor is painted tool cursor (example: this canvas
 * is used in graphics editor providing brush painting tool and tool cursor of
 * this tool is outline of used brush). Simply it's just image painted under
 * mouse cursor. Tool cursor is zoomed (differently of mouse cursor) same as
 * canvas.
 *
 * tool image: image which is painted under mouse and tool cursor. This image is
 * moving with mouse and tool cursor and it's serves like preview of something.
 * Tool image is zoomed same as canvas.
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface Visible {

    /**
     * Sets mouse cursor.
     *
     * @param mouseCursor new mouse cursor which will be used, if this value is
     * null, default cursor will be used
     */
    public void setMouseCursor(MouseCursor mouseCursor);

    /**
     * Sets tool cursor.
     *
     * @param toolCursor new tool cursor, if it's null no tool cursor will be
     * used
     */
    public void setToolCursor(ToolCursor toolCursor);

    /**
     * Sets tool image.
     *
     * @param toolImage new tool image, if it's null no tool image will be used
     */
    public void setToolImage(ToolImage toolImage);
}
