package simulador;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class GeraGrafico {

	static String tipoY = "Slots Vazios";
	static double[] xData = new double[] { 100.0, 200.0, 300.0, 400.0, 500.0, 600.0, 700.0, 800.0, 900.0, 1000.0 };
	static double[] lower = new double[10];
	static double[] lee = new double[] {0.0, 12.0, 28.0, 30.0, 53.0, 60.0, 88.0, 96.0, 110.0, 125.0};
	static double[][] yData = new double[][] {lower, lee};
	
	//metodo Render();
	public GeraGrafico (double[] lower) {
		this.lower = lower;
	}

	static void Render(){

		String[] names = new String[] {"Lower","Lee"};
		// Create Chart
		XYChart chart = QuickChart.getChart("Desempenho dos Estimadores", "Número de Slots", tipoY, names, xData, yData);
		chart.setWidth(700);
		chart.setHeight(400);
		// Show it
		new SwingWrapper(chart).displayChart();
	}

}