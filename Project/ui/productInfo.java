package project.ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.bean.CartBean;
import project.bean.Constant;
import project.bean.CustomMethod;
import project.bean.MemberBean;
import project.bean.ProductBean;
import project.bean.ReviewBean;
import project.db.ShopMgr;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.DefaultComboBoxModel;

public class productInfo extends JFrame implements ActionListener, Runnable {

	private static productInfo pi;
	private JPanel contentPane;
	private CustomMethod cm = new CustomMethod();
	private ShopMgr sm = ShopMgr.getInstance();
	private MemberBean mb;
	public ProductBean pb;
	private JLabel lbProName;
	private JLabel lbPrice;
	private JLabel lbProImg;
	private JLabel noData;
	private JPanel commentPanel;
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private JButton btnCart;
	private boolean isPopUp;
	private JPanel panelPopUp;
	private JLabel lbPopUpGrade;
	private JLabel lbPopUpPoint;
	private JButton btnMyPage;
	private JLabel lbPopUpName;
	private JButton btnClosePopUp;
	private Vector<ReviewBean> rbv = new Vector<>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					productInfo frame = productInfo.getInstance();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public static productInfo getInstance() {
		if (pi == null) {
			pi = new productInfo(new ProductBean(), new MemberBean());
		}
		return pi;
	}

	public void refresh(ProductBean pb, MemberBean mb) {
		this.pb = pb;
		this.mb = mb;
		lbProName.setText(this.pb.getProName());
		lbPrice.setText(cm.toWon(this.pb.getPrice()) + "    ");
		lbProImg.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\product" + pb.getProIdx() + ".png"), 300, 300));
		lbPopUpName.setText(mb.getName() + "???");
		lbPopUpGrade.setText("?????? : " + mb.getGrade());
		lbPopUpPoint.setText("????????? : " + mb.getPoint());
		setTitle("NICE ?????? ?????? ????????? - " + mb.getName() + "???");
		comboBox.setSelectedIndex(0);
		if (sm.updateMember(mb)) {
			System.out.println("????????????????????????");
		} else {
			System.out.println("???????????????????????? ??????");
		}
		addData();
	}

	/**
	 * Create the frame.
	 */
	private productInfo(ProductBean pb, MemberBean mb) {
		this.pb = pb;
		this.mb = mb;
		setTitle("NICE ?????? ?????? ????????? - " + mb.getName() + "???");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./IMG\\LogoIcon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 832);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelPopUp = new JPanel();
		panelPopUp.setBounds(524, 0, 150, 800);
		panelPopUp.setBackground(new Color(200, 200, 200, 240));

		contentPane.add(panelPopUp);
		panelPopUp.setLayout(null);

