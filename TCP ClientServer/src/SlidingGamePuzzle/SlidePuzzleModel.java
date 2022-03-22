package SlidingGamePuzzle;

public class SlidePuzzleModel {
private static final int ROWS =3;
private static final int COLS =3;
private Tile[][] _contents;
private Tile     _emptyTile;
public SlidePuzzleModel(){
	_contents = new Tile[ROWS][COLS];
	reset();
}
String getFace(int row, int col){
	return _contents[row][col].getFace();
}
public void reset(){
	for(int r=0; r<ROWS; r++){
		for(int c=0; c<COLS; c++){
			_contents[r][c] = new Tile(r,c,""+ (r*COLS+c+1));
		}
	}
	_emptyTile = _contents[ROWS-1][COLS-1];
	_emptyTile.setFace(null);
	for(int r=0; r<ROWS; r++){
		for(int c=0; c<COLS; c++){
			exchangeTiles(r,c,(int)(Math.random()*ROWS) ,
							  (int)(Math.random()*COLS));
		}
	}
}
public boolean moveTile(int r, int c){
	return checkEmpty(r,c,-1,0) || checkEmpty(r,c,1,0) 
		|| checkEmpty(r,c,0,-1) || checkEmpty(r,c,0,1);
}
private boolean checkEmpty(int r, int c, int rdelta, int cdelta){
	int rNeighbor = r + rdelta;
	int cNeighbor = c + cdelta;
	if(isLegalRowCol(rNeighbor,cNeighbor) 
			&& _contents[rNeighbor][cNeighbor] == _emptyTile){
		exchangeTiles(r,c,rNeighbor,cNeighbor);
		return true;
	}
	return false;
}
public boolean isLegalRowCol(int r, int c){
	return r>=0 && r<ROWS && c>=0 && c<COLS;
}
private void exchangeTiles(int r1, int c1, int r2, int c2){
	Tile temp = _contents[r1][c1];
	_contents[r1][c1] = _contents[r2][c2];
	_contents[r2][c2] = temp;
}
@SuppressWarnings("unused")
public boolean isGameOver(){
	for(int r=0;r<ROWS;r++){
		for(int c=0;c<ROWS;c++){
			Tile trc = _contents[r][c];
			return trc.isFineLocation(r,c);
		}
	}
	return true;
}
}
class Tile{
	private int _row;
	private int _col;
	private String _face;
	public Tile(int row,int col,String face){
		_row = row;
		_col = col;
		_face = face;
	}
	public void setFace(String newFace){
		_face = newFace;
	}
	public String getFace(){
		return _face;
	}
	public boolean isFineLocation(int r, int c){
		return r==_row && c==_col;
	}
}

