package chess;

/**
 * @author pushpanjay.kumar created on 13/3/20
 */
public abstract class Piece {
    private int pieceId;
    private String pieceName;

    abstract boolean isValidMove();
}
