package project.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import project.db.ShopMgr;

public class SignUpPage extends JFrame {

	private static SignUpPage sup;
	private JPanel contentPane;
	private JTextField idText;
	private JPasswordField pwText;
	private JTextField nameText;
	private ShopMgr sm;
//
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

	public static SignUpPage getInstance() {
		if (sup == null) {
			sup = new SignUpPage();
		} return sup;
	}
	
	/**
	 * Create the frame.
	 */
	private SignUpPage() {
		ShopMgr StrService = ShopMgr.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./IMG\\LogoIcon.png"));
		setBounds(100, 100, 540, 800);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		ImageIcon imageIcon = new ImageIcon("C:\\Java\\eclipse-workspace\\myJava\\project\\Signup.png");
		Image image = imageIcon.getImage().getScaledInstance(524, 761, Image.SCALE_SMOOTH);

		ImageIcon reImg = new ImageIcon(image);
		getContentPane().setLayout(null);

		// JLabel에 조정된 이미지를 설정
		JLabel label = new JLabel(new ImageIcon("C:\\Java2\\NICE_PROJECT\\IMG\\SingUpPage.png"));
		label.setBounds(0, 0, 540, 800);
		getContentPane().add(label);

		idText = new JTextField();
		idText.setBounds(56, 164, 408, 41);
		idText.setBackground(new Color(252, 252, 252));
		idText.setBorder(null);
		idText.setFont(new Font("굴림", Font.PLAIN, 23));
		getContentPane().add(idText);
		idText.setColumns(10);

		pwText = new JPasswordField();
		pwText.setBounds(56, 246, 408, 41);
		pwText.setBackground(new Color(252, 252, 252));
		pwText.setBorder(null);
		getContentPane().add(pwText);

		nameText = new JTextField();
		nameText.setBounds(56, 324, 408, 41);
		nameText.setBackground(new Color(252, 252, 252));
		nameText.setBorder(null);
		nameText.setFont(new Font("굴림", Font.PLAIN, 23));
		getContentPane().add(nameText);
		nameText.setColumns(10);

		JButton signUpBtn = new JButton("회원가입");
		signUpBtn.setBounds(43, 612, 200, 83);
		signUpBtn.setOpaque(false);
		signUpBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
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
				} else {
					// Insert the user data into the database
					if (StrService.signUp(id, pw, name)) {
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.");
						LoginPage lp = LoginPage.getInstance();
						lp.setVisible(true);
						dispose();
					} else
						JOptionPane.showMessageDialog(null, "회원가입 실패!");
				}
			}
		});
		getContentPane().add(signUpBtn);
		
		JButton cancleBtn = new JButton("로그인 화면으로");
		cancleBtn.setBounds(280, 612, 200, 83);
		cancleBtn.setOpaque(false);
		cancleBtn.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		cancleBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage lp = LoginPage.getInstance();
				lp.setVisible(true);
				dispose();
			}
		});
		getContentPane().add(cancleBtn);
	}
}
