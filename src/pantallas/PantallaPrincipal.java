package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

import controlador.ObservadorPantallas;
import negocio.InstanciaSudoku;
import negocio.Sudoku;

public class PantallaPrincipal extends Pantalla{
	private ObservadorPantallas _controlador;
	private JTextField[][] celdas;
	private JPanel panelTablero;
    private JLabel labelEstado;
    
	public PantallaPrincipal(String titulo, int xPosicion, int yPosicion, int anchoVentana, int altoVentana,
																				ObservadorPantallas controlador) {
		super(titulo, xPosicion, yPosicion, anchoVentana, altoVentana);
		celdas = new JTextField[9][9];
		panelTablero = new JPanel(new GridLayout(9, 9, 1, 1));
		_controlador = controlador;
		inicializarTablero();
	}
	private void inicializarTablero() {
		panelTablero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panelTablero.setBackground(Color.BLACK);
        
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                JTextField celda = new JTextField();
                celda.setHorizontalAlignment(JTextField.CENTER);
                celda.setFont(new Font("Arial", Font.BOLD, 24));
               
                Color colorFondo = Color.WHITE;
                celda.setBackground(colorFondo);
                
                Border border;
                int top = (fila % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = 1;
                int right = 1;
                border = BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK);
                celda.setBorder(border);
                
                final int f = fila;
                final int c = col;
                celdaComportamiento(celda, f, c);
                celdas[fila][col] = celda;
                agregarElementosPanel(panelTablero, celda);
            }
        }
        
        agregarElementosPanel(getContentPane(), panelTablero, BorderLayout.CENTER);
        crearBotones();
	}
	private void celdaComportamiento(JTextField celda, final int f, final int c) {
		celda.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		    	if(celdaConContenido(celda)) {
		    		celda.setText("");
		    	}
		        char caracter = e.getKeyChar();
		        if (!Character.isDigit(caracter) || caracter == '0') {
		            e.consume();
		        }
		    }

			private boolean celdaConContenido(JTextField celda) {
				return celda.getText().length() > 0;
			}
		    
		    @Override
		    public void keyReleased(KeyEvent e) {
		        actualizarCelda(f, c);
		    }
		});
	}
    
    private void actualizarCelda(int fila, int col) {        
    	JTextField celda = celdas[fila][col];
        String texto = celda.getText().trim();
        
        if (texto.isEmpty()) {
        	actualizarCelda (fila, col, 0);
        	return;
        } 
        try {
            int valor = Integer.parseInt(texto);
            actualizarCelda(fila, col, valor);
        } catch (NumberFormatException e) {

        }
    }
    
    private void actualizarCelda(int fila, int col, int valor) {
		_controlador.modificarInstancia(fila, col, valor);
	}
    
	private void crearBotones() {
        JPanel panelBotones = new JPanel(new FlowLayout());
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarTablero());
        
        JButton btnVerificar = new JButton("Buscar Soluciones");
        btnVerificar.addActionListener(e -> BuscarSoluciones());
        
        JButton btnGenerarInstanciaAleatoria = new JButton("GenerarAleatoria");
        btnGenerarInstanciaAleatoria.addActionListener(e -> generarInstanciaAleatoria());
        
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVerificar);
        panelBotones.add(btnGenerarInstanciaAleatoria);
        
        labelEstado = new JLabel("Bienvenido al Sudoku!");
        labelEstado.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstado.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelBotones, BorderLayout.CENTER);
        panelInferior.add(labelEstado, BorderLayout.SOUTH);
        
        add(panelInferior, BorderLayout.SOUTH);
    }
    
	private void generarInstanciaAleatoria() {
	    String input = JOptionPane.showInputDialog(
	        null,
	        "Elija la cantidad de valores prefijados que debe tener su instancia:",
	        "Generar instancia aleatoria",
	        JOptionPane.QUESTION_MESSAGE
	    );

	    if (input != null && !input.trim().isEmpty()) {
	        try {
	            int cantidad = Integer.parseInt(input.trim());
	            _controlador.generarInstanciaAleatoria(cantidad);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
	        }
	    }
	}
	private void BuscarSoluciones() {
    	_controlador.buscarSoluciones();
	}
	private void limpiarTablero() {
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                    celdas[fila][col].setText("");
                    _controlador.modificarInstancia(fila, col, 0);
            }
        }
        actualizarEstado("Tablero limpiado");
    }
	
	public void actualizarTablero(int fila, int columna, String valor) {
		celdas[fila][columna].setText("");
		celdas[fila][columna].setText(valor);
	}
    
    public void actualizarEstado(String mensaje) {
        labelEstado.setText(mensaje);
    }
}
