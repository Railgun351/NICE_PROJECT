package Project.Bean;

import java.util.ArrayList;

public class StarShooseStar {
	   private ArrayList<StarObserver> observers = new ArrayList<>();

	    public void addObserver(StarObserver observer) {
	        observers.add(observer);
	    }

	    public void removeObserver(StarObserver observer) {
	        observers.remove(observer);
	    }

	    public void notifyObservers(int score) {
	        for (StarObserver observer : observers) {
	            observer.update(score);
	        }
	    }

	    public void receiveScore(int score) {
	        System.out.println("별점:" + score+"정보");
	        notifyObservers(score);
	    }
}
