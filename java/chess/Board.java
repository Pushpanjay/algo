package chess;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;

/**
 * @author pushpanjay.kumar created on 13/3/20
 */
public class Board {
    private long boardId;
    private final int row = 8;
    private final int column = 8;
    private Piece[][]cells;
    private List<Piece> diedPieces;
    private Stack<Move> moves;

    private Map<Color, Piece> kingPiece;

    private Map<String,ValidateMove> functionMap;

    public Board() {
        boardId = UUID.randomUUID().getLeastSignificantBits();
        diedPieces = new ArrayList<>();
        kingPiece = new HashMap<>();
        resetBoard();
    }

    //todo: @PostConstruct not working look for alternatives
    public void initializeMap(){
        functionMap = new HashMap<>();
        functionMap.put("InLineScan", (this::isReachableStraight));
        functionMap.put("DiagonalScan", (this::isReachableDigonally));
        functionMap.put("true", (x, y)->true);
        functionMap.put("false", (x, y)->false);
        functionMap.put("killMoveCheck", this::checkKillMoves);
    }

    public void move(Coordinate srcPos, Coordinate destPos){
        initializeMap();
        Coordinate blocker = new Coordinate();
        if(isDestSame(srcPos, destPos) || !isSrcValid(srcPos) || !isDestValid(srcPos, destPos)){
            throw new RuntimeException("Invalid move coordinates");
        }
        Piece srcPiece = cells[srcPos.getX()][srcPos.getY()];
        List<String> validMoves = srcPiece.isValidMove(destPos);

        if(validMoves.stream().anyMatch(v->functionMap.get(v).validate(srcPos, destPos))){
            if(srcPiece.isSpecialMoveCandidate(destPos) && isSpecialMove(srcPos, destPos)){
                performSpecialMove(srcPos, destPos);
                checkKing(srcPiece);
                return;
            }
            moveSrcPieceToDest(srcPos, destPos);
            checkKing(srcPiece);
        } else{
            System.out.println("Not a valid move");
        }
    }

    private boolean checkKing(Piece srcPiece){
        Coordinate blocker = new Coordinate();
        if(!isKingSafe(srcPiece.getColor(), blocker)){
            System.out.println("King has check from {" + blocker.getX() +", "+ blocker.getY() + "}" + ", can't perform provided move");
            //todo: undo logic
            return false;
        }
        Color opponentColor = srcPiece.getColor()==Color.WHITE?Color.BLACK:Color.WHITE;
        if(!isKingSafe(opponentColor, blocker)){
            System.out.println("Opponent have check from {" + blocker.getX() +", "+ blocker.getY() + "}");
        }
        return true;
    }

    private void undo(){

    }

    private boolean isKingSafe(Color color, Coordinate blocker){
        Coordinate kingCoordinate = kingPiece.get(color).getCorrdinates();
        if(isKingSafeInLine(kingCoordinate, blocker, true) && isKingSafeInLine(kingCoordinate, blocker, false) && isKingSafeDiagonally(kingCoordinate, blocker, true) && isKingSafeDiagonally(kingCoordinate, blocker, false)){
            return true;
        }
        return false;
    }

    private boolean isKingSafeInLine(Coordinate kingCoordinate, Coordinate blocker, boolean horizontal){
        int j=horizontal?kingCoordinate.getY()-1:kingCoordinate.getX()-1;
        while(j>=0){
            Piece srcPiece = horizontal?cells[kingCoordinate.getX()][j]:cells[j][kingCoordinate.getY()];
            if(srcPiece!=null){
                if(srcPiece.getColor() == cells[kingCoordinate.getX()][kingCoordinate.getY()].getColor()){
                    return true;
                }
                Coordinate srcPos = new Coordinate(srcPiece.getxCoordinate(), srcPiece.getyCoordinate());
                if(!isSafe(srcPos, kingCoordinate, blocker)){
                    return false;
                }
                break;
            }
            j--;
        }
        j=horizontal?kingCoordinate.getY()+1:kingCoordinate.getX()+1;
        while(j<=7){
            Piece srcPiece = horizontal?cells[kingCoordinate.getX()][j]:cells[j][kingCoordinate.getY()];
            if(srcPiece!=null){
                if(srcPiece.getColor() == cells[kingCoordinate.getX()][kingCoordinate.getY()].getColor()){
                    return true;
                }
                Coordinate srcPos = new Coordinate(srcPiece.getxCoordinate(), srcPiece.getyCoordinate());
                if(!isSafe(srcPos, kingCoordinate, blocker)){
                    return false;
                }
                break;
            }
            j++;
        }
        return true;
    }

