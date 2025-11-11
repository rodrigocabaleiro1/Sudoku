package interfaz;

import negocio.SolverSudoku;

public interface Observador {
	void notificar(SolverSudoku o);
}
