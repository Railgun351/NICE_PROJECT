package project.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import project.bean.CartBean;
import project.bean.Constant;
import project.bean.CustomMethod;
import project.bean.MemberBean;
import project.bean.OrderLogBean;
import project.db.ShopMgr;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class OrderLogPage extends JFrame implements ActionListener{

	private static OrderLogPage olp;
	private JPanel contentPane;
	private MemberBean mb;
	private JPanel dataPanel;
	private JLabel noData;
	private JLabel lbTitle;
	private Vector<OrderLogBean> olbv;
	private CustomMethod cm = new CustomMethod();
	private ShopMgr sm = ShopMgr.getInstance();
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OrderLogPage frame = new OrderLogPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public static OrderLogPage getInstance() {
		if (olp == null) {
			olp = new OrderLogPage();
		} return olp;
	}
	
	public void refresh(MemberBean mb) {
		this.mb = mb;
		setTitle(this.mb.getName()+"님의 마이페이지 - 주문 내역");
		lbTitle.setText(this.mb.getName()+"님의 주문 내역");
		addData();
	}
	
	/**
	 * Create the frame.
	 */
	private OrderLogPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		noData = new JLabel("주문 내역이 없습니다.");
		noData.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		noData.setHorizontalAlignment(SwingConstants.CENTER);
		noData.setBounds(138, 220, 308, 84);
		contentPane.add(noData);
		
		JButton btnBack = new JButton("");
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBounds(12, 10, 30, 30);
		btnBack.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\BACK.png"), 30, 30));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPage myp = MyPage.getInstance();
				myp.setLocationRelativeTo(olp);
				myp.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnBack);
		
		JLabel lbLOGO = new JLabel("");
		lbLOGO.setBounds(54, 10, 69, 39);
		lbLOGO.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\LOGO.png"), 69, 39));
		contentPane.add(lbLOGO);
		
		lbTitle = new JLabel("New label");
		lbTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setBounds(127, 26, 330, 50);
		contentPane.add(lbTitle);
		
		dataPanel = new JPanel();
		dataPanel.setBackground(new Color(255, 255, 255));
		dataPanel.setLayout(new GridLayout(0,1,0,0));
		
		JScrollPane scrollPane = new JScrollPane(dataPanel);
		scrollPane.setBounds(0, 86, 584, 365);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.add(scrollPane);
	}
	
	public void addData() {
		dataPanel.removeAll();
		olbv = sm.selectOrderLog(this.mb.getIdx());
		if (olbv.size() > 0) {
			noData.setVisible(false);
			for (int i = 0; i < olbv.size(); i++) {
				JPanel p = createData(olbv.get(i));
				p.setBackground(new Color(255, 255, 255));
				p.setPreferredSize(new Dimension(560, 100));
				dataPanel.add(p);
			}
		} else {
			noData.setVisible(true);
		}
		repaint();
		validate();
	}

	private JPanel createData(OrderLogBean olb) {
		JPanel panel = new JPanel();
//		panel.setBounds(12, 253, 560, 100);
		panel.setLayout(null);
		
		JButton btnConfirm = new JButton("");
		btnConfirm.setContentAreaFilled(false);
		btnConfirm.setBorderPainted(false);
		btnConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnConfirm.setBounds(479, 30, 60, 60);
		btnConfirm.setToolTipText("수취 확인");
		btnConfirm.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\ConfirmNull.png"), 60, 60));
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConfirm.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\ConfirmFill.png"), 60, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnConfirm.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\ConfirmNull.png"), 60, 60));
			}
		});
		btnConfirm.addActionListener(this);
		if (olb.getStatus() == Constant.CARTDELIVERYCOMPLETE) {
			btnConfirm.setVisible(true);
		} else {
			btnConfirm.setVisible(false);
		}
		olb.setBtn(btnConfirm);
		panel.add(btnConfirm);
		
		JLabel lbProPrice = new JLabel("결제 금액 : "+cm.toWon(olb.getPb().getPrice() * (long)olb.getQuantity()));
		lbProPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbProPrice.setBounds(182, 70, 166, 20);
		panel.add(lbProPrice);
		
		JLabel lbProName = new JLabel(olb.getPb().getProName());
		lbProName.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbProName.setBounds(182, 30, 166, 20);
		panel.add(lbProName);
		
		JLabel lbQuantity = new JLabel("수량 : "+olb.getQuantity()+"개");
		lbQuantity.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbQuantity.setBounds(182, 50, 166, 20);
		panel.add(lbQuantity);
		
		JLabel lbProImg = new JLabel("");
		lbProImg.setBounds(110, 30, 60, 60);
		lbProImg.setIcon(cm.resizeIcon(new ImageIcon(olb.getPb().getImgAddress()), 60, 60));
		panel.add(lbProImg);
		
		JLabel lbDate = new JLabel("날짜");
		lbDate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbDate.setHorizontalAlignment(SwingConstants.CENTER);
		lbDate.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbDate.setBounds(0, 0, 100, 20);
		panel.add(lbDate);
		
		JLabel lbProduct = new JLabel("상품정보");
		lbProduct.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lbProduct.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbProduct.setBounds(100, 0, 260, 20);
		panel.add(lbProduct);
		
		JLabel lbConfirmbtn = new JLabel("확인");
		lbConfirmbtn.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbConfirmbtn.setHorizontalAlignment(SwingConstants.CENTER);
		lbConfirmbtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbConfirmbtn.setBounds(460, 0, 100, 20);
		panel.add(lbConfirmbtn);
		
		JLabel lbStatus = new JLabel("상태");
		lbStatus.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lbStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbStatus.setBounds(360, 0, 100, 20);
		panel.add(lbStatus);
		
		JLabel lbDate_1 = new JLabel("<html><body><center>"+olb.getOrderDate()+"<br> ~ <br>"+olb.getFinalDate()+"</body></html>");
		lbDate_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbDate_1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		lbDate_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbDate_1.setBounds(0, 20, 100, 80);
		panel.add(lbDate_1);
		
		JLabel lbProduct_1 = new JLabel("");
		lbProduct_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbProduct_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbProduct_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbProduct_1.setBounds(100, 20, 260, 80);
		panel.add(lbProduct_1);
		
		JLabel lbStatus_1 = new JLabel("");
		lbStatus_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbStatus_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbStatus_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbStatus_1.setBounds(360, 20, 100, 80);
		String str = "";
		switch (olb.getStatus()) {
		case Constant.CARTORDERCOMPLETE:
			str = "배송 중";
			break;
		case Constant.CARTDELIVERYCOMPLETE:
			str = "배송 완료";
			break;
		case Constant.CARTCONFIRMATIONOFRECEIPT:
			str = "수취 확인";
			break;
		default:
			break;
		}
		lbStatus_1.setText(str);
		panel.add(lbStatus_1);
		
		JLabel lbConfirmbtn_1 = new JLabel("");
		lbConfirmbtn_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbConfirmbtn_1.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		lbConfirmbtn_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbConfirmbtn_1.setBounds(460, 20, 100, 80);
		panel.add(lbConfirmbtn_1);
		
		olb.setMemIdx(mb.getIdx());
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OrderLogBean olb = new OrderLogBean();
		for (int i = 0; i < olbv.size(); i++) {
			if (e.getSource() == olbv.get(i).getBtn()) {
				olb = olbv.get(i);
				break;
			}
		}
		if (sm.updateOrderLog(olb, Constant.CARTDELIVERYCOMPLETE, Constant.CARTCONFIRMATIONOFRECEIPT)) {
			JOptionPane.showMessageDialog(olp, "상품 수령을 확인했습니다. 이용해 주셔서 감사합니다.","안내",JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(olp, "문제가 발생했습니다. 잠시 후 다시 시도해주세요.","안내",JOptionPane.ERROR_MESSAGE);
		}
		addData();
	}
}
