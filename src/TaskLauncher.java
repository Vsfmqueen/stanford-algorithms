import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class TaskLauncher {

    private static HashMap<Integer, LinkedList<Integer>> array = new HashMap<Integer, LinkedList<Integer>>();

    private static int minCutNumber;

    public static void main(String[] args) {
        scanFile();
        cutGraph();
    }

    private static void scanFile() {
        File file = new File("c:\\graph.txt");

        Scanner scanner;
        try {
            scanner = new Scanner(file);
            int lineNumber = 1;
            while (scanner.hasNext()) {

                String row = scanner.nextLine();
                String[] rowArray = row.split(" ", row.length());
                LinkedList<Integer> integerRow = new LinkedList<Integer>();
                for (int i = 0; i < rowArray.length; i++) {
                    integerRow.add(Integer.parseInt(rowArray[i]));
                }
                array.put(lineNumber, integerRow);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println("Current array: " + array);
    }

    private static void cutGraph() {
        int edgesCount = array.size();

        while (array.size() > 2) {
            Random rand = new Random();

            int firstVertice = rand.nextInt(edgesCount);

            while (array.get(firstVertice) == null) {
                firstVertice = rand.nextInt(edgesCount);
            }

            LinkedList<Integer> currentRow = array.get(firstVertice);
            
            int secondVerticeIndex = rand.nextInt(currentRow.size() - 1);
            
            int secondVertice = array.get(firstVertice).get(secondVerticeIndex); // !
            System.out.println("First vertice = " + firstVertice + " second vertice = " + secondVertice);
            changeEdge(firstVertice, secondVertice);
        }

        Set<Integer> keySet = array.keySet();
        
        for (Integer key : keySet) {
            int rowSize = array.get(key).size();
            if (minCutNumber == 0 || rowSize < minCutNumber) {
                minCutNumber = rowSize;
                break;
            }
        }
        
        System.out.println("Min cut number: " + minCutNumber);
    }

    private static void changeEdge(Integer firstVertice, Integer secondVertice) {

        // dependencies of the row by firstVertice
        LinkedList<Integer> dependencies = array.get(firstVertice);

        array.get(secondVertice).addAll(dependencies);

        System.out.println("Added edges: " + array);
        
        for (Integer edge : dependencies) {
            List<Integer> adjacentedRow = array.get(edge);
            
            ListIterator<Integer> adjacentIterator = (ListIterator<Integer>) adjacentedRow.iterator();

            // check every row in
            while (adjacentIterator.hasNext()) {
                Integer rowEdge = adjacentIterator.next();

                // change the first edge on the second vertice
                if (firstVertice.equals(rowEdge)) {
                    adjacentIterator.remove();
                    adjacentIterator.add(secondVertice);
                }
            }
        }

        array.remove(firstVertice);
        System.out.println("Removed row: " + array);
        removeCircleEdge(array.get(secondVertice), secondVertice);
    }

    private static void removeCircleEdge(List<Integer> verticeRow, Integer secondVertice) {
        Iterator<Integer> verticeRowIterator = verticeRow.iterator();
        while (verticeRowIterator.hasNext()) {
            if (secondVertice.equals(verticeRowIterator.next())) {
                verticeRowIterator.remove();
            }
        }
        System.out.println("Removed circle edges: " + array);
    }
}
