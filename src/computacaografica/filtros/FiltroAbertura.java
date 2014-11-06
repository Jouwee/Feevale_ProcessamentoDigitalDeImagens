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
public class FiltroAbertura extends GenericFiltro implements FiltroLimiarizado  {

    
    private ThresholdRange thresholdRange;
    private Threshold threshold;
    private FiltroDilatacao dilatacao;
    private FiltroReducao reducao;

    public FiltroAbertura() {
        super(3);
        threshold = new Threshold(0);
        thresholdRange = new ThresholdRange(-255, 255);
        dilatacao = new FiltroDilatacao();
        dilatacao.setThreshold(threshold);
        reducao = new FiltroReducao();
        reducao.setThreshold(threshold);
    }

    @Override
    public Imagem aplica(Imagem imagem) {
        return dilatacao.aplica(reducao.aplica(imagem));
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
