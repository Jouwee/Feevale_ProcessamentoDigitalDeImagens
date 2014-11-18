package computacaografica;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author nicolas
 */
public class ImagemLoader {
    
    public static Imagem fromFile(String fileName) {
        return fromFile(new File(fileName));
    }
    
    public static Imagem fromFile(File file) {
        try {
            BufferedImage im = ImageIO.read(file);
            
            Raster r = im.getData();
            
            Imagem img = new Imagem(im.getWidth(), im.getHeight());

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    
                    if(r.getNumBands() > 1) {
                        int[] v = new int[3];
                        v[0] = r.getSample(x, y, 0);
                        v[1] = r.getSample(x, y, 1);
                        v[2] = r.getSample(x, y, 2);

                        img.setPixel(x, y, toGrayScale(v));
                    } else {
                        img.setPixel(x, y, r.getSample(x, y, 0));
                    }
                    
                }
            }

            return img;
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static int toGrayScale(int[] bands) {
        return (int) Math.round(((0.3 * bands[0]) + (0.59 * bands[1]) + (0.11 * bands[2])));
    }
    
}
