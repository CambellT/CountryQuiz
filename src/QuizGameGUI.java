import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizGameGUI extends JFrame {
    private JButton countryButton;
    private JButton capitalButton;
    private JButton continentButton;
    private JButton flagButton;

    public QuizGameGUI() {
        setTitle("Country Quiz Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

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