    private boolean isKingSafeDiagonally(Coordinate kingCoordinate, Coordinate blocker, boolean isLeadDiagonal){
        int j = isLeadDiagonal?1:-1;
        int x = kingCoordinate.getX()+1;
        int y = kingCoordinate.getY()+j;
        while(x<=7 && (y>=0 && y<=7)){
            if(cells[x][y]!=null){
                if(cells[x][y].getColor() == cells[kingCoordinate.getX()][kingCoordinate.getY()].getColor()){
                    return true;
                }
                Coordinate srcPos = new Coordinate(x, y);
                if(!isSafe(srcPos, kingCoordinate, blocker)){
                    return false;
                }
                break;
            }
            x++;
            y+=j;
        }
        j = isLeadDiagonal?-1:1;
        x = kingCoordinate.getX()-1;
        y = kingCoordinate.getY()+j;
        while(x>=0 && (y>=0 && y<=7)){
            if(cells[x][y]!=null){
                if(cells[x][y].getColor() == cells[kingCoordinate.getX()][kingCoordinate.getY()].getColor()){
                    return true;
                }
                Coordinate srcPos = new Coordinate(x, y);
                if(!isSafe(srcPos, kingCoordinate, blocker)){
                    return false;
                }
                break;
            }
            x--;
            y+=j;
        }
        return true;
    }

    private boolean isSafe(Coordinate srcPos, Coordinate destPos, Coordinate blocker){
        List<String> validMoves = cells[srcPos.getX()][srcPos.getY()].isValidMove(destPos);
        if(validMoves.stream().anyMatch(v->functionMap.get(v).validate(srcPos, destPos))){
            blocker.setCoordinate(srcPos.getX(), srcPos.getY());
            return false;
        }
        return true;
    }

    private boolean isSrcValid(Coordinate srcPos){
        return isInRange(srcPos) && cells[srcPos.getX()][srcPos.getY()]!=null;
    }

    private boolean isDestValid(Coordinate srcPos, Coordinate destPos){
        Piece srcPiece = cells[srcPos.getX()][srcPos.getY()];
        if(isInRange(destPos)){
            Piece destPiece = cells[destPos.getX()][destPos.getY()];
            if(destPiece == null || !destPiece.getColor().equals(srcPiece.getColor())){
                return true;
            }
        }
        return false;
    }

    private boolean isInRange(Coordinate pos){
        return pos.getX()>=0 && pos.getY()>=0 && pos.getX()<row && pos.getY()<column;
    }

    private void resetBoard(){
        cells = new Piece[row][column];
        setPiecesAtDefaultLocation();
    }

    private void setPiecesAtDefaultLocation(){
        cells[0][3] = new King(Pieces.KING, Color.WHITE, 0, 3);
        kingPiece.put(Color.WHITE, cells[0][3]);
        cells[7][3] = new King(Pieces.KING, Color.BLACK, 7, 3);
        kingPiece.put(Color.BLACK, cells[7][3]);
        cells[0][4] = new Queen(Pieces.QUEEN, Color.WHITE, 0, 4);
        cells[7][4] = new Queen(Pieces.QUEEN, Color.BLACK, 7, 4);
        cells[0][2] = new Bishop(Pieces.BISHOP, Color.WHITE,0,2);
        cells[0][5] = new Bishop(Pieces.BISHOP, Color.WHITE, 0, 5);
        cells[7][2] = new Bishop(Pieces.BISHOP, Color.BLACK, 7, 2);
        cells[7][5] = new Bishop(Pieces.BISHOP, Color.BLACK, 7, 5);
        cells[0][1] = new Knight(Pieces.KNIGHT, Color.WHITE, 0, 1);
        cells[0][6] = new Knight(Pieces.KNIGHT, Color.WHITE, 0, 6);
        cells[7][1] = new Knight(Pieces.KNIGHT, Color.BLACK, 7, 1);
        cells[7][6] = new Knight(Pieces.KNIGHT, Color.BLACK, 7, 6);
        cells[0][0] = new Rook(Pieces.ROOK, Color.WHITE, 0, 0);
        cells[0][7] = new Rook(Pieces.ROOK, Color.WHITE, 0, 7);
        cells[7][0] = new Rook(Pieces.ROOK, Color.BLACK, 7, 0);
        cells[7][7] = new Rook(Pieces.ROOK, Color.BLACK, 7, 7);

        for(int j=0;j<column;j++){
            cells[1][j] = new Pawn(Pieces.PAWN, Color.WHITE, 1, j);
        }

        for(int j=0;j<column;j++){
            cells[6][j] = new Pawn(Pieces.PAWN, Color.BLACK, 6, j);
        }
    }

