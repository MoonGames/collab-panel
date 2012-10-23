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

package cz.mgn.collabcanvas;

import cz.mgn.collabcanvas.canvas.CollabCanvas;
import cz.mgn.collabcanvas.factory.CollabCanvasFactory;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelKeyEvent;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelListener;
import cz.mgn.collabcanvas.interfaces.listenable.CollabPanelMouseEvent;
import cz.mgn.collabcanvas.interfaces.paintable.PaintData;
import cz.mgn.collabcanvas.interfaces.paintable.PaintImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class Main {

    public static void main(String args[]) {
        test();
    }

    @Deprecated
    public static void test() {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setSize(100, 100);

        CollabCanvas canvas = CollabCanvasFactory.createLocalCollabCanvas(0);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(canvas.getCanvasComponent(), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.pack();

        canvas.getPaintable().setResolution(200, 200);
        canvas.getPaintable().setLayersOrder(new int[]{1, 2, 3}, true);
        canvas.getPaintable().selectLayer(2);
        canvas.getPaintable().paint(new PaintData() {

            @Override
            public ArrayList<PaintImage> getPaintImages() {
                ArrayList<PaintImage> pi = new ArrayList<PaintImage>();
                pi.add(new PaintImage() {

                    @Override
                    public ArrayList<Point> getApplyPoints() {
                        ArrayList<Point> points = new ArrayList<Point>();
                        points.add(new Point(0, 0));
                        return points;
                    }

                    @Override
                    public BufferedImage getImage() {
                        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_4BYTE_ABGR);
                        Graphics g = image.getGraphics();
                        g.setColor(Color.PINK);
                        g.fillOval(2, 2, 100, 100);
                        g.dispose();
                        return image;
                    }

                    @Override
                    public boolean isAddChange() {
                        return true;
                    }
                });
                return pi;
            }
        });

        canvas.getListenable().addListener(new CollabPanelListener() {

            @Override
            public void keyEvent(CollabPanelKeyEvent keyEvent) {
                System.out.println("key event");
            }

            @Override
            public void mouseEvent(CollabPanelMouseEvent mouseEvent) {
                System.out.println("mouse event");
            }
        });
    }
}
