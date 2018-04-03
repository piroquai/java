import java.util.Arrays;
import java.util.stream.IntStream;

public class Mean {

    public static void average(int array_length, float array[]) {
        float sum = 0;
        for (float n : array)
            sum += n;
        float srednee = sum / array_length;
        System.out.println("Среднее арифметическое элементов массива: "+ srednee);
    }

    public static void main(String[] args) {
        int array_length = 10;
        float array[] = new float[array_length];
        for (int i = 0; i < array_length; i++)
            array[i] = (float) Math.random();
        System.out.println(Arrays.toString(array));

        average(array_length, array);
    }


}
