import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import javax.swing.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class passwordGenerator {

	public static void main(String[] args) {
		createSwing();
	}
	//read from text file and store every line into a list
	private static List<String[]> readBooklet(){
		List<String[]> lis = new ArrayList();
		try(BufferedReader br = new BufferedReader(new FileReader("secret.txt"))){
			for(String line; (line = br.readLine())!= null; ) {
				lis.add(line.trim().split("\\s+"));
			}
		}
		catch(Exception e) {
			System.out.println("File Not Found");
		}
		return lis;
	}
	//Generate encrypted password and store actual password
	private static String generatePassword(String actualPassword) {
		List<String[]> passwordList = readBooklet();
		int randomRow, randomWord;
		String password = "";
		for(int i = 0; i<2; i++) {
			randomRow = (int) (Math.random()*(passwordList.size()+1));
			randomWord = (int) (Math.random()*((passwordList.get(randomRow).length)+1));
			password += intFormat(randomRow);
			password += intFormat(randomWord);
			actualPassword += passwordList.get(randomRow)[randomWord];
		}
		return password;
	}
	
	protected static String intFormat(int integer) {
		if(integer<10)
			return "00"+integer;
		else if(integer<100)
			return "0"+integer;
		else
			return Integer.toString(integer);
	}
	
	private static void createSwing() {
		String bankPassword = "";
		JFrame main = new JFrame();
		JLabel bankLabel = new JLabel("Your banking password");
		bankLabel.setBounds(10, 10, 150, 30);
		JButton b = createPasswordButton(bankPassword);
		b.setBounds(20, 40, 100, 30);
		JTextField vali = new JTextField();
		vali.setBounds(20, 80, 100, 30);
		JButton v = createValidateButton(bankPassword, vali.getText());
		v.setBounds(20, 120, 100, 30);
		JPanel newPanel = new JPanel();
		newPanel.add(bankLabel);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBounds(0, 0, 150, 450);
		main.add(b);
		main.add(v);
		main.add(vali);
		main.add(newPanel);
		main.setSize(800, 600);
		main.setLayout(null);
		main.setResizable(false);
		main.setTitle("Password Generator");
		main.setVisible(true);
	}
	//create button with eventListener
	//Note: you need a textArea to link the listener
	protected static JButton createPasswordButton(String actualPassword) {
		JButton button = new JButton("Get");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "your password is "+generatePassword(actualPassword), "pPassword", JOptionPane.PLAIN_MESSAGE);
			}
		});
		return button;
	}
	protected static JButton createValidateButton(String password, String testPassword) {
		JButton button = new JButton("Validate");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "your password is "+testPassword(password, testPassword), "Validate", JOptionPane.PLAIN_MESSAGE);
			}
		});
		return button;
	}
	protected static String testPassword(String password, String test) {
		if(password.equals(test))
			return "correct";
		else
			return "wrone";
	}
}
