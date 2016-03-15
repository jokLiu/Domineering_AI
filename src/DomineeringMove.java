import java.util.HashSet;
import java.util.Set;

public class DomineeringMove extends Object {

	private final int first, second;

	public DomineeringMove(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public String toString() {
		return (first + "," + second);
	}

	@Override
	public int hashCode() {
		
		return first+second;
	}

	@Override
	public boolean equals(Object move) {
		if (move == null || move.getClass() != this.getClass()) {
			return false;
		}
		DomineeringMove m = (DomineeringMove) move;

		return ((first == m.getFirst() && second == m.getSecond()));

	}
	
	public boolean isTouching(DomineeringMove move)
	{
		return false;
	}

}
