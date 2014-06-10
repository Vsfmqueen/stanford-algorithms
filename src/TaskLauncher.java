import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class TaskLauncher {

    private static HashMap<Integer, ArrayList<Integer>> array = new HashMap<Integer, ArrayList<Integer>>();

    private static Integer minCutNumber;

    public static void main(String[] args) {
        scanFile();
        cutGraph();
    }

    private static void scanFile() {
        File file = new File("c:\\graph.txt");

        Scanner scanner;
        try {
            scanner = new Scanner(file);
            int lineNumber = 0;
            while (scanner.hasNext()) {

                String row = scanner.nextLine();
                String[] rowArray = row.split(" ", row.length());
                ArrayList<Integer> integerRow = new ArrayList<Integer>();
                for (int i = 0; i < rowArray.length; i++) {
                    integerRow.add(Integer.parseInt(rowArray[i]));
                }
                array.put(lineNumber, integerRow);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void cutGraph() {
        int edgesCount = array.size();

        while (array.size() > 2) {
            Random rand = new Random();

            int firstVertice = rand.nextInt(edgesCount);
            int secondVertice = array.get(firstVertice).get(0); // !

            changeEdge(firstVertice, secondVertice);
        }
        
        if (minCutNumber > 0 && array.get(0).size() < minCutNumber) {
            minCutNumber = array.get(0).size();
        }
    }

    private static void changeEdge(Integer firstVertice, Integer secondVertice) {

        // dependencies of the row by firstVertice
        ArrayList<Integer> dependencies = array.get(firstVertice);

        array.get(secondVertice).addAll(dependencies);

        for (Integer edge : dependencies) {
            ArrayList<Integer> adjacentedRow = array.get(edge);

            // check every row in
            for (int j = 0; j < adjacentedRow.size(); j++) {
                Integer rowEdge = adjacentedRow.get(j);

                // change the first edge on the second
                if (firstVertice.equals(rowEdge)) {
                    adjacentedRow.remove(j);
                    adjacentedRow.add(j, secondVertice);
                }
            }
        }
        
        array.remove(firstVertice);

    }
}
