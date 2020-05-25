package chess.pieces;

import chess.Color;
import chess.Coordinate;
import chess.Pieces;

import java.util.Arrays;
import java.util.List;

/**
 * @author pushpanjay.kumar created on 13/3/20
 */
public class Queen extends Piece {

    public Queen(Pieces pieceName, Color color,int x, int y) {
        super(pieceName, color, x, y);
    }

    @Override
    public List<String> isValidMove(Coordinate destPos) {
        return Arrays.asList("InLineScan", "DiagonalScan");
    }

    @Override
    public boolean isSpecialMoveCandidate(Coordinate destPos) {
        return false;
    }
}
