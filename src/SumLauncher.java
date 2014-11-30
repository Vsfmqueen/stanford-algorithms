import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Vera_Sidarovich on 11/28/2014.
 */
public class SumLauncher {

    public static void main(String... args) {
        ArrayList<Long> numbers = new ArrayList<Long>(fillArrayFromFile());
        TreeSet<Long> sums = new TreeSet<Long>();

        int upperBound = 10000;
        int lowerBound = -10000;

        int numbersSize = numbers.size();
        int i = 0;
        int j = numbersSize - 1;

        int lastJ = 0;

        while (i < j) {
            long sum = numbers.get(i) + numbers.get(j);

            if (sum >= lowerBound && sum <= upperBound) {
                sums.add(sum);
            }

            if (sum <= upperBound && lastJ == 0) {
                lastJ = j;
            }

            if (sum < lowerBound) {
                j = lastJ;
                lastJ = 0;
                i++;
                continue;
            }
            j--;
        }

        System.out.print(sums.size());
    }

    private static TreeSet<Long> fillArrayFromFile() {
        TreeSet<Long> numbers = new TreeSet<Long>();
        Scanner scanner = null;
        File file = new File("c:\\algo1-programming_prob-2sum.txt");

        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                numbers.add(Long.parseLong(row));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        return numbers;
    }


}