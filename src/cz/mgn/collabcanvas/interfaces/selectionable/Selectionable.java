/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.selectionable;

import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public interface Selectionable {

    /**
     * vrati obrazek soucasneho oznaceni (alpha udava miru aplikace vyberu)
     */
    public BufferedImage getSelectionImage();

    /**
     * vybere vse
     */
    public void selectAll();

    /**
     * updatuje vyber
     */
    public void select(SelectionUpdate selectoinUpdate);
}
