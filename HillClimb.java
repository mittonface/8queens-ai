public class HillClimb{

    public HillClimb(){}
    public static void main(String[] args){

        // loop until I can't find a higher successor.
        Board current_board = new Board();
        Board current_successors[] = new Board[56];
        boolean found = true;
        
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
        current_board.print();
        System.out.println("");
        System.out.println("Heuristic Value: " + current_board.getH());
    }

    public static Board runBoard(Board b){

        // loop until I can't find a higher successor.
        Board current_board = b;
        Board current_successors[] = new Board[56];
        boolean found = true;
        int nodes_generated = 0;
    
        while(found){ 
            found = false;
            // I just know that each board has 56 successors.
            // I should make this more general for N queens.
            current_board.generateSuccessors();
            current_successors = current_board.successors;
            nodes_generated += 56;
            for (int i=0; i<56; i++){
                if (current_successors[i].compareTo(current_board) < 0){
                    current_board = current_successors[i];
                    found = true;
                }
            }
        }

        System.out.print(nodes_generated + ",");
        return current_board;
    }
}
    