/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author nicolas
 */
public class PanelEstatisticas extends JPanel {
    
    /** Estatísticas */
    private Estatisticas estatisticas;
    private JPanel p;

    public PanelEstatisticas() {
        super();
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        estatisticas = new Estatisticas();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        p = new JPanel(new GridLayout(0, 2, 5, 2));
        p.setPreferredSize(new Dimension(500, 150));
        p.setMaximumSize(new Dimension(500, 150));
        add(p);
        add(Box.createVerticalGlue());
    }
    
    private void adicionaLinha(String legenda, double valor) {
        p.add(new JLabel(legenda, JLabel.RIGHT));
        DecimalFormat nf = new DecimalFormat("#,###.00");
        JTextField field = new JTextField(nf.format(valor));
        field.setEnabled(false);
        p.add(field);
    }

    public Estatisticas getEstatisticas() {
        return estatisticas;
    }

    public void setEstatisticas(Estatisticas estatisticas) {
        this.estatisticas = estatisticas;
        update();
    }
    
    private void update() {
        p.removeAll();
        adicionaLinha("Média acima da diagonal superior", estatisticas.getMedia());
        adicionaLinha("Mediana inferior a diagonal principal", estatisticas.getMediana());
        adicionaLinha("Moda da diagonal secundária", estatisticas.getModa());
        adicionaLinha("Variância de toda a imagem", estatisticas.getVariancia());
        adicionaLinha("Valores < 100 acima da diagonal secundária", estatisticas.getQtdPixels1());
        adicionaLinha("Valores > 150 abaixo da diagonal secundária", estatisticas.getQtdPixels2());
    }

}



