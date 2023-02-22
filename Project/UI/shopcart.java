package project.ui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.math.BigInteger;
import java.text.AttributedCharacterIterator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import project.bean.*;
import project.db.ShopMgr;

import java.awt.TextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.FontMetrics;

public class shopcart extends JFrame implements ChangeListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	JScrollPane scrollPane;
	ImageIcon icon;
	JPanel dataPanel;
	ShopMgr sm;
	String ImgName = "./IMG\\";
	private String memName;
	private int memIdx;
	private Vector<CartBean> cartBV = new Vector<>();
	private JLabel totalPriceLb;
	private long totalPrice;
	public shopcart(String memName, int memIdx) {

		//DB연결
		sm = ShopMgr.getInstance();
		
		this.memIdx = memIdx;
		this.memName = memName;

		icon = new ImageIcon(ImgName + "back2.PNG");

		// 배경 Panel 생성후 컨텐츠페인으로 지정
		JPanel background = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {

				g.drawImage(icon.getImage(), 0, 0, null);

				setOpaque(false);
				super.paintComponent(g);
			}
		};
		background.setBackground(SystemColor.menu);
		background.setForeground(Color.LIGHT_GRAY);
		background.setLayout(null);
//		scrollPane = new JScrollPane(background);
		totalPriceLb = new JLabel("0");
		totalPriceLb.setBounds(0, 530, 400, 30);
		totalPriceLb.setBackground(Color.WHITE);
		totalPriceLb.setOpaque(true);
		totalPriceLb.setFont(new Font("돋움체", Font.BOLD, 20));
		totalPriceLb.setHorizontalAlignment(JLabel.RIGHT);
		background.add(totalPriceLb);

		JButton logout = new JButton("");
		logout.setIcon(new ImageIcon(ImgName + "LOGOUT.PNG"));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logout.setBounds(360, 10, 31, 39);
		logout.setOpaque(true);
		background.add(logout);

		JButton back = new JButton("");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		back.setIcon(new ImageIcon(ImgName + "BACK.PNG"));
		back.setBounds(12, 10, 18, 39);
		background.add(back);

