package project.bean;
import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.List;

public class Factory_main {

	 public static void main(String[] args) {
	        List<FactoryItemFromMenu> items = new ArrayList<>();
	        items.add(FactoryItemFactory.createItem(1,"이름1", 9, "댓글"));
	        items.add(FactoryItemFactory.createItem(2,"", 0, "댓글2"));
	        items.add(FactoryItemFactory.createItem(3,"", 0, "photo3"));
	        FactoryItemFromMenu firstItem = items.get(0);
	        int num = firstItem.getNum();
	        String name = firstItem.getName();
	        double price = firstItem.getPrice();
	        String photo = firstItem.getPhoto();
	        
	        
	        FactoryItemPrinter.printAllitems(items);
	       
	        System.out.println(num+name +price+photo);
	    }

}
	