package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.card.*;

import java.util.ArrayList;
import java.util.Random;

import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class Player {
    private final String name;
    private int lives;
    private final Random rand = new Random();
    private final ArrayList<Card> cards;

    private final ArrayList<Card> blueCards = new ArrayList<>();     ///these are the cards that the player has laid out in front of him

    private  boolean deathFlag ;



    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.lives = 4;
        this.deathFlag = false;

    }
    public String getName() {
        return name;
    }
    public Random getRand() {
        return rand;
    }
    public int getLives() {
        return lives;
    }
    public ArrayList<Card> getPlayerBlueCards() {
        return blueCards;
    }
    public boolean isLiving() {
        return this.lives > 0;
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
    public boolean getDeathFlag() {
        return deathFlag;
    }
    public void kill(){
        if(!this.isLiving()) {
            if (!this.getDeathFlag()) {
                System.out.println("!!!   " + this.getName() + " is  dead! !!! ");
            }
            this.deathFlag = true;}

    }
    public ArrayList<Card> takeFromHand() {
        ArrayList<Card> takenCards = new ArrayList<>();
        takenCards.addAll(this.cards);
        takenCards.addAll(this.blueCards);
        this.cards.clear();
        this.blueCards.clear();
        return takenCards;
    }


    public void drawCards(ArrayList<Card> deck) {
        drawCards(deck, 2);
    }

    public void drawCards(ArrayList<Card> deck, int numberOfCards) {
        if (!deck.isEmpty()) {
            for (int i = 0; i < numberOfCards; i++) {
                this.cards.add(deck.get(deck.size() - 1));
                System.out.println(this.getName() + " drew a " + deck.get(deck.size() - 1).getName() + " card!");
                deck.remove(deck.size() - 1);
                if (deck.size() - 1 < numberOfCards) {
                    System.out.println("There are no more cards to draw!");
                    return;
                }
            }
        }

    }

    public int getNumberCards() {
        return (this.cards.size() - 1);
    }

    public void discardRandomCard(ArrayList<Card> usedDeck, ArrayList<Card> playerCards) {
        if (!playerCards.isEmpty() && playerCards.size() > 1) {
            int randCardInd = this.rand.nextInt(playerCards.size() - 1);
            Card card = playerCards.get(randCardInd);
            System.out.println(card.getName() + " has been discarded from " + this.getName() + "'s cards!");
            playerCards.remove(randCardInd);
            usedDeck.add(card);
        }
        if(playerCards.size() == 1){
            Card card = playerCards.get(0);
            System.out.println(card.getName() + " has been discarded from " + this.getName() + "'s cards!");
            playerCards.remove(0);
            usedDeck.add(card);
        }
    }

    public void discardCard(ArrayList<Card> usedDeck, ArrayList<Card> playerCard, int cardInd) {
        if (!playerCard.isEmpty()) {
            Card card = playerCard.get(cardInd);
            System.out.println(card.getName() + " has been discarded from " + this.getName() + "'s cards!");
            playerCard.remove(cardInd);
            usedDeck.add(card);
        }
    }

    public void printPlayerCards() {
        if (!this.getPlayerCards().isEmpty()) {
            System.out.println(this.getName() + " has " + this.getLives() + " lives and these cards on hand: ");
            for (int i = 0; i < this.getPlayerCards().size(); i++) {
                if (this.getPlayerCards().get(i).isPlayable()) {
                    System.out.println((i + 1) + ". " + this.getPlayerCards().get(i).getName());
                } else {
                    System.out.println((i + 1) + ". " + this.getPlayerCards().get(i).getName() + "(Not playable)");
                }
            }
            if (!this.getPlayerBlueCards().isEmpty()) {
                System.out.println(this.getName() + " has  these blue cards on the table: ");

                for (int i = 0; i < this.getPlayerBlueCards().size(); i++) {
                    System.out.println((i + 1) + ". " + this.getPlayerBlueCards().get(i).getName());
                }
            }
        } else {
            System.out.println(this.getName() + " has " + this.getLives() + " lives and no cards on hand,his turn ends!  ");

        }
    }

    public int pickACard() {
        if (!this.getPlayerCards().isEmpty()) {
            while (true) {
                int whichCard = ((ZKlavesnice.readInt("''' Choose which card you want to play! Enter 0 if you want your turn to end. '''")) - 1);
                if (whichCard > this.getNumberCards() || whichCard < -1) {
                    System.out.println("Choose from the cards that " + this.getName() + " has on hand!");
                    continue;
                }
                if (whichCard > -1) {
                    if (!this.getPlayerCards().get(whichCard).isPlayable()) {
                        System.out.println(this.getPlayerCards().get(whichCard).getName() + " Is not playable, please choose a different card");
                        continue;
                    }
                }
                return whichCard;

            }
        }
        return -1;
    }


}
