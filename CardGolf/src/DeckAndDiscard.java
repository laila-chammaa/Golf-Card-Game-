// -------------------------------------------------------
// Assignment #4
// Written by: Laila Chamma'a, ID: 40107470
// For COMP 248 Section W – Winter 2019
// --------------------------------------------------------
import java.lang.Math;
public class DeckAndDiscard {
	private char[] deck;
	private char[] discardPile;
	private int nextDiscard;
	private int nextCard;
	private int numOfCards;
	
	
	//default constructor:
	public DeckAndDiscard() {
		//assigning the cards to deck!
		char[] array1 = {'A','A','A','A', '2','2','2','2', '3','3','3','3', '4','4','4','4', 
				'5','5','5','5', '6','6','6','6', '7','7','7','7', '8','8','8','8', 
				'9','9','9','9', 'T','T','T','T', 'J','J','J','J', 'Q','Q','Q','Q',
				'K','K','K','K', '?','?'};
		deck = array1;
		
		//calling the shuffle method!
		this.shuffle();
		discardPile = new char[54];
		nextDiscard = 0;
		nextCard = 53;
		numOfCards = 54;
		
	}
	public void shuffle() {
		for (int i = 0; i <= 1000; i++) {
			int rand1 = (int) (Math.random() * 53) + 1;
			int rand2 = (int) (Math.random() * 53) + 1;
			char temp = deck[rand1];
			deck[rand1] = deck[rand2];
			deck[rand2] = temp;
		}
	}
	//returns the top card (char) in the deck.
	public char pickACard() {
		//use the current index of nextCard and then update it.
		numOfCards--;
		return deck[nextCard--];
		//check if it actually reduces nextCard
	}
	
	//throws a card into the discard pile
	public void discard(char card) {
		discardPile[nextDiscard] = card;
		//updating
		nextDiscard++;
	}
	//return the card at the top of the discard pile
	public char topDiscard() {
		return discardPile[nextDiscard-1];
	}
	public void displayDeck() {
		for (int i = 0; i <= nextCard; i++) 
			System.out.print(deck[i] + "  ");
		System.out.println();
	}
	public void displayDiscardPile() {
		for (int i = 0; i < nextDiscard; i++)
			System.out.print(discardPile[i] + "  ");
		System.out.println();
	}
	public boolean emptyDeck() {
		return (numOfCards == 0);
	}
	public void switch_with_discard(char card) {
		discardPile[nextDiscard-1] = card;
	}
}
