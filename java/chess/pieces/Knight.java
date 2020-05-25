package chess.pieces;

import chess.Color;
import chess.Coordinate;
import chess.Pieces;

import java.util.Collections;
import java.util.List;

/**
 * @author pushpanjay.kumar created on 14/3/20
 */
public class Knight extends Piece {
    int [] allowedRows = new int[]{-2, -2, 2, 2, -1, 1, -1, 1};
    int [] allowedColumns = new int[]{-1, 1, -1, -1, -2, -2, 2, 2};

    public Knight(Pieces pieceName, Color color,int x, int y) {
        super(pieceName, color, x, y);
    }

    @Override
    public List<String> isValidMove(Coordinate destPos) {
        return Collections.singletonList(String.valueOf(isReachableInOneStep(allowedRows, allowedColumns, destPos)));
    }

    @Override
    public boolean isSpecialMoveCandidate(Coordinate destPos) {
        return false;
    }
}
