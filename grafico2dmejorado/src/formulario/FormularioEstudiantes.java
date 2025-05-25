/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package formulario;

/**
 *
 * @author itsmeale_m
 */
import org.knowm.xchart.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FormularioEstudiantes extends JFrame {

    private final JTextField campoNombre;
    private final JTextField campoEdad;
    private final JTextField campoNota;
    private final JComboBox<String> comboGenero;
    private final JTable tabla;
    private final DefaultTableModel modeloTabla;
    private final JPanel panelGrafico;

    public FormularioEstudiantes() {
        setTitle("registro de notas app");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("REGISTRO DE NOTAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        titulo.setBounds(250, 10, 300, 30);
        add(titulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombre.setBounds(50, 60, 100, 25);
        add(lblNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(150, 60, 400, 25);
        add(campoNombre);

        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblEdad.setBounds(50, 100, 100, 25);
        add(lblEdad);

        campoEdad = new JTextField();
        campoEdad.setBounds(150, 100, 400, 25);
        add(campoEdad);

        JLabel lblGenero = new JLabel("Genero:");
        lblGenero.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblGenero.setBounds(50, 140, 100, 25);
        add(lblGenero);

        comboGenero = new JComboBox<>(new String[]{"...", "Masculino", "Femenino"});
        comboGenero.setBounds(150, 140, 400, 25);
        add(comboGenero);

        JLabel lblNota = new JLabel("Nota:");
        lblNota.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNota.setBounds(50, 180, 100, 25);
        add(lblNota);

        campoNota = new JTextField();
        campoNota.setBounds(150, 180, 400, 25);
        add(campoNota);

        JButton btnGuardar = new JButton("GUARDAR INFORMACION");
        btnGuardar.setBounds(150, 220, 200, 30);
        btnGuardar.setBackground(Color.white);
        btnGuardar.setForeground(Color.RED);
        add(btnGuardar);

        JButton btnGrafico = new JButton("MOSTRAR GRAFICO");
        btnGrafico.setBounds(360, 220, 200, 30);
        btnGrafico.setBackground(Color.white);
        btnGrafico.setForeground(Color.BLUE);
        add(btnGrafico);

        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Edad", "Genero", "Nota"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(30, 270, 720, 100);
        add(scrollPane);

        panelGrafico = new JPanel();
        panelGrafico.setBounds(30, 380, 720, 170);
        panelGrafico.setLayout(new BorderLayout());
        add(panelGrafico);

        btnGuardar.addActionListener(e -> guardarEstudiante());
        btnGrafico.addActionListener(e -> mostrarGrafico());

        setVisible(true);
    }

    private void guardarEstudiante() {
        String nombre = campoNombre.getText();
        String edad = campoEdad.getText();
        String genero = (String) comboGenero.getSelectedItem();
        String notaStr = campoNota.getText();

        if (nombre.isEmpty() || edad.isEmpty() || genero.equals("...") || notaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "completa los datos");
            return;
        }

        try {
            int edadNum = Integer.parseInt(edad);
            double nota = Double.parseDouble(notaStr);
            if (nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(this, "ingresa una nota correcta");
                return;
            }

            modeloTabla.addRow(new Object[]{nombre, edadNum, genero, nota});
            campoNombre.setText("");
            campoEdad.setText("");
            campoNota.setText("");
            comboGenero.setSelectedIndex(0);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "edad y nota en numeros");
        }
    }

 private void mostrarGrafico() {
    int filas = modeloTabla.getRowCount();
    if (filas == 0) {
        JOptionPane.showMessageDialog(this, "completa los datos");
        return;
    }

    CategoryChart chart = new CategoryChartBuilder()
            .width(720)
            .height(300)
            .title("notas estudiante")
            .xAxisTitle("estudiantes")
            .yAxisTitle("notas")
            .build();

    chart.getStyler().setLegendVisible(false);
    chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);

    Color[] colores = new Color[]{
        Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE,
        Color.MAGENTA, Color.CYAN, Color.PINK, Color.YELLOW,
        new Color(128, 0, 128), new Color(0, 128, 128)
    };


    for (int i = 0; i < filas; i++) {
        String nombre = (String) modeloTabla.getValueAt(i, 0);
        Double nota = Double.valueOf(modeloTabla.getValueAt(i, 3).toString());

        List<String> nombreLista = new ArrayList<>();
        nombreLista.add(nombre);

        List<Double> notaLista = new ArrayList<>();
        notaLista.add(nota);

        CategorySeries serie = chart.addSeries(nombre, nombreLista, notaLista);
        if (i < colores.length) {
            serie.setFillColor(colores[i]);
        }
    }

    panelGrafico.removeAll();
    panelGrafico.add(new XChartPanel<>(chart), BorderLayout.CENTER);
    panelGrafico.revalidate();
    panelGrafico.repaint();
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormularioEstudiantes::new);
    }
}