import java.util.Arrays;

public class Array {
    public static void main(String[] args) {
        int numbers[] = new int [10];
        for (int i=0; i<10; i++ )
            numbers[i]= (int) (Math.random()*50);
        System.out.println(Arrays.toString(numbers));
        //Bubble sort
        for (int i=9; i>=0; i--){
            for (int j=0; j<i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int tmp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = tmp;
                }
            }
        }
//        //Insert sort
//
//        for( int i = 1; i < 10; i++) {
//            int j, value;
//            value = numbers[i];
//                for (j = i - 1; j >= 0 && numbers[j] > value; j--) {
//                    numbers[j + 1] = numbers[j];
//                }
//                    numbers[j + 1] = value;
//        }
//        // Select sort
//
//        for (int i = 0; i < 9; i++) {
//            /* устанавливаем начальное значение минимального индекса */
//            int min_i = i;
//            /* находим индекс минимального элемента */
//                for (int j = i + 1; j < 10; j++) {
//                    if (numbers[j] < numbers[min_i]) {
//                        min_i = j;
//                    }
//                }
//            /* меняем значения местами */
//            int temp = numbers[i];
//            numbers[i] = numbers[min_i];
//            numbers[min_i] = temp;
//        }
        System.out.println(Arrays.toString(numbers));
    }
}
