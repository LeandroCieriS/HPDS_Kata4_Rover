package refactoring;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static refactoring.Rover.Order.*;

public class Rover {

	private ViewPoint viewPoint;
	public Rover(ViewPoint viewPoint){
		this.viewPoint = viewPoint;
	}

	private final Map<Order, Action> actions = new HashMap<>();
	{
		actions.put(Left, ViewPoint::turnLeft);
		actions.put(Right, ViewPoint::turnRight);
		actions.put(Forward, ViewPoint::forward);
		actions.put(Backward, ViewPoint::backward);
	}

	public ViewPoint viewPoint(){
		return viewPoint;
	}

	public void go(String instructions){
		set(go(Arrays.stream(instructions.split("")).map(Order::of)));
	}

	public void go(Order... orders){
		set(go(Arrays.stream(orders)));
	}

	private void set(ViewPoint viewPoint) {
		if (viewPoint==null) return;
		this.viewPoint = viewPoint;
	}

	public ViewPoint go (Stream<Order> orders){
		return orders.filter(Objects::nonNull)
				.reduce(viewPoint, this::execute, (v1,v2)->null);
	}

	private ViewPoint execute(ViewPoint v, Order o) {
		return v != null ? actions.get(o).execute(v) : null;
	}

	@FunctionalInterface
	public interface Action {
		ViewPoint execute(ViewPoint viewPoint);
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
