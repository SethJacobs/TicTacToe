public class Board {
    String[][] backingBoard = new String[3][3];

    public Board(){
        for(int i = 0; i < this.backingBoard.length; i++){
            for(int j = 0; j < this.backingBoard[i].length; j++){
                this.backingBoard[i][j] = "-";
            }
        }
    }

    public String getSpot(int i, int j){
        return backingBoard[i][j];
    }

    public void setSpot(int i, int j, Player player){
        backingBoard[i][j] = player.getLetter();
    }

    public void printBoard() {
        System.out.println("     0      1      2   ");
        System.out.println("-----------------------");
        for (int i = 0; i < backingBoard.length; i++) {
            System.out.println(i + " |  " + backingBoard[i][0] + "  |" + "|  " + backingBoard[i][1] + "  |" + "|  " + backingBoard[i][2] + "  |");
        }
        System.out.println("-----------------------");
    }

    public boolean isValidSpot(int i, int j){
        if(this.backingBoard[i][j].equals("-")){
            return true;
        }
        else
        {
            return false;
        }
    }

}
