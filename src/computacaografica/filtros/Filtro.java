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
public abstract class Filtro {
    
    private final int tamanho;

    public Filtro(int tamanho) {
        this.tamanho = tamanho;
    }
    
    /**
     * 
     * @param pixels
     * @return 
     */
    public abstract int calcula(int[][] pixels);
    
    /**
     * 
     * @param imagem 
     */
    public Imagem aplica(Imagem imagem) {

        Imagem novaImagem = new Imagem(imagem.getWidth(), imagem.getHeight());
        
        int metadeTamanho = (int) Math.floor((float)tamanho / 2);
        
        for(int x = metadeTamanho; x < imagem.getWidth() - metadeTamanho; x++) {
            for(int y = metadeTamanho; y < imagem.getHeight() - metadeTamanho; y++) {
                int[][] pixels = new int[tamanho][tamanho];
                
                for(int x2 = 0; x2 < tamanho; x2++) {
                    for(int y2 = 0; y2 < tamanho; y2++) {
                        pixels[x2][y2] = imagem.getPixel(x + x2 - metadeTamanho, y + y2 - metadeTamanho);
                    }
                }
                
                novaImagem.setPixel(x, y, calcula(pixels));
            }
        }
        return novaImagem;
        
        
    }
    
    
}
