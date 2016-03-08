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

	// the convention is that h player start the move
	@Override
	Player nextPlayer() {
		return ((hMoves.size() + vMoves.size()) % 4 == 0 ? H : V);
	}

	// available moves (dots)
	@Override
	Set<DomineeringMove> availableMoves() {
		return (value() == 0 ? complement(union(hMoves, vMoves)) : DomineeringMove.noMoves());
	}

	@Override
	int value() {
		return (DomineeringMove.winsH(union(vMoves, hMoves)) ? 1
				: DomineeringMove.winsV(union(vMoves, hMoves)) ? -1 : 0);
	}

	@Override
	Board<DomineeringMove> play(DomineeringMove move) {
		assert (!hMoves.contains(move) && !vMoves.contains(move));

		if (nextPlayer() == H)
			return new DomineeringBoard(add(hMoves, move), vMoves);
		else
			return new DomineeringBoard(hMoves, add(vMoves, move));

	}

	// The following short private methods are for readability. They
	// ensure immutability.

	// A simple conversion to string for testing:
	public String toString() {
		return (pm(DomineeringMove.A0) + " | " + pm(DomineeringMove.A1) + " | " + pm(DomineeringMove.A2)
				+ pm(DomineeringMove.A3) + "\n" + "---+----+---+---\n" + pm(DomineeringMove.B0) + " | "
				+ pm(DomineeringMove.B1) + " | " + pm(DomineeringMove.B2) + pm(DomineeringMove.B3) + "\n"
				+ "---+----+---+---\n" + pm(DomineeringMove.C0) + " | " + pm(DomineeringMove.C1) + " | "
				+ pm(DomineeringMove.C2) + pm(DomineeringMove.C3) + "\n" + "---+----+---+---\n" + pm(DomineeringMove.D0)
				+ " | " + pm(DomineeringMove.D1) + " | " + pm(DomineeringMove.D2) + pm(DomineeringMove.D3) + "\n");
	}

	private String pm(DomineeringMove m) {
		return (hMoves.contains(m) ? "X " : vMoves.contains(m) ? "O " : m.toString());
	}

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
