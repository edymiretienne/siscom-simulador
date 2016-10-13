package simulador;
import java.util.Random;

public class Reader {

	private int[] Frame; //Frame utilizado para tentativa de leitura
	private int FrameSize; //Tamanho do quadro informado pelo usuário
	private Random rdmGenerator; // Objeto Random para gerar pseudo-aleatorios
	private int TagsNumber; //Numero de tags a identificar
	private int[] Tags; //O indice representa o ID da tag, e o valor a escolha do slot para transmitir
	private int emptySlots; //Numero de slots vazios numa tentativa de leitura
	private int conflictedSlots; //Numero de slots onde houve conflito de tentativa de transmissao
	private int sucessSlots; //Numero de slots onde apenas uma tag trasmitiu
	
	public Reader (int FrameSize, int TagsNumber) {
		this.FrameSize = FrameSize;
		this.TagsNumber = TagsNumber;
		Frame = new int[this.FrameSize];
		Tags = new int[this.TagsNumber];
		rdmGenerator = new Random();
		emptySlots = 0; //Numero de slots vazios numa tentativa de leitura
		conflictedSlots = 0; //Numero de slots onde houve conflito de tentativa de transmissao
		sucessSlots = 0;
	}
	
	private void CreateNewFrame(){ //Cria um novo Frame baseado no estimador sendo utilizado
		//FrameSize = 
	}
	
	private void Broadcast() { //Simula o momento que o leitor faz a tentativa de leitura em seu range
		for (int i = 0; i < TagsNumber; i++){ //Tags escolhem um slot para transmitir
			Tags[i] = SlotChoose(); //Cada tag escolhe um slot
		}
	}
	
	private int SlotChoose(){ //Simula uma tag escolhendo um slot para transmitir
		int slot = 0;
		slot = rdmGenerator.nextInt(FrameSize); //Gera um random com range de 0 a tamanho do Frame
		return slot;
	}
	
	private void ReadBySlot(){ //O leitor fara a leitura slot por slot identificando Vazios, Conflito e Sucesso
		int tagResponses = 0; //Quantidade de tags que tentaram transmitir naquele slot
		for (int i = 0; i < FrameSize; i++){ //Para um determinado slot
			for(int j = 0; j < TagsNumber; j++){ //Quais tags querem ler aqui nesse slot
				if (Tags[j] == i) tagResponses++; //Tags que tentaram falar naquele slot
			}
			if (tagResponses == 0){
				emptySlots++;
			} else if (tagResponses == 1) {
						sucessSlots++;
					} else conflictedSlots++;
		}	
	}
	
	
	
	
}
