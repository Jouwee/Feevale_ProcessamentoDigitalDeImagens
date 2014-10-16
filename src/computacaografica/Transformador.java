/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica;

import java.awt.Point;

/**
 *
 * @author jouwee
 */
public class Transformador {
    
    private final double[][] matrizTransformacao;

    public Transformador(double[][] matrizTransformacao) {
        this.matrizTransformacao = matrizTransformacao;
    }
    
    public Imagem transforma(Imagem imagem) {
        return transform(imagem, false);
    }
    
    public Imagem transform(Imagem imagem, boolean fill) {
        Imagem newImagem = new Imagem(imagem.getWidth(), imagem.getHeight(), fill);
        int z = 1;
        int halfX = imagem.getWidth() / 2;
        int halfY = imagem.getHeight() / 2;
       
        
        for (int x = -halfX; x < halfX; x++) {
            for (int y = -halfY; y < halfY; y++) {
                int newX = (int) Math.round(x * matrizTransformacao[0][0] + y * matrizTransformacao[0][1] + z * matrizTransformacao[0][2]);
                int newY = (int) Math.round(x * matrizTransformacao[1][0] + y * matrizTransformacao[1][1] + z * matrizTransformacao[1][2]);
                
                newX += halfX;
                newY += halfY;
                
                if(newX >= imagem.getWidth() || newY >= imagem.getHeight() || newX < 0 || newY < 0) {
                    continue;
                }
                
                
                newImagem.setPixel(newX, newY, imagem.getPixel(x + halfX, y + halfY));
                
            }
        }
        
        
        if(fill) {
            newImagem = fill(newImagem);
        }
        
        
        return newImagem;
    }
    
    /**
     * Preenche pixels vazios
     * 
     * @param imagem
     * @return 
     */
    private Imagem fill(Imagem imagem) {
        
        Point lastPixelPos = null;
        int lastPixel = 0;
        for (int y = 0; y < imagem.getHeight(); y++) {
            for (int x = 0; x < imagem.getWidth(); x++) {
                if(imagem.getPixelAlpha(x, y) != 0) {
                    if(lastPixelPos != null) {
                        int media = (imagem.getPixel(x, y) + lastPixel) / 2;
                        for (int i = lastPixelPos.x + 1; i < x; i++) {
                            imagem.setPixel(i, y, media);
                        }
                    }
                    lastPixelPos = new Point(x, y);
                    lastPixel = imagem.getPixel(x, y);
                }
                if(x == imagem.getWidth() - 1 && lastPixelPos != null) {
                    for (int i = lastPixelPos.x + 1; i < imagem.getWidth(); i++) {
                        imagem.setPixel(i, y, lastPixel);

                    }
                }
            }
            lastPixelPos = null;
            lastPixel = 0;
        }
        
        lastPixelPos = null;
        lastPixel = 0;
        for (int x = 0; x < imagem.getWidth(); x++) {
            for (int y = 0; y < imagem.getHeight(); y++) {
                if(imagem.getPixelAlpha(x, y) != 0) {
                    if(lastPixelPos != null) {
                        int media = (imagem.getPixel(x, y) + lastPixel) / 2;
                        for (int i = lastPixelPos.y + 1; i < y; i++) {
                            imagem.setPixel(x, i, media);
                            
                        }
                    }
                    lastPixelPos = new Point(x, y);
                    lastPixel = imagem.getPixel(x, y);
                }
                if(y == imagem.getHeight() - 1 && lastPixelPos != null) {
                    for (int i = lastPixelPos.y + 1; i < imagem.getHeight(); i++) {
                        imagem.setPixel(x, i, lastPixel);

                    }
                }
            }
            lastPixelPos = null;
            lastPixel = 0;
        }
        
        return imagem;
    }
    
}
