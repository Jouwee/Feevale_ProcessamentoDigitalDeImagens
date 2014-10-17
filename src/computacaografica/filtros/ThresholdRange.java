/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica.filtros;

/**
 * Limites de um limiar / Threshold
 * 
 * @author Jouwee
 */
public class ThresholdRange {
    
    /** Limite baixo do limiar */
    private int floor;
    /** Limite alto do limiar */
    private int ceiling;

    /**
     * Cria um novo limite de limiar
     * 
     * @param floor Limite baixo
     * @param ceiling Limite alto
     */
    public ThresholdRange(int floor, int ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    /**
     * Retorna o limite baixo do Threshold
     * 
     * @return int
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Define o limite baixo do Threshold
     * 
     * @param floor 
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * Retorna o limite alto do Threshold
     * 
     * @return int
     */
    public int getCeiling() {
        return ceiling;
    }

    /**
     * Define o limite alto do Threshold
     * 
     * @param ceiling 
     */
    public void setCeiling(int ceiling) {
        this.ceiling = ceiling;
    }
    
}
