package computacaografica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
        fLista.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                visualizacao.repaint();
            }
        });
        
        visualizacao = new MyPanelVisualizacao();
        visualizacao.setImagem(imagem);
        
//        fLista = new 
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(visualizacao);
        getContentPane().add(fLista, BorderLayout.WEST);
        
    }
    
    public class MyPanelVisualizacao extends PanelVisualizacaoImagem {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // Obtem o objeto selecionado
            ObjetoImagem objeto = (ObjetoImagem) fLista.getSelectedValue();
            if(objeto == null) {
                return;
            }
            // Deslocamento, pra ficar centralizado
            int dx = (getWidth() / 2) - (getImagem().getWidth() / 2);
            int dy = (getHeight() / 2) - (getImagem().getHeight() / 2);
            // Desenha o perímetro
            g.setColor(Color.red);
            for (Point point : objeto.getPerimetro()) {
                g.fillRect(point.x + dx, point.y + dy, 1, 1);
            }
        }
        
    }
    
}
