package Project.Bean;

public class BarketProduct {
	private int Pdindex;
    private String name;
    private String image;
    private int quantity;
    private int price;

    public BarketProduct(String name, String image, int quantity, int price) {
    
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " " + image + " " + quantity + " " + price;
    }
    
  
    
}
