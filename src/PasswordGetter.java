import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PasswordGetter {

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
		private static String getPassword(String password) {
			//TODO: Error checking
			List<String[]> booklet = readBooklet();
			String[] splitedPassword = new String[4];
			String literalPassword = "";
			splitedPassword = password.split("(?<=\\G...)");
			for(int i=0; i<4; i+=2) {
				int row = Integer.parseInt(splitedPassword[i])-1;
				int word = Integer.parseInt(splitedPassword[i+1])-1;
				literalPassword += booklet.get(row)[word];
			}
			return literalPassword;
		}
		private static void createSwing() {
			JFrame main = new JFrame();
			JTextArea password = new JTextArea();
			JTextField number = new JTextField();
			JButton b = new JButton("Get");
			b.setBounds(100, 100, 150, 30);
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					password.setText(getPassword(number.getText()));
				}
			});
			password.setBounds(100, 10, 150, 30);
			number.setBounds(100, 150, 150, 30);
			main.add(b);
			main.add(number);
			main.add(password);
			main.setSize(800,600);
			main.setResizable(false);
			main.setTitle("Password Getter");
			main.setVisible(true);
		}
}
