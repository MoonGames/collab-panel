/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.panel.events;

import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelMouseEvent;
import java.awt.Point;

/**
 *
 * @author indy
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
