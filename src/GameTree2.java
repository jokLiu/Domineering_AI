import java.util.Map;
import java.util.Set;

public class GameTree2<Move> {
	private final Board2<Move> board;
	private final Set<Move> moves;
	private final int optimalOutcome;
	public final Move lastMove;

	public GameTree2(Board2<Move> board, Set<Move> moves, int optimalOutcome, Move lastMove) {

		assert (board != null);
		this.board = board;
		this.moves = moves;
		this.lastMove = lastMove;
		this.optimalOutcome = optimalOutcome;
	}

	public boolean isLeaf() {
		return (moves.isEmpty());
	}

	// Getter methods:
	public Board2<Move> board() {
		return board;
	}

	public Set<Move> availMoves() {
		return moves;
	}

	public int optimalOutcome() {
		return optimalOutcome;
	}

	// Plays first using this tree:
	public void firstPlayer(MoveChannel<Move> c, boolean isSmallBoard) {
		c.comment(board + "\nThe optimal outcome is " + optimalOutcome);

		if (isLeaf()) {
			assert (optimalOutcome == board.value());
			c.end(board.value());
		} else {

			if (isSmallBoard) {
				GameTree2<Move> tree = board.tree(Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
				c.giveMove(tree.lastMove);
				GameTree2<Move> newTree = board.tree(tree.lastMove);
				newTree.secondPlayer(c, isSmallBoard);
			}
			else
			{
				GameTree2<Move> tree = board.tree(Integer.MIN_VALUE, Integer.MAX_VALUE, 6);
				c.giveMove(tree.lastMove);
				GameTree2<Move> newTree = board.tree(tree.lastMove);
				newTree.secondPlayer(c, isSmallBoard);
			}
		}
	}

	// Plays second using this tree:
	public void secondPlayer(MoveChannel<Move> c, boolean isSmallBoard) {
		c.comment(board + "\nThe optimal outcome is " + optimalOutcome);

		if (isLeaf()) {
			assert (optimalOutcome == board.value());
			c.end(board.value());
		} else {
			Move m = c.getMove();
			assert (availMoves().contains(m));
			GameTree2<Move> tree = board.tree(m);
			tree.firstPlayer(c, isSmallBoard);
		}
	}
}