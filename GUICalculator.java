/*
 * Nripdeep singh
 * 12 August,2017
 * GUI Calculator
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUICalculator extends JFrame implements ActionListener{
	// field
	private Calculator c;
	private JMenuBar menuBar; 
	private JMenu mnuFile; 
	private JMenu mnuConvert;
	private JMenu mnuHelp;
	
	private JMenuItem mnuExit;
	
	private JRadioButton mnuHex;
	private JRadioButton mnuDec;
	private JRadioButton mnuOct;
	private JRadioButton mnuBin;
	private JMenuItem mnuHTU;
	private JMenuItem mnuAbout;
	
	private JTextField display;
	
	private JPanel keyboard;
	
	private JButton[] btn;
	
	private Font font ;
	
	
	//constructor
	public GUICalculator()
	{
		super("Calculator");
		
		c = new Calculator();
		
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 22);
		
		setLayout(new BorderLayout());
		buildMenuBar();
		buildDisplay();
		buildKeyboard();
        // Set up methods for the frame
		setSize(300, 365);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	/**
	* Method Name: builMenuBar
	* Purpose: to build Menubar
	* Accepts: nothing
	* Returns: nothing
	*/
	private void buildMenuBar()
	{
		menuBar = new JMenuBar(); 
		buildFile();
		buildConvert();
		buildHelp();
		setJMenuBar(menuBar);
	}

	/**
	* Method Name: buildFile
	* Purpose: to build File menu
	*/
	
	private void buildFile(){
		mnuFile = new JMenu("File");
		
		mnuExit = new JMenuItem("Exit");
		mnuExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}// End of actionPerformed method
		}// End of inner class
		);
		
		mnuFile.add(mnuExit);
		menuBar.add(mnuFile);
	}
	
	/**
	* Method Name: buildConvert
	* Purpose: to build Convert menu
	* Accepts: nothing
	* Returns: nothing
	*/
	
	
	private void buildConvert(){
		mnuConvert = new JMenu("Convert");
		
		mnuHex = new JRadioButton("Hex");
		mnuHex.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				
				if(c.getOperand() == "")
				c.setOperand(display.getText());
				
				String s = c.getOperand();
				boolean haveD = false;
				for (int i = 0; i < s.length(); i++){
					
					if (s.charAt(i) == '.')
						haveD = true;
				}
				
				
				
				if(!haveD){
					display.setText(c.convertHex(c.getOperand()));
				}else{
					String x = s.substring(0,s.indexOf('.'));
					String z = s.substring(s.indexOf('.') + 1);
					
					display.setText(c.convertHex(x) + "." + c.convertHex(z));
					
				}
					
			}// End of actionPerformed method
		}// End of inner class
		);		
		mnuDec = new JRadioButton("Dec");
		mnuDec.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				display.setText(c.convertDec(c.getOperand()));
			}// End of actionPerformed method
		}// End of inner class
		);
		mnuOct = new JRadioButton("Oct");
		mnuOct.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				
				if(c.getOperand() == "")
				c.setOperand(display.getText());
				
				String s = c.getOperand();
				
				boolean haveD = false;
				for (int i = 0; i < s.length(); i++){
					
					if (s.charAt(i) == '.')
						haveD = true;
				}
				
				if(!haveD){
					display.setText(c.convertOct(c.getOperand()));
				}else{
					String x = s.substring(0,s.indexOf('.'));
					String z = s.substring(s.indexOf('.') + 1);
					
					display.setText(c.convertOct(x) + "." + c.convertOct(z));
					
				}
			}
		}
		);
		
		mnuBin = new JRadioButton("Bin");
		mnuBin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				boolean haveD = false;
				
				if(c.getOperand() == "")
				c.setOperand(display.getText());
				
				String s = c.getOperand();

				for (int i = 0; i < s.length(); i++){
					if (s.charAt(i) == '.')
						haveD = true;
				}
				if (!haveD){
					display.setText(c.convertBin(s));
				} else if (haveD){

					String x = s.substring(0, s.indexOf('.'));
					String z = s.substring(s.indexOf('.') + 1);

					display.setText(c.convertBin(x) + "." + c.convertBin(z));
				}

			}// End of actionPerformed method
		}// End of inner class
		);
		ButtonGroup group = new ButtonGroup();
		group.add(mnuHex);
		group.add(mnuDec);
		group.add(mnuOct);
		group.add(mnuBin);
		
		mnuConvert.add(mnuHex);
		mnuConvert.add(mnuDec);
		mnuConvert.add(mnuOct);
		mnuConvert.add(mnuBin);
		menuBar.add(mnuConvert);
	}
	/**
	* Method Name: buildHelp
	* Purpose: to build Help menu
	*/
	private void buildHelp(){
		mnuHelp = new JMenu("Help");
		
		mnuHTU = new JMenuItem("How To Use");
		mnuAbout = new JMenuItem("About");
		
		mnuHelp.add(mnuHTU);
		mnuHelp.add(mnuAbout);
		
		menuBar.add(mnuHelp);
	}

	/**
	* Method Name: buildDisplay
	* Purpose: to build text field

	*/
	private void buildDisplay(){
		display = new JTextField("0.0"); 
		display.setFont(font);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);
		display.setBorder(BorderFactory.createLineBorder(Color.black));
		display.setBackground(Color.white);
		
		add(display , BorderLayout.NORTH);
	}
	/**
	* Method Name: buildKeyboard
	* Purpose: to build Keyboard
	* Accepts: nothing
	* Returns: nothing
	*/
	
	private void buildKeyboard(){
		keyboard = new JPanel();
		btn = new JButton[24];
		
		keyboard.setLayout(new GridLayout(6, 4, 5, 5));
		
		for( int i = 0 ; i < 24 ; i++){
			btn[i] = new JButton(" ");
		}
		btn[0] = new JButton("C");
		btn[1] = new JButton("BACKSPACE");
		btn[2] = new JButton("%");
		btn[3] = new JButton("+/-");
		btn[4] = new JButton("X^2");
		btn[5] = new JButton("X^1/2");
		
		btn[8] = new JButton(7 + "");
		btn[9] = new JButton(8 + "");
		btn[10] = new JButton(9 + "");
		btn[12] = new JButton(4 + "");
		btn[13] = new JButton(5 + "");
		btn[14] = new JButton(6 + "");
		btn[16] = new JButton(1 + "");
		btn[17] = new JButton(2 + "");
		btn[18] = new JButton(3 + "");
		btn[21] = new JButton(0 + "");
		btn[22] = new JButton(".");
		
		btn[7] = new JButton("/");
		btn[11] = new JButton("x");
		btn[15] = new JButton("-");
		btn[19] = new JButton("+");
		btn[23] = new JButton("=");
		
		for( int i = 0 ; i < 24 ; i++){
			
			keyboard.add(btn[i]);
			btn[i].addActionListener(this);
			btn[i].setFont(font);;
		}
		
		add(keyboard, BorderLayout.CENTER);
	}
	/**
	* Method Name: setLookAndFeel
	* Purpose: to set the look and feel
	* Accepts: nothing
	* Returns: nothing
	*/
	private static void setLookAndFeel(){
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	/**
	* Method Name: main
	* Purpose: create and frame object
	* Accepts: nothing
	* Returns: nothing
	*/

	public static void main(String[] args){
		
		setLookAndFeel();
		
		GUICalculator frame = new GUICalculator();
	}// End of main method


	/**
	* Method Name: actionPerformed
	* Purpose: create action listener
	* Accepts: ActionEvent 
	* Returns: nothing
	*/
	public void actionPerformed(ActionEvent e){
		for(int i = 0; i < btn.length; i++){
			
			if(e.getSource() == btn[i]){
				
				
				switch(i){
				
				// c
				case 0: c.clear();
				        display.setText("0.0");
				        c.getList().clear();
				        break;
				// backspace
				case 1:	
					
						c.backspace();
					
				        display.setText(c.getOperand());
				        break;
				// %
				case 2: c.setOperand(c.findPercentage(display.getText()));
				
				        c.getList().add(c.findPercentage(display.getText()));
				        display.setText(c.getOperand());
				        c.clear();
				        break;
				//+/-
				case 3:  c.setOperand(display.getText()); 
					c.setOperand(c.togglePlusMinus((Double.parseDouble(display.getText()) > 0)));
				        
				       
					    display.setText(c.getOperand());
					    
				        break;
				     
				// X^2
				case 4: c.setOperand(c.findSquared(display.getText()));
				        c.getList().add(c.getOperand());
				
				        display.setText(c.getOperand());
				        c.clear();
		                break;
				// x ^ 1/2
				case 5: c.setOperand(c.findSquareRoot(display.getText()));
				        c.getList().add(c.getOperand());
	
		                display.setText(c.getOperand());
		                c.clear();
                        break;
				// /
				case 7: c.buildExpression("/");
				        
				        break;
				// 7
				case 8: try{
						c.buildOperand("7");
					} catch (LongOprandException e1)
					{
						
						System.out.println(e1.getMessage());
					}
				        display.setText(c.getOperand());
				        break;
				        
				//8        
				case 9: try{
						c.buildOperand("8");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
		                display.setText(c.getOperand());
		                break;
		                
		        // 9        
				case 10: try{
						c.buildOperand("9");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
                        display.setText(c.getOperand());
                        break;
                   
                 // x    
				case 11: c.buildExpression("x");
		                 
		                 break;
		            
		         // 4
				case 12: try{
						c.buildOperand("4");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
                display.setText(c.getOperand());
                break;
                // 5
				case 13: try{
						c.buildOperand("5");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
                display.setText(c.getOperand());
                break;
				// 6
				case 14: try{
						c.buildOperand("6");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
                display.setText(c.getOperand());
                break;
                
                
                // -
				case 15: c.buildExpression("-");
                
                break;
                
                // 1
				case 16: try{
						c.buildOperand("1");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
                display.setText(c.getOperand());
                
                break;
                
                // 2
				case 17:
					try{
						c.buildOperand("2");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
                display.setText(c.getOperand());
                break;
                
                //3
                
				case 18:
	                try{
						c.buildOperand("3");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
	                display.setText(c.getOperand());
	                break;
	                
	            // +
				case 19: c.buildExpression("+");
               
                break;
                
                // 0
				case 21:
	                try{
						c.buildOperand("0");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
	                display.setText(c.getOperand());
	                break;
                
	            // .
				case 22:
	                try{
	                	if(c.getOperand() == ""){
	                		c.buildOperand("0");
	                	}
						c.buildOperand(".");
					} catch (LongOprandException e1)
					{
						System.out.println(e1.getMessage());
					}
	                display.setText(c.getOperand());
	                c.setDecimalPressed(true);
	                break;
                
	                
	            // =     
				case 23: String s = "";
					try{s = c.calculate() + "";}
				catch(ArithmeticException e1){
					
					System.out.println(e1.getMessage());
					c.clear();
					
				}
					display.setText(s);
				
				
					c.getList().add(s);
   
				}
	
			} 
			
		}
		
	}
	
	
}
