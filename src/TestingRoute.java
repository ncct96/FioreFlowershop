/**
 * An implementation of the traveling salesman problem in Java using dynamic
 * programming to improve the time complexity from O(n!) to O(n^2 * 2^n).
 *
 * Time Complexity: O(n^2 * 2^n)
 * Space Complexity: O(n * 2^n)
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 * */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TestingRoute {

    private final int N, start;
    private final double[][] distance;
    private List<Integer> tour = new ArrayList<>();
    private double minTourCost = Double.POSITIVE_INFINITY;
    private boolean ranSolver = false;

    public TestingRoute(double[][] distance) {
        this(0, distance);
    }

    public TestingRoute(int start, double[][] distance) {
        N = distance.length;

        if (N <= 2) {
            throw new IllegalStateException("N <= 2 not yet supported.");
        }
        if (N != distance[0].length) {
            throw new IllegalStateException("Matrix must be square (n x n)");
        }
        if (start < 0 || start >= N) {
            throw new IllegalArgumentException("Invalid start node.");
        }
//        if (N > 32) {
//            throw new IllegalArgumentException("Matrix too large! A matrix that size for the DP TSP problem with a time complexity of"
//                    + "O(n^2*2^n) requires way too much computation for any modern home computer to handle");
//        }

        this.start = start;
        this.distance = distance;
    }

    // Returns the optimal tour for the traveling salesman problem.
    public List<Integer> getTour() {
        if (!ranSolver) {
            solve();
        }
        return tour;
    }

    // Returns the minimal tour cost.
    public double getTourCost() {
        if (!ranSolver) {
            solve();
        }
        return minTourCost;
    }

    // Solves the traveling salesman problem and caches solution.
    public void solve() {

        if (ranSolver) {
            return;
        }

        final int END_STATE = (1 << N) - 1;
        Double[][] memo = new Double[N][1 << N];

        // Add all outgoing edges from the starting node to memo table.
        for (int end = 0; end < N; end++) {
            if (end == start) {
                continue;
            }
            memo[end][(1 << start) | (1 << end)] = distance[start][end];
        }

        for (int r = 3; r <= N; r++) {
            for (int subset : combinations(r, N)) {
                if (notIn(start, subset)) {
                    continue;
                }
                for (int next = 0; next < N; next++) {
                    if (next == start || notIn(next, subset)) {
                        continue;
                    }
                    int subsetWithoutNext = subset ^ (1 << next);
                    double minDist = Double.POSITIVE_INFINITY;
                    for (int end = 0; end < N; end++) {
                        if (end == start || end == next || notIn(end, subset)) {
                            continue;
                        }
                        double newDistance = memo[end][subsetWithoutNext] + distance[end][next];
                        if (newDistance < minDist) {
                            minDist = newDistance;
                        }
                    }
                    memo[next][subset] = minDist;
                }
            }
        }

        // Connect tour back to starting node and minimize cost.
        for (int i = 0; i < N; i++) {
            if (i == start) {
                continue;
            }
            double tourCost = memo[i][END_STATE] + distance[i][start];
            if (tourCost < minTourCost) {
                minTourCost = tourCost;
            }
        }

        int lastIndex = start;
        int state = END_STATE;
        tour.add(start);

        // Reconstruct TSP path from memo table.
        for (int i = 1; i < N; i++) {

            int index = -1;
            for (int j = 0; j < N; j++) {
                if (j == start || notIn(j, state)) {
                    continue;
                }
                if (index == -1) {
                    index = j;
                }
                double prevDist = memo[index][state] + distance[index][lastIndex];
                double newDist = memo[j][state] + distance[j][lastIndex];
                if (newDist < prevDist) {
                    index = j;
                }
            }

            tour.add(index);
            state = state ^ (1 << index);
            lastIndex = index;
        }

        tour.add(start);
        Collections.reverse(tour);

        ranSolver = true;
    }

    private static boolean notIn(int elem, int subset) {
        return ((1 << elem) & subset) == 0;
    }

    // This method generates all bit sets of size n where r bits 
    // are set to one. The result is returned as a list of integer masks.
    public static List<Integer> combinations(int r, int n) {
        List<Integer> subsets = new ArrayList<>();
        combinations(0, 0, r, n, subsets);
        return subsets;
    }

    // To find all the combinations of size r we need to recurse until we have
    // selected r elements (aka r = 0), otherwise if r != 0 then we still need to select
    // an element which is found after the position of our last selected element
    private static void combinations(int set, int at, int r, int n, List<Integer> subsets) {

        // Return early if there are more elements left to select than what is available.
        int elementsLeftToPick = n - at;
        if (elementsLeftToPick < r) {
            return;
        }

        // We selected 'r' elements so we found a valid subset!
        if (r == 0) {
            subsets.add(set);
        } else {
            for (int i = at; i < n; i++) {
                // Try including this element
                set ^= (1 << i);

                combinations(set, i + 1, r - 1, n, subsets);

                // Backtrack and try the instance where we did not include this element
                set ^= (1 << i);
            }
        }
    }

    public static void main(String[] args) {
        // Create adjacency matrix
        int n = 20;
        double[][] distanceMatrix = new double[n][n];
        for (double[] row : distanceMatrix) {
            java.util.Arrays.fill(row, 1000000);
        }
        
        distanceMatrix[0][0] = 0;
        distanceMatrix[0][1] = 12312312;
        distanceMatrix[0][2] = 1243254;
        distanceMatrix[0][3] = 1213254;
        distanceMatrix[0][4] = 1213254;
        distanceMatrix[0][5] = 12132354;
        distanceMatrix[0][6] = 12132154;
        distanceMatrix[0][7] = 12123254;
        distanceMatrix[0][8] = 5133254;
        distanceMatrix[0][9] = 6213254;
        distanceMatrix[1][0] = 7213254;
        distanceMatrix[1][2] = 8213254;
        distanceMatrix[1][3] = 9213254;
        distanceMatrix[1][4] = 2213254;
        distanceMatrix[1][5] = 2413254;
        distanceMatrix[1][6] = 1413254;
        distanceMatrix[1][7] = 1613254;
        distanceMatrix[1][8] = 1813254;
        distanceMatrix[1][9] = 8213254;
        distanceMatrix[2][1] = 1913254;
        distanceMatrix[2][3] = 9813254;
        distanceMatrix[2][4] = 9713254;
        distanceMatrix[2][5] = 9313254;
        distanceMatrix[2][6] = 9513254;
        distanceMatrix[2][7] = 35835;
        distanceMatrix[2][8] = 3586;
        distanceMatrix[2][9] = 5368;
        distanceMatrix[3][0] = 5368;
        distanceMatrix[3][1] = 13132;
        distanceMatrix[3][2] = 5368;
        distanceMatrix[3][4] = 347;
        distanceMatrix[3][5] = 5368;
        distanceMatrix[3][6] = 4576;
        distanceMatrix[3][7] = 345745;
        distanceMatrix[3][8] = 347374;
        distanceMatrix[3][9] = 5476;
        distanceMatrix[4][1] = 9696;
        distanceMatrix[4][2] = 6659;
        distanceMatrix[4][3] = 34626;
        distanceMatrix[4][5] = 55454;
        distanceMatrix[4][6] = 965976;
        distanceMatrix[4][7] = 657646;
        distanceMatrix[4][8] = 56758;
        distanceMatrix[4][9] = 32432;
        distanceMatrix[5][0] = 4576;
        distanceMatrix[5][1] = 574343;
        distanceMatrix[5][2] = 2423432;

        int startNode = 0;
        TestingRoute solver = new TestingRoute(startNode, distanceMatrix);

        // Prints: [0, 3, 2, 4, 1, 5, 0]
        System.out.println("Tour: " + solver.getTour());

        // Print: 42.0
        System.out.println("Tour cost: " + solver.getTourCost());
    }
}
