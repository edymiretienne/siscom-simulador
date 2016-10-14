package simulador;

public class Simulador {
	
	String estimador; //Protocolos a serem rodados
	int numInicialTags = 0; //Quantidade inicial de etiquetas
	int passoIncremento = 0; //Passo de	incremento da quantidade de etiquetas
	int maxTags = 0; //Quantidade máxima de etiquetas
	int numRepeticoes = 0; //Número repetições para cada quantidade de etiquetas
	int tamQuadroInicial = 0; //Tamanho do quadro inicial
	boolean potDois = false; //Usar ou não tamanho de quadro limitado a uma potência de 2
	
	//Variáveis para plotar o gráfico
	/* Total de Slots
	 * Total de Slots Vazios
	 * Total de Slots em colisão
	 * Total de Comandos enviados pelo leitor para as etiquetas
	 * Tempo médio de Execução
	 */
	public static int totalSlots = 0;
	public static int totalSlotsVazios = 0;
	public static int totalSlotsColisao = 0;
	public static int comandos  = 0;
	
	public void Simulacao(String estimador, int numIniTags, int passo, int maxTags, int numRep, int tamQuadro, boolean poten){
		
		Reader leitor = new Reader(tamQuadro, numIniTags, estimador);
		//for (int i = 0; i < numRep ; i++){
//			leitor.Broadcast();
	//}
		
		//Aqui, depois da execução, eu acho a média das variaveis baseado no num de repeticoes
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		
		
	}

}
