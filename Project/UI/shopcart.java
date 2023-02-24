package project.ui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import project.bean.*;
import project.db.ShopMgr;

import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;

public class Shopcart extends JFrame implements ChangeListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Shopcart sc;
//	JScrollPane scrollPane;
	ImageIcon icon;
	JPanel dataPanel;
	ShopMgr sm;
	String ImgName = "./IMG\\";
	private JLabel noData;
	private MemberBean mem;
	private JLabel subTitle;
	private Vector<CartBean> cartBV = new Vector<>();
	private JLabel totalPriceLb;
	private long totalPrice;
	
	public static Shopcart getInstance() {
		if (sc == null) {
			sc = new Shopcart(new MemberBean());
		} return sc;
	}
	
	public void refresh(MemberBean mem) {
		this.mem = mem;
		setTitle(mem.getName()+"님의 장바구니 페이지");
		subTitle.setText(mem.getName()+"님의 장바구니 페이지");
		addData();
	}
	
	private Shopcart(MemberBean mem) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./IMG\\LogoIcon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 665);
		setVisible(true);
		setLocationRelativeTo(null);
		//DB연결
		sm = ShopMgr.getInstance();
		
		this.mem = mem;
		setTitle(mem.getName()+"님의 장바구니 페이지");
		icon = new ImageIcon(ImgName + "CartBack.PNG");

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
		
		noData = new JLabel();
		noData.setBounds(52, 172, 300, 300);
		ImageIcon ic = new ImageIcon(ImgName+"NoProduct.png");
		Image img = ic.getImage();
		Image img2 = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img2);
		noData.setIcon(ic);
		noData.setOpaque(true);
		noData.setVisible(false);
		background.add(noData);
		
//		scrollPane = new JScrollPane(background);
		totalPriceLb = new JLabel("0");
		totalPriceLb.setBounds(0, 530, 400, 30);
		totalPriceLb.setBackground(Color.WHITE);
		totalPriceLb.setOpaque(true);
		totalPriceLb.setFont(new Font("돋움체", Font.BOLD, 20));
		totalPriceLb.setHorizontalAlignment(JLabel.RIGHT);
		background.add(totalPriceLb);

		JButton home = new JButton("");
		home.setIcon(resizeIcon(new ImageIcon(ImgName + "HomeNull.PNG"), 40, 40));
		home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				home.setIcon(resizeIcon(new ImageIcon(ImgName + "HomeNull.PNG"), 40, 40));
				Mainpage mp = Mainpage.getInstance();
				mp.setVisible(true);
				dispose();
			}
		});
		home.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				home.setIcon(resizeIcon(new ImageIcon(ImgName + "HomeFill.PNG"), 40, 40));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				home.setIcon(resizeIcon(new ImageIcon(ImgName + "HomeNull.PNG"), 40, 40));
			}
		});
		home.setBounds(300, 10, 40, 40);
		home.setOpaque(true);
		home.setContentAreaFilled(false);
		home.setBorderPainted(false);
		background.add(home);
		
		JButton logout = new JButton("");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout.setIcon(resizeIcon(new ImageIcon(ImgName + "LogOutNull.PNG"), 40, 40));
				LoginPage lp = LoginPage.getInstance();
				lp.setVisible(true);
				dispose();
			}
		});
		logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				logout.setIcon(resizeIcon(new ImageIcon(ImgName + "LogOutFill.PNG"), 40, 40));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				logout.setIcon(resizeIcon(new ImageIcon(ImgName + "LogOutNull.PNG"), 40, 40));
			}
		});
		logout.setIcon(resizeIcon(new ImageIcon(ImgName + "LogOutNull.PNG"), 40, 40));
		logout.setBounds(350, 10, 40, 40);
		logout.setContentAreaFilled(false);
		logout.setBorderPainted(false);
		background.add(logout);
		
		dataPanel = new JPanel(new GridLayout(0,1,10,10));
		dataPanel.setBackground(Color.WHITE);
		
		JScrollPane sp = new JScrollPane(dataPanel);
		sp.setBounds(0, 120, 404, 404);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		background.add(sp);
		
		subTitle = new JLabel(mem.getName()+"님의 장바구니");
		subTitle.setFont(new Font("돋움체", Font.PLAIN, 18));
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setBounds(0, 84, 405, 33);
		background.add(subTitle);

		addData();
		
		JButton immediatePurchase = new JButton("");
		immediatePurchase.setIcon(new ImageIcon(ImgName + "주문하기.png"));
		immediatePurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "장바구니에 있는 모든 상품을 주문합니다.","안내",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					confirmOrder();
				} else {
					JOptionPane.showMessageDialog(null, "결재를 취소했습니다.","안내",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		immediatePurchase.setBounds(0, 570, 405, 58);
		background.add(immediatePurchase);
		
//		setContentPane(scrollPane);
		setContentPane(background);
		validate();
	}
	
	public Icon resizeIcon(ImageIcon ii, int w, int h) {
		ImageIcon ic = ii;
		Image img = ic.getImage();
		Image img2 = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		ic = new ImageIcon(img2);
		return ic;
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
		
		cb.setMemIdx(mem.getIdx());
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
		HashMap<Integer, CartBean> cartMap = sm.selectCart(mem.getIdx());
		if (cartMap.size() > 0) {
			noData.setVisible(false);
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
			noData.setVisible(true);
			totalPriceLb.setText("합계 : 0원");
		}
		repaint();
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
			while (cartBV.size() != 0) {
				if (!sm.updateCartFromPro(cartBV.get(0), 1)) {
					JOptionPane.showMessageDialog(null, "결제 중 문제가 발생하였습니다.\n다시 한번 확인해주세요.","안내",JOptionPane.ERROR_MESSAGE);
					break;
				}
				cartBV.remove(0);
			}
			if (cartBV.size() == 0) {
				JOptionPane.showMessageDialog(null, "상품 주문 및 결제가 완료되었습니다.\n 결제 금액 : "+toWon(totalPrice),"안내",JOptionPane.INFORMATION_MESSAGE);
				addData();
			}
		} else {
			JOptionPane.showMessageDialog(null, "결제 할 상품이 없습니다.\n다시 한번 확인해주세요.","안내",JOptionPane.ERROR_MESSAGE);
		}
	}
	
//	public static void main(String[] args) {
//		Shopcart frame = new Shopcart("서수정",1);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(420, 665);
//		frame.setVisible(true);
//	}

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
