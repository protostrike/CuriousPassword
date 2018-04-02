import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class GetterPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private JTextField textField;
	
	public GetterPanel() {
		setPanel();
		JLabel message1 = new JLabel("Enter your 12-digit number below, and click 'Get'. ");
		JLabel message2 = new JLabel("Once you have your password, go back to 'Generator' to test it.");
		message1.setBounds(10, 20, 500, 20);
		message2.setBounds(10, 40, 500, 20);
		add(message1, BorderLayout.PAGE_START);
		add(message2, BorderLayout.PAGE_START);

		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Get Password", TitledBorder.DEFAULT_POSITION, TitledBorder.TOP,
				new Font("Times New Roman", Font.PLAIN, 18), Color.BLACK);
		setBorder(border);
		setSize(400, 400);
		setLayout(new BorderLayout());
		setVisible(true);
		addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorAdded(AncestorEvent arg0) {
				// TODO Auto-generated method stub
				textArea.setText("");
				textField.setText("");
			}

			@Override
			public void ancestorMoved(AncestorEvent arg0) {
				// TODO Auto-generated method stub
				textArea.setText("");
				textField.setText("");
			}

			@Override
			public void ancestorRemoved(AncestorEvent arg0) {
				// TODO Auto-generated method stub
				textArea.setText("");
				textField.setText("");
			}
		});
	}

	private void setPanel() {
		JPanel newPanel = new JPanel();

		JLabel input = new JLabel("Enter your number: ");
		input.setBounds(10, 20, 150, 30);

		JTextField number = new JTextField();
		number.setBounds(10, 50, 150, 30);
		textField = number;
		
		JButton b = new JButton("Get");
		b.setBounds(10, 100, 150, 30);

		JLabel output = new JLabel("Your password is: ");
		output.setBounds(10, 140, 150, 30);

		JTextArea password = new JTextArea();
		password.setBounds(10, 170, 150, 30);
		password.setEditable(false);
		textArea = password;
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				password.setText(getPassword(number.getText()));
			}
		});
		number.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				b.doClick();
			}
		});
		newPanel.add(input);
		newPanel.add(output);
		newPanel.add(b);
		newPanel.add(number);
		newPanel.add(password);
		newPanel.setLayout(new BorderLayout());
		newPanel.setBounds(50, 100, 200, 300);
		add(newPanel, BorderLayout.CENTER);
	}

	// read from text file and store every line into a list
	private static List<String[]> readBooklet() {
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

	private static String getPassword(String password) {
		// TODO: Error checking
		if (password.length() != 12)
			return "Incorrect input\nCheck your number";
		List<String[]> booklet = readBooklet();
		String[] splitedPassword = new String[4];
		String literalPassword = "";
		splitedPassword = password.split("(?<=\\G...)");
		try {
			for (int i = 0; i < 4; i += 2) {
				int row = Integer.parseInt(splitedPassword[i]) - 1;
				int word = Integer.parseInt(splitedPassword[i + 1]) - 1;
				literalPassword += decode(booklet.get(row)[word]);
			}
			return literalPassword;
		} catch (NumberFormatException e) {
			return "Nondigital Input.\nCheck your input";
		}
	}
	//Decode word
		private static String decode(String word) {
			String output = "";
			output = new String(Base64.getDecoder().decode(word));
			return output;
		}
}