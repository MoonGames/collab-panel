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

package cz.mgn.collabcanvas.canvas.panel.justcanvas;

import cz.mgn.collabcanvas.canvas.image.CanvasImage;
import cz.mgn.collabcanvas.canvas.image.CanvasImageChangeListener;
import cz.mgn.collabcanvas.interfaces.informing.InfoListener;
import cz.mgn.collabcanvas.interfaces.informing.Informing;
import cz.mgn.collabcanvas.interfaces.visible.MouseCursor;
import cz.mgn.collabcanvas.interfaces.visible.ToolCursor;
import cz.mgn.collabcanvas.interfaces.visible.ToolImage;
import cz.mgn.collabcanvas.interfaces.visible.Visible;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Martin Indra <aktive@seznam.cz>
 */
public class JustCanvas extends JPanel implements Informing, Visible, CanvasImageChangeListener, MouseListener, MouseMotionListener {

    //info
    protected Set<InfoListener> infoListeners = new TreeSet<InfoListener>();
    protected static final Color background = new Color(0xfff6f6f6);
    protected CanvasImage canvasImage;
    //
    protected MainImage mainImage = new MainImage();
    protected static BufferedImage logo;
    protected ToolCursor toolCursor;
    protected ToolImage toolImage;
    protected int mouseX = -1;
    protected int mouseY = -1;

