/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica.iterators;

import computacaografica.Imagem;

/**
 * Iterador de imagens
 * 
 */
public interface ImagemIterator {
    
    /**
     * Itera a imagem
     * 
     * @param imagem
     * @param callback 
     */
    public abstract void iterate(Imagem imagem, ImageIteratorCallback callback);
    
}
