// the only difference between this and hillclimb is that this will keep trying until it gets
// an h value == 0. I think.
// There is a chance that I don't understand what is supposed to happen.
public class RandomRestart
{
    public static void main(String[] args){

        // loop until I can't find a higher successor.
        Board current_board = new Board();
        Board current_successors[] = new Board[56];
        boolean found = true;

        while (current_board.getH() != 0){
            current_successors = new Board[56];
            while(found){ 
                found = false;
                // I just know that each board has 56 successors.
                // I should make this more general for N queens.
                current_board.generateSuccessors();
                current_successors = current_board.successors;
                for (int i=0; i<56; i++){
                    if (current_successors[i].compareTo(current_board) < 0){
                        current_board = current_successors[i];
                        found = true;
                    }
                }
            }
            // generate a new initial state and try this one. This is just my understanding
            // based on how the book words it, maybe I'm supposed to do something a bit more
            // complicated than this
            if (current_board.getH() != 0){
                System.out.println("restart");
                System.out.println(current_board.getH());
                current_board = new Board();
                found = true;
            }  
        }

        current_board.print();
        System.out.println("");
        System.out.print("Heuristic Value: " + current_board.getH());
    }
}
