package computacaografica;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jouwee
 */
public class ExtratorCaracteristicas {

    private final Imagem imagem;
    private final List<ObjetoImagem> objetos;
    private int background;
    private int foreground;
    
    public ExtratorCaracteristicas(Imagem imagem) {
        this.imagem = imagem;
        this.objetos = new ArrayList<>();
        
        // TODO: Parametrizar
        background = 255;
        foreground = 0;
        
    }
    
    public void executa() {
        // Classifica os objetos
        classificaObjetos();
        // Extrai dados geométricos basicos (Área e perímetro)
        extraiGeometria();
        
        
    }

    /**
     * Classifica os objetos na imagem
     */
    public void classificaObjetos() {
        int nextColor = 1;
        for(int y = 0; y < imagem.getHeight(); y++) {
            for(int x = 0; x < imagem.getWidth(); x++) {
                // Se for cor de frente
                if(imagem.getPixel(x, y) == foreground) {
                    // Cria o objeto
                    ObjetoImagem obj = new ObjetoImagem();
                    obj.setCor(nextColor);
                    objetos.add(obj);
                    // Coloriza os pixeis vizinhos de forma recursiva
                    colorNeighbours(x, y, nextColor);
                    nextColor++;
                }
            }
        }
    }
    
    /**
     * Coloriza os vizinhos que forem da cor foreground da cor especificada de
     * forma recursiva, até colorir todo o objeto
     * 
     * @param x
     * @param y
     * @param color 
     */
    public void colorNeighbours(int x, int y, int color) {
        imagem.setPixel(x, y, color);
        if(y > 0 && imagem.getPixel(x, y - 1) == foreground) {
            colorNeighbours(x, y - 1, color);
        }
        if(y < imagem.getHeight() - 1 && imagem.getPixel(x, y + 1) == foreground) {
            colorNeighbours(x, y + 1, color);
        }
        if(x > 0 && imagem.getPixel(x - 1, y) == foreground) {
            colorNeighbours(x - 1, y, color);
        }
        if(x < imagem.getWidth() - 1 && imagem.getPixel(x + 1, y) == foreground) {
            colorNeighbours(x + 1, y, color);
        }
    }
    
    /**
     * Extrai informações geométricas básicas da imagem
     */
    public void extraiGeometria() {
        for(int y = 0; y < imagem.getHeight(); y++) {
            for(int x = 0; x < imagem.getWidth(); x++) {
                // Se não for cor de fundo
                if(imagem.getPixel(x, y) != background) {
                    ObjetoImagem objeto = getObjetoByCor(imagem.getPixel(x, y));
                    // Incrementa a área
                    objeto.setArea(objeto.getArea() + 1);
                    // Se estiver na borda da imagem
                    if(x == 0 || y == 0 || x == imagem.getWidth() - 1 || y == imagem.getHeight() - 1) {
                        objeto.addToPerimetro(new Point(x, y));
                    } else {
                        // Se algum dos vizinhos for a cor de fundo
                        if(imagem.getPixel(x - 1, y) == background || imagem.getPixel(x + 1, y) == background || imagem.getPixel(x, y - 1) == background || imagem.getPixel(x, y + 1) == background) {
                            objeto.addToPerimetro(new Point(x, y));
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Retorna o objeto à partir da cor
     * 
     * @param cor
     * @return ObjetoImagem
     */
    public ObjetoImagem getObjetoByCor(int cor) {
        for (ObjetoImagem objetoImagem : objetos) {
            if(objetoImagem.getCor() == cor) {
                return objetoImagem;
            }
        }
        return null;
    }
    
    public Imagem getImagem() {
        return imagem;
    }

    public List<ObjetoImagem> getObjetos() {
        return objetos;
    }
    
}
