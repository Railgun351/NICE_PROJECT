package project.bean;
//package project.bean;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
////import db.StarDto;
////import db.StarService;
//
////
////class Customer implements Observer {
////    private String name;
////    private int targetScore;
////    private String review;
////    private Date date;
////    public Customer(String name, int targetScore,String review,Date date) {
////        this.name = name;
////        this.targetScore = targetScore;
////        this.review = review;
////        this.date = date;
////    }
////
////    public void update(int score) {
////        if (score == targetScore) {
////            System.out.println(name + ": " + score +"  "+ "리뷰"+review);
////        }
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public int getTargetScore() {
////        return targetScore;
////    }
////
////    public String getReview() {
////        return review;
////    }
////
////    public Date getDate() {
////        return date;
////    }
////}
//
//public class StarMain {
//    public static void main(String[] args) throws Exception {
//        // ArrayList에 고객 정보 저장
//        ArrayList<StarCustomer> customers = new ArrayList<>();
//        
//        
//        StarService empService = StarService.getInstance();
//        List<StarDto> empDtoList = empService.getStarDtoList();
//
//        Object[] arr = new Object[empDtoList.size()];
//        int i = 0;
//        
//        for (StarDto empDto : empDtoList) {
//        customers.add(new StarCustomer( empDto.getMEM_IDX(),empDto.getSTAR_RATING(), empDto.getCOMMENTS(), empDto.getCOM_DATE()));
//        }
////        customers.add(new StarCustomer("박영희1", 4, "사이즈가작아요", new Date()));
////        customers.add(new StarCustomer("박영희2", 4, "좋아요", new Date()));
////        customers.add(new StarCustomer("이민수", 3, "이뻐요", new Date()));
////  
//        
//      
//
//
//        // 입력된 별점에 따라 Restaurant 객체의 receiveScore() 메소드 호출
//        int score = 3;
//        if (score >= 1 && score <= 5) {
//        	StarShooseStar GetShooseStar = new StarShooseStar();
//       
//        	ArrayList<StarCustomer> customers1 = new ArrayList<>();
//        	// customers 리스트에 고객 정보 추가 (생략)
//            List<StarCustomer> fourStarCustomers = new ArrayList<>();
//            for (StarCustomer customer : customers1) {
//                if (customer.getTargetScore() == score) {
//                    fourStarCustomers.add(customer);
//                }
//            }
//            
//            List<StarCustomer> fourStarCustomers1 = new ArrayList<>();
//            for (StarCustomer customer : customers) {
//                if (customer.getTargetScore() == score) {
//                    fourStarCustomers1.add(customer);
//                }
//            }
//            
//            
//            
//            
//            
//            
//            String[][] result = new String[fourStarCustomers1.size()][4];
//            for (int i1 = 0; i1 < fourStarCustomers1.size(); i1++) {
//                StarCustomer customer = fourStarCustomers1.get(i1);
//                result[i1][0] = Integer.toString(customer.getMembername());
//                result[i1][1] = Integer.toString(customer.getTargetScore());
//                result[i1][2] = customer.getReview();
//                result[i1][3] = customer.getDate().toString();
//            }
//
//            // 배열 출력 점수대score 전부풀력
//            for (int i1 = 0; i1 < result.length; i1++) {
//                System.out.println(Arrays.toString(result[i1]));
//            }
//            
//            
//            // 2차원 배열에 결과 저장
//            String[][] result1 = new String[customers1.size()][3];
//            for (int i1 = 0; i1 < customers1.size(); i1++) {
//                StarCustomer c = customers1.get(i1);
//                result1[i1][0] =  Integer.toString(c.getMembername());
//                result1[i1][1] = Integer.toString(c.getTargetScore());
//                result1[i1][2] = c.getReview();
//            }
//
//
//            for (int i1 = 0; i1 < result1.length; i1++) {
//                System.out.println(result1[i1][0] + ": " + result1[i1][1] + " " + result1[i1][2]);
//            }
//       
//        
//        
//        
//        
//        } 
//        
//        
//        
//        else {
//            System.out.println("잘못된 입력입니다.");
//        }
//        
//        System.out.println(); // 개행
//        System.out.println(); // 개행
//
//        // ArrayList를 배열로 변환
//        Object[] arr1 = customers.toArray();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년MM월dd일");
//
//        // 첫번째 요소 출력
//        StarCustomer c1 = (StarCustomer) arr1[0];
//        System.out.println(((StarCustomer) arr1[0]).getMembername() + ": " + c1.getTargetScore() + " " + c1.getReview() + " " + dateFormat.format(c1.getDate()));
//
//        // 두번째 요소 출력
//        StarCustomer c2 = (StarCustomer) arr1[1];
//        System.out.println(c2.getMembername() + ": " + c2.getTargetScore() + " " + c2.getReview() + " " + dateFormat.format(c2.getDate()));
//
//        // 세번째 요소 출력
//        StarCustomer c3 = (StarCustomer) arr1[2];
//        System.out.println(c3.getMembername() + ": " + c3.getTargetScore() + " " + c3.getReview() + " " + dateFormat.format(c3.getDate()));
//    }
//}
