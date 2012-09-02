/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.panel.justcanvas;

import cz.mgn.collabcanvas.canvas.image.CanvasImage;
import cz.mgn.collabcanvas.canvas.image.CanvasImageChangeListener;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author indy
 */
public class JustCanvas extends JPanel implements Visible, CanvasImageChangeListener, MouseListener, MouseMotionListener {

    protected static final Color background = new Color(0xfff6f6f6);
    protected CanvasImage canvasImage;
    //
    protected MainImage mainImage = new MainImage();
    protected static BufferedImage logo;
    protected ToolCursor toolCursor;
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
        Point result = new Point(
                point.x - (getWidth() - mainImage.getImage().getWidth()) / 2,
                point.y - (getHeight() - mainImage.getImage().getHeight()) / 2);
        result.x /= canvasImage.getZoom();
        result.y /= canvasImage.getZoom();
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
            int xx = mouseX + toolCursor.getRelativeLocatoin().x;
            int yy = mouseY + toolCursor.getRelativeLocatoin().y;
            if (toolCursor.getLocationMode() == ToolCursor.LOCATION_MODE_CENTER) {
                xx -= img.getWidth() / 2;
                yy -= img.getHeight() / 2;
            }
            g.drawImage(img, xx, yy, this);
        }
    }

    protected void paintToolImage(Graphics2D g) {
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
        //TODO:
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
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        synchronized (this) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        synchronized (this) {
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();
        }
    }
}
