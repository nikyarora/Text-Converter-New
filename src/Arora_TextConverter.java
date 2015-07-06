import java.awt.*;			//needed for GUI elements
import java.awt.event.*;	//needed for processing events stuff (clicking, etc)

import javax.swing.*;		//JFrame, Timer...

/*********************************************************************************
 * 
 * Name:	Niky Arora
 * Block:	A
 * Date:	9/8/14
 * 	
 * Program A: TextConverter
 * Description:
 * 		This code creates a text pane for entering a "student comment" and 
 * buttons to select whether to convert the text from masculine to feminine 
 * ("he" -> "she" and other related changes) or feminine to masculine. However 
 * it doesn't yet do the conversion. Your job is to add the String manipulation 
 * code to do the conversions. For more details, see the program write-up.
 * 		This application has been split into two classes -- one to do the setup 
 * and the text conversion and one to manage most of the graphics. To do the 
 * required portions of the program you will need to write the two "convert" 
 * methods in the "main" class but don't need to modify the WindowContents 
 * class. If add extras requiring additional conversions and hence more buttons 
 * then you will need to modify the WindowContents as well.
 * 
 * EXTRAS: Converts to pig latin as well
 * 	Rules of pig latin are...
 * 	-if it starts with a vowel then it is the word with -way at the end
 * 		-example: egg--> eggway
 * 	-if it starts with a consonant it is the word with the first letter at the end plus -ay
 * 		-example: dog --> ogday
 * 				
 ***********************************************************************************/
public class Arora_TextConverter extends JFrame
{
	public static final int WINDOW_WIDTH = 500;		// Initial window width
	public static final int WINDOW_HEIGHT = 300;	// Initial window height
	public static String [][] maleToFemale = {{"his", "her"}, 
											{"he", "she"}, 
											{"him", "her"}, 
											{"His", "Her"},
											{"He", "She"},
											{"Him", "Her"},
											{"boy", "girl"}};
	public static String [][] femaleToMale = {{"her", "him"}, 
											{"she", "he"}, 
											{"Her", "Him"}, 
											{"She", "He"},
											{"girl", "boy"}};
	public static Character [] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
	
	public static void main(String[] rags)
	{
		Arora_TextConverter window = new Arora_TextConverter();
	}

	/**
	 * Create the window contents and arrange for callbacks when the
	 *  buttons are clicked.
	 */
	public Arora_TextConverter()
	{
		// Set up the window basics
		setTitle("Text Converter");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create and assemble the components for our window in one JPanel and
		//	add it into our JFrame. Send "this" so button actions can call
		//	methods in this class.
		JPanel windowContents = new ContentsOfWindow(this);		
		add(windowContents);

		setVisible(true);
	}

	/**
	 * Converts the given text from the female student version to the male
	 * student version.
	 * 
	 * @param text		code to convert
	 * @return			text converted from female to male
	 */
	public String convertFemaleToMale(String text)
	{
		//Location of edited text that will be displayed
		String newText = "";
		
		//Keep going through all the text until there is no more
		while(text.length() != 0)
		{
			//Find the next word in the original text,
			//replace it if needed and add it to the new text
			String word = "";
			word = findWord(text);	
			text = text.substring(word.length(), text.length());
			word = checkIfReplaceFemaleToMale(word);
			newText += word;
			
			//Find the next non word in the original text
			//and add it to the new text
			String nonword = "";
			nonword = findNonWord(text);
			text = text.substring(nonword.length(), text.length());
			newText += nonword;
		}
		return newText;
	}

	/**
	 * Converts the given text from the male student version to the female
	 * student version.
	 * 
	 * @param text		code to convert
	 * @return			text converted from male to female
	 */
	public String convertMaleToFemale(String text)
	{
		//Location of edited text that will be displayed
		String newText = "";
		
		//Keep going through all the text
		while(text.length() != 0)
		{
			//Find the next word in the original text,
			//replace it if needed and add it to the new text
			String word = "";
			word = findWord(text);	
			text = text.substring(word.length(), text.length());
			word = checkIfReplaceMaleToFemale(word);
			newText += word;
			
			//Find the next non word in the original text
			//and add it to the new text
			String nonword = "";
			nonword = findNonWord(text);	
			text = text.substring(nonword.length(), text.length());
			newText += nonword;	
		}
		return newText;
	}
	
	/**
	 * Convert the given text to pig latin
	 * 
	 * @param text	code to convert
	 * @return		text converted to pig latin
	 */
	public String convertToPigLatin(String text)
	{
		//Location of edited text that will be displayed
		String newText = "";
		
		//Keep going through all the text
		while(text.length() != 0)
		{
			//Find the next word in the original text,
			//replace it, and add it to the new text
			String word = "";
			word = findWord(text);	
			text = text.substring(word.length(), text.length());
			if(word.length() != 0)
			{
				word = replaceToPigLatin(word);
			}
			newText += word;
			
			//Find the next non word in the original text
			//and add it to the new text
			String nonword = "";
			nonword = findNonWord(text);	
			text = text.substring(nonword.length(), text.length());
			newText += nonword;	
		}
		return newText;
	}
	
