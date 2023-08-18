package org.unp.plp.interprete;

public class WumpusWorld {

	private String[][] world;
	private final String GOLD = "gold";
	private final String PIT = "pit";

	public void setSize(int i, int j) {
		world = new String[i][j];
	}

	public int size() {
		return (world.length * world[0].length);
	}

	/**
	 * 
	 * @return true si el mundo Wumpus existe, false
	 * en caso contrario
	 */
	public boolean worldExists() {
		return (world != null);
	}

	/**
	 * @param i
	 * @param j
	 * @return true si la casilla existe en el mundo Wumpus,
	 * false en caso contrario
	 */
	public boolean boxExists(int i, int j) {

		if (i < 0 || i > world.length - 1) {
			return false;
		}

		if (j < 0 || j > world[0].length - 1) {
			return false;
		}

		return true;
	}

	// **********************************************************
	// Metodos para la colocacion de elementos en el mundo Wumpus
	// **********************************************************

	/**
	 * Coloca oro en la casilla [i,j] del mundo Wumpus
	 * 
	 * @param i
	 * @param j
	 */
	public void putGold(int i, int j) {

		if (!worldExists()) {
			printNonexistentWorldMessage();
			return;
		}

		if (!boxExists(i, j)) {
			printNonexistentBoxMessage();
			return;
		}

		world[i][j] = GOLD;

		System.out.println("Pone oro en la casilla " + "[" + i + "," + j + "]");
		System.out.println("Contenido de la casilla " + "[" + i + "," + j + "]: " + world[i][j]);
		System.out.println();
	}

	/**
	 * Coloca un pozo en la casilla [i,j] del mundo Wumpus
	 * 
	 * @param i
	 * @param j
	 */
	public void putPit(int i, int j) {

		if (!worldExists()) {
			printNonexistentWorldMessage();
			return;
		}

		if (!boxExists(i, j)) {
			printNonexistentBoxMessage();
			return;
		}

		world[i][j] = PIT;

		System.out.println("Pone un pozo en la casilla " + "[" + i + "," + j + "]");
		System.out.println("Contenido de la casilla " + "[" + i + "," + j + "]: " + world[i][j]);
		System.out.println();
	}

	// **************************************************************
	// Metodos para la impresion de mensajes ante entradas no validas
	// **************************************************************

	private void printNonexistentWorldMessage() {
		System.out.println("Mundo Wumpus indefinido");
		System.out.println("Defina el mundo Wumpus mediante el comando world");
		System.out.println();
	}

	private void printNonexistentBoxMessage() {
		System.out.println("La casilla elegida no existe");
		System.out.println("El tamaño (casillas) del mundo " + "[0" + "-" + (world.length - 1) + "," + "0" + "-" + ( world[0].length - 1) + "]" + " es: " + size());
		System.out.println();
	}

}
