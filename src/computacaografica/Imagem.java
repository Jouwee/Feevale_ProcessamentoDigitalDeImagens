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
        this(width, height, false);
    }

    /**
     * Cria uma imagem vazia
     *
     * @param width
     * @param height
     * @param alpha
     */
    public Imagem(int width, int height, boolean alpha) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;
        if(!alpha) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    setPixel(x, y, 0);
                }
            }
        }

    }

    /**
     * Cria uma imagem vazia
     *
     * @param image
     */
    public Imagem(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void setPixel(int x, int y, int v) {
        setPixel(x, y, v, 255);
    }

    public int getPixel(int x, int y) {
        return image.getRGB(x, y) & 0xFF;
    }

    public void setPixel(int x, int y, int v, int alpha) {
        Color c = new Color(v, v, v, alpha);
        image.setRGB(x, y, c.getRGB());
    }
    public void setPixel(int x, int y, Color color) {
        image.setRGB(x, y, color.getRGB());
    }

    public int getPixelAlpha(int x, int y) {
        return image.getRGB(x, y) & 0xFF000000;
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

    public Imagem clone() {
        Imagem clone = new Imagem(width, height);
        for(int y = 0; y < getHeight(); y++) {
            for(int x = 0; x < getWidth(); x++) {
                clone.setPixel(x, y, getPixel(x, y));
            }
        }
        return clone;
    }

}
