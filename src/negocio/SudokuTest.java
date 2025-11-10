package negocio;

import static org.junit.Assert.*;

import java.awt.Point;
import java.io.IOException;

import org.junit.Test;

public class SudokuTest {
	private int[][] tablero = {
			{1,2,3,4,5,6,7,8,9},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,1,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0},
			{0,0,0,0,1,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}		
	};
	
	@Test
	public void crearSudokuInstanciaTest() {
		InstanciaSudoku instancia = new InstanciaSudoku(tablero);
		Sudoku sudoku = new Sudoku(instancia);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void modificarCeldaValorInvalidoMuyBajo() throws RuntimeException{
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(1, 2, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void modificarCeldaValorInvalidoMuyAlto() throws RuntimeException{
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(1,2, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void numeroRepetidoFila() throws RuntimeException{
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(1, 1, 8);
		sudoku.modificarCelda(1, 8, 8);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void numeroRepetidoColumna() throws RuntimeException{
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(1, 1, 1);
		sudoku.modificarCelda(8, 1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void numeroRepetidoCuadrante() throws RuntimeException {
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(1, 1, 1);
		sudoku.modificarCelda(2, 2, 1);
	}
	
	@Test
	public void tableroConSolucion() {
		InstanciaSudoku instancia = new InstanciaSudoku(tablero);
		Sudoku sudoku = new Sudoku(instancia);
		assertTrue(sudoku.haySolucion());
	}
	
	@Test
	public void tableroSinSolucionPorFilaInvalida() {
		int[][] tableroInvalido = {
				{1,2,3,4,5,6,7,8,9},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				
				
				{0,0,1,0,0,0,0,0,1},
				
				
				{0,0,0,0,0,0,0,0,0}		
		};
		InstanciaSudoku instancia = new InstanciaSudoku(tableroInvalido);
		Sudoku sudoku = new Sudoku(instancia);
		assertFalse(sudoku.haySolucion());
	}
	
	@Test
	public void tableroSinSolucionPorColumnaInvalida() {
		int[][] tableroInvalido = {
				{1,2,3,4,5,6,7,8, 	9},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	0},
				{0,0,0,0,0,0,0,0, 	9}		
		};
		InstanciaSudoku instancia = new InstanciaSudoku(tableroInvalido);
		Sudoku sudoku = new Sudoku(instancia);
		assertFalse(sudoku.haySolucion());
	}
	
	@Test
	public void tableroSinSolucionPorCuadranteInvalido() {
		int[][] tableroInvalido = {
				{1,2,3,4,5,6,  		7,8,9},
				{0,0,0,0,0,0, 	 	0,0,0},
				{0,0,0,0,0,0,  		8,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0}		
		};
		InstanciaSudoku instancia = new InstanciaSudoku(tableroInvalido);
		Sudoku sudoku = new Sudoku(instancia);
		assertFalse(sudoku.haySolucion());
	}
	
	@Test
	public void tableroCompleto() {
		int[][] tableroCompleto = {
			    {5, 3, 4, 6, 7, 8, 9, 1, 2},
			    {6, 7, 2, 1, 9, 5, 3, 4, 8},
			    {1, 9, 8, 3, 4, 2, 5, 6, 7},
			    {8, 5, 9, 7, 6, 1, 4, 2, 3},
			    {4, 2, 6, 8, 5, 3, 7, 9, 1},
			    {7, 1, 3, 9, 2, 4, 8, 5, 6},
			    {9, 6, 1, 5, 3, 7, 2, 8, 4},
			    {2, 8, 7, 4, 1, 9, 6, 3, 5},
			    {3, 4, 5, 2, 8, 6, 1, 7, 9}
			};
		InstanciaSudoku instancia = new InstanciaSudoku(tableroCompleto);
		Sudoku sudoku = new Sudoku(instancia);
		assertTrue(sudoku.tableroCompleto());
	}
	
	@Test
	public void celdasModificables() {
		int[][] tableroCompleto = {
			    {5, 3, 4, 6, 7, 8, 9, 1, 2},
			    {6, 7, 2, 1, 9, 5, 3, 4, 8},
			    {1, 9, 8, 3, 4, 2, 5, 6, 7},
			    {8, 5, 9, 7, 6, 1, 4, 2, 3},
			    {4, 2, 6, 8, 5, 3, 7, 9, 1},
			    {7, 1, 3, 9, 2, 4, 8, 5, 6},
			    {9, 6, 1, 5, 3, 7, 2, 8, 4},
			    {2, 8, 7, 4, 1, 9, 6, 3, 5},
			    {3, 4, 5, 2, 8, 6, 1, 0, 0}
			};
		InstanciaSudoku instancia = new InstanciaSudoku(tableroCompleto);
		Sudoku sudoku = new Sudoku(instancia);
		for(int i = 0; i < tableroCompleto.length; i++) {
			for(int j = 0; j < tableroCompleto.length; j++) {
				int valorActual = tableroCompleto[i][j];
				if(valorActual == 0) {
					assertTrue(sudoku.celdaModificable(i, j));
				}else {
					assertFalse(sudoku.celdaModificable(i, j));
				}
			}
		}
	}
}
