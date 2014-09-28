/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

import computacaografica.iterators.FullImageIterator;
import computacaografica.iterators.ImageIteratorCallback;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author 0118194
 */
public class PanelReprocessamento extends JPanel {
    
    /** Imagem */
    private Imagem imagem;
    /** Estatisticas */
    private Estatisticas estatisticas;
    private Processador processador;
    private PanelVisualizacaoImagem pImagem;
    
    public PanelReprocessamento() {
        setLayout(new BorderLayout());
        pImagem = new PanelVisualizacaoImagem();
        add(pImagem);
        
        final JList listaProcessadores = new JList(new Object[] {new Processador1(), new Processador2(), new Processador3(), new Processador4(), new Processador5()});
        listaProcessadores.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaProcessadores.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                processador = (Processador) listaProcessadores.getSelectedValue();
                update();
            }
        });
        listaProcessadores.setSelectedIndex(0);
        processador = (Processador) listaProcessadores.getSelectedValue();
        add(listaProcessadores, BorderLayout.WEST);
        
    }
    
    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
        update();
    }

    public Estatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
        update();
    }
    
    private void update() {
        if(imagem == null) {
            return;
        }
        pImagem.setImagem(processador.processa(imagem));
        repaint();
    }
    
    private abstract class Processador {
        
        private String nome;

        public Processador(String nome) {
            this.nome = nome;
        }
        
        public Imagem processa(final Imagem imagem) {
            init(imagem);
            FullImageIterator iterator = new FullImageIterator();
            final Imagem newImagem = new Imagem(imagem.getWidth(), imagem.getHeight());
            iterator.iterate(imagem, new ImageIteratorCallback() {
                @Override
                public void callback(int x, int y) {
                    newImagem.setPixel(x, y, processaPixel(imagem.getPixel(x, y)));
                }
            });
            return newImagem;
        }
        
        public void init(Imagem imagem) {
            
        }
        
        public abstract int processaPixel(int v);

        @Override
        public String toString() {
            return nome;
        }
        
        
        
    }
    
    private class Processador1 extends Processador {

        public Processador1() {
            super("Valores maiores ou iguais a média calculada anteriormente recebem branco");
        }

        @Override
        public int processaPixel(int v) {
            if(v >= estatisticas.getMedia()) {
                return 255;
            }
            return v;
        }
    }
    
    private class Processador2 extends Processador {

        public Processador2() {
            super("Valores maiores ou iguais a moda calculada anteriormente recebem 200.");
        }

        @Override
        public int processaPixel(int v) {
            if(v >= estatisticas.getModa()) {
                return 200;
            }
            return v;
        }
    }

    private class Processador3 extends Processador {

        public Processador3() {
            super("Valores maiores ou iguais a mediana calculada anteriormente recebem 220.");
        }

        @Override
        public int processaPixel(int v) {
            if(v >= estatisticas.getMediana()) {
                return 220;
            }
            return v;
        }
    }

    private class Processador4 extends Processador {

        float media;
        
        public Processador4() {
            super("Valores menores que a média de toda a imagem recebem 100.");
        }

        @Override
        public void init(Imagem imagem) {
            media = EstatisticasFactory.calculaMedia(imagem, new FullImageIterator());
        }

        @Override
        public int processaPixel(int v) {
            
            if(v <= media) {
                return 100;
            }
            return v;
        }
    }


    private class Processador5 extends Processador {

        float mediana;
        float media;
        
        public Processador5() {
            super("Valores maiores que a mediana de toda a imagem recebem 0 e menores que a média recebem 255.");
        }

        @Override
        public void init(Imagem imagem) {
            mediana = EstatisticasFactory.calculaMediana(imagem, new FullImageIterator());
            media = EstatisticasFactory.calculaMedia(imagem, new FullImageIterator());
        }

        @Override
        public int processaPixel(int v) {
            
            if(v > mediana) {
                return 0;
            }
            if(v < media) {
                return 255;
            }
            return v;
        }
    }
}
