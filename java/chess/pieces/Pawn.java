package chess.pieces;

import chess.Color;
import chess.Coordinate;
import chess.Pieces;

import java.util.Collections;
import java.util.List;

/**
 * @author pushpanjay.kumar created on 14/3/20
 */
public class Pawn extends Piece{
    int [][] rows = new int[][]{{1}, {-1}};
    int [][] columns = new int[][]{{0}, {0}};

    int [][] firstMoveRows = new int[][]{{1, 2}, {-2, -1}};
    int [][] firstMoveColumns = new int[][]{{0, 0}, {0, 0}};

    int [][] killMoveRows = new int[][]{{1, 1}, {-1, -1}};
    int [][] killMoveColumns = new int[][]{{-1, 1}, {-1, 1}};

    public Pawn(Pieces pieceName, Color color,int x, int y) {
        super(pieceName, color, x, y);
    }

    public int[] getKillMoveRows() {
        return killMoveRows[this.playerNum-1];
    }

    public int[] getKillMoveColumns() {
        return killMoveColumns[this.playerNum-1];
    }

    @Override
    public List<String> isValidMove(Coordinate destPos) {
        if(!isMovedOnce){
            return Collections.singletonList(String.valueOf(isReachableInOneStep(firstMoveRows[playerNum-1], firstMoveColumns[playerNum-1], destPos)));
        }
        return Collections.singletonList(isReachableInOneStep(rows[playerNum-1], columns[playerNum-1], destPos)?"true":"killMoveCheck");
    }

    @Override
    public boolean isSpecialMoveCandidate(Coordinate destPos) {
        if(destPos.getX()==7 || destPos.getX() == 0){
            return true;
        }
        return false;
    }
}
