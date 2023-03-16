package sk.stuba.fei.uim.oop.player;
import sk.stuba.fei.uim.oop.card.*;
import java.util.ArrayList;

public class Player {
    private final  String name;
    private int lives;

    private ArrayList<Card> cards;



    private ArrayList<Card> blueCards = new ArrayList<Card>();     ///these are the cards that the player has laid out in front of him


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
    public void takeLife() {
        this.lives--;
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
    public  void drawCards(ArrayList<Card> deck){
        for (int i = 0; i <2; i++) {
            deck.get(deck.size() - 1).moveCard(deck, this.cards);
        }
    }
    public int getNumberCards(){
         return (this.cards.size() - 1);
    }
}
