package project.ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import project.db.*;
import project.db.ShopMgr;
import project.bean.*;




public class d extends JFrame {
	/**
	 * 
	 */
	
	

	
	private JLabel  게시판1;
	
	private JLabel 게시판별점1; 



	private JLabel 게시판1이름;
	private JLabel 게시판2이름;
	private JLabel 게시판2;
	private JLabel 게시판별점2;


	JFrame frame;

	
	
	
	
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane;
	ImageIcon icon;
	private JTextField textField;
	private int[] scores = new int[5];
	public ArrayList<StarCustomer> customers = new ArrayList<>();
	int score = 4;
	// 전화면에서 크릭해서 들어온이미지
	public int x1 = 1;
	public int x2 = 2;
	public int page = 1;

	String ImgName = "C:/Users/dita810/Downloads/wLSAKR/NICE_PROJECT/IMG/";
	
	
	
	//몇번째인지 보고검색하는로직
	
	//************************
	//*************************
	//************************
		//*************************
	//이떄 실행하는값은 firstItem.getNum() 이며 아래에 코드를 봐주세요
	public  ArrayList<StarCustomer> datacreat(int ItemNum)throws Exception  {
		// ItemNum == 몇번쨰아이템인지 들고옴 db에선 PRO_IDX
		// 고유번호 아이디 별점 내용 날짜
		ArrayList<StarCustomer> customers = new ArrayList<>();

		ShopMgr StrService = ShopMgr.getInstance();
		List<starDto> StarDtoList = StrService.getStarDtoLists(ItemNum);

		Object[] arr = new Object[StarDtoList.size()];
		int i = 0;

		for (starDto starDto : StarDtoList) {
			customers.add(new StarCustomer(starDto.getMEM_IDX(), starDto.getSTAR_RATING(), starDto.getCOMMENTS(),
					starDto.getCOM_DATE()));
			System.out.println(starDto.getMEM_IDX() + starDto.getSTAR_RATING() + starDto.getCOMMENTS());
			System.out.println(starDto.getCOM_DATE());
		}
		System.out.println(); // 개행
		System.out.println(); // 개행
		System.out.println(); // 개행
		System.out.println("별점에관한정보 출력"); // 개행


	return customers;
	}
	//************************
	//*************************
	//************************
		//*************************
	
//출력메소드
	//************************
	//*************************
	//*****************	//************************
	//*************************
	//************************
		//*************************
	
		//*************************
//	/ViewData에값을 넣을수 있습니다. 값을넣어서 	items.add(FactoryItemFactory.createItem(1, "신발이름", 10000, ImgName + "bigshoose1.PNG")); 을바꿀수 있습니다.
	//전체화면 번호 신발이름 가격 이미지이름을 재활용하여 넣을수 있습니다.
	public List<FactoryItemFromMenu> ViewData()throws Exception  {
	//  숫자 글자 숫자 글자  => 번호 이름 가격 이미지

		
		// 백엔드 메인로직
	//factory 사용시에 해당로직을 사용합니다 이떄 번호 이름 가격 이미지를 출력합니다.
	List<FactoryItemFromMenu> items = new ArrayList<>();
	items.add(FactoryItemFactory.createItem(1, "신발이름", 10000, ImgName + "bigshoose1.PNG"));
	return items;
	}
	//다시그리는 화면
	
public void updateDisplay(int x) {
    System.out.print(customers.size());

    if (customers.size() >= x) {
        this.x1 = x * 2-1;
        this.x2 = x * 2 ;
        if (customers.size() >= x * 2) {
            StarCustomer c1 = customers.get(customers.size() - this.x1);
            System.out.println("첫번째ARR");
            System.out.println(customers.size());
            System.out.println("첫번쨰C1");
            System.out.println(c1.getMembername());
            게시판1.setText(c1.getReview());
            게시판1이름.setText(String.valueOf(c1.getMembername()));
            게시판별점1.setText(String.valueOf(c1.getTargetScore()));
            StarCustomer c2 = customers.get(customers.size() - this.x2);

            게시판2이름.setText(String.valueOf(c2.getMembername()));
            게시판별점2.setText(String.valueOf(c2.getTargetScore()));
            게시판2.setText(String.valueOf(c2.getReview()));
            System.out.println(c2);
            repaint();
        } else if (customers.size() >= x * 2 - 1) {
            StarCustomer c1 = customers.get(customers.size() - this.x1);
            System.out.println("두번쨰c1은");
            System.out.println(String.valueOf(c1.getMembername()));
            게시판1.setText(String.valueOf(c1.getReview()));
            게시판1이름.setText(String.valueOf(c1.getMembername()));
            게시판별점1.setText(String.valueOf(c1.getTargetScore()));

            게시판2이름.setText("");
            게시판별점2.setText("");
            게시판2.setText("");
            repaint();
        } else if (customers.size() >= x * 2 - 2) {
            게시판1.setText("");
            게시판1이름.setText("");
            게시판별점1.setText("");

            게시판2이름.setText("");
            게시판별점2.setText("");
            게시판2.setText("");
            repaint();
        }
        repaint(); // 다시 그리도록 repaint() 메소드 호출
    }
    
    
}

	public d() throws Exception {

		
		List<FactoryItemFromMenu> items =  ViewData();
		// 백엔드 메인로직
		//factory 사용시에 해당로직을 사용합니다 이떄 번호 이름 가격 이미지를 출력합니다.
	
		FactoryItemFromMenu firstItem = items.get(0);
		 int ComPageNum = firstItem.getNum();
		String name = firstItem.getName();
		int price = firstItem.getPrice();
		String photo = firstItem.getPhoto();

		FactoryItemPrinter.printAllitems(items);

		System.out.println(name + price + photo);

		System.out.println(); // 개행
		System.out.println(); // 개행
		System.out.println(); // 개행
		System.out.println(); // 개행

		// ---------------------------
		
		
		ArrayList<StarCustomer> customers = datacreat(firstItem.getNum());
		
		this.customers=customers;
		Object[] arr1 = customers.toArray();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
		// 첫번째 요소 출력
		StarCustomer c1 = (StarCustomer) arr1[arr1.length - 1];
		System.out.println(c1.getMembername() + ": " + c1.getTargetScore() + " " + c1.getReview() + " "
				+ dateFormat.format(c1.getDate()));

		// 두번째 요소 출력

		StarCustomer c2 = (StarCustomer) arr1[arr1.length - 2];
		System.out.println(c2.getMembername() + ": " + c2.getTargetScore() + " " + c2.getReview() + " "
				+ dateFormat.format(c2.getDate()));

		icon = new ImageIcon(ImgName + "back1.PNG");
	

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
		background.setLayout(null);
	       JPanel CreateReview = new JPanel() {
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
	        CreateReview.setLayout(null);
		// -------------------------------------------------------

	    this.게시판1 = new JLabel(c1.getReview());
	    this.게시판1.setBounds(73, 415, 165, 45);
		background.add(this.게시판1);
	    this.게시판별점1 = new JLabel(Integer.toString(c1.getTargetScore()));
	    this.게시판별점1.setBounds(248, 426, 40, 15);
		background.add(    this.게시판별점1);

	    this.게시판1이름 = new JLabel();
	    this.게시판1이름.setText(Integer.toString(c1.getMembername()));
	    this.게시판1이름.setBounds(12, 430, 57, 15);
		background.add(    this.게시판1이름);

		this.게시판2이름 = new JLabel(Integer.toString(c2.getMembername()));
	    this.게시판2이름.setBounds(12, 485, 57, 15);
		background.add(    this.게시판2이름);
	    this.게시판2 = new JLabel(c2.getReview());
	    this.게시판2.setBounds(73, 470, 165, 45);
		background.add(    this.게시판2);

	    this.게시판별점2 = new JLabel(Integer.toString(c2.getTargetScore()));
	    this.게시판별점2.setBounds(248, 474, 40, 15);
		background.add(    this.게시판별점2);

		// -----------------------------------

		JButton basket = new JButton("");
		basket.setBorder(null);
		basket.setIcon(new ImageIcon(ImgName + "장바구니.PNG"));
		basket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 장바구니담기
			}
		});
		basket.setBounds(0, 531, 401, 48);
		background.add(basket);
		scrollPane = new JScrollPane(background);

		JButton immediatepurchase = new JButton("");
		immediatepurchase.setBorder(null);
		immediatepurchase.setIcon(new ImageIcon(ImgName + "즉시구매.PNG"));
		immediatepurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 즉시구매
			}
		});
		immediatepurchase.setBounds(0, 576, 401, 48);
		background.add(immediatepurchase);

		JButton x_2 = new JButton("");
		immediatepurchase.setBorder(null);
		x_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//여기수정
	
				  StarCustomer a = customers.remove(customers.size() - x2);
				  	
			  		FactoryItemFromMenu firstItem = items.get(0);
				
					 
					ShopMgr obj = ShopMgr.getInstance();;
					  
			  		boolean isDeleted =  obj.deleteStarCustomer(ComPageNum, a.getMembername(), a.getDate(), a.getReview(), a.getTargetScore());
			  		
			  		
			  		
			  		    updateDisplay(page);
				
			
				
			
				}});
		x_2.setBounds(300, 485, 31, 34);
		background.add(x_2);

		JButton x_1 = new JButton("");
		x_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
//여기수정
		  		  StarCustomer a = customers.remove(customers.size() - x1);
		  	
		  		FactoryItemFromMenu firstItem = items.get(0);
			
				 
				ShopMgr obj = ShopMgr.getInstance();;
				  
		  		boolean isDeleted =  obj.deleteStarCustomer(ComPageNum, a.getMembername(), a.getDate(), a.getReview(), a.getTargetScore());
		  		
		  		
		  		
		  		    updateDisplay(page);
		    }
		    
		});

		x_1.setBorder(null);
		x_2.setBorder(null);
		x_1.setIcon(new ImageIcon(ImgName + "x.PNG"));
		x_2.setIcon(new ImageIcon(ImgName + "x.PNG"));
		x_1.setBounds(300, 426, 31, 34);
		background.add(x_1);

		JButton createReview = new JButton("");
		createReview.setBackground(Color.WHITE);
		createReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로직추가
				// customers.add(new StarCustomer(1,2, 1, "별루에요", new Date()));
				
				dispose();
				CreateReview createReview;
				try {
					createReview = new CreateReview();
					createReview.frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		
			    
			        
				//a,b->number(값은몇인지모름)
			//return [a=firstItem.getNum();,b=0];
			}
		});
		createReview.setBorder(null);
		createReview.setIcon(new ImageIcon(ImgName + "리뷰작성.PNG"));
		createReview.setBounds(327, 378, 64, 23);

		background.add(createReview);

		JLabel images = new JLabel("");
		images.setBounds(0, 139, 401, 229);
		images.setIcon(new ImageIcon(photo));
		background.add(images);

		JButton gotobarket = new JButton("");
		gotobarket.setIcon(new ImageIcon(ImgName + "CART.PNG"));
		gotobarket.setBounds(357, 10, 31, 39);
		background.add(gotobarket);
		gotobarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 장바구니이동
			}
		});
		gotobarket.setBorder(null);
		JButton logout = new JButton("");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그아웃페이지
			}
		});
		logout.setBorder(null);
		logout.setIcon(new ImageIcon(ImgName + "LOGOUT.PNG"));
		logout.setBounds(327, 10, 31, 39);
		background.add(logout);

		JButton back = new JButton("");
		back.setIcon(new ImageIcon(ImgName + "BACK.PNG"));
		back.setBounds(12, 10, 18, 39);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 뒤로가기
			}
		});
		back.setBorder(null);
		background.add(back);

		JLabel 이름 = new JLabel(name);
		이름.setBounds(128, 67, 130, 23);
		background.add(이름);

		JLabel 가격 = new JLabel(Integer.toString(price));
		가격.setBounds(261, 79, 130, 23);
		background.add(가격);

		Button star1 = new Button("1star");
		star1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();

				int score = 1;

				for (StarCustomer customer : customers) {
					if (customer.getTargetScore() == score) {
						fourStarCustomers1.add(customer);
					}
				}

				// 2차원 배열에 결과 저장
				String[][] result1 = new String[fourStarCustomers1.size()][3];
				for (int i = 0; i < fourStarCustomers1.size(); i++) {

					StarCustomer c = fourStarCustomers1.get(i);
					result1[i][0] = Integer.toString(c.getMembername());
					result1[i][1] = Integer.toString(c.getTargetScore());
					result1[i][2] = c.getReview();
				}

				if (fourStarCustomers1.size() >= 2) {
					게시판1이름.setText(result1[0][0]);
					게시판별점1.setText(result1[0][1]);
					게시판1.setText(result1[0][2]);
					게시판2이름.setText(result1[1][0]);
					게시판별점2.setText(result1[1][1]);
					게시판2.setText(result1[1][2]);
					System.out.println(); // 개행
					System.out.println(); // 개행
				}
				System.out.println(); // 개행
				System.out.println(); // 개행

			}

		});
		star1.setBounds(10, 378, 40, 23);
		background.add(star1);

		Button star2 = new Button("2star");
		star2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();

				int score = 2;

				for (StarCustomer customer : customers) {
					if (customer.getTargetScore() == score) {
						fourStarCustomers1.add(customer);
					}
				}
				System.out.println(fourStarCustomers1.size());

				// 2차원 배열에 결과 저장
				String[][] result1 = new String[fourStarCustomers1.size()][3];
				for (int i = 0; i < fourStarCustomers1.size(); i++) {

					StarCustomer c = fourStarCustomers1.get(i);
					result1[i][0] = Integer.toString(c1.getMembername());
					result1[i][1] = Integer.toString(c.getTargetScore());
					result1[i][2] = c.getReview();
				}

				if (fourStarCustomers1.size() >= 2) {
					게시판1이름.setText(result1[0][0]);
					게시판별점1.setText(result1[0][1]);
					게시판1.setText(result1[0][2]);
					게시판2이름.setText(result1[1][0]);
					게시판별점2.setText(result1[1][1]);
					게시판2.setText(result1[1][2]);
					System.out.println(); // 개행
					System.out.println(); // 개행
				}

			}
		});
		star2.setBounds(66, 378, 40, 23);
		background.add(star2);

		Button star3 = new Button("3star");
		star3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<StarCustomer> customers1 = new ArrayList<>();
				// customers 리스트에 고객 정보 추가 (생략)

				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();

				int score = 3;

				for (StarCustomer customer : customers) {
					if (customer.getTargetScore() == score) {
						fourStarCustomers1.add(customer);
					}
				}

				// 2차원 배열에 결과 저장
				String[][] result1 = new String[fourStarCustomers1.size()][3];
				for (int i = 0; i < fourStarCustomers1.size(); i++) {

					StarCustomer c = fourStarCustomers1.get(i);
					result1[i][0] = Integer.toString(c1.getMembername());
					result1[i][1] = Integer.toString(c.getTargetScore());
					result1[i][2] = c.getReview();
				}

				if (fourStarCustomers1.size() >= 2) {
					게시판1이름.setText(result1[0][0]);
					게시판별점1.setText(result1[0][1]);
					게시판1.setText(result1[0][2]);
					게시판2이름.setText(result1[1][0]);
					게시판별점2.setText(result1[1][1]);
					게시판2.setText(result1[1][2]);
					System.out.println(); // 개행
					System.out.println(); // 개행
				}

				System.out.println(); // 개행
				System.out.println(); // 개행

			}
		});
		star3.setBounds(128, 378, 40, 23);
		background.add(star3);

		Button star4 = new Button("4star");
		star4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ArrayList<StarCustomer> customers1 = new ArrayList<>();
				// customers 리스트에 고객 정보 추가 (생략)

				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();

				int score = 4;

				for (StarCustomer customer : customers) {
					if (customer.getTargetScore() == score) {
						fourStarCustomers1.add(customer);
					}
				}

				// 2차원 배열에 결과 저장
				String[][] result1 = new String[fourStarCustomers1.size()][3];
				for (int i = 0; i < fourStarCustomers1.size(); i++) {

					StarCustomer c = fourStarCustomers1.get(i);
					result1[i][0] = Integer.toString(c1.getMembername());
					result1[i][1] = Integer.toString(c.getTargetScore());
					result1[i][2] = c.getReview();
				}

				if (fourStarCustomers1.size() >= 2) {
					게시판1이름.setText(result1[0][0]);
					게시판별점1.setText(result1[0][1]);
					게시판1.setText(result1[0][2]);
					게시판2이름.setText(result1[1][0]);
					게시판별점2.setText(result1[1][1]);
					게시판2.setText(result1[1][2]);
					System.out.println(); // 개행
					System.out.println(); // 개행
				}
				System.out.println(); // 개행
				System.out.println(); // 개행
			}
		});
		star4.setBounds(187, 378, 40, 23);
		background.add(star4);

		Button star5 = new Button("5star");
		star5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<StarCustomer> fourStarCustomers1 = new ArrayList<>();

				int score = 5;

				for (StarCustomer customer : customers) {
					if (customer.getTargetScore() == score) {
						fourStarCustomers1.add(customer);
					}
				}

				// 2차원 배열에 결과 저장
				String[][] result1 = new String[fourStarCustomers1.size()][3];
				for (int i = 0; i < fourStarCustomers1.size(); i++) {

					StarCustomer c = fourStarCustomers1.get(i);
					result1[i][0] = Integer.toString(c1.getMembername());
					result1[i][1] = Integer.toString(c.getTargetScore());
					result1[i][2] = c.getReview();
				}

				if (fourStarCustomers1.size() >= 2) {
					게시판1이름.setText(result1[0][0]);
					게시판별점1.setText(result1[0][1]);
					게시판1.setText(result1[0][2]);
					게시판2이름.setText(result1[1][0]);
					게시판별점2.setText(result1[1][1]);
					게시판2.setText(result1[1][2]);
					System.out.println(); // 개행
					System.out.println(); // 개행
				}

				System.out.println(); // 개행
				System.out.println(); // 개행

			}
		});
		star5.setBounds(248, 378, 40, 23);
		background.add(star5);

		JButton btnNewButton = new JButton("1");
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				d.this.page=1;
				updateDisplay(1);
			}
		});
		btnNewButton.setBounds(337, 415, 58, 15);
		background.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.this.page=2;
		
				updateDisplay(2);
		
			}
		});
		btnNewButton_1.setBounds(337, 435, 58, 15);
		background.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("3");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				d.this.page=3;
				updateDisplay(3);
				
			}
			
		});
		btnNewButton_2.setBounds(337, 453, 58, 15);
		background.add(btnNewButton_2);

		JButton btnNewButton_1_1 = new JButton("4");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.this.page=4;
	
				updateDisplay(4);
			}
		});
		btnNewButton_1_1.setBounds(337, 473, 58, 15);
		background.add(btnNewButton_1_1);

		JButton btnNewButton_2_1 = new JButton("5");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.this.page=5;
				updateDisplay(5);
			}
		});
		btnNewButton_2_1.setBounds(338, 491, 58, 15);
		background.add(btnNewButton_2_1);

		JButton btnNewButton_1_1_1 = new JButton("6");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			
				
			d.this.page=6;
				updateDisplay(6);
				
				
				
				
			

			}
		});
		
		btnNewButton_1_1_1.setBounds(338, 511, 58, 15);
		background.add(btnNewButton_1_1_1);

		JLabel lblNewLabel = new JLabel("고객번호");
		lblNewLabel.setBounds(4, 415, 57, 15);
		background.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("고객번호");
		lblNewLabel_1.setBounds(0, 460, 57, 15);
		background.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("별점");
		lblNewLabel_2.setBounds(245, 407, 57, 15);
		background.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("별점");
		lblNewLabel_2_1.setBounds(245, 451, 57, 15);
		background.add(lblNewLabel_2_1);

		setContentPane(scrollPane);}
		
		



	public static void main(String[] args) throws Exception {
		d frame = new d();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 665);
		frame.setVisible(true);
	}
}