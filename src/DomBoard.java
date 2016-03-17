import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomBoard extends Board2<DomineeringMove> {

	// Players for the domineering
	private final Player H = Player.MAXIMIZER;
	private final Player V = Player.MINIMIZER;

	// We use the following class fields to represent the Board2:
	private final List<DomineeringMove> hMoves;
	private final List<DomineeringMove> vMoves;
	private final List<DomineeringMove> movesMade;
	private final int sizeX;
	private final int sizeY;

	// A public method to construct the initial Board2:
	public DomBoard() {
		hMoves = hor(4, 4);
		vMoves = ver(4, 4);
		movesMade = new ArrayList<>();
		sizeX = 4;
		sizeY = 4;

	}

	public DomBoard(int x, int y) {
		hMoves = hor(x, y);
		vMoves = ver(x, y);
		movesMade = new ArrayList<>();
		sizeX = x;
		sizeY = y;
	}

	// All other constructors should be private (why?):
	private DomBoard(List<DomineeringMove> hMoves, List<DomineeringMove> vMoves, List<DomineeringMove> movesMade,
			int sizeX, int sizeY) {
		// assert (disjoint(hMoves, vMoves));
		this.hMoves = hMoves;
		this.vMoves = vMoves;
		this.movesMade = movesMade;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	private static List<DomineeringMove> hor(int horizontal, int vertical) {

		List<DomineeringMove> moves = new ArrayList<>();

		for (int v = 0; v < vertical; v++) {
			for (int h = 0; h < horizontal - 1; h++) {
				moves.add(new DomineeringMove(h, v));
			}

		}
		return moves;
	}

	private static List<DomineeringMove> ver(int horizontal, int vertical) {

		List<DomineeringMove> moves = new ArrayList<DomineeringMove>();

		for (int h = 0; h < horizontal; h++) {
			for (int v = 0; v < vertical - 1; v++) {
				moves.add(new DomineeringMove(h, v));
			}

		}

		return moves;
	}

	// the convention is that h player start the move
	@Override
	Player nextPlayer() {

		return (movesMade.size() % 2 == 0 ? H : V);

	}

	// available moves
	@Override
	Set<DomineeringMove> availableMoves() {

		if (nextPlayer() == H) {

			return new HashSet<>(hMoves);
		}

		return new HashSet<>(vMoves);
	}

	@Override
	Set<DomineeringMove> availableHorizontalMoves() {

		return new HashSet<>(hMoves);
	}

	@Override
	Set<DomineeringMove> availableVerticalMoves() {
		return new HashSet<>(vMoves);
	}

	@Override
	int realHorizontalMoves() {
		
		int same = 0;
		int coord = -1;
		for (DomineeringMove h : hMoves) {
			
			if (h.getFirst() == coord)
			{
				same++;
			}
			coord = h.getSecond();
		}
		
		
		return (hMoves.size()-(1+same)/2);
	}

	@Override
	int realVerticalMoves() {
		int same = 0;
		int coord = -1;
		for (DomineeringMove h : vMoves) {
			
			if (h.getFirst() == coord)
			{
				same++;
			}
			coord = h.getSecond();
		}
		
		
		return (vMoves.size()-(1+same)/2);
	}

	@Override
	int safeHorizontalMoves() {
		int number = 0;
		for (DomineeringMove h : hMoves) {
			number++;
			for (DomineeringMove v : vMoves) {
				if ((h.getFirst() == v.getFirst() && h.getSecond() == v.getSecond())
						|| (h.getFirst() + 1 == v.getFirst() && h.getSecond() == v.getSecond())
						|| (h.getFirst() == v.getFirst() && h.getSecond() - 1 == v.getSecond())
						|| (h.getFirst() + 1 == v.getFirst() && h.getSecond() - 1 == v.getSecond())) {
					number--;
					break;
				}
			}
		}
		return hMoves.size() - number;
	}

	@Override
	int safeVerticalMoves() {
		int number = 0;
		for (DomineeringMove v : vMoves) {
			number++;
			for (DomineeringMove h : hMoves) {
				if ((v.getFirst() == h.getFirst() && v.getSecond() == h.getSecond())
						|| (h.getFirst() == v.getFirst() && h.getSecond() + 1 == v.getSecond())
						|| (h.getFirst() - 1 == v.getFirst() && h.getSecond() == v.getSecond())
						|| (h.getFirst() - 1 == v.getFirst() && h.getSecond() + 1 == v.getSecond())) {
					number--;
					break;
				}
			}
		}
		return vMoves.size() - number;
	}

	@Override
	int value() {
		if (nextPlayer() == V && vMoves.isEmpty())
			return 1;
		else if (nextPlayer() == H && hMoves.isEmpty())
			return -1;
		return 0;
	}

	@Override
	Board2<DomineeringMove> play(DomineeringMove move) {
		assert (hMoves.contains(move) || vMoves.contains(move));

		final List<DomineeringMove> mov = new ArrayList<>(movesMade);
		mov.add(move);
		if (nextPlayer() == Player.MAXIMIZER) {
			return new DomBoard(deleteHorizontal(hMoves, move, Player.MAXIMIZER),
					deleteVertical(vMoves, move, Player.MAXIMIZER), mov, sizeX, sizeY);
		} else {
			return new DomBoard(deleteHorizontal(hMoves, move, Player.MINIMIZER),
					deleteVertical(vMoves, move, Player.MINIMIZER), mov, sizeX, sizeY);
		}
	}

	private static List<DomineeringMove> deleteHorizontal(List<DomineeringMove> moves, DomineeringMove m, Player p) {
		List<DomineeringMove> newMoves = new ArrayList<>(moves);

		// getting the coordinates of the move
		int x1 = m.getFirst();
		int y1 = m.getSecond();
		int x2;
		int y2;
		if (p == Player.MAXIMIZER) {
			y2 = y1;
			x2 = x1 + 1;
		} else {
			y2 = y1 + 1;
			x2 = x1;
		}
		// ------------

		int[] toDelete = new int[100];
		int size = 0;
		int count = 0;
		for (DomineeringMove move : newMoves) {
			if ((move.getFirst() == x1 && move.getSecond() == y1) || (move.getFirst() == x2 && move.getSecond() == y2)
					|| (move.getFirst() == x2 - 1 && move.getSecond() == y2)
					|| (move.getFirst() == x1 - 1 && move.getSecond() == y1)) {
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

	private static List<DomineeringMove> deleteVertical(List<DomineeringMove> moves, DomineeringMove m, Player p) {
		List<DomineeringMove> newMoves = new ArrayList<>(moves);

		// getting the coordinates of the move
		int x1 = m.getFirst();
		int y1 = m.getSecond();
		int x2;
		int y2;
		if (p == Player.MAXIMIZER) {
			y2 = y1;
			x2 = x1 + 1;
		} else {
			y2 = y1 + 1;
			x2 = x1;
		}
		// ------------

		int[] toDelete = new int[100];
		int size = 0;
		int count = 0;
		for (DomineeringMove move : newMoves) {
			if ((move.getFirst() == x1 && move.getSecond() == y1) || (move.getFirst() == x2 && move.getSecond() == y2)
					|| (move.getFirst() == x2 && move.getSecond() == y2 - 1)
					|| (move.getFirst() == x1 && move.getSecond() == y1 - 1)) {
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

	// A simple conversion to string for testing:
	public String toString() {
		String s = "";

		s = availableMoves().toString();
		s += "\n\n";
		if (nextPlayer() == Player.MAXIMIZER) {
			for (int v = 0; v < sizeY; v++) {
				for (int h = 0; h < sizeX; h++) {
					if (hMoves.contains(new DomineeringMove(h, v)))
						s += h + "," + v + " ";
					else if (movesMade.contains(new DomineeringMove(h, v)))
						s += "  H ";
					else
						s += "  * ";
				}
				s += "\n";

			}
		} else {
			for (int v = 0; v < sizeY; v++) {
				for (int h = 0; h < sizeX; h++) {
					if (vMoves.contains(new DomineeringMove(h, v)))
						s += h + "," + v + " ";
					else if (movesMade.contains(new DomineeringMove(h, v)))
						s += "  V ";
					else
						s += "  * ";
				}
				s += "\n";

			}
		}

		return s;

	}

}
