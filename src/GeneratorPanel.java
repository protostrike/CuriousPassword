import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class GeneratorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public Random randomGenerator = new Random();
	private HashMap<String, JTextField> testPasswords = new HashMap<String, JTextField>();
	private HashMap<String, JPanel> panels = new HashMap<>();
	
	public GeneratorPanel() {
		JLabel message1 = new JLabel("Click 'Get' for your password. This is not your actual password. You need to find your password by clicking 'Menu'>>'Get Password'." );
		JLabel message2 = new JLabel("The 12-digit number is actually four three-digit parts, the first and second, and the third and fourth determine two words respectively.");
		JLabel message3 = new JLabel("Try it in the box below, then click 'Confirm' to confirm your password and move to next one.");
		JLabel message4 = new JLabel("Just click 'Get' again if you don't like this password");
		JLabel message5 =  new JLabel("Once you click 'Confirm', you cannot get new password again.");
		message1.setBounds(10, 20, 850, 25);
		message1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		message2.setBounds(10, 45, 850, 25);
		message2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		message3.setBounds(10, 70, 850, 25);
		message3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		message4.setBounds(10, 95, 850, 25);
		message4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		message5.setBounds(10, 120, 850, 25);
		message5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(message1, BorderLayout.PAGE_START);
		add(message2, BorderLayout.PAGE_START);
		add(message3, BorderLayout.PAGE_START);
		add(message4, BorderLayout.PAGE_START);
		add(message5, BorderLayout.PAGE_START);

		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Generator", TitledBorder.DEFAULT_POSITION, TitledBorder.TOP,
				new Font("Times New Roman", Font.PLAIN, 18), Color.BLACK);
		setBorder(border);

		createBankPanel();
		createEmailPanel();
		createShopPanel();
		addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				// TODO Auto-generated method stub
				testPasswords.get("bank").setText("");
				testPasswords.get("email").setText("");
				testPasswords.get("shop").setText("");
			}

			@Override
			public void ancestorMoved(AncestorEvent arg0) {
				// TODO Auto-generated method stub
				testPasswords.get("bank").setText("");
				testPasswords.get("email").setText("");
				testPasswords.get("shop").setText("");
			}

			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
				// TODO Auto-generated method stub
				testPasswords.get("bank").setText("");
				testPasswords.get("email").setText("");
				testPasswords.get("shop").setText("");
			}
		});
		setLayout(new BorderLayout());
	}

	private void createBankPanel() {
		JPanel newPanel = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(null, "Banking", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
		newPanel.setBorder(border);

		JLabel bankLabel = new JLabel("Your banking password");
		bankLabel.setBounds(10, 20, 150, 30);
		newPanel.add(bankLabel);

		JButton b = createPasswordButton("bank");
		b.setBounds(10, 50, 100, 30);
		newPanel.add(b);

		JLabel test = new JLabel("Test it here");
		test.setBounds(10, 80, 150, 30);
		newPanel.add(test);

		JTextField testpw = new JTextField();
		testpw.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "test", "start"));
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}

		});
		testpw.setBounds(10, 120, 150, 30);
		testPasswords.put("bank", testpw);
		newPanel.add(testpw);

		JButton tb = createTestButton("bank");
		tb.setBounds(10, 160, 100, 30);
		newPanel.add(tb);

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				b.setEnabled(false);
				panels.get("email").setVisible(true);
			}

		});
		confirm.setBounds(10, 200, 100, 30);
		newPanel.add(confirm);
		newPanel.setBounds(10, 150, 300, 500);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(new Color(129,165,148));
		panels.put("bank", newPanel);
		add(newPanel, BorderLayout.LINE_START);
	}

	private void createEmailPanel() {
		JPanel newPanel = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(null, "Email", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
		newPanel.setBorder(border);

		JLabel bankLabel = new JLabel("Your email password");
		bankLabel.setBounds(10, 20, 150, 30);
		newPanel.add(bankLabel);

		JButton b = createPasswordButton("email");
		b.setBounds(10, 50, 100, 30);
		newPanel.add(b);

		JLabel test = new JLabel("Test it here");
		test.setBounds(10, 80, 150, 30);
		newPanel.add(test);

		JTextField testpw = new JTextField();
		testpw.setBounds(10, 120, 150, 30);
		testpw.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "test", "start"));
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}

		});
		testPasswords.put("email", testpw);
		newPanel.add(testpw);

		JButton tb = createTestButton("email");
		tb.setBounds(10, 160, 100, 30);
		newPanel.add(tb);

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				b.setEnabled(false);
				panels.get("shop").setVisible(true);
			}

		});
		confirm.setBounds(10, 200, 100, 30);
		newPanel.add(confirm);
		newPanel.setBounds(310, 150, 300, 500);
		newPanel.setLayout(new BorderLayout());
		panels.put("email", newPanel);
		newPanel.setVisible(false);
		newPanel.setBackground(new Color(129,165,148));
		add(newPanel, BorderLayout.CENTER);
	}

	private void createShopPanel() {
		JPanel newPanel = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(null, "Shopping", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
		newPanel.setBorder(border);

		JLabel bankLabel = new JLabel("Your shopping password");
		bankLabel.setBounds(10, 20, 150, 30);
		newPanel.add(bankLabel);

		JButton b = createPasswordButton("shop");
		b.setBounds(10, 50, 100, 30);
		newPanel.add(b);

		JLabel test = new JLabel("Test it here");
		test.setBounds(10, 80, 150, 30);
		newPanel.add(test);

		JTextField testpw = new JTextField();
		testpw.setBounds(10, 120, 150, 30);
		testpw.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "test", "start"));
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}

		});
		testPasswords.put("shop", testpw);
		newPanel.add(testpw);

		JButton tb = createTestButton("shop");
		tb.setBounds(10, 160, 100, 30);
		newPanel.add(tb);

		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				b.setEnabled(false);
				panels.get("shop").setVisible(true);
			}

		});
		confirm.setBounds(10, 200, 100, 30);
		newPanel.add(confirm);
		newPanel.setBounds(610, 150, 300, 500);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(new Color(129,165,148));
		newPanel.setVisible(false);
		panels.put("shop", newPanel);
		add(newPanel, BorderLayout.LINE_END);
	}

	// read from text file and store every line into a list
	private List<String[]> readBooklet() {
		List<String[]> lis = new ArrayList<String[]>();
		InputStream is = PasswordGenerator.class.getResourceAsStream("encodedSecret.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			for (String line; (line = br.readLine()) != null;) {
				lis.add(line.trim().split("\\s+"));
			}
		} catch (Exception e) {
			System.out.println("File Not Found");
		}
		return lis;
	}

	// Generate encrypted password and store actual password
	private String generatePassword(String key) {
		List<String[]> passwordList = readBooklet();
		int randomRow, randomWord;
		String actualPassword = "";
		String password = "";
		for (int i = 0; i < 2; i++) {
			randomRow = 1 + randomGenerator.nextInt(passwordList.size() - 1);
			randomWord = 1 + randomGenerator.nextInt(passwordList.get(randomRow).length - 1);
			password += intFormat(randomRow);
			password += intFormat(randomWord);
			actualPassword += decode(passwordList.get(randomRow - 1)[randomWord - 1]);
		}
		MainPage.passwords.put(key, actualPassword);
		return password;
	}

	// Modify format of integer so that they are all three-digit
	private String intFormat(int integer) {
		if (integer < 10)
			return "00" + integer;
		else if (integer < 100)
			return "0" + integer;
		else
			return Integer.toString(integer);
	}

	// Create button with eventListener
	private JButton createPasswordButton(String key) {
		JButton button = new JButton("Get");
		button.addActionListener(new ActionListener() {
			@Override
			// Display your password in a new window
			public void actionPerformed(ActionEvent arg0) {
				MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "create", "good"));
				JOptionPane.showMessageDialog(null, "your password is " + generatePassword(key), "Password",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		return button;
	}

	// create test button
	private JButton createTestButton(String key) {
		JButton button = new JButton("Test");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				testPassword(key);
			}

		});
		return button;
	}

	// Test your password
	private void testPassword(String key) {
		String password = MainPage.passwords.get(key);
		String test = testPasswords.get(key).getText();
		if (!password.equals(test)) {
			JOptionPane.showMessageDialog(null, "Wrong password!!!");
			MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "test", "success"));
		} else {
			JOptionPane.showMessageDialog(null, "Correct password!!!");
			MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "test", "failure"));
		}
	}

	// Decode word
	private String decode(String word) {
		String output = "";
		output = new String(Base64.getDecoder().decode(word));
		return output;
	}

}