package view_gang;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import barket.ArrayGenerator;
import barket.Product;
import barket.ProductIterator;

import java.awt.TextField;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
 
public class shopcart extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane;
    ImageIcon icon;
    private JTextField textField;
    private int[] scores = new int[5];
    
    String ImgName =  "C:/Users/dita810/Downloads/wLSAKR/NICE_PROJECT/IMG/";
private int index; // 현재 인덱스 저장용
private static final String[] IMAGES = { // 이미지 경로 문자열로 저장
"C:/Users/dita810/Desktop/myJava/SWING/src/afasf.PNG",
"C:/Users/dita810/Desktop/myJava/SWING/src/afasf.PNG",
"C:/Users/dita810/Desktop/myJava/SWING/src/afasf.PNG",
};
public int add1=0;
public int add2=0;

public int add3=0;


    public shopcart() {
    	
    	  
           /// 데이터입력 이름 ,주소 ,재고량 ,가격
           
    	Product[] products = new Product[]{
    		    new Product("나이키 이름이름", ImgName+"shoose1.PNG", 10, 10000),
    		    new Product("나이키이르이름2", ImgName+"shoose2.PNG", 20, 20000)
    		};

    		Product[] productArray = new Product[products.length];
    		String[] names = new String[products.length];
    		ImageIcon[] icons = new ImageIcon[products.length];
    		int[] quantity = new int[products.length];
    		int[] price = new int[products.length];

    		int i = 0;
    		for (Product product : new ProductIterator(products)) {
    		    productArray[i++] = product;
    		}

    		// 배열에 정보 저장
    		for (int j = 0; j < productArray.length; j++) {
    		    icons[j] = new ImageIcon(productArray[j].getImage());
    		    names[j] = productArray[j].getName();
    		    quantity[j] = productArray[j].getQuantity();
    		    price[j] = productArray[j].getPrice();
    		}

    		// 배열 출력
    		for (int j = 0; j < productArray.length; j++) {
    		    System.out.println("Name: " + names[j]);
    		    System.out.println("Image: " + icons[j]);
    		    System.out.println("Quantity: " + quantity[j]);
    		    System.out.println("Price: " + price[j]);
    		   
    		    System.out.println();
    		}

    	
        
    		String[][] arrays = new String[quantity.length][];
    		for (int j = 0; j < quantity.length; j++) {
    		    ArrayGenerator intArray = new ArrayGenerator(quantity[j]);
    		    int[] array = intArray.getArray();
    		    arrays[j] = Arrays.toString(array).replaceAll("[\\[\\]]", "").split(", ");
    		}

    		// 배열 출력
    		for (int j = 0; j < arrays.length; j++) {
    		    System.out.println("Array " + j + ": " + Arrays.toString(arrays[j]));
    		}
    		
    		
        icon = new ImageIcon(ImgName+"back2.PNG");
       
        //배경 Panel 생성후 컨텐츠페인으로 지정      
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
        scrollPane = new JScrollPane(background);
        
        JButton gotobarket = new JButton("");
        gotobarket.setIcon(new ImageIcon(ImgName+"CART.PNG"));
        gotobarket.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
     
        gotobarket.setBounds(370, 10, 31, 39);
        background.add(gotobarket);
        
        JButton logout = new JButton("");
        logout.setIcon(new ImageIcon(ImgName+"LOGOUT.PNG"));
        logout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        logout.setBounds(327, 10, 31, 39);
        background.add(logout);
        
        JButton back = new JButton("");
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        back.setIcon(new ImageIcon(ImgName+"BACK.PNG"));
        back.setBounds(12, 10, 18, 39);
        background.add(back);
        
        JTextPane 회원이름 = new JTextPane();
        회원이름.setFont(new Font("굴림", Font.PLAIN, 18));
        회원이름.setBackground(SystemColor.menu);
        회원이름.setForeground(Color.BLACK);
        회원이름.setText("홍길동");
        회원이름.setBounds(136, 84, 61, 33);
        background.add(회원이름);
        
        

     

        JLabel 가격 = new JLabel("0");
        가격.setBounds(321, 531, 99, 33);
        background.add(가격);
      
            
        JComboBox comboBox = new JComboBox(arrays[0]);
       
        comboBox.setBounds(244, 271, 54, 23);
        background.add(comboBox);
       
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 String selectedItem = (String)comboBox.getSelectedItem();

                 
                 add1=Integer.parseInt(selectedItem)*(price[0]);
         		System.out.print(add1);
         		add3=add1+add2;
         	  	가격.setText(Integer.toString(add1));
            }
        });
		//System.out.print(a);
      //  System.out.print(selectedItem);
   

        JComboBox comboBox_1 = new JComboBox(arrays[1]);
        comboBox_1.addActionListener(new ActionListener() {
        	
        	
 	        
 	        
            @Override
            public void actionPerformed(ActionEvent e) {
           	 String selectedItem = (String)comboBox_1.getSelectedItem();

                
                add2=Integer.parseInt(selectedItem)*(price[0]);
        		System.out.print(add2);
        		add3=add1+add2;
        	  	가격.setText(Integer.toString(add3));
           }
        });
    	
    	
        comboBox_1.setBounds(244, 456, 54, 23);
        background.add(comboBox_1);
        
        JLabel 이미지1 = new JLabel("");
        이미지1.setIcon(icons[0]);
        이미지1.setBounds(24, 154, 150, 151);
        background.add(이미지1);
        
        JLabel 이미지2 = new JLabel("");
        이미지2.setIcon(icons[1]);
        이미지2.setBounds(12, 332, 172, 166);
        background.add(이미지2);
        
        JLabel 남성신발 = new JLabel("남성신발");
        남성신발.setBounds(196, 186, 74, 21);
        background.add(남성신발);
        
        JLabel 남성신발_1 = new JLabel("남성신발");
        남성신발_1.setBounds(196, 375, 74, 21);
        background.add(남성신발_1);
        
        JLabel 신발이름2 = new JLabel( names[1]);
        신발이름2.setBounds(196, 345, 150, 33);
        background.add(신발이름2);
        
        JLabel 신발이름1 = new JLabel(names[0]);
        신발이름1.setBounds(194, 154, 150, 33);
        background.add(신발이름1);
        
        JLabel 장바구니 = new JLabel("의장바구니");
        장바구니.setFont(new Font("굴림", Font.PLAIN, 18));
        장바구니.setBounds(199, 84, 120, 33);
        background.add(장바구니);
        
        JLabel 각격1 = new JLabel(Integer.toString(price[0]));
        각격1.setBounds(241, 234, 150, 33);
        background.add(각격1);
        
        JLabel lblNewLabel = new JLabel(Integer.toString(price[1]));
        lblNewLabel.setBounds(244, 419, 150, 27);
        background.add(lblNewLabel);
        
        JButton immediatepurchase = new JButton("");
        immediatepurchase.setIcon(new ImageIcon(ImgName+"주문하기.png"));
        immediatepurchase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        immediatepurchase.setBounds(0, 574, 432, 58);
        background.add(immediatepurchase);
        System.out.print(add1+add2);
   
      
 
  
        setContentPane(scrollPane);
    }
 

    
    public static void main(String[] args) {
        shopcart frame = new shopcart();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,665);
        frame.setVisible(true);
    }
}
