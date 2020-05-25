package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author pushpanjay.kumar created on 16/3/20
 */
public class Game {
    private Player p1;
    private Player p2;
    private Board board;
    private boolean isPlayer1Turn;

    public Game(Player p1, Player p2, Board board) {
        isPlayer1Turn = true;
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
    }

    private void begin() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter move in following format: x1 y1 x2 y2");
        board.disp();
        while(true){
            if(this.isPlayer1Turn){
                System.out.println("Player1 >");
                this.isPlayer1Turn=false;
            } else{
                System.out.println("Player2 >");
                this.isPlayer1Turn=true;
            }
            String[] input = br.readLine().trim().split(" ");
            if(input.length!=4){
                System.out.println("Invalid Input");
                continue;
            }
            this.board.move(new Coordinate(convertToInt(input[0]), convertToInt(input[1])), new Coordinate(convertToInt(input[2]), convertToInt(input[3])));
            board.disp();
        }
    }

    private int convertToInt(String s){
        return Integer.valueOf(s);
    }

    public static void main(String[] args) {
        Board board = new Board();
        Game game = new Game(new Player("player1", Color.WHITE), new Player("player2", Color.BLACK), board);
        try {
            game.begin();
        } catch (Exception e){
            System.out.println("Message: " + e.getMessage());
            System.out.println(e);
            System.out.println(e.getStackTrace());
        }
    }
}
