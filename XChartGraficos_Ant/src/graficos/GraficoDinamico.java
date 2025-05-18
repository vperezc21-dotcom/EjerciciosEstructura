package graficos;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraficoDinamico extends JFrame {
    private final JTextField campoCategoria;
    private final JTextField campoValor;
    private final JButton btnAgregar;
    private final Map<String, Integer> datos = new LinkedHashMap<>();

    public GraficoDinamico() {
        setTitle("agregar notas");
        setSize(500, 120);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("materia:"));
        campoCategoria = new JTextField();
        add(campoCategoria);

        add(new JLabel("notas:"));
        campoValor = new JTextField();
        add(campoValor);

        btnAgregar = new JButton("ver grafico");
        add(btnAgregar);

        btnAgregar.addActionListener(e -> mostrarGrafico());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void mostrarGrafico() {
        String categoria = campoCategoria.getText();
        int valor;

        try {
            valor = Integer.parseInt(campoValor.getText());
            if (valor < 0 || valor > 1000) {
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

        CategoryChart chart;
        chart = new CategoryChartBuilder()
                .width(400)
                .height(400)
                .title("agregar notas")
                .xAxisTitle("materia")
                .yAxisTitle("notas")
                .build();

        chart.addSeries("notas", new ArrayList<>(datos.keySet()), new ArrayList<>(datos.values()));

        new Thread(() -> new SwingWrapper<>(chart).displayChart()).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GraficoDinamico::new);
    }
}
 