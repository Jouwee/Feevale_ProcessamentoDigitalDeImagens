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
public class FiltroAfinamento extends Filtro {

    public FiltroAfinamento() {
        super(3);
    }

    @Override
    public int calcula(int[][] pixels) {
        int p2 = pixels[1][0] / 255;
        int p3 = pixels[2][0] / 255;
        int p4 = pixels[2][1] / 255;
        int p5 = pixels[2][2] / 255;
        int p6 = pixels[1][2] / 255;
        int p7 = pixels[0][2] / 255;
        int p8 = pixels[0][1] / 255;
        int p9 = pixels[0][0] / 255;
        int np = p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
        int sp = (p2 < p3 ? 1 : 0) +
                 (p3 < p4 ? 1 : 0) +
                 (p4 < p5 ? 1 : 0) +
                 (p5 < p6 ? 1 : 0) +
                 (p6 < p7 ? 1 : 0) +
                 (p7 < p8 ? 1 : 0) +
                 (p8 < p9 ? 1 : 0) +
                 (p9 < p2 ? 1 : 0);
        
        if((np >= 2 || np <= 6) && sp == 1 && (p2 * p4 * p6 == 0) && (p4 * p6 * p8 == 0)) {
            return 0;
        }
        return pixels[1][1];
    }

    
    
    @Override
    public Imagem aplica(Imagem imagem) {
        Imagem novaImagem = new Imagem(imagem.getWidth(), imagem.getHeight());
        
        boolean mudou = true;
        
        while(mudou) {
            mudou = false;
        
            int metadeTamanho = (int) Math.floor((float)getTamanho() / 2);

            for(int x = metadeTamanho; x < imagem.getWidth() - metadeTamanho; x++) {
                for(int y = metadeTamanho; y < imagem.getHeight() - metadeTamanho; y++) {
                    int[][] pixels = new int[getTamanho()][getTamanho()];

                    for(int x2 = 0; x2 < getTamanho(); x2++) {
                        for(int y2 = 0; y2 < getTamanho(); y2++) {
                            pixels[x2][y2] = imagem.getPixel(x + x2 - metadeTamanho, y + y2 - metadeTamanho);
                        }
                    }

                    int v = Math.max(Math.min(calcula(pixels), 255), 0);
                    
                    if(v != imagem.getPixel(x, y)) {
                        mudou = true;
                    }
                    
                    novaImagem.setPixel(x, y, v);
                }
            }
            
            imagem = novaImagem;
            
        }
        return novaImagem;
    }
    
}
