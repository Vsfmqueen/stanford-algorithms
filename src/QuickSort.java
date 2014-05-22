import java.util.Arrays;

public class QuickSort {

    private static int comparasionCount = 0;
    
    private static Integer[] array = {3, 9, 8, 4, 6, 10, 2, 5, 7, 1};

    public static void main(String... args) {

        sortArray(array, 0, array.length - 1);
        
        System.out.println(comparasionCount);
    }

    private static void sortArray(Integer[] array, int start, int end) {

        if (end <= start) {
            return;
        }

      //  int middle = calculateFirstCase(array, start, end);
      //  int middle = calculateSecondCase(array, start, end);
        
        int pivotElement = detectPivotElement(array, start, end);
        int pivotIndex = Arrays.asList(array).indexOf(pivotElement);
        
         exchange(array, pivotIndex, start);
        int middle = partition(array, start, end);
        
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
        System.out.println(Arrays.deepToString(array));
        return i - 1;
    }

    private static void exchange(Integer[] array, int firstIndex, int secondIndex) {
        int a = 0;

        a = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = a;
    }
    
    private static int detectPivotElement(Integer[] array, int start, int end) {
        int middle = (end - start) / 2;

        int startElement = array[start];
        int endElement = array[end];
        int middleElement = array[middle];

        int pivotElement;

        if (startElement < endElement) {
            pivotElement = findMiddle(startElement, middleElement, endElement);
        } else {
            pivotElement = findMiddle(endElement, middleElement, startElement);
        }

        System.out.println("Start = " + startElement + " Middle = " + middleElement + " End = " + endElement
                + " Pivot = " + pivotElement);

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
