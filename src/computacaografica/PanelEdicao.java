/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package computacaografica;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
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
    
}
