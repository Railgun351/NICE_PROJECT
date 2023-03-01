package project.bean;

import java.sql.Timestamp;
import java.util.Date;

public class StarCustomer implements StarObserver {

    private int Membername;
    private int targetScore;
    private String review;
    private Timestamp date;
    public StarCustomer(int Membername, int targetScore,String review,Timestamp date) {
        this.Membername = Membername;
        this.targetScore = targetScore;
        this.review = review;
        this.date = date;
    }



	public void update(int score) {
        if (score == targetScore) {
            System.out.println(Membername + ": " + score +"  "+ "리뷰"+review);
        }
    }
  
    public int getMembername() {
        return Membername;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public String getReview() {
        return review;
    }

    public Timestamp getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Membername: " + Membername + ", TargetScore: " + targetScore + ", Review: " + review + ", Date: " + date;
    }



	}




