import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<DomineeringMove> hMoves = new ArrayList<>();
		List<DomineeringMove> vMoves = new ArrayList<>();

		hMoves.add(new DomineeringMove(0,2));
		hMoves.add(new DomineeringMove(1,2));
		hMoves.add(new DomineeringMove(0,3));
		hMoves.add(new DomineeringMove(1,3));
		
		vMoves.add(new DomineeringMove(0,0));
		vMoves.add(new DomineeringMove(3,0));
		vMoves.add(new DomineeringMove(0,1));
		vMoves.add(new DomineeringMove(0,2));
		vMoves.add(new DomineeringMove(1,2));
		vMoves.add(new DomineeringMove(2,2));
		// for (int v = 0; v < 4; v++) {
		// for (int h = 0; h < 5 - 1; h++) {
		// System.out.print((h+ ","+ v));
		// }
		// System.out.println();
		//
		// }
		// System.out.println();
		// for (int v = 0; v <5 -1; v++) {
		// for (int h = 0; h < 5 ; h++) {
		// System.out.print(h+","+ v);
		// }
		// System.out.println();
		//
		// }
//
//		int number = 0;
//		for (DomineeringMove v : vMoves) {
//			number++;
//			for (DomineeringMove h : hMoves) {
//				if ((v.getFirst() == h.getFirst() && v.getSecond() == h.getSecond())
//						|| (h.getFirst() == v.getFirst() && h.getSecond() + 1 == v.getSecond())
//						|| (h.getFirst() - 1 == v.getFirst() && h.getSecond() == v.getSecond())
//						|| (h.getFirst() - 1 == v.getFirst() && h.getSecond() + 1 == v.getSecond())) {
//					sysout
//					number--;
//					break;
//				}
//			}
//		}
//		System.out.println(number);

	}

}
