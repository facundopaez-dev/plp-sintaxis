package org.unp.plp.interprete;

import java.util.Set;
import java.util.HashSet;

public class WumpusWorld {

	private boolean[][] world;
	private Coordinate goldCoordinate;
	private Coordinate wumpusCoordinate;
	private Coordinate heroCoordinate;
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
	 * @return referencia a un objeto de tipo Set<Coordinate> que
	 * contiene todas las coordenadas del mundo Wumpus. Esto es que
	 * hace el producto cartesiano entre filas y columnas del mundo
	 * Wumpus.
	 */
	public Set<Coordinate> generateAllCoordinates() {
		Set<Coordinate> setCoordinates = new HashSet<>(); 

		for (int i = 0; i < world.length; i++) {

			for (int j = 0; j < world[0].length; j++) {
				setCoordinates.add(new Coordinate(i, j));
			}

		}

		return setCoordinates;
	}

	/*
	 * **************************************
	 * Metodos para las operaciones con filas
	 * **************************************
	 */

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return
	 */
	public Set<Coordinate> subtractRow(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.row = currentCoordinate.row - scalar;
		}

		return coordinates;
	}

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return
	 */
	public Set<Coordinate> multiplyRow(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.row = currentCoordinate.row * scalar;
		}

		return coordinates;
	}

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return
	 */
	public Set<Coordinate> divisionRow(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.row = currentCoordinate.row / scalar;
		}

		return coordinates;
	}

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene el
	 * valor correspondiente a la fila de los pares resultante
	 * de la suma entre la fila y un escalar
	 */
	public Set<Coordinate> addScalarToRow(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.row = currentCoordinate.row + scalar;
		}

		return coordinates;
	}

	/*
	 * *****************************************
	 * Metodos para las operaciones con columnas
	 * *****************************************
	 */

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return
	 */
	public Set<Coordinate> subtractColumn(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.column = currentCoordinate.column - scalar;
		}

		return coordinates;
	}

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return
	 */
	public Set<Coordinate> multiplyColumn(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.column = currentCoordinate.column * scalar;
		}

		return coordinates;
	}

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return
	 */
	public Set<Coordinate> divisionColumn(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.column = currentCoordinate.column / scalar;
		}

		return coordinates;
	}

	/**
	 * 
	 * @param scalar
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene el
	 * valor correspondiente a la fila de los pares resultante
	 * de la suma entre la fila y un escalar
	 */
	public Set<Coordinate> addScalarToColumn(int scalar, Set<Coordinate> coordinates) {

		for (Coordinate currentCoordinate : coordinates) {
			currentCoordinate.column = currentCoordinate.column + scalar;
		}

		return coordinates;
	}

	/**
	 * @param constant
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene
	 * todas las coordenadas en las que el valor correspondiente
	 * a la fila es igual a una constante dada
	 */
	public Set<Coordinate> rowEqualToConstant(int constant, Set<Coordinate> coordinates) {
		Set<Coordinate> resultSet = new HashSet<>();

		for (Coordinate currentCoordinate : coordinates) {

			if (currentCoordinate.row == constant) {
				resultSet.add(currentCoordinate);
			}
		}

		return resultSet;
	}

	/**
	 * @param constant
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene
	 * todas las coordenadas en las que el valor correspondiente
	 * a la fila es estrictamente menor a una constante dada
	 */
	public Set<Coordinate> rowLessThanConstant(int constant, Set<Coordinate> coordinates) {
		Set<Coordinate> resultSet = new HashSet<>();

		for (Coordinate currentCoordinate : coordinates) {

			if (currentCoordinate.row < constant) {
				resultSet.add(currentCoordinate);
			}
		}

		return resultSet;
	}

	/**
	 * @param constant
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene
	 * todas las coordenadas en las que el valor correspondiente
	 * a la fila es estrictamente mayor a una constante dada
	 */
	public Set<Coordinate> rowGraterThanConstant(int constant, Set<Coordinate> coordinates) {
		Set<Coordinate> resultSet = new HashSet<>();

		for (Coordinate currentCoordinate : coordinates) {

			if (currentCoordinate.row > constant) {
				resultSet.add(currentCoordinate);
			}
		}

		return resultSet;
	}

	/**
	 * @param constant
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene
	 * todas las coordenadas en las que el valor correspondiente
	 * a la fila es menor o igual a una constante dada
	 */
	public Set<Coordinate> rowLessThanOrEqualConstant(int constant, Set<Coordinate> coordinates) {
		Set<Coordinate> resultSet = new HashSet<>();

		for (Coordinate currentCoordinate : coordinates) {

			if (currentCoordinate.row <= constant) {
				resultSet.add(currentCoordinate);
			}
		}

		return resultSet;
	}

	/**
	 * @param constant
	 * @param coordinates
	 * @return referencia a un objeto de tipo Set que tiene
	 * todas las coordenadas en las que el valor correspondiente
	 * a la fila es mayor o igual a una constante dada
	 */
	public Set<Coordinate> rowGraterThanOrEqualConstant(int constant, Set<Coordinate> coordinates) {
		Set<Coordinate> resultSet = new HashSet<>();

		for (Coordinate currentCoordinate : coordinates) {

			if (currentCoordinate.row >= constant) {
				resultSet.add(currentCoordinate);
			}
		}

		return resultSet;
	}

	/**
	 * @param setOne
	 * @param setTwo
	 * @return referencia a un objeto de tipo Set que contiene
	 * los elementos de un conjunto y de otro conjunto
	 */
	public Set<Coordinate> joinSets(Set<Coordinate> setOne, Set<Coordinate> setTwo) {
		Set<Coordinate> unionSet = new HashSet<>();
		unionSet.addAll(setOne);
		unionSet.addAll(setTwo);

		return unionSet;
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

		if (element.equals(WUMPUS)) {
			putWumpus(givenCoordinate);
			return;
		}

		putHero(givenCoordinate);
	}

	/**
	 * Coloca un pozo en el mundo wumpus
	 * 
	 * @param element
	 * @param givenCoordinate
	 */
	public void putPit(String element, Coordinate givenCoordinate) {

		if (element.equals(PIT)) {
			putPit(givenCoordinate);
			return;
		}

	}

	/**
	 * Elimina un elemento del mundo wumpus
	 * 
	 * @param element
	 * @param givenCoordinate
	 */
	public void rem(String element, Coordinate givenCoordinate) {

		if (element.equals(GOLD)) {
			removeGold(givenCoordinate);
			return;
		}

		if (element.equals(PIT)) {
			removePit(givenCoordinate);
			return;
		}

		if (element.equals(WUMPUS)) {
			removeWumpus(givenCoordinate);
			return;
		}

		removeHero(givenCoordinate);
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
	 * Almacena la coordenada del heroe, si la coordenada
	 * pertenece al mundo Wumpus
	 * 
	 * @param givenCoordinate
	 */
	private void putHero(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		heroCoordinate = givenCoordinate;
	}

	// **********************************************************
	// Metodos para la eliminacion de elementos en el mundo Wumpus
	// **********************************************************

	/**
	 * Elimina la coordenada en la que se encuentra el oro en el
	 * mundo Wumpus, si la coordenada pertenece a dicho mundo
	 * 
	 * @param givenCoordinate
	 */
	private void removeGold(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		goldCoordinate.row = -1;
		goldCoordinate.column = -1;
	}

	/**
	 * Elimina un pozo en la coordenada dada del mundo Wumpus,
	 * si la coordenada pertenece a dicho mundo
	 * 
	 * @param givenCoordinate
	 */
	private void removePit(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		world[givenCoordinate.row][givenCoordinate.column] = false;
	}

	/**
	 * Elimina el wumpus del mundo, si la coordenada pertenece
	 * al mundo Wumpus
	 * 
	 * @param givenCoordinate
	 */
	private void removeWumpus(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		wumpusCoordinate.row = -1;
		wumpusCoordinate.column = -1;
	}

	/**
	 * Elimina el heroe del mundo, si la coordenada pertenece
	 * al mundo Wumpus
	 * 
	 * @param givenCoordinate
	 */
	private void removeHero(Coordinate givenCoordinate) {

		if (!boxExists(givenCoordinate)) {
			printNonexistentBoxMessage();
			return;
		}

		heroCoordinate.row = -1;
		heroCoordinate.column = -1;
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
