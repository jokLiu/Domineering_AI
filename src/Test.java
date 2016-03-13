import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
List<DomineeringMove> moves = new ArrayList<>();
		
		for (int v = 0; v < 4; v++) {
			for (int h = 0; h < 5 - 1; h++) {
				System.out.println((h+ ","+ v));
			}
		
		}
		System.out.println();
		for (int v = 0; v <4 -1; v++) {
			for (int h = 0; h < 5 ; h++) {
				System.out.println(h+","+ v);
			}

		}

	}

}
