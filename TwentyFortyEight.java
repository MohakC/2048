import java.util.Random;
import java.util.Scanner;

public class TwentyFortyEight {
    private int[][] board;
    private int score;
    static int boardWidth;

    public TwentyFortyEight(int boardWidth){
        board = new int [boardWidth][boardWidth];
        score = 0;
        this.boardWidth = boardWidth;
        placeRandom();
    }

    //This function resets the board to its initial state
    public void reset() {
        score = 0;
        for (int i = 0;i<board.length;i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
        placeRandom();
    }

    //This function returns the total number of blank spaces on the board
    public int numBlanks() {
        int blankspaces=0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    blankspaces++;
                }
            }
        }
        return blankspaces;
    }

    //This function places a 2 at a random blank space on the board
    public void placeRandom(){
        boolean end = false;
        if (numBlanks() == 0) {

        } else {
            while (end != true) {
                int random1 = (int) (Math.random() * 10);
                int random2 = (int) (Math.random() * 10);
                if (random1 < boardWidth && random2 < boardWidth && board[random1][random2] ==0) {
                    board[random1][random2] = 2;
                    end = true;
                } else {
                    end = false;
                }
            }
        }
    }

    //This function attempts to move a cell at some coordinates
    public boolean moveTo(int fromRow, int fromCol, int toRow, int toCol) {
        boolean change = false;
        if (fromRow>=boardWidth||fromCol>=boardWidth||toRow>=boardWidth||toCol>=boardWidth){
            change = false;
        }
        else if (fromRow<0||fromCol<0||toRow<0||toCol<0){
            change = false;
        }
        else if (fromRow-toRow<=-2||fromRow-toRow>=2||fromCol-toCol<=-2||fromCol-toCol>=2){
            change = false;
        }
        else if (board[fromRow][fromCol]==0){
            change = false;
        }
        else if (board[fromRow][fromCol]==board[toRow][toCol]){
            board[toRow][toCol]=(board[toRow][toCol])*2;
            board[fromRow][fromCol]=0;
            change = true;
        }
        else if (board[toRow][toCol]==0){
            board[toRow][toCol]=board[fromRow][fromCol];
            board[fromRow][fromCol]=0;
            change = true;
        }
        else {
            change = false;
        }
        return change;
    }

    //The following four functions move the board in a single direction
    public boolean moveUp(){
        boolean change = false;
        for (int i = 0;i<boardWidth;i++){
            for (int j = 0;j<boardWidth;j++){
                if (moveTo(i,j,i-1,j)){
                    change = true;
                }
            }
        }
        for (int i = 0;i<boardWidth;i++){
            for (int j = 0;j<boardWidth;j++){
                if (board[i][j]>score){
                    score = board[i][j];
                }
            }
        }
        return change;
    }

    public boolean moveDown() {
        boolean change = false;
        for (int i = boardWidth-1;i>=0;i--){
            for (int j = 0;j<boardWidth;j++){
                if (moveTo(i,j,i+1,j)){
                    change = true;
                }
            }
        }
        for (int i = 0;i<boardWidth;i++){
            for (int j = 0;j<boardWidth;j++){
                if (board[i][j]>score){
                    score = board[i][j];
                }
            }
        }
        return change;
    }

    public boolean moveRight() {
        boolean change = false;
        for (int i = 0;i<boardWidth;i++){
            for (int j = boardWidth-1;j>=0;j--){
                if (moveTo(i,j,i,j+1)){
                    change = true;
                }
            }
        }
        for (int i = 0;i<boardWidth;i++){
            for (int j = 0;j<boardWidth;j++){
                if (board[i][j]>score){
                    score = board[i][j];
                }
            }
        }
        return change;
    }

    public boolean moveLeft() {
        boolean change = false;
        for (int i = 0;i<boardWidth;i++){
            for (int j = 0;j<boardWidth;j++){
                if (moveTo(i,j,i,j-1)){
                    change = true;
                }
            }
        }
        for (int i = 0;i<boardWidth;i++){
            for (int j = 0;j<boardWidth;j++){
                if (board[i][j]>score){
                    score = board[i][j];
                }
            }
        }
        return change;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    public void showBoard() {
        for(int x = 0; x < boardWidth * 6 + 1; x++){
            System.out.print("-");
        }
        System.out.println();

        for(int x = 0; x < boardWidth; x++) {
            for(int y = 0; y < boardWidth; y++) {
                String square = (board[x][y] == 0)? "":board[x][y] + "";
                System.out.printf("|%5s", square);
            }
            System.out.println("|");

            for(int y = 0; y < boardWidth * 6 + 1; y++){
                System.out.print("-");
            }
            System.out.println();
        }
    }

    public int getScore() {
        return score;
    }

    // Main method creates the visible board
    public static void main(String args[]) {
        TwentyFortyEight tfe = new TwentyFortyEight(6);
        Scanner s = new Scanner(System.in);
        int bestScore = 0;
        boolean resetFlag = false;

        while(true) {
            System.out.println("Current score: " + tfe.getScore() + " | Best score: " + bestScore);
            tfe.showBoard();

            System.out.println("Enter up, down, left or right to move in that direction.");
            System.out.println("Enter reset to reset the game or quit to exit.");
            String line = s.nextLine();
            line = line.toLowerCase();

            switch(line){
                case "up":
                    while(tfe.moveUp()){

                    }
                    break;
                case "down":
                    while(tfe.moveDown()){

                    }
                    break;
                case "left":
                    while(tfe.moveLeft()){

                    }
                    break;
                case "right":
                    while(tfe.moveRight()){

                    }
                    break;
                case "reset":
                    tfe.reset();
                    resetFlag = true;
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Invalid move, Please try again!!!!\n");
                    continue;

            }

            bestScore = Math.max(bestScore, tfe.getScore());
            if(!resetFlag) {
                tfe.placeRandom();
                resetFlag = false;
            }
        }
    }
}
