package pckg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MyFrame extends JFrame implements ActionListener {
    String player1 = "X";
    String player2 = "O";
    Boolean ActivePlayer = true;
    JFrame frame = new JFrame();
    JPanel gridPanel = new JPanel();
    JButton onebtn = new JButton();
    JButton twobtn = new JButton();
    JButton threebtn = new JButton();
    JButton fourbtn = new JButton();
    JButton fivebtn = new JButton();
    JButton sixbtn = new JButton();
    JButton sevenbtn = new JButton();
    JButton eightbtn = new JButton();
    JButton ninebtn = new JButton();
    int[][] winningCombinations = {

    	    {0, 1, 2}, 
    	    {3, 4, 5}, 
    	    {6, 7, 8}, 

    	    {0, 3, 6}, 
    	    {1, 4, 7}, 
    	    {2, 5, 8}, 

    	    {0, 4, 8}, 
    	    {2, 4, 6}
    	};
    JButton[] buttons = {onebtn, twobtn, threebtn, fourbtn, fivebtn, sixbtn, sevenbtn, eightbtn, ninebtn};
    JButton resetGame = new JButton("RESET");

    MyFrame() {
    	gridPanel.setBackground(new Color(91,132,177));
        gridPanel.setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
        	buttons[i].setFocusable(false);
            buttons[i].setFont(new Font("Algerian",Font.PLAIN,40));
            buttons[i].setBackground(new Color(252, 118, 106));
            gridPanel.add(buttons[i]);
        }
        resetGame.addActionListener(this);
        resetGame.setBackground(Color.CYAN);
        resetGame.setFocusable(false);
        frame.setLayout(new BorderLayout());
        frame.add(gridPanel, BorderLayout.CENTER); 
        frame.add(resetGame, BorderLayout.SOUTH);  
        frame.setTitle("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                if (ActivePlayer) {
                    buttons[i].setText(player1);
                    buttons[i].setEnabled(false);
                    ActivePlayer = false;
                    checkWinner();
                } else {
                    buttons[i].setText(player2);
                    buttons[i].setEnabled(false);
                    ActivePlayer = true;
                    checkWinner();
                }
            }
        }
        if(e.getSource()==resetGame) {
        	for(JButton i : buttons) {
        		i.setText("");
        		i.setEnabled(true);
        		ActivePlayer=true;
        	}
        }
        String winner = checkWinner();
        if(winner!=null) {
        	JOptionPane.showMessageDialog(null, "Player " + winner + " Has Won!");
        	for(JButton btn : buttons) {
        		btn.setEnabled(false);
        	}
        }
    }
    public String checkWinner() {
    	for(int i =0;i<winningCombinations.length;i++) {
    		int[] combination = winningCombinations[i];
    		String filled = buttons[combination[0]].getText();
    		 if (!filled.isEmpty() && filled.equals(buttons[combination[1]].getText()) 
    		 &&  filled.equals(buttons[combination[2]].getText())) {
    			 return filled;
    		 }
    	}
		return null;
    	
    }
}

public class TicTacToe {
    public static void main(String[] args) {
        new MyFrame();
    }
}
