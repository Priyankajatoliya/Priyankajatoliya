import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class ATMsys extends JFrame implements ActionListener
{
    	JFrame f;
    	JLabel atmNameLabel, l1, l2;
    	JTextField t1;
    	JButton enter, depositButton, withdrawButton, balanceButton, detailsButton, changePinButton, logoutButton, clearButton, cancelButton, receiptButton, 		printReceiptButton;
    	JButton[] digitButtons;
    	double balance = 0; // Initial balance
    	String currentPin;
    	String accountType; // To store the selected account type

   	 boolean isValidPinEntered = false;
    	boolean isAccountTypeSelected = false;
    	StringBuilder amountInput = new StringBuilder(); // To store the entered amount

    	ATMsys()
	{
       		 f = new JFrame("ATM SYSTEM");

        		atmNameLabel = new JLabel("VES ATM");
        		atmNameLabel.setBounds(180, 10, 100, 20);
        		atmNameLabel.setForeground(Color.BLUE);
        		atmNameLabel.setOpaque(true);
        		atmNameLabel.setBackground(Color.WHITE);

        		l1 = new JLabel("Enter Pin:");
        		l1.setBounds(20, 40, 100, 20);
        		t1 = new JTextField(15);
        		t1.setBounds(120, 40, 200, 20);

        		enter = new JButton("Enter");
        		enter.setBounds(330, 30, 80, 40);
        		enter.addActionListener(this);

        		depositButton = new JButton("Deposit");
        		depositButton.setBounds(330, 80, 150, 40);
        		depositButton.addActionListener(this);

        		withdrawButton = new JButton("Withdraw");
        		withdrawButton.setBounds(330, 130, 150, 40);
        		withdrawButton.addActionListener(this);

        		balanceButton = new JButton("Balance");
        		balanceButton.setBounds(330, 180, 150, 40);
        		balanceButton.addActionListener(this);

        		detailsButton = new JButton("Account Details");
        		detailsButton.setBounds(330, 230, 150, 40);
        		detailsButton.addActionListener(this);

        		changePinButton = new JButton("Change PIN");
        		changePinButton.setBounds(330, 280, 150, 40);
        		changePinButton.addActionListener(this);

        		logoutButton = new JButton("Logout");
        		logoutButton.setBounds(330, 330, 150, 40);
        		logoutButton.addActionListener(this);

        		clearButton = new JButton("Clear");
        		clearButton.setBounds(190, 250, 70, 40);
        		clearButton.addActionListener(this);

        		cancelButton = new JButton("Cancel");
        		cancelButton.setBounds(20, 250, 70, 40);
        		cancelButton.addActionListener(this);

        		receiptButton = new JButton("Receipt");
        		receiptButton.setBounds(330, 430, 150, 40);
        		receiptButton.addActionListener(this);

        		printReceiptButton = new JButton("Print Receipt");
        		printReceiptButton.setBounds(330, 480, 150, 40);
        		printReceiptButton.addActionListener(this);

        		digitButtons = new JButton[10];
        		digitButtons[1] = new JButton("1");
        		digitButtons[1].setBounds(20, 100, 80, 40);
        		digitButtons[1].addActionListener(this);
        		f.add(digitButtons[1]);

        		digitButtons[2] = new JButton("2");
        		digitButtons[2].setBounds(100, 100, 80, 40);
        		digitButtons[2].addActionListener(this);
        		f.add(digitButtons[2]);

        		digitButtons[3] = new JButton("3");
        		digitButtons[3].setBounds(180, 100, 80, 40);
        		digitButtons[3].addActionListener(this);
        		f.add(digitButtons[3]);

        		digitButtons[4] = new JButton("4");
        		digitButtons[4].setBounds(20, 150, 80, 40);
        		digitButtons[4].addActionListener(this);
        		f.add(digitButtons[4]);

        		digitButtons[5] = new JButton("5");
        		digitButtons[5].setBounds(100, 150, 80, 40);
        		digitButtons[5].addActionListener(this);
        		f.add(digitButtons[5]);

        		digitButtons[6] = new JButton("6");
        		digitButtons[6].setBounds(180, 150, 80, 40);
        		digitButtons[6].addActionListener(this);
        		f.add(digitButtons[6]);

        		digitButtons[7] = new JButton("7");
        		digitButtons[7].setBounds(20, 200, 80, 40);
        		digitButtons[7].addActionListener(this);
        		f.add(digitButtons[7]);

        		digitButtons[8] = new JButton("8");
        		digitButtons[8].setBounds(100, 200, 80, 40);
        		digitButtons[8].addActionListener(this);
        		f.add(digitButtons[8]);

        		digitButtons[9] = new JButton("9");
        		digitButtons[9].setBounds(180, 200, 80, 40);
        		digitButtons[9].addActionListener(this);
        		f.add(digitButtons[9]);

        		digitButtons[0] = new JButton("0");
        		digitButtons[0].setBounds(100, 250, 80, 40);
        		digitButtons[0].addActionListener(this);
       		 f.add(digitButtons[0]);

        		f.add(atmNameLabel);
        		f.add(l1);
        		f.add(t1);
        		f.add(enter);
        		f.add(depositButton);
        		f.add(withdrawButton);
        		f.add(balanceButton);
        		f.add(detailsButton);
        		f.add(changePinButton);
        		f.add(logoutButton);
        		f.add(clearButton);
        		f.add(cancelButton);
       		f.add(receiptButton);
        		f.add(printReceiptButton);

        		// Set button colors
        		Color buttonColor = new Color(210, 210, 210); // Light gray
        		for (JButton button : digitButtons)
		{
            			button.setBackground(buttonColor);
        		}
        		clearButton.setBackground(buttonColor);
        		cancelButton.setBackground(buttonColor);
        		receiptButton.setBackground(buttonColor);

        		// Set frame background color
        		f.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray

        		f.setSize(500, 600);
        		f.setLayout(null);
        		f.setVisible(true);
        		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}

    	private void printReceipt() 
	{
       	 	// Implement printing logic here
        		System.out.println("Receipt Printed");
        		displayMessage("Receipt was printed");

	}

    	private void copyReceiptToFile(String receiptMessage)
	{
        		try
		{
            			FileWriter fw = new FileWriter("receipt.txt");
            			fw.write(receiptMessage);
            			fw.close();
             
            			System.out.println("Receipt copied to receipt.txt");
            			//JOptionPane.showMessageDialog(f, "Receipt was printed", "Print Receipt", JOptionPane.INFORMATION_MESSAGE);
        		}
		catch (IOException e)
		{
            			System.out.println("Error copying receipt to file: " + e.getMessage());
       		 }
   	 }

    	private void handleDigitButton(Object source)
	{
        		for (int i = 0; i < 10; i++)
		{
           			 if (source == digitButtons[i])
			{
                				amountInput.append(i);
                				t1.setText(amountInput.toString()); // Update the amount field using t1
               				 break;
            			}
        		}
    	}

    	private void performTransaction(boolean isDeposit)
	{
       		 if (amountInput.length() > 0)
		{
            			double transactionAmount = Double.parseDouble(amountInput.toString());
           			 if (isDeposit)
			{
                				deposit(transactionAmount);
           			 } 
			else
			{
                				withdraw(transactionAmount);
            			}
            			amountInput.setLength(0); // Clear the amount input
            			t1.setText(""); // Clear the amount field using t1
        		} 
		else
		{
            			System.out.println("No amount entered");
            			displayMessage("No amount entered");
        		}
   	 }

    	private void deposit(double depositAmount)
	{
        		balance += depositAmount;
        		System.out.println("Deposited: " + depositAmount);
        		displayMessage("Deposited: " + depositAmount);
        		showReceipt("Deposit", depositAmount);
    	}

    	private void withdraw(double withdrawAmount)
	{
        		if (withdrawAmount > balance)
		{
            			System.out.println("Insufficient funds");
            			displayMessage("Insufficient funds");
       		 } 
		else
		{
            			balance -= withdrawAmount;
            			System.out.println("Withdrawn: " + withdrawAmount);
            			displayMessage("Withdrawn: " + withdrawAmount);
            			showReceipt("Withdrawal", withdrawAmount);
        		}
   	 }

    	private void displayMessage(String message)
	{
        		JOptionPane.showMessageDialog(f, message, "Transaction Result", JOptionPane.INFORMATION_MESSAGE);
    	}

    	private void showBalance()
	{
        		System.out.println("Current Balance: " + balance);
        		displayMessage("Current Balance: " + balance);
    	}

    	private boolean isDigitButton(Object source)
	{
        		for (int i = 0; i < 10; i++)
		{
           			 if (source == digitButtons[i])
			{
                				return true;
            			}
       		 }
        		return false;
    	}

    	private void showAccountDetails()
	{
        		// logic to display account details
        		String accountDetails = "Account Holder: Neha Gupta\nAccount Number: 123456789\n";
        		displayMessage(accountDetails);
    	}

    	private void changePin()
	{
        		// logic to change the PIN
        		String newPin = JOptionPane.showInputDialog(f, "Enter new PIN:");
        		if (newPin != null && !newPin.isEmpty())
		{
            			updatePinInFile(currentPin, newPin);
            			currentPin = newPin;
            			displayMessage("PIN changed successfully");
        		}
		 else
		{
            			displayMessage("Invalid PIN. Please try again.");
        		}
    	}

   	 private void updatePinInFile(String oldPin, String newPin)
	{
        		try
		{
            			File inputFile = new File("ATM.txt");
            			File tempFile = new File("temp.txt");

            			BufferedReader br = new BufferedReader(new FileReader(inputFile));
            			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            			String line;
            			while ((line = br.readLine()) != null)
			{
               				 if (line.equals(oldPin))
				{
                    				// Replace the old PIN with the new PIN
                    				bw.write(newPin);
                				}
				else
				{
                    				bw.write(line);
                				}
                				bw.newLine();
           			 }

           			 br.close();
            			bw.close();

            			// Rename the temporary file to the original file
            			if (!inputFile.delete() || !tempFile.renameTo(inputFile))
			{
                				System.out.println("Error updating PIN in file.");
           		 	}
        		} 
		catch (IOException ex)
		{
            			System.out.println(ex);
       		 }
    	}

    	private void logout()
	{
        		// logic for logging out
        		isValidPinEntered = false;
        		isAccountTypeSelected = false; // Reset account type selection
        		currentPin = null;
        		accountType = null;
        		balance = 0;
        		t1.setText("");
        		amountInput.setLength(0);
        		displayMessage("Logged out successfully");
    	}

    	private boolean verifyPin(String enteredPin)
	{
        		try
		{
            			BufferedReader br = new BufferedReader(new FileReader("ATM.txt"));
            			String line;

            			while ((line = br.readLine()) != null)
			{
                				if (line.equals(enteredPin))
				{
                    				br.close();
                    				return true;
                				}
           			 }
            			br.close();
        		} 
		catch (IOException ex)
		{
            			System.out.println(ex);
        		}
        		return false;
    	}

    	private void handleCancelButton()
	{
        		if (amountInput.length() > 0)
		{
            			amountInput.deleteCharAt(amountInput.length() - 1);
            			t1.setText(amountInput.toString());
        		}
    	}

    	private void showReceipt(String transactionType, double transactionAmount)
	{
        		// Get the current date and day
        		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        		String currentDate = dateFormat.format(new Date());
        		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        		String currentDay = dayFormat.format(new Date());

        		// Build the receipt message
        		String receiptMessage = "ATM Name: VES ATM\n";
        		receiptMessage += "-------------------------------------------------------------" + "\n";
        		receiptMessage += "ATM No:" + "P1ENC003" + "\n";
        		receiptMessage += "Date: " + currentDate + "\n";
        		receiptMessage += "Day: " + currentDay + "\n";
        		receiptMessage += "Transaction Type: " + transactionType + "\n";
        		receiptMessage += "Amount: " + transactionAmount + "\n";
        		receiptMessage += "TAX Inc: " + "5.0%" + "\n";
        		receiptMessage += "Current Balance: " + balance + "\n";
        		receiptMessage += "--------------------------------------------------------------" + "\n";
        		receiptMessage += "PLEASE CONTACT BRANCH MANAGER " + "\n" + "FOR ANY CLARIFICATIONS" + "\n";
        		receiptMessage += "********************************************" + "\n";
        		receiptMessage += "THANK YOU FOR BANKING WITH US" + "\n";
        		receiptMessage += "\n";
        		receiptMessage += "\n";
        		receiptMessage += "Visit us at www.ves.co.in" + "\n";

        		JOptionPane.showMessageDialog(f, receiptMessage, "Transaction Receipt", JOptionPane.INFORMATION_MESSAGE);
        		copyReceiptToFile(receiptMessage); // Copy the receipt to a file
    	}

    	public void actionPerformed(ActionEvent e)
	{
       		 if (isValidPinEntered && e.getSource() == printReceiptButton)
		{
            			// Print the receipt
            			printReceipt();
        		} 
		else if (!isAccountTypeSelected) 
		{
            			// Handle account type selection
            			// Validate PIN and ask for account type
            			String enteredPin = t1.getText();
            			if (verifyPin(enteredPin)) 
			{
                				isValidPinEntered = true;
                				currentPin = enteredPin;
                				t1.setText("");  // Clear the text field
                				amountInput.setLength(0); // Clear the amount input
               				 // Prompt user to select account type
                				String[] accountTypes = {"Saving", "Current"};
                				int accountTypeChoice = JOptionPane.showOptionDialog(f, "Select Account Type", "Account Type", 								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, accountTypes, accountTypes[0]);
                				if (accountTypeChoice == 0)
				{
                    				accountType = "Saving";
                				}
				 else if (accountTypeChoice == 1)
				 {
                    				accountType = "Current";
                				}
                				isAccountTypeSelected = true;
                				displayMessage("WELCOME TO VES");
                				System.out.println("Valid PIN entered");
           			 } 
			else 
			{
                				System.out.println("Invalid PIN entered");
                				displayMessage("Invalid PIN. Please try again.");
            			}
        		} 
		else
		 {
            			// Handle other actions after account type is selected
            			if (isValidPinEntered || e.getSource() == enter) 
			{
                				String enteredPin = t1.getText();

                				if (e.getSource() == enter) 
				{
                    				if (verifyPin(enteredPin)) 
					{
                        					isValidPinEntered = true;
                        					currentPin = enteredPin;
                        					t1.setText("");  // Clear the text field
                        					amountInput.setLength(0); // Clear the amount input
                        					displayMessage("WELCOME TO VES");
                        					System.out.println("Valid PIN entered");
                    				} 
					else 
					{
                        					System.out.println("Invalid PIN entered");
                        					displayMessage("Invalid PIN. Please try again.");
                    				}
               	 			} 
				else if (isValidPinEntered && isDigitButton(e.getSource()))
				{
                    				// If a valid PIN is entered and the source is a digit button, append the digit to the amount input
                    				handleDigitButton(e.getSource());
                				} 
				else if (isValidPinEntered && e.getSource() == depositButton) 
				{
                   		 			performTransaction(true);
               	 			} 
				else if (isValidPinEntered && e.getSource() == withdrawButton) 
				{
                    				performTransaction(false);
                				} 
				else if (isValidPinEntered && e.getSource() == balanceButton) 
				{
                    				showBalance();
                				} 
				else if (isValidPinEntered && e.getSource() == detailsButton)
	 			{
                   		 			showAccountDetails();
                				} 
				else if (isValidPinEntered && e.getSource() == changePinButton) 
				{
                    				changePin();
               	 			} 
				else if (isValidPinEntered && e.getSource() == logoutButton) 
				{
                    				logout();
                				} 
				else if (isValidPinEntered && e.getSource() == clearButton) 
				{
                    				t1.setText("");
                    				amountInput.setLength(0);
                			}
	 		else if (isValidPinEntered && e.getSource() == cancelButton)
	 		{
                    			// Cancel the current operation and remove the last entered digit
                    			handleCancelButton();
                			} 
			else if (isValidPinEntered && e.getSource() == receiptButton) 
			{
                    			// Display a receipt for the last transaction
                    			showReceipt("Last Transaction", 0); // Placeholder for last transaction amount
               	 		} 
			else
	 		{
                    			displayMessage("Please enter a valid PIN first");
                			}
            			}
        	 }
	 }
	public static void main(String args[]) 
	{
        		new ATMsys();
    	}
}
