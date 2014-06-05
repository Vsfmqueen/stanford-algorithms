import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    private static int comparasionCount = 0;

    private static Integer[] array = new Integer[10000];

    public static void main(String... args) {

        Scanner scanner = null;
        File file = new File("c:\\numbers.txt");
        try {
            scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNext()) {
                Integer number = scanner.nextInt();
                array[i] = number;
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        sortArray(array, 0, array.length - 1);

        System.out.println(comparasionCount);
    }

    private static void sortArray(Integer[] array, int start, int end) {

        if (end <= start) {
            return;
        }

        int middle = calculateFirstCase(array, start, end);
        // int middle = calculateSecondCase(array, start, end);
        // int middle = calculateThirdCase(array, start, end);

        sortArray(array, start, middle - 1);
        sortArray(array, middle + 1, end);
    }

    private static int calculateFirstCase(Integer[] array, int start, int end) {
        return partition(array, start, end);
    }

    private static int calculateSecondCase(Integer[] array, int start, int end) {
        exchange(array, start, end);
        return partition(array, start, end);
    }

    private static int calculateThirdCase(Integer[] array, int start, int end) {
        int pivotElement = detectPivotElement(array, start, end);
        int pivotIndex = Arrays.asList(array).indexOf(pivotElement);

        if (start != pivotIndex) {
            exchange(array, pivotIndex, start);
        }

        return partition(array, start, end);
    }

    private static int partition(Integer[] array, int start, int end) {

        int arrayLength = end - start;
        comparasionCount += arrayLength;

        int i = start + 1;
        int j = start + 1;
        int pivotElement = array[start];

        while (j <= end) {
            if (array[j] < pivotElement) {
                exchange(array, i, j);
                i++;
            }
            j++;
        }

        exchange(array, start, i - 1);
        return i - 1;
    }

    private static void exchange(Integer[] array, int firstIndex, int secondIndex) {
        int a = 0;

        a = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = a;
    }

    private static int detectPivotElement(Integer[] array, int start, int end) {

        int startElement = array[start];
        int endElement = array[end];

        int middle = start + (end - start) / 2;
        int middleElement = array[middle];

        int pivotElement;

        if (startElement < endElement) {
            pivotElement = findMiddle(startElement, middleElement, endElement);
        } else {
            pivotElement = findMiddle(endElement, middleElement, startElement);
        }

        return pivotElement;
    }

    private static int findMiddle(int start, int mid, int end) {
        if (mid <= start) {
            return start;
        }
        if (mid >= start && mid <= end) {
            return mid;
        }
        return end;
    }
}
