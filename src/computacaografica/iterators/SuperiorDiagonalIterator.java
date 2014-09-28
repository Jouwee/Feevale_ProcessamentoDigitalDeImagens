/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica.iterators;

import computacaografica.Imagem;

public class SuperiorDiagonalIterator implements ImagemIterator {

    @Override
    public void iterate(Imagem imagem, ImageIteratorCallback callback) {
        if(imagem == null) {
            return;
        }
        int lim = Math.max(imagem.getWidth(), imagem.getHeight());
        for (int i = 0; i < lim; i++) {
            int x = Math.min(i, imagem.getWidth() - 1);
            int y = Math.min(i, imagem.getHeight() - 1);
            callback.callback(x, y);
        }
    }
    
}
