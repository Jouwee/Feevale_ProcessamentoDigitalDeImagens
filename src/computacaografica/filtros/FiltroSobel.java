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
public class FiltroSobel extends FiltroDuasMascaras {

    public FiltroSobel() {
        super(new Threshold(200), new ThresholdRange(0, 1020), new int [][]{{1, 0, -1}, {2, 0, -2}, {1, 0, -1}}, new int [][]{{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}});
    }
    
}
