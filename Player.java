package vikingbattle;

import java.util.ArrayList;

public class Player
{
	String name;
	ArrayList<Card> cards, hand, playedCards;
	boolean treaty = false;
	
	public Player(String name, ArrayList<Card> cards, ArrayList<Card> hand, ArrayList<Card> playedCards)
	{
		this.name = name;
		this.cards = cards;
		this.hand = hand;
		this.playedCards = playedCards;
	}
}