/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica;

/**
 *
 * @author jouwee
 */
public class MatrizTransformacaoFactory {
    
    public static double[][] translacao(double x, double y) {
        return new double[][]
            {{1, 0, x},
             {0, 1, y},
             {0, 0, 1},
            };
    }
    
    public static double[][] escala(double x, double y) {
        return new double[][]
            {{x, 0, 0},
             {0, y, 0},
             {0, 0, 1},
            };
    }
    
    public static double[][] rotacao(double a) {
        return new double[][]
            {{Math.cos(Math.toRadians(a)), Math.sin(Math.toRadians(a)), 0},
             {-Math.sin(Math.toRadians(a)), Math.cos(Math.toRadians(a)), 0},
             {0, 0, 1},
            };
    }
    
}
