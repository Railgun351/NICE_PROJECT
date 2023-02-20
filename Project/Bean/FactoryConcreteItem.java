package Project.Bean;
public class FactoryConcreteItem implements FactoryItemFromMenu {
	 private int num;
    private String name;
    private int price;
    private String photo;



    public FactoryConcreteItem(int num,String name, int price, String photo) {
//        if (photo == null || price == 0  ||name==null ) {
//            throw new NullPointerException("제품 사진은 null 일 수 없습니다.");
//        }
    	  this.num = num;
        this.name = name;
        this.price = price;
        this.photo = photo;
    }
    public FactoryConcreteItem(String photo) {
    	 this.num = 0;
      this.name = null;
      this.price = 0;
      this.photo = photo;
  }
    @Override
    public int getNum() {
        return num;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getPhoto() {
        return photo;
    }
}