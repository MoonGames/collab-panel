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

import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelMouseEvent;
import java.awt.Point;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class CanvasMouseEvent implements CollabPanelMouseEvent {

    protected boolean alt;
    protected boolean shift;
    protected boolean control;
    protected Point point;
    protected int type;
    protected int button;
    protected int wheel;

    public CanvasMouseEvent(boolean alt, boolean shift, boolean control, Point point, int type, int button, int wheel) {
        this.alt = alt;
        this.shift = shift;
        this.control = control;
        this.point = point;
        this.type = type;
        this.button = button;
        this.wheel = wheel;
    }

    @Override
    public boolean isAltDown() {
        return alt;
    }

    @Override
    public boolean isShiftDown() {
        return shift;
    }

    @Override
    public boolean isControlDown() {
        return control;
    }

    @Override
    public int getEventType() {
        return type;
    }

    @Override
    public Point getEventCoordinates() {
        return point;
    }

    @Override
    public int getButton() {
        return button;
    }

    @Override
    public int getWheelAmount() {
        return wheel;
    }
}
