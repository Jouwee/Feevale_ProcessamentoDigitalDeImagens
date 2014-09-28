/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica.iterators;

import computacaografica.Imagem;

public class UnderInferiorDiagonalIterator implements ImagemIterator {

    @Override
    public void iterate(Imagem imagem, ImageIteratorCallback callback) {
        if(imagem == null) {
            return;
        }
        int lim = Math.max(imagem.getWidth(), imagem.getHeight());
        for (int i = 0; i < lim; i++) {
            int y = imagem.getHeight() - Math.min(i, imagem.getHeight() - 1) - 1;
            for(int x = 0; x < i; x++) {
                callback.callback(x, y);
            }
        }
    }
    
}
