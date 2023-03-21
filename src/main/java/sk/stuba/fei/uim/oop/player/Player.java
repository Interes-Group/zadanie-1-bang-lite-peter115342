package sk.stuba.fei.uim.oop.player;
import sk.stuba.fei.uim.oop.card.*;
import java.util.ArrayList;
import java.util.Random;

public class Player {
    private final  String name;
    private int lives;
    private Random rand = new Random();
    private ArrayList<Card> cards;



    private ArrayList<Card> blueCards = new ArrayList<Card>();     ///these are the cards that the player has laid out in front of him

    public Random getRand() {
        return rand;
    }
    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.lives = 4;

    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public ArrayList<Card> getPlayerBlueCards() {
        return blueCards;
    }

    public void setBlueCards(ArrayList<Card> blueCards) {
        this.blueCards = blueCards;
    }

    public boolean isLiving() {
        return this.lives >0;
    }
    public boolean takeLife() {
        this.lives--;
        return this.lives < 1 ;
    }
    public void addLife() {
        this.lives++;
    }

    public ArrayList<Card> getPlayerCards() {
        return this.cards;
    }

    public ArrayList<Card> takeFromHand() {
        ArrayList<Card> takenCards = new ArrayList<>(this.cards);
        this.cards.clear();
        return takenCards;
    }

    public ArrayList<Card> getPlayableCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : this.cards){
            if(card.isPlayable()){
                cards.add(card);
            }
        }
        return cards;
    }

    public void drawCards(ArrayList<Card> deck) {
        drawCards(deck, 2);
    }

    public  void drawCards(ArrayList<Card> deck, int numberOfCards){
        for (int i = 0; i <numberOfCards; i++) {
            this.cards.add(deck.get(deck.size()-1));
            System.out.println(this.getName()+" drew a "+ deck.get(deck.size()-1).getName() + " card!" );
            deck.remove(deck.size()-1);
        }
    }
    public int getNumberCards(){
        return (this.cards.size() - 1);
    }
    public void discardRandomCard(ArrayList<Card> usedDeck, ArrayList<Card> playerCards) {
        int randCardInd = this.rand.nextInt(playerCards.size()-1);
        Card card = cards.get(randCardInd);
        System.out.println( card.getName() + " has been discarded from " + this.getName()+ "'s cards!" );
        cards.remove(randCardInd);
        usedDeck.add(card);
    }
    public void discardCard(ArrayList<Card> usedDeck, ArrayList<Card> playerCard,int cardInd){
        Card card = cards.get(cardInd);
        System.out.println( card.getName() + " has been discarded from " + this.getName()+ "'s cards!" );
        cards.remove(cardInd);
        usedDeck.add(card);
    }
}
