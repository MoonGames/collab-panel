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

package cz.mgn.collabcanvas.canvas.image.imageprocessing;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class ImageProcessor {

    public static void paintAdd(BufferedImage image, BufferedImage add, int x, int y) {
        Graphics g = image.getGraphics();
        g.drawImage(add, x, y, null);
        g.dispose();
    }

    public static void paintRemove(BufferedImage image, BufferedImage remove, int x, int y) {
        Graphics g = image.getGraphics();
        g.drawImage(remove, x, y, null);
        g.dispose();
    }

    public static void addToImage(BufferedImage image, BufferedImage add, int x, int y) {
        addToImage(image, add, x, y, x, y, add.getWidth(), add.getHeight());
    }

    public static void addToImage(BufferedImage image, BufferedImage add, int x, int y, int uX, int uY, int uWidth, int uHeight) {
        int dx1 = Math.max(x, uX);
        int dy1 = Math.max(y, uY);
        int dx2 = Math.min(x + add.getWidth(), uX + uWidth);
        int dy2 = Math.min(y + add.getHeight(), uY + uHeight);
        if ((dx2 - dx1) > 0 && (dy2 - dy1) > 0) {
            int sx1 = dx1 - x;
            int sy1 = dy1 - y;
            int sx2 = sx1 + (dx2 - dx1);
            int sy2 = sy1 + (dy2 - dy1);
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.drawImage(add, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
            g.dispose();
        }
    }

    public static void removeFromImage(BufferedImage image, BufferedImage remove, int x, int y) {
        removeFromImage(image, remove, x, y, x, y, remove.getWidth(), remove.getHeight());
    }

    public static void removeFromImage(BufferedImage image, BufferedImage remove, int x, int y, int uX, int uY, int uWidth, int uHeight) {
        int dx1 = Math.max(x, uX);
        int dy1 = Math.max(y, uY);
        int dx2 = Math.min(x + remove.getWidth(), uX + uWidth);
        int dy2 = Math.min(y + remove.getHeight(), uY + uHeight);
        if ((dx2 - dx1) > 0 && (dy2 - dy1) > 0) {
            int sx1 = dx1 - x;
            int sy1 = dy1 - y;
            int sx2 = sx1 + (dx2 - dx1);
            int sy2 = sy1 + (dy2 - dy1);
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setComposite(AlphaComposite.DstOut);
            g.drawImage(remove, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
            g.dispose();
        }
    }
}
