import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GetterPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GetterPanel() {
		setPanel();
		setSize(700, 650);
		setLayout(new BorderLayout());
		setVisible(true);
	}

	private void setPanel() {
		JLabel input = new JLabel("Enter your number here");
		input.setBounds(100, 20, 150, 30);
		
		JTextField number = new JTextField();
		number.setBounds(100, 60, 150, 30);
		
		JButton b = new JButton("Get");
		b.setBounds(100, 100, 150, 30);
		
		JLabel output = new JLabel("Your password is here");
		output.setBounds(100, 140, 150, 30);
		
		JTextArea password = new JTextArea();
		password.setBounds(100, 170, 150, 30);
		password.setEditable(false);
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				password.setText(getPassword(number.getText()));
			}
		});
		
		add(input);
		add(output);
		add(b);
		add(number);
		add(password);
	}
	// read from text file and store every line into a list
	private static List<String[]> readBooklet() {
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

	private static String getPassword(String password) {
		// TODO: Error checking
		List<String[]> booklet = readBooklet();
		String[] splitedPassword = new String[4];
		String literalPassword = "";
		splitedPassword = password.split("(?<=\\G...)");
		for (int i = 0; i < 4; i += 2) {
			int row = Integer.parseInt(splitedPassword[i]) - 1;
			int word = Integer.parseInt(splitedPassword[i + 1]) - 1;
			literalPassword += booklet.get(row)[word];
		}
		return literalPassword;
	}

	
}
