package Project.Bean;

import java.util.Date;

public class StarCustomer implements StarObserver {
    private String name;
    private int targetScore;
    private String review;
    private Date date;
    public StarCustomer(String name, int targetScore,String review,Date date) {
        this.name = name;
        this.targetScore = targetScore;
        this.review = review;
        this.date = date;
    }
    public void update(int score) {
        if (score == targetScore) {
            System.out.println(name + ": " + score +"  "+ "리뷰"+review);
        }
    }

    public String getName() {
        return name;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public String getReview() {
        return review;
    }

    public Date getDate() {
        return date;
    }
}
