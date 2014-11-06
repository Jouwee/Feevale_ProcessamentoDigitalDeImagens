package computacaografica.filtros;

/**
 * Filtros que possuem configuração de limiar / Threshold
 * 
 * @author Jouwee
 */
public interface FiltroLimiarizado {
    
    /**
     * Retorna o limiar do filtro
     * 
     * @return Threshold
     */
    public abstract Threshold getThreshold();
    
    /**
     * Define o limiar do filtro
     * 
     * @param threshold
     */
    public abstract void setThreshold(Threshold threshold);
    
    /**
     * Retorna os limites do limiar do filtro
     * 
     * @return ThresholdRange
     */
    public abstract ThresholdRange getThresholdRange();
    
}
