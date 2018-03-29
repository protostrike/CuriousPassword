import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class GeneratorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private HashMap<String,String> passwords = new HashMap<String, String>();
	private HashMap<String,JPasswordField> testPasswords = new HashMap<String,JPasswordField>();
	public Random randomGenerator = new Random();
	
	public GeneratorPanel() {
		passwords.put("bank", "");
		passwords.put("email", "");
		passwords.put("shop", "");
		setSize(400, 400);
		
		JLabel message1 = new JLabel("Click 'Get' for your password. Click 'Validate' to test your password.");
		JLabel message2 = new JLabel("You can find your password by clicking 'Menu'>>'Get Password'.");
		message1.setBounds(10, 20, 500, 20);
		message1.setFont(new Font("Times New Roman",Font.PLAIN,13));
		message2.setBounds(10, 40, 500, 20);
		message2.setFont(new Font("Times New Roman",Font.PLAIN,13));
		add(message1,BorderLayout.PAGE_START);
		add(message2,BorderLayout.PAGE_START);
		
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Generator & Tester", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
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
		
		JLabel test = new JLabel("Enter your password: ");
		test.setBounds(10, 120, 150, 30);
		newPanel.add(test);
		
		JPasswordField vali = new JPasswordField();
		vali.setBounds(10, 160, 100, 30);
		testPasswords.put("bank", vali);
		
		JButton v = createValidateButton("bank");
		v.setBounds(10, 200, 100, 30);
		newPanel.add(v);
		
		vali.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				v.doClick();
			}
		});
		newPanel.add(vali);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(Color.WHITE);
		newPanel.setBounds(50, 100, 200, 300);
		add(newPanel,BorderLayout.LINE_START);
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
		
		JLabel test = new JLabel("Enter your password: ");
		test.setBounds(10, 120, 150, 30);
		newPanel.add(test);
		
		JPasswordField vali = new JPasswordField();
		vali.setBounds(10, 160, 100, 30);
		newPanel.add(vali);
		testPasswords.put("email", vali);
		
		JButton v = createValidateButton("email");
		v.setBounds(10, 200, 100, 30);
		newPanel.add(v);
		
		vali.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				v.doClick();
			}
		});
		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(Color.WHITE);
		newPanel.setBounds(250, 100, 200, 300);
		add(newPanel,BorderLayout.CENTER);
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
		
		JLabel test = new JLabel("Enter your password: ");
		test.setBounds(10, 120, 150, 30);
		newPanel.add(test);
		
		JPasswordField vali = new JPasswordField();
		vali.setBounds(10, 160, 100, 30);
		newPanel.add(vali);
		testPasswords.put("shop", vali);
		
		JButton v = createValidateButton("shop");
		v.setBounds(10, 200, 100, 30);
		newPanel.add(v);
		
		
		vali.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				v.doClick();
			}
		});
		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(Color.WHITE);
		newPanel.setBounds(450, 100, 200, 300);
		add(newPanel,BorderLayout.LINE_END);
	}
	// read from text file and store every line into a list
	private List<String[]> readBooklet() {
		List<String[]> lis = new ArrayList<String[]>();
		InputStream is = PasswordGenerator.class.getResourceAsStream("secret.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
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
			randomRow = 1 + randomGenerator.nextInt(passwordList.size()-1);
			randomWord = 1 + randomGenerator.nextInt(passwordList.get(randomRow).length-1);
			password += intFormat(randomRow);
			password += intFormat(randomWord);
			actualPassword += passwordList.get(randomRow-1)[randomWord-1];
		}
		passwords.put(key, actualPassword);
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
				JOptionPane.showMessageDialog(null, "your password is " + generatePassword(key), "Password",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		return button;
	}

	// Create button for testing password
	private JButton createValidateButton(String key) {
		JButton button = new JButton("Validate");
		button.addActionListener(new ActionListener() {
			@Override
			// Display your test result in a new window
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, testPassword(passwords.get(key), testPasswords.get(key).getText()),
						"Validate", JOptionPane.PLAIN_MESSAGE);
			}
		});
		return button;
	}

	// Test your password
	private String testPassword(String password, String test) {
		if (test=="")
			return "Password can not be empty";
		else if(!password.equals(test))
			return "Wrong password!!!";
		else
			return "Correct password!!!";
	}
}