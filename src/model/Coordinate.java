package model;

public class Coordinate {
	public int row,col;
	
	public Coordinate(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public Coordinate(Coordinate coordinate) {
		this.row = coordinate.row;
		this.col = coordinate.col;
	}

	@Override
	public String toString(){
		return "Row: " + row + " Col: " + col;
		}

	public void set(Coordinate coordinate) {
		row = coordinate.row;
		col = coordinate.col;
		
	}
	public Coordinate clone() {
		return new Coordinate(this.row,this.col);
	}
}
