package Project.Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//
//class Customer implements Observer {
//    private String name;
//    private int targetScore;
//    private String review;
//    private Date date;
//    public Customer(String name, int targetScore,String review,Date date) {
//        this.name = name;
//        this.targetScore = targetScore;
//        this.review = review;
//        this.date = date;
//    }
//
//    public void update(int score) {
//        if (score == targetScore) {
//            System.out.println(name + ": " + score +"  "+ "리뷰"+review);
//        }
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getTargetScore() {
//        return targetScore;
//    }
//
//    public String getReview() {
//        return review;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//}

public class StarMain {
    public static void main(String[] args) {
        // ArrayList에 고객 정보 저장
        ArrayList<StarCustomer> customers = new ArrayList<>();
        customers.add(new StarCustomer("김철수", 5, "별루에요", new Date()));
        customers.add(new StarCustomer("박영희1", 4, "사이즈가작아요", new Date()));
        customers.add(new StarCustomer("박영희2", 4, "좋아요", new Date()));
        customers.add(new StarCustomer("이민수", 3, "이뻐요", new Date()));
  
        // 입력된 별점에 따라 Restaurant 객체의 receiveScore() 메소드 호출
        int score = 3;
        if (score >= 1 && score <= 5) {
        	StarShooseStar GetShooseStar = new StarShooseStar();
       
        	ArrayList<StarCustomer> customers1 = new ArrayList<>();
        	// customers 리스트에 고객 정보 추가 (생략)
            List<StarCustomer> fourStarCustomers = new ArrayList<>();
            for (StarCustomer customer : customers1) {
                if (customer.getTargetScore() == score) {
                    fourStarCustomers.add(customer);
                }
            }
            
            List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
            for (StarCustomer customer : customers) {
                if (customer.getTargetScore() == score) {
                    fourStarCustomers1.add(customer);
                }
            }
            
            
            
            
            
            
            String[][] result = new String[fourStarCustomers1.size()][4];
            for (int i = 0; i < fourStarCustomers1.size(); i++) {
                StarCustomer customer = fourStarCustomers1.get(i);
                result[i][0] = customer.getName();
                result[i][1] = Integer.toString(customer.getTargetScore());
                result[i][2] = customer.getReview();
                result[i][3] = customer.getDate().toString();
            }

            // 배열 출력 점수대score 전부풀력
            for (int i = 0; i < result.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
            
            
            // 2차원 배열에 결과 저장
            String[][] result1 = new String[customers1.size()][3];
            for (int i = 0; i < customers1.size(); i++) {
                StarCustomer c = customers1.get(i);
                result1[i][0] = c.getName();
                result1[i][1] = Integer.toString(c.getTargetScore());
                result1[i][2] = c.getReview();
            }


            for (int i = 0; i < result1.length; i++) {
                System.out.println(result1[i][0] + ": " + result1[i][1] + " " + result1[i][2]);
            }
       
        
        
        
        
        } 
        
        
        
        else {
            System.out.println("잘못된 입력입니다.");
        }
        
        System.out.println(); // 개행
        System.out.println(); // 개행

        // ArrayList를 배열로 변환
        Object[] arr = customers.toArray();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");

        // 첫번째 요소 출력
        StarCustomer c1 = (StarCustomer) arr[0];
        System.out.println(((StarCustomer) arr[0]).getName() + ": " + c1.getTargetScore() + " " + c1.getReview() + " " + dateFormat.format(c1.getDate()));

        // 두번째 요소 출력
        StarCustomer c2 = (StarCustomer) arr[1];
        System.out.println(c2.getName() + ": " + c2.getTargetScore() + " " + c2.getReview() + " " + dateFormat.format(c2.getDate()));

        // 세번째 요소 출력
        StarCustomer c3 = (StarCustomer) arr[2];
        System.out.println(c3.getName() + ": " + c3.getTargetScore() + " " + c3.getReview() + " " + dateFormat.format(c3.getDate()));
    }
}
