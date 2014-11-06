/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica.filtros;

import computacaografica.Imagem;

/**
 *
 * @author jouwee
 */
public abstract class GenericFiltro {
    
    private final int tamanho;

    public GenericFiltro(int tamanho) {
        this.tamanho = tamanho;
    }
    
    /**
     * 
     * @param imagem 
     */
    public abstract Imagem aplica(Imagem imagem);

    public int getTamanho() {
        return tamanho;
    }
    

}
