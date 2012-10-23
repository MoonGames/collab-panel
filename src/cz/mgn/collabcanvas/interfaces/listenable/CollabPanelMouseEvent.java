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

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public interface CollabPanelMouseEvent {

    public static final int TYPE_MOVE = 0;
    public static final int TYPE_DRAG = 1;
    public static final int TYPE_PRESS = 2;
    public static final int TYPE_RELEASE = 3;
    public static final int TYPE_CLICK = 4;
    public static final int TYPE_WHEEL = 5;
    public static final int BUTTON1 = MouseEvent.BUTTON1;
    public static final int BUTTON2 = MouseEvent.BUTTON2;
    public static final int BUTTON3 = MouseEvent.BUTTON3;

    public boolean isAltDown();

    public boolean isShiftDown();

    public boolean isControlDown();

    public int getEventType();

    public Point getEventCoordinates();

    public int getButton();

    public int getWheelAmount();
}
