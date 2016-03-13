public class BlackBoxDomineering2 {
	
	private static class CommandLineBlackBox implements MoveChannel<DomineeringMove> {

		public DomineeringMove getMove() {
			String s = System.console().readLine("Enter your move: ");
			// System.out.println(s);
			int one = 0, two;
			String str = "";
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) != ',') {
					str += s.charAt(i);
				} else {
					one = Integer.parseInt(str);
					str = "";
				}
			}

			two = Integer.parseInt(str);
			System.out.println(one + " " + two);
			return (new DomineeringMove(one, two));
		}

		public void giveMove(DomineeringMove move) {
//			System.out.println("I play " + move);
		}

		public void comment(String msg) {
			System.out.println(msg);
		}

		public void end(int value) {
			System.out.println("Game over. The result is " + value);
		}
	}

	public static void main(String[] args) {
		assert (args.length == 3);
		String whoStarts = args[0];
		int width = Integer.parseInt(args[1]);
		int height = Integer.parseInt(args[2]);
		Board2<DomineeringMove> board = new DomBoard(width, height);
		if (whoStarts.equals("first")) {
			board.tree().firstPlayer(new CommandLineBlackBox(), Computer.FIRST);
		} else {
			board.tree().secondPlayer(new CommandLineBlackBox(),  Computer.SECOND);
		}

	}

}
