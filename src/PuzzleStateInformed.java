import java.util.*;

class PuzzleStateInformed{
    int[] board;
    int emptyIndex;
    int g;  // Cost from start to current state
    int h;  // Heuristic cost to goal
    PuzzleStateInformed parent;

    public int[] getBoard() {
        return board;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public int getEmptyIndex() {
        return emptyIndex;
    }

    public void setEmptyIndex(int emptyIndex) {
        this.emptyIndex = emptyIndex;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public PuzzleStateInformed getParent() {
        return parent;
    }

    public void setParent(PuzzleStateInformed parent) {
        this.parent = parent;
    }

    public PuzzleStateInformed(int[] board, int emptyIndex, int g) {
        this.board = board.clone();
        this.emptyIndex = emptyIndex;
        this.g = g;
        this.h = calculateManhattanDistance();
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

    public List<PuzzleStateInformed> getNeighbors() {
        List<PuzzleStateInformed> neighbors = new ArrayList<>();
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
                neighbors.add(new PuzzleStateInformed(newBoard, newIndex, g + 1));
            }
        }
        return neighbors;
    }

    public int calculateManhattanDistance() {
        int distance = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] != 0) {
                int value = board[i];
                int targetRow = (value - 1) / 3;
                int targetCol = (value - 1) % 3;
                int row = i / 3;
                int col = i % 3;
                distance += Math.abs(row - targetRow) + Math.abs(col - targetCol);
            }
        }
        return distance;
    }

    public List<PuzzleStateInformed> getPath() {
        List<PuzzleStateInformed> path = new ArrayList<>();
        PuzzleStateInformed current = this;
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
        PuzzleStateInformed that = (PuzzleStateInformed) o;
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
