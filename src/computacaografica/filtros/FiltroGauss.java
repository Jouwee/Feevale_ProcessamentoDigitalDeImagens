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
public class FiltroGauss extends Filtro {

    public int[][] matriz = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};

    public FiltroGauss(int tamanho) {
        super(tamanho);
    }
    
    @Override
    public int calcula(int[][] pixels) {
        return (int)(((float)(pixels[0][0] * matriz[0][0])
                + (pixels[0][1] * matriz[0][1])
                + (pixels[0][2] * matriz[0][2])
                + (pixels[1][0] * matriz[1][0])
                + (pixels[1][1] * matriz[1][1])
                + (pixels[1][2] * matriz[1][2])
                + (pixels[2][0] * matriz[2][0])
                + (pixels[2][1] * matriz[2][1])
                + (pixels[2][2] * matriz[2][2])) / (float)16);
    }
    
}
