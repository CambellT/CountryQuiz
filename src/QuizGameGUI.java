import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class QuizGameGUI implements ActionListener {
	List<Country> allCountries = CountryQuizGame.loadCountries();
	String gameType = "A";

    String[] questions = 	{
        "What is the capital of ",
        "What is the capital of England?",
        "What is the capital of USA?",
        "What is the capital of Egypt?",
		"Flag test"
        };
    String[][] options = 	{
            {"Tokyo","London","Washington DC","Cairo"},
            {"Tokyo","London","Washington DC","Cairo"},
            {"Tokyo","London","Washington DC","Cairo"},
            {"Tokyo","London","Washington DC","Cairo"}
        };
    char[] answers = 		{
            'A',
            'B',
            'C',
            'D'
        };

    //char guess;
	char answer;
	int index;
	int correctGuesses = 0;
	int totalQuestions = 4;
	int result;
	int seconds = 5;

	JFrame frame = new JFrame();
	JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JTextField correctCount = new JTextField();
    JTextField percentageCorrect = new JTextField();

	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
    JButton countryButton = new JButton();
    JButton capitalButton = new JButton();
    JButton continentButton = new JButton();
    JButton flagButton = new JButton();

	JLabel labelA = new JLabel();
	JLabel labelB = new JLabel();
	JLabel labelC = new JLabel();
	JLabel labelD = new JLabel();
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
		frame.setSize(800,600);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(null);
		frame.setResizable(false);

        textField.setBounds(0,50,700,50);
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(255,255,255));
		textField.setFont(new Font("Sans Serif",Font.ITALIC,30));
		textField.setBorder(BorderFactory.createBevelBorder(3));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);

		textArea.setBounds(0,100,700,50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(25,25,25));
		textArea.setForeground(new Color(255,255,255));
		textArea.setFont(new Font("Sans Serif",Font.BOLD,25));
		textArea.setBorder(BorderFactory.createBevelBorder(3));
		textArea.setEditable(false);

        countryButton.setBounds(0,0,200,50);
		countryButton.setFont(new Font("Sans Serif",Font.BOLD,15));
		countryButton.setBackground(Color.GREEN);
		countryButton.setFocusable(false);
		countryButton.addActionListener(this);
		countryButton.setText("Country Quiz");	

        capitalButton.setBounds(200,0,200,50);
		capitalButton.setFont(new Font("Sans Serif",Font.BOLD,15));
		capitalButton.setBackground(Color.BLUE);
		capitalButton.setFocusable(false);
		capitalButton.addActionListener(this);
		capitalButton.setText("Capital Quiz");

        continentButton.setBounds(400,0,200,50);
		continentButton.setFont(new Font("Sans Serif",Font.BOLD,15));
		continentButton.setFocusable(false);
		continentButton.addActionListener(this);
		continentButton.setText("Continent Quiz");

        flagButton.setBounds(600,0,200,50);
		flagButton.setFont(new Font("Sans Serif",Font.BOLD,15));
		flagButton.setFocusable(false);
		flagButton.addActionListener(this);
		flagButton.setText("Flag Quiz");

        buttonA.setBounds(0,150,100,100);
		buttonA.setFont(new Font("Sans Serif",Font.BOLD,35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");		

		buttonB.setBounds(0,250,100,100);
		buttonB.setFont(new Font("Sans Serif",Font.BOLD,35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");

		buttonC.setBounds(0,350,100,100);
		buttonC.setFont(new Font("Sans Serif",Font.BOLD,35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");

		buttonD.setBounds(0,450,100,100);
		buttonD.setFont(new Font("Sans Serif",Font.BOLD,35));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("D");

        labelA.setBounds(125,150,500,100);
		labelA.setBackground(new Color(50,50,50));
		labelA.setForeground(new Color(255,255,255));
		labelA.setFont(new Font("Sans Serif",Font.PLAIN,35));

		labelB.setBounds(125,250,500,100);
		labelB.setBackground(new Color(50,50,50));
		labelB.setForeground(new Color(255,255,255));
		labelB.setFont(new Font("Sans Serif",Font.PLAIN,35));

		labelC.setBounds(125,350,500,100);
		labelC.setBackground(new Color(50,50,50));
		labelC.setForeground(new Color(255,255,255));
		labelC.setFont(new Font("Sans Serif",Font.PLAIN,35));

		labelD.setBounds(125,450,500,100);
		labelD.setBackground(new Color(50,50,50));
		labelD.setForeground(new Color(255,255,255));
		labelD.setFont(new Font("Sans Serif",Font.PLAIN,35));

        secondsLeft.setBounds(700,50,100,100);
		secondsLeft.setBackground(new Color(0,0,0));
		secondsLeft.setForeground(new Color(255,255,255));
		secondsLeft.setFont(new Font("Ink Free",Font.BOLD,60));
		secondsLeft.setBorder(BorderFactory.createBevelBorder(6));
		secondsLeft.setOpaque(true);
		secondsLeft.setHorizontalAlignment(JTextField.CENTER);
		secondsLeft.setText(String.valueOf(seconds));
		
		timeLabel.setBounds(700,150,100,25);
		timeLabel.setBackground(new Color(0,0,0));
		timeLabel.setForeground(new Color(255,255,255));
		timeLabel.setFont(new Font("Sans Serif",Font.PLAIN,16));
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		timeLabel.setText("Timer");
		
		correctCount.setBounds(300,200,200,100);
		correctCount.setBackground(new Color(25,25,25));
		correctCount.setForeground(new Color(25,255,0));
		correctCount.setFont(new Font("Sans Serif",Font.BOLD,50));
		correctCount.setBorder(BorderFactory.createBevelBorder(2));
		correctCount.setHorizontalAlignment(JTextField.CENTER);
		correctCount.setEditable(false);
		
		percentageCorrect.setBounds(300,300,200,100);
		percentageCorrect.setBackground(new Color(25,25,25));
		percentageCorrect.setForeground(new Color(25,255,0));
		percentageCorrect.setFont(new Font("Sans Serif",Font.BOLD,50));
		percentageCorrect.setBorder(BorderFactory.createBevelBorder(2));
		percentageCorrect.setHorizontalAlignment(JTextField.CENTER);
		percentageCorrect.setEditable(false);

        frame.add(timeLabel);
		frame.add(secondsLeft);
		frame.add(labelA);
		frame.add(labelB);
		frame.add(labelC);
		frame.add(labelD);
        frame.add(countryButton);
        frame.add(capitalButton);
        frame.add(continentButton);
        frame.add(flagButton);
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
                gameType = "A";
            }
        });
        capitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = "B";
            }
        });
        capitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = "C";
            }
        });
        capitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = "D";
            }
        });
    }

    public void nextQuestion() {
		Country countryA = getRandomCountry();
		Country countryB = getRandomCountry();
		Country countryC = getRandomCountry();
		Country countryD = getRandomCountry();

		if(index >= totalQuestions) {
			results();
		}
		else {
			//Country Game
			if (gameType == "A"){

			}
			//Capital Game
			if (gameType == "B"){
				textField.setText("Question "+(index+1));
				textArea.setText(questions[index]+countryA.name() + "?");
				labelA.setText(countryA.capital());
				labelB.setText(countryB.capital());
				labelC.setText(countryC.capital());
				labelD.setText(countryD.capital());
			}
			// Continent Game
			if (gameType == "C"){

			}
			// Continent Game
			if (gameType == "D"){

				ImageIcon imageA = new ImageIcon(CountryQuizGame.getFilePath("flags/.png"));
				labelA.setIcon(imageA);
			}
			
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
            if(answer == answers[index]) {correctGuesses++;}
        }
        if(e.getSource()==buttonB) {
            answer= 'B';
            if(answer == answers[index]) {correctGuesses++;}
        }
        if(e.getSource()==buttonC) {
            answer= 'C';
            if(answer == answers[index]) {correctGuesses++;}
        }
        if(e.getSource()==buttonD) {
            answer= 'D';
            if(answer == answers[index]) {correctGuesses++;}
        }
        displayAnswer();
    }

    public void displayAnswer() {
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if(answers[index] != 'A'){labelA.setForeground(new Color(255,0,0));}
        else {labelA.setForeground(new Color(25,255,0));}

		if(answers[index] != 'B'){labelB.setForeground(new Color(255,0,0));}
        else {labelB.setForeground(new Color(25,255,0));}

		if(answers[index] != 'C'){labelC.setForeground(new Color(255,0,0));}
        else {labelC.setForeground(new Color(25,255,0)); }

		if(answers[index] != 'D'){labelD.setForeground(new Color(255,0,0));}
        else {labelD.setForeground(new Color(25,255,0)); }
		Timer pause = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelA.setForeground(new Color(255,255,255));
				labelB.setForeground(new Color(255,255,255));
				labelC.setForeground(new Color(255,255,255));
				labelD.setForeground(new Color(255,255,255));
				answer = ' ';
				seconds = 10;
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

        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
        timeLabel.setVisible(false);
        secondsLeft.setVisible(false);
		
		result = (int)((correctGuesses/(double) totalQuestions)*100);
		
		textField.setText("RESULTS");
		textArea.setText("");
		labelA.setText("");
		labelB.setText("");
		labelC.setText("");
		labelD.setText("");
		
		correctCount.setText("("+correctGuesses+"/"+totalQuestions+")");
		percentageCorrect.setText(result+"%");
		
		frame.add(correctCount);
		frame.add(percentageCorrect);
	}

	private Country getRandomCountry() {
        Random random = new Random();
        int index = random.nextInt(allCountries.size());
        return allCountries.get(index);
    }

}

