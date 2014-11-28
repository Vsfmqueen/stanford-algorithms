import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Created by Vera_Sidarovich on 11/28/2014.
 */
public class TaskLauncher {
    public static void main(String... args) {
        ArrayList<Long> numbers = new ArrayList<Long>(fillArrayFromFile());

        int numbersSize = numbers.size();
        int i = 0;
        int j = numbersSize;

        while (i < numbersSize && j > 0 && i != j) {

        }

    }

    private static TreeSet<Long> fillArrayFromFile() {
        TreeSet<Long> numbers = new TreeSet<Long>();
        Scanner scanner = null;
        File file = new File("c:\\test.txt");

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
