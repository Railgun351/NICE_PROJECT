package project.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.bean.StatisBean;
import project.dbFunction.ShopMgr;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class Admin_Statistics extends JFrame {

	private JPanel contentPane;
	private JPanel data_panel;
	private ShopMgr sm;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Statistics frame = new Admin_Statistics();
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
	public Admin_Statistics() {
		sm = ShopMgr.getInstance();
		setForeground(new Color(255, 255, 255));
		setTitle("관리자 판매 통계");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 778);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 500, 774);
		contentPane.add(layeredPane);

		JButton btn_Back = new JButton("");
		btn_Back.setContentAreaFilled(false);
		btn_Back.setBounds(12, 20, 33, 26);
		layeredPane.add(btn_Back);

		JButton btn_Home = new JButton("");
		btn_Home.setContentAreaFilled(false);
		btn_Home.setBounds(441, 10, 47, 47);
		layeredPane.add(btn_Home);

		data_panel = new JPanel();
		data_panel.setLayout(new GridLayout(0, 1, 10, 10));

		JScrollPane scrollPane = new JScrollPane(data_panel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(12, 363, 476, 357);
		layeredPane.add(scrollPane);

		JComboBox comboBox_Sub = new JComboBox();
		comboBox_Sub.setBounds(222, 310, 102, 23);
		comboBox_Sub.setEnabled(false);
		layeredPane.add(comboBox_Sub);

		JComboBox comboBox_Main = new JComboBox();
		comboBox_Main.setFont(new Font("돋움체", Font.PLAIN, 20));
		comboBox_Main.setModel(new DefaultComboBoxModel(new String[] {"카테고리 별", "연도 별", "분기 별"}));
		comboBox_Main.setBounds(37, 286, 173, 47);
		comboBox_Main.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				comboBox_Sub.removeAllItems();
				if (comboBox_Main.getSelectedIndex() == 2) {
					Vector<String> years = sm.selectYear();
					for (int i = 0; i < years.size(); i++) {
						comboBox_Sub.addItem(years.get(i));
					}
					comboBox_Sub.setEnabled(true);
				} else {
					comboBox_Sub.setEnabled(false);
					comboBox_Sub.removeAllItems();
				}
			}
		});
		layeredPane.add(comboBox_Main);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("수량");
		rdbtnNewRadioButton.setFont(new Font("돋움체", Font.PLAIN, 26));
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(37, 240, 80, 40);
		layeredPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("금액");
		rdbtnNewRadioButton_1.setFont(new Font("돋움체", Font.PLAIN, 26));
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(130, 240, 80, 40);
		layeredPane.add(rdbtnNewRadioButton_1);
		
		JButton btn_Search = new JButton("");
		btn_Search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_Search.setFocusPainted(false);
		btn_Search.setBorderPainted(false);
		btn_Search.setContentAreaFilled(false);
		btn_Search.setBounds(343, 286, 119, 47);
		btn_Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int type = 0;
				if (rdbtnNewRadioButton.isSelected()) {
					type = 0;
				} else {
					type = 1;
				}
				System.out.println(type);
				addData(comboBox_Main.getSelectedIndex(), (String)comboBox_Sub.getSelectedItem(), type);
				validate();
			}
		});
		layeredPane.add(btn_Search);

		JLabel lbBGI = new JLabel("");
		lbBGI.setIcon(new ImageIcon("C:\\Java\\eclipse-workspace\\NICE_PROJECT\\IMG\\statistics.png"));
		lbBGI.setBounds(0, 0, 500, 774);
		lbBGI.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		contentPane.add(lbBGI);
		addData(0, null, 0);
	}

	public JPanel createData(String labelTxt, int valuesRatio, int values) {
		JPanel panel = new JPanel();
//		panel.setBounds(10,y+10,420, 50);
//		panel.setSize(420, 50);
//		panel.setPreferredSize(new Dimension(420, 50));
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(labelTxt);
//		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setBounds(10, 15, 100, 20);
		panel.add(lblNewLabel);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(valuesRatio);
//		panel.add(progressBar, BorderLayout.EAST);
		progressBar.setBounds(120, 15, 250, 20);
		panel.add(progressBar);
//		System.out.println(panel.getBounds());
		
		JLabel lblNewLabel2 = new JLabel(Integer.toString(values));
		lblNewLabel2.setBounds(380, 15, 100, 20);
		panel.add(lblNewLabel2);
		
		return panel;
	}
	
	public JPanel createData(String labelTxt, int valuesRatio, String won) {
		JPanel panel = new JPanel();
//		panel.setBounds(10,y+10,420, 50);
//		panel.setSize(420, 50);
//		panel.setPreferredSize(new Dimension(420, 50));
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel(labelTxt);
//		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setBounds(10, 15, 100, 20);
		panel.add(lblNewLabel);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(valuesRatio);
//		panel.add(progressBar, BorderLayout.EAST);
		progressBar.setBounds(120, 15, 250, 20);
		panel.add(progressBar);
//		System.out.println(panel.getBounds());
		
		JLabel lblNewLabel2 = new JLabel(won);
		lblNewLabel2.setBounds(380, 15, 100, 20);
		panel.add(lblNewLabel2);
		return panel;
	}

	public void addData(int idx, String year, int type) {
		data_panel.removeAll();
		if (idx == 0) {
			Vector<StatisBean> vsb;
			if (type == 0) {
				vsb = sm.selectCateSt();
			} else {
				vsb = sm.selectCateAmountSt();
			}
			for (int i = 0; i < vsb.size(); i++) {
				JPanel p;
				if (type == 0) {
					p = createData(vsb.get(i).getcName(), vsb.get(i).getPurCountRatio(), vsb.get(i).getPurCount());
				} else {
					p = createData(vsb.get(i).getcName(), vsb.get(i).getPurCountRatio(), toWon(vsb.get(i).getPurCount()));
				}
				p.setPreferredSize(new Dimension(420, 50));
				data_panel.add(p);
			}
		} else if (idx == 1) {
			Vector<StatisBean> vsb;
			if (type == 0) {
				vsb = sm.selectYearSt();
			} else {
				vsb = sm.selectYearAmountSt();
			}
			for (int i = 0; i < vsb.size(); i++) {
				JPanel p;
				if (type == 0) {
					p = createData(Integer.toString(vsb.get(i).getYear()), vsb.get(i).getPurCountRatio(), vsb.get(i).getPurCount());
				} else {
					p = createData(Integer.toString(vsb.get(i).getYear()), vsb.get(i).getPurCountRatio(), toWon(vsb.get(i).getPurCount()));
				}
				p.setPreferredSize(new Dimension(420, 50));
				data_panel.add(p);
			}
		} else {
			if (year != null) {
				Vector<StatisBean> vsb;
				if (type == 0) {
					vsb = sm.selectQuarterSt(Integer.parseInt(year));
				} else {
					vsb = sm.selectQuarterAmountSt(Integer.parseInt(year));
				}
				for (int i = 0; i < vsb.size(); i++) {
					JPanel p;
					if (type == 0) {
						p = createData(vsb.get(i).getcName(), vsb.get(i).getPurCountRatio(), vsb.get(i).getPurCount());
					} else {
						p = createData(vsb.get(i).getcName(), vsb.get(i).getPurCountRatio(), toWon(vsb.get(i).getPurCount()));
					}
					p.setPreferredSize(new Dimension(420, 50));
					data_panel.add(p);
				}
			} else {
				JOptionPane.showMessageDialog(null, "연도를 선택해야 합니다.","경고",JOptionPane.ERROR_MESSAGE);
			}
		}
		data_panel.repaint();
	}
	
	public String toWon(int Amount) {
		StringBuilder sb = new StringBuilder();
		while (true) {
			if (Amount > Math.pow(10, 12)) {
				sb.append((int)Math.floor(Amount/Math.pow(10, 12))+"조");
				Amount %= Math.pow(10, 12);
			} else if (Amount > Math.pow(10, 8)) {
				sb.append((int)Math.floor(Amount/Math.pow(10, 8))+"억");
				Amount %= Math.pow(10, 8);
			} else if (Amount > Math.pow(10, 4)) {
				sb.append((int)Math.floor(Amount/Math.pow(10, 4))+"만");
				Amount %= Math.pow(10, 4);
			} else {
				if (Amount == 0) {
					sb.append("원");
				} else {
					sb.append(Amount+"원");
				}
				break;
			}
		}
		return sb.toString();
	}
}
