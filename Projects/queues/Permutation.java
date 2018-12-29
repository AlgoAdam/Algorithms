/* *****************************************************************************
 *  Name: Adam Butterworth
 *  Date: 29th December 2018
 *  Description: Client to output random numbers from selection
 **************************************************************************** */


import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        int k = Integer.parseInt(args[0]);
        // Initialise Randomized Queue
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        // Loop through StdIn and enqueue onto rq
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }
        // Loop through rq and print output k times
        for (int j = 0; j < k; j++) {
            System.out.println(rq.dequeue());
        }


    }
}
