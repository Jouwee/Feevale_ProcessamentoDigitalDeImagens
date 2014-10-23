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
public class FiltroReducao extends Filtro implements FiltroLimiarizado {

    private ThresholdRange thresholdRange;
    private Threshold threshold;
    
    
    public FiltroReducao() {
        super(3);
        threshold = new Threshold(127);
        thresholdRange = new ThresholdRange(0, 255);
    }

    
    
    @Override
    public int calcula(int[][] pixels) {
        int menor = thresholdRange.getCeiling();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int r = pixels[i][j] - threshold.getThreshold();
                if(r < menor) {
                    menor = r;
                }
            }
        }
        return menor;
    }

    @Override
    public Threshold getThreshold() {
        return threshold;
    }

    @Override
    public void getThreshold(Threshold threshold) {
        this.threshold = threshold;
    }

    @Override
    public ThresholdRange getThresholdRange() {
        return thresholdRange;
    }
    
}
