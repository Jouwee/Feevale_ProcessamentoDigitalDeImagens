/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica.iterators;

import computacaografica.Imagem;
import junit.framework.Assert;
import org.junit.Test;

public class InferiorDiagonalIteratorTest {

    private int i;
    
    @Test
    public void test() {
        Imagem img;
        ImagemIterator iterator = new InferiorDiagonalIterator();
        // Teste com largura maior
        i = 0;
        img = new Imagem(4, 3);
        iterator.iterate(img, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                switch(i) {
                    case 0: assertPosition(0, 2, x, y); break;
                    case 1: assertPosition(1, 1, x, y); break;
                    case 2: assertPosition(2, 0, x, y); break;
                    case 3: assertPosition(3, 0, x, y); break;
                    default: Assert.fail();
                }
                i++;
            }
        });
        // Teste com altura maior
        i = 0;
        img = new Imagem(3, 4);
        iterator.iterate(img, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                switch(i) {
                    case 0: assertPosition(0, 3, x, y); break;
                    case 1: assertPosition(1, 2, x, y); break;
                    case 2: assertPosition(2, 1, x, y); break;
                    case 3: assertPosition(2, 0, x, y); break;
                    default: Assert.fail();
                }
                i++;
            }
        });
    }
    
    public void assertPosition(int xExpected, int yExpected, int xFound, int yFound) {
        Assert.assertEquals(xExpected, xFound);
        Assert.assertEquals(yExpected, yFound);
    }
    
}
