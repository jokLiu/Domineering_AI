
public class CommandLineDomineering {
	private static class CommandLineDom implements MoveChannel<DomineeringMove> {
	   
		public DomineeringMove getMove() {
	      String s = System.console().readLine("Enter your move: ");
//	      System.out.println(s);
	      int one =0, two;
	      String str="";
	      for(int i=0; i<s.length(); i++)
	      {
	    	  if(s.charAt(i) != ',')
	    	  {
	    		  str+=s.charAt(i);
	    	  }
	    	  else {
	    		  one = Integer.parseInt(str);
	    		  str="";
	    	  }
	      }
	      
	      two = Integer.parseInt(str);
	      System.out.println(one + " " + two);
	      return(new DomineeringMove(one, two));
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
		  Board<DomineeringMove> board = new DomineeringBoard();
	      board.tree().firstPlayer(new CommandLineDom());
//	    board.tree().secondPlayer(new CommandLineDom());
	  }

}
