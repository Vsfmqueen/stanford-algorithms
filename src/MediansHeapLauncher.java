import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Vsfmqueen on 30.11.2014.
 */
public class MediansHeapLauncher {
    public static void main(String... args) {
        List<Integer> numbers = fillArrayFromFile();


        List<Integer> medians = new ArrayList<Integer>();

        // the lowest numbers
        PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>(10,
                new MinComporator());

        // the highest numbers
        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>();

        int firstElement = numbers.get(0);
        int secondElement = numbers.get(1);

        if (firstElement < secondElement) {
            minQueue.add(firstElement);
            maxQueue.add(secondElement);
        } else {
            minQueue.add(secondElement);
            maxQueue.add(firstElement);
        }

        int elementIndex1 = findElementIndex(1);

        int median1 = findMedian(minQueue, maxQueue, elementIndex1);

        medians.add(median1);

        int elementIndex2 = findElementIndex(2);
        int median2 = findMedian(minQueue, maxQueue, elementIndex2);
        medians.add(median2);

        for (int i = 2; i < numbers.size(); i++) {
            Integer nextElement = numbers.get(i);
            Integer minRoot = minQueue.peek();

            if (nextElement < minRoot) {
                minQueue.add(nextElement);
            } else {
                maxQueue.add(nextElement);
            }

            System.out.println("--------------------------------");

            System.out.println("Before rebalance = ");
            System.out.println("Min Queue = " + minQueue);
            System.out.println("Max Queue = " + maxQueue);

            rebalanceHeaps(minQueue, maxQueue);
            System.out.println("After rebalance = ");
            System.out.println("Min Queue = " + minQueue);
            System.out.println("Max Queue = " + maxQueue);

            int elementIndex = findElementIndex(i + 1);
            int median = findMedian(minQueue, maxQueue, elementIndex);

            medians.add(median);

        }

        System.out.println("Medians = " + medians);

        int sum = 0;

        for (Integer med : medians) {
            sum += med;
        }

        System.out.println(sum);

    }

    private static void rebalanceHeaps(PriorityQueue<Integer> minQueue,
                                       PriorityQueue<Integer> maxQueue) {
        if (maxQueue.size() > minQueue.size()) {
            Integer root = maxQueue.poll();
            minQueue.add(root);
        } else if (minQueue.size() > maxQueue.size()) {
            Integer root = minQueue.poll();
            maxQueue.add(root);
        }
    }

    private static int findElementIndex(int i) {
        int k = 0;

        if (i % 2 == 0) { // even
            k = i / 2;
        } else { // odd
            k = (i + 1) / 2;
        }

        System.out.println("Current i = " + i + "   Current k = " + k);
        return k;
    }

    private static int findMedian(PriorityQueue<Integer> minQueue,
                                  PriorityQueue<Integer> maxQueue, int elementIndex) {
        int median = 0;

        if (elementIndex > minQueue.size()) {
            median = maxQueue.peek();
        } else {
            median = minQueue.peek();
        }

        return median;
    }

    private static List<Integer> fillArrayFromFile() {
        List<Integer> numbers = new ArrayList<Integer>();
        Scanner scanner = null;
        File file = new File("c:\\test.txt");

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                numbers.add(Integer.parseInt(row));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return numbers;
    }
}


class MinComporator implements Comparator<Integer> {

    @Override
    public int compare(Integer n1, Integer n2) {
        if (n1 < n2) {
            return 1;
        } else if (n1 > n2) {
            return -1;
        }
        return 0;
    }

}


