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
		if(!sudoku.haySolucion()) {
			soluciones = null;
		} else {
			probarConTodosLosValores(sudoku, 0, 0);
			soluciones.remove(null);
		}
	}

	private void probarConTodosLosValores(Sudoku solucionActual, int fila, int columna) {
		for(int valor = 1; valor < 10; valor++) {
			resolverSudoku(solucionActual.clonar(), fila, columna, valor);
		}
	}

	private void resolverSudoku(Sudoku solucionActual, int filaActual, int columnaActual, int valorActual) {
		if (solucionActual.tableroCompleto()) {
			soluciones.add(solucionActual);
		} else {
			int siguienteFila = filaActual;
			int siguienteColumna = columnaActual;
			System.out.println(columnaActual + " | " + filaActual + " = " + valorActual);
			if(siguienteFila >= 8) {
				siguienteColumna++;
				siguienteFila = 0;
			}else {
				siguienteFila++;
			}
			try {
				solucionActual.modificarCelda(filaActual, columnaActual, valorActual);
				probarConTodosLosValores(solucionActual, siguienteFila, siguienteColumna);
			} catch (CeldaInmutableException e) {
				probarConTodosLosValores(solucionActual, siguienteFila, siguienteColumna);
			} catch (IllegalArgumentException e) {
			}
		}

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