    static {
        try {
            logo = ImageIO.read(JustCanvas.class.getResourceAsStream("/resources/logo_gray.png"));
        } catch (IOException ex) {
            Logger.getLogger(JustCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JustCanvas(CanvasImage canvasImage) {
        this.canvasImage = canvasImage;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public Point translateMousePoint(Point point) {
        Point result = translateMousePointNoZoom(point);
        result.x /= canvasImage.getZoom();
        result.y /= canvasImage.getZoom();
        return result;
    }

    public Point translateMousePointNoZoom(Point point) {
        Point result = new Point(
                point.x - Math.max(getWidth() - mainImage.getImage().getWidth(), 0) / 2,
                point.y - Math.max(getHeight() - mainImage.getImage().getHeight(), 0) / 2);
        return result;
    }

    @Override
    public void paintComponent(Graphics g2) {
        Rectangle rect = getVisibleRect();
        Graphics2D g = (Graphics2D) g2;
        g.clipRect(rect.x, rect.y, rect.width, rect.height);
        paintBackground(g);
        paintImage(g);
        paintToolImage(g);
        paintToolCursor(g);
    }

    protected void paintToolCursor(Graphics2D g) {
        if (toolCursor != null && mouseX >= 0) {
            BufferedImage img = null;
            if (toolCursor.isScalingSupported()) {
                img = toolCursor.getScaledCursorImage(canvasImage.getZoom());
            } else {
                img = toolCursor.getCursorImage();
                img = new BufferedImage((int) (img.getWidth() * canvasImage.getZoom()), (int) (img.getHeight() * canvasImage.getZoom()), img.getType());
                Graphics2D ig = (Graphics2D) img.getGraphics();
                ig.scale(canvasImage.getZoom(), canvasImage.getZoom());
                ig.drawImage(toolCursor.getCursorImage(), 0, 0, null);
                ig.dispose();
            }
            int xx = (int) (mouseX + toolCursor.getRelativeLocatoin().x * canvasImage.getZoom());
            int yy = (int) (mouseY + toolCursor.getRelativeLocatoin().y * canvasImage.getZoom());
            if (toolCursor.getLocationMode() == ToolCursor.LOCATION_MODE_CENTER) {
                xx -= img.getWidth() / 2;
                yy -= img.getHeight() / 2;
            }
            g.drawImage(img, xx, yy, this);
        }
    }

    protected void paintToolImage(Graphics2D g) {
        if (toolImage != null && mouseX >= 0) {
            Rectangle clip = g.getClipBounds();

            BufferedImage img = mainImage.getImage();
            int w = img.getWidth();
            int h = img.getHeight();
            int x = (getWidth() - w) / 2;
            int y = (getHeight() - h) / 2;
            g.clip(new Rectangle(x, y, w, h));

            float zoom = canvasImage.getZoom();
            if (toolImage.isScalingSupported()) {
                img = toolImage.getScaledToolImage(zoom);
            } else {

                w = (int) (toolImage.getToolImage().getWidth() * zoom);
                h = (int) (toolImage.getToolImage().getHeight() * zoom);
                img = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D g2 = (Graphics2D) img.getGraphics();
                g2.drawImage(toolImage.getToolImage(), 0, 0, w, h, null);
                g2.dispose();
            }
            x = mouseX;
            y = mouseY;
            x += toolImage.getRelativeLocatoin().x * zoom;
            y += toolImage.getRelativeLocatoin().y * zoom;
            g.drawImage(img, x, y, null);

            g.setClip(clip);
        }
    }

    protected void paintImage(Graphics2D g) {
        BufferedImage img = mainImage.getImage();
        int x = (getWidth() - img.getWidth()) / 2;
        int y = (getHeight() - img.getHeight()) / 2;
        g.drawImage(img, x, y, null);
    }

    protected void paintBackground(Graphics2D g) {
        g.setColor(background);
        g.fillRect(0, 0, getWidth(), getHeight());
        int size = Math.min(getWidth(), getHeight());
        float scale = 1;
        if ((size / 2f) < logo.getWidth()) {
            scale = Math.max(0.1f, (size / 2f) / logo.getWidth());
            g.scale(scale, scale);
        }
        g.drawImage(logo, 0, (int) (getHeight() / scale) - logo.getHeight(), null);
        g.scale(1 / scale, 1 / scale);
    }

    protected void infoInformings() {
        int x = mouseX, y = mouseY;
        if (x >= 0 && y >= 0) {
            Point translatedMousePoint = translateMousePoint(new Point(x, y));
            if (translatedMousePoint.x > canvasImage.getWidth() || translatedMousePoint.y > canvasImage.getHeight()) {
                x = -1;
                y = -1;
            }
            if (x >= 0 && y >= 0) {
                x = translatedMousePoint.x;
                y = translatedMousePoint.y;
            }
        }
        for (InfoListener infoListener : infoListeners) {
            infoListener.mouseMoved(x, y);
        }
    }

    @Override
    public void setMouseCursor(MouseCursor mouseCursor) {
        //TODO:
    }

    @Override
    public void setToolCursor(ToolCursor toolCursor) {
        synchronized (this) {
            this.toolCursor = toolCursor;
            repaint();
        }
    }

    @Override
    public void setToolImage(ToolImage toolImage) {
        synchronized(this) {
            this.toolImage = toolImage;
            repaint();
        }
    }

    @Override
    public void change(Rectangle rect) {
        synchronized (this) {
            mainImage.reconstruct(rect, canvasImage);
            repaint();
        }
    }

    @Override
    public void selectionChange() {
        synchronized (this) {
            mainImage.reconstruct(canvasImage);
            repaint();
        }
    }

    @Override
    public void imageResized(int width, int height) {
        synchronized (this) {
            setPreferredSize(new Dimension(width, height));
            setSize(getPreferredSize());
            mainImage.imageResized(width, height);
        }
    }

    @Override
    public void zoomChanged(float zoom) {
        synchronized (this) {
            mainImage.zoomChanged(zoom, canvasImage);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        requestFocus();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        synchronized (this) {
            mouseX = -1;
            mouseY = -1;
            repaint();
        }
        infoInformings();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        synchronized (this) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
        }
        infoInformings();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        synchronized (this) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
        }
        infoInformings();
    }

    @Override
    public Set<InfoListener> getInfoListeners() {
        synchronized (this) {
            return infoListeners;
        }
    }

    @Override
    public void addInfoListener(InfoListener listener) {
        synchronized (this) {
            infoListeners.add(listener);
        }
    }

    @Override
    public boolean removeInfoListener(InfoListener listener) {
        synchronized (this) {
            return infoListeners.remove(listener);
        }
    }
}
