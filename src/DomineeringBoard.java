import java.util.EnumSet;
import java.util.Set;

public class DomineeringBoard extends Board<DomineeringMove> {

	// Players for the domineering
	private static final Player H = Player.MAXIMIZER;
	private static final Player V = Player.MINIMIZER;

	// We use the following class fields to represent the board:
	private final EnumSet<DomineeringMove> hMoves;
	private final EnumSet<DomineeringMove> vMoves;

	// A public method to construct the initial board:
	public DomineeringBoard() {
		hMoves = DomineeringMove.noMoves();
		vMoves = DomineeringMove.noMoves();
	}

	// All other constructors should be private (why?):
	private DomineeringBoard(EnumSet<DomineeringMove> hMoves, EnumSet<DomineeringMove> vMoves) {
		assert (disjoint(hMoves, vMoves));
		this.hMoves = hMoves;
		this.vMoves = vMoves;
	}

	//the convention is that h player start the move
	@Override
	Player nextPlayer() {
		return ((hMoves.size() + vMoves.size()) % 4 == 0 ? H : V);
	}

	@Override
	Set<DomineeringMove> availableMoves() {
		return null;
	}

	@Override
	int value() {
		return 0;
	}

	@Override
	Board<DomineeringMove> play(DomineeringMove move) {
		return null;
	}

	// The following short private methods are for readability. They
	// ensure immutability.

	// We promise we won't change the set a (so we clone it):
	static private EnumSet<DomineeringMove> intersection(EnumSet<DomineeringMove> a, EnumSet<DomineeringMove> b) {
		EnumSet<DomineeringMove> c = EnumSet.copyOf(a); // a.clone();
		c.retainAll(b);
		return c;
	}

	static private boolean disjoint(EnumSet<DomineeringMove> a, EnumSet<DomineeringMove> b) {
		return (intersection(a, b).isEmpty());
	}

	static private EnumSet<DomineeringMove> union(EnumSet<DomineeringMove> a, EnumSet<DomineeringMove> b) {
		EnumSet<DomineeringMove> c = EnumSet.copyOf(a); // a.clone();
		c.addAll(b);
		return c;
	}

	static private EnumSet<DomineeringMove> add(EnumSet<DomineeringMove> a, DomineeringMove b) {
		EnumSet<DomineeringMove> c = EnumSet.copyOf(a); // .clone();
		c.add(b);
		return c;
	}

	static private EnumSet<DomineeringMove> complement(EnumSet<DomineeringMove> a) {
		return (EnumSet.complementOf(a));
	}

}
