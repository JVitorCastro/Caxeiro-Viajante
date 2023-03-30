package viajante;

import java.util.Arrays;
import java.util.ArrayList;

public class CaixeiroViajante2 {
	private int[][] grafo; // matriz de adjacência
    private ArrayList<Integer> visitados; // Array com o caminho percorrido
    private ArrayList<Integer> caminhoFinal; // Array com o caminho percorrido
    private int custoMinimo = Integer.MAX_VALUE; // custo mínimo inicial

    // receber uma matriz 2x2
    public CaixeiroViajante2(int[][] grafo) {
        this.grafo = grafo;
        this.visitados = new ArrayList<Integer>(); // instancia um vetor para armazenar o caminho percorrido
        this.caminhoFinal = new ArrayList<Integer>(); // instancia um vetor para armazenar o caminho percorrido
    }

    public void buscaEmProfundidade(int noAtual, int noInicial, int custo) {
    	if (visitados.size() == 0){visitados.add(noInicial);} // Adiciona a cidade de origem ao caminho percorrido
    	
        if (visitados.size() == grafo.length) { // Verifica se já visitou todas as cidades
        	custo = custo + grafo[noAtual][noInicial]; // Adiciona o custo de retorno a cidade de origem
            if (custo < custoMinimo) { // Verifica se o custo atual é menor do que o custo do caminho anterior
                custoMinimo = custo; // Armazena o novo custo
                caminhoFinal.clear(); // Limpa o caminho percorrido anteriormente
            	caminhoFinal.addAll(visitados); // Armazena o novo caminho percorrido
            	caminhoFinal.add(noInicial); // Adiciona o retorno a cidade de origem
            }
            return;
        }
        for (int i = 0; i < grafo.length; i++) { // Percorre todo o grafo
            if ((grafo[noAtual][i] != 0 && !(visitados.contains(i)))) { // Verifica se a próxima está disponível
                visitados.add(i);// Marca a cidade como visitada
                buscaEmProfundidade(i, noInicial, custo + grafo[noAtual][i]); // Recursivamente refaz os passos agora a partir da próxima cidade
                visitados.remove(visitados.size()-1); // Desmarca a cidade como visitada para fazer outros caminhos possíveis
            }

        }
    }

    public int getCustoMinimo() {
        return custoMinimo;
    }
    public ArrayList<Integer> getCaminhoFinal() {
    	return this.caminhoFinal;
    }
    public ArrayList<Character> converteCidades(ArrayList<Integer> lista) { // Método para converter os números das cidades em letras
		ArrayList<Character> novaLista = new ArrayList<Character>();
        
        for (int i = 0; i < lista.size(); i++) {
            int inteiro = lista.get(i);
            char letra = (char) (inteiro + 'A');
            novaLista.add(letra);
        }
        
        return novaLista;
    }



	public static void main(String[] args) {
		int matriz [] [] = new int [3][3];
		
		matriz [0][0] = 0; // Cidade A -> A
		matriz [0][1] = 5; // Cidade A -> B
		matriz [0][2] = 7; // Cidade A -> C
		matriz [1][0] = 5; // Cidade B -> A 
		matriz [1][1] = 0; // Cidade B -> B
		matriz [1][2] = 8; // Cidade B -> C
		matriz [2][0] = 7; // Cidade C -> A
		matriz [2][1] = 8; // Cidade C -> B
		matriz [2][2] = 0; // Cidade C -> C
		
        int[][] grafo = {
        			{ 0, 10, 15, 20 }, // A
        			{ 10, 0, 35, 25 }, // B
        			{ 15, 35, 0, 30 }, // C
        			{ 20, 25, 30, 0 }  // D
    			}; // grafo de exemplo
        
        
        
        CaixeiroViajante2 caixeiro = new CaixeiroViajante2(grafo);
        caixeiro.buscaEmProfundidade(0, 0, 0); // inicia a busca em profundidade a partir do nó 0        
        System.out.println("Custo mínimo: " + caixeiro.getCustoMinimo());
        System.out.println("Caminho percorrido: " + caixeiro.converteCidades(caixeiro.getCaminhoFinal()));

	}

}