	/**
	 * Method that checks if a character is a letter or non letter
	 * 
	 * @param ch	character given to check
	 * @return		returns a boolean
	 */
	public boolean checkIfLetter(Character ch)
	{
		if(ch >= 'a' && ch <= 'z' || ch >= 'A'  && ch <= 'Z')
		{
			return true; //is a letter
		}
		else
		{
			return false; //is not a letter
		}
	}
	
	/**
	 * Replaces the word from female to male if necessary
	 * 
	 * @param word	the word to replace
	 * @return		returns the original or edited word
	 */
	public String checkIfReplaceFemaleToMale(String word)
	{
		//replace the word if necessary
		for(int i = 0; i < femaleToMale.length; i++)
		{
			if(word.equals(femaleToMale[i][0]))
			{
				word = femaleToMale[i][1];
			}
		}
		return word;
	}
	
	/**
	 * Replaces the word from male to female if necessary
	 * 
	 * @param word	the word to replace
	 * @return		returns the orignal or edited word
	 */
	public String checkIfReplaceMaleToFemale(String word)
	{
		//replace the word if necessary
		for(int i = 0; i < maleToFemale.length; i++)
		{
			if(word.equals(maleToFemale[i][0]))
			{
				word = maleToFemale[i][1];
			}
		}
		return word;
	}
	
	/**
	 * Changes the word to pig latin
	 * 
	 * @param word		the word to be replace
	 * @return			returns the edited word
	 */
	public String replaceToPigLatin(String word)
	{
		String newWord = "";
		char letter = word.charAt(0);
		for(int i = 0; i < vowels.length; i++)
		{
			if(letter == (vowels[i]))
			{
				newWord = word + "way";
				return newWord;
			}
		}
		word = word.substring(1, word.length());
		newWord = word + letter + "ay";
		return newWord;	
	}
	
	/**
	 * Finds the next word in the text
	 * 
	 * @param text		the text the user entered
	 * @return			returns the word it finds
	 */
	public String findWord(String text)
	{
		Boolean isLetter;
		String word = "";			
		
		for(int i = 0; i < text.length(); i++)
		{
			char ch = ' ';
			ch = text.charAt(i);
			isLetter = checkIfLetter(ch);	//check if character is a letter
			if(!isLetter)
			{
				word = text.substring(0, i);	//make a substring of the word
				return word;
			}
		}
		return text;
	}
	
	/**
	 * Finds the next non word in the text
	 * @param text	the text the user enters
	 * @return		returns the non word it finds
	 */
	public String findNonWord(String text)
	{
		Boolean isLetter;		
		String nonWord = "";			
		
		for(int i = 0; i < text.length(); i++)
		{
			char ch = ' ';
			ch = text.charAt(i);			
			isLetter = checkIfLetter(ch);	//check if character is a letter
			if(isLetter)
			{
				nonWord = text.substring(0,i);	//make a substring of the nonword
				return nonWord;
			}
		}
		return text;
	}
	
	/**
	 * Paint the window, including all of its child components.
	 *  @param g		Graphics object
	 */
	public void paint(Graphics g)
	{
		// Paints all of the child components (recursively).
		paintComponents(g);
	}
}

/*********************************************************************************
 * 
 * WindowContents class
 * 
 * 		This class is a JPanel containing the scrollable text pane for the
 * text to convert and buttons for the user to initiate the conversions. It
 * detects the button clicks and decides which button was clicked but it does
 * not do the actual conversions. Instead it calls methods in the TextConverter
 * to do the real conversion work. It was written as its own class to
 * separate most of the GUI (graphical user interface) mess from the code
 * in the "main" class to do the non-graphical work.
 * 
 * 		The JPanel contains components which, in turn, can contain addition
 * components. The structure of components set up here is as follows:
 * 		JPanel
 * 			JPanel for label
 * 				Label
 * 				horizontal glue
 * 			JScrollPane for text
 * 			JPanel for buttons
 * 				JButton for "to MALE"
 * 				spacer
 * 				JButton for "to FEMALE"
 * 				spacer
 * 				JButton for "to PIG LATIN"
 * Additional components could be added as needed.
 * 				
 ***********************************************************************************/
class ContentsOfWindow extends JPanel implements ActionListener
{
	private Arora_TextConverter tc;			// TextConverter object containing
										//	the conversion methods to call
	private JButton buttonToMale;		// Button for converting to masculine
	private JButton buttonToFemale;		// Button for converting to feminine
	private JButton buttonToPigLatin;	//Button for converting to pig latin
	private JTextArea textArea;			// Component containing text to convert

	public static final Color LIGHT_BLUE = new Color(180, 210, 255);