    private boolean isReachableStraight(Coordinate srcPos, Coordinate destPos){
        return isHorizontallyReachable(srcPos, destPos) || isVerticallyReachable(srcPos, destPos);
    }

    private boolean isReachableDigonally(Coordinate srcPos, Coordinate destPos){
        return isReachableLeadingDiagonally(srcPos, destPos) || isReachableCounterDiagonally(srcPos, destPos);
    }

    private boolean isHorizontallyReachable(Coordinate srcPos, Coordinate destPos){
        if(srcPos.getX() == destPos.getX()){
            if(srcPos.getY()<destPos.getY()) {
                return isReachableInLine(srcPos.getY()+1, destPos.getY(), srcPos.getX(),true,true);
            } else {
                return isReachableInLine(srcPos.getY()-1, destPos.getY(), srcPos.getX(),false,true);
            }
        }
        return false;
    }

    private boolean isVerticallyReachable(Coordinate srcPos, Coordinate destPos){
        if(srcPos.getY() == destPos.getY()){
            if(srcPos.getX()<destPos.getX()){
                return isReachableInLine(srcPos.getX()+1, destPos.getX(), srcPos.getY(), true, false);
            } else{
                return isReachableInLine(srcPos.getX()-1, destPos.getX(), srcPos.getY(), false, false);
            }
        }
        return false;
    }

    private boolean isReachableInLine(int srcIndex, int targetIndex, int fixedValue, boolean lowtoHigh, boolean isHorizontalCheck){
        if(lowtoHigh) {
            while(srcIndex < targetIndex){
                Piece piece = isHorizontalCheck?cells[fixedValue][srcIndex]:cells[srcIndex][fixedValue];
                if(piece!=null){
                    return false;
                }
                srcIndex++;
            }
            return true;
        } else {
            while(srcIndex>targetIndex){
                Piece piece = isHorizontalCheck?cells[fixedValue][targetIndex]:cells[targetIndex][fixedValue];
                if(piece!=null){
                    return false;
                }
                srcIndex--;
            }
            return true;
        }
    }

    private boolean isReachableLeadingDiagonally(Coordinate srcPos, Coordinate destPos) {
        if(getDiff(destPos.getX(), srcPos.getX()) == getDiff(destPos.getY(), srcPos.getY())){
            if(srcPos.getX()<destPos.getX()){
                return isReachableDiagonally(srcPos, destPos,true,true);
            } else{
                return isReachableDiagonally(srcPos, destPos,false,true);
            }
        }
        return false;
    }

    private boolean isReachableCounterDiagonally(Coordinate srcPos, Coordinate destPos) {
        if(Math.abs(getDiff(destPos.getX(), srcPos.getX())) == Math.abs(getDiff(destPos.getY(), srcPos.getY()))){
            if(srcPos.getX()<destPos.getX()){
                return isReachableDiagonally(srcPos, destPos,true,false);
            } else{
                return isReachableDiagonally(srcPos, destPos,false,false);
            }
        }
        return false;
    }

