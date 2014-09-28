package computacaografica;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Imagem de um canal (grayscale)
 * 
 * @author nicolas
 */
public class Imagem {
    
    /** Dados */
    private BufferedImage image;
    /** Largura */
    private int width;
    /** Altura */
    private int height;

    /**
     * Cria uma imagem vazia
     * 
     * @param width
     * @param height 
     */
    public Imagem(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.width = width;
        this.height = height;
        
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                setPixel(x, y, 130);
//            }
//            
//        }
        
    }

    /**
     * Cria uma imagem vazia
     * 
     * @param width
     * @param height 
     */
    public Imagem(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void setPixel(int x, int y, int v) {
        Color c = new Color(v, v, v);
        image.setRGB(x, y, c.getRGB());
    }

    public int getPixel(int x, int y) {
        return image.getRGB(x, y) & 0x0000FF;
    }
    
    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}
