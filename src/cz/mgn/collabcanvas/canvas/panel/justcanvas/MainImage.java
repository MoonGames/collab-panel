/*
 * Collab desktop - Software for shared drawing via internet in real-time
 * Copyright (C) 2012 Martin Indra <aktive@seznam.cz>
 *
 * This file is part of Collab desktop.
 *
 * Collab desktop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Collab desktop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Collab desktop.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.panel.justcanvas;

import cz.mgn.collabcanvas.canvas.image.CanvasImage;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public class MainImage {

    protected static final int ALPHA_BACKGROUND_BOX_SIZE = 8;
    protected static final int ALPHA_BACKGROUND_COLOR_A = 0xff999999;
    protected static final int ALPHA_BACKGROUND_COLOR_B = 0xff666666;
    protected BufferedImage image;
    protected BufferedImage alpha;

    protected MainImage() {
        imageResized(1, 1);
    }

    protected void generateAlpha() {
        int w = image.getWidth() - 2;
        int h = image.getHeight() - 2;
        alpha = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D ga = (Graphics2D) alpha.getGraphics();

        ga.setColor(new Color(ALPHA_BACKGROUND_COLOR_B));
        ga.fillRect(0, 0, w, h);
        ga.setColor(new Color(ALPHA_BACKGROUND_COLOR_A));

        boolean shiftB = true;
        for (int y = 0; y < h; y += ALPHA_BACKGROUND_BOX_SIZE) {
            int shift = shiftB ? ALPHA_BACKGROUND_BOX_SIZE : 0;
            shiftB = !shiftB;
            for (int x = 0; x < w; x += (2 * ALPHA_BACKGROUND_BOX_SIZE)) {
                ga.fillRect(shift + x, y, ALPHA_BACKGROUND_BOX_SIZE, ALPHA_BACKGROUND_BOX_SIZE);
            }
        }
        ga.dispose();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void zoomChanged(float zoom, CanvasImage image) {
        image.getSelection().setOutlineZoom(zoom);
    }

    public void reconstruct(CanvasImage image) {
        reconstruct(new Rectangle(0, 0, image.getWidth() - 2, image.getHeight() - 2), image);
    }

    public void reconstruct(Rectangle rect, CanvasImage image) {
        synchronized (this) {
            Graphics2D g = (Graphics2D) this.image.getGraphics();
            g.clipRect(1, 1, this.image.getWidth() - 2, this.image.getHeight() - 2);
            int x1 = rect.x;
            int x2 = rect.x + rect.width;
            int y1 = rect.y;
            int y2 = rect.y + rect.height;

            g.drawImage(alpha, x1 + 1, y1 + 1, x2 + 1, y2 + 1, x1, y1, x2, y2, null);
            g.drawImage(image.getZoomedImage(), x1 + 1, y1 + 1, x2 + 1, y2 + 1, x1, y1, x2, y2, null);
            g.drawImage(image.getSelection().getSelectionOutline(), x1 + 1, y1 + 1, x2 + 1, y2 + 1, x1, y1, x2, y2, null);
            g.dispose();
        }
    }

    public void imageResized(int width, int height) {
        synchronized (this) {
            image = new BufferedImage(width + 2, height + 2, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setColor(Color.WHITE);
            g.drawRect(0, 0, width + 1, height + 1);
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[]{2f}, 0f));
            g.drawRect(0, 0, width + 1, height + 1);
            g.dispose();
            generateAlpha();
        }
    }
}