    private boolean isReachableDiagonally(Coordinate srcPos, Coordinate destPos, boolean lowtoHigh, boolean isLeadingDiagonal){
        int i=lowtoHigh?1:-1;
        int j=lowtoHigh?(isLeadingDiagonal?1:-1):(isLeadingDiagonal?-1:1);
        int x = srcPos.getX()+i;
        int y = srcPos.getY()+j;

        while (lowtoHigh?x<destPos.getX():x>destPos.getX()){
            if(cells[x][y]!=null){
                return false;
            }
            x+=i;
            y+=j;
        }
        return y == destPos.getY();
    }

    private int getDiff(Integer a, Integer b){
        return a-b;
    }

    private boolean checkKillMoves(Coordinate srcPos, Coordinate destPos){
        Piece srcPiece = cells[srcPos.getX()][srcPos.getY()];
        int [] killMoveRows = ((Pawn) srcPiece).getKillMoveRows();
        int [] killMoveColumns = ((Pawn) srcPiece).getKillMoveColumns();
        if(srcPiece.isReachableInOneStep(killMoveRows, killMoveColumns, destPos)){
            if(cells[destPos.getX()][destPos.getY()]!=null){
                return true;
            }
        }
        return false;
    }

    private boolean isDestSame(Coordinate srcPos, Coordinate destPos){
        return srcPos.getX() == destPos.getX() && srcPos.getY() == destPos.getY();
    }

    private void moveSrcPieceToDest(Coordinate srcPos, Coordinate destPos) {
        Piece srcPiece = cells[srcPos.getX()][srcPos.getY()];
        Piece destPiece = cells[destPos.getX()][destPos.getY()];

        if(destPiece != null) {
            destPiece.setStatus(PieceState.KILLED);
            diedPieces.add(destPiece);
        }
        srcPiece.setCoordinates(destPos.getX(), destPos.getY());
        srcPiece.setMovedOnce(true);
        if(srcPiece.getPieceName()==Pieces.KING){
            kingPiece.put(srcPiece.getColor(), srcPiece);
        }
        cells[destPos.getX()][destPos.getY()] = srcPiece;
        cells[srcPos.getX()][srcPos.getY()] = null;
    }

    private boolean isSpecialMove(Coordinate srcPos, Coordinate destPos){
        switch (cells[srcPos.getX()][srcPos.getY()].getPieceName()){
            case KING:
                if(cells[srcPos.getX()][srcPos.getY()-1] == null && cells[srcPos.getX()][srcPos.getY()-2] == null && cells[srcPos.getX()][srcPos.getY()-3] != null){
                    return true;
                }
                break;
            default: return true;
        }
        return false;
    }

    private void performSpecialMove(Coordinate srcPos, Coordinate destPos){
        Piece srcPiece = cells[srcPos.getX()][srcPos.getY()];
        switch (srcPiece.getPieceName()){
            case KING:
                moveSrcPieceToDest(srcPos, destPos);
                moveSrcPieceToDest(new Coordinate(srcPos.getX(), srcPos.getY()-3), new Coordinate(srcPos.getX(), srcPos.getY()-1));
                break;
            case PAWN:
                cells[destPos.getX()][destPos.getY()] = new Queen(Pieces.QUEEN, srcPiece.getColor(), destPos.getX(), destPos.getY());
                break;
            default: break;
        }
    }

    public void disp(){
        System.out.println();
        System.out.print("  |");

        for(int i=0;i<8;i++){
            System.out.print("  "+i+"   |");
        }
        System.out.println();
        for(int p1=0;p1<58;p1++){
            System.out.print("-");
        }
        System.out.println();

        for(int i=0;i<row;i++){
            System.out.print(i + " | ");
            for(int j=0;j<column;j++){
                Piece p = cells[i][j];
                if(p == null){
                    System.out.print("     | ");
                } else{
                    System.out.print(p.getPieceName().name().substring(0, 2) +"_"+ p.getColor().name().substring(0,1)  + " | ");
                }
            }

            System.out.println();
            for(int p2=0;p2<58;p2++){
                System.out.print("-");
            }
            System.out.println();
        }
        System.out.print("Killed Pieces: ");
        diedPieces.forEach(p-> System.out.print(p.getPieceName().name().substring(0, 2) +"_"+ p.getColor().name().substring(0,1)  + " |"));
        System.out.println();
    }
}
