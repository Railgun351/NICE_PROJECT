package Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.PublicKey;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import javax.swing.Icon;

public class Admin_AddInventory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String Header[] = { "상품번호", "상품명", "카테고리", "가격", "현재재고량" };
	private DefaultTableModel dtm = new DefaultTableModel(Header, 0);
	private DefaultComboBoxModel<String> dcbm = new DefaultComboBoxModel(new String[] { "전체" });
	ShopMgr sm;
	private int Pro_idx = -1;
	private BufferedImage bgi = null;
	private ImageIcon icon = new ImageIcon("IMG\\addInven.png");
	private Image img = icon.getImage();
	private Admin_AddInventory aa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_AddInventory frame = new Admin_AddInventory();
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
	public Admin_AddInventory() {
		sm = ShopMgr.getInstance();
		setResizable(false);
		setTitle("관리자 기존 상품 재고 추가");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 568, 769);
		JLayeredPane lp = new JLayeredPane();
		lp.setLocation(0, 0);
		lp.setSize(550, 731);
		lp.setLayout(null);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(lp);
		sm.selectCate(dcbm);

		JLabel bgilb = new JLabel(new ImageIcon("C:\\Java\\eclipse-workspace\\NICE_PROJECT\\IMG\\addInven.png"));
		bgilb.setBounds(0, 0, 550, 731);
		bgilb.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		contentPane.add(bgilb);

		JButton btn_back = new JButton("");
		btn_back.setContentAreaFilled(false);
		btn_back.setBounds(28, 21, 30, 30);
		lp.add(btn_back);

		JButton btn_Exit = new JButton("");
		btn_Exit.setContentAreaFilled(false);
		btn_Exit.setBounds(482, 25, 40, 40);
		lp.add(btn_Exit);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(34, 59, 97, 23);
		lp.add(comboBox);
		comboBox.setModel(dcbm);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 92, 475, 341);
		lp.add(scrollPane);

		table = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {

				// all cells false

				return false;

			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				Pro_idx = (int) table.getModel().getValueAt(row, 0);
				textField.setText((String) table.getModel().getValueAt(row, 1));
				textField_1.setText("카테고리: " + (String) table.getModel().getValueAt(row, 2));
				textField_2.setText(Integer.toString((int) table.getModel().getValueAt(row, 3)) + "원");
			}
		});

		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(55, 483, 172, 40);
		lp.add(textField);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setOpaque(false);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		textField_1 = new JTextField();
		textField_1.setBounds(55, 562, 172, 40);
		lp.add(textField_1);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setOpaque(false);
		textField_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		textField_2 = new JTextField();
		textField_2.setBounds(55, 637, 172, 40);
		lp.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setOpaque(false);
		textField_2.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		JLabel lblNewLabel = new JLabel("추가할 재고량");
		lblNewLabel.setBounds(284, 455, 204, 40);
		lp.add(lblNewLabel);
		lblNewLabel.setFont(new Font("돋움체", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("돋움체", Font.PLAIN, 30));
		spinner.setBounds(284, 508, 204, 61);
		spinner.setOpaque(false);
		spinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		lp.add(spinner);

		JButton btn_Confirm = new JButton("");
		btn_Confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_Confirm.setFocusPainted(false);
		btn_Confirm.setBorderPainted(false);
		btn_Confirm.setContentAreaFilled(false);
		btn_Confirm.setBounds(310, 619, 153, 69);
		lp.add(btn_Confirm);
		btn_Confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sm.updateInven(Pro_idx, (int) spinner.getValue())) {
					JOptionPane.showMessageDialog(null, "성공적으로 재고가 추가되었습니다.", "성공", JOptionPane.PLAIN_MESSAGE);
					String cate = (String) comboBox.getSelectedItem();
					updateTable(cate, dtm);
				} else {
					JOptionPane.showMessageDialog(null, "재고 추가중에 문제가 발생했습니다.\n다시 확인해주세요.", "오류",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
//		btnNewButton_2.setBorderPainted(false);
//		btnNewButton_2.setFocusPainted(false);
		table.updateUI();
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cate = (String) comboBox.getSelectedItem();
				updateTable(cate, dtm);
			}
		});
		sm.selectPro("전체", dtm);
		validate();
	}

	public void updateTable(String Cate, DefaultTableModel dtm) {
		sm.selectPro(Cate, dtm);
		table.updateUI();
	}
}
