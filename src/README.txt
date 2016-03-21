Instructions how to play:

	Board representation:

	------------------> x
	|
	|	0,0  1,0  2,0  3,0
	|	0,1  1,1  2,1  3,1
	|	0,2  1,2  2,2  3,2
	|	0,3  1,3  2,3  3,3  
    \/
    y

    Part 1:

	1. Moves are written by two numbers seperated space: "0,0", 
	where first number is the x coordinate and the second number is the second y coordinate, and
	the move depends on the player (if the player is horizontal then the move consists of x,y and (x+1), y.
	If the player is veritacal then the move consists of x,y and x,(y+1) ).
	The moves are the same as in the specification of the 2nd and 3rd parts.
	The representation is different than in the first submition, it is changed due to 2nd and 3rd parts.

	Part 2:

	BlackBoxDomineering is fully working, and tested with the BlackBox which were provided by lecturer (Martin Escardo).

	Part 3:

	BlackBoxDomineering2 is aslo fully working, it is optimal until 5x5 board. (I used alpha beta prunning to achieve that).
	For the bigger boards it might not be efficient because I am still looking for a better heuristic. (Currently I am using 
	number of available horizontal and vertical moves). However, the board is efficient against random player until 7x7 board, and 
	also about 50% efficient against heuristic BlackBox3.