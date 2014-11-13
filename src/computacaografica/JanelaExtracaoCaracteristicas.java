package computacaografica;

import java.awt.BorderLayout;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JList;

/**
 *
 * @author jouwee
 */
public class JanelaExtracaoCaracteristicas extends JDialog {
    
    private Imagem imagem;
    private ExtratorCaracteristicas extrator;
    private PanelVisualizacaoImagem visualizacao;
    private JList fLista;

    public JanelaExtracaoCaracteristicas(Imagem imagem) {
        super((Dialog)null, "Extração de características");
        setSize(800, 600);
        setLocationRelativeTo(null);
        this.imagem = imagem;
        
        extrator = new ExtratorCaracteristicas(imagem);
        extrator.executa();
        
        fLista = new JList<>(extrator.getObjetos().toArray());
        
        
        visualizacao = new PanelVisualizacaoImagem();
        visualizacao.setImagem(imagem);
        
//        fLista = new 
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(visualizacao);
        getContentPane().add(fLista, BorderLayout.WEST);
        
    }
    
    
    
}
