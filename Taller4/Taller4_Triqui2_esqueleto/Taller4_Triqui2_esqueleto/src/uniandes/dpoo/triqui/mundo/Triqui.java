package uniandes.dpoo.triqui.mundo;

public class Triqui
{
	public static final int VACIO = 0;
	public static final int OS = 1;
	public static final int XS = 2;
	public static final int EMPATE = 3;
	public static final int SIN_TERMINAR = 4;

	private int[][] tablero;
	private int estadoJuego;
	private boolean[][] posicionGanadora;
	private int turno;
	private int cantidadJugadas;

	public Triqui()
	{
		tablero = new int[3][3];
		estadoJuego = SIN_TERMINAR;
		turno = XS;
		cantidadJugadas = 0;
	}

	/**
	 * Pone la siguiente pieza en la fila y columna indicada, actualiza el turno
	 * siguiente y revisa si el juego terminó.
	 * 
	 * La jugada sólo se realiza si la posición en el tablero está vacía y si el
	 * juego no ha terminado
	 *
	 * @param fila
	 * @param columna
	 * @return Retorna true si se pudo realizar la jugada y false de lo contrario.
	 */
	public boolean jugar(int fila, int columna)
	{
		boolean pudoJugar = false;
		if (estadoJuego == SIN_TERMINAR)
		{
			if (tablero[fila][columna] == VACIO)
			{
				tablero[fila][columna] = turno;
				cantidadJugadas++;
				pudoJugar = true;

				if (turno == XS)
					turno = OS;
				else
					turno = XS;

				revisarJuegoTerminado();
			}
		}
		
		return pudoJugar;
	}

	/**
	 * Reinicia el juego: desocupa el tablero, reinicia el contador de jugadas y
	 * deja listo todo para que el jugador que usa XS empiece a jugar.
	 */
	public void reiniciarJuego()
	{
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				tablero[i][j] = VACIO;
		turno = XS;
		estadoJuego = SIN_TERMINAR;
		cantidadJugadas = 0;
		posicionGanadora = null;
	}

	private void revisarJuegoTerminado()
	{
		boolean[][] temp = new boolean[3][3];
		boolean hayGanador = false;
		int ganador = XS;

		// Revisar filas
		for (int i = 0; i < 3; i++)
		{
			int p0 = tablero[i][0];
			int p1 = tablero[i][1];
			int p2 = tablero[i][2];
			if (p0 == p1 && p1 == p2 && p0 != VACIO)
			{
				hayGanador = true;
				ganador = p0;
				for (int j = 0; j < 3; j++)
					temp[i][j] = true;
			}
		}

		// Revisar las columnas
		for (int i = 0; i < 3; i++)
		{
			int p0 = tablero[0][i];
			int p1 = tablero[1][i];
			int p2 = tablero[2][i];
			if (p0 == p1 && p1 == p2 && p0 != VACIO)
			{
				hayGanador = true;
				ganador = p0;
				for (int j = 0; j < 3; j++)
					temp[j][i] = true;
			}
		}

		// Revisar la diagonal mayor
		int d0 = tablero[0][0];
		int d1 = tablero[1][1];
		int d2 = tablero[2][2];

		if (d0 == d1 && d1 == d2 && d0 != VACIO)
		{
			hayGanador = true;
			ganador = d0;
			for (int j = 0; j < 3; j++)
				temp[j][j] = true;
		}

		// Revisar la diagonal menor
		d0 = tablero[0][2];
		d1 = tablero[1][1];
		d2 = tablero[2][0];

		if (d0 == d1 && d1 == d2 && d0 != VACIO)
		{
			hayGanador = true;
			ganador = d0;
			for (int j = 0; j < 3; j++)
				temp[j][2 - j] = true;
		}

		if (hayGanador)
		{
			posicionGanadora = temp;
			estadoJuego = ganador;
		}
		else if (cantidadJugadas == 9)
		{
			estadoJuego = EMPATE;
		}

		System.out.println("estado"+estadoJuego);
	}

	/**
	 * Indica si el juego no ha terminado, si hay un ganador o empate
	 * 
	 * @return SIN_TERMINAR si todavía no hay un ganador y todavía hay casillas
	 *         donde se puede jugar. EMPATE, si ya no hay casillas donde se pueda
	 *         jugar pero no hay un ganador para el juego. OS si el jugador que usa
	 *         las O ya ganó y Xs de lo contrario.
	 */
	public int estadoJuego()
	{
		return estadoJuego;
	}

	/**
	 * Retorna una matriz que indica cuáles son las casillas que hacen parte de la
	 * jugada ganadora de un jugador. La matriz tiene valores true en las casillas
	 * que hacen parte de la jugada ganadora y false en el resto de casillas.
	 * 
	 * @return Retorna una matriz de 3 x 3 booleanos. Si no hay un ganador aún,
	 *         retorna null;
	 */
	public boolean[][] casillasGanadoras()
	{
		return posicionGanadora;
	}

	/**
	 * Retorna la cantidad de jugadas hechas en este juego
	 * 
	 * @return cantidadJugadas
	 */
	public int darCantidadJugadas()
	{
		return cantidadJugadas;
	}

	/**
	 * Retorna **una copia** de las casillas del tablero
	 * 
	 * @return Una copia de las casillas
	 */
	public int[][] darCasillas()
	{
		int[][] copia = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				copia[i][j] = tablero[i][j];
		return copia;
	}

}
