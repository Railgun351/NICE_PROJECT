package Project.Bean;

import java.lang.reflect.Array;

public class BarketMain {

    public static void main(String[] args) {
    	
    	        BarketProduct[] products = new BarketProduct[]{
    	                new BarketProduct("책", "book.jpg", 10, 10000),
    	                new BarketProduct("의자", "chair.jpg", 20, 20000),
    	                new BarketProduct("테이블", "table.jpg", 5, 50000)
    	        };

    	        BarketProduct[] productArray = new BarketProduct[3];	
    	        int i = 0;
    	        for (BarketProduct product : new BarketProductIterator(products)) {
    	            productArray[i++] = product;
    	        }
    	        for (BarketProduct product : productArray) {
    	            System.out.println(product.getName().charAt(0));
    	        }
    	        // Product 객체 배열 출력
    	        for (BarketProduct product : productArray) {
    	            System.out.println(product.getName());
    	        }
    	   }}
