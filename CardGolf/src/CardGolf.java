// -------------------------------------------------------
// Assignment #4
// Written by: Laila Chamma'a, ID: 40107470
// For COMP 248 Section W – Winter 2019
// --------------------------------------------------------
import java.util.Scanner;
public class CardGolf {

	public static void main(String[] args) {
		Scanner user = new Scanner(System.in);
		//header
		System.out.println("\t-------****-------****-------****-------****-------");
		System.out.println("\t\tWelcome to Laila's Card Golf Game!");
		System.out.println("\t-------****-------****-------****-------****-------");
		System.out.println("To win this game you need some luck with the cards and a bit of "
				+ "strategy.\r\n" + 
				"Just like the outdoor game of golf, the card game known as Golf has a goal "
				+ "of keeping\nthe score as low as possible.\r\n" + 
				"Okay .. Let's start the game! May the best golfer win!!!");

		Player[] players = new Player[2];

		//asking for names
		System.out.println("\nWhat's the name of 1st player?");
		players[0] = new Player(user.next());
		System.out.println("What's the name of 2nd player?");
		players[1] = new Player(user.next());


		DeckAndDiscard deck1 = new DeckAndDiscard();

		//dealing 9 cards for player1 and player2
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				players[0].setTo(r, c, deck1.pickACard());
				players[1].setTo(r, c, deck1.pickACard());
			}
		}
		//two 3x3 star boards
		char[][] stars1 = new char[3][3];
		char[][] stars2 = new char[3][3];

		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				stars1[r][c] = '*';
				stars2[r][c] = '*';
			}
		}
		
		//which cards to turn over

		for (int j = 0; j < 2; j++) {
			System.out.println("\n" + players[j].getName() +", time to decide which 2 cards "
					+ "you want to turn over");
			for (int i = 0; i < 2; i++) {
				System.out.print("Which card to you want to flip (row col) ");
				int row1 = user.nextInt();
				int col1 = user.nextInt();
				//flipping after validating
				if (validateCard(row1, col1)) {
					if (j == 0) {
						stars1[row1][col1] = players[j].cardAt(row1, col1);
						players[j].flip(row1, col1);
					}
					else {
						stars2[row1][col1] = players[j].cardAt(row1, col1);
						players[j].flip(row1, col1);
					}
				}
				//if the input is wrong
				while (validateCard(row1, col1) == false) {
					System.out.print("Enter valid coordinates, please: ");
					row1 = user.nextInt();
					col1 = user.nextInt();
					if (validateCard(row1, col1)) {
					if (j == 0) {
						stars1[row1][col1] = players[j].cardAt(row1, col1);
						players[j].flip(row1, col1);
					}
					else {
						stars2[row1][col1] = players[j].cardAt(row1, col1);
						players[j].flip(row1, col1);
					}
					}
				}
			}
		}
		//add a card to the discard pile
		deck1.discard(deck1.pickACard());

		while (players[0].allTurned() == false && players[1].allTurned() == false) {
			for (int j = 0; j < 2; j++) {
				if (players[0].allTurned() == false && players[1].allTurned() == false) {
					//alternating between player 1 and 2
					System.out.println("\nDiscard pile has " + deck1.topDiscard());
					System.out.println("\n" + players[j].getName() + "'s turn:\n------------------------");
					System.out.println("Here is your board.");
					if (j == 0)
						displayStars(stars1);
					else
						displayStars(stars2);

					int row1 = 0;
					int col1 = 0;

					System.out.print("\nDo you want the card on the discard pile(0) or a new card(1) ");
					//if discard pile
					int decision = user.nextInt();
					if (decision == 0) {
						System.out.print(players[j].getName() + ", do you want to replace a flipped card(0) "
								+ "or flip a new card(1) ");
						decision = user.nextInt();
						//if replace a flipped card
						if (decision == 0) {
							System.out.print("Which flipped card do you want to replace (row col)? ");
							row1 = user.nextInt();
							col1 = user.nextInt();
							if (validateCard(row1, col1)) {
								if (players[j].isTurned(row1, col1)) {
									//to make the switch we need a third variable 
									char temp = deck1.topDiscard();
									//discard the card
									deck1.switch_with_discard(players[j].cardAt(row1, col1));
									//set the space to the discard card
									players[j].setTo(row1, col1, temp);
									if (j == 0) {
										stars1[row1][col1] = players[j].cardAt(row1, col1);
									}
									else {
										stars2[row1][col1] = players[j].cardAt(row1, col1);
									}
								}
								while (players[j].isTurned(row1, col1) == false) {
									System.out.print("This card is not yet flipped. Choose a "
											+ "flipped card, please: ");
									row1 = user.nextInt();
									col1 = user.nextInt();
									if (validateCard(row1, col1)) {
										if (players[j].isTurned(row1, col1) == true) {
											//to make the switch we need a third variable 
											char temp = deck1.topDiscard();
											//discard the card
											deck1.switch_with_discard(players[j].cardAt(row1, col1));
											//set the space to the discard card
											players[j].setTo(row1, col1, temp);
											if (j == 0) {
												stars1[row1][col1] = players[j].cardAt(row1, col1);
											}
											else {
												stars2[row1][col1] = players[j].cardAt(row1, col1);
											}
										}
									}
								}
							}
						}
						//if flip a new card
						else if (decision == 1) {
							System.out.print("Which non-flipped card do you want to flip (row col)? ");
							row1 = user.nextInt();
							col1 = user.nextInt();
							if (validateCard(row1, col1)) {
								if (players[j].isTurned(row1, col1) == false) {
									//to make the switch we need a third variable 
									char temp = deck1.topDiscard();
									//discard the card
									deck1.switch_with_discard(players[j].cardAt(row1, col1));
									//set the space to the discard card
									players[j].setTo(row1, col1, temp);
									if (j == 0) {
										stars1[row1][col1] = players[j].cardAt(row1, col1);
									}
									else {
										stars2[row1][col1] = players[j].cardAt(row1, col1);
									}
								}
								//makes sure that the card they picked isn't flipped
								while (players[j].isTurned(row1, col1) == true) {
									System.out.print("This card is already flipped. Choose an "
											+ "unflipped card, please: ");
									row1 = user.nextInt();
									col1 = user.nextInt();
									if (validateCard(row1, col1)) {
										if (players[j].isTurned(row1, col1) == false) {
											//to make the switch we need a third variable 
											char temp = deck1.topDiscard();
											//discard the card
											deck1.switch_with_discard(players[j].cardAt(row1, col1));
											//set the space to the discard card
											players[j].setTo(row1, col1, temp);
											if (j == 0) {
												stars1[row1][col1] = players[j].cardAt(row1, col1);
											}
											else {
												stars2[row1][col1] = players[j].cardAt(row1, col1);
											}
										}
									}
								}
								if (players[j].isTurned(row1, col1) == false) {
									players[j].flip(row1, col1);
								}
							}
						}
					}
					//if new card from deck
					else if (decision == 1) {
						deck1.discard(deck1.pickACard());
						System.out.println("The card you are playing is " + deck1.topDiscard());
						System.out.print("\nReplace a card(0) or toss it(1)? ");
						decision = user.nextInt();
						//if replace
						if (decision == 0) {
							System.out.print(players[j].getName() + ", do you want to replace a flipped "
									+ "card(0) or flip a new card(1) ");
							decision = user.nextInt();
							//if replace a flipped card
							if (decision == 0) {

								System.out.print("Which flipped card do you want to replace (row col)? ");
								row1 = user.nextInt();
								col1 = user.nextInt();
								if (validateCard(row1, col1)) {
									if (players[j].isTurned(row1, col1) == true) {
										//to make the switch we need a third variable 
										char temp = deck1.topDiscard();
										//discard the card
										deck1.switch_with_discard(players[j].cardAt(row1, col1));
										//set the space to the discard card
										players[j].setTo(row1, col1, temp);
										if (j == 0) {
											stars1[row1][col1] = players[j].cardAt(row1, col1);
										}
										else {
											stars2[row1][col1] = players[j].cardAt(row1, col1);
										}
									}
									while (players[j].isTurned(row1, col1) == false) {
										System.out.print("This card is not yet flipped. Choose a "
												+ "flipped card, please: ");
										row1 = user.nextInt();
										col1 = user.nextInt();
										if (validateCard(row1, col1)) {
											if (players[j].isTurned(row1, col1) == true) {
												//to make the switch we need a third variable 
												char temp = deck1.topDiscard();
												//discard the card
												deck1.switch_with_discard(players[j].cardAt(row1, col1));
												//set the space to the discard card
												players[j].setTo(row1, col1, temp);
												if (j == 0) {
													stars1[row1][col1] = players[j].cardAt(row1, col1);
												}
												else {
													stars2[row1][col1] = players[j].cardAt(row1, col1);
												}
											}
										}
									}
									
								}
							}
							//if flip a new card
							else if (decision == 1) {
								System.out.print("Which non-flipped card do you want to flip (row col)? ");
								row1 = user.nextInt();
								col1 = user.nextInt();
								//makes sure that the card isn't flipped already
								while (players[j].isTurned(row1, col1) == true) {
									System.out.print("This card is already flipped. Choose an "
											+ "unflipped card, please: ");
									row1 = user.nextInt();
									col1 = user.nextInt();
									if (players[j].isTurned(row1, col1) == false) {
										if (validateCard(row1, col1)) {
											if (j == 0) {
												stars1[row1][col1] = deck1.topDiscard();
												
											}
											else {
												stars2[row1][col1] = deck1.topDiscard();
												
											}
											if (players[j].isTurned(row1, col1) == true) {
												//to make the switch we need a third variable 
												char temp = deck1.topDiscard();
												//discard the card
												deck1.switch_with_discard(players[j].cardAt(row1, col1));
												//set the space to the discard card
												players[j].setTo(row1, col1, temp);
											}
										}
									}
								}
								if (players[j].isTurned(row1, col1) == false) {
									if (validateCard(row1, col1)) {
										if (j == 0) {
											stars1[row1][col1] = players[j].cardAt(row1, col1);
											players[j].flip(row1, col1);
										}
										else {
											stars2[row1][col1] = players[j].cardAt(row1, col1);
											players[j].flip(row1, col1);
										}
									} 
								}
								//after flipping the card we show the board then ask replace or toss
								if (j == 0)
									displayStars(stars1);
								else
									displayStars(stars2);
								System.out.print("\nReplace this card(0) or toss(1)? ");
								decision = user.nextInt();
								//if replace
								if (decision == 0) {
									//to make the switch we need a third variable 
									char temp = deck1.topDiscard();
									//discard the card
									deck1.switch_with_discard(players[j].cardAt(row1, col1));
									//set the space to the discard card
									players[j].setTo(row1, col1, temp);
									if (j == 0) {
										stars1[row1][col1] = players[j].cardAt(row1, col1);
									}
									else {
										stars2[row1][col1] = players[j].cardAt(row1, col1);
									}
									
								}
							}

						}
						//if toss keep the card on the discard pile
					}
					if (deck1.emptyDeck()) {
						//if the deck cards run out
						System.out.println("\nDeck cards have run out! We're flipping all the cards open"
								+ " and calculating the score.");
						for (int r = 0; r < 3; r++) {
							for (int c = 0; c < 3; c++) {
								players[0].flip(r, c);
							}
						}
					}
				}
			}
		}
		if (deck1.emptyDeck() == false) {
			if (players[0].allTurned())
				System.out.println("\n"+ players[0].getName() + " has turned over all cards.");
			else if (players[1].allTurned()) {
				System.out.println("\n"+ players[1].getName() + " has turned over all cards.");
			}
		}
		//calculating then announcing the winner
		System.out.println("Time to calculate points! Here are your boards with all cards turned over");
		System.out.println("\n" + players[0].getName() + "\n----------------------");
		players[0].displayBoard();
		System.out.println("\n\n" + players[1].getName() + "\n----------------------");
		players[1].displayBoard();
		System.out.println();
		System.out.println("\nFinal results:");
		System.out.println("  " + players[0].getName() + " scored " + players[0].calculatePts());
		System.out.println("  " + players[1].getName() + " scored " + players[1].calculatePts());
		//the winner
		if (players[0].calculatePts() < players[1].calculatePts())
			System.out.println("\nCONGRATULATIONS!!! The winner is " + players[0].getName());
		else if (players[0].calculatePts() > players[1].calculatePts())
			System.out.println("\nCONGRATULATIONS!!! The winner is " + players[1].getName());
		else {
			System.out.println("It's a TIE! That's anticlimactic...");
		}
		System.out.println("---------------------------------------------");
		System.out.println("Just for information sake, here are the cards remaining in the deck:\n");
		if (deck1.emptyDeck() == false)
			deck1.displayDeck();
		else
			System.out.println("nothing");
		System.out.println("\nHere are the cards in the discard pile:\n");
		deck1.displayDiscardPile();
		System.out.println("\nThat's it! Thank you for playing this game on the computer"
				+ " instead of just using real card!");
		user.close();

	}
	//static methods
	public static void displayStars(char[][] stars) {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				System.out.print(stars[r][c] + " ");
				if ((r == 0 || r == 1) && c == 2) System.out.println();
			}
		}
	}
	//make sure it's within the 3x3 board
	public static boolean validateCard(int r, int c) {
		return ((r <= 2 && r >= 0) && (c <= 2 && c >= 0));
	}
}