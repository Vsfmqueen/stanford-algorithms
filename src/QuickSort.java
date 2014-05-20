import java.util.Arrays;

public class QuickSort {

	private static Integer[] array = { 3, 8, 2, 5, 1, 4, 7, 6 };

	public static void main(String... args) {

		divideArray(array, 0, array.length);
	}

	private static void divideArray(Integer[] array, int start, int end) {
		int middle = start + (end - start) / 2;

		if (end <= start) {
			return;
		}

		
		divideArray(array, start, middle);
		divideArray(array, middle + 1, end);
		
		System.out.println("Start = " + start + " End = " + end);
		
		sort(array, array[start], start);
	}

	private static void sort(Integer[] array, int pivotElement, int pivotIndex) {
		int i = pivotIndex++;
		int j = pivotIndex++;

		int a = 0;

		while (j < array.length) {
			if (array[j] < pivotElement) {
				a = array[i];
				array[i] = array[j];
				array[j] = a;
				i++;
			}
			j++;
		}
		System.out.println(Arrays.deepToString(array));
	}
}
