import java.util.Set;

public abstract class Board2<Move> {
	abstract Player nextPlayer();

	abstract Set<Move> availableMoves();

	abstract Set<Move> availableHorizontalMoves();

	abstract Set<Move> availableVerticalMoves();

	abstract Move getRandomHorMove();
	
	abstract Move getRandomVerMove();

	abstract int value();
	
	abstract Move getBestHorMove(int i);
	
	abstract Move getBestVerMove(int i);

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
		if(availableMoves().size() >= 44)
		{
			if(getBestHorMove(1) != null)
			 return new GameTree2<Move>(this, availableMoves(), 1, getBestHorMove(1));
			else if(getBestHorMove(2) != null)
				 return new GameTree2<Move>(this, availableMoves(), 1, getBestHorMove(2));
			else if(getBestHorMove(3) != null)
				 return new GameTree2<Move>(this, availableMoves(), 1, getBestHorMove(3));
			else if(getBestHorMove(4) != null)
				 return new GameTree2<Move>(this, availableMoves(), 1, getBestHorMove(4));
		}
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
				return new GameTree2<Move>(this, availableMoves(), optimalOutcome, lastMove);
				//break;
			}
		}
		return new GameTree2<Move>(this, availableMoves(), optimalOutcome, getRandomHorMove());
		

		
	}

	public GameTree2<Move> minTree(int alfa, int beta, int level) {
		assert (!availableMoves().isEmpty());

		if(availableMoves().size() >= 44)
		{
			if(getBestVerMove(1) != null)
				return new GameTree2<Move>(this, availableMoves(), -1, getBestVerMove(1));
			else if(getBestVerMove(2) != null)
				return new GameTree2<Move>(this, availableMoves(), -1, getBestVerMove(2));
			else if(getBestVerMove(3) != null)
				return new GameTree2<Move>(this, availableMoves(), -1, getBestVerMove(3));
			else if(getBestVerMove(4) != null)
				return new GameTree2<Move>(this, availableMoves(), -1, getBestVerMove(4));
		}
		
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
				return new GameTree2<Move>(this, availableMoves(), optimalOutcome, lastMove);
//				break;
			}
		}
		return new GameTree2<Move>(this, availableMoves(), optimalOutcome, getRandomVerMove());
		
	}
}