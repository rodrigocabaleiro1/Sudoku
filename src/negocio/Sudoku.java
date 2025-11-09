package negocio;

import java.awt.Point;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {
	private int [][] tablero;
	boolean[][] celdasInmutables;

	public Sudoku() {
		tablero = new int [9][9];
		celdasInmutables = new boolean [9][9];
	}
	
	public Sudoku(InstanciaSudoku instancia) {
		this.tablero = instancia.obtenerInstancia();
		celdasInmutables = new boolean [9][9];
		determinarCeldasInmutables();
	}

	private void determinarCeldasInmutables() {
		for(int f = 0; f < tablero.length; f++) {
			for (int c = 0; c < tablero.length; c++) {
				if (tablero[f][c] != 0) {
					celdasInmutables[f][c] = true;
				}
				else {
					celdasInmutables[f][c] = false;
				}
			}
		}
	}
	
	public void modificarCelda(int fila, int col, int valor){
		verificarFilayColumna(fila,col);
		verificarCeldaModificable(fila, col);
		validarValor(valor);
		validarValorEnFila(fila, valor);
		validarValorEnColumna(col, valor);
		validarValorEnCuadrante(fila, col, valor);
		tablero[fila][col] = valor;
	}
	
	private void verificarCeldaModificable(int fila, int col) throws CeldaInmutableException{
		if(celdasInmutables[fila] [col]) {
			throw new CeldaInmutableException("¡Esta celda NO puede modificarse! Celda: (" + fila + ", " + col + ")");
		}
	}

	public boolean haySolucion() {
		boolean haySolucion = true;
		haySolucion &= filasValidas();
		haySolucion &= columnasValidas();
		haySolucion &= cuadrantesValidos();
		return haySolucion;
	}
	
	private boolean filasValidas() {
		boolean filasValidas = true;
		for(int f = 0; f < tablero.length; f++) {
			filasValidas &= filaValida(tablero[f]);
		}
		return filasValidas;
	}

	private boolean filaValida(int[] fila) {
        Set<Integer> elementosFila = new HashSet<>();
		for(int i = 0; i < fila.length; i++) {
			if(elementosFila.contains(fila[i]) && fila[i] != 0) {
				return false;
			}
			else {
				elementosFila.add(fila[i]);
			}
		}
		return true;
	}

	private boolean columnasValidas() {
		boolean columnasValidas = true;
		for(int c = 0; c < tablero.length; c++) {
			columnasValidas &= columnaValida(c);
		}
		return columnasValidas;
	}

	private boolean columnaValida(int columna) {
		Set<Integer> elementosColumna = new HashSet<>();
		for(int i = 0; i < tablero.length; i++) {
			if(elementosColumna.contains(tablero[i][columna]) && tablero[i][columna] != 0) {
				return false;
			}
			else {
				elementosColumna.add(tablero[i][columna]);
			}
		}
		return true;
	}

	private boolean cuadrantesValidos() {
		boolean cuadrantesValidos = true;
		for(int horizontal = 0; horizontal < 3; horizontal++) {
			for(int vertical = 0; vertical < 3; vertical++) {
				cuadrantesValidos &= cuadranteValido(vertical, horizontal);
			}
		}
		return cuadrantesValidos;
	}

	private boolean cuadranteValido(int vertical, int horizontal) {
		Set<Integer> elementosCuadrante = new HashSet<>();
		for(int f = 0; f < 3; f++) {
			for(int c = 0; c < 3; c++) {
				int celda = tablero[coordenadaEnCuadrante(vertical, f)][coordenadaEnCuadrante(horizontal, c)];
				if(elementosCuadrante.contains(celda)
						&& celda != 0) {
					return false;
				}
				else {
					elementosCuadrante.add(celda);
				}
			}
		}
		return true;
	}
	
	private int obtenerCuadrante(int indice) {
		if (indice < 3) {
			return 0;
		}
		else if(indice < 6) {
			return 1;
		}
		else {
			return 2;
		}
	}

	private int coordenadaEnCuadrante(int cuadrante, int indice) {
		return indice + 3 * cuadrante;
	}

	private void validarValor(int valor) throws IllegalArgumentException{
		if(valor > 9 || valor <= 0) {
			throw new IllegalArgumentException("¡El valor ingresado es invalido¡ Valor: " +  valor);
		}
	}
	
	private void validarValorEnFila (int fila, int valor) throws IllegalArgumentException{
		for(int i = 0; i < tablero.length; i++) {
			if((tablero[fila][i] != 0) && (tablero[fila][i] == valor)) {
				throw new IllegalArgumentException("¡No pueden existir dos valores repetidos en la misma fila¡ Valor: " +  valor + "; Fila: " + fila);
			}
		}
	}
	
	private void verificarFilayColumna(int fila, int columna) throws IllegalArgumentException{
		if (fila < 0 || fila >= tablero.length) {
			throw new IllegalArgumentException("¡No se puede acceder a una FILA inexistente! Fila:" + fila);
		}
		
		if (columna < 0 || columna >= tablero.length) {
			throw new IllegalArgumentException("¡No se puede acceder a una COLUMNA inexistente! columna:" + columna);
		}
	}
	
	private void validarValorEnColumna(int columna, int valor) throws IllegalArgumentException{
		for(int i = 0; i < tablero.length; i++) {
			if((tablero[i][columna] != 0) && (tablero[i][columna] == valor)) {
				throw new IllegalArgumentException("¡No pueden existir dos valores repetidos en la misma columna¡ Valor: " +  valor + "; Columna: " + columna );
			}
		}
	}
	private void validarValorEnCuadrante(int fila, int columna, int valor) throws IllegalArgumentException{
		int cuadranteVertical = obtenerCuadrante(fila);
		int cuadranteHorizontal = obtenerCuadrante(columna);
		for(int f = 0; f < 3; f++) {
			for(int c = 0; c < 3; c++) {
				int celda = tablero[coordenadaEnCuadrante(cuadranteVertical, f)][coordenadaEnCuadrante(cuadranteHorizontal, c)];
				if((celda != 0) && (celda == valor)) {
					throw new IllegalArgumentException("¡No pueden existir dos valores repetidos en el mismo Cuadrante¡ Valor: " 
														+  valor + "; Cuadrante: (" + cuadranteVertical + ", " + cuadranteHorizontal + ")" );
				}
			}
		}
	}	
}

@SuppressWarnings("serial")
class CeldaInmutableException extends RuntimeException {
    public CeldaInmutableException(String msg) {
        super(msg);
    }
}
