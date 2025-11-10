package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import interfaz.Observador;

public class SolverSudoku implements interfaz.SujetoObservable{
	private final List<Observador> observadores = new ArrayList<>();
	private Sudoku sudoku;
	private Set<Sudoku> soluciones;
	
	public SolverSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
		soluciones = new HashSet<Sudoku>();
	}
	
	public void resolverSudoku() {
		if(sudoku.haySolucion()) {
			probarConTodosLosValores(sudoku, 0, 0);
		}
		notificarObservadores();

	}

	private void probarConTodosLosValores(Sudoku solucionActual, int fila, int columna) {
		if(!solucionActual.tableroCompleto()) {
			if(solucionActual.celdaModificable(fila, columna)) {
				for(int valor = 1; valor <= 9; valor++) {
					resolverSudoku(solucionActual.clonar(), fila, columna, valor);
				}
			} else {
				int[] siguienteCelda = siguienteCelda(fila, columna);
				fila = siguienteCelda[0];
				columna = siguienteCelda[1];
				probarConTodosLosValores(solucionActual, fila, columna);
			}
		}else {
			soluciones.add(solucionActual);
		}
	}

	private void resolverSudoku(Sudoku solucionActual, int filaActual, int columnaActual, int valorActual) {
			int[] siguienteCelda = siguienteCelda(filaActual, columnaActual);
			int siguienteFila = siguienteCelda[0];			
			int siguienteColumna = siguienteCelda[1];			
			try {
				solucionActual.modificarCelda(filaActual, columnaActual, valorActual);
				probarConTodosLosValores(solucionActual, siguienteFila, siguienteColumna);
			} catch (CeldaInmutableException e) {
				probarConTodosLosValores(solucionActual, siguienteFila, siguienteColumna);
			} catch (IllegalArgumentException e) {
			}

	}
	
	private int[] siguienteCelda(int fila, int columna) {
	    columna++;
	    if (columna == 9) {
	    	columna = 0;
	        fila++;
	    }
	    return new int[]{fila, columna};
	}

	public HashSet<Sudoku> soluciones() throws SudokuSinSolucionException{
		if(this.soluciones == null || soluciones.size() == 0) {
			throw new SudokuSinSolucionException("¡No hay soluciones posibles para esta Instancia de Sudoku!");
		}
		return new HashSet<Sudoku>(this.soluciones);
	}
	
	
	@Override
	public void agregarObservador(Observador o) {
		observadores.add(o);
	}
	
	@Override
	public void eliminarObservador(Observador o) {
		observadores.remove(o);	
	}
	
	@Override
	public void notificarObservadores() {
		this.observadores.forEach(o -> o.notificar(this));
	}
}

@SuppressWarnings("serial")
class SudokuSinSolucionException extends Exception {
    public SudokuSinSolucionException(String msg) {
        super(msg);
    }
}
