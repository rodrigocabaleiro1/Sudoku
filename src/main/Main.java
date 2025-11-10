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
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {8, 5, 9, 7, 6, 1, 4, 2, 3},
			    {4, 2, 6, 8, 5, 3, 7, 9, 1},
			    {7, 1, 3, 9, 2, 4, 8, 5, 6},
			    {9, 6, 1, 5, 3, 7, 2, 8, 4},
			    {2, 8, 7, 4, 1, 9, 6, 3, 5},
			    {3, 4, 5, 2, 8, 6, 1, 7, 9}	
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
		
		
		InstanciaSudoku i= new InstanciaSudoku(tablero);//InstanciaSudoku.generarInstanciaAleatoria();
		Sudoku s = new Sudoku(i);
		System.out.println("INSTANCIA INICIAL");
		System.out.println(s);
		SolverSudoku solver = new SolverSudoku(s);
		System.out.println("---------------------------------------------");
		solver.resolverSudoku();
		try {
			soluciones = solver.soluciones();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		for(Sudoku sudo : soluciones) {
			System.out.println(sudo);
		}
		System.out.println("Cantidad de Soluciones:" + soluciones.size());
	}

}
