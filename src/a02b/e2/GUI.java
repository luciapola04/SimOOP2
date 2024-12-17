package a02b.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {

    private final Map<JButton, Pair<Integer, Integer>> grid = new HashMap<>();
    private final JButton check = new JButton("Check > Restart");
    private final Logics logics;
    private boolean reset = false;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logics = new LogicsImpl(size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        main.add(BorderLayout.SOUTH, check);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    Pair<Integer, Integer> buttonPosition = grid.get(button);
                button.setText(logics.hit(buttonPosition.getX(), buttonPosition.getY()) ? "*" : " ");
            }
        };

        check.addActionListener(e -> {
            if(reset){
                for (var button : this.grid.keySet()){
                    this.logics.reset();
                    button.setText(" ");
                    button.setEnabled(true);
                    reset = false;
                }
            }else{
                List<Pair<Integer, Integer>> diagonals = logics.checkDiagonal();
                if (!diagonals.isEmpty()) {
                    reset = true;
                    for (var pair : diagonals) {
                        int startRow = pair.getX();
                        int startCol = pair.getY();
                        int row = startRow;
                        int col = startCol;
            
                        List<Pair<Integer, Integer>> buttonsToDisable = new ArrayList<>();

                        while (row < size && col < size) {
                            buttonsToDisable.add(new Pair<>(row, col));
                            row++;
                            col++;
                        }

                        buttonsToDisable.forEach(pairToDisable -> 
                            grid.keySet().stream()
                                .filter(button -> grid.get(button).equals(pairToDisable))
                                .forEach(button -> button.setEnabled(false)) 
                        );
                    }
                }

            }
        });
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.grid.put(jb, new Pair<Integer,Integer>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }    
}
