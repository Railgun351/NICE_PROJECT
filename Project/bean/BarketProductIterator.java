package project.bean;

import java.util.Iterator;

public class BarketProductIterator implements Iterable<BarketProduct>, Iterator<BarketProduct> {
    private BarketProduct[] products;
    private int index = 0;

    public BarketProductIterator(BarketProduct[] products) {
        this.products = products;
    }

    @Override
    public Iterator<BarketProduct> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return index < products.length;
    }

    @Override
    public BarketProduct next() {
        return products[index++];
    }


  
}