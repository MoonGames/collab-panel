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

package cz.mgn.collabcanvas.examples;

import cz.mgn.collabcanvas.canvas.CollabCanvas;
import cz.mgn.collabcanvas.factory.CollabCanvasFactory;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelKeyEvent;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelMouseEvent;
import cz.mgn.collabcanvas.interfaces.paintable.PaintData;
import cz.mgn.collabcanvas.interfaces.paintable.PaintImage;
import cz.mgn.collabcanvas.interfaces.visible.ToolCursor;
import cz.mgn.collabcanvas.interfaces.visible.ToolImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This is simple example of usage Collab canvas in offline mode.
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class ExampleOffline implements ActionListener, CollabPanelListener {

    // button for remove old and create new canvas
    protected JButton recreate;
    // last used ID
    protected int lastID = 0;
    // current canvas
    protected CollabCanvas canvas;
    // the frame where all GUI is
    protected JFrame frame;
    // last paint update mouse coordinates
    protected Point lastMousePoint;

    public ExampleOffline() {
        // init GUI (swing)
        initGUI();
        // drop old canvas and create new
        recreateCanvas();
    }

    /**
     * Destroy and remove current canvas than create, initializes and add new
     * canvas.
     */
    private void recreateCanvas() {
        if (canvas != null) {
            // removes old canvas from frame
            frame.getContentPane().remove(canvas.getCanvasComponent());
            // destroy old canvas
            canvas.destroy();
        }
        // create new canvas with new ID
        canvas = CollabCanvasFactory.createLocalCollabCanvas(lastID++);
        // listen for user events over canvas
        canvas.getListenable().addListener(this);
        // sets canvas paintable area size to 300x200
        canvas.getPaintable().setResolution(300, 200);
        // create new layer in canvas
        int layerID = canvas.getPaintable().addLayer();
        // select created layer
        canvas.getPaintable().selectLayer(layerID);
        // set cursor of current "tool"
        canvas.getVisible().setToolCursor(generateToolCursor());
        // set image of current "tool"
        canvas.getVisible().setToolImage(generateToolImage());
        // add canvas to frame
        frame.getContentPane().add(canvas.getCanvasComponent(),
                BorderLayout.CENTER);

        frame.validate();
    }

    /**
     * Generate new tool image.
     */
    private ToolImage generateToolImage() {
        final BufferedImage toolImage = new BufferedImage(50, 50,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) toolImage.getGraphics();
        Random r = new Random();
        // random color
        g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        // 50x50 rectangle
        g.drawRect(0, 0, 49, 49);
        // 2px width
        g.drawRect(1, 1, 47, 47);
        g.dispose();

        // crate tool image object
        return new ToolImage() {
            @Override
            public Point getRelativeLocatoin() {
                // mouse is in middle of tool image (-25 is -(50/2))
                return new Point(-25, -25);
            }

            @Override
            public BufferedImage getToolImage() {
                // not scalet version of tool image (our rectangle)
                return toolImage;
            }

            @Override
            public boolean isScalingSupported() {
                // scaling is not supportet, means that scaling will be provided by framework not by us
                return false;
            }

            @Override
            public BufferedImage getScaledToolImage(float scale) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    /**
     * Generates tool cursor.
     */
    private ToolCursor generateToolCursor() {
        final BufferedImage cursorImage = new BufferedImage(10, 10,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = cursorImage.getGraphics();
        Random r = new Random();
        // random color
        g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
        // paint 10x10 circle
        g.fillOval(0, 0, 10, 10);
        g.dispose();

        // Tool cursor object
        return new ToolCursor() {
            @Override
            public Point getRelativeLocatoin() {
                // relative location (shift from counted position) is zero
                return new Point();
            }

            @Override
            public int getLocationMode() {
                // zero coordinate of tool image is in center of image
                return ToolCursor.LOCATION_MODE_CENTER;
            }

            @Override
            public BufferedImage getCursorImage() {
                // cursor image (circle)
                return cursorImage;
            }

            @Override
            public boolean isScalingSupported() {
                // scaling is not supportet, means that scaling will be provided by framework not by us
                return false;
            }

            @Override
            public BufferedImage getScaledCursorImage(float scale) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    private void initGUI() {
        // init window
        initFrame();
        // fill it by all GUI
        initFrameContent();
    }

    private void initFrameContent() {
        // window content container
        Container container = frame.getContentPane();
        // sets layout
        container.setLayout(new BorderLayout());
        // creates re-create button
        recreate = new JButton("Re-create canvas");
        // add re-create button to north margin of frame
        container.add(recreate, BorderLayout.NORTH);
        // listen to action on re-create button
        recreate.addActionListener(this);
    }

    private void initFrame() {
        // create new window
        frame = new JFrame("Collab canvas example");
        // sets window size
        frame.setSize(600, 400);
        // close application on close window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // makes frame visible
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recreate) {
            // remove and create new canvas
            recreateCanvas();
        }
    }

    @Override
    public void keyEvent(CollabPanelKeyEvent keyEvent) {
    }

    @Override
    public void mouseEvent(CollabPanelMouseEvent mouseEvent) {
        if (mouseEvent.getEventType() == CollabPanelMouseEvent.TYPE_DRAG) {
            Point coordinates = mouseEvent.getEventCoordinates();
            if (lastMousePoint != null) {
                Random r = new Random();
                PaintData paintData = new LinePaintData(new Color(r.nextInt(
                        256), r.nextInt(
                        256), r.nextInt(
                        256)),
                        lastMousePoint, coordinates);
                canvas.getPaintable().paint(paintData);
            }
            lastMousePoint = coordinates;
        } else if (mouseEvent.getEventType()
                == CollabPanelMouseEvent.TYPE_PRESS) {
            lastMousePoint = mouseEvent.getEventCoordinates();
        } else if (mouseEvent.getEventType()
                == CollabPanelMouseEvent.TYPE_RELEASE) {
            lastMousePoint = null;
        }
    }

    public static class LinePaintData implements PaintData {

        protected PaintImage paintImage;

        public LinePaintData(Color color, Point from, Point to) {
            int squareWidth = Math.abs(from.x - to.x);
            int squareHeight = Math.abs(from.y - to.y);
            int positionX = Math.min(from.x, to.x);
            int positionY = Math.min(from.y, to.y);
            BufferedImage image = new BufferedImage(Math.max(1, squareWidth),
                    Math.max(1, squareHeight),
                    BufferedImage.TYPE_4BYTE_ABGR);

            Graphics g = image.getGraphics();
            g.setColor(color);
            if ((from.x > to.x && from.y < to.y)
                    || (from.x < to.x && from.y > to.y)) {
                g.drawLine(squareWidth - 1, 0, 0, squareHeight);
            } else {
                g.drawLine(0, 0, squareWidth, squareHeight);
            }
            g.dispose();

            paintImage = new SinglePointAddImage(image, new Point(positionX,
                    positionY));
        }

        @Override
        public ArrayList<PaintImage> getPaintImages() {
            ArrayList<PaintImage> images = new ArrayList<PaintImage>();
            images.add(paintImage);
            return images;
        }
    }

    public static class SinglePointAddImage implements PaintImage {

        protected BufferedImage image;
        protected Point point;

        public SinglePointAddImage(BufferedImage image, Point point) {
            this.image = image;
            this.point = point;
        }

        @Override
        public ArrayList<Point> getApplyPoints() {
            ArrayList<Point> points = new ArrayList<Point>();
            points.add(point);
            return points;
        }

        @Override
        public BufferedImage getImage() {
            return image;
        }

        @Override
        public boolean isAddChange() {
            return true;
        }
    }
}
