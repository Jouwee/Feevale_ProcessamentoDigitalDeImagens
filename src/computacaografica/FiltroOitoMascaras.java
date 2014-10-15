/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica;

/**
 *
 * @author Nicolas
 */
public class FiltroOitoMascaras extends Filtro {
    
    private final int[][] mascaraN;
    private final int[][] mascaraNE;
    private final int[][] mascaraE;
    private final int[][] mascaraSE;
    private final int[][] mascaraS;
    private final int[][] mascaraSW;
    private final int[][] mascaraW;
    private final int[][] mascaraNW;
    private final int threshold;

    public FiltroOitoMascaras(int threshold, int[][] mascaraN, int[][] mascaraNE, int[][] mascaraE, int[][] mascaraSE, int[][] mascaraS, int[][] mascaraSW, int[][] mascaraW, int[][] mascaraNW) {
        super(3);
        this.mascaraN = mascaraN;
        this.mascaraNE = mascaraNE;
        this.mascaraE = mascaraE;
        this.mascaraSE = mascaraSE;
        this.mascaraS = mascaraS;
        this.mascaraSW = mascaraSW;
        this.mascaraW = mascaraW;
        this.mascaraNW = mascaraNW;
        this.threshold = threshold;
    }
    
    
    
    
    @Override
    public int calcula(int[][] pixels) {
        double gradienteN = 0.0d;
        double gradienteNE = 0.0d;
        double gradienteE = 0.0d;
        double gradienteSE = 0.0d;
        double gradienteS = 0.0d;
        double gradienteSW = 0.0d;
        double gradienteW = 0.0d;
        double gradienteNW = 0.0d;
        
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                int valor = pixels[x][y];
                gradienteN += (double)valor * (double)mascaraN[x][y];
                gradienteNE += (double)valor * (double)mascaraNE[x][y];
                gradienteE += (double)valor * (double)mascaraE[x][y];
                gradienteSE += (double)valor * (double)mascaraSE[x][y];
                gradienteS += (double)valor * (double)mascaraS[x][y];
                gradienteSW += (double)valor * (double)mascaraSW[x][y];
                gradienteW += (double)valor * (double)mascaraW[x][y];
                gradienteNW += (double)valor * (double)mascaraNW[x][y];
                
            }
        }
        
        
        
        double gradiente = gradienteN;
        if(gradienteNE > gradiente) {
            gradiente = gradienteNE;
        }
        if(gradienteE > gradiente) {
            gradiente = gradienteE;
        }
        if(gradienteSE > gradiente) {
            gradiente = gradienteSE;
        }
        if(gradienteS > gradiente) {
            gradiente = gradienteS;
        }
        if(gradienteSW > gradiente) {
            gradiente = gradienteSW;
        }
        if(gradienteW > gradiente) {
            gradiente = gradienteW;
        }
        if(gradienteNW > gradiente) {
            gradiente = gradienteNW;
        }
        
        int novoValor = 0;
        if(gradiente > threshold) {
            novoValor = 255;
        }
        return novoValor;
    }

    
}
