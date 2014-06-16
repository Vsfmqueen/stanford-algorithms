import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class TaskLauncher {

    //change LinkedList to ArrayList
    private static HashMap<Integer, ArrayList<Edge>> array;

    private static ArrayList<Edge> initialEdges;
    
    private static int minCutNumber;
    
    private static int iterationCount = 1000;

    public static void main(String[] args) {

        for (int i = 0; i < iterationCount; i++) {
            array = new HashMap<Integer, ArrayList<Edge>>();
            scanFile();
            cutGraph();
        }

        System.out.println("Min cut number: " + minCutNumber);
        System.out.println("Initial edges: " + initialEdges);
    }

    private static void scanFile() {
        File file = new File("c:\\graph_1.txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            int lineNumber = 1;
            while (scanner.hasNext()) {

                String row = scanner.nextLine();
                String[] rowArray = row.split(" ", row.length());
                ArrayList<Edge> integerRow = new ArrayList<Edge>();
                for (int i = 0; i < rowArray.length; i++) {
                    Integer secondVertice = Integer.parseInt(rowArray[i]);
                    String initialEdge = "{" + lineNumber + ", " + secondVertice + "}";
                    Edge edge = new Edge(secondVertice, initialEdge);
                    integerRow.add(edge);
                }
                array.put(lineNumber, integerRow);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        
       // System.out.println("Current array: " + array);
    }

    private static void cutGraph() {
        int edgesCount = array.size();

        while (array.size() > 2) {
            Random rand = new Random();

            int firstVertice = rand.nextInt(edgesCount);

            while (array.get(firstVertice) == null) {
                firstVertice = rand.nextInt(edgesCount);
            }

            ArrayList<Edge> currentRow = array.get(firstVertice);
            
            int secondVerticeIndex = rand.nextInt(currentRow.size() > 1 ? currentRow.size() - 1 : 1);
            int secondVertice = array.get(firstVertice).get(secondVerticeIndex).getSecondVertice(); 
            
      //      System.out.println("First vertice = " + firstVertice + " second vertice = " + secondVertice);
            changeEdge(firstVertice, secondVertice);
        }

        Set<Integer> keySet = array.keySet();
        
        for (Integer key : keySet) {
            int rowSize = array.get(key).size();
            if (minCutNumber == 0 || rowSize < minCutNumber) {
                minCutNumber = rowSize;
                initialEdges = array.get(key);
                break;
            }
        }
        
   //     System.out.println("Min cut number: " + minCutNumber);
        
     
    }

    private static void changeEdge(Integer firstVertice, Integer secondVertice) {

        // add dependencies from 1st vertice to 2nd vertice
        ArrayList<Edge> dependencies = array.get(firstVertice);
        array.get(secondVertice).addAll(dependencies);
       // System.out.println("Added edges: " + array);
        
        //select raws from array by index from 1st vertice dependencies 
        for (Edge edge : dependencies) {
            List<Edge> adjacentedRow = array.get(edge.getSecondVertice());

            // search firstVertice in selected row, change the it to the 2nd one  
           
            for(Edge rowEdge: adjacentedRow) {
                // change the first edge on the second vertice
                if (firstVertice.equals(rowEdge.getSecondVertice())) {
                    rowEdge.setSecondVertice(secondVertice);
                }
            }
        }

        array.remove(firstVertice);
      //  System.out.println("Removed row: " + array);
        removeCircleEdge(array.get(secondVertice), secondVertice);
    }

    private static void removeCircleEdge(List<Edge> verticeRow, Integer secondVertice) {
        Iterator<Edge> verticeRowIterator = verticeRow.iterator();
        while (verticeRowIterator.hasNext()) {
            if (secondVertice.equals(verticeRowIterator.next().getSecondVertice())) {
                verticeRowIterator.remove();
            }
        }
       // System.out.println("Removed circle edges: " + array);
    }
}
