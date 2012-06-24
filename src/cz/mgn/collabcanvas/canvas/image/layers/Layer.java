/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.canvas.image.layers;

import cz.mgn.collabcanvas.canvas.image.network.NetworkImage;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public class Layer {

    protected int id;
    protected float opaqueness = 1f;
    protected BufferedImage image;
    protected NetworkImage networkImage = null;

    public Layer(int id, int width, int height) {
        this.id = id;
        setResolution(width, height);
    }

    public Layer(int id, int width, int height, NetworkImage networkImage) {
        this.id = id;
        this.networkImage = networkImage;
        setResolution(width, height);
    }

    public NetworkImage getNetworkImage() {
        return networkImage;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getID() {
        return id;
    }

    public float getOpaqueness() {
        return opaqueness;
    }

    public void setOpaqueness(float opaqueness) {
        this.opaqueness = opaqueness;
    }

    public void setResolution(int width, int height) {
        BufferedImage nImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        if (image != null) {
            Graphics g = nImage.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
        }
        image = nImage;
        if (networkImage != null) {
            networkImage.setResolution(width, height);
        }
    }
}
