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
public class FiltroDuasMascaras extends Filtro {

    private final int treshold;
    private final int[][] mascaraX;
    private final int[][] mascaraY;
    
    public FiltroDuasMascaras(int treshold, int[][] mascaraX, int[][] mascaraY) {
        super(3);
        this.treshold = treshold;
        this.mascaraX = mascaraX;
        this.mascaraY = mascaraY;
    }
    
    @Override
    public int calcula(int[][] pixels) {
        double gradienteX = 0.0d;
        double gradienteY = 0.0d;
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int valor = pixels[x][y];
                gradienteX += (double)valor * (double)mascaraX[x][y];
                gradienteY += (double)valor * (double)mascaraY[x][y];
                
            }
        }
        
        double gradiente = Math.sqrt(Math.pow(gradienteX, 2) + Math.pow(gradienteY, 2));
        
        int novoValor = 0;
        if(gradiente > treshold) {
            novoValor = 255;
        }
        return novoValor;
    }
    
}
