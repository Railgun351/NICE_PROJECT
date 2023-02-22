//
//package project.ui;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.lang.reflect.Array;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import javax.swing.AbstractButton;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JSpinner;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SpinnerNumberModel;
//import javax.swing.border.Border;
//
//import project.bean.*;
//import project.bean.FactoryItemFactory;
//import project.bean.FactoryItemFromMenu;
//import project.bean.FactoryItemPrinter;
//import project.bean.StarCustomer;
//
//import java.awt.BorderLayout;
//import javax.swing.JScrollBar;
//import java.awt.Checkbox;
//import java.awt.Button;
//
//public class EWT extends JFrame {
//	/**
//	* 
//	*/
//	private static final long serialVersionUID = 1L;
//	JScrollPane scrollPane;
//	ImageIcon icon;
//	private JTextField textField;
//	private int[] scores = new int[5];
//	ArrayList<StarCustomer> customers = new ArrayList<>();
//	String ImgName = "./IMG\\";
//	int score = 4;
//	private JFrame f;
//	// 전화면에서 크릭해서 들어온이미지
//
//	public EWT() {
//
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(420, 665);
//		setTitle("제품 상세 페이지");
//		this.f = this;
//
//		// 백엔드 메인로직
//		List<FactoryItemFromMenu> items = new ArrayList<>();
//		items.add(FactoryItemFactory.createItem(1, "신발이름", 10000, ImgName + "product1.PNG"));
//		items.add(FactoryItemFactory.createItem(1, "", 0, "photo2"));
//		items.add(FactoryItemFactory.createItem(1, "", 0, "photo3"));
//		FactoryItemFromMenu firstItem = items.get(0);
//
//		String name = firstItem.getName();
//		int price = firstItem.getPrice();
//		String photo = firstItem.getPhoto();
//
//		FactoryItemPrinter.printAllitems(items);
//
//		System.out.println(name + price + photo);
//
//		System.out.println(); // 개행
//		System.out.println(); // 개행
//		System.out.println(); // 개행
//		System.out.println(); // 개행
//
//		// ---------------------------
//
//		// 리뷰이름 번호 댓글 날짜
//
//		customers.add(new StarCustomer("김철수1", 1, "별루에요", new Date()));
//		customers.add(new StarCustomer("김철수2", 1, "별루에요", new Date()));
//		customers.add(new StarCustomer("박영희1", 2, "사이즈가작아요", new Date()));
//		customers.add(new StarCustomer("박영희2", 2, "사이즈가작아요", new Date()));
//		customers.add(new StarCustomer("박영희3", 2, "사이즈가작아요", new Date()));
//		customers.add(new StarCustomer("박영희4", 2, "사이즈가작아요", new Date()));
//		customers.add(new StarCustomer("삼식이1", 3, "좋아요", new Date()));
//		customers.add(new StarCustomer("심식이2", 3, "좋아요", new Date()));
//		customers.add(new StarCustomer("이민1", 4, "이뻐요", new Date()));
//		customers.add(new StarCustomer("이민2", 4, "이뻐요", new Date()));
//		customers.add(new StarCustomer("이민3", 4, "이뻐요", new Date()));
//		customers.add(new StarCustomer("오민1", 5, "이뻐요", new Date()));
//		customers.add(new StarCustomer("오민2", 5, "이뻐요", new Date()));
//		System.out.println(); // 개행
//		System.out.println(); // 개행
//		System.out.println(); // 개행
//		System.out.println("별점에관한정보 출력"); // 개행
//		// 입력된 별점에 따라 Restaurant 객체의 receiveScore() 메소드 호출
//
////   	        
////   	     	ArrayList<Customer> customers1 = new ArrayList<>();
////           	// customers 리스트에 고객 정보 추가 (생략)
////          
////               List<Customer> fourStarCustomers1 = new ArrayList<>();
////               for (Customer customer : customers) {
////                   if (customer.getTargetScore() == 3) {
////                       fourStarCustomers1.add(customer);
////                   }
////               }
////            
//		//
////   	            // 2차원 배열에 결과 저장
////   	            String[][] result1 = new String[customers.size()][3];
////   	            for (int i = 0; i < customers.size(); i++) {
////   	                Customer c = customers.get(i);
////   	                result1[i][0] = c.getName();
////   	                result1[i][1] = Integer.toString(c.getTargetScore());
////   	                result1[i][2] = c.getReview();
////   	            }
//		//
////   	            System.out.println(    result1[0][0] );
////   	            System.out.println(    result1[0][0] );
////   	            System.out.println(    result1[0][0] ); 
////   	            System.out.println(    result1[0][0] ); 
//		//
////   	        System.out.println(); // 개행
////   	        System.out.println(); // 개행
//
//		// --------------
//
//		Object[] arr = customers.toArray();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
//		// 첫번째 요소 출력
//		StarCustomer c1 = (StarCustomer) arr[arr.length - 1];
////   	        System.out.println(c1.getName() + ": " + c1.getTargetScore() + " " + c1.getReview() + " " + dateFormat.format(c1.getDate()));
//
//		// 두번째 요소 출력
//
//		StarCustomer c2 = (StarCustomer) arr[arr.length - 2];
////   	        System.out.println(c2.getName() + ": " + c2.getTargetScore() + " " + c2.getReview() + " " + dateFormat.format(c2.getDate()));
//
//		icon = new ImageIcon(ImgName + "back1.PNG");
//
//		// 배경 Panel 생성후 컨텐츠페인으로 지정
//		JPanel background = new JPanel() {
//			/**
//			* 
//			*/
//			private static final long serialVersionUID = 1L;
//
//			public void paintComponent(Graphics g) {
//				
//				g.drawImage(icon.getImage(), 0, 0, null);
//
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
//		background.setLayout(null);
//
//		// -------------------------------------------------------
//
//		JLabel 게시판1 = new JLabel(c1.getReview());
//		게시판1.setBounds(73, 415, 165, 45);
//		background.add(게시판1);
//
//		JLabel 게시판2 = new JLabel(c2.getReview());
//		게시판2.setBounds(73, 470, 165, 45);
//		background.add(게시판2);
//
//		JLabel 게시판1이름 = new JLabel();
//		게시판1이름.setText(c1.getName());
//		게시판1이름.setBounds(12, 412, 57, 15);
//		background.add(게시판1이름);
//
//		JLabel 게시판2이름 = new JLabel(c2.getName());
//		게시판2이름.setBounds(12, 458, 57, 15);
//		background.add(게시판2이름);
//
//		JLabel 게시판별점1 = new JLabel(Integer.toString(c1.getTargetScore()));
//		게시판별점1.setBounds(248, 426, 40, 15);
//		background.add(게시판별점1);
//
//		JLabel 게시판별점2 = new JLabel(Integer.toString(c2.getTargetScore()));
//		게시판별점2.setBounds(248, 474, 40, 15);
//		background.add(게시판별점2);
//
//		// -----------------------------------
//
//		JButton basket = new JButton("");
//		basket.setIcon(new ImageIcon(ImgName + "장바구니.PNG"));
//		basket.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 장바구니담기
//			}
//		});
//		basket.setBounds(0, 531, 401, 48);
//		background.add(basket);
//		scrollPane = new JScrollPane(background);
//
//		JButton immediatepurchase = new JButton("");
//		immediatepurchase.setIcon(new ImageIcon(ImgName + "즉시구매.PNG"));
//		immediatepurchase.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 즉시구매
//			}
//		});
//		immediatepurchase.setBounds(0, 576, 401, 48);
//		background.add(immediatepurchase);
//
//		JButton x_2 = new JButton("");
//		x_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (customers.size() > 2) {
//					customers.remove(customers.size() - 1);
//					Object[] arr = customers.toArray();
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
//					// 첫번째 요소 출력
//
//					StarCustomer c1 = (StarCustomer) arr[arr.length - 1];
//					System.out.println(c2.getName() + ": " + c2.getTargetScore() + " " + c2.getReview() + " "
//							+ dateFormat.format(c2.getDate()));
//
//					게시판1.setText(c1.getReview());
//					게시판1이름.setText(c1.getName());
//					게시판별점1.setText(Integer.toString(c1.getTargetScore()));
//					StarCustomer c2 = (StarCustomer) arr[arr.length - 2];
//					System.out.println(c2.getName() + ": " + c2.getTargetScore() + " " + c2.getReview() + " "
//							+ dateFormat.format(c2.getDate()));
//
//					게시판2이름.setText(c2.getName());
//					게시판별점2.setText(Integer.toString(c2.getTargetScore()));
//					게시판2.setText(c2.getReview());
//				}
//			}
//		});
//		x_2.setBounds(300, 485, 31, 34);
//		background.add(x_2);
//
//		JButton x_1 = new JButton("");
//		x_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 첫번째지우기
//				// customers.remove(customers.size() - 1);;
//
//				if (customers.size() > 2) {
//					customers.remove(customers.size() - 1);
//					Object[] arr = customers.toArray();
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
//
//					StarCustomer c1 = (StarCustomer) arr[arr.length - 1];
//					System.out.println(c2.getName() + ": " + c2.getTargetScore() + " " + c2.getReview() + " "
//							+ dateFormat.format(c2.getDate()));
//
//					게시판1.setText(c1.getReview());
//					게시판1이름.setText(c1.getName());
//					게시판별점1.setText(Integer.toString(c1.getTargetScore()));
//					StarCustomer c2 = (StarCustomer) arr[arr.length - 2];
//					System.out.println(c2.getName() + ": " + c2.getTargetScore() + " " + c2.getReview() + " "
//							+ dateFormat.format(c2.getDate()));
//
//					게시판2이름.setText(c2.getName());
//					게시판별점2.setText(Integer.toString(c2.getTargetScore()));
//					게시판2.setText(c2.getReview());
//
//					repaint();
//				}
//			}
//
//			private String integertoString(int targetScore) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		});
//
//		x_1.setIcon(new ImageIcon(ImgName + "x.PNG"));
//		x_2.setIcon(new ImageIcon(ImgName + "x.PNG"));
//		x_1.setBounds(300, 426, 31, 34);
//		background.add(x_1);
//
//		JButton createReview = new JButton("");
//		createReview.setBackground(Color.WHITE);
//		createReview.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 로직추가
////				customers.add(new StarCustomer("김철수", 1, "별루에요", new Date()));
//				AddReveiw ar = AddReveiw.getInstance(f);
//			}
//		});
//		createReview.setIcon(new ImageIcon(ImgName + "리뷰작성.PNG"));
//		createReview.setBounds(327, 378, 64, 23);
//
//		background.add(createReview);
//
//		JLabel images = new JLabel("");
//		images.setBounds(85, 120, 250, 250);
//		ImageIcon ic = new ImageIcon(photo);
//		Image img = ic.getImage();
//		Image img2 = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
//		ic = new ImageIcon(img2);
//		images.setIcon(ic);
//		background.add(images);
//
//		JButton gotobarket = new JButton("");
//		gotobarket.setIcon(new ImageIcon(ImgName + "CART.PNG"));
//		gotobarket.setBounds(360, 10, 31, 39);
//		background.add(gotobarket);
//		gotobarket.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 장바구니이동
//			}
//		});
//		JButton logout = new JButton("");
//		logout.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 로그아웃페이지
//			}
//		});
//		logout.setIcon(new ImageIcon(ImgName + "LOGOUT.PNG"));
//		logout.setBounds(327, 10, 31, 39);
//		background.add(logout);
//
//		JButton back = new JButton("");
//		back.setIcon(new ImageIcon(ImgName + "BACK.PNG"));
//		back.setBounds(12, 10, 18, 39);
//		back.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// 뒤로가기
//			}
//		});
//		background.add(back);
//
//		JLabel 이름 = new JLabel(name);
//		이름.setBounds(128, 67, 130, 23);
//		background.add(이름);
//
//		JLabel 가격 = new JLabel(Integer.toString(price));
//		가격.setBounds(261, 79, 130, 23);
//		background.add(가격);
//
//		Button star1 = new Button("1star");
//		star1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
//
//				int score = 1;
//
//				for (StarCustomer customer : customers) {
//					if (customer.getTargetScore() == score) {
//						fourStarCustomers1.add(customer);
//					}
//				}
//
//				// 2차원 배열에 결과 저장
//				String[][] result1 = new String[fourStarCustomers1.size()][3];
//				for (int i = 0; i < fourStarCustomers1.size(); i++) {
//
//					StarCustomer c = fourStarCustomers1.get(i);
//					result1[i][0] = c.getName();
//					result1[i][1] = Integer.toString(c.getTargetScore());
//					result1[i][2] = c.getReview();
//				}
//
//				if (fourStarCustomers1.size() >= 2) {
//					게시판1.setText(result1[0][0]);
//					게시판1이름.setText(result1[0][1]);
//					게시판별점1.setText(result1[0][2]);
//					게시판2.setText(result1[1][0]);
//					게시판2이름.setText(result1[1][1]);
//					게시판별점2.setText(result1[1][2]);
//					System.out.println(); // 개행
//					System.out.println(); // 개행
//				}
//				System.out.println(); // 개행
//				System.out.println(); // 개행
//
//			}
//
//		});
//		star1.setBounds(10, 378, 40, 23);
//		background.add(star1);
//
//		Button star2 = new Button("2star");
//		star2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
//
//				int score = 2;
//
//				for (StarCustomer customer : customers) {
//					if (customer.getTargetScore() == score) {
//						fourStarCustomers1.add(customer);
//					}
//				}
//				System.out.println(fourStarCustomers1.size());
//
//				// 2차원 배열에 결과 저장
//				String[][] result1 = new String[fourStarCustomers1.size()][3];
//				for (int i = 0; i < fourStarCustomers1.size(); i++) {
//
//					StarCustomer c = fourStarCustomers1.get(i);
//					result1[i][0] = c.getName();
//					result1[i][1] = Integer.toString(c.getTargetScore());
//					result1[i][2] = c.getReview();
//				}
//
//				if (fourStarCustomers1.size() >= 2) {
//					게시판1.setText(result1[0][0]);
//					게시판1이름.setText(result1[0][1]);
//					게시판별점1.setText(result1[0][2]);
//					게시판2.setText(result1[1][0]);
//					게시판2이름.setText(result1[1][1]);
//					게시판별점2.setText(result1[1][2]);
//					System.out.println(); // 개행
//					System.out.println(); // 개행
//				}
//
//			}
//		});
//		star2.setBounds(66, 378, 40, 23);
//		background.add(star2);
//
//		Button star3 = new Button("3star");
//		star3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				ArrayList<StarCustomer> customers1 = new ArrayList<>();
//				// customers 리스트에 고객 정보 추가 (생략)
//
//				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
//
//				int score = 3;
//
//				for (StarCustomer customer : customers) {
//					if (customer.getTargetScore() == score) {
//						fourStarCustomers1.add(customer);
//					}
//				}
//
//				// 2차원 배열에 결과 저장
//				String[][] result1 = new String[fourStarCustomers1.size()][3];
//				for (int i = 0; i < fourStarCustomers1.size(); i++) {
//
//					StarCustomer c = fourStarCustomers1.get(i);
//					result1[i][0] = c.getName();
//					result1[i][1] = Integer.toString(c.getTargetScore());
//					result1[i][2] = c.getReview();
//				}
//
//				if (fourStarCustomers1.size() >= 2) {
//					게시판1.setText(result1[0][0]);
//					게시판1이름.setText(result1[0][1]);
//					게시판별점1.setText(result1[0][2]);
//					게시판2.setText(result1[1][0]);
//					게시판2이름.setText(result1[1][1]);
//					게시판별점2.setText(result1[1][2]);
//					System.out.println(); // 개행
//					System.out.println(); // 개행
//				}
//
//				System.out.println(); // 개행
//				System.out.println(); // 개행
//
//			}
//		});
//		star3.setBounds(128, 378, 40, 23);
//		background.add(star3);
//
//		Button star4 = new Button("4star");
//		star4.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				ArrayList<StarCustomer> customers1 = new ArrayList<>();
//				// customers 리스트에 고객 정보 추가 (생략)
//
//				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
//
//				int score = 4;
//
//				for (StarCustomer customer : customers) {
//					if (customer.getTargetScore() == score) {
//						fourStarCustomers1.add(customer);
//					}
//				}
//
//				// 2차원 배열에 결과 저장
//				String[][] result1 = new String[fourStarCustomers1.size()][3];
//				for (int i = 0; i < fourStarCustomers1.size(); i++) {
//
//					StarCustomer c = fourStarCustomers1.get(i);
//					result1[i][0] = c.getName();
//					result1[i][1] = Integer.toString(c.getTargetScore());
//					result1[i][2] = c.getReview();
//				}
//
//				if (fourStarCustomers1.size() >= 2) {
//					게시판1.setText(result1[0][0]);
//					게시판1이름.setText(result1[0][1]);
//					게시판별점1.setText(result1[0][2]);
//					게시판2.setText(result1[1][0]);
//					게시판2이름.setText(result1[1][1]);
//					게시판별점2.setText(result1[1][2]);
//					System.out.println(); // 개행
//					System.out.println(); // 개행
//				}
//				System.out.println(); // 개행
//				System.out.println(); // 개행
//			}
//		});
//		star4.setBounds(187, 378, 40, 23);
//		background.add(star4);
//
//		Button star5 = new Button("5star");
//		star5.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
//
//				int score = 5;
//
//				for (StarCustomer customer : customers) {
//					if (customer.getTargetScore() == score) {
//						fourStarCustomers1.add(customer);
//					}
//				}
//
//				// 2차원 배열에 결과 저장
//				String[][] result1 = new String[fourStarCustomers1.size()][3];
//				for (int i = 0; i < fourStarCustomers1.size(); i++) {
//
//					StarCustomer c = fourStarCustomers1.get(i);
//					result1[i][0] = c.getName();
//					result1[i][1] = Integer.toString(c.getTargetScore());
//					result1[i][2] = c.getReview();
//				}
//
//				if (fourStarCustomers1.size() >= 2) {
//					게시판1.setText(result1[0][0]);
//					게시판1이름.setText(result1[0][1]);
//					게시판별점1.setText(result1[0][2]);
//					게시판2.setText(result1[1][0]);
//					게시판2이름.setText(result1[1][1]);
//					게시판별점2.setText(result1[1][2]);
//					System.out.println(); // 개행
//					System.out.println(); // 개행
//				}
//
//				System.out.println(); // 개행
//				System.out.println(); // 개행
//
//			}
//		});
//		star5.setBounds(248, 378, 40, 23);
//		background.add(star5);
//
//		setContentPane(scrollPane);
//		setVisible(true);
//	}
//
//	static class AddReveiw extends JDialog {
//
//		private static AddReveiw instance;
//		
//		public static AddReveiw getInstance(JFrame f) {
//			if (instance == null) {
//				synchronized (AddReveiw.class) {
//					instance = new AddReveiw(f);
//				}
//			}
//			return instance;
//		}
//		
//		private AddReveiw(JFrame f) {
//			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			setTitle("리뷰 작성");
//			setSize(300, 100);
//			setLocationRelativeTo(f);
//			setModal(true);
//			setLayout(new BorderLayout());
//			JPanel p1 = new JPanel(new BorderLayout());
//			JLabel commentTitle = new JLabel("리뷰 작성(0/100)");
//			commentTitle.setHorizontalAlignment(JLabel.CENTER);
//			p1.add(commentTitle,BorderLayout.NORTH);
//			JTextArea ta = new JTextArea();
//			ta.setLineWrap(true);
//			ta.addKeyListener(new KeyAdapter() {
//				@Override
//				public void keyReleased(KeyEvent e) {
//					int txtCnt = ta.getText().length();
//					commentTitle.setText("리뷰 작성("+txtCnt+"/100)");
//					if (txtCnt > 100) {
//						commentTitle.setForeground(Color.RED);
//					} else {
//						commentTitle.setForeground(Color.BLACK);
//					}
//				}
//			});
//			JScrollPane sp = new JScrollPane(ta);
//			p1.add(sp, BorderLayout.CENTER);
//			add(p1,BorderLayout.CENTER);
//			JPanel p2 = new JPanel(new BorderLayout());
//			SpinnerNumberModel model = new SpinnerNumberModel(1,1,5,1);
//			JSpinner sn = new JSpinner(model);
//			p2.add(sn, BorderLayout.CENTER);
//			JLabel starRating = new JLabel("별점");
//			p2.add(starRating,BorderLayout.NORTH);
//			add(p2,BorderLayout.EAST);
//			JButton confirmBtn = new JButton("작성");
//			p2.add(confirmBtn, BorderLayout.SOUTH);
//
//			setVisible(true);
//		}
//	}
//
//	public static void main(String[] args) {
//		EWT frame = new EWT();
//	}
//}
