import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import javax.swing.*;
import java.awt.*;

public class Graficoestatico extends JFrame {

    public Graficoestatico() {
        setTitle("venta de salchipapas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PieChart chart = new PieChartBuilder().width(600).height(400).title("Ventas:").build();

        chart.addSeries("enero", 550);
        chart.addSeries("febrero", 300);
        chart.addSeries("marzo", 100);
        chart.addSeries("abril", 200);
        chart.addSeries("mayo", 200);
   

        XChartPanel<PieChart> panel = new XChartPanel<>(chart);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Graficoestatico().setVisible(true));
    }
}