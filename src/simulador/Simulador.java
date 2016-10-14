package simulador;

public class Simulador {
	/*Variáveis para plotar o gráfico 
	 * Tempo médio de Execução
	 */
	public static boolean debug = false;
	public static int totalSlots = 0; //Total de Slots
	public static int totalSlotsVazios = 0; //Total de Slots Vazios
	public static int totalSlotsColisao = 0; //Total de Slots em colisão
	public static int comandos  = 0; //Total de Comandos enviados pelo leitor para as etiquetas
	
	public static void Simulacao(String estimador, int numIniTags, int passo, int maxTags, int numRep, int tamQuadro, boolean poten){
		
		Reader leitor = new Reader(tamQuadro, numIniTags, estimador);
		if(debug) System.out.println("...........Começando a simulação...........");
		leitor.Identify();
		//Aqui, depois da execução, eu acho a média das variaveis baseado no num de repeticoes
		if(debug) System.out.println("Número total de slots utilizados - " + totalSlots);
		if(debug) System.out.println("Número total de slots vazios - " + totalSlotsVazios);
		if(debug) System.out.println("Número total de colisões - " + totalSlotsColisao);
		if(debug) System.out.println("Número total de comandos dados pelo leitor - " + comandos);
	}
	
	public static void main(String[] args) {
		int[] lowerTotalVazios = new int[10];
		
		//PRIMEIRO GRÁFICO - TOTAL VAZIOS
		for (int w = 0; w < 10; w++){ //Dez iterações aumentando de 100 em 100
			for (int q = 0; q < 10; q++){
				Simulador.Simulacao("Lower Bound",((w+1) * 5), 0, 0, 1, 50, false);
			}
			lowerTotalVazios[w] = totalSlotsVazios/10;
			totalSlotsVazios = 0;
		}
		
		double[] lowerTotalVaziosd = new double[10];
		for (int i = 0; i < 10; i++){
			lowerTotalVaziosd[i] = lowerTotalVazios[i];
		}
		
		GeraGrafico graf = new GeraGrafico(lowerTotalVaziosd);
		graf.Render();
		
	}

}
