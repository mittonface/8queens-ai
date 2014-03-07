public class SimulatedAnnealing{
    public SimulatedAnnealing(){}
    public static void main(String[] args){


        Board initial_board = new Board();
        Board solution = anneal(initial_board, 30, 0.001);
        solution.print();


    }

    public static Board runBoard(Board b){
        Board solution = anneal(b, 30, 0.0005);
        return solution;
    }

    public static Board anneal(Board b, double start_temperature, double step_val){

        double temperature = start_temperature;  // initial temperature
        double t_step = step_val;            // temperature decrease per loop
        double p;                  // probability for accepting random state

        Board current = b;
        Board next = new Board(); // stores the random neighbour
        int delta_e;
        double rand_num;
        int nodes_generated = 0;

        while (temperature > 0 && current.getH() != 0){

            // pick a random neighbour
            next = current.randomNeighbour();
            nodes_generated++;

            // compare the neighbours h value
            delta_e = current.getH() - next.getH()  ;
            
            // if delta_e is greater than zero it means that currents H value was higher than
            // nexts H value. So we want to take next
            if (delta_e > 0){
                current = next;
            }else{
                // if next has a higher h_value we still want to take it sometimes.
                p = Math.exp(delta_e / temperature);
                rand_num = Math.random();
                //System.out.println("P -> " + p);
                if (rand_num <= p){
                    current = next;
                }
            }
            temperature -= t_step;
        }
        System.out.print(nodes_generated + ",");
        return current;
    }
}
