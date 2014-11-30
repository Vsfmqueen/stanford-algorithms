import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Vsfmqueen on 30.11.2014.
 */
public class MedianSimpleLauncher {
    public static void main(String... args) {
        List<Integer> numbers = fillArrayFromFile();
        List<Integer> medians = new ArrayList<Integer>();


        for (int i = 1; i <= numbers.size(); i++) {
            int k = findElementIndex(i);
            List<Integer> subList = numbers.subList(0, i);
            Collections.sort(subList);

            int median = subList.get(k-1);
            medians.add(median);

        }

        System.out.println(medians);

        int sum = 0;

        for(Integer med: medians){
            sum+=med;
        }

        System.out.println(sum);

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
