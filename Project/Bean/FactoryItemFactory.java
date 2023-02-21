package project.bean;

public class FactoryItemFactory {
	

	public static FactoryItemFromMenu createItem(int num,String name, int price, String photo) {
		// TODO Auto-generated method stub
		   return new FactoryConcreteItem(num,name, price, photo);
	}
}
