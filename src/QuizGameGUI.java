import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizGameGUI extends JFrame {

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();

    private JButton countryButton;
    private JButton capitalButton;
    private JButton continentButton;
    private JButton flagButton;

    private JLabel answerA = new JLabel();
    private JLabel answerB = new JLabel();
    private JLabel answerC = new JLabel();
    private JLabel answerD = new JLabel();
    private JLabel timerLabel = new JLabel();
    private JLabel secondsLeft = new JLabel();

    JTextField correctCount = new JTextField();
    JTextField correctPercentage = new JTextField();

    public QuizGameGUI() {
        setTitle("Country Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800,600);

        countryButton = new JButton("Guess Countries");
        capitalButton = new JButton("Guess Capitals");
        continentButton = new JButton("Guess Continents");
        flagButton = new JButton("Guess Flags");

        answerA = new JLabel("A");
        answerB = new JLabel("B");
        answerC = new JLabel("C");
        answerD = new JLabel("D");
        timerLabel = new JLabel("Timer");
        secondsLeft = new JLabel("10");

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
        capitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("C");
            }
        });
        capitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame("D");
            }
        });

        add(countryButton);
        add(capitalButton);
        add(continentButton);
        add(flagButton);

        add(answerA);
        add(answerB);
        add(answerC);
        add(answerD);
        add(timerLabel);
        add(secondsLeft);

        pack();
        setVisible(true);
    }

    public void startGame(String gameType) {
        List<Country> allCountries = CountryQuizGame.loadCountries();
        switch (gameType) {
            case ("A") -> {CountryQuizGame.playCountryGame(5, allCountries);}
            case ("B") -> {CountryQuizGame.playCapitalGame(5, allCountries);}
            case ("C") -> {CountryQuizGame.playContinentGame(5, allCountries);}
            case ("D") -> {CountryQuizGame.playFlagGame(5, allCountries);}
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizGameGUI();
            }
        });
    }
}

