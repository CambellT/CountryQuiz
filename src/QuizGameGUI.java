/*
 * Author: Cambell Tanner
 * Date: July 2023
 * Country Quiz Game personal project
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * GUI for the Country game, plays a game in a java window where the user can choose from 4 different games,
 * which ask questions to match the country to capital, capital to country, country to continent or country 
 * to its flag. 
 */
public class QuizGameGUI implements ActionListener {
	List<Country> allCountries = CountryLoader.loadCountries();
	private static final Random random = new Random();
	char answer;
	int index;
	int correctGuesses;
	int totalQuestions = 10;
	int result;
	int seconds = 10;
	String gameType = null;
	Country answerCountry;

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

	/*
	 * Constructor which build the frame, buttons and text fields to be displayed
	 */
    public QuizGameGUI() {
        frame.setTitle("Country Quiz Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,600);
		frame.getContentPane().setBackground(new Color(100,125,100));
		frame.setLayout(null);
		frame.setResizable(false);

        textField.setBounds(0,50,700,50);
		textField.setBackground(new Color(50,50,50));
		textField.setForeground(new Color(255,255,255));
		textField.setFont(new Font("Sans Serif",Font.ITALIC,30));
		textField.setBorder(BorderFactory.createBevelBorder(3));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setEditable(false);

		textArea.setBounds(0,100,700,50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(new Color(100,100,100));
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

		labelB.setBounds(125,250,500,100);
		labelB.setBackground(new Color(50,50,50));
		labelB.setForeground(new Color(255,255,255));

		labelC.setBounds(125,350,500,100);
		labelC.setBackground(new Color(50,50,50));
		labelC.setForeground(new Color(255,255,255));

		labelD.setBounds(125,450,500,100);
		labelD.setBackground(new Color(50,50,50));
		labelD.setForeground(new Color(255,255,255));

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
		correctCount.setBackground(new Color(50,50,50));
		correctCount.setForeground(new Color(255,255,255));
		correctCount.setFont(new Font("Sans Serif",Font.BOLD,50));
		correctCount.setBorder(BorderFactory.createBevelBorder(2));
		correctCount.setHorizontalAlignment(JTextField.CENTER);
		correctCount.setEditable(false);
		
		percentageCorrect.setBounds(300,300,200,100);
		percentageCorrect.setBackground(new Color(50,50,50));
		percentageCorrect.setForeground(new Color(255,255,255));
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

		startGame();
    }

	/*
	 * Starts or resets the game with the selected game type
	 */
	public void startGame(){
		countryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "countryGame";
			}
		});
		capitalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "capitalGame";
			}
		});
		continentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "continentGame";
			}
		});
		flagButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "flagGame";
			}
		});
		
		textField.setText("Choose a game type");
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);
		correctCount.setVisible(false);
		percentageCorrect.setVisible(false);
		labelA.setFont(new Font("Sans Serif",Font.PLAIN,35));
		labelB.setFont(new Font("Sans Serif",Font.PLAIN,35));
		labelC.setFont(new Font("Sans Serif",Font.PLAIN,35));
		labelD.setFont(new Font("Sans Serif",Font.PLAIN,35));
		labelA.setIcon(null);
		labelB.setIcon(null);
		labelC.setIcon(null);
		labelD.setIcon(null);
		index = 0;
		correctGuesses = 0;
		seconds = 10;

		nextQuestion();
	}

	/*
	 * Called to move on to the next question, gets four random countries and shuffles them and displays the questions
	 * and four answers to be chosen from by the user
	 */
    public void nextQuestion() {
		Set<Country> uniqueCountries = Stream.generate(this::getRandomCountry)
        									.distinct()
        									.limit(4)
        									.collect(Collectors.toSet());
		Country[] countries = uniqueCountries.toArray(new Country[0]);
		if (gameType != null && gameType.equals("continentGame")){
			Set<Country> uniqueContinents = new HashSet<>();
    		Set<String> usedContinents = new HashSet<>();
			while (uniqueContinents.size() < 4) {
				Country country = getRandomCountry();
				String continent = country.continent();
				if (!usedContinents.contains(continent)) {
					uniqueContinents.add(country);
					usedContinents.add(continent);
				}
			}
			countries = uniqueContinents.toArray(new Country[0]);
		}

		Country countryA = countries[0];
		Country countryB = countries[1];
		Country countryC = countries[2];
		Country countryD = countries[3];

		List<Country> options = new ArrayList<>();
    	options.add(countryA);
    	options.add(countryB);
    	options.add(countryC);
    	options.add(countryD);

    	Collections.shuffle(options);
		answerCountry = countryA;

		if(index >= totalQuestions) {
			results();
		}
		else {
			if (gameType == null){
				return;
			}
			//Country Game
			if (gameType.equals("countryGame")){
				textField.setText("Question "+(index+1));
				textArea.setText(countryA.capital() + " is the capital of which country?");
				labelA.setText(options.get(0).name());
				labelB.setText(options.get(1).name());
				labelC.setText(options.get(2).name());
				labelD.setText(options.get(3).name());
			}
			//Capital Game
			if (gameType.equals("capitalGame")){
				textField.setText("Question "+(index+1));
				textArea.setText("What is the capital of "+countryA.name() + "?");
				labelA.setText(options.get(0).capital());
				labelB.setText(options.get(1).capital());
				labelC.setText(options.get(2).capital());
				labelD.setText(options.get(3).capital());
			}
			// Continent Game
			if (gameType.equals("continentGame")){
				textField.setText("Question "+(index+1));
				textArea.setText("What continent is " + countryA.name() + " in?");
				labelA.setText(options.get(0).continent());
				labelB.setText(options.get(1).continent());
				labelC.setText(options.get(2).continent());
				labelD.setText(options.get(3).continent());
			}
			// Continent Game
			if (gameType.equals("flagGame")){
				textField.setText("Question "+(index+1));
				textArea.setText("What is the flag of "+countryA.name() + "?");
				ImageIcon imageA = new ImageIcon(CountryLoader.getFilePath("flags/"+options.get(0).id()+".png"));
				ImageIcon imageB = new ImageIcon(CountryLoader.getFilePath("flags/"+options.get(1).id()+".png"));
				ImageIcon imageC = new ImageIcon(CountryLoader.getFilePath("flags/"+options.get(2).id()+".png"));
				ImageIcon imageD = new ImageIcon(CountryLoader.getFilePath("flags/"+options.get(3).id()+".png"));
				labelA.setFont(new java.awt.Font("Lucida Grande", 1, 0));
				labelB.setFont(new java.awt.Font("Lucida Grande", 1, 0));
				labelC.setFont(new java.awt.Font("Lucida Grande", 1, 0));
				labelD.setFont(new java.awt.Font("Lucida Grande", 1, 0));
				labelA.setText(options.get(0).id());
				labelB.setText(options.get(1).id());
				labelC.setText(options.get(2).id());
				labelD.setText(options.get(3).id());
				labelA.setIcon(imageA);
				labelB.setIcon(imageB);
				labelC.setIcon(imageC);
				labelD.setIcon(imageD);
			}

			buttonA.setVisible(true);
        	buttonB.setVisible(true);
        	buttonC.setVisible(true);
        	buttonD.setVisible(true);
			buttonA.setEnabled(true);
			buttonB.setEnabled(true);
			buttonC.setEnabled(true);
			buttonD.setEnabled(true);
			countryButton.setEnabled(true);
			capitalButton.setEnabled(true);
			continentButton.setEnabled(true);
			flagButton.setEnabled(true);
            timer.start();
		}
	}

	/*
	 * Main method which calls the constructor for the Quiz Game GUI
	 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizGameGUI();
            }
        });
    }

	/*
	 * Listens for presses of buttons on the screen and responds accordingly
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		countryButton.setEnabled(false);
		capitalButton.setEnabled(false);
		continentButton.setEnabled(false);
		flagButton.setEnabled(false);
			
		if(e.getSource() == buttonA) {
            if(getAnswer().equals(labelA.getText())) {correctGuesses++;}
			displayAnswer();
        }
        if(e.getSource() == buttonB) {
            if(getAnswer().equals(labelB.getText())) {correctGuesses++;}
			displayAnswer();
        }
        if(e.getSource() == buttonC) {
            if(getAnswer().equals(labelC.getText())) {correctGuesses++;}
			displayAnswer();
        }
        if(e.getSource() == buttonD) {
            if(getAnswer().equals(labelD.getText())) {correctGuesses++;}
			displayAnswer();
        }
		if(e.getSource() == countryButton) {
            gameType = "countryGame";
			startGame();
        }
		if(e.getSource() == capitalButton) {
            gameType = "capitalGame";
			startGame();
        }
		if(e.getSource() == continentButton) {
            gameType = "continentGame";
			startGame();
        }
		if(e.getSource() == flagButton) {
            gameType = "flagGame";
			startGame();
        }
    }

	/*
	 * Displays the correct answer in green text and incorrect answers in red text to let the user know if their
	 * answer was correct or not (For flag game, the incorrect answers become red X's).
	 * Also restarts the timer and shows the answer if timer is up
	 */
    public void displayAnswer() {
		timer.stop();
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		ImageIcon redX = new ImageIcon(CountryLoader.getFilePath("flags/redX.png"));
		
		if(!getAnswer().equals(labelA.getText())){
			labelA.setForeground(new Color(255,0,0));
			if (gameType.equals("flagGame")){labelA.setIcon(redX);}
		}
        else {labelA.setForeground(new Color(25,255,0));}

		if(!getAnswer().equals(labelB.getText())){
			labelB.setForeground(new Color(255,0,0));
			if (gameType.equals("flagGame")){labelB.setIcon(redX);}
		}
        else {labelB.setForeground(new Color(25,255,0));}

		if(!getAnswer().equals(labelC.getText())){
			labelC.setForeground(new Color(255,0,0));
			if (gameType.equals("flagGame")){labelC.setIcon(redX);}
		}
        else {labelC.setForeground(new Color(25,255,0)); }

		if(!getAnswer().equals(labelD.getText())){
			labelD.setForeground(new Color(255,0,0));
			if (gameType.equals("flagGame")){labelD.setIcon(redX);}
		}
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
				countryButton.setEnabled(true);
				capitalButton.setEnabled(true);
				continentButton.setEnabled(true);
				flagButton.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}

	/*
	 * Shows the results of the game as a fraction and percentage and allows a new game to be played
	 */
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
        secondsLeft.setText("");
		
		result = (int)((correctGuesses/(double) totalQuestions)*100);
		
		textField.setText("RESULTS");
		textArea.setText("Choose another game to restart");
		labelA.setText("");
		labelB.setText("");
		labelC.setText("");
		labelD.setText("");
		labelA.setIcon(null);
		labelB.setIcon(null);
		labelC.setIcon(null);
		labelD.setIcon(null);
		
		correctCount.setText("("+correctGuesses+"/"+totalQuestions+")");
		percentageCorrect.setText(result+"%");
		correctCount.setVisible(true);
		percentageCorrect.setVisible(true);
		
		frame.add(correctCount);
		frame.add(percentageCorrect);

		
		gameType = null;
		countryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "countryGame";
				startGame();
			}
		});
		capitalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "capitalGame";
				startGame();
			}
		});
		continentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "continentGame";
				startGame();
			}
		});
		flagButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameType = "flagGame";
				startGame();
			}
		});
	}

	/*
	 * Gets a random country from the country list
	 */
	private Country getRandomCountry() {
        int randomInt = random.nextInt(allCountries.size());
        return allCountries.get(randomInt);
    }

	/*
	 * Checks the game mode then gets the correct answer for that mode
	 */
	private String getAnswer() {
		if (gameType.equals("countryGame")){
			return answerCountry.name();
		}
		if (gameType.equals("capitalGame")){
			return answerCountry.capital();
		}
		if (gameType.equals("continentGame")){
			return answerCountry.continent();
		}
		if (gameType.equals("flagGame")){
			return answerCountry.id();
		}
		return "";
	}

}

