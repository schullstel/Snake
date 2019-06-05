package utilities;

import java.util.ArrayList;

public class Generator {
    public static ArrayList<Integer> generate_int_arraylist(long size) {
        final int START_FROM_NUMBER = 0;
        ArrayList<Integer> vec = new ArrayList<Integer>();
        for(int i = START_FROM_NUMBER; i < size; i++) {
            vec.add(i);
        }
        return vec;
    }

    public static void generate_random_arraylist(ArrayList<Double> vec,
                                                 long size) {

    }

    public static int generate_random_int(int min, int max) {
        double random_int = (int)(Math.random()*((max - min) + 1)) + min;
        return (int)random_int;
    }
}
