import java.util.PriorityQueue;

/**
 * Created by Vera_Sidarovich on 7/11/2014.
 */
public class Test {

    public static void main(String... args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        queue.add(4);
        queue.add(3);
        queue.add(7);
        queue.add(2);
        queue.add(6);
        System.out.print(queue);
    }
}
