package negocio;

import java.util.Random;

public class InstanciaSudoku {
	private int [][] sudoku;
	
	public InstanciaSudoku () {
		sudoku = new int [9][9];
		generarInstanciaAleatoria();
	}

	private void generarInstanciaAleatoria() {
		Random random = new Random();
		for (int i = 0; i < sudoku.length; i++) {
			for(int j = 0; j < sudoku.length; j++) {
				if(random.nextBoolean()) {
					sudoku[i][j] = random.nextInt(10);
			
				}
			}
		}
	}
	
	public InstanciaSudoku(int [][] sudoku) {
		validarInstancia(sudoku);
		this.sudoku = sudoku;
	}
	
	private void validarInstancia(int[][] sudoku) {
		if(sudoku.length < 9 || sudoku.length != sudoku.length) {
			throw new RuntimeException("¡Se ha ingresado una instancia de Sudoku INVALIDA!");
		}
	}

	public int[][] obtenerInstancia(){
		return sudoku;
	}
}
