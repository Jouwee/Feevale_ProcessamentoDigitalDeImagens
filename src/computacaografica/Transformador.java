/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica;

/**
 *
 * @author jouwee
 */
public class Transformador {
    
    private Imagem imagem;
    private double[][] matrizTransformacao;

    public Transformador(Imagem imagem, double[][] matrizTransformacao) {
        this.imagem = imagem;
        this.matrizTransformacao = matrizTransformacao;
    }
    
    public Imagem transforma() {
        Imagem newImagem = new Imagem(imagem.getWidth(), imagem.getHeight());
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
        
        
        
        
        
        return newImagem;
    }
    
}
