/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package computacaografica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 *
 * @author nicolas
 */
public class PanelHistograma extends JPanel {
    
    private static final Color COLOR_BARS = new Color(0x7CB5EC);
    private static final Color COLOR_RULER = new Color(0xC0C0C0);
    private static final Color COLOR_AXIS = new Color(0x606060);
    
    private static final Font FONT = new Font("Courier", Font.PLAIN, 10);
    
    private static final Insets INSETS = new Insets(15, 15, 15, 15);
    private static final Insets CHART_INSETS = new Insets(0, 30, 15, 0);
    
    private Histograma histograma;

    public PanelHistograma(Histograma histograma) {
        super();
        this.histograma = histograma;
        setBackground(Color.WHITE);
    }

    public Histograma getHistograma() {
        return histograma;
    }

    public void setHistograma(Histograma histograma) {
        this.histograma = histograma;
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(histograma == null) {
            return;
        }
        // Valor maximo do histograma
        int max = histograma.getMaxValue();
        Graphics2D g2d = (Graphics2D) g.create();
        // Tamanho do histograma sem as borads
        Dimension histoSize = getSize();
        histoSize.width -= INSETS.left + INSETS.right;
        histoSize.height -= INSETS.top + INSETS.bottom;
        // Tamanho do gr[afico sem as borads
        Dimension chartSize = histoSize.getSize();
        chartSize.width -= CHART_INSETS.right;
        chartSize.height -= CHART_INSETS.top + CHART_INSETS.bottom;
        // Escala
        float scaleY = (float)chartSize.height / (float)max;
        float scaleX = (float)chartSize.width / (float)256;
        // Posicao do grafico
        g.setFont(FONT);
        // Desenha a grade
        for(int i = 0; i < 6; i++) {
            int x1 = CHART_INSETS.left;
            int x2 = CHART_INSETS.left + chartSize.width;
            int y = chartSize.height - (int) ((float)chartSize.height / 6f * (float)i) + CHART_INSETS.top;
            g2d.setColor(COLOR_RULER);
            g2d.drawLine(x1, y, x2, y);
        }
    
        int gap = 2;
        
        g2d.setColor(COLOR_BARS);
        int[] data = histograma.getData();
        for (int i = 0; i < data.length; i++) {
            int width = (int)scaleX - gap;
            int height = (int) (data[i] * scaleY);
            int x = i * (width + gap) + CHART_INSETS.left;
            int y = chartSize.height - height - 15;
            g2d.fillRect(x, y, width, height);
            
            if( i % 15 == 0) {
                g2d.setColor(COLOR_RULER);
                g2d.drawLine(x, CHART_INSETS.top + chartSize.height - 10, x, CHART_INSETS.top + chartSize.height - 5);
                g2d.setColor(COLOR_AXIS);
                g2d.drawString(String.valueOf(i), x, CHART_INSETS.top + chartSize.height);
                g2d.setColor(COLOR_BARS);
            }
            
        }

        g2d.dispose();
    }
    
}

