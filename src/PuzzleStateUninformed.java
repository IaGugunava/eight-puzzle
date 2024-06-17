import java.util.*;

class PuzzleStateUninformed {
    int[] board;
    int emptyIndex;
    PuzzleStateUninformed parent;

    public PuzzleStateUninformed(int[] board, int emptyIndex) {
        this.board = board.clone();
        this.emptyIndex = emptyIndex;
        this.parent = null;
    }

    public boolean isGoal() {
        for (int i = 0; i < 8; i++) {
            if (board[i] != i + 1) {
                return false;
            }
        }
        return board[8] == 0;
    }

    public List<PuzzleStateUninformed> getNeighbors() {
        List<PuzzleStateUninformed> neighbors = new ArrayList<>();
        int row = emptyIndex / 3;
        int col = emptyIndex % 3;

        // Possible moves: up, down, left, right
        int[] directions = {-1, 1, -3, 3};
        for (int direction : directions) {
            int newRow = row + direction / 3;
            int newCol = col + direction % 3;
            int newIndex = emptyIndex + direction;
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                int[] newBoard = board.clone();
                newBoard[emptyIndex] = newBoard[newIndex];
                newBoard[newIndex] = 0;
                neighbors.add(new PuzzleStateUninformed(newBoard, newIndex));
            }
        }
        return neighbors;
    }

    public List<PuzzleStateUninformed> getPath() {
        List<PuzzleStateUninformed> path = new ArrayList<>();
        PuzzleStateUninformed current = this;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PuzzleStateUninformed that = (PuzzleStateUninformed) o;
        return Arrays.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    @Override
    public String toString() {
        return Arrays.toString(board);
    }
}

