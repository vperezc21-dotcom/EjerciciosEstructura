package graficos;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraficoDinamico extends JFrame {
    private final JTextField campoCategoria;
    private final JTextField campoValor;
    private final JButton btnAgregar;
    private final Map<String, Integer> datos = new LinkedHashMap<>();
    private final JPanel panelGrafico;
    private XChartPanel<CategoryChart> chartPanel;

    public GraficoDinamico() {
        setTitle("agregar notas");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel panelEntrada = new JPanel(new GridLayout(3, 2));
        panelEntrada.add(new JLabel("materia:"));
        campoCategoria = new JTextField();
        panelEntrada.add(campoCategoria);
        panelEntrada.add(new JLabel("notas:"));
        campoValor = new JTextField();
        panelEntrada.add(campoValor);
        btnAgregar = new JButton("insertar grafico");
        panelEntrada.add(btnAgregar);

        add(panelEntrada, BorderLayout.NORTH);

        panelGrafico = new JPanel(new BorderLayout());
        add(panelGrafico, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> mostrarGrafico());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void mostrarGrafico() {
        String categoria = campoCategoria.getText();
        int valor;

        try {
            valor = Integer.parseInt(campoValor.getText());
            if (valor < 0 || valor > 100) {
                JOptionPane.showMessageDialog(this, "ingresa bien la nota");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "valor invalido");
            return;
        }

        if (categoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ingresa una materia");
            return;
        }

        datos.put(categoria, valor);

        CategoryChart chart = new CategoryChartBuilder()
                .width(400)
                .height(400)
                .title("agregar notas")
                .xAxisTitle("materia")
                .yAxisTitle("notas")
                .build();

        chart.addSeries("notas", new ArrayList<>(datos.keySet()), new ArrayList<>(datos.values()));

        if (chartPanel != null) {
            panelGrafico.remove(chartPanel);
        }

        chartPanel = new XChartPanel<>(chart);
        panelGrafico.add(chartPanel, BorderLayout.CENTER);
        panelGrafico.revalidate();
        panelGrafico.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraficoDinamico::new);
    }
}
