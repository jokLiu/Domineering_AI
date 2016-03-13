import java.util.LinkedHashMap;
import java.util.Set;

public abstract class Board2<Move> {
	abstract Player nextPlayer();

	abstract Set<Move> availableMoves();

	abstract int value();

	abstract Board2<Move> play(Move move);

	public GameTree2<Move> tree(Move m)
	{
		Board2<Move> board = play(m);
		return new GameTree2<Move>(board, board.availableMoves(), board.value(), null);
	}
	
	public GameTree2<Move> tree()
	{
		return new GameTree2<Move>(this, this.availableMoves(), value(), null);
	}
	
	public GameTree2<Move> tree(int alfa, int beta, MoveChannel<Move> c, Computer comp) {
		
		
		if (availableMoves().isEmpty()) {
			return new GameTree2<Move>(this, this.availableMoves(), value(), null);
		}
		else
		{
			return (nextPlayer() == Player.MAXIMIZER ? maxTree(alfa, beta, c,comp) : minTree(alfa, beta, c, comp));
		}
		
	}

	// Two helper methods for that, which call the above method tree:
	public GameTree2<Move> maxTree(int alfa, int beta, MoveChannel<Move> c, Computer comp) {
		assert (!availableMoves().isEmpty());

		int optimalOutcome = Integer.MIN_VALUE;
		LinkedHashMap<Move, GameTree2<Move>> children = new LinkedHashMap<Move, GameTree2<Move>>();
		Move lastMove = null;
		for (Move m : availableMoves()) {
			lastMove = m;
			GameTree2<Move> subtree = play(m).tree(alfa, beta, c, comp);
			optimalOutcome = Math.max(optimalOutcome, subtree.optimalOutcome());
			alfa = Math.max(alfa,  optimalOutcome);
			if(alfa==1)
			{
				break;
			}
		}
		c.giveMove(lastMove);
		return new GameTree2<Move>(this, availableMoves(), optimalOutcome, lastMove);
	}

	public GameTree2<Move> minTree(int alfa, int beta, MoveChannel<Move> c, Computer comp) {
		assert (!availableMoves().isEmpty());

		int optimalOutcome = Integer.MAX_VALUE;
		LinkedHashMap<Move, GameTree2<Move>> children = new LinkedHashMap<Move, GameTree2<Move>>();
		Move lastMove = null;
		for (Move m : availableMoves()) {
			lastMove = m;
			GameTree2<Move> subtree = play(m).tree(alfa, beta, c, comp);
			optimalOutcome = Math.min(optimalOutcome, subtree.optimalOutcome());
			beta = Math.min(beta, optimalOutcome);
			if(beta==-1)
			{
				break;
			}
		}
		c.giveMove(lastMove);
		return new GameTree2<Move>(this, availableMoves(), optimalOutcome, lastMove);
	}
}