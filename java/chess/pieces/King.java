package chess.pieces;

import chess.Color;
import chess.Coordinate;
import chess.Pieces;

import java.util.Collections;
import java.util.List;

/**
 * @author pushpanjay.kumar created on 13/3/20
 */
public class King extends Piece {
    private boolean isMovedOnce;

    int [] allowedRows = new int[]{1, -1, 0, 0, -1, -1, 1, 1};
    int [] allowedColumns = new int[]{0, 0, 1, -1, -1, 1, -1, 1};

    public King(Pieces pieceName, Color color,int x, int y) {
        super(pieceName, color, x, y);
        this.isMovedOnce = false;
    }

    @Override
    public List<String> isValidMove(Coordinate destPos) {
        return Collections.singletonList(String.valueOf(isReachableInOneStep(allowedRows, allowedColumns, destPos)));
    }

    @Override
    public boolean isSpecialMoveCandidate(Coordinate destPos) {
        return !isMovedOnce;
    }
}
