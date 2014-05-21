import java.util.Arrays;

public class QuickSort {

    private static Integer[] array = { 3, 8, 2, 5, 1, 4, 7, 6 };

    public static void main(String... args) {

        sortArray(array, 0, array.length - 1);
    }

    private static void sortArray(Integer[] array, int start, int end) {

        if (end <= start) {
            return;
        }

        int middle = partition(array, start, end);

        sortArray(array, start, middle - 1);
        sortArray(array, middle + 1, end);
    }

    private static int partition(Integer[] array, int start, int end) {
        int i = start + 1;
        int j = start + 1;

        while (j <= end) {
            if (array[j] < array[start]) {
                exchange(array, i, j);
                i++;
            }
            j++;
        }

        exchange(array, start, i - 1);
        System.out.println(Arrays.deepToString(array));
        return i - 1;
    }

    private static void exchange(Integer[] array, int firstIndex, int secondIndex) {
        int a = 0;

        a = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = a;
    }
}
