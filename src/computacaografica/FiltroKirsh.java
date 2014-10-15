/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

/**
 *
 * @author Nicolas
 */
public class FiltroKirsh extends FiltroOitoMascaras {

    public FiltroKirsh() {
        super(1000,
                new int[][]{
                    {5, 5, 5},
                    {-3, 0, -3},
                    {-3, -3, -3}

                },
                new int[][]{
                    {-3, 5, 5},
                    {-3, 0, 5},
                    {-3, -3, -3}

                },
                new int[][]{
                    {-3, -3, 5},
                    {-3, 0, 5},
                    {-3, -3, 5}

                },
                new int[][]{
                    {-3, -3, -3},
                    {-3, 0, 5},
                    {-3, 5, 5}

                },
                new int[][]{
                    {-3, -3, -3},
                    {-3, 0, -3},
                    {5, 5, 5}

                },
                new int[][]{
                    {-3, -3, -3},
                    {5, 0, -3},
                    {5, 5, -3}

                },
                new int[][]{
                    {5, -3, -3},
                    {5, 0, -3},
                    {5, -3, -3}
                },
                new int[][]{
                    {5, 5, -3},
                    {5, 0, -3},
                    {-3, -3, -3}

                }
        );

    }

}
