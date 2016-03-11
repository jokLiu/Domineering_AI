
public class Test {

	public static void main(String[] args) {
//		int count=0;
//		for(int v=0; v<4; v++)
//		{
//			for(int h=0; h<4-1; h++)
//			{
//				System.out.println(count + "   " +  ++count);
//			}
//			System.out.println();
//			count++;
//		}
//		
////		return moves;
		DomineeringMove m = new DomineeringMove(0,3);
		DomineeringMove m2 = new DomineeringMove(0,3);
		System.out.println(m.equals(m2));
	}

}
