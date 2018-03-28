import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class GeneratorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public HashMap<String,String> passwords = new HashMap<String, String>();
	
	
	public GeneratorPanel() {
		passwords.put("bank", "");
		passwords.put("email", "");
		passwords.put("shop", "");
		setSize(700, 650);
		createBankPanel();
		createEmailPanel();
		createShopPanel();
		setLayout(new BorderLayout());
	}
	
	private void createBankPanel() {
		JPanel newPanel = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(null, "Banking", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
		newPanel.setBorder(border);
		
		JLabel bankLabel = new JLabel("Your banking password");
		bankLabel.setBounds(10, 50, 150, 30);
		newPanel.add(bankLabel);
		
		JButton b = createPasswordButton("bank");
		b.setBounds(15, 80, 100, 30);
		
		JTextField vali = new JTextField();
		vali.setBounds(15, 120, 100, 30);
		
		JButton v = createValidateButton("bank", vali.getText());
		v.setBounds(15, 160, 100, 30);
		
		newPanel.add(b);
		newPanel.add(v);
		newPanel.add(vali);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBounds(10, 50, 150, 300);
		add(newPanel);
	}

	private void createEmailPanel() {
		JPanel newPanel = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(null, "Email", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
		newPanel.setBorder(border);
		
		JLabel bankLabel = new JLabel("Your email password");
		bankLabel.setBounds(10, 50, 150, 30);
		newPanel.add(bankLabel);
		
		JButton b = createPasswordButton("email");
		b.setBounds(15, 80, 100, 30);
		JTextField vali = new JTextField();
		vali.setBounds(15, 120, 100, 30);
		JButton v = createValidateButton("email", vali.getText());
		v.setBounds(15, 160, 100, 30);
		newPanel.add(b);
		newPanel.add(v);
		newPanel.add(vali);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBounds(160, 50, 150, 300);
		add(newPanel);
	}
	
	private void createShopPanel() {
		JPanel newPanel = new JPanel();
		TitledBorder border = BorderFactory.createTitledBorder(null, "Shopping", TitledBorder.DEFAULT_POSITION,
				TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14), Color.BLACK);
		newPanel.setBorder(border);
		
		JLabel bankLabel = new JLabel("Your shopping password");
		bankLabel.setBounds(10, 50, 150, 30);
		newPanel.add(bankLabel);
		
		JButton b = createPasswordButton("shop");
		b.setBounds(15, 80, 100, 30);
		JTextField vali = new JTextField();
		vali.setBounds(15, 120, 100, 30);
		JButton v = createValidateButton("shop", vali.getText());
		v.setBounds(15, 160, 100, 30);
		newPanel.add(b);
		newPanel.add(v);
		newPanel.add(vali);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBounds(320, 50, 150, 300);
		add(newPanel);
	}
	// read from text file and store every line into a list
	private List<String[]> readBooklet() {
		List<String[]> lis = new ArrayList<String[]>();
		try (BufferedReader br = new BufferedReader(new FileReader("secret.txt"))) {
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
			randomRow = (int) (Math.ceil(Math.random() * (passwordList.size() + 1)));
			randomWord = (int) (Math.ceil(Math.random() * (passwordList.get(randomRow).length)));
			password += intFormat(randomRow);
			password += intFormat(randomWord);
			actualPassword += passwordList.get(randomRow)[randomWord];
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
	private JButton createValidateButton(String key, String testPassword) {
		JButton button = new JButton("Validate");
		button.addActionListener(new ActionListener() {
			@Override
			// Display your test result in a new window
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "your password is " + testPassword(passwords.get(key), testPassword),
						"Validate", JOptionPane.PLAIN_MESSAGE);
			}
		});
		return button;
	}

	// Test your password
	private String testPassword(String password, String test) {
		if (password.equals(test))
			return "correct";
		else
			return "wrong";
	}

}
