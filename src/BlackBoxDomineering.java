import java.util.Scanner;

public class BlackBoxDomineering {

	private static class CommandLineBlackBox implements MoveChannel<DomineeringMove> {

		public DomineeringMove getMove() {
			
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
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
			return (new DomineeringMove(one, two));
		}

		public void giveMove(DomineeringMove move) {
			System.out.println(move);
			System.out.flush();;
		}

		public void comment(String msg) {

		}

		public void end(int value) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		assert (args.length == 4);
		String whoStarts = args[0];
		int width = Integer.parseInt(args[2]);
		int height = Integer.parseInt(args[3]);
		Board<DomineeringMove> board = new DomineeringBoard(width, height);
		if (whoStarts.equals("first")) {
			board.tree().firstPlayer(new CommandLineBlackBox());
		} else {
			board.tree().secondPlayer(new CommandLineBlackBox());
		}

	}

}
