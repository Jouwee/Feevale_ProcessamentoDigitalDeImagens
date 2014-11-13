package computacaografica;

import java.util.AbstractList;
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
        classificaObjetos();
        
        System.out.println("Encontrei " + objetos.size() + " objetos");
        
        
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
                    int color = 128;
                    // Verifica se existe algum vizinho já classificado
                    int neighbours[] = getNeighboursColors(x, y);
                    if(neighbours.length > 0) {
                        if(neighbours.length > 1) {
                            color = joinColors(neighbours);
                        } else {
                            color = neighbours[0];
                        }
                    } else {
                        color = nextColor;
                        
                        ObjetoImagem obj = new ObjetoImagem();
                        obj.setCor(color);
                        objetos.add(obj);
                        
                        nextColor+= 1;
                    }
                    
                    if(color > 255) {
                        System.out.println(color);
                    }
                    
                    imagem.setPixel(x, y, color);
                }
            }
        }
    }
    
    private int[] getNeighboursColors(int x, int y) {
        int[] ret = new int[5];
        int i = 0;
        if(imagem.getPixel(x, y - 1) != foreground && imagem.getPixel(x, y - 1) != background) {
            ret[i] = imagem.getPixel(x, y - 1);
            i++;
        }
        if(imagem.getPixel(x, y + 1) != foreground && imagem.getPixel(x, y + 1) != background) {
            ret[i] = imagem.getPixel(x, y + 1);
            i++;
        }
        if(imagem.getPixel(x - 1, y) != foreground && imagem.getPixel(x - 1, y) != background) {
            ret[i] = imagem.getPixel(x - 1, y);
            i++;
        }
        if(imagem.getPixel(x + 1, y) != foreground && imagem.getPixel(x + 1, y) != background) {
            ret[i] = imagem.getPixel(x + 1, y);
            i++;
        }
        
        if(i == 0) {
            return new int[] {};
        }
        
        for (int j = 0; j < i; j++) {
            if(ret[j] == ret[j + 1]) {
                for (int k = j; k < i; k++) {
                    ret[k] = ret[k + 1];
                }
            }
        }

        
        int[] newRet = new int[i];
        for (int j = 0; j < i; j++) {
            newRet[j] = ret[j];
        }
        return newRet;
    }
    
    private int joinColors(int[] colors) {
        
        List<ObjetoImagem> read = new ArrayList<>(objetos);
        for (ObjetoImagem obj : read) {
            for (int i = 1; i < colors.length; i++) {
                if(obj.getCor() == colors[i]) {
                    objetos.remove(obj);
                    break;
                }
            }
        }
        
        
        // TODO: LENTO DEMAIS. Talvez fazer isso ser uma etapa depois?
        
        
        int base = colors[0];
        for(int y = 0; y < imagem.getHeight(); y++) {
            for(int x = 0; x < imagem.getWidth(); x++) {
                
                int v = imagem.getPixel(x, y);
                
                if(v == foreground || v == background) {
                    continue;
                }
                
                
                for (int i = 1; i < colors.length; i++) {
                    if(v == colors[i]) {
                        imagem.setPixel(x, y, base);
                        break;
                    }
                }
            }
        }
        return base;
    }
    
    public Imagem getImagem() {
        return imagem;
    }

    public List<ObjetoImagem> getObjetos() {
        return objetos;
    }
    
}
