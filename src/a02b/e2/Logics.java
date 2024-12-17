package a02b.e2;

import java.util.List;

public interface Logics {

    boolean hit(int row, int column);

    List<Pair<Integer, Integer>> checkDiagonal();

    void reset();

}
