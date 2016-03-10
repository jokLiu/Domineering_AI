import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomineeringBoard extends Board<DomineeringMove> {

	// Players for the domineering
	private static final Player H = Player.MAXIMIZER;
	private static final Player V = Player.MINIMIZER;

	// We use the following class fields to represent the board:
	private final List<DomineeringMove> hMoves;
	private final List<DomineeringMove> vMoves;
	private List<DomineeringMove> movesMade;
	private final int sizeX;
	private final int sizeY;

	// A public method to construct the initial board:
	public DomineeringBoard() {
		hMoves = hor(4, 4);
		vMoves = ver(4, 4);
		movesMade = new ArrayList<>();
		sizeX = 4;
		sizeY = 4;

	}

	public DomineeringBoard(int x, int y) {
		hMoves = hor(x, y);
		vMoves = ver(x, y);
		movesMade = new ArrayList<>();
		sizeX = x;
		sizeY = y;
	}

	// All other constructors should be private (why?):
	private DomineeringBoard(List<DomineeringMove> hMoves, List<DomineeringMove> vMoves,
			List<DomineeringMove> movesMade, int sizeX, int sizeY) {
		// assert (disjoint(hMoves, vMoves));
		this.hMoves = hMoves;
		this.vMoves = vMoves;
		this.movesMade = movesMade;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	private List<DomineeringMove> hor(int horizontal, int vertical) {

		List<DomineeringMove> moves = new ArrayList<>(horizontal * vertical);
		int count = 0;
		for (int v = 0; v < vertical; v++) {
			for (int h = 0; h < horizontal - 1; h++) {
				moves.add(new DomineeringMove(count, ++count));
			}
			count++;
		}
		return moves;
	}

	private List<DomineeringMove> ver(int horizontal, int vertical) {

		List<DomineeringMove> moves = new ArrayList<DomineeringMove>();
		int count = 0;
		for (int v = 0; v < horizontal; v++) {
			for (int h = 0; h < vertical - 1; h++) {
				moves.add(new DomineeringMove(count++, count + horizontal - 1));
			}

		}

		return moves;
	}

	// the convention is that h player start the move
	@Override
	Player nextPlayer() {

		return ((hMoves.size() + vMoves.size()) % 2 == 0 ? H : V);

	}

	// available moves (dots)
	@Override
	Set<DomineeringMove> availableMoves() {
		if (nextPlayer() == H) {
			return new HashSet<>(hMoves);
		}
		return new HashSet<>(vMoves);
	}

	@Override
	int value() {
		return (hMoves.isEmpty() ? -1 : vMoves.isEmpty() ? 1 : 0);
	}

	@Override
	Board<DomineeringMove> play(DomineeringMove move) {
		assert (!hMoves.contains(move) && !vMoves.contains(move));
		List<DomineeringMove> mov = movesMade;
		mov.add(move);
		return new DomineeringBoard(delete(hMoves, move), delete(vMoves, move), mov, sizeX, sizeY);

	}

	private List<DomineeringMove> delete(List<DomineeringMove> moves, DomineeringMove m) {
		List<DomineeringMove> newMoves = moves;
		int x = m.getFirst();
		int y = m.getSecond();
		int[] toDelete = new int[100];
		int size = 0;
		int count = 0;
		for (DomineeringMove move : newMoves) {
			if (move.getFirst() == x || move.getFirst() == y || move.getSecond() == x || move.getSecond() == y) {
				toDelete[size] = count;
				size++;
			}
			count++;
		}

		for (int i = size - 1; i >= 0; i--) {
			newMoves.remove(toDelete[i]);
		}
		return newMoves;
	}

	// The following short private methods are for readability. They
	// ensure immutability.

	// A simple conversion to string for testing:
	// public String toString() {
	// return (pm(DomineeringMove.A0) + " | " + pm(DomineeringMove.A1) + " | " +
	// pm(DomineeringMove.A2)
	// + pm(DomineeringMove.A3) + "\n" + "---+----+---+---\n" +
	// pm(DomineeringMove.B0) + " | "
	// + pm(DomineeringMove.B1) + " | " + pm(DomineeringMove.B2) +
	// pm(DomineeringMove.B3) + "\n"
	// + "---+----+---+---\n" + pm(DomineeringMove.C0) + " | " +
	// pm(DomineeringMove.C1) + " | "
	// + pm(DomineeringMove.C2) + pm(DomineeringMove.C3) + "\n" +
	// "---+----+---+---\n" + pm(DomineeringMove.D0)
	// + " | " + pm(DomineeringMove.D1) + " | " + pm(DomineeringMove.D2) +
	// pm(DomineeringMove.D3) + "\n");
	// }

	// private String pm(DomineeringMove m) {
	// return (hMoves.contains(m) ? "X " : vMoves.contains(m) ? "O " :
	// m.toString());
	// }

	// private List<String> getCoord(List<DomineeringMove> moves) {
	// List<String> col = new ArrayList<String>();
	// for (int i = 0; i < moves.size(); i++) {
	// col.add(moves.get(i).getCoord1());
	// col.add(moves.get(i).getCoord2());
	// }
	// return col;
	// }

	// static private List<DomineeringMove> intersection(List<DomineeringMove>
	// a, List<DomineeringMove> b) {
	// List<DomineeringMove> c = a; // a.clone();
	// c.retainAll(b);
	// return c;
	// }
	//
	// static private boolean disjoint(List<DomineeringMove> a,
	// List<DomineeringMove> b) {
	// return (intersection(a, b).isEmpty());
	// }
	//
	// static private List<DomineeringMove> union(List<DomineeringMove> a,
	// List<DomineeringMove> b) {
	// List<DomineeringMove> c = a;
	// c.addAll(b);
	// return c;
	// }
	//
	// static private List<DomineeringMove> add(List<DomineeringMove> a,
	// DomineeringMove b) {
	// List<DomineeringMove> c = a;
	// c.add(b);
	// return c;
	// }

	// private List<DomineeringMove> addHor(List<DomineeringMove> a,
	// DomineeringMove b)
	// {
	// String coord1 = b.getCoord1();
	// String coord2 = b.getCoord2();
	// List<DomineeringMove> previousMoves = a;
	// List<DomineeringMove> moves = hor(sizeX, sizeY);
	// for(DomineeringMove move: moves)
	// {
	// if(move.getCoord1().equals(coord1) ||
	// move.getCoord1().equals(coord2) ||
	// move.getCoord2().equals(coord1) ||
	// move.getCoord2().equals(coord2))
	// {
	// previousMoves.add(move);
	// }
	// }
	// return previousMoves;
	//
	// }
	//
	// private List<DomineeringMove> addVer(List<DomineeringMove> a,
	// DomineeringMove b)
	// {
	// String coord1 = b.getCoord1();
	// String coord2 = b.getCoord2();
	// List<DomineeringMove> previousMoves = a;
	// List<DomineeringMove> moves = ver(sizeX, sizeY);
	// for(DomineeringMove move: moves)
	// {
	// if(move.getCoord1().equals(coord1) ||
	// move.getCoord1().equals(coord2) ||
	// move.getCoord2().equals(coord1) ||
	// move.getCoord2().equals(coord2))
	// {
	// previousMoves.add(move);
	// }
	// }
	// return previousMoves;
	//
	// }

	// private Set<DomineeringMove> complement(List<DomineeringMove> a,
	// List<DomineeringMove> b) {
	// List<DomineeringMove> c = ver(sizeX, sizeY);
	// List<DomineeringMove> e = hor(sizeX, sizeY);
	// for (DomineeringMove move : a) {
	// c.remove(a);
	// }
	// for (DomineeringMove move : b) {
	// e.remove(a);
	// }
	// List<DomineeringMove> finalList = union(c, e);
	// Set<DomineeringMove> moves = new HashSet<DomineeringMove>(finalList);
	// return moves;
	// }
	//
	// private boolean winsH() {
	// return (vMoves.containsAll(ver(sizeX, sizeY)));
	//
	// }
	//
	// private boolean winsV() {
	// return (hMoves.containsAll(hor(sizeX, sizeY)));
	//
	// }

}
