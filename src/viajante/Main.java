package viajante;


public class Main {
	
	public static void main(String[] args) {
		
		
		
//		 A B C
//	   A 0 5 7
//	   B 3 0 0
//	   C 9 2 0

		// Linha 1 -> A
		// Linha 2 -> B
		// Linha 3 -> C
		int matriz [] [] = new int [3][3];
		
		matriz [0][0] = 0; // Cidade A -> A
		matriz [0][1] = 5; // Cidade A -> B
		matriz [0][2] = 7; // Cidade A -> C
		matriz [1][0] = 3; // Cidade B -> A 
		matriz [1][1] = 0; // Cidade B -> B
		matriz [1][2] = 0; // Cidade B -> C
		matriz [2][0] = 9; // Cidade C -> A
		matriz [2][1] = 2; // Cidade C -> B
		matriz [2][2] = 0; // Cidade C -> C
		
		busca(matriz);
		
	}
	

	public static int[] busca (int m[][]) {
		int v [] = new int [100];
		int cont = 0;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				if (m[i][j] != 0) {
					v[cont] = m[i][j];
					cont++;
				}
			}
		}
		
		
		for (int i = 0; i < v.length; i++) {
			if (v[i] != 0) {
				System.out.println(v[i]);
			}
		}
		return v;
	}

}



















