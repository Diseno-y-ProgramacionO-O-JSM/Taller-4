package uniandes.dpoo.triqui.interfaz;

import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelBotones extends JPanel implements ActionListener
{
	
	JButton reiniciar;
	private VentanaTriqui ventana;
	
	public PanelBotones(VentanaTriqui padre)
	{
		this.ventana=padre;
		JButton reiniciar=new JButton("Reiniciar");
		add(reiniciar,BorderLayout.SOUTH);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		ventana.reiniciar();
	}

	public void actualizarCantidadJugadas(int numero)
	{
	}
}
