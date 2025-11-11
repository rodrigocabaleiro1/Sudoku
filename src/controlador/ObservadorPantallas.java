package controlador;

import java.awt.BorderLayout;
import java.util.HashSet;

import javax.swing.JFrame;

import negocio.InstanciaSudoku;
import negocio.SolverSudoku;
import pantallas.PantallaPrincipal;
import pantallas.PantallaSoluciones;

public class ObservadorPantallas implements interfaz.Observador{
	PantallaPrincipal pantallaPrincipal;
	PantallaSoluciones pantallaSoluciones;
	InstanciaSudoku instancia;
	
	public ObservadorPantallas() {
		pantallaPrincipal = new PantallaPrincipal("Sudoku Solver", 100, 100, 600, 700, this);
		instancia = new InstanciaSudoku();
		mostrarPantallaPrincipal();
	}

	private void mostrarPantallaPrincipal() {
		pantallaPrincipal.setVisible(true);
	}

	@Override
	public void notificar(SolverSudoku solver) {
		HashSet<InstanciaSudoku> soluciones;
		try {
			soluciones = solver.soluciones();
			mostrarSoluciones(soluciones.size(), soluciones);
		} catch (Exception e) {
			pantallaPrincipal.actualizarEstado(e.toString());
		}		
	}
	
	private void mostrarSoluciones(int size, HashSet<InstanciaSudoku> soluciones) {
		HashSet<int[][]> sol = new HashSet <int[][]>();
		for(InstanciaSudoku i : soluciones) {
			sol.add(i.obtenerInstancia());
		}
		pantallaSoluciones = new PantallaSoluciones("Soluciones",100, 100, 600, 800);
		pantallaSoluciones.inicializar(sol);
		
	}

	public void generarInstanciaAleatoria(int celdasPrefijadas) {
		instancia = InstanciaSudoku.generarInstanciaAleatoria(celdasPrefijadas);
		int [][] tablero = instancia.obtenerInstancia();
		for(int f = 0; f<tablero.length; f++) {
			for(int c = 0; c<tablero.length; c++) {
				if (tablero[f][c] != 0) {
					String valor = " " + tablero[f][c];
					pantallaPrincipal.actualizarTablero(f, c, valor);
				}
			}
		}
		
	}

	public void buscarSoluciones() {
		SolverSudoku solver = new SolverSudoku(instancia);
		solver.agregarObservador(this);
		solver.resolverSudoku();
	}
	
	public void modificarInstancia(int fila, int columna, int valor) {
		instancia.modificarCelda(fila, columna, valor);
	}
	
}
