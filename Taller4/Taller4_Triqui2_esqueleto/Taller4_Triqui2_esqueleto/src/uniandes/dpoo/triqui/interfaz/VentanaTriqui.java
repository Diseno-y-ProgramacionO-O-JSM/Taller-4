package uniandes.dpoo.triqui.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.dpoo.triqui.mundo.Triqui;

@SuppressWarnings("serial")
public class VentanaTriqui extends JFrame
{
	private Triqui triqui;

	private PanelTriqui pTriqui;
	private PanelBotones pBotones;

	public VentanaTriqui()
	{
		triqui = new Triqui();

		pTriqui = new PanelTriqui(this);
		add(pTriqui, BorderLayout.CENTER);
		pBotones = new PanelBotones(this);
		add(pBotones, BorderLayout.SOUTH);

		actualizar();
		setSize(500, 500);
		setTitle("Triqui");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void jugar(int fila, int columna)
	{
		triqui.jugar(fila, columna);
		actualizar();

		int estado = triqui.estadoJuego();
		if (estado != Triqui.SIN_TERMINAR)
		{
			if (estado == Triqui.EMPATE)
			{
				JOptionPane.showMessageDialog(this, "El juego terminó en empate", "Empate",
						JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "¡Hay un ganador!", "Fin del juego",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public void reiniciar()
	{
		triqui.reiniciarJuego();
		actualizar();
	}

	public void actualizar()
	{
		pTriqui.actualizarTablero(triqui);
		pBotones.actualizarCantidadJugadas(triqui.darCantidadJugadas());
	}

	public static void main(String[] args)
	{
		new VentanaTriqui();
	}

}
