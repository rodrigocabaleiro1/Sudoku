package negocio;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class InstanciaSudoku {
	private int [][] sudoku;
	
	public InstanciaSudoku () {
		sudoku = new int [9][9];
	}
	
	public InstanciaSudoku(int [][] sudoku) {
		validarInstancia(sudoku);
		this.sudoku = sudoku;
	}
	public void modificarCelda(int fila, int columna, int valor) {
		validarValor(valor);
		validarPosicion(fila, columna);
		sudoku[fila][columna] = valor;
	}
	public static InstanciaSudoku generarInstanciaAleatoria() {
		int[][] sudoku = new int[9][9];
		Random random = new Random();
		for (int i = 0; i < sudoku.length; i++) {
			for(int j = 0; j < sudoku.length; j++) {
				if(random.nextBoolean()) {
					sudoku[i][j] = random.nextInt(10);		
				}
			}
		}return new InstanciaSudoku(sudoku);
	}
	
	public static InstanciaSudoku generarInstanciaAleatoria(int cantidadCeldasPrefijadas) {
		int[][] sudoku = new int[9][9];
		Random random = new Random();
		Set<Point> celdas = new HashSet<Point>();
		while (celdas.size() < cantidadCeldasPrefijadas) {
			int n1 = random.nextInt(9);
			int n2 = random.nextInt(9);
			Point nuevaCelda = new Point(n1, n2);
			celdas.add(nuevaCelda);
		}
		
		celdas.forEach(celda -> sudoku[celda.x][celda.y] = random.nextInt(9) + 1);
		return new InstanciaSudoku(sudoku);
	}
	
	
	private void validarInstancia(int[][] sudoku) {
		if(sudoku.length < 9 || sudoku.length != sudoku[0].length) {
			throw new RuntimeException("¡Se ha ingresado una instancia de Sudoku INVALIDA!");
		}
	}
	
	private void validarValor(int valor) {
		if(valor > 9 || valor < 0) {
			throw new IllegalArgumentException("¡Valor ingresado INVALIDO! VALOR: " + valor);
		}
	}
	
	private void validarPosicion(int fila, int columna) {
		if(fila >= 9 || fila < 0 || columna >= 9 || columna < 0) {
			throw new IllegalArgumentException("¡Posicion INVALIDA! POSICION: [" + fila + ", " + columna + "] (IndicesValidos: 0 al 8)");
		}
	}

	public int[][] obtenerInstancia(){
		return sudoku;
	}
}
