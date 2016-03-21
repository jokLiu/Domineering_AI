import java.util.Scanner;

public class BlackBoxDomineering {

	private static class CommandLineBlackBox implements MoveChannel<DomineeringMove> {

		public DomineeringMove getMove() {

			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			int one = 0, two=0;
			String str = "";
			try {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) != ',') {
						str += s.charAt(i);
					} else {
						one = Integer.parseInt(str);
						str = "";
					}
				}
			
			two = Integer.parseInt(str);
			} catch (NumberFormatException e) {
				System.exit(1);
			}
			return (new DomineeringMove(one, two));

		}

		public void giveMove(DomineeringMove move) {
			System.out.println(move);
			System.out.flush();
			;
		}

		public void comment(String msg) {

		}

		public void end(int value) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		try {
			assert (args.length == 4);
			String whoStarts = args[0];
			int width = Integer.parseInt(args[2]);
			int height = Integer.parseInt(args[3]);
			Board<DomineeringMove> board = new DomineeringBoard(width, height);
			if (whoStarts.equals("first")) {
				board.tree().firstPlayer(new CommandLineBlackBox());
			} else if (whoStarts.equals("second")) {
				board.tree().secondPlayer(new CommandLineBlackBox());
			} else {
				System.exit(0);
			}
		} catch (AssertionError e) {
			System.exit(1);
		}

	}

}
