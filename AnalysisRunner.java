// this class is used to compare the different algorithms against each
// other
public class AnalysisRunner
{
    public static void main(String[] args){
        HillClimb hillclimb = new HillClimb();
        SimulatedAnnealing annealing = new SimulatedAnnealing();
        RandomRestart randomrestart = new RandomRestart();

        Board hc_solution, anneal_solution, restart_solution;

        int hc_successes = 0;
        int anneal_successes = 0;
        int restart_successes = 0;

        for (int i=0; i < 1000; i++){
            // the board to test using each algorithm
            Board test_board = new Board();
            
            hc_solution = hillclimb.runBoard(test_board);
            anneal_solution = annealing.runBoard(test_board);
            restart_solution = randomrestart.runBoard(test_board);
            System.out.println();
            if (hc_solution.getH() == 0)
                hc_successes++;

            if (anneal_solution.getH() == 0)
                anneal_successes++;

            if (restart_solution.getH() == 0)
                restart_successes++;
        }

        System.out.println("Hill Climbing Successes " + hc_successes + "/1000");
        System.out.println("Simulated Annealing Successes " + anneal_successes + "/1000");
        System.out.println("Random Restart Hill Climbing Successes " + restart_successes + "/1000");
    }
}
