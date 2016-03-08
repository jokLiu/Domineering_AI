
public class CommandLineDomineering {
	private static class CommandLineDom implements MoveChannel<DomineeringMove> {
	    public DomineeringMove getMove() {
	      return(DomineeringMove.valueOf(System.console().readLine("Enter your move: ")));
	    }

	    public void giveMove(DomineeringMove move) {
	      System.out.println("I play " + move);
	    }

	    public void comment(String msg) {
	      System.out.println(msg);
	    }

	    public void end(int value) {
	      System.out.println("Game over. The result is " + value);
	    }
	  }

	  public static void main(String [] args) {
		  DomineeringBoard board = new DomineeringBoard();
	     // board.tree().firstPlayer(new CommandLineTTT());
	    board.tree().secondPlayer(new CommandLineDom());
	  }

}
