package refactoring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static refactoring.Rover.Order.*;

public class Rover {

	private ViewPoint viewPoint;
	public Rover(ViewPoint viewPoint){
		this.viewPoint = viewPoint;
	}

	private final Map<Order, Action> actions = new HashMap<>();
	{
		actions.put(Left, () -> viewPoint = viewPoint.turnLeft());
		actions.put(Right, () -> viewPoint = viewPoint.turnRight());
		actions.put(Forward, () -> viewPoint = viewPoint.forward());
		actions.put(Backward, () -> viewPoint = viewPoint.backward());
	}

	public ViewPoint viewPoint(){
		return viewPoint;
	}

	public void go(String instructions){
		String[] instructionsList = instructions.split("");
		Arrays.stream(instructionsList)
				.filter(Order::isValidOrder)
				.forEach(c -> actions.get(Order.of(c)).execute());
	}

	public void go(Order... orders){
		Arrays.stream(orders).forEach(order -> actions.get(order).execute());
	}

	public interface Action {
		void execute();
	}

	public enum Order {
		Forward, Backward, Left, Right;
		static Map<Character, Order> orders = new HashMap<>();

		static {
			orders.put('R', Right);
			orders.put('L', Left);
			orders.put('F', Forward);
			orders.put('B', Backward);
		}

		public static Order of(String label) {
			return orders.get(label.charAt(0));
		}

		public static boolean isValidOrder (String label) {
			return orders.containsKey(label.charAt(0));
		}
	}
}
