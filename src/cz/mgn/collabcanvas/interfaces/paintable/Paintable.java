/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabcanvas.interfaces.paintable;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author indy
 */
public interface Paintable {

    /**
     * nakresli zmenu do aktualne vybrane vrstvy
     */
    public void paint(PaintData paintData);

    /**
     * nastavi vrstvu jako oznacenou selectnutou v pripade ze vrstva se zadanym
     * id neexistuje neprovede zadnou akci
     */
    public void selectLayer(int layerID);

    /*
     * nastavi poradi vrstvev, v pripade neodstranovani prebyvajichc vrstev
     * zbyle prida nekonec v jejich soucasnem poradi a vytvori ty co zatim
     * neexistujou priklad: existujici vrstvy {1, 2, 3, 4, 5}, parametr {2, 1,
     * 4, 7}, vrstvy po provedeni {2, 1, 4, 7, 3, 5} (bez 3 a 5 pri odstranovani
     * prebytecnych vrstev)
     *
     * @param remove jestli se maji prebytecne vrstvy co nejsou uvedene v poradi
     * mazat
     */
    public void setLayersOrder(int[] order, boolean remove);

    /**
     * vrati usporadani vrstev 0 prvek = vrstva nejvice vespod
     */
    public int[] getLayersOrder();

    /**
     * nastavi jestli je vrstva viditelna
     */
    public void setLayerVisibility(int layerID, boolean visible);

    /**
     * vrati jestli je vrstva zviditelnena 1 = ano, 0 = ne, -1 vrstva
     * neexsistuje
     */
    public int getLayerVisibility(int layerID);

    /**
     * nastavi velikost aplikace vrstvy (interval <0; 1>) 1 = nakresli se 100%,
     * 0 = je neviditelna
     */
    public void setLayerOpaqueness(int layerID, float opaqueness);

    /**
     * vrati velikost aplikace vrstvy (interval <0; 1>) 1 = kresli se 100%, 0 =
     * je neviditelna, -1 v pripade ze vrstva neexistuje
     */
    public float getLayerOpaqueness(int layerID);

    /**
     * prida novou vrstvu a vrati jeji ID, -1 pokud ji nevytvori (network mode)
     */
    public int addLayer();

    /**
     * @return vrati jestli se vrstvu podarilo smazat (pokud vrstva s timto id
     * neexistuje nebo je zapnut network mode vrati false)
     */
    public boolean removeLayer(int layerID);

    /**
     * vrati sirku kresleneho obrazku
     */
    public int getWidth();

    /**
     * vrati vysku kresleneho obrazku
     */
    public int getHeight();

    /**
     * zmeni rozmery obrazku (puvodni obrazek se nakresli do rohu, pripadne
     * orizne)
     */
    public void setResolution(int width, int height);

    /**
     * vrati cast oznacene vrstvy, null v pripade ze obdelnik zasahuje mimo
     * vrstvu
     * 
     * @param rect if null, return all layer
     */
    public BufferedImage getSelectedLayerImage(Rectangle rect);

    /**
     * vrati cast vsech vrstev po jejich rekombinaci, null v pripade ze obdelnik
     * zasahuje mimo obrazek
     * 
     * @param rect if null, return all image
     */
    public BufferedImage getImage(Rectangle rect);

    /**
     * vrati oznacenou cast obrazku zbytek zustane alpha
     */
    public BufferedImage getImageSelection();

    /**
     * vrati oznacenou cast vrstvy zbytek pruhlednost
     */
    public BufferedImage getSelectedLayerImageSelection();
}
