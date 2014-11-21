package computacaografica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
    private JTextField fId;
    private JTextField fArea;
    private JTextField fCircularidade;
    private JTextField fPerimetro;
    private JTextField fVertices;
    private JTextField fTipo;

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
                ObjetoImagem selected = (ObjetoImagem) fLista.getSelectedValue();
                visualizacao.repaint();
                fId.setText(String.valueOf(selected.getCor()));
                fArea.setText(selected.getArea().size() + " px");
                DecimalFormat f = new DecimalFormat();
                fCircularidade.setText(f.format(selected.getCircularidade()));
                fPerimetro.setText(selected.getPerimetro().size() + " px");
                fVertices.setText(String.valueOf(selected.getVertices().size()));
                fTipo.setText(selected.getTipo());
            }
        });

        visualizacao = new MyPanelVisualizacao();
        visualizacao.setImagem(imagem);


        JPanel pInformacoes = new JPanel(null);
        pInformacoes.setBorder(BorderFactory.createTitledBorder("Informações"));
        pInformacoes.setPreferredSize(new Dimension(800, 110));

        JLabel l1 = new JLabel("ID", JLabel.RIGHT);
        l1.setBounds(0, 20, 150, 25);
        pInformacoes.add(l1);
        fId = new JTextField();
        fId.setEditable(false);
        fId.setBounds(160, 20, 50, 25);
        pInformacoes.add(fId);

        JLabel l2 = new JLabel("Perímetro", JLabel.RIGHT);
        l2.setBounds(0, 45, 150, 25);
        pInformacoes.add(l2);
        fPerimetro = new JTextField();
        fPerimetro.setEditable(false);
        fPerimetro.setBounds(160, 45, 100, 25);
        pInformacoes.add(fPerimetro);

        JLabel l3 = new JLabel("Área", JLabel.RIGHT);
        l3.setBounds(260, 45, 50, 25);
        pInformacoes.add(l3);
        fArea = new JTextField();
        fArea.setEditable(false);
        fArea.setBounds(320, 45, 100, 25);
        pInformacoes.add(fArea);

        JLabel l4 = new JLabel("Circularidade", JLabel.RIGHT);
        l4.setBounds(420, 45, 100, 25);
        pInformacoes.add(l4);
        fCircularidade = new JTextField();
        fCircularidade.setEditable(false);
        fCircularidade.setBounds(530, 45, 90, 25);
        pInformacoes.add(fCircularidade);

        JLabel l5 = new JLabel("Tipo de objeto", JLabel.RIGHT);
        l5.setBounds(0, 70, 150, 25);
        pInformacoes.add(l5);
        fTipo = new JTextField();
        fTipo.setEditable(false);
        fTipo.setBounds(160, 70, 200, 25);
        pInformacoes.add(fTipo);

        JLabel l6 = new JLabel("No vértices", JLabel.RIGHT);
        l6.setBounds(310, 70, 150, 25);
        pInformacoes.add(l6);
        fVertices = new JTextField();
        fVertices.setEditable(false);
        fVertices.setBounds(470, 70, 150, 25);
        pInformacoes.add(fVertices);

        JPanel pPri = new JPanel(new BorderLayout(5, 5));
        pPri.add(visualizacao);
        JScrollPane scroll = new JScrollPane(fLista);
        scroll.setPreferredSize(new Dimension(150, 600));
        pPri.add(scroll, BorderLayout.WEST);
        pPri.add(pInformacoes, BorderLayout.SOUTH);

        pPri.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pPri);

    }

    public class MyPanelVisualizacao extends PanelVisualizacaoImagem implements MouseListener {

        public MyPanelVisualizacao() {
            super();
            addMouseListener(this);
        }

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
                g.fillRect(point.x + dx - 1, point.y + dy - 1, 3, 3);
            }
            // Desenha o perímetro
            g.setColor(Color.blue);
            for (Vertice vertice : objeto.getVertices()) {
                g.fillRect(vertice.getX() + dx - 2, vertice.getY() + dy - 2, 5, 5);
            }
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            // Deslocamento, pra ficar centralizado
            int dx = (getWidth() / 2) - (getImagem().getWidth() / 2);
            int dy = (getHeight() / 2) - (getImagem().getHeight() / 2);
            Point p = me.getPoint();
            p.x -= dx;
            p.y -= dy;
            for (ObjetoImagem objeto : extrator.getObjetos()) {
                if(objeto.contains(p)) {
                    fLista.setSelectedValue(objeto, true);
                    break;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }



    }

}
