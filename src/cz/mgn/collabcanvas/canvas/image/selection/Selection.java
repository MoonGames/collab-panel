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
package cz.mgn.collabcanvas.canvas.image.selection;

import cz.mgn.collabcanvas.canvas.utils.graphics.OutlineUtil;
import cz.mgn.collabcanvas.interfaces.selectionable.SelectionUpdate;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public class Selection {

    protected BufferedImage selectionFilterImage = null;
    protected BufferedImage selectionOutline = null;
    protected float zoom = 1f;
    protected boolean all = true;

    public Selection() {
        setResolution(1, 1);
    }

    public void setResolution(int width, int height) {
        selectionFilterImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        generateOutline();
    }

    public BufferedImage filterPaint(int x, int y, BufferedImage source) {
        if (all) {
            return source;
        }
        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) result.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.setComposite(AlphaComposite.DstIn);
        g.drawImage(selectionFilterImage, 0, 0, result.getWidth(), result.getHeight(),
                x, y, x + result.getWidth(), y + result.getHeight(), null);
        g.dispose();
        return result;
    }

    public BufferedImage getSelectionOutline() {
        return selectionOutline;
    }

    public BufferedImage getSelectionFilterImage() {
        return selectionFilterImage;
    }

    public void setOutlineZoom(float zoom) {
        this.zoom = zoom;
        generateOutline();
    }

    protected void generateOutline() {
        if (all) {
            selectionOutline = null;
            return;
        }
        BufferedImage outlineSource = selectionFilterImage;
        if (zoom != 1f) {
            outlineSource = new BufferedImage((int) (selectionFilterImage.getWidth() * zoom),
                    (int) (selectionFilterImage.getHeight() * zoom), BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = (Graphics2D) outlineSource.getGraphics();
            g.scale(zoom, zoom);
            g.drawImage(selectionFilterImage, 0, 0, null);
            g.dispose();
        }
        selectionOutline = OutlineUtil.generateOutline(outlineSource, Color.BLACK, Color.WHITE, true);
    }

    public void update(SelectionUpdate select) {
        if (select == null) {
            all = true;
            Graphics2D g = (Graphics2D) selectionFilterImage.getGraphics();
            g.setComposite(AlphaComposite.Clear);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, selectionFilterImage.getWidth(), selectionFilterImage.getHeight());
            g.dispose();
        } else {
            switch (select.getUpdateMode()) {
                case SelectionUpdate.MODE_INTERSECTION:
                    updateIntersection(select);
                    break;
                case SelectionUpdate.MODE_UNIOIN:
                    updateUnion(select);
                    break;
                case SelectionUpdate.MODE_REMOVE:
                    updateRemove(select);
                    break;
                case SelectionUpdate.MODE_XOR:
                    updateXOR(select);
                    break;
                default:
                    updateReplace(select);
                    break;
            }
            all = false;

        }
        generateOutline();
    }

    protected void updateRemove(SelectionUpdate select) {
        Graphics2D g = (Graphics2D) selectionFilterImage.getGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT, select.getApplyAmount()));
        g.drawImage(select.getUpdateImage(), select.getUpdateCoordinates().x, select.getUpdateCoordinates().y, null);
        g.dispose();
    }

    protected void updateUnion(SelectionUpdate select) {
        Graphics2D g = (Graphics2D) selectionFilterImage.getGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, select.getApplyAmount()));
        g.drawImage(select.getUpdateImage(), select.getUpdateCoordinates().x, select.getUpdateCoordinates().y, null);
        g.dispose();
    }

    protected void updateXOR(SelectionUpdate select) {
        Graphics2D g = (Graphics2D) selectionFilterImage.getGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.XOR, select.getApplyAmount()));
        g.drawImage(select.getUpdateImage(), select.getUpdateCoordinates().x, select.getUpdateCoordinates().y, null);
        g.dispose();
    }

    protected void updateIntersection(SelectionUpdate select) {
        BufferedImage update = new BufferedImage(selectionFilterImage.getWidth(), selectionFilterImage.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = (Graphics2D) update.getGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, select.getApplyAmount()));
        g.drawImage(select.getUpdateImage(), select.getUpdateCoordinates().x, select.getUpdateCoordinates().y, null);
        g.dispose();
        g = (Graphics2D) selectionFilterImage.getGraphics();
        g.setComposite(AlphaComposite.SrcIn);
        g.drawImage(update, 0, 0, null);
        g.dispose();
    }

    protected void updateReplace(SelectionUpdate select) {
        BufferedImage update = select.getUpdateImage();
        Graphics2D g = (Graphics2D) selectionFilterImage.getGraphics();
        g.setColor(new Color(0xff000000));
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(0, 0, selectionFilterImage.getWidth(), selectionFilterImage.getHeight());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, select.getApplyAmount()));
        g.drawImage(update, select.getUpdateCoordinates().x, select.getUpdateCoordinates().y, null);
        g.dispose();
    }
}
