
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class CollectionWorkout {
	private static int eum;

	public static void main(String... args) {
		 
		System.out.println("Hi");

		Queue<String> strPBQ = new PriorityBlockingQueue<String>();

		Queue<String> strPQ = new PriorityQueue<String>();

		strPBQ.offer("Str4");
		strPBQ.offer("Str9");
		strPBQ.offer("Str11");

		String[] srtingAr = { "d", "D", "f" };
		List<String> arStr = Arrays.asList(srtingAr);
		System.out.println(arStr);
		strPBQ.addAll(arStr);
		strPQ.add("STR!!");
		strPQ.add("STR!!!");
		Arrays.sort(strPBQ.toArray());
		System.out.println(strPBQ.peek());
		while (!strPBQ.isEmpty()) {
			System.out.println(strPBQ.poll());
			System.out.println(strPBQ.size());
		}
		while (!strPQ.isEmpty()) {
			System.out.println(strPQ.poll());
			System.out.println(strPQ.size());
		}
	}

}
