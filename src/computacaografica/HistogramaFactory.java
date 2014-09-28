/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

/**
 *
 * @author nicolas
 */
public class HistogramaFactory {
    
    public static Histograma buildHistograma(Imagem imagem) {
        Histograma histograma = new Histograma();
        for (int i = 0; i < imagem.getWidth(); i++) {
            for (int j = 0; j < imagem.getHeight(); j++) {
                histograma.addOne(imagem.getPixel(i, j));
            }
        }
        return histograma;
    }
    
    
}
