public class Runner{

    public static void main(String[] args){
        Board board = new Board();

        board.generateSuccessors();

        for (int i=0; i<56; i++){
            board.successors[i].print();
        }

    }
}
    