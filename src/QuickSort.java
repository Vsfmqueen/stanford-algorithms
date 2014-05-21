import java.util.Arrays;

public class QuickSort {

    private static int comparasionCount = 0;
    
    private static Integer[] array = {3, 9, 8, 4, 6, 10, 2, 5, 7, 1};

    public static void main(String... args) {

        sortArray(array, 0, array.length - 1);
        
        System.out.println(comparasionCount);
    }

    private static void sortArray(Integer[] array, int start, int end) {

        boolean middlePivot = true;
        
        if (end <= start) {
            return;
        }

      //  int middle = calculateMiddleFirstCase(array, start, end);
      //  int middle = calculateMiddleSecondCase(array, start, end);
        int middle = partition(array, start, end, true);
      
        sortArray(array, start, middle - 1);
        sortArray(array, middle + 1, end);
    }

    private static int calculateMiddleFirstCase(Integer[] array, int start, int end) {
        boolean middlePivot = false;
        return partition(array, start, end, middlePivot);
    }
    
    private static int calculateMiddleSecondCase(Integer[] array, int start, int end) {
        boolean middlePivot = false;
        exchange(array, start, end);
        return partition(array, start, end, middlePivot);
    }
    
    private static int partition(Integer[] array, int start, int end, boolean middlePivot) {

        int arrayLength = end - start;
        comparasionCount += arrayLength;

        int i = start + 1;
        int j = start + 1;
        int pivotElement;
        
        if (middlePivot) {
            pivotElement = detectPivotElement(array, start, end);
        } else {
            pivotElement = array[start];
        }

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
            pivotElement = findSmallerElement(middleElement, startElement);
        } else {
            pivotElement = findSmallerElement(middleElement, endElement);
        }

        return pivotElement;
    }
    
    private static int findSmallerElement(int firstElement, int endElement) {
        if (firstElement > endElement) {
            return firstElement;
        } else {
            return endElement;
        }
    }
}
