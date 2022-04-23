package uniandes.dpoo.triqui.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import uniandes.dpoo.triqui.mundo.Triqui;

@SuppressWarnings("serial")
public class PanelTriqui extends JPanel implements MouseListener
{
	
	private VentanaTriqui ventana;
	private Triqui triqui;
	int contador=0;
	ArrayList<Integer> coord = new ArrayList<Integer>();
	

	public PanelTriqui(VentanaTriqui ventanaTriqui)
	{

		this.ventana=ventanaTriqui;
		ventanaTriqui.addMouseListener(this);
		
		ventanaTriqui.setVisible(true);
	
		
	}
		

	public void actualizarTablero(Triqui triqui)
	{
		this.triqui=triqui;
		repaint();
		
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		pintarLineas(g2d);
		pintarJugadas(g2d);
	}
	
	
	private void pintarJugadas(Graphics2D g2d)
	{//((posx+1)*(x*0.3))
		int x = getWidth();
		int y= getHeight();
		
		double tamano=y*0.8;
		
		
		Graphics2D g2s=(Graphics2D) g2d.create();
		
		System.out.println(coord);
		
		if ((contador%2==0) && (contador!=0)){
			int posx = coord.get(0);
			int posy = coord.get(1);
			g2s.setStroke(new BasicStroke(15));
			g2s.setColor(Color.CYAN);			
			g2s.drawOval((int)(((y-(y*0.3))/3)*(posy+1)), (int)(((x-(y*0.3))/3)*(posx+1)), (int)(tamano*0.19), (int)(tamano*0.19));
			System.out.println("X");
		}
		else if  ((contador%2==1) && (contador!=0)){
			int posx = coord.get(0);
			int posy = coord.get(1);
			g2s.setStroke(new BasicStroke(15));
			g2s.setColor(Color.ORANGE);			
			g2s.drawOval((int)(((y-(y*0.3))/3)*(posy+1)), (int)(((y-(y*0.3))/3)*(posx+1)), (int)(tamano*0.19), (int)(tamano*0.19));
		
			System.out.println("O");
			}
		
	}
	
	

	private void pintarLineas(Graphics2D g2d)
	{
		
		int x = getWidth();
		int y= getHeight();
		
		double y2size = y*0.90;
		double ysize = y*0.10;
		double tamano=y*0.8;
		double inx= (x-tamano)/2;
		
		Graphics2D g2s=(Graphics2D) g2d.create();
		g2s.setStroke (new BasicStroke(10f));
		g2s.setColor(Color.PINK);
		g2s.drawLine((int)(inx+tamano*0.33),(int)y2size,(int)(inx+tamano*0.33), (int)ysize);
		g2s.setColor (Color.PINK);
		g2s.drawLine((int)(inx+tamano*0.67),(int)y2size,(int)(inx+tamano*0.67), (int)ysize);
		g2s.setColor (Color.PINK);
		g2s.drawLine((int)inx,(int)(ysize+tamano*0.33),(int)(x-inx), (int)(ysize+tamano*0.33));
		g2s.setColor(Color.PINK);
		g2s.drawLine((int)inx,(int)(ysize+tamano*0.67),(int)(x-inx), (int)(ysize+tamano*0.67));
		
	}

	@Override
	public void mouseClicked(MouseEvent e)	
	{
		
		ArrayList<Integer> coord = accion(e);
		System.out.println(coord);
		boolean puede = triqui.jugar(coord.get(0), coord.get(1));
		if (puede){
			
			ventana.jugar(coord.get(0),coord.get(1));
			contador++;
			System.out.println("c"+contador);
		}
		
		
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	public ArrayList<Integer> accion(MouseEvent e) {
			
			int x = getWidth();
			int y= getHeight();
			
			double ysize = y*0.10;
			double tamano=y*0.8;
			double inx= (x-tamano)/2;
			int xmouse=e.getX();
			int ymouse=e.getY();
			
			if ((inx<xmouse && xmouse<=(inx+tamano*0.33)) &&(ysize<ymouse && ymouse<=(ysize+tamano*0.33))) {
				coord.clear();
				coord.add(0);
				coord.add(0);
				
				return coord;
				
				}
			else if (((inx+tamano*0.33)<xmouse && xmouse<=(inx+tamano*0.67)) &&(ysize<ymouse && ymouse<=(ysize+tamano*0.33))) {
				coord.clear();
				coord.add(0);
				coord.add(1);
				return coord;
				}
			else if (((inx+tamano*0.67)<xmouse && xmouse<=(inx+tamano)) &&(ysize<ymouse && ymouse<=(ysize+tamano*0.33))) {
				coord.clear();
				coord.add(0);
				coord.add(2);
				return coord;
				}
			else if ((inx<xmouse && xmouse<=(inx+tamano*0.33)) &&((ysize+tamano*0.33)<ymouse && ymouse<=(ysize+tamano*0.67))) {
				coord.clear();
				coord.add(1);
				coord.add(0);
				return coord;
				}
			else if (((inx+tamano*0.33)<xmouse && xmouse<=(inx+tamano*0.67)) && ((ysize+tamano*0.33)<ymouse && ymouse<=(ysize+tamano*0.67))) {
				coord.clear();
				coord.add(1);
				coord.add(1);
				return coord;
				}
			else if (((inx+tamano*0.67)<xmouse && xmouse<=(inx+tamano)) && ((ysize+tamano*0.33)<ymouse && ymouse<=(ysize+tamano*0.67))) {
				coord.clear();
				coord.add(1);
				coord.add(2);
				return coord;
				}
			else if ((inx<xmouse && xmouse<=(inx+tamano*0.33)) && ((ysize+tamano*0.67)<ymouse && ymouse<=(ysize+tamano))) {
				coord.clear();
				coord.add(2);
				coord.add(0);
				return coord;
				}
			else if (((inx+tamano*0.33)<xmouse && xmouse<=(inx+tamano*0.67)) && ((ysize+tamano*0.67)<ymouse && ymouse<=(ysize+tamano))) {
				coord.clear();
				coord.add(2);
				coord.add(1);
				return coord;
				}
			else if (((inx+tamano*0.67)<xmouse && xmouse<=(inx+tamano)) && ((ysize+tamano*0.67)<ymouse && ymouse<=(ysize+tamano))) {
				coord.clear();
				coord.add(2);
				coord.add(2);
				return coord;
				}
			else{return null;}
			
			
	}
	
	
	
	
}
