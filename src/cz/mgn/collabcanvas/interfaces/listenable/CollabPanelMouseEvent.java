/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.listenable;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author indy
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
