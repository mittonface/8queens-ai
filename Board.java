import java.util.Random;
// this class will keep track of the configuration of queens, I may not have needed an extra class for
// this but that's okay.
public class Board implements Comparable<Board>
{
    public Queen[] list = new Queen[8];
    private int h;
    public Board successors[] = new Board[56];


    // generate a random (well...not random columns) layout of 8 queens
    public Board(){
        list[0] = new Queen(randInt(0,7),0);
        list[1] = new Queen(randInt(0,7),1);
        list[2] = new Queen(randInt(0,7),2);
        list[3] = new Queen(randInt(0,7),3);
        list[4] = new Queen(randInt(0,7),4);
        list[5] = new Queen(randInt(0,7),5);
        list[6] = new Queen(randInt(0,7),6);
        list[7] = new Queen(randInt(0,7),7);

        h();

    }

    // create another board that is an identical copy of the current board.
    // used in state generation. since neighbour states only have a 1-move difference
    public Board(Board baseboard){
        for (int i=0; i<8; i++){
            list[i] = new Queen(baseboard.list[i].row, i);
        } 
        h();
    }

    // generate all successors for this board. A successor state is one where one of the queens
    // in one of the columns has moved down X number of spaces
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

    // getter for the H value. 
    public int getH(){
        h();
        return h;
    }

    // comparison based on the h value, this should be able to be used in the actual 
    // algorithm.
    public int compareTo(Board b2){
        if(h < b2.getH()){
            return -1;
        }else if (h > b2.getH()){
            return 1;
        }else{
            return 0;
        }
    }

    // return a random neighbour for this board. I don't need to generate all
    // neighbours for simulated annealing, so this function can save me some
    // cycles.
    public Board randomNeighbour(){
        // pick a random queen to move.
        int queen_num = randInt(0,7);

        // choose a random amount to move the queen down.
        int move_distance = randInt(1,7);

        // create the new state and move the queen
        Board b = new Board(this);
        b.list[queen_num].down(move_distance);

        return b;
    }

    private int randInt(int min, int max) {

    // Usually this can be a field rather than a method variable
    Random rand = new Random();

    // nextInt is normally exclusive of the top value,
    // so add 1 to make it inclusive
    int randomNum = rand.nextInt((max - min) + 1) + min;

    return randomNum;
}
}
