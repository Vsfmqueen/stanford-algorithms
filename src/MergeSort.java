import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MergeSort {
	
	private static Long[] aux = new Long[100000];
	
	private static ArrayList<Long> couters = new ArrayList<Long>();
	
	private static Long count=0L;
	
	public static void main(String... args) {
		
		Long[] array = new Long[100000];
		
		File file = new File("c:\\test_1000.txt");
		try {
			Scanner scanner = new Scanner(file);
			int i = 0;
			while (scanner.hasNext()) {
				Long number = scanner.nextLong();
				array[i] = number;
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		System.out.println(couters);
		//System.out.println(Arrays.deepToString(array));
		
		MergeSort sort = new MergeSort();
		sort.sort(array, 0, array.length - 1);
		sort.sort(array, 0, array.length-1);
	//	System.out.println(Arrays.deepToString(array));
		System.out.println(count);
	}

	public void sort(Long[] array, int start, int end) {	
		if (end <= start) {
			return;
		}
		
		int newMid = start + (end-start )/ 2;
		
	//	System.out.println("Mid: " + newMid);
		sort(array, start, newMid);
		sort(array, newMid+1, end);
		merge(array, start, newMid, end);
	}
	
	public void merge(Long[] a, int lo, int mid, int hi) { 
		int i = lo, j = mid + 1;
		
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++)
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (aux[j] < aux[i]) {
				countInversions(mid+1, i);//обработать для массивов с 2-мя элементами
				a[k] = aux[j++];
			}
			else
				a[k] = aux[i++];
	}
	
	private void countInversions(int arrayLength, int currentIndex){
		int inversionsCount = arrayLength-currentIndex;
		if (count <= Long.MAX_VALUE) {
			count += inversionsCount;
		} else {
			couters.add(count);
			count = 0L;
		}
		
	}
	
}