		btnClosePopUp = new JButton("");
		btnClosePopUp.setContentAreaFilled(false);
		btnClosePopUp.setBorderPainted(false);
		btnClosePopUp.setBounds(12, 10, 30, 30);
		btnClosePopUp.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\XNull.png"), 30, 30));
		btnClosePopUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClosePopUp.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\XFill.png"), 30, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnClosePopUp.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\XNull.png"), 30, 30));
			}
		});
		btnClosePopUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnClosePopUp.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\XNull.png"), 30, 30));
				togglePopUp();
			}
		});
		panelPopUp.add(btnClosePopUp);

		lbPopUpName = new JLabel("New label");
		lbPopUpName.setFont(new Font("?????? ??????", Font.BOLD, 20));
		lbPopUpName.setBounds(12, 44, 126, 30);
		panelPopUp.add(lbPopUpName);

		lbPopUpGrade = new JLabel("New label");
		lbPopUpGrade.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		lbPopUpGrade.setBounds(12, 84, 126, 30);
		panelPopUp.add(lbPopUpGrade);

		lbPopUpPoint = new JLabel("New label");
		lbPopUpPoint.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		lbPopUpPoint.setBounds(12, 124, 126, 30);
		panelPopUp.add(lbPopUpPoint);

		btnMyPage = new JButton("???????????????");
		btnMyPage.setContentAreaFilled(false);
		btnMyPage.setBounds(12, 164, 126, 30);
		btnMyPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyPage myp = MyPage.getInstance();
				myp.refresh(pi.mb, Constant.PREPAGEPRODUCTINFO);
				myp.setLocationRelativeTo(pi);
				myp.setVisible(true);
				togglePopUp();
				dispose();
			}
		});
		panelPopUp.add(btnMyPage);

		noData = new JLabel("<html><body><center>????????? ????????? ????????????.<br>??? ????????? ??????????????????!</center></body></html>");
		noData.setFont(new Font("?????? ??????", Font.BOLD, 18));
		noData.setOpaque(true);
		noData.setBounds(159, 530, 210, 113);
		contentPane.add(noData);

		JButton btnImPur = new JButton();
		btnImPur.setBounds(0, 733, 526, 60);
		btnImPur.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\????????????.png"), 540, 60));
		btnImPur.setContentAreaFilled(false);
		btnImPur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CartBean cb = new CartBean();
				cb.getMb().setIdx(pi.mb.getIdx());
				cb.getMb().setPoint(pi.mb.getPoint());
				cb.setProIdx(pi.pb.getProIdx());
				cb.setProName(pi.pb.getProName());
				cb.setProPrice(pi.pb.getPrice());
				cb.setQuantity(1);
				cb.setPayment(cb.getProPrice() * cb.getQuantity());
				int answer = JOptionPane.showConfirmDialog(pi,
						pi.pb.getProName() + " ??????(1???)??? ?????? ???????????????.\n?????? ?????? : " + cm.toWon(pi.pb.getPrice()), "??????",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					if (cb.getMb().getPoint() > 0
							&& JOptionPane.showConfirmDialog(pi, cb.getMb().getPoint() + " ???????????? ????????????. ?????????????????????????", "??????",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						if (cb.getMb().getPoint() >= cb.getPayment()) {
							int sub = cb.getMb().getPoint() - cb.getPayment();
							cb.getMb().setPoint(sub);
							pi.mb.setPoint(sub);
							cb.setPayment(0);
						} else {
							int sub = cb.getPayment() - cb.getMb().getPoint();
							cb.getMb().setPoint(0);
							pi.mb.setPoint(0);
							cb.setPayment(sub);
						}
						if (sm.insertShopCart(cb, Constant.CARTORDERCOMPLETE) > 0) {
							JOptionPane.showMessageDialog(pi, "???????????? ???????????? ?????? ??? ??????????????? ?????????????????????.\n?????? ?????? : "+cm.toWon(cb.getPayment())+"/?????? ????????? : "+cb.getMb().getPoint());
						} else {
							JOptionPane.showMessageDialog(pi, "?????? ??? ????????? ??????????????????.\n?????? ??? ????????? ?????????");
						}
					} else {
						long temp = 0;
						if (pi.mb.getGrade().equals(Constant.BRONZE)) {
							temp = (long) (cb.getPayment()*(Constant.BRONZEPERPOINT));
						} else if (pi.mb.getGrade().equals(Constant.SILVER)) {
							temp = (long) (cb.getPayment()*(Constant.SILVERPERPOINT));
						} else if (pi.mb.getGrade().equals(Constant.GOLD)) {
							temp = (long) (cb.getPayment()*(Constant.GOLDPERPOINT));
						} else if (pi.mb.getGrade().equals(Constant.PLATINUM)) {
							temp = (long) (cb.getPayment()*(Constant.PLATINUMPERPOINT));
						} else if (pi.mb.getGrade().equals(Constant.DIAMOND)) {
							temp = (long) (cb.getPayment()*(Constant.DIAMONDPERPOINT));
						}
						pi.mb.setPoint(pi.mb.getPoint() + (int)temp);
						if (sm.insertShopCart(cb, Constant.CARTORDERCOMPLETE) > 0 && sm.updateMember(pi.mb)) {
							JOptionPane.showMessageDialog(pi, "?????? ??? ??????????????? ?????????????????????.\n"+(int)temp+" ????????? ??????");
						} else {
							JOptionPane.showMessageDialog(pi, "?????? ??? ????????? ??????????????????.\n?????? ??? ????????? ?????????");
							pi.mb.setPoint(pi.mb.getPoint() - (int)temp);
						}
					}
				} else {
					JOptionPane.showMessageDialog(pi, "????????? ??????????????????.", "??????", JOptionPane.INFORMATION_MESSAGE);
				}
				gradeCheck(pi.mb);
			}
		});
		contentPane.add(btnImPur);

		JButton btnAddCart = new JButton();
		btnAddCart.setBounds(0, 673, 526, 60);
		btnAddCart.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\????????????.png"), 540, 60));
		btnAddCart.setContentAreaFilled(false);
		btnAddCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CartBean cb = new CartBean();
				cb.getMb().setIdx(pi.mb.getIdx());
				cb.setProIdx(pi.pb.getProIdx());
				cb.setProName(pi.pb.getProName());
				cb.setProPrice(pi.pb.getPrice());
				cb.setQuantity(1);
				cb.setPayment(cb.getProPrice()*cb.getQuantity());
				int result = sm.insertShopCart(cb, Constant.CARTORDER);
				if (result > 0) {
					JOptionPane.showMessageDialog(pi, "?????? ????????? ??????????????? ?????????????????????.", "??????", JOptionPane.INFORMATION_MESSAGE);
				} else if (result == 0) {
					JOptionPane.showMessageDialog(pi, "??????????????? ???????????? ???????????????.\n?????? ?????? ?????? ??????????????????.", "??????",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(pi, "?????? ????????? ??????????????????.", "??????", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		contentPane.add(btnAddCart);

		lbProName = new JLabel("New label");
		lbProName.setOpaque(true);
		lbProName.setHorizontalAlignment(SwingConstants.CENTER);
		lbProName.setBackground(SystemColor.controlHighlight);
		lbProName.setFont(new Font("?????? ??????", Font.BOLD, 25));
		lbProName.setBounds(0, 60, 526, 41);
		contentPane.add(lbProName);

		JLabel lbLogo = new JLabel();
		lbLogo.setBounds(10, 10, 69, 39);
		lbLogo.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\LOGO.png"), 69, 39));
		contentPane.add(lbLogo);

		JButton btnMyAc = new JButton("");
		btnMyAc.setBorderPainted(false);
		btnMyAc.setContentAreaFilled(false);
		btnMyAc.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\MyAcNull.png"), 40, 40));
		btnMyAc.setBounds(474, 10, 40, 40);
		btnMyAc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMyAc.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\MyAcFill.png"), 40, 40));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMyAc.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\MyAcNull.png"), 40, 40));
			}
		});
		btnMyAc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				togglePopUp();
			}
		});
		contentPane.add(btnMyAc);

		JButton btnHome = new JButton();
		btnHome.setBorderPainted(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setBounds(424, 10, 40, 40);
		btnHome.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\HomeNull.png"), 40, 40));
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnHome.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\HomeFill.png"), 40, 40));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnHome.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\HomeNull.png"), 40, 40));
			}
		});
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnHome.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\HomeNull.png"), 40, 40));
				Mainpage mp = Mainpage.getInstance();
				mp.refresh(pi.mb);
				mp.setLocationRelativeTo(pi);
				mp.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnHome);

		lbPrice = new JLabel("New label");
		lbPrice.setOpaque(true);
		lbPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lbPrice.setBackground(SystemColor.controlHighlight);
		lbPrice.setFont(new Font("?????? ??????", Font.BOLD, 15));
		lbPrice.setBounds(0, 99, 526, 32);
		contentPane.add(lbPrice);

		lbProImg = new JLabel();
		lbProImg.setOpaque(true);
		lbProImg.setBackground(SystemColor.control);
		lbProImg.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\product" + pb.getProIdx() + ".png"), 300, 300));
		lbProImg.setBounds(113, 141, 300, 300);
		contentPane.add(lbProImg);

		commentPanel = new JPanel();

		scrollPane = new JScrollPane(commentPanel);
		commentPanel.setLayout(new GridLayout(0, 1, 10, 0));
		scrollPane.setBounds(0, 501, 526, 173);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.add(scrollPane);

		JButton btnCreateReview = new JButton("");
		btnCreateReview.setContentAreaFilled(false);
		btnCreateReview.setBorderPainted(false);
		btnCreateReview.setToolTipText("????????????");
		btnCreateReview.setBounds(474, 451, 40, 40);
		btnCreateReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\ComNull.png"), 40, 40));
		btnCreateReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCreateReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\ComFill.png"), 40, 40));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCreateReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\ComNull.png"), 40, 40));
			}
		});
		btnCreateReview.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateReview cr = CreateReview.getInstance();
				ReviewBean rb = new ReviewBean();
				rb.setMemIdx(pi.mb.getIdx());
				rb.setProIdx(pi.pb.getProIdx());
				System.out.println(rb.getMemIdx() + "/" + rb.getProIdx());
				cr.refresh(rb, true);
				cr.setLocationRelativeTo(pi);
				cr.setVisible(true);
			}
		});
		contentPane.add(btnCreateReview);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "?????????", "?????? ?????? ???", "?????? ?????? ???" }));
		comboBox.setBounds(10, 468, 91, 23);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addData();
			}
		});
		contentPane.add(comboBox);

		btnCart = new JButton("");
		btnCart.setBorderPainted(false);
		btnCart.setContentAreaFilled(false);
		btnCart.setBounds(374, 10, 40, 40);
		btnCart.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\CartNull.png"), 40, 40));
		btnCart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCart.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\CartFill.png"), 40, 40));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCart.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\CartNull.png"), 40, 40));
			}
		});
		btnCart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCart.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\\\CartNull.png"), 40, 40));
				Shopcart sc = Shopcart.getInstance();
				sc.refresh(pi.mb, Constant.PREPAGEPRODUCTINFO);
				sc.setLocationRelativeTo(pi);
				sc.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnCart);

		addData();
		validate();
	}

	// ?????? ???????????? ?????? ??????
	public JPanel createData(ReviewBean bean) {
		JPanel panel = new JPanel();
		panel.setLayout(null);

		StringBuilder cDate = new StringBuilder();
		StringBuilder cs = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		sb.append(bean.getComments());
		if (sb.length() > 5 && sb.substring(0, 5).equals("(?????????)")) {
			cDate.append(bean.getComDate() + " / (?????????)");
			cs.append(sb.substring(5));
		} else {
			cDate.append(bean.getComDate());
			cs.append(sb);
		}
		String isPurchased = "";
		if (bean.isHimBuy()) {
			isPurchased = " (?????????)";
		} else {
			isPurchased = " (?????? ??????)";
		}
		JLabel memName = new JLabel(bean.getMemName() + isPurchased);
		memName.setBounds(10, 10, 480, 15);
		memName.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		panel.add(memName);

		for (int i = 0; i < 5; i++) {
			JLabel starIcon = new JLabel();
			starIcon.setBounds(10 + 15 * i, 25, 15, 15);
			if (i < bean.getStarRating()) {
				starIcon.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\StarFill.png"), 15, 15));
			} else {
				starIcon.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\StarNull.png"), 15, 15));
			}
			panel.add(starIcon);
		}

		JLabel comDate = new JLabel(cDate.toString());
		comDate.setBounds(90, 25, 400, 15);
		comDate.setFont(new Font("?????? ??????", Font.PLAIN, 12));
		panel.add(comDate);

		JTextArea comments = new JTextArea(cs.toString());
		comments.setEditable(false);
		comments.setLineWrap(true);

		JButton btnDeleteReview = new JButton();
		btnDeleteReview.setBounds(460, 10, 30, 30);
		btnDeleteReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\DeleteNull.png"), 30, 30));
		btnDeleteReview.setContentAreaFilled(false);
		btnDeleteReview.setBorderPainted(false);
		btnDeleteReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDeleteReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\DeleteNull.png"), 30, 30));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnDeleteReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\DeleteFill.png"), 30, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDeleteReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\DeleteNull.png"), 30, 30));
			}
		});
		btnDeleteReview.addActionListener(this);
		bean.setDeleteBtn(btnDeleteReview);
		panel.add(btnDeleteReview);

		JButton btnModifyReview = new JButton();
		btnModifyReview.setBounds(420, 10, 30, 30);
		btnModifyReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\EditNull.png"), 30, 30));
		btnModifyReview.setContentAreaFilled(false);
		btnModifyReview.setBorderPainted(false);
		btnModifyReview.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnModifyReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\EditNull.png"), 30, 30));
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnModifyReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\EditFill.png"), 30, 30));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnModifyReview.setIcon(cm.resizeIcon(new ImageIcon("./IMG\\EditNull.png"), 30, 30));
			}
		});
		btnModifyReview.addActionListener(this);
		bean.setModifyBtn(btnModifyReview);
		panel.add(btnModifyReview);

		JScrollPane sc = new JScrollPane(comments);
		sc.setBounds(10, 40, 480, 50);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(sc);

		return panel;
	}

	// ??? ?????? ???????????? ?????? ?????? ??? ?????????
	public void addData() {
		commentPanel.removeAll();
		rbv = sm.selectReview(pb.getProIdx(), comboBox.getSelectedIndex());
		if (rbv.size() > 0) {
			noData.setVisible(false);
			for (int i = 0; i < rbv.size(); i++) {
				ReviewBean rb = rbv.get(i);
				rb.setHimBuy(sm.isPurchased(rb.getMemIdx(), rb.getProIdx()));
				JPanel p = createData(rb);
				p.setPreferredSize(new Dimension(500, 90));
				commentPanel.add(p);
				if (rb.getMemIdx() == mb.getIdx()) {
					rb.getModifyBtn().setVisible(true);
					rb.getDeleteBtn().setVisible(true);
				} else {
					rb.getModifyBtn().setVisible(false);
					if (mb.getType().equals("?????????")) {
						rb.getDeleteBtn().setVisible(true);
					} else {
						rb.getDeleteBtn().setVisible(false);
					}
				}
			}
		} else {
			noData.setVisible(true);
		}
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMinimum());
		repaint();
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int type = -1;
		int idx = -1;
		for (int i = 0; i < rbv.size(); i++) {
			if (rbv.get(i).getDeleteBtn() == e.getSource()) {
				type = 0;
				idx = i;
				break;
			} else if (rbv.get(i).getModifyBtn() == e.getSource()) {
				type = 1;
				idx = i;
				break;
			}
		}
		if (type == 0) {
			int confirm = JOptionPane.showConfirmDialog(pi, "?????? ????????? ?????????????????????????", "??????", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				if (sm.deleteReview(rbv.get(idx))) {
					JOptionPane.showMessageDialog(pi, "?????? ????????? ??????????????? ??????????????????.", "??????", JOptionPane.INFORMATION_MESSAGE);
					addData();
				} else {
					JOptionPane.showMessageDialog(pi, "?????? ?????? ??? ????????? ??????????????????.\n ?????? ??? ?????? ??????????????????.", "??????",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(pi, "?????? ????????? ??????????????????.", "??????", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (type == 1) {
			CreateReview cr = CreateReview.getInstance();
			cr.refresh(rbv.get(idx), false);
			cr.setLocationRelativeTo(pi);
			cr.setVisible(true);
		}
	}

	public void gradeCheck(MemberBean mb) {
		if (this.mb.getIdx() != 0) {
			String oldGrade = this.mb.getGrade();
			long Amount = sm.selectConsumption(mb.getIdx());
			if (Amount >= Constant.GRADEDIAMOND) {
				mb.setGrade(Constant.DIAMOND);
			} else if (Amount >= Constant.GRADEPLATINUM) {
				mb.setGrade(Constant.PLATINUM);
			} else if (Amount >= Constant.GRADEGOLD) {
				mb.setGrade(Constant.GOLD);
			} else if (Amount >= Constant.GRADESILVER) {
				mb.setGrade(Constant.SILVER);
			} else {
				mb.setGrade(Constant.BRONZE);
			}
			if (!mb.getGrade().equals(oldGrade)) {
				JOptionPane.showMessageDialog(pi, "?????? ????????? ?????????????????????. ???????????????.\n"+oldGrade+"->"+mb.getGrade());
			}
		} refresh(pi.pb, mb);
	}
	
	public void togglePopUp() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		Component[] components = contentPane.getComponents();
		for (Component component : components) {
			component.setEnabled(isPopUp);
		}
		for (int i = 0; i < rbv.size(); i++) {
			JButton delbtn = rbv.get(i).getDeleteBtn();
			delbtn.setVisible(isPopUp);
			JButton modibtn = rbv.get(i).getModifyBtn();
			modibtn.setVisible(isPopUp);
		}
		if (isPopUp) {
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			addData();
		} else {
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		}
		try {
			if (isPopUp) {
				isPopUp = !isPopUp;
				btnClosePopUp.setEnabled(false);
				for (int i = 0; i < 150; i++) {
					int x = panelPopUp.getX() + 1;
					panelPopUp.setLocation(x, panelPopUp.getY());
					Thread.sleep((long) Math.pow(i / 50, 2));
				}
			} else {
				isPopUp = !isPopUp;
				for (int i = 0; i < 150; i++) {
					int x = panelPopUp.getX() - 1;
					panelPopUp.setLocation(x, panelPopUp.getY());
					Thread.sleep((long) Math.pow(i / 50, 2));
				}
				btnClosePopUp.setEnabled(true);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
