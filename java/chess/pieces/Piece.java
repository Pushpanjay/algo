package chess.pieces;

import chess.Color;
import chess.Coordinate;
import chess.PieceState;
import chess.Pieces;

import java.util.List;
import java.util.UUID;

/**
 * @author pushpanjay.kumar created on 13/3/20
 */
public abstract class Piece {
    protected long pieceId;
    protected Pieces pieceName;
    protected Color color;
    protected int playerNum;
    protected PieceState status;
    protected int xCoordinate;
    protected int yCoordinate;
    protected boolean isMovedOnce;

    public Piece(Pieces pieceName, Color color, int x, int y) {
        this.pieceId = UUID.randomUUID().getLeastSignificantBits();
        this.pieceName = pieceName;
        this.color = color;
        this.status = PieceState.ALIVE;
        playerNum = color.equals(Color.WHITE) ? 1 : 2;
        this.xCoordinate = x;
        this.yCoordinate = y;
        isMovedOnce = false;
    }

    public long getPieceId() {
        return pieceId;
    }

    public void setPieceId(long pieceId) {
        this.pieceId = pieceId;
    }

    public Pieces getPieceName() {
        return pieceName;
    }

    public void setPieceName(Pieces pieceName) {
        this.pieceName = pieceName;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PieceState getStatus() {
        return status;
    }

    public void setStatus(PieceState status) {
        this.status = status;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setMovedOnce(boolean movedOnce) {
        isMovedOnce = movedOnce;
    }

    public void setCoordinates(int x, int y){
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public Coordinate getCorrdinates(){
        return new Coordinate(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Do destination falls on the path of Piece
     *
     * @param destPos
     * @return
     */
    public abstract List<String> isValidMove(Coordinate destPos);

    public abstract boolean isSpecialMoveCandidate(Coordinate destPos);

    public boolean isReachableInOneStep(int[] allowedRows, int[] allowedColumns, Coordinate destPos) {
        for (int i = 0; i < allowedRows.length; i++) {
            int x = this.xCoordinate + allowedRows[i];
            int y = this.yCoordinate + allowedColumns[i];
            if (destPos.getX() == x && destPos.getY() == y) {
                return true;
            }
        }
        return false;
    }
}