//		JComboBox comboBox = new JComboBox(arrays[0]);
//
//		comboBox.setBounds(244, 271, 54, 23);
//		background.add(comboBox);
//
//		comboBox.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String selectedItem = (String) comboBox.getSelectedItem();
//
//				add1 = Integer.parseInt(selectedItem) * (price[0]);
//				System.out.print(add1);
//				add3 = add1 + add2;
//				가격.setText(Integer.toString(add3));
//			}
//		});
		
		dataPanel = new JPanel(new GridLayout(0,1,10,10));
		dataPanel.setBackground(Color.BLACK);
		
		JScrollPane sp = new JScrollPane(dataPanel);
		sp.setBounds(0, 120, 404, 404);
		background.add(sp);
		
		JLabel subTitle = new JLabel(memName+"님의 장바구니");
		subTitle.setFont(new Font("돋움체", Font.PLAIN, 18));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setBounds(0, 84, 405, 33);
		background.add(subTitle);

		addData();
		
		JButton immediatepurchase = new JButton("");
		immediatepurchase.setIcon(new ImageIcon(ImgName + "주문하기.png"));
		immediatepurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "장바구니에 있는 모든 상품을 주문합니다.","안내",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					confirmOrder();
				} else {
					JOptionPane.showMessageDialog(null, "결재를 취소했습니다.","안내",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		immediatepurchase.setBounds(0, 570, 405, 58);
		background.add(immediatepurchase);

//		setContentPane(scrollPane);
		setContentPane(background);
		validate();
	}

	public JPanel createData(String proName, int proPrice, int proIdx, int quantity) {
		JPanel panel = new JPanel();
//		panel.setBounds(10,y+10,420, 50);
//		panel.setSize(420, 50);
//		panel.setPreferredSize(new Dimension(420, 50));
		panel.setLayout(null);
		
		CartBean cb = new CartBean();
		
		JLabel imgLb = new JLabel("NO IMG");
		ImageIcon ic = new ImageIcon(ImgName+"product"+proIdx+".png");
		Image img = ic.getImage();
		Image img2 = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img2);
		imgLb.setIcon(ic);
		imgLb.setBounds(10, 10, 180, 180);
		panel.add(imgLb);
		
		JLabel nameLb = new JLabel(proName);
		nameLb.setBounds(200, 30, 160, 60);
		panel.add(nameLb);
		
		JLabel priceLb = new JLabel(toWon(proPrice));
		priceLb.setBounds(200, 90, 160, 60);
		panel.add(priceLb);
		
		JLabel quanLb = new JLabel("수량");
		quanLb.setBounds(200, 150, 40, 40);
		panel.add(quanLb);
		
		SpinnerNumberModel model = new SpinnerNumberModel(1,1,999999999,1);
		JSpinner quanSp = new JSpinner(model);
		quanSp.setBounds(230, 150, 90, 40);
		quanSp.setFont(new Font("돋움체", Font.BOLD, 20));
		quanSp.setValue(quantity);
		quanSp.addChangeListener(this);
		panel.add(quanSp);
		
		JButton btn = new JButton();
		ic = new ImageIcon(ImgName + "x.PNG");
		img = ic.getImage();
		img2 = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img2);
		btn.setIcon(ic);
		btn.setBounds(355, 0, 30, 30);
		btn.setContentAreaFilled(false);
		btn.addActionListener(this);
		panel.add(btn);
		
		cb.setMemIdx(memIdx);
		cb.setProIdx(proIdx);
		cb.setProName(proName);
		cb.setProPrice(proPrice);
		cb.setQuantity(quantity);
		cb.setJsp(quanSp);
		cb.setBtn(btn);
		cartBV.add(cb);
		totalPrice += cb.getProPrice()*cb.getQuantity();
		totalPriceLb.setText("합계 : "+toWon(totalPrice));
		
		return panel;
	}
	
	public void addData() {
		dataPanel.removeAll();
		totalPrice = 0;
		HashMap<Integer, CartBean> cartMap = sm.selectCart(memIdx);
		if (cartMap.size() > 0) {
			for (int key:cartMap.keySet()) {
				String proName = cartMap.get(key).getProName();
				int proPrice = cartMap.get(key).getProPrice();
				int proIdx = cartMap.get(key).getProIdx();
				int quantity = cartMap.get(key).getQuantity();
				JPanel p = createData(proName, proPrice, proIdx, quantity);
				p.setPreferredSize(new Dimension(370, 200));
				dataPanel.add(p);
			}
		} else {
			
		}
		repaint();
		validate();
	}
	
	public String toWon(long Amount) {
		StringBuilder sb = new StringBuilder();
		while (true) {
			if (Amount > (long)Math.pow(10, 12)) {
				sb.append((long)Math.floor(Amount/(long)Math.pow(10, 12))+"조");
				Amount %= (long)Math.pow(10, 12);
			} else if (Amount > (long)Math.pow(10, 8)) {
				sb.append((long)Math.floor(Amount/(long)Math.pow(10, 8))+"억");
				Amount %= (long)Math.pow(10, 8);
			} else if (Amount > (long)Math.pow(10, 4)) {
				sb.append((long)Math.floor(Amount/(long)Math.pow(10, 4))+"만");
				Amount %= (long)Math.pow(10, 4);
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
	
	private void confirmOrder() {
		if (cartBV.size() > 0) {
			for (int i = 0; i < cartBV.size(); i++) {
				if (!sm.updateCartFromPro(cartBV.get(i), 1)) {
					JOptionPane.showMessageDialog(null, "결제 중 문제가 발생하였습니다.\n다시 한번 확인해주세요.","안내",JOptionPane.ERROR_MESSAGE);
					break;
				}
				if (i == cartBV.size()-1) {
					JOptionPane.showMessageDialog(null, "상품 주문 및 결제가 완료되었습니다.\n 결제 금액 : "+toWon(totalPrice),"안내",JOptionPane.INFORMATION_MESSAGE);
					addData();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "결제 할 상품이 없습니다.\n다시 한번 확인해주세요.","안내",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		shopcart frame = new shopcart("서수정",1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 665);
		frame.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner jsp = (JSpinner) e.getSource();
		CartBean cb = null;
		int idx = -1;
		for (int i = 0; i < cartBV.size(); i++) {
			if (cartBV.get(i).getJsp() == jsp) {
				cb = cartBV.get(i);
				idx = i;
				break;
			}
		}
		long subtract = (int)jsp.getValue() - cb.getQuantity();
		cartBV.get(idx).setQuantity((int)jsp.getValue());
		totalPrice += cb.getProPrice()*subtract;
		totalPriceLb.setText("합계 : "+toWon(totalPrice));
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		CartBean cb = null;
		int idx = -1;
		for (int i = 0; i < cartBV.size(); i++) {
			if (btn == cartBV.get(i).getBtn()) {
				cb = cartBV.get(i);
				idx = i;
				break;
			}
		}
		if (sm.updateCartFromPro(cb, 2)) {
			JOptionPane.showMessageDialog(null, "장바구니에서 해당 상품이 삭제되었습니다.","안내",JOptionPane.INFORMATION_MESSAGE);
			cartBV.remove(idx);
			addData();
		} else {
			JOptionPane.showMessageDialog(null, "상품삭제 중 문제가 발생하였습니다.\n다시 한번 확인해주세요.","안내",JOptionPane.ERROR_MESSAGE);
		}
	}
}
