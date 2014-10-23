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
public class FiltroAbertura extends Filtro {

    
    private FiltroDilatacao dilatacao;
    private FiltroReducao reducao;

    public FiltroAbertura() {
        super(3);
        dilatacao = new FiltroDilatacao();
        reducao = new FiltroReducao();
    }

    @Override
    public Imagem aplica(Imagem imagem) {
        return dilatacao.aplica(reducao.aplica(imagem));
    }

    @Override
    public int calcula(int[][] pixels) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
