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
			{0,0,0,1,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0}		
	};
	
	@Test
	public void crearSudokuInstanciaTest() {
		InstanciaSudoku instancia = new InstanciaSudoku(tablero);
		Sudoku sudoku = new Sudoku(instancia);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void modificarCeldaValorInvalidoMuyBajo() {
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(new Point(1,1), -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void modificarCeldaValorInvalidoMuyAlto() {
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(new Point(1,1), 10);
	}
	
	@Test(expected = IOException.class)
	public void numeroRepetidoFila() {
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(new Point(1,1), 1);
		sudoku.modificarCelda(new Point(1,8), 1);
	}
	
	@Test(expected = IOException.class)
	public void numeroRepetidoColumna() {
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(new Point(1,1), 1);
		sudoku.modificarCelda(new Point(8,1), 1);
	}
	
	@Test(expected = IOException.class)
	public void numeroRepetidoCuadrante() {
		Sudoku sudoku = new Sudoku();
		sudoku.modificarCelda(new Point(1,1), 1);
		sudoku.modificarCelda(new Point(2,2), 1);
	}
	
	@Test
	public void tableroConSolucion() {
		
	}
}
