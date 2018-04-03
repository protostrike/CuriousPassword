import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPage extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private Controller control = new Controller();
	
	protected static HashMap<String, String> passwords = new HashMap<String, String>();
	protected static List<LogDatum> logData = new ArrayList<LogDatum>();
	protected static String name;
	
	public MainPage() throws HeadlessException, IOException {
		control.addObserver(this);
		cardPanel = setCards();
		
		passwords.put("bank", "");
		passwords.put("email", "");
		passwords.put("shop", "");
		// add(cardPanel);
		add(cardPanel, BorderLayout.CENTER);
		setMenu();
		setSize(1024, 768);
		setLayout(null);
		setResizable(false);
		setTitle("Curious Password");
		Image img = ImageIO.read(PasswordGenerator.class.getResource("password1.png"));
		setIconImage(img);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JLabel title = new JLabel("Curious Password");
		title.setFont(new Font(null, Font.ITALIC, 25));
		title.setBounds(300,10,500,30);
		add(title);
		addWindowListener(new WindowListener() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				try {
					writeLogData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		setVisible(true);
		getContentPane().setBackground(new Color(141,182,0));
		getUserName();
		logData.add(new LogDatum(name, new Date(), "login", "start"));
	}

	private void setMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem generator = new JMenuItem("Generator");
		generator.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.changePanel("card1");
			}

		});
		JMenuItem getter = new JMenuItem("Get Password");
		getter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.changePanel("card2");
			}

		});
		JMenuItem tester = new JMenuItem("Tester");
		tester.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				control.changePanel("card3");

			}

		});
		menu.add(generator);
		menu.add(getter);
		menu.add(tester);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

	private JPanel setCards() {
		JPanel card1 = new GeneratorPanel();
		card1.setBackground(new Color(141,182,0));
		JPanel card2 = new GetterPanel();
		card2.setBackground(new Color(141,182,0));
		JPanel card3 = new TesterPanel();
		card3.setBackground(new Color(141,182,0));
		JPanel blank = new JPanel();
		blank.setBackground(new Color(141,182,0));
		JPanel cards = new JPanel(cardLayout);
		cards.add(blank,"blank");
		cards.add(card1, "card1");
		cards.add(card2, "card2");
		cards.add(card3, "card3");
		cards.setBounds(10, 50, 1000, 700);
		return cards;
	}

	private void writeLogData() throws IOException {
		String userHome = System.getProperty("user.home");
		String fileName = userHome+"/Desktop/LogData.csv";
		String newLine = System.getProperty("line.separator");
		MainPage.logData.add(new LogDatum(MainPage.name, new Date(), "exit", "good"));
		File log = new File(fileName);
		if(log.exists()==false)
			log.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(log,true));
		for(LogDatum ld: logData) {
			out.append(ld.toCSV()+newLine);
		}
		out.close();
	}
	
	private void getUserName() {
		String input = JOptionPane.showInputDialog("Give us a nickname");
		if(input==null)
			System.exit(1);
		else {
			name = input;
			control.changePanel("card1");
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		cardLayout.show(cardPanel, arg.toString());
	}

	public class Controller extends Observable {

		public void changePanel(String panel) {
			setChanged();
			notifyObservers(panel);
		}
	}
	
}