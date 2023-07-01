import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGameGUI extends JFrame {

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();

    private JButton countryButton = new JButton();
    private JButton capitalButton = new JButton();
    private JButton continentButton = new JButton();
    private JButton flagButton = new JButton();

    JLabel answerA = new JLabel();
    JLabel answerB = new JLabel();
    JLabel answerC = new JLabel();
    JLabel answerD = new JLabel();
    JLabel timerLabel = new JLabel();
    JLabel secondsLeft = new JLabel();

    JTextField correctCount = new JTextField();
    JTextField correctPercentage = new JTextField();

    public QuizGameGUI() {
        setTitle("Country Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(new FlowLayout());

        frame.setSize(800,600);
        frame.setVisible(true);

        countryButton = new JButton("Guess Countries");
        capitalButton = new JButton("Guess Capitals");
        continentButton = new JButton("Guess Continents");
        flagButton = new JButton("Guess Flags");

        countryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("A");
            }
        });

        capitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("B");
            }
        });

        add(countryButton);
        add(capitalButton);
        add(continentButton);
        add(flagButton);

        pack();
        setVisible(true);
    }

    public void startGame(String gameType) {
        // TO DO
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizGameGUI();
            }
        });
    }
}

