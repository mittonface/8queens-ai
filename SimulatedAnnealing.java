public class SimulatedAnnealing{

    public static void main(String[] args){
        Board initial_board = new Board();
        System.out.println("PROBLEM");
        initial_board.print();
        Board solution = anneal(initial_board);

        System.out.println("SOLUTION");
        solution.print();
    }

    public static Board anneal(Board b){

        double temperature = 1000;  // initial temperature
        double t_step = 0.1;            // temperature decrease per loop
        double p;                  // probability for accepting random state

        Board current = b;
        Board next = new Board(); // stores the random neighbour
        int delta_e;
        double rand_num;

        while (temperature > 0){

            next = current.randomNeighbour();

            delta_e = next.getH() - current.getH();

           // System.out.println("Delta E ->" + delta_e);
            
            if (delta_e > 0){
                current = next;
            }else{
                
                p = Math.exp(delta_e / temperature);
                rand_num = Math.random();
                //System.out.println("P -> " + p);
                if (rand_num <= p){
                    current = next;
                }
            }
            temperature -= t_step;
        }

        return current;
    }
}
