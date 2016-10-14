package simulador;
import java.util.Random;
/*
 * COMANDOS LEITOR:
 * somente comando de ajuste/reajuste de tamanho de quadro e de início de slot
 * 
 * 
 * 
 */
public class Reader {

	private int[] Frame; //Frame utilizado para tentativa de leitura
	private int FrameSize; //Tamanho do quadro informado pelo usuário
	private Random rdmGenerator; // Objeto Random para gerar pseudo-aleatorios
	private int TagsNumber; //Numero de tags a identificar
	private int[] Tags; //O indice representa o ID da tag, e o valor a escolha do slot para transmitir
	private int emptySlots; //Numero de slots vazios numa tentativa de leitura
	private int conflictedSlots; //Numero de slots onde houve conflito de tentativa de transmissao
	private int sucessSlots; //Numero de slots onde apenas uma tag trasmitiu
	private String estimador;
	private int restantes;
	/*
	 * Total de Slots
	 * Total de Slots Vazios
	 * Total de Slots em colisão
	 * Total de Comandos enviados pelo leitor para as etiquetas
	 * Tempo médio de Execução
	 */
	public int totalSlots = 0;
	public int totalEmpty = 0;
	public int totalConflicted = 0;
	public int totalSentCommands = 0;
	public double avgTimeSpent = 0;
	
	
	public Reader (int FrameSize, int TagsNumber, String estimador) {
		this.FrameSize = FrameSize;
		this.TagsNumber = TagsNumber;
		Frame = new int[this.FrameSize];
		Tags = new int[this.TagsNumber];
		rdmGenerator = new Random();
		emptySlots = 0; //Numero de slots vazios numa tentativa de leitura
		conflictedSlots = 0; //Numero de slots onde houve conflito de tentativa de transmissao
		sucessSlots = 0;
		this.estimador = estimador;
		restantes = TagsNumber;
	}
	
	private void CreateNewFrame(){ //Cria um novo Frame baseado no estimador sendo utilizado
		switch (estimador) {
			case "Schoute":
				FrameSize = (int) (conflictedSlots * 2.39) ;
				break;
			case "ILCM-SbS":
				//Implementar ILCM-SbS
				break;
			case "Lower Bound":
				FrameSize = conflictedSlots * 2 ;
				break;
			default:
				System.out.println("Informe um estimador válido.") ;
		}
	}
	
	private void Broadcast() { //Simula o momento que o leitor faz a tentativa de leitura em seu range
		for (int i = 0; i < restantes; i++){ //Tags escolhem um slot para transmitir
			Tags[i] = SlotChoose(); //Cada tag escolhe um slot
		}
		Simulador.comandos++;
	}
	
	private int SlotChoose(){ //Simula uma tag escolhendo um slot para transmitir
		int slot = 0;
		slot = rdmGenerator.nextInt(FrameSize); //Gera um random com range de 0 a tamanho do Frame
		return slot;
	}
	
	private void ReadBySlot(){ //O leitor fara a leitura slot por slot identificando Vazios, Conflito e Sucesso
		int tagResponses = 0; //Quantidade de tags que tentaram transmitir naquele slot
		for (int i = 0; i < FrameSize; i++){ //Para um determinado slot
			for(int j = 0; j < restantes; j++){ //Quais tags querem ler aqui nesse slot
				if (Tags[j] == i) tagResponses++; //Tags que tentaram falar naquele slot
			}
			if (tagResponses == 0){ //Nenhuma tag tentou falar nesse slot
				emptySlots++;
			} else if (tagResponses == 1) {
						sucessSlots++; //Apenas uma tag transmitiu nesse slot
					} else conflictedSlots++; //Conflito gerado
			Simulador.comandos++;
		}
	}
	
	public void Identify(){
		
		while (restantes > 0 ) {
			Broadcast();
			ReadBySlot();
			restantes = restantes - sucessSlots;
			Simulador.totalSlots = Simulador.totalSlots + FrameSize;
			Simulador.totalSlotsColisao = Simulador.totalSlotsColisao + conflictedSlots;
			Simulador.totalSlotsVazios = Simulador.totalSlotsVazios + emptySlots;
			if (restantes > 0 ) {
				
				
				//Agora vou criar o novo quadro e tentar ler de novo
				//Então tenho que salvar os dados que preciso para o gráfico
				
				
			}
		}
		
		
	}
	
	
	
}
