package computacaografica;

import computacaografica.filtros.FiltroLimiarizado;
import computacaografica.filtros.GenericFiltro;
import computacaografica.filtros.Threshold;
import computacaografica.filtros.ThresholdRange;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Factory para o painel de configuração de filtros
 * 
 * @author Jouwee
 */
public class PanelConfiguracaoFactory {
    
    /**
     * Cria o painel para a configuração de filtros
     * 
     * @param filtro Filtro
     * @param panel Painel
     * @param panelImagem
     */
    public static void buildFiltroConfig(final GenericFiltro filtro, final JPanel panel, final PanelEdicao panelImagem) {
        panel.removeAll();
        panel.repaint();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Filtro limiarizado
        if(filtro instanceof FiltroLimiarizado) {
            FiltroLimiarizado filtroLimiarizado = (FiltroLimiarizado) filtro;
            final Threshold treshold = filtroLimiarizado.getThreshold();
            ThresholdRange range = filtroLimiarizado.getThresholdRange();
            // Cria o slider
            final JSlider slider = new JSlider(range.getFloor(), range.getCeiling(), treshold.getThreshold());
            // Adiciona o listener ao Slider
            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent ce) {
                    treshold.setThreshold(slider.getValue());
                    panelImagem.setPreview(filtro.aplica(panelImagem.getImagem()));
                }
            });
            panel.add(slider);
        }
        // Adiciona o botão de aplicar
        panel.add(new JButton(new AbstractAction("Aplicar") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                panel.repaint();
                panelImagem.setImagem(filtro.aplica(panelImagem.getImagem()));
            }
        }));
        // Adiciona o botão de cancelar
        panel.add(new JButton(new AbstractAction("Cancelar") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panel.removeAll();
                panel.repaint();
                panelImagem.setPreview(null);
            }
        }));
    }
    
}
