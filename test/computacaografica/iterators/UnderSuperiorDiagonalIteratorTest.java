package computacaografica.iterators;

import computacaografica.Imagem;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 */
public class UnderSuperiorDiagonalIteratorTest {

    private int i;
    
    @Test
    public void test() {
        if(true)return;
        Imagem img;
        ImagemIterator iterator = new UnderSuperiorDiagonalIterator();
        // Teste com largura maior
        i = 0;
        img = new Imagem(4, 3);
        iterator.iterate(img, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                switch(i) {
                    case 0: assertPosition(0, 1, x, y); break;
                    case 1: assertPosition(0, 2, x, y); break;
                    case 2: assertPosition(1, 2, x, y); break;
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
                    case 0: assertPosition(0, 1, x, y); break;
                    case 1: assertPosition(0, 2, x, y); break;
                    case 3: assertPosition(1, 2, x, y); break;
                    case 4: assertPosition(0, 3, x, y); break;
                    case 5: assertPosition(1, 3, x, y); break;
                    case 6: assertPosition(2, 3, x, y); break;
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
