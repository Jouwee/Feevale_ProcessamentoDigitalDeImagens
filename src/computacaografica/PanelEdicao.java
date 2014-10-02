/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jouwee
 */
public class PanelEdicao extends JPanel {
    
    private Imagem imagem;
    private PanelVisualizacaoImagem visualizacao;

    public PanelEdicao() {
        super(new BorderLayout());
        visualizacao = new PanelVisualizacaoImagem();

        JPanel pFerramentas = new JPanel();
        pFerramentas.setLayout(new BoxLayout(pFerramentas, BoxLayout.Y_AXIS));
        pFerramentas.add(new JButton(new ActionTranslacao()));
        pFerramentas.add(new JButton(new ActionEscalaMaior()));
        pFerramentas.add(new JButton(new ActionEscalaMenor()));
        pFerramentas.add(new JButton(new ActionEspelhamentoHorizontal()));
        pFerramentas.add(new JButton(new ActionEspelhamentoVertical()));
        pFerramentas.add(new JButton(new ActionRotacao90()));
        pFerramentas.add(new JButton(new ActionRotacao180()));
        
        
        add(visualizacao);
        add(pFerramentas, BorderLayout.WEST);
        
    }
    
    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
        visualizacao.setImagem(imagem);
    }
    
    private class ActionTranslacao extends AbstractAction {

        public ActionTranslacao() {
            super("Transl.");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.translacao(5, 5));
            setImagem(t.transforma());
            repaint();
        }
        
    }

    private class ActionEscalaMaior extends AbstractAction {

        public ActionEscalaMaior() {
            super("Escala maior");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.escala(2, 2));
            setImagem(t.transform(true));
            repaint();
        }
        
    }

    private class ActionEscalaMenor extends AbstractAction {

        public ActionEscalaMenor() {
            super("Escala menor");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.escala(.5, .5));
            setImagem(t.transforma());
            repaint();
        }
        
    }

    private class ActionEspelhamentoHorizontal extends AbstractAction {

        public ActionEspelhamentoHorizontal() {
            super("Espelh. hor");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.escala(-1, 1));
            setImagem(t.transforma());
            repaint();
        }
        
    }

    private class ActionEspelhamentoVertical extends AbstractAction {

        public ActionEspelhamentoVertical() {
            super("Espelh. ver");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.escala(1, -1));
            setImagem(t.transforma());
            repaint();
        }
        
    }

    private class ActionRotacao90 extends AbstractAction {

        public ActionRotacao90() {
            super("Rotação 90");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.rotacao(90));
            setImagem(t.transforma());
            repaint();
        }
        
    }

    private class ActionRotacao180 extends AbstractAction {

        public ActionRotacao180() {
            super("Rotação 180");
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            Transformador t = new Transformador(imagem, MatrizTransformacaoFactory.rotacao(180));
            setImagem(t.transforma());
            repaint();
        }
        
    }
    
}
