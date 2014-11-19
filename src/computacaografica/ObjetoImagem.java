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
    private final List<Vertice> vertices;
    private String tipo;
    private boolean closed;
    
    public ObjetoImagem() {
        perimetro = new ArrayList<>();
        vertices = new ArrayList<>();
        tipo = "Indefinido";
        closed = false;
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

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void addVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    @Override
    public String toString() {
        return "ObjetoImagem{" + "cor=" + cor + ", area=" + area + ", perimetro=" + perimetro.size() + ", vertices=" + vertices.size() + ", tipo=" + tipo + ", closed=" + closed + '}';
    }

}
