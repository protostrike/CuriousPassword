import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainPage extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private Controller control = new Controller();
	protected static HashMap<String, String> passwords = new HashMap<String, String>();

	public MainPage() throws HeadlessException, IOException {
		control.addObserver(this);
		cardPanel = setCards();
		
		passwords.put("bank", "");
		passwords.put("email", "");
		passwords.put("shop", "");
		// add(cardPanel);
		add(cardPanel, BorderLayout.CENTER);
		setMenu();
		setSize(800, 600);
		setLayout(null);
		setResizable(false);
		setTitle("Curious Password");
		Image img = ImageIO.read(PasswordGenerator.class.getResource("password1.png"));
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
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
		JPanel card2 = new GetterPanel();
		JPanel card3 = new TesterPanel();
		JPanel cards = new JPanel(cardLayout);
		cards.add(card1, "card1");
		cards.add(card2, "card2");
		cards.add(card3, "card3");
		cards.setBounds(10, 10, 720, 500);
		return cards;
	}

	public static void start() {
		System.out.println("Main page working!!!");
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