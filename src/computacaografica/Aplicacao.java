package computacaografica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author nicolas
 */
public class Aplicacao extends JFrame {

    /** Imagem carregada */
    private Imagem imagem;
    private Histograma histograma;
    private Estatisticas estatisticas;
    private PanelHistograma panelHistograma;
    private PanelVisualizacaoImagem panelImagemGrayscale;
    private PanelEstatisticas panelEstatisticas;
    private PanelReprocessamento panelReprocessamento;
    private PanelEdicao panelEdicao;
    
    public Aplicacao() {
        super("Tabalho computação gráfica");
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Cria o menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu mCarregar = new JMenu("Carregar exemplo");
        mCarregar.add(new ActionCarregarExemplo("Lena", "lena.bmp"));
        mCarregar.addSeparator();
        mCarregar.add(new ActionCarregarExemplo("Exemplo 1", "1640752.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 2", "BF4-Menu.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 3", "Western-1024x682.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 4", "beyond-two-souls1.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 5", "tumblr_m8enw53BMf1rraheuo4_1280.jpg"));
        
        JMenu mFiltros = new JMenu("Filtros");
        mFiltros.add(new ActionFiltroGauss());
        
        JMenu mTransformar = new JMenu("Transformar");

        menuBar.add(mCarregar);
        menuBar.add(mTransformar);
        menuBar.add(mFiltros);
        menuBar.add(new JMenuItem(new ActionCarregar()));
        setJMenuBar(menuBar);
        
        panelImagemGrayscale = new PanelVisualizacaoImagem();
        panelHistograma = new PanelHistograma(new Histograma());
        panelEstatisticas = new PanelEstatisticas();
        panelReprocessamento = new PanelReprocessamento();
        panelEdicao = new PanelEdicao();
        
        JTabbedPane tabs = new JTabbedPane();
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelImagemGrayscale);
        panel.add(panelEstatisticas, BorderLayout.EAST);
        
        tabs.add("Imagem original (Tons de cinza)", panel);
        tabs.add("Histograma", panelHistograma);
        tabs.add("Panel reprocessamento", panelReprocessamento);
        tabs.add("Editor", panelEdicao);
        
        getContentPane().add(tabs);
        
        
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
        
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.setVisible(true);
    }
    
    private class ActionCarregar extends AbstractAction {

        public ActionCarregar() {
            super("Carregar imagem externa...");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(Aplicacao.this);
            imagem = ImagemLoader.fromFile(chooser.getSelectedFile());
            histograma = HistogramaFactory.buildHistograma(imagem);
            estatisticas = EstatisticasFactory.calculaEstatisticasExercicios(imagem);
            panelImagemGrayscale.setImagem(imagem);
            panelHistograma.setHistograma(histograma);
            panelEstatisticas.setEstatisticas(estatisticas);
            panelReprocessamento.setEstatisticas(estatisticas);
            panelReprocessamento.setImagem(imagem);
            getContentPane().repaint();
        }
        
    }
    
    private class ActionCarregarExemplo extends AbstractAction {

        private String file;
        
        public ActionCarregarExemplo(String nome, String file) {
            super(nome);
            this.file = file;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            imagem = ImagemLoader.fromFile(Aplicacao.class.getResource("/computacaografica/samples/" + file).getFile());
            histograma = HistogramaFactory.buildHistograma(imagem);
            estatisticas = EstatisticasFactory.calculaEstatisticasExercicios(imagem);
            panelImagemGrayscale.setImagem(imagem);
            panelHistograma.setHistograma(histograma);
            panelEstatisticas.setEstatisticas(estatisticas);
            panelReprocessamento.setEstatisticas(estatisticas);
            panelReprocessamento.setImagem(imagem);
            panelEdicao.setImagem(imagem);
            getContentPane().repaint();
        }
        
    }
    
    private class ActionFiltroGauss extends AbstractAction {

        public ActionFiltroGauss() {
            super("Filtro Gaussiano");
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            FiltroGauss filtro = new FiltroGauss(3);
            filtro.aplica(panelEdicao.getImagem());
            getContentPane().repaint();
        }
        
    }
    
    
    
}
