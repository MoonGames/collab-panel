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
import java.awt.geom.Rectangle2D;
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
        Graphics2D g = (Graphics2D) this.image.getGraphics();
        g.clipRect(rect.x, rect.y, rect.width, rect.height);
        g.drawImage(alpha, 1, 1, null);
        g.drawImage(image.getZoomedImage(), 1, 1, null);
        g.drawImage(image.getSelection().getSelectionOutline(), 1, 1, null);
        g.dispose();
    }

    public void imageResized(int width, int height) {
        image = new BufferedImage(width + 2, height + 2, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f, new float[]{2f}, 0f));
        g.drawRect(0, 0, width, height);
        g.dispose();
        generateAlpha();
    }
}
