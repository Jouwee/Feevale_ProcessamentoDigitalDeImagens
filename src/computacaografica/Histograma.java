/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

/**
 *
 * @author nicolas
 */
public class Histograma {
    
    public int[] data;

    public Histograma() {
        data = new int[256];
        // Preenche com zeros
        for (int i = 0; i < data.length; i++) {
            data[i] = 0;
        }
        
    }
    
    public void addOne(int color) {
        data[color]++;
    }
    
    public int[] getData() {
        return data;
    }
    
    public int getMaxValue() {
        int max = 0;
        for (int i : data) {
            if(i > max) {
                max = i;
            }
        }
        return max;
    }
    
    
}
