package main;

import java.util.HashSet;
import java.util.Set;

import negocio.InstanciaSudoku;
import negocio.SolverSudoku;
import negocio.Sudoku;

public class Main {

	public static void main(String[] args) {
		Set<Sudoku> soluciones = new HashSet<Sudoku>();
		int[][] tablero = {
				{5, 3, 4, 6, 7, 8, 9, 0, 2},
			    {6, 7, 2, 1, 9, 5, 3, 4, 8},
			    {1, 9, 8, 3, 4, 2, 5, 6, 7},
			    {8, 5, 9, 7, 6, 1, 4, 2, 3},
			    {4, 2, 6, 8, 5, 3, 7, 9, 1},
			    {7, 1, 3, 9, 2, 4, 8, 5, 6},
			    {9, 6, 1, 5, 3, 7, 2, 8, 4},
			    {2, 8, 7, 4, 1, 9, 6, 3, 5},
			    {3, 4, 5, 2, 8, 6, 1, 7, 9}	
		};
		int [][] tablero2 ={ 
				{0, 0, 0, 0, 0, 2, 1, 0, 0},
				{1, 0, 2, 4, 6, 0, 0, 3, 9},
				{0, 0, 8, 1, 9, 7, 0, 5, 0},
				{0, 0, 0, 2, 0, 0, 7, 9, 3},
				{0, 4, 7, 0, 5, 9, 0, 0, 2},
				{0, 0, 0, 7, 1, 6, 4, 8, 0},
				{9, 0, 0, 6, 0, 0, 5, 0, 0},
			    {0, 2, 0, 0, 0, 1, 0, 0, 4},
				{7, 0, 5, 9, 2, 0, 0, 0, 1}
		};
		
		
		int [][] vacio ={ 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		
		
		InstanciaSudoku i= new InstanciaSudoku(tablero2);
		InstanciaSudoku aleatoria = InstanciaSudoku.generarInstanciaAleatoria(17);
		System.out.println("INSTANCIA INICIAL");
		SolverSudoku solver = new SolverSudoku(i);
		System.out.println("---------------------------------------------");
		solver.resolverSudoku();
		try {
			soluciones = solver.soluciones();
		} catch (Exception e) {
		}
		for(Sudoku sudo : soluciones) {
			System.out.println(sudo);
		}
		System.out.println("Cantidad de Soluciones:" + soluciones.size());
	}

}
