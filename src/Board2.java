import java.util.Set;

public abstract class Board2<Move> {
	abstract Player nextPlayer();

	abstract Set<Move> availableMoves();

	abstract Set<Move> availableHorizontalMoves();

	abstract Set<Move> availableVerticalMoves();

	abstract int realHorizontalMoves();

	abstract int realVerticalMoves();

	abstract int safeHorizontalMoves();

	abstract int safeVerticalMoves();

	abstract int value();

	abstract Board2<Move> play(Move move);

	public GameTree2<Move> tree(Move m) {
		Board2<Move> board = play(m);
		return new GameTree2<Move>(board, board.availableMoves(), board.value(), null);
	}

	public GameTree2<Move> tree() {
		return new GameTree2<Move>(this, this.availableMoves(), value(), null);
	}

	public GameTree2<Move> tree(int alfa, int beta, int level) {

		if (availableMoves().isEmpty()) {
			return new GameTree2<Move>(this, this.availableMoves(), value(), null);
		} else {
			return (nextPlayer() == Player.MAXIMIZER ? maxTree(alfa, beta, level) : minTree(alfa, beta, level));
		}

	}

	// Two helper methods for that, which call the above method tree:
	public GameTree2<Move> maxTree(int alfa, int beta, int level) {
		assert (!availableMoves().isEmpty());

		if (level <= 0 ) {
			if (availableVerticalMoves().size() <= availableHorizontalMoves().size()) {
				return new GameTree2<Move>(this, availableMoves(), 1, null);
			} else  {
				return new GameTree2<Move>(this, availableMoves(), -1, null);
			} 
		}
		int optimalOutcome = Integer.MIN_VALUE;

		Move lastMove = null;
		for (Move m : availableMoves()) {
			lastMove = m;
			GameTree2<Move> subtree = play(m).tree(alfa, beta, level - 1);
			optimalOutcome = Math.max(optimalOutcome, subtree.optimalOutcome());
			alfa = Math.max(alfa, optimalOutcome);
			if (alfa == 1) {
				break;
			}
		}

		return new GameTree2<Move>(this, availableMoves(), optimalOutcome, lastMove);
	}

	public GameTree2<Move> minTree(int alfa, int beta, int level) {
		assert (!availableMoves().isEmpty());

		if (level <= 0) {
			if ( availableVerticalMoves().size() >= availableHorizontalMoves().size()) {
				return new GameTree2<Move>(this, availableMoves(), -1, null);
			} else  {
				return new GameTree2<Move>(this, availableMoves(), 1, null);
			} 
		}

		int optimalOutcome = Integer.MAX_VALUE;

		Move lastMove = null;
		for (Move m : availableMoves()) {
			lastMove = m;
			GameTree2<Move> subtree = play(m).tree(alfa, beta, level - 1);
			optimalOutcome = Math.min(optimalOutcome, subtree.optimalOutcome());
			beta = Math.min(beta, optimalOutcome);
			if (beta == -1) {
				break;
			}
		}

		return new GameTree2<Move>(this, availableMoves(), optimalOutcome, lastMove);
	}
}