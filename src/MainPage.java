import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainPage extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel cardPanel = new JPanel();
	private CardLayout cardLayout = new CardLayout();
	private Controller control = new Controller();
	
	 
	public MainPage() throws HeadlessException, IOException {
		control.addObserver(this);
		cardPanel = setCards();
		//add(cardPanel);
		add(cardPanel, BorderLayout.CENTER);
		setMenu();
		setSize(800, 600);
		setLayout(null);
		setResizable(false);
		setTitle("Curious Password");
		Image img= ImageIO.read(PasswordGenerator.class.getResource("password1.png"));
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
		menu.add(generator);
		menu.add(getter);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
	
	private JPanel setCards() {
		JPanel card1 = new GeneratorPanel();
		JPanel card2 = new GetterPanel();
		JPanel cards = new JPanel(cardLayout);
		cards.add(card1,"card1");
		cards.add(card2,"card2");
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


	    public void changePanel(String panel){
	        setChanged();
	        notifyObservers(panel);
	    }
	}
}