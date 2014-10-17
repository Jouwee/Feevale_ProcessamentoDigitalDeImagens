/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica.filtros;

/**
 * Limiar / Threashold
 * 
 * @author Jouwee
 */
public class Threshold {
    
    /** Valor atual do Threshold */
    private int threshold;

    /**
     * Cria o threshold
     * 
     * @param threshold 
     */
    public Threshold(int threshold) {
        this.threshold = threshold;
    }
    
    /**
     * Returna o valor do threshold
     * 
     * @return int
     */
    public int getThreshold() {
        return threshold;
    }

    /**
     * Define o threshold
     * 
     * @param threshold
     */
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
    
}
