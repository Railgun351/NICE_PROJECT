package Project.UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import Project.db.ShopMgr;

public class SignUpPage extends JFrame {

	private JPanel contentPane;
	private JTextField idText;
	private JPasswordField pwText;
	private JTextField nameText;
	private ShopMgr sm;

	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPage frame = new SignUpPage();
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
	public SignUpPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 800);

		JPanel panel = new JPanel();
		ImageIcon imageIcon = new ImageIcon("C:\\Java\\eclipse-workspace\\myJava\\project\\Signup.png");
		Image image = imageIcon.getImage().getScaledInstance(524, 761, Image.SCALE_SMOOTH);

		ImageIcon reImg = new ImageIcon(image);

		// JLabel�� ������ �̹����� ����
		JLabel label = new JLabel(reImg);
		label.setBounds(0, 0, 524, 761);
		getContentPane().add(label);

		getContentPane().setLayout(null);

		idText = new JTextField();
		idText.setBackground(new Color(252, 252, 252));
		idText.setBounds(56, 164, 408, 41);
		idText.setBorder(null);
		idText.setFont(new Font("����", Font.PLAIN, 23));
		getContentPane().add(idText);
		idText.setColumns(10);

		pwText = new JPasswordField();
		pwText.setBackground(new Color(252, 252, 252));
		pwText.setBounds(56, 246, 408, 41);
		pwText.setBorder(null);
		getContentPane().add(pwText);

		nameText = new JTextField();
		nameText.setBackground(new Color(252, 252, 252));
		nameText.setBounds(56, 324, 408, 41);
		nameText.setBorder(null);
		nameText.setFont(new Font("����", Font.PLAIN, 23));
		getContentPane().add(nameText);
		nameText.setColumns(10);

		JButton signUpBtn = new JButton("New button");
		signUpBtn.setOpaque(false);
		signUpBtn.setBounds(43, 612, 449, 83);

		getContentPane().add(signUpBtn);

		signUpBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the user input from the text fields
				String id = idText.getText();
				String pw = new String(pwText.getPassword());
				String name = nameText.getText();

				// Validate the input
				if (id.isEmpty() || pw.isEmpty() || name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill out all fields.");
					return;
				}

				// Insert the user data into the database
				boolean result = updateMember(id, pw, name);
				if (result) {
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "회원가입 실패");
				}
			}
		});

	}

	protected boolean updateMember(String id, String pw, String name) {
		return false;
	}
}
