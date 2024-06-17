import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] initialBoard = {1, 2, 3, 4, 5, 6, 7, 0, 8};
        int emptyIndex = 7;
        PuzzleStateUninformed initialStateUninformed = new PuzzleStateUninformed(initialBoard, emptyIndex);
        PuzzleStateInformed initialStateInformed = new PuzzleStateInformed(initialBoard, emptyIndex, 0);


        if (aStar(initialStateInformed)) {
            System.out.println("Solution found!");
        } else {
            System.out.println("No solution exists.");
        }

        if (bfs(initialStateUninformed)) {
            System.out.println("Solution found!");
        } else {
            System.out.println("No solution exists.");
        }
    }

    public static boolean bfs(PuzzleStateUninformed initialState) {
        Queue<PuzzleStateUninformed> frontier = new LinkedList<>();
        Set<PuzzleStateUninformed> explored = new HashSet<>();
        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            PuzzleStateUninformed state = frontier.poll();

            if (state.isGoal()) {
                List<PuzzleStateUninformed> path = state.getPath();
                for (PuzzleStateUninformed step : path) {
                    System.out.println(step);
                }
                return true;
            }

            explored.add(state);

            for (PuzzleStateUninformed neighbor : state.getNeighbors()) {
                if (!explored.contains(neighbor) && !frontier.contains(neighbor)) {
                    neighbor.parent = state;
                    frontier.add(neighbor);
                }
            }
        }
        return false;
    }

    public static boolean aStar(PuzzleStateInformed initialState) {
        PriorityQueue<PuzzleStateInformed> frontier = new PriorityQueue<>(Comparator.comparingInt(s -> s.g + s.h));
        Set<PuzzleStateInformed> explored = new HashSet<>();
        frontier.add(initialState);

        while (!frontier.isEmpty()) {
            PuzzleStateInformed state = frontier.poll();

            if (state.isGoal()) {
                List<PuzzleStateInformed> path = state.getPath();
                for (PuzzleStateInformed step : path) {
                    System.out.println(step);
                }
                return true;
            }

            explored.add(state);

            for (PuzzleStateInformed neighbor : state.getNeighbors()) {
                if (!explored.contains(neighbor)) {
                    neighbor.parent = state;
                    frontier.add(neighbor);
                }
            }
        }
        return false;
    }
}