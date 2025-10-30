package negocio;

import java.awt.Point;
import java.io.IOException;

public class Sudoku {
	private int [][] tablero;
	public Sudoku() {
		tablero = new int [9][9];
	}
	
	public Sudoku(InstanciaSudoku instancia) {
		this.tablero = instancia.obtenerInstancia();
	}
	
	public void modificarCelda(Point coordenadas, int valor){}
	public boolean haySolucion() {
		return false;
	}
	private void validarValor(int valor) throws IllegalArgumentException{}
	private void validarFila (int fila) throws IOException{}
	private void validarColumna(int columna) throws IOException{}
	private void validarCuadrante(int fila, int col) throws IOException{}
	
	//cuadrantes son del 1 al 3 tanto en vertical como horizontal
	private Point cuadrante(int fila, int col) {
		return null;
	}
	
}
