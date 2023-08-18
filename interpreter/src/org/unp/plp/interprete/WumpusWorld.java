package org.unp.plp.interprete;

public class WumpusWorld {

	private String[][] world;
	private final String GOLD = "gold";
	private final String PIT = "pit";
	private final String WUMPUS = "wumpus";
	private final String HERO = "hero";
	private final String EMPTY = "empty";

	private int[] goldPosition;
	private int[] wumpusPosition;

	public void setSize(int i, int j) {
		world = new String[i][j];
		goldPosition = new int[2];
		wumpusPosition = new int[2];
	}

	public int size() {
		return (world.length * world[0].length);
	}

	/**
	 * 
	 * @return true si el mundo Wumpus existe, false
	 * en caso contrario
	 */
	private boolean worldExists() {
		return (world != null);
	}

	/**
	 * @param i
	 * @param j
	 * @return true si la casilla existe en el mundo Wumpus,
	 * false en caso contrario
	 */
	private boolean boxExists(int i, int j) {

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

		removeCurrentGold();
		world[i][j] = GOLD;
		saveGoldPosition(i, j);

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

	/**
	 * Coloca un wumpus en la casilla [i,j] del mundo Wumpus
	 * 
	 * @param i
	 * @param j
	 */
	public void putWumpus(int i, int j) {

		if (!worldExists()) {
			printNonexistentWorldMessage();
			return;
		}

		if (!boxExists(i, j)) {
			printNonexistentBoxMessage();
			return;
		}

		removeWumpus();
		world[i][j] = WUMPUS;
		saveWumpusPosition(i, j);

		System.out.println("Pone un wumpus re loco en la casilla " + "[" + i + "," + j + "]");
		System.out.println("Contenido de la casilla " + "[" + i + "," + j + "]: " + world[i][j]);
		System.out.println();
	}

	/**
	 * Coloca un heroe en la casilla [i,j] del mundo Wumpus
	 * 
	 * @param i
	 * @param j
	 */
	public void putHero(int i, int j) {

		if (!worldExists()) {
			printNonexistentWorldMessage();
			return;
		}

		if (!boxExists(i, j)) {
			printNonexistentBoxMessage();
			return;
		}

		world[i][j] = HERO;

		System.out.println("Pone un heroe en la casilla " + "[" + i + "," + j + "]");
		System.out.println("Contenido de la casilla " + "[" + i + "," + j + "]: " + world[i][j]);
		System.out.println();
	}

	// ***********************************************************
	// Metodos para la eliminacion de elementos en el mundo Wumpus
	// ***********************************************************

	/**
	 * Elimina el oro de la casilla i,j del mundo Wumpus
	 * 
	 * @param i
	 * @param j
	 */
	public void removeGold(int i, int j) {

		if (!worldExists()) {
			printNonexistentWorldMessage();
			return;
		}

		if (!boxExists(i, j)) {
			printNonexistentBoxMessage();
			return;
		}

		String element = world[i][j];

		if (element != null) {
			world[i][j] = EMPTY;
			System.out.println("Oro eliminado de la casilla " + "[" + i + "," + j + "]");
			System.out.println("Contenido de la casilla " + "[" + i + "," + j + "]: " + world[i][j]);
			System.out.println();
			return;
		}

		System.out.println("No hay oro en la casilla " + "[" + i + "," + j + "]");
		System.out.println();
	}

	/**
	 * Elimina un pozo de la casilla i,j del mundo Wumpus
	 * 
	 * @param i
	 * @param j
	 */
	public void removePit(int i, int j) {

		if (!worldExists()) {
			printNonexistentWorldMessage();
			return;
		}

		if (!boxExists(i, j)) {
			printNonexistentBoxMessage();
			return;
		}

		String element = world[i][j];

		if (element != null) {
			world[i][j] = EMPTY;
			System.out.println("Pozo eliminado de la casilla " + "[" + i + "," + j + "]");
			System.out.println("Contenido de la casilla " + "[" + i + "," + j + "]: " + world[i][j]);
			System.out.println();
			return;
		}

		System.out.println("No hay un pozo en la casilla " + "[" + i + "," + j + "]");
		System.out.println();
	}

	/**
	 * Almacena la posicion del oro
	 * 
	 * @param i
	 * @param j
	 */
	private void saveGoldPosition(int i, int j) {
		goldPosition[0] = i;
		goldPosition[1] = j;
	}

	/**
	 * Elimina el unico oro que hay en el mundo, ya que solo
	 * puede haber una ocurrencia del oro en el mundo
	 * simultaneamente
	 */
	private void removeCurrentGold() {
		String element = world[goldPosition[0]][goldPosition[1]];

		/*
		 * Inicialmente el contenido de la posicion dada tiene
		 * el valor null. Por lo tanto, es necesario realizar
		 * este control antes de eliminar un elemento de dicha
		 * posicion.
		 */
		if (element != null) {
			world[goldPosition[0]][goldPosition[1]] = EMPTY;
			System.out.println("Oro eliminado de la casilla [" + goldPosition[0] + "," + goldPosition[1] + "]");
			System.out.println("Contenido de la casilla [" + goldPosition[0] + "," + goldPosition[1] + "]: " + world[goldPosition[0]][goldPosition[1]]);
		}

	}

	/**
	 * Almacena la posicion de un wumpus
	 * 
	 * @param i
	 * @param j
	 */
	private void saveWumpusPosition(int i, int j) {
		wumpusPosition[0] = i;
		wumpusPosition[1] = j;
	}

	/**
	 * Elimina el unico wumpus que hay en el mundo, ya que solo
	 * puede haber un wumpus en el mundo simultaneamente
	 */
	private void removeWumpus() {
		String element = world[wumpusPosition[0]][wumpusPosition[1]];

		/*
		 * Inicialmente el contenido de la posicion dada tiene
		 * el valor null. Por lo tanto, es necesario realizar
		 * este control antes de eliminar un elemento de dicha
		 * posicion.
		 */
		if (element != null) {
			world[wumpusPosition[0]][wumpusPosition[1]] = EMPTY;
			System.out.println("Wumpus re loco eliminado de la casilla [" + wumpusPosition[0] + "," + wumpusPosition[1] + "]");
			System.out.println("Contenido de la casilla [" + wumpusPosition[0] + "," + wumpusPosition[1] + "]: " + world[wumpusPosition[0]][wumpusPosition[1]]);
		}

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
