package computacaografica;

import computacaografica.filtros.FiltroRoberts;
import computacaografica.filtros.FiltroSobel;
import computacaografica.filtros.FiltroGauss;
import computacaografica.filtros.FiltroKirsh;
import computacaografica.filtros.FiltroRobinson;
import computacaografica.filtros.FiltroAbertura;
import computacaografica.filtros.FiltroAfinamento;
import computacaografica.filtros.FiltroDilatacao;
import computacaografica.filtros.FiltroFechamento;
import computacaografica.filtros.FiltroReducao;
import computacaografica.filtros.GenericFiltro;
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
    /** Panel de configuração de filtros */
    private JPanel panelConfigFiltro;
    private final PanelHistograma panelHistograma;
    private final PanelEdicao panelEdicao;
    private final PanelEstatisticas panelEstatisticas;
    private final PanelReprocessamento panelReprocessamento;
    
    public Aplicacao() {
        super("Tabalho computação gráfica");
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Cria o menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu mArquivo = new JMenu("Arquivo");
        JMenu mCarregar = new JMenu("Abrir exemplo");
        mCarregar.add(new ActionCarregarExemplo("Lena", "lena.bmp"));
        mCarregar.addSeparator();
        mCarregar.add(new ActionCarregarExemplo("Exemplo 1", "1640752.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 2", "BF4-Menu.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 3", "Western-1024x682.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 4", "beyond-two-souls1.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 5", "tumblr_m8enw53BMf1rraheuo4_1280.jpg"));
        mCarregar.add(new ActionCarregarExemplo("Exemplo 6", "teste_1.png"));
        
        mArquivo.add(new JMenuItem(new ActionAbrir()));
        mArquivo.add(mCarregar);
        
        JMenu mFiltros = new JMenu("Filtros");
        mFiltros.add(new ActionFiltro(new FiltroGauss(3), "Desfoque de Gauss"));
        JMenu mDetecaoDeBordas = new JMenu("Detecção de bordas");
        mDetecaoDeBordas.add(new ActionFiltro(new FiltroRoberts(), "Método de Roberts"));
        mDetecaoDeBordas.add(new ActionFiltro(new FiltroSobel(), "Método de Sobel"));
        mDetecaoDeBordas.add(new ActionFiltro(new FiltroKirsh(), "Método de Kirsh"));
        mDetecaoDeBordas.add(new ActionFiltro(new FiltroRobinson(), "Método de Robinson"));
        mFiltros.add(mDetecaoDeBordas);

        JMenu mMorfologia = new JMenu("Morfologia");
        mMorfologia.add(new ActionFiltro(new FiltroDilatacao(), "Dilatação"));
        mMorfologia.add(new ActionFiltro(new FiltroReducao(), "Redução"));
        mMorfologia.add(new ActionFiltro(new FiltroAbertura(), "Abertura"));
        mMorfologia.add(new ActionFiltro(new FiltroFechamento(), "Fechamento"));
        mMorfologia.add(new ActionFiltro(new FiltroAfinamento(), "Afinamento"));
//        mMorfologia.add(new ActionFiltro(new FiltroKirsh(), "Método de Kirsh"));
//        mMorfologia.add(new ActionFiltro(new FiltroRobinson(), "Método de Robinson"));
        mFiltros.add(mMorfologia);
        
        JMenu mTransformar = new JMenu("Transformar");
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.translacao(5, 5), "Transladar"));
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.escala(2, 2), "Escala maior"));
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.escala(0.5, 0.5), "Escala menor"));
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.escala(1, -1), "Espelhamento horiz."));
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.escala(-1, 1), "Espelhamento vertical"));
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.rotacao(90), "Rotação 90o"));
        mTransformar.add(new ActionTransformacao(MatrizTransformacaoFactory.rotacao(180), "Rotação 180o"));

        JMenu mExtracaoCaracteristicas = new JMenu("Extração de cracterísticas");
        mExtracaoCaracteristicas.add(new ActionExtracaoCaracteristicas());
        
        menuBar.add(mArquivo);
        menuBar.add(mTransformar);
        menuBar.add(mFiltros);
        menuBar.add(mExtracaoCaracteristicas);
        setJMenuBar(menuBar);
        
        panelHistograma = new PanelHistograma(new Histograma());
        panelEstatisticas = new PanelEstatisticas();
        panelReprocessamento = new PanelReprocessamento();
        panelEdicao = new PanelEdicao();
        
        JTabbedPane tabs = new JTabbedPane();
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(panelEdicao);
        
        
        JPanel pEsquerdo = new JPanel(new BorderLayout());
        panelConfigFiltro = new JPanel();
        pEsquerdo.add(panelEstatisticas, BorderLayout.NORTH);
        pEsquerdo.add(panelConfigFiltro, BorderLayout.CENTER);
        
        panel.add(pEsquerdo, BorderLayout.EAST);
        
        tabs.add("Imagem", panel);
        tabs.add("Histograma", panelHistograma);
        tabs.add("Panel reprocessamento", panelReprocessamento);
        
        getContentPane().add(tabs);
        
        
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.setVisible(true);
    }
    
    private class ActionAbrir extends AbstractAction {

        public ActionAbrir() {
            super("Abrir...");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(Aplicacao.this);
            imagem = ImagemLoader.fromFile(chooser.getSelectedFile());
            histograma = HistogramaFactory.buildHistograma(imagem);
            estatisticas = EstatisticasFactory.calculaEstatisticasExercicios(imagem);
            panelHistograma.setHistograma(histograma);
            panelEstatisticas.setEstatisticas(estatisticas);
            panelReprocessamento.setEstatisticas(estatisticas);
            panelReprocessamento.setImagem(imagem);
            panelEdicao.setImagem(imagem);
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
            panelHistograma.setHistograma(histograma);
            panelEstatisticas.setEstatisticas(estatisticas);
            panelReprocessamento.setEstatisticas(estatisticas);
            panelReprocessamento.setImagem(imagem);
            panelEdicao.setImagem(imagem);
            getContentPane().repaint();
        }
        
    }
    
    /**
     * Action para execução de filtros
     */
    private class ActionFiltro extends AbstractAction {
        
        /** Filtro à aplicar */
        private GenericFiltro filtro;

        /**
         * Cria a action
         * @param filtro
         * @param string 
         */
        public ActionFiltro(GenericFiltro filtro, String string) {
            super(string);
            this.filtro = filtro;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            PanelConfiguracaoFactory.buildFiltroConfig(filtro, panelConfigFiltro, panelEdicao);
//            // Aplica o filtro
//            panelEdicao.setImagem(filtro.aplica(panelEdicao.getImagem()));
//            // Atualiza o painel
//            getContentPane().repaint();
        }
        
    }
    
    /**
     * Action para transformações
     */
    private class ActionTransformacao extends AbstractAction {

        /** Transformador */
        private final Transformador transformador;

        /**
         * Cria a action
         * 
         * @param matriz
         * @param string 
         */
        public ActionTransformacao(double[][] matriz, String string) {
            super(string);
            // Cria o transformador
            transformador = new Transformador(matriz);
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Aplica o filtro
            panelEdicao.setImagem(transformador.transforma(panelEdicao.getImagem()));
            // Atualiza o painel
            getContentPane().repaint();
        }
    }
    
    private class ActionExtracaoCaracteristicas extends AbstractAction {

        public ActionExtracaoCaracteristicas() {
            super("Extração de características");
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            JanelaExtracaoCaracteristicas janela = new JanelaExtracaoCaracteristicas(imagem);
            janela.setVisible(true);
        }   
        
    }
    
}
