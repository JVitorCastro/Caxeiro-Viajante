package viajante;

import java.util.ArrayList;
import java.util.Arrays;

public class CaixeiroViajante {

    private int[][] grafo; // matriz de adjacência
    private int[] visitado; // vetor de nós visitados
    private int[] caminho; // vetor com o caminho percorrido
    private int custoMinimo = Integer.MAX_VALUE; // custo mínimo inicial

    // receber uma matriz 2x2
    public CaixeiroViajante(int[][] grafo) {
        this.grafo = grafo;
        this.visitado = new int[grafo.length]; // instancia um vetor com o tamanho do grafo
        this.caminho = new int[grafo.length]; // instancia um vetor para armazenar o caminho percorrido
        Arrays.fill(this.visitado, 0); // preenche o vetor com 0
        Arrays.fill(this.caminho, -1); // preenche o vetor com -1
    }

    public void buscaEmProfundidade(int noAtual, int noInicial, int custo, int profundidade) {
    	// verifica se o algoritmo está no nó inicial e se já visitou todos os nós
    	// se for verdadeiro atualiza a solução final
        if (profundidade == grafo.length && noAtual == noInicial) {
            if (custo < custoMinimo) { // o custo atual é menor do que o custo armazenado anteriormente?
                custoMinimo = custo;
                caminho = visitado.clone(); // cria uma cópia dos nós visitados em "caminho"
                							// caminho = visitado faria com que ambos apontassem para o mesmo endereço
                							// lembre-se: vetores não funcionam como variáveis comuns - se usa "ponteiros"
            }
            return; // encerra o método e retorna o custo mínimo
        }
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[noAtual][i] != 0 && visitado[i] == 0 && i+1 != noInicial) { // verificação de adjacência e visita
                visitado[i] = profundidade + 1; // marcando o nó atual como visitado
                buscaEmProfundidade(i, noInicial, custo + grafo[noAtual][i], profundidade + 1); // chamada recursiva
                visitado[i] = 0; // se chegou aqui significa que percorreu todos os nós e não achou nenhum livre
                				 // então desmarca pois não foi visitado
                				 // e reinicia a contagem anterior agora com i+1, ou seja
                				 // retorna a cidade anterior e faz outra escolha de caminho
            }
        }
    }

    public int getCustoMinimo() {
        return custoMinimo;
    }

    public int[] getCaminho() {
        return caminho;
    }

    public static void main(String[] args) {
    	
    	// lembre-se: uma matriz nada mais é do que um vetor que armazena outros vetores
    	// essencialmente uma lista de vetores
    	// portanto grafo.lenght = número de linhas da matriz
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
        			{ 0, 10, 15, 20 }, 
        			{ 10, 0, 35, 25 }, 
        			{ 15, 35, 0, 30 },
        			{ 20, 25, 30, 0 }
    			}; // grafo de exemplo
        
        
        
        CaixeiroViajante caixeiro = new CaixeiroViajante(grafo);
        caixeiro.buscaEmProfundidade(0, 0, 0, 0); // inicia a busca em profundidade a partir do nó 0
        System.out.println("Custo mínimo: " + caixeiro.getCustoMinimo());
        System.out.println("Caminho percorrido: " + Arrays.toString(caixeiro.getCaminho()));
        

    }
}