	/**
	 * Create the window and arrange for callbacks to actionPerformed
	 * 	when the buttons are clicked.
	 * 
	 * @param tcIn		Object that will contain this JPanel. It has the
	 * 					methods to call to do the actual text conversion
	 */
	
	public ContentsOfWindow(Arora_TextConverter tcIn)
	{
		// Save the pointer to the TextConverter object. We need it since the
		//	methods defining the actions to take when the buttons are clicked
		//	are all in the TextConverter object.
		tc = tcIn;

		// Create a panel containing a label on the left.
		JPanel labelPanel = createLabelPanel();

		// Create a scrollable text area to enter text. The text should
		//	wrap and break at word boundaries when possible.
		JScrollPane scrollPane = createTextPanel();

		// Make a panel containing buttons lined up horizontally.
		JPanel buttonPanel = createButtonPanel();

		// Insert the label, the scrollable text area and the buttons with 
		//	appropriate dividers, border and background color.
		assembleTheWindow(labelPanel, scrollPane, buttonPanel);
		
		// Make sure actionPerformed is called when the buttons are clicked.
		buttonToMale.addActionListener(this);
		buttonToFemale.addActionListener(this);
		buttonToPigLatin.addActionListener(this);
	}

	/**
	 * Create a panel containing a label on the left.
	 * 
	 * @return			the newly created panel with the label
	 */
	private JPanel createLabelPanel()
	{
		// Create the panel with a horizontal layout.
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));
		
		// Create the label
		JLabel label = new JLabel(" Enter Text To Be Converted:");
		
		// Fill the panel, adding "glue" at the end to force the label
		//	to be all the way to the left.
		labelPanel.add(label);
		labelPanel.add(Box.createHorizontalGlue());
		
		labelPanel.setBackground(LIGHT_BLUE);
		
		return labelPanel;
	}
	
	/**
	 * Create a scrollable text area in which the user can enter text. The 
	 * text should wrap and break at word boundaries when possible.
	 * 
	 * @return			the newly created JScrollPane for entering text
	 */
	private JScrollPane createTextPanel()
	{
		// Create the (empty) text area with wrapping a word boundaries.
		textArea = new JTextArea("", 1, 1);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		// Place it in a scroll pane and return the result.
		return new JScrollPane(textArea);
	}

	/**
	 * Create a panel containing a row of buttons.
	 * 
	 * @return			the newly created panel of clickable buttons
	 */
	private JPanel createButtonPanel()
	{
		// Create the panel with horizontal layout
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));

		// Create buttons
		buttonToMale = new JButton("To MALE");
		buttonToFemale = new JButton("To FEMALE");
		buttonToPigLatin = new JButton("To PIG LATIN");
		/**
		 * You may create more buttons here as needed. Declare them class
		 * scope since the actionPerformed thread must refer to them.
		 */

		// Add newly created buttons to the panel with spacers (rigid areas)
		//	for spacing.
		buttonPanel.add(buttonToMale);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(buttonToFemale);
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(buttonToPigLatin);
		/**
		 * If you create more buttons then add them to the JPanel here. It 
		 * looks nice if you put additional spacers between them.
		 */

		buttonPanel.setBackground(LIGHT_BLUE);

		return buttonPanel;
	}
	
	/**
	 * Assemble all of the child components for this panel.
	 * 
	 * @param labelPanel	first component in this window
	 * @param scrollPane	second component in this window
	 * @param buttonPanel	third component in this window
	 */
	private void assembleTheWindow(	JComponent labelPanel, 
									JComponent scrollPane, 
									JComponent buttonPanel)
	{
		// This panel is laid out vertically, with appropriate border and 
		//	background color.
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setBorder(BorderFactory.createEmptyBorder(15,15,15,15));		
		setBackground(LIGHT_BLUE);
		
		// Put the components into the panel with spacers.
		add(labelPanel);
		add(Box.createRigidArea(new Dimension(0,5)));
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(buttonPanel);
	}

	/**
	 * Receive callbacks whenever a button is clicked. See which was clicked
	 * 	and perform the appropriate action.
	 * 
	 * @param e			ActionEvent that contains the source of this action		
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object sourceOfAction = e.getSource();

		if(sourceOfAction == buttonToMale)
		{
			// Convert feminine terms to masculine
			String text = textArea.getText();
			text = tc.convertFemaleToMale(text);
			textArea.setText(text);
		}
		else if(sourceOfAction == buttonToFemale)
		{
			// Convert masculine terms to feminine
			String text = textArea.getText();			
			text = tc.convertMaleToFemale(text);
			textArea.setText(text);
		}
		else if(sourceOfAction == buttonToPigLatin)
		{
			// Convert masculine terms to feminine
			String text = textArea.getText();			
			text = tc.convertToPigLatin(text);
			textArea.setText(text);
		}
		/**
		 * If you create more buttons then add corresponding actions here.
		 */
	}

}