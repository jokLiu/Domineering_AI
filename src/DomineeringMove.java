import java.util.EnumSet;

public enum DomineeringMove {
	
//	The possible moves of the game
	A0, A1, A2, A3,
	B0, B1, B2, B3,
	C0, C1, C2, C3,
	D0, D1, D2, D3;

//	Horizontal moves
	 static private final EnumSet<DomineeringMove> H1 = EnumSet.of(A0,A1);
	 static private final EnumSet<DomineeringMove> H2 = EnumSet.of(A1,A2);
	 static private final EnumSet<DomineeringMove> H3 = EnumSet.of(A2,A3);
	 static private final EnumSet<DomineeringMove> H4 = EnumSet.of(B0,B1);
	 static private final EnumSet<DomineeringMove> H5 = EnumSet.of(B1,B2);
	 static private final EnumSet<DomineeringMove> H6 = EnumSet.of(B2,B3);
	 static private final EnumSet<DomineeringMove> H7 = EnumSet.of(C0,C1);
	 static private final EnumSet<DomineeringMove> H8 = EnumSet.of(C1,C2);
	 static private final EnumSet<DomineeringMove> H9 = EnumSet.of(C2,C3);
	 static private final EnumSet<DomineeringMove> H10 = EnumSet.of(D0,D1);
	 static private final EnumSet<DomineeringMove> H11 = EnumSet.of(D1,D2);
	 static private final EnumSet<DomineeringMove> H12 = EnumSet.of(D2,D3);
	 
//	Vertical moves
	 static private final EnumSet<DomineeringMove> V1 = EnumSet.of(A0,B0);
	 static private final EnumSet<DomineeringMove> V2 = EnumSet.of(B0,C0);
	 static private final EnumSet<DomineeringMove> V3 = EnumSet.of(C0,D0);
	 static private final EnumSet<DomineeringMove> V4 = EnumSet.of(A1,B1);
	 static private final EnumSet<DomineeringMove> V5 = EnumSet.of(B1,C1);
	 static private final EnumSet<DomineeringMove> V6 = EnumSet.of(C1,D1);
	 static private final EnumSet<DomineeringMove> V7 = EnumSet.of(A2,B2);
	 static private final EnumSet<DomineeringMove> V8 = EnumSet.of(B2,C2);
	 static private final EnumSet<DomineeringMove> V9 = EnumSet.of(C2,D2);
	 static private final EnumSet<DomineeringMove> V10 = EnumSet.of(A3,B3);
	 static private final EnumSet<DomineeringMove> V11 = EnumSet.of(B3,C3);
	 static private final EnumSet<DomineeringMove> V12 = EnumSet.of(C3,D3);
	 
	 
	 //empty board (no moves)
	 static public EnumSet<DomineeringMove> noMoves() {
		    return(EnumSet.noneOf(DomineeringMove.class));
		  }
	 

}
