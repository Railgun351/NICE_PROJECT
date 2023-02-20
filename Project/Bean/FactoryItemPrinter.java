package Project.Bean;
import java.util.List;

public class FactoryItemPrinter {
    public static void printAllitems(List<FactoryItemFromMenu> Items) {
        for (FactoryItemFromMenu Item : Items) {
            System.out.println(Item.getNum()+"Name: " + Item.getName() + ", Price: " + Item.getPrice() + ", Photo: " + Item.getPhoto());
        }
    }
}