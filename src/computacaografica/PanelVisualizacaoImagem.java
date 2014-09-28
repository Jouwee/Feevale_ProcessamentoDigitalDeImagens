/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelVisualizacaoImagem extends JPanel {
    
    private Imagem imagem;

    public PanelVisualizacaoImagem() {
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(imagem == null) {
            return;
        }
        int x = getWidth() / 2 - imagem.getWidth() / 2;
        int y = getHeight() / 2 - imagem.getHeight() / 2;
        g.drawImage(imagem.getImage(), x, y, this);
    }
    
}
