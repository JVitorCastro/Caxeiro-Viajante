package teste_1-CV;

import java.util.*;

public class CaixeiroViajante {
	private int[][] grafo;         // matriz de adjacência representando o grafo
    private boolean[] visitado;    // vetor para controle dos vértices visitados
    private int[] caminho;         // vetor para armazenar o caminho atual sendo percorrido
    private int[] melhorCaminho;   // vetor para armazenar o melhor caminho encontrado até o momento
    private int menorCusto;        // variável para armazenar o custo do melhor caminho encontrado
    private int n_vertices;        // número de vértices do grafo

    public CaixeiroViajante(int[][] grafo) {
        this.grafo = grafo;
        n_vertices = grafo.length;
        visitado = new boolean[n_vertices];
        caminho = new int[n_vertices];
        melhorCaminho = new int[n_vertices];
        menorCusto = Integer.MAX_VALUE;
        caminho[0] = 0; // a primeira cidade sempre é a 0
    }

    public void buscaProfundidade(int cidadeAtual, int custo, int profundidade) {
        visitado[cidadeAtual] = true;

        if (profundidade == n_vertices - 1) {
            // todas as cidades foram visitadas
            int custoTotal = custo + grafo[cidadeAtual][0]; // volta para a cidade inicial
            if (custoTotal < menorCusto) {
                menorCusto = custoTotal;
                System.arraycopy(caminho, 0, melhorCaminho, 0, n_vertices);
            }
        } else {
            // continua a busca a partir da última cidade adicionada
            for (int proximaCidade = 0; proximaCidade < n_vertices; proximaCidade++) {
                if (!visitado[proximaCidade]) {
                    caminho[profundidade + 1] = proximaCidade;
                    buscaProfundidade(proximaCidade, custo + grafo[cidadeAtual][proximaCidade], profundidade + 1);
                }
            }
        }

        // desfaz a marcação da cidade atual
        visitado[cidadeAtual] = false;
    }

    public void encontrarMelhorCaminho() {
        buscaProfundidade(0, 0, 0);// Chama o método de busca em profundidade a partir da cidade 0, com custo 0 e profundidade 0.

        System.out.print("Melhor caminho: "); // Imprime o melhor caminho encontrado.
        for (int cidade : melhorCaminho) {
            System.out.print(cidade + " ");
        }
        
        System.out.println("0");
        System.out.println("Custo: " + menorCusto); // Imprime o custo do melhor caminho encontrado.
     }

    public static void main(String[] args) {
        int[][] grafo = {
//cidades     0  1   2   3
      /*0*/ { 0, 10, 15, 20 },
      /*1*/ { 10, 0, 35, 25 },
      /*2*/ { 15, 35, 0, 30 },
      /*3*/ { 20, 25, 30, 0 }
        };
        CaixeiroViajante caixeiro = new CaixeiroViajante(grafo);
        caixeiro.encontrarMelhorCaminho();
    }
}

