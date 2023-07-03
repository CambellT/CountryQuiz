import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizGameGUI implements ActionListener {
    private JButton countryButton;
    private JButton capitalButton;
    private JButton continentButton;
    private JButton flagButton;

    String[] questions = 	{
        "What is the capital of Japan?"
        };
    String[][] options = 	{
            {"Tokyo","London","Washington DC","Cairo"}
        };
    char[] answers = 		{
            'A',
            'B',
            'C',
            'D'
        };

    char guess;
	char answer;
	int index;
	int correctGuesses = 0;
	int totalQuestions = 5;
	int result;
	int seconds = 10;

	JFrame frame = new JFrame();
	JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JTextField correctCount = new JTextField();
    JTextField percentageCorrect = new JTextField();

	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
	JLabel timeLabel = new JLabel();
	JLabel secondsLeft = new JLabel();

    Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			secondsLeft.setText(String.valueOf(seconds));
			    if(seconds <= 0) {
				    displayAnswer();
			    }
			}
		});

    public QuizGameGUI() {
        frame.setTitle("Country Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,650);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(false);

        textField.setBounds(0,0,800,50);
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,0));
		textField.setFont(new Font("Ink Free",Font.BOLD,30));
		textField.setBorder(BorderFactory.createBevelBorder(1));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);

		textArea.setBounds(0,50,800,50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(25,25,25));
		textArea.setForeground(new Color(25,255,0));
		textArea.setFont(new Font("MV Boli",Font.BOLD,25));
		textArea.setBorder(BorderFactory.createBevelBorder(1));
		textArea.setEditable(false);

        buttonA.setBounds(0,100,100,100);
		buttonA.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");		

		buttonB.setBounds(0,200,100,100);
		buttonB.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");

		buttonC.setBounds(0,300,100,100);
		buttonC.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");

		buttonD.setBounds(0,400,100,100);
		buttonD.setFont(new Font("MV Boli",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");

        answer_labelA.setBounds(125,100,500,100);
		answer_labelA.setBackground(new Color(50,50,50));
		answer_labelA.setForeground(new Color(25,255,0));
		answer_labelA.setFont(new Font("MV Boli",Font.PLAIN,35));

		answer_labelB.setBounds(125,200,500,100);
		answer_labelB.setBackground(new Color(50,50,50));
		answer_labelB.setForeground(new Color(25,255,0));
		answer_labelB.setFont(new Font("MV Boli",Font.PLAIN,35));

		answer_labelC.setBounds(125,300,500,100);
		answer_labelC.setBackground(new Color(50,50,50));
		answer_labelC.setForeground(new Color(25,255,0));
		answer_labelC.setFont(new Font("MV Boli",Font.PLAIN,35));

		answer_labelD.setBounds(125,400,500,100);
		answer_labelD.setBackground(new Color(50,50,50));
		answer_labelD.setForeground(new Color(25,255,0));
		answer_labelD.setFont(new Font("MV Boli",Font.PLAIN,35));

        secondsLeft.setBounds(675,500,100,100);
		secondsLeft.setBackground(new Color(0,0,0));
		secondsLeft.setForeground(new Color(255,255,255));
		secondsLeft.setFont(new Font("Ink Free",Font.BOLD,60));
		secondsLeft.setBorder(BorderFactory.createBevelBorder(1));
		secondsLeft.setOpaque(true);
		secondsLeft.setHorizontalAlignment(JTextField.CENTER);
		secondsLeft.setText(String.valueOf(seconds));
		
		timeLabel.setBounds(675,470,100,25);
		timeLabel.setBackground(new Color(0,0,0));
		timeLabel.setForeground(new Color(255,255,255));
		timeLabel.setFont(new Font("MV Boli",Font.PLAIN,16));
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		timeLabel.setText("Timer");
		
		correctCount.setBounds(425,225,200,100);
		correctCount.setBackground(new Color(25,25,25));
		correctCount.setForeground(new Color(25,255,0));
		correctCount.setFont(new Font("Ink Free",Font.BOLD,50));
		correctCount.setBorder(BorderFactory.createBevelBorder(1));
		correctCount.setHorizontalAlignment(JTextField.CENTER);
		correctCount.setEditable(false);
		
		percentageCorrect.setBounds(425,325,200,100);
		percentageCorrect.setBackground(new Color(25,25,25));
		percentageCorrect.setForeground(new Color(25,255,0));
		percentageCorrect.setFont(new Font("Ink Free",Font.BOLD,50));
		percentageCorrect.setBorder(BorderFactory.createBevelBorder(2));
		percentageCorrect.setHorizontalAlignment(JTextField.CENTER);
		percentageCorrect.setEditable(false);

        countryButton = new JButton("Guess Countries");
        capitalButton = new JButton("Guess Capitals");
        continentButton = new JButton("Guess Continents");
        flagButton = new JButton("Guess Flags");

        frame.add(timeLabel);
		frame.add(secondsLeft);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textArea);
		frame.add(textField);
		frame.setVisible(true);

        nextQuestion();

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

    }

    public void nextQuestion() {
		if(index >= totalQuestions) {
			results();
		}
		else {
			textField.setText("Question "+(index+1));
			textArea.setText(questions[index]);
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
            timer.start();
		}
	}


    public void startGame(String gameType) {
        List<Country> allCountries = CountryQuizGame.loadCountries();
        switch (gameType) {
            case ("A") -> {CountryQuizGame.playCountryGame(5, allCountries);}
            case ("B") -> {CountryQuizGame.playCapitalGame(5, allCountries);}
            //case ("C") -> {CountryQuizGame.playContinentGame(5, allCountries);}
            //case ("D") -> {CountryQuizGame.playFlagGame(5, allCountries);}
            default -> {}
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizGameGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
			
		if(e.getSource() == buttonA) {
            answer= 'A';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource()==buttonB) {
            answer= 'B';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource()==buttonC) {
            answer= 'C';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        if(e.getSource()==buttonD) {
            answer= 'D';
            if(answer == answers[index]) {
                correctGuesses++;
            }
        }
        displayAnswer();
    }
    public void displayAnswer() {
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(answers[index] != 'A')
			answer_labelA.setForeground(new Color(255,0,0));
		if(answers[index] != 'B')
			answer_labelB.setForeground(new Color(255,0,0));
		if(answers[index] != 'C')
			answer_labelC.setForeground(new Color(255,0,0));
		if(answers[index] != 'D')
			answer_labelD.setForeground(new Color(255,0,0));
		Timer pause = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(25,255,0));
				answer_labelB.setForeground(new Color(25,255,0));
				answer_labelC.setForeground(new Color(25,255,0));
				answer_labelD.setForeground(new Color(25,255,0));
				answer = ' ';
				seconds=10;
				secondsLeft.setText(String.valueOf(seconds));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}

    public void results(){
		
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		result = (int)((correctGuesses/(double) totalQuestions)*100);
		
		textField.setText("RESULTS!");
		textArea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		correctCount.setText("("+correctGuesses+"/"+totalQuestions+")");
		percentageCorrect.setText(result+"%");
		
		frame.add(correctCount);
		frame.add(percentageCorrect);
	}

}

