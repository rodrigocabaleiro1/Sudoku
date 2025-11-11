package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PantallaSoluciones extends Pantalla{
	
	public PantallaSoluciones(String titulo, int xPosicion, int yPosicion, int anchoVentana, int altoVentana) {
		super(titulo, xPosicion, yPosicion, anchoVentana, altoVentana);
	}
	public void inicializar(HashSet<int [][]> tableros) {
        JPanel contenedorPrincipal = new JPanel();
        establecerGrilla(contenedorPrincipal, tableros.size(), 0);
        cambiarDistanciaEntreCeldas((GridLayout) contenedorPrincipal.getLayout(), 10, 10);
        definirColorDeFondo(contenedorPrincipal, Color.WHITE);
        JLabel cantidadSoluciones = new JLabel("¡Hay un total de " + tableros.size() + " Soluciones para la instancia dada!");
        agregarElementosPanel(contenedorPrincipal, cantidadSoluciones);

        for (int[][] tablero : tableros) {
            contenedorPrincipal.add(crearPanelSudoku(tablero));
        }

        JScrollPane scroll = new JScrollPane(contenedorPrincipal);
        agregarElementosPanel(getContentPane(), scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearPanelSudoku(int[][] tablero) {
        JPanel panelSudoku = new JPanel();
        establecerGrilla(panelSudoku, 9, 9);
        establecerBorde(panelSudoku, 5, 5, 5, 5);
        panelSudoku.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JLabel celda = new JLabel(String.valueOf(tablero[fila][col]), SwingConstants.CENTER);
                celda.setFont(new Font("SansSerif", Font.BOLD, 16));
                celda.setOpaque(true);
                celda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                if ((fila / 3 + col / 3) % 2 == 0) {
                    celda.setBackground(new Color(240, 240, 240));
                } else {
                    celda.setBackground(Color.WHITE);
                }

                panelSudoku.add(celda);
            }
        }
        return panelSudoku;
    }

}
