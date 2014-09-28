/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica.iterators;

import computacaografica.Imagem;

/**
 * Iterador para acima da diagonal superior
 */
public class AboveSuperiorDiagonalIterator implements ImagemIterator {

    @Override
    public void iterate(Imagem imagem, ImageIteratorCallback callback) {
        if(imagem == null) {
            return;
        }
        int lim = Math.max(imagem.getWidth(), imagem.getHeight());
        for (int i = 0; i < lim; i++) {
            int y = Math.min(i, imagem.getHeight() - 1);
            for(int x = i; x < imagem.getWidth(); x++) {
                callback.callback(x, y);
            }
        }
    }
    
}
