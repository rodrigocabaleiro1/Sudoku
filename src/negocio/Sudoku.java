package negocio;

import java.awt.Point;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sudoku{
	private int [][] tablero;
	int cantidadCeldasInmutables;

	public Sudoku() {
		tablero = new int [9][9];
		cantidadCeldasInmutables = 0;
	}
	
	public Sudoku(InstanciaSudoku instancia) {
		this.tablero = instancia.obtenerInstancia();
		cantidadCeldasInmutables = 0;
		determinarCeldasInmutables();
	}
	
	public InstanciaSudoku obtenerSudoku() {
		return new InstanciaSudoku(tablero);
	}
	
	public boolean celdaModificable(int fila, int columna) {
		return tablero[fila][columna] == 0;
	}
	
	public boolean valorCorrectoEnCelda(int fila, int columna, int valor) {
		return this.tablero[fila][columna] == valor;
	}

	private void determinarCeldasInmutables() {
		for(int f = 0; f < tablero.length; f++) {
			for (int c = 0; c < tablero.length; c++) {
				if (tablero[f][c] != 0) {
					cantidadCeldasInmutables++;
				}
			}
		}
	}
	
	public boolean tableroCompleto() {
		return cantidadCeldasInmutables == 9*9;
	}
	public void modificarCelda(int fila, int col, int valor) throws RuntimeException{
		verificarFilayColumna(fila,col);
		verificarCeldaModificable(fila, col);
		validarValor(valor);
		validarValorEnFila(fila, valor);
		validarValorEnColumna(col, valor);
		validarValorEnCuadrante(fila, col, valor);
		tablero[fila][col] = valor;
		cantidadCeldasInmutables++;
	}
	
	private void verificarCeldaModificable(int fila, int col) throws CeldaInmutableException{
		if(!celdaModificable(fila, col)) {
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

	public Sudoku clonar() {
		int[][] copia = new int[9][9];
	    for (int i = 0; i < 9; i++) {
	    	for (int j = 0; j < 9; j++) {
	        	copia[i][j] = tablero[i][j];
	    	}
	    }
	    InstanciaSudoku instancia = new InstanciaSudoku(copia);
	    return new Sudoku(instancia);
	}
	
	@Override 
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("--------------SUDOKU-----------------\n");
		sb.append("_____________________________________\n");
		for(int f = 0; f < 9; f++) {
			sb.append("|");
			for(int c = 0; c < 9; c++) {
				int celda = tablero[f][c];
				if(celda == 0) {
					sb.append("   |");					
				}else {
					sb.append(" " + celda + " |");
				}
			}
			sb.append("\n");

			sb.append("_____________________________________\n");
		}
		return sb.toString();
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // misma referencia
        if (obj == null || getClass() != obj.getClass()) return false;
        Sudoku otro = (Sudoku) obj;
        boolean resultado = true;
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                resultado &= otro.valorCorrectoEnCelda(fila, col, tablero[fila][col]);
            }
        }
        return resultado;
    }

	@Override
    public int hashCode() {
    	int resultado = Arrays.deepHashCode(tablero);
    	resultado = 31 * resultado;
        return resultado;
    }
}

@SuppressWarnings("serial")
class CeldaInmutableException extends RuntimeException {
    public CeldaInmutableException(String msg) {
        super(msg);
    }
}
