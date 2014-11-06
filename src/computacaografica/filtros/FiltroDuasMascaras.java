/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica.filtros;

/**
 *
 * @author jouwee
 */
public class FiltroDuasMascaras extends Filtro implements FiltroLimiarizado {

    private Threshold threshold;
    private final ThresholdRange thresholdRange;
    private final int[][] mascaraX;
    private final int[][] mascaraY;
    
    
    public FiltroDuasMascaras(Threshold threshold, ThresholdRange thresholdRange, int[][] mascaraX, int[][] mascaraY) {
        super(3);
        this.threshold = threshold;
        this.thresholdRange = thresholdRange;
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
                gradienteX += valor * mascaraX[x][y];
                gradienteY += valor * mascaraY[x][y];
                
            }
        }
        
        double gradiente = Math.sqrt(Math.pow(gradienteX, 2) + Math.pow(gradienteY, 2));
        
        int novoValor = 0;
        if(gradiente > threshold.getThreshold()) {
            novoValor = 255;
        }
        return novoValor;
    }

    @Override
    public Threshold getThreshold() {
        return threshold;
    }

    @Override
    public void setThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    @Override
    public ThresholdRange getThresholdRange() {
        return thresholdRange;
    }
    
}
