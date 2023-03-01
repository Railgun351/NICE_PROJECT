package project.bean;
public class BarketArrayGenerator {
    private int[] array;

    public BarketArrayGenerator(int length) {
        array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = length - i;
        }
    }

    public int[] getArray() {
        return array;
    }
}
