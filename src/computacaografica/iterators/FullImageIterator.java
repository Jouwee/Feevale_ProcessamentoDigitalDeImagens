/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica.iterators;

import computacaografica.Imagem;

/**
 * Iterador da imagem completa
 */
public class FullImageIterator implements ImagemIterator {

    @Override
    public void iterate(Imagem imagem, ImageIteratorCallback callback) {
        if(imagem == null) {
            return;
        }
        for (int y = 0; y < imagem.getHeight(); y++) {
            for (int x = 0; x < imagem.getWidth(); x++) {
                callback.callback(x, y);
            }
        }
    }
    
}
