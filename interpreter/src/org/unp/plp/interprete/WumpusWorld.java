package org.unp.plp.interprete;

public class WumpusWorld {

	private boolean[][] world;
	private Coordinate goldCoordinate;
	private Coordinate heroCoordinate;
	private Coordinate wumpusCoordinate;
	private final String GOLD = "gold";
	private final String PIT = "pit";
	private final String WUMPUS = "wumpus";
	private final String HERO = "hero";

	/*
	 * Cosas a tener en cuenta:
	 * - Cuando el wumpus se mueve se deben cambiar los mensajes (breeze, strench) adyacentes.
	 * - El heroe y el wumpus se pueden mover, con lo cual se debe actualizar la posicion de estos personajes.
	 * - El heroe comienza en la columna 0 y en la fila = fila - 1.
	 */

	public void create(int i, int j) {
		world = new boolean[i][j];
		goldCoordinate = new Coordinate();
		heroCoordinate = new Coordinate();
		wumpusCoordinate = new Coordinate();
	}

	public int size() {
		return (world.length * world[0].length);
	}

	/**
	 * Imprime el mundo Wumpus
	 */
	public void print() {
		System.out.println("world," + world.length + "," + world[0].length);
		System.out.println(WUMPUS + "," + wumpusCoordinate.row + "," + wumpusCoordinate.column);
		System.out.println(GOLD + "," + goldCoordinate.row + "," + goldCoordinate.column);
		System.out.println(HERO + "," + heroCoordinate.row + "," + heroCoordinate.column);

		for (int i = 0; i < world.length; i++) {

			for (int j = 0; j < world[0].length; j++) {

				if (world[i][j]) {
					System.out.println(PIT + "," + i + "," + j);
				}

			}

		}

		System.out.println();
	}

	/**
	 * Coloca un elemento en el mundo wumpus
	 * 
	 * @param element
	 * @param givenCoordinate
	 */
	public void put(String element, Coordinate givenCoordinate) {

		if (element.equals(GOLD)) {
			putGold(givenCoordinate);
			return;
		}

		if (element.equals(PIT)) {
			putPit(givenCoordinate);
			return;
		}

		if (element.equals(WUMPUS)) {
			putWumpus(givenCoordinate);
			return;
		}

	}

	// **********************************************************
	// Metodos para la colocacion de elementos en el mundo Wumpus
	// **********************************************************

	/**
	 * Almacena la coordenada en la que se encuentra el oro en el
	 * mundo Wumpus, si la coordenada pertenece a dicho mundo
	 * 
	 * @param givenCoordinate
	 */
	private void putGold(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		goldCoordinate = givenCoordinate;
	}

	/**
	 * Coloca un pozo en la coordenada dada del mundo Wumpus,
	 * si la coordenada pertenece a dicho mundo
	 * 
	 * @param givenCoordinate
	 */
	private void putPit(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		world[givenCoordinate.row][givenCoordinate.column] = true;
	}

	/**
	 * Almacena la coordenada del Wumpus, si la coordenada
	 * pertenece al mundo Wumpus
	 * 
	 * @param givenCoordinate
	 */
	private void putWumpus(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		wumpusCoordinate = givenCoordinate;
	}

	/**
	 * @param givenCoordinate
	 * @return true si la casilla elegida existe, en caso
	 * contrario false
	 */
	private boolean boxExists(Coordinate givenCoordinate) {

		if (givenCoordinate.row < 0 || givenCoordinate.row > world.length - 1) {
			return false;
		}

		if (givenCoordinate.column < 0 || givenCoordinate.column > world[0].length - 1) {
			return false;
		}

		return true;
	}

	// **************************************************************
	// Metodos para la impresion de mensajes ante entradas no validas
	// **************************************************************

	private void printNonexistentBoxMessage() {
		System.out.println("La casilla elegida no existe");
		System.out.println("El tamaño (casillas) del mundo " + "[0" + "-" + (world.length - 1) + "," + "0" + "-" + (world[0].length - 1) + "]" + " es: " + size());
		System.out.println();
	}

}
