// this class will keep track of the configuration of queens, I may not have needed an extra class for
// this but that's okay.
public class Board
{
    // maybe not efficient to keep track of the queens in two different
    public Queen[] list = new Queen[8];
    private int h;
    public Board successors[] = new Board[56];
    public Board(){
        list[0] = new Queen(0,0);
        list[1] = new Queen(0,1);
        list[2] = new Queen(0,2);
        list[3] = new Queen(0,3);
        list[4] = new Queen(0,4);
        list[5] = new Queen(0,5);
        list[6] = new Queen(0,6);
        list[7] = new Queen(0,7);
        h();
    }

    // copy another board to a new board
    public Board(Board baseboard){
        for (int i=0; i<8; i++){
            list[i] = new Queen(baseboard.list[i].row, i);
        } 
        h();
    }

    // generate all successors for this board
    public void generateSuccessors(){
        int count = 0;
        // for each of the 8 queens
        for (int i=0; i<8; i++){
            // move them down a place on the board, one at a time
            for(int j=1; j<=7; j++){
                successors[count] = new Board(this); // new board identical to this one
                successors[count].list[i].down(j);   // move a queen down j spaces
                successors[count].h();               // calculate the h value for this board
                count++;
            }
        }
    }


    // calculate the h-value for the current board.
    // this heuristic is # pairs of queens that can attack each other
    // for the least optimal board it should return 8C2 = 28
    // for the optimal board it should return 0 
    public void h(){
        int h_val = 0;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                // I probably don't need to check if Queens can attack themselves.
                // We'll assume these Queens are non self-harming.
                if (i != j){
                    if (list[i].canAttack(list[j])){
                        h_val++;
                    }
                }
            }
        }
        h = h_val / 2;
    }

    // now introducing a way to print a chess-board with only 512 iterations.
    public void print(){
        for(int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                boolean queen = false;
                for (int k=0; k<8; k++){
                    if (list[k].row == i && list[k].col == j)
                        queen = true;
                }
                if (queen)
                    System.out.print("[Q]");
                else
                    System.out.print("[ ]");
            }
            System.out.println("");
        }
            System.out.println("");
            System.out.println("-------------------------");
    }
}
