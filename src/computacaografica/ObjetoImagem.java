package computacaografica;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jouwee
 */
public class ObjetoImagem {
    
    private int cor;
    private int area;
    private final List<Point> perimetro;

    public ObjetoImagem() {
        this.perimetro = new ArrayList<>();
    }
    
    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public List<Point> getPerimetro() {
        return perimetro;
    }

    public void addToPerimetro(Point pixel) {
        perimetro.add(pixel);
    }

    @Override
    public String toString() {
        return "ObjetoImagem{" + "cor=" + cor + ", area=" + area + ", perimetro=" + perimetro.size() + '}';
    }
    
}
