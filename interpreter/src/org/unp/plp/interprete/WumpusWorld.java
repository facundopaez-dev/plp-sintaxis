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

	/**
	 * Imprime el mundo Wumpus
	 */
	public void print() {
		System.out.println("world," + world.length + "," + world[0].length);

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
		}

		if (element.equals(PIT)) {
			putPit(givenCoordinate);
		}

	}

	// **********************************************************
	// Metodos para la colocacion de elementos en el mundo Wumpus
	// **********************************************************

	/**
	 * Almacena la coordenada en la que se encuentra el oro en el
	 * mundo Wumpus
	 * 
	 * @param givenCoordinate
	 */
	private void putGold(Coordinate givenCoordinate) {
		goldCoordinate = givenCoordinate;
	}

	/**
	 * Coloca un pozo en la coordenada dada del mundo Wumpus
	 * 
	 * @param givenCoordinate
	 */
	private void putPit(Coordinate givenCoordinate) {
		world[givenCoordinate.row][givenCoordinate.column] = true;
	}

}
