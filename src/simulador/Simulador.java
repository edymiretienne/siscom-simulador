package simulador;

public class Simulador {
	
	String estimador; //Protocolos a serem rodados
	int numInicialTags = 0; //Quantidade inicial de etiquetas
	int passoIncremento = 0; //Passo de	incremento da quantidade de etiquetas
	int maxTags = 0; //Quantidade máxima de etiquetas
	int numRepeticoes = 0; //Número repetições para cada quantidade de etiquetas
	int tamQuadroInicial = 0; //Tamanho do quadro inicial
	boolean potDois = false; //Usar ou não tamanho de quadro limitado a uma potência de 2
	
	/*Variáveis para plotar o gráfico 
	 * Tempo médio de Execução
	 */
	public static int totalSlots = 0; //Total de Slots
	public static int totalSlotsVazios = 0; //Total de Slots Vazios
	public static int totalSlotsColisao = 0; //Total de Slots em colisão
	public static int comandos  = 0; //Total de Comandos enviados pelo leitor para as etiquetas
	
	public static void Simulacao(String estimador, int numIniTags, int passo, int maxTags, int numRep, int tamQuadro, boolean poten){
		
		Reader leitor = new Reader(tamQuadro, numIniTags, estimador);
		System.out.println("...........Começando a simulação...........");
		//for (int i = 0; i < numRep ; i++){
//			leitor.Broadcast();
	//}
		leitor.Identify();
		//Aqui, depois da execução, eu acho a média das variaveis baseado no num de repeticoes
		System.out.println("Número total de slots utilizados - " + totalSlots);
		System.out.println("Número total de slots vazios - " + totalSlotsVazios);
		System.out.println("Número total de colisões - " + totalSlotsColisao);
		System.out.println("Número total de comandos dados pelo leitor - " + comandos);
	}
	
	public static void main(String[] args) {
		Simulador.Simulacao("Lower Bound", 5, 0, 0, 1, 4, false);
	}

}
