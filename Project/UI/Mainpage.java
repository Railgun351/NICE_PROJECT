package Project.UI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Choice;
import java.awt.Scrollbar;
import javax.swing.JTextArea;
import java.awt.Color;

public class Mainpage extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	public static String selected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpage frame = new Mainpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mainpage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 800);
		contentPane = new JPanel(); // �߰�
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel = new JPanel();
		ImageIcon imageIcon = new ImageIcon("C:\\Java\\eclipse-workspace\\myJava\\project\\main.png");
		Image image = imageIcon.getImage().getScaledInstance(524, 761, Image.SCALE_SMOOTH);
		
		

		setContentPane(contentPane);
		Choice choice = new Choice();
		choice.setBounds(26, 200, 62, 21);
		choice.add("1");
		choice.add("2");
		choice.add("3");

		getContentPane().add(choice);
		ImageIcon reImg = new ImageIcon(image);
		JLabel label = new JLabel(reImg);
		label.setBounds(0, 0, 524, 761);
		getContentPane().add(label);
		getContentPane().setLayout(null);

		search = new JTextField();
		search.setBackground(new Color(252, 252, 252));
		search.setBounds(159, 200, 311, 21);
		getContentPane().add(search);
		search.setColumns(10);

		JButton logout = new JButton("");
		logout.setOpaque(false);
		logout.setBounds(376, 15, 31, 31);
		getContentPane().add(logout);

		JButton market = new JButton("");
		market.setOpaque(false);
		market.setBounds(471, 15, 31, 31);
		getContentPane().add(market);

		JButton setting = new JButton("");
		setting.setOpaque(false);
		setting.setBounds(425, 15, 31, 31);
		getContentPane().add(setting);

	
		choice.addItemListener((ItemListener) new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selected = (String) e.getItem();
				}
			}
		});

		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setBackground(new Color(192, 192, 192));
		scrollbar.setBounds(507, 238, 17, 523);
		getContentPane().add(scrollbar);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 260, 36, 22);
		getContentPane().add(textArea);
		setContentPane(contentPane);

	}

}
