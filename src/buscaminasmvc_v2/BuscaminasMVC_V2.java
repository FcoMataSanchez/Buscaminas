/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminasmvc_v2;

import controlador.Control;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import modelo.Tablero;



/**
 *
 * @author paco
 */
public class BuscaminasMVC_V2 {
    // // dimensiones del tablero por defecto 10x10
	private int alto = 10;
	private int ancho = 10;
	private int anchoBoton = 32;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuscaminasMVC_V2 buscaminas = new BuscaminasMVC_V2();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public BuscaminasMVC_V2() {
		initialize();
	}

	private void initialize() {

		JFrame frame = new JFrame();
		JPanel contenedor = new JPanel();
		JPanel titulo = new JPanel();
		JPanel panelPrincipal = new JPanel();
		frame.setLayout(new BorderLayout());
		contenedor.setLayout(new BorderLayout());
		frame.setTitle("Buscaminas");

		Control control = new Control();
		control.prepararPanel(titulo, alto * ancho / 5);

		// ActionListener de smiley del panel superior
		control.getBtnSmiley().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset(ancho, alto, panelPrincipal, frame, control);

			}
		});

		frame.getContentPane().add(titulo, BorderLayout.NORTH);

		// Inicializar el tablero del buscaminas
		Tablero tablero = new Tablero(alto, alto, control);
		tablero.crearTablero(panelPrincipal);
		frame.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

		// Crear el menú superior
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Archivo");
		JMenuItem nuevo = new JMenuItem("Nueva partida");

		nuevo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset(ancho, alto, panelPrincipal, frame, control);
			}
		});

		JMenuItem salir = new JMenuItem("Salir");

		salir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu nivelMenu = new JMenu("Nivel");
		JRadioButtonMenuItem principiante = new JRadioButtonMenuItem(
				"Principiante");
		JRadioButtonMenuItem intermedio = new JRadioButtonMenuItem("Intermedio");
		JRadioButtonMenuItem experto = new JRadioButtonMenuItem("Experto");

		principiante.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				intermedio.setSelected(false);
				experto.setSelected(false);
				alto = 5;
				ancho = 5;
				reset(ancho, alto, panelPrincipal, frame, control);
			}
		});

		intermedio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				principiante.setSelected(false);
				experto.setSelected(false);
				alto = 10;
				ancho = 10;
				reset(ancho, alto, panelPrincipal, frame, control);
			}
		});

		experto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				intermedio.setSelected(false);
				principiante.setSelected(false);
				alto = 18;
				ancho = 18;
				reset(18, 18, panelPrincipal, frame, control);

			}
		});

		intermedio.setSelected(true);
		fileMenu.add(nuevo);
		fileMenu.add(salir);
		nivelMenu.add(principiante);
		nivelMenu.add(intermedio);
		nivelMenu.add(experto);
		menuBar.add(fileMenu);
		menuBar.add(nivelMenu);
		frame.setJMenuBar(menuBar);

		frame.setBounds(0, 0, anchoBoton * ancho + 15, anchoBoton * alto + 100);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public void reset(int ancho, int alto, JPanel panel, JFrame frame,
			Control control) {
		
		Tablero tablero = new Tablero(alto, alto, control);
		control.getBtnSmiley().setIcon(control.getSmiley());
		control.inicializarBanderas(tablero.getnColumnas()
				* tablero.getnFilas() / 5);
		tablero.crearTablero(panel);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setBounds(0, 0, anchoBoton * ancho + 15, anchoBoton * alto + 100);
	}

    
    
}
