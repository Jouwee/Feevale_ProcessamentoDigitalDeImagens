/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

/**
 *
 * @author nicolas
 */
public class Estatisticas {

    /** média das tonalidades de cinza da imagem da parte acima da diagonal superior */
    private float media;
    /** mediana das tonalidades de cinza da imagem da parte inferior a diagonal principal */
    private int mediana;
    /** moda das tonalidades de cinza da imagem da diagonal secundária */
    private int moda;
    /** variância das tonalidades de cinza da imagem de toda a imagem */
    private float variancia;
    /** quantos pixels com tonalidade menor do que 100 tem na imagem acima da diagonal secundária */
    private int qtdPixels1;
    /** quantos pixels com tonalidade superior a 150 tem na imagem abaixo da diagonal secundária */
    private int qtdPixels2;

    public Estatisticas() {
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public int getMediana() {
        return mediana;
    }

    public void setMediana(int mediana) {
        this.mediana = mediana;
    }

    public int getModa() {
        return moda;
    }

    public void setModa(int moda) {
        this.moda = moda;
    }

    public float getVariancia() {
        return variancia;
    }

    public void setVariancia(float variancia) {
        this.variancia = variancia;
    }

    public int getQtdPixels1() {
        return qtdPixels1;
    }

    public void setQtdPixels1(int qtdPixels1) {
        this.qtdPixels1 = qtdPixels1;
    }

    public int getQtdPixels2() {
        return qtdPixels2;
    }

    public void setQtdPixels2(int qtdPixels2) {
        this.qtdPixels2 = qtdPixels2;
    }

    
    
}
