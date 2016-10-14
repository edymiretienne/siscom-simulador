package simulador;
import java.util.Random;
/*
 * COMANDOS LEITOR:
 * somente comando de ajuste/reajuste de tamanho de quadro e de início de slot
 */
public class Reader {
	
	private int FrameSize; //Tamanho do quadro informado pelo usuário
	private Random rdmGenerator; // Objeto Random para gerar pseudo-aleatorios
	private int TagsNumber; //Numero de tags a identificar
	private int[] Tags; //O indice representa o ID da tag, e o valor a escolha do slot para transmitir
	private int emptySlots; //Numero de slots vazios numa tentativa de leitura
	private int conflictedSlots; //Numero de slots onde houve conflito de tentativa de transmissao
	private int sucessSlots; //Numero de slots onde apenas uma tag trasmitiu
	private String estimador; //Estimador sendo usado para criar novo quadro
	private int restantes; //Numero de tags restantes para identificar
	
	public Reader (int FrameSize, int TagsNumber, String estimador) {
		this.FrameSize = FrameSize;
		this.TagsNumber = TagsNumber;
		Tags = new int[this.TagsNumber];
		rdmGenerator = new Random();
		emptySlots = 0; //Numero de slots vazios numa tentativa de leitura
		conflictedSlots = 0; //Numero de slots onde houve conflito de tentativa de transmissao
		sucessSlots = 0; 
		this.estimador = estimador;
		restantes = TagsNumber; //No começo, todas as tags ainda restam para serem identificadas
	}
	
	private void CreateNewFrame(){ //Cria um novo Frame baseado no estimador sendo utilizado
		if(Simulador.debug) System.out.println("...Criando novo quadro...");
		switch (estimador) {
			case "Schoute":
				FrameSize = (int) (conflictedSlots * 2.39) ;
				if(Simulador.debug) System.out.println("...Criando por Schoute, tamanho do frame = "+FrameSize+"...");
				break;
			case "ILCM-SbS":
				//Implementar ILCM-SbS
				break;
			case "Lower Bound":
				FrameSize = conflictedSlots * 2 ;
				if(Simulador.debug) System.out.println("...Criando por Lower Bound, tamanho do frame = "+FrameSize+"...");
				break;
			default:
				System.out.println("Informe um estimador válido.") ;
		}
	}
	
	private void Broadcast() { //Simula o momento que o leitor faz a tentativa de leitura em seu range
		if(Simulador.debug) System.out.println("...........Começando o Broadcast...........");
		for (int i = 0; i < restantes; i++){ //Tags escolhem um slot para transmitir
			Tags[i] = SlotChoose(); //Cada tag escolhe um slot
			if(Simulador.debug) System.out.println("....Tag: "+i+" escolheu: "+Tags[i]+".....");
		}
		Simulador.comandos++;
		if(Simulador.debug) System.out.println("...Somou 1 aos comandos, comandos = "+Simulador.comandos+"...........");
	}
	
	private int SlotChoose(){ //Simula uma tag escolhendo um slot para transmitir
		int slot = 0;
		slot = rdmGenerator.nextInt(FrameSize);//Gera um random com range de 0 a tamanho do Frame
		return slot;
	}
	
	private void ReadBySlot(){ //O leitor fara a leitura slot por slot identificando Vazios, Conflito e Sucesso
		int tagResponses = 0; //Quantidade de tags que tentaram transmitir naquele slot
		if(Simulador.debug) System.out.println("...........Começando a ler por slot...........");
		for (int i = 0; i < FrameSize; i++){ //Para um determinado slot
			if(Simulador.debug) System.out.println("...No Slot "+i+"...");
			for(int j = 0; j < restantes; j++){ //Quais tags querem ler aqui nesse slot
				if(Simulador.debug) System.out.println("...Tag "+j+" valor: "+Tags[j]+"...");
				if (Tags[j] == i){
					tagResponses++; //Tags que tentaram falar naquele slot
					if(Simulador.debug) System.out.println("...Mais uma tag respondeu...");
				}
			}
			if (tagResponses == 0){ //Nenhuma tag tentou falar nesse slot
				emptySlots++;
				if(Simulador.debug) System.out.println("...Mais um slot vazio, vazio = "+emptySlots+"...");
			} else if (tagResponses == 1) {
						sucessSlots++; //Apenas uma tag transmitiu nesse slot
						if(Simulador.debug) System.out.println("...Mais um slot com sucesso, sucesso = "+sucessSlots+"...");
					} else {
						conflictedSlots++; //Conflito gerado
						if(Simulador.debug) System.out.println("...Mais uma colisão, colisão = "+conflictedSlots+"...");
					}
			Simulador.comandos++;
			if(Simulador.debug) System.out.println("...Somou 1 aos comandos, comandos = "+Simulador.comandos+"...........");
			tagResponses = 0;
		}
	}
	
	public boolean Identify(){
		boolean termino = false;
		
		while (restantes > 0 ) {
			Broadcast();
			ReadBySlot();
			if(Simulador.debug) System.out.println("...Restantes = "+restantes+"-sucessSlots = "+sucessSlots+"...");
			restantes = restantes - sucessSlots;
			if(Simulador.debug) System.out.println("...Restantes = "+restantes+"...");
			if(Simulador.debug) System.out.println("...Total de Slots = "+Simulador.totalSlots+"+TamanhoFrame = "+FrameSize+"...");
			Simulador.totalSlots = Simulador.totalSlots + FrameSize;
			if(Simulador.debug) System.out.println("...Total de Slots = "+Simulador.totalSlots+"...");
			if(Simulador.debug) System.out.println("...Total de Slots Colisão = "+Simulador.totalSlotsColisao+"+conflitos = "+conflictedSlots+"...");
			Simulador.totalSlotsColisao = Simulador.totalSlotsColisao + conflictedSlots;
			if(Simulador.debug) 	System.out.println("...Total de Slots Colisao= "+Simulador.totalSlotsColisao+"...");
			if(Simulador.debug) 	System.out.println("...Total de Slots Vazios = "+Simulador.totalSlotsVazios+"+emptyslots = "+emptySlots+"...");
			Simulador.totalSlotsVazios = Simulador.totalSlotsVazios + emptySlots;
			if(Simulador.debug) System.out.println("...Total de Slots Vazios= "+Simulador.totalSlotsVazios+"...");
			if (restantes > 0 ) {
				CreateNewFrame();		
			}
			emptySlots = 0;
			conflictedSlots = 0;
			sucessSlots = 0;
		}
		termino = true;
		return termino;
	}
}
