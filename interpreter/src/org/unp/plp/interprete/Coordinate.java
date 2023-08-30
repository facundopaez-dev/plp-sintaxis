package org.unp.plp.interprete;

import java.util.Objects;

public class Coordinate {

    public int row;
    public int column;
    public int value;

    public Coordinate() {
        row = -1;
        column = -1;
        value = -1;
    }

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public String toString() {
        return "[" + row + ", " + column + "]";
    }

	@Override
	public int hashCode() {
		return Objects.hash(column, row);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return column == other.column && row == other.row;
	}

}