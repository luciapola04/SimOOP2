package a02b.e2;

import java.util.ArrayList;
import java.util.List;


public class LogicsImpl implements Logics{

    private List<List<Boolean>> grid = new ArrayList<>();
    
    public LogicsImpl(int size) {
        for(int i=0; i<size; i++) {
            final List<Boolean> row = new ArrayList<>();
            this.grid.add(row);
            for(int j=0; j<size; j++) {
                row.add(false);
            }
        }
    }

    private boolean gridGet(int row, int column) {
        return this.grid.get(row).get(column);
    }

    @Override
    public boolean hit(int row, int column) {
        final boolean value = !this.gridGet(row, column);
        this.grid.get(row).set(column, value);
        return value;
    }

    @Override
    public List<Pair<Integer, Integer>> checkDiagonal() {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        int size = this.grid.size();
    
        for (int startCol = 0; startCol < size; startCol++) {
            int row = 0;
            int col = startCol;
            int trueCount = 0;
            while (row < size && col < size) {
                if (this.gridGet(row, col)) {
                    trueCount++;
                }
                row++;
                col++;
            }
            if (trueCount == 3) {
                result.add(new Pair<>(0, startCol));
                break;
            }
        }

        if(result.isEmpty()) {
            for (int startRow = 1; startRow < size; startRow++) {
                int row = startRow;
                int col = 0;
                int trueCount = 0;
                while (row < size && col < size) {
                    if (this.gridGet(row, col)) {
                        trueCount++;
                    }
                    row++;
                    col++;
                }
                if (trueCount == 3) {
                    result.add(new Pair<>(startRow, 0)); 
                    break;
                }
            }
        }
        
        return result; 
    }

    @Override
    public void reset() {
        this.grid.forEach(b -> b.replaceAll(p -> false));
    }
}

