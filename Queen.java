public class Queen
{
    public int row, col;
    
    public Queen(int r, int c){
        row = r;
        col = c;
    }


    // check if this queen can attack another queen
    public boolean canAttack(Queen q2){
        boolean attack = false;
        
        if (row == q2.row)
            attack = true;

        if (col == q2.col)
            attack = true;

        // check for diagonal attacks
        if (Math.abs(col-q2.col) == Math.abs(row-q2.row))
            attack = true;
        
        return attack;
    }
    

    public void up(int amt){
        row = row - amt;

        if (row < 0)
            row = 0;
    }
    

    public void down(int amt){
        row = row + amt;
        
        if (row > 7)
            row = (row % 7) -1 ; // this seems right to me, but it's very late
                                 // I could be wrong.
    }
}
