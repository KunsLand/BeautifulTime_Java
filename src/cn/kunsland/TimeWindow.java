package cn.kunsland;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimeWindow implements TimeProcessor{

	private JFrame frame;
	private JEditorPane contentPane;
	private Point initialClick;
	private HtmlController html;
	private TimeListener tl;
	private JLabel label_quit;
	private int width = 590, height = 450;
	private int width_quit = 25, height_quit = 25;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimeWindow window = new TimeWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TimeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(new ImageIcon("icons/time.png").getImage());
		frame.setUndecorated(true);
		
		contentPane = new JEditorPane();
		contentPane.setLayout(null);
		contentPane.setContentType("text/html");
		contentPane.setEditable(false);
		frame.setContentPane(contentPane);
		
		html = new HtmlController();
		contentPane.setText(html.getContent());
		tl = new TimeListener(this);
		tl.start();

		
		try {
			BufferedImage img = ImageIO.read(new File("icons/quit.png"));
			Image img_quit = img.getScaledInstance(width_quit, height_quit,
					Image.SCALE_SMOOTH);
			label_quit = new JLabel(new ImageIcon(img_quit));
			label_quit.setBounds(width - width_quit - 6, 5, width_quit,
					height_quit);
			label_quit.setVisible(false);
			label_quit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent e) {
					label_quit.setVisible(false);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					frame.dispatchEvent(new WindowEvent(frame,
							WindowEvent.WINDOW_CLOSING));
				}
			});
			contentPane.add(label_quit);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (e.getX() > width - width_quit - 6
						&& e.getY() < 5 + height_quit)
					label_quit.setVisible(true);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// get location of Window
				int thisX = frame.getLocation().x;
				int thisY = frame.getLocation().y;

				// Determine how much the mouse moved since the initial click
				int xMoved = e.getX() - initialClick.x;
				int yMoved = e.getY() - initialClick.y;

				// Move window to this position
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				frame.setLocation(X, Y);
			}
		});
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
				frame.getComponentAt(initialClick);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					contentPane.setText(html.getNextContent());
				}
			}
		});
	}

	@Override
	public void processTimeChanged(String time) {
		if(time.endsWith("1")||time.endsWith("6"))
			contentPane.setText(html.getRandomContent());
		else contentPane.setText(html.getContent());
	}

}
