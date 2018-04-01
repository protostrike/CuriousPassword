import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class TesterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private HashMap<String, JPasswordField> testPasswords = new HashMap<String, JPasswordField>();
	private HashMap<String, JPanel> panelControl = new HashMap<String, JPanel>();
	private HashMap<String, Integer> timers = new HashMap<String, Integer>();
	private HashMap<String, JButton> nexts = new HashMap<String, JButton>();

	public TesterPanel() {
		timers.put("bank", 0);
		timers.put("shop", 0);
		timers.put("email", 0);

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
		TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Tester", TitledBorder.DEFAULT_POSITION, TitledBorder.TOP, new Font("Times New Roman", Font.PLAIN, 14),
				Color.BLACK);
		setBorder(border);
	}

	private void createBankPanel() {
		JPanel newPanel = new JPanel();

		JLabel test = new JLabel("Enter your bank password: ");
		test.setBounds(10, 20, 150, 30);
		newPanel.add(test);

		JPasswordField vali = new JPasswordField();
		vali.setBounds(10, 60, 100, 30);
		testPasswords.put("bank", vali);

		JButton v = createValidateButton("bank");
		v.setBounds(10, 100, 100, 30);
		newPanel.add(v);

		vali.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				v.doClick();
			}
		});
		newPanel.add(vali);


		JButton next = new JButton("Next");
		next.setBounds(10, 180, 100, 30);
		next.setEnabled(false);
		nexts.put("shop", next);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panelControl.get("email").setVisible(true);
			}
			
		});
		newPanel.add(next);

		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(Color.WHITE);
		newPanel.setBounds(250, 100, 200, 300);
		newPanel.setVisible(false);
		add(newPanel, BorderLayout.LINE_START);
		panelControl.put("bank", newPanel);
	}

	private void createEmailPanel() {
		JPanel newPanel = new JPanel();

		JLabel test = new JLabel("Enter your email password: ");
		test.setBounds(10, 20, 150, 30);
		newPanel.add(test);

		JPasswordField vali = new JPasswordField();
		vali.setBounds(10, 60, 100, 30);
		newPanel.add(vali);
		testPasswords.put("email", vali);

		JButton v = createValidateButton("email");
		v.setBounds(10, 100, 100, 30);
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
		newPanel.setVisible(false);
		add(newPanel, BorderLayout.CENTER);
		panelControl.put("email", newPanel);
	}

	private void createShopPanel() {
		JPanel newPanel = new JPanel();

		JLabel test = new JLabel("Enter your shopping password: ");
		test.setBounds(10, 20, 150, 30);
		newPanel.add(test);

		JPasswordField vali = new JPasswordField();
		vali.setBounds(10, 60, 100, 30);
		newPanel.add(vali);
		testPasswords.put("shop", vali);

		JButton v = createValidateButton("shop");
		v.setBounds(10, 100, 100, 30);
		newPanel.add(v);

		vali.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				v.doClick();
			}
		});

		JButton next = new JButton("Next");
		next.setBounds(10, 180, 100, 30);
		next.setEnabled(false);
		nexts.put("shop", next);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				panelControl.get("bank").setVisible(true);
			}
			
		});
		newPanel.add(next);
		
		newPanel.setLayout(new BorderLayout());
		newPanel.setBackground(Color.WHITE);
		newPanel.setBounds(50, 100, 200, 300);
		add(newPanel, BorderLayout.LINE_END);
		panelControl.put("shop", newPanel);
	}

	// Create button for testing password
	private JButton createValidateButton(String key) {
		JButton button = new JButton("Validate");
		button.addActionListener(new ActionListener() {
			@Override
			// Display your test result in a new window
			public void actionPerformed(ActionEvent arg0) {
				if (testPassword(key) == true) {
					nexts.get(key).setEnabled(true);
				}
			}
		});
		return button;
	}

	// Test your password
	private boolean testPassword(String key) {
		String password = MainPage.passwords.get(key);
		String test = testPasswords.get(key).getText();
		Integer time = timers.get(key);
		if (!password.equals(test)) {
			if ((time + 1) < 3) {
				timers.put(key, time + 1);
			} else
				output();
			JOptionPane.showMessageDialog(null, "Wrong password!!!");
			return false;
		} else {
			JOptionPane.showMessageDialog(null, "Correct password!!!");
			return true;
		}
	}

	private void output() {

	}
}
