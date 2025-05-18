package graficos;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.util.Arrays;

public class GraficoEstatico {

    public static void main(String[] args) {
        CategoryChart chart;
        chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("gastos mensuales")
                .yAxisTitle("monto")
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.addSeries("mes", Arrays.asList("comida", "transporte", "alquiler"), Arrays.asList(300, 100, 600));

        JFrame frame = new SwingWrapper<>(chart).displayChart();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
