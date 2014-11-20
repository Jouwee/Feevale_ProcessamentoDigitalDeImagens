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
    private List<Point> area;
    private double circularidade;
    private List<Point> perimetro;
    private List<Vertice> vertices;
    private String tipo;
    
    public ObjetoImagem() {
        perimetro = new ArrayList<>();
        vertices = new ArrayList<>();
        area = new ArrayList<>();
        tipo = "Indefinido";
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public List<Point> getArea() {
        return area;
    }

    public void addToArea(Point p) {
        area.add(p);
    }
    
    public void setArea(List<Point> area) {
        this.area = area;
    }

    public List<Point> getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(List<Point> perimetro) {
        this.perimetro = perimetro;
    }
    
    public void addToPerimetro(Point pixel) {
        perimetro.add(pixel);
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
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

    public double getCircularidade() {
        return circularidade;
    }

    public void setCircularidade(double circularidade) {
        this.circularidade = circularidade;
    }
    
    public boolean contains(Point p) {
        return area.contains(p);
    }
    
    @Override
    public String toString() {
        return "Objeto # " + cor;
    }

}
