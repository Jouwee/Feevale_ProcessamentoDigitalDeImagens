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
public class FiltroRoberts extends FiltroDuasMascaras {

    public FiltroRoberts() {
        super(new Threshold(45), new ThresholdRange(0, 255), new int [][]{{0, 0, 0}, {0, -1, 0}, {0, 0, 1}}, new int [][]{{0, 0, 0}, {0, 0, -1}, {0, 1, 0}});
    }
    
}
