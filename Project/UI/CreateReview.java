package project.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import project.db.ShopMgr;
public class CreateReview {
    String ImgName =  "C:/Users/dita810/Downloads/wLSAKR/NICE_PROJECT/IMG/";
	JFrame frame;
	private JTextField commentsT;
	public int dbstate=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateReview window = new CreateReview();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//********************
	//********************
	//********************
	//********************
	//********************
	//db인선트중     strUpdate.updateStarCustomer(proIdx, memIdx, comments, starRating); 합니다
	//이떄 임의로 proIdx=1 memIdx=2 로 아래에 넣었습니다 수정시에 확인부탁드립니다
	// 이떄 위의 proIdx memIdx 는받아야할값입니다 로그인한 상품번호와 고객번호에 해당합니다.
public void CreateUser(int proIdx, int memIdx, String comments, int starRating){
	try {
		if(dbstate==0) {
			//중복클릭방지 얼리 리턴
			return ;
		}
		  dbstate=0;
	    ShopMgr strUpdate = ShopMgr.getInstance();
	    System.out.println(proIdx + "" + memIdx + comments + starRating);
	    strUpdate.updateStarCustomer(proIdx, memIdx, comments, starRating);
	    System.out.println("실행됨");
	  
		//값을 0으로바꿈
	} catch (Exception e) {
	    System.err.println("에러 발생: " + e.getMessage());
	    dbstate=1;
	}finally {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		    @Override
		    public void run() {
		    	CreateReview.this.dbstate=1;
		    }
		}, 3000);
		dbstate=2;
	}

}


//버퍼링상태관리
public void ObserverRelodimg(int dbstate) {

	switch (dbstate) {
	    case 1:
	        System.out.println("Number is 1");
	        break;
	    case 2:
	        System.out.println("Number is 2");
	        break;
	    case 3:
	        System.out.println("Number is 3");
	        break;
	    default:
	        System.out.println("Number is not 1, 2, or 3");
	        break;
	}
	
}



	/**
	 * Create the application.
	 */
	public CreateReview() throws Exception  {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	  private void initialize() throws Exception  {
	        frame = new JFrame("자기소개 라벨 예제");
	        frame.setBounds(0, 0, 420, 680);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        JPanel panel = new JPanel();
	        frame.getContentPane().add(panel, BorderLayout.CENTER);
	        panel.setLayout(null);
	        
	        JButton back = new JButton("");
	        back.setIcon(new ImageIcon(ImgName + "BACK.PNG"));
	        back.setBorder(null);
	        back.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	                d D;
	                try {
	             
	                    d a = new d();
	            		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            		a.setSize(420, 665);
	            		a.setVisible(true);
	            		
	            		
	                    // Hide this frame
	                    frame.setVisible(false);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });
	       
	        back.setBounds(12, 10, 31, 49);
	        panel.add(back);
	        
	        JLabel comments1 = new JLabel("comments");
	        comments1.setBounds(24, 86, 89, 15);
	        panel.add(comments1);
	        
	        JLabel lblNewLabel = new JLabel("starRating");
	        lblNewLabel.setBounds(155, 404, 89, 15);
	        panel.add(lblNewLabel);
	        commentsT = new JTextField();
	        commentsT.setBounds(24, 111, 346, 283);
	        panel.add(commentsT);
	        commentsT.setColumns(10);
	        JButton btnNewButton = new JButton("등록하기");
	        JComboBox starComboBox = new JComboBox(new Integer[] {1, 2, 3, 4, 5});
	        starComboBox.setBounds(35, 429, 320, 60);
	        starComboBox.setSelectedIndex(4); // 초기 선택값은 5점
	        panel.add(starComboBox);
	        
	        btnNewButton.addActionListener(new ActionListener() {
	        	
	        	
	        

				public void actionPerformed(ActionEvent e) {
	        	
	                // 선택한 별점 가져오기
	                int starRating = (int) starComboBox.getSelectedItem();
	                String comments = commentsT.getText();
	        
	               
//	                int proIdx, int memIdx, String comments, int starRating
	        
	               
	                CreateUser(1,2,comments,starRating);
	                
	                
	        	}
	        });
	        
	        btnNewButton.setBounds(111, 514, 191, 60);
	        panel.add(btnNewButton);
	        
	        JLabel lblNewLabel_1 = new JLabel("등록하세요");
	        lblNewLabel_1.setBounds(283, 86, 72, 15);
	        panel.add(lblNewLabel_1);
	        
	  
	     
	      
	    }




	}