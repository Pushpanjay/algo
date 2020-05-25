package chess.pieces;

import chess.Color;
import chess.Coordinate;
import chess.Pieces;

import java.util.Collections;
import java.util.List;

/**
 * @author pushpanjay.kumar created on 14/3/20
 */
public class Bishop extends Piece {
    public Bishop(Pieces pieceName, Color color,int x, int y) {
        super(pieceName, color, x, y);
    }

    @Override
    public List<String> isValidMove(Coordinate destPos) {
        return Collections.singletonList("DiagonalScan");
    }

    @Override
    public boolean isSpecialMoveCandidate(Coordinate destPos) {
        return false;
    }
}
