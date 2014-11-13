package computacaografica;

/**
 *
 * @author jouwee
 */
public class ObjetoImagem {
    
    private int cor;
    private int area;
    private int perimetro;

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

    public int getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(int perimetro) {
        this.perimetro = perimetro;
    }

    @Override
    public String toString() {
        return "ObjetoImagem{" + "cor=" + cor + ", area=" + area + ", perimetro=" + perimetro + '}';
    }
    
}
