package org.unp.plp.interprete;

public class WumpusWorld {

	String[][] world;

	public void setSize(int i, int j) {
		world = new String[i][j];
	}

	public int size() {
		return (world.length * world[0].length);
	}

}
