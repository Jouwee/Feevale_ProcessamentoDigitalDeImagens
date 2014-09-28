/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

import computacaografica.iterators.AboveInferiorDiagonalIterator;
import computacaografica.iterators.AboveSuperiorDiagonalIterator;
import computacaografica.iterators.FullImageIterator;
import computacaografica.iterators.ImageIteratorCallback;
import computacaografica.iterators.ImagemIterator;
import computacaografica.iterators.InferiorDiagonalIterator;
import computacaografica.iterators.UnderInferiorDiagonalIterator;
import computacaografica.iterators.UnderSuperiorDiagonalIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author nicolas
 */
public class EstatisticasFactory {
    
    /** Contador auxiliar */
    private static float count;
    /** Soma auxiliar */
    private static float soma;
    
    /**
     * Calcula as estatísticas para resolução dos exercícios
     */
    public static Estatisticas calculaEstatisticasExercicios(Imagem imagem) {
        Estatisticas estatisticas = new Estatisticas();
        estatisticas.setMedia(calculaMedia(imagem, new AboveSuperiorDiagonalIterator()));
        estatisticas.setMediana(calculaMediana(imagem, new UnderSuperiorDiagonalIterator()));
        estatisticas.setModa(calculaModa(imagem, new InferiorDiagonalIterator()));
        estatisticas.setVariancia(calculaVariancia(imagem, new FullImageIterator()));
        estatisticas.setQtdPixels1(calculaQtdPixels1(imagem, new AboveInferiorDiagonalIterator()));
        estatisticas.setQtdPixels2(calculaQtdPixels2(imagem, new UnderInferiorDiagonalIterator()));
        return estatisticas;
    }

    /**
     * Calcula a média das tonalidades de cinza
     * 
     * @param imagem
     * @param iterador 
     * @return float
     */
    public static float calculaMedia(final Imagem imagem, ImagemIterator iterador) {
        soma = 0;
        count = 0;
        iterador.iterate(imagem, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                count++;
                soma += imagem.getPixel(x, y);
            }
        });
        return soma / count;
    }

    /**
     * Calcula a mediana das tonalidades de cinza
     * 
     * @param imagem
     * @param iterador 
     * @return 
     */
    public static int calculaMediana(final Imagem imagem, ImagemIterator iterador) {
        final List<Integer> lista = new ArrayList<Integer>();
        iterador.iterate(imagem, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                lista.add(imagem.getPixel(x, y));
            }
        });
        Integer[] array = lista.toArray(new Integer[] {});
        Arrays.sort(array);
        return array[array.length / 2];
    }
    
    /**
     * Calcula a moda das tonalidades de cinza da imagem da diagonal secundária.
     * 
     * @param imagem
     * @return 
     */
    public static int calculaModa(final Imagem imagem, ImagemIterator iterador) {
        final int[] contagem = new int[256];
        for (int i = 0; i < contagem.length; i++) {
            contagem[i] = 0;
        }

        iterador.iterate(imagem, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                contagem[imagem.getPixel(x, y)]++;
            }
        });

        int maior = 0;
        int maiorI = 0;
        
        for (int i = 0; i < contagem.length; i++) {
            if( contagem[i] > maior ) {
                maior = contagem[i];
                maiorI = i;
            }
        }
        
        return maiorI;
    }
    
    /**
     * Calcula a variância das tonalidades de cinza da imagem de toda a imagem.
     * 
     * @param imagem
     * @return 
     */
    public static float calculaVariancia(final Imagem imagem, ImagemIterator iterador) {
        final float media = calculaMedia(imagem, iterador);
        soma = 0;
        
        iterador.iterate(imagem, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                float v = (imagem.getPixel(x, y) - media);
                soma += v * v;
            }
        });

        return soma / (imagem.getHeight() * imagem.getWidth());
    }
    
    /**
     * Calcula quantos pixels com tonalidade menor do que 100 tem na imagem acima da diagonal secundária.
     * 
     * @param imagem
     * @return 
     */
    public static int calculaQtdPixels1(final Imagem imagem, ImagemIterator iterador) {
        count = 0;

        iterador.iterate(imagem, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                if(imagem.getPixel(x, y) < 100) {
                    count++;
                }
            }
        });

        return (int) count;
    }
    
    /**
     * Calcula quantos pixels com tonalidade superior a 150 tem na imagem abaixo da diagonal secundária.
     * 
     * @param imagem
     * @return 
     */
    public static int calculaQtdPixels2(final Imagem imagem, ImagemIterator iterador) {
        count = 0;

        iterador.iterate(imagem, new ImageIteratorCallback() {
            @Override
            public void callback(int x, int y) {
                if(imagem.getPixel(x, y) > 150) {
                    count++;
                }
            }
        });

        return (int) count;
    }
    
}
