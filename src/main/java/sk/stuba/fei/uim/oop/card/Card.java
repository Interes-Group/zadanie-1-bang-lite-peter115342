package sk.stuba.fei.uim.oop.card;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public abstract class Card {
    protected String name;
    protected CardColor cardColor;

    public Card(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public abstract boolean isPlayable();

    public abstract boolean isPlayable(int cardIndex);


    public CardColor getCardColor() {
        return cardColor;
    }

    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer) {
        if (this.getCardColor().equals(CardColor.BLUE)) {
            players[currentPlayer].getPlayerBlueCards().add(this);
        }
    }

    public abstract boolean canUseOnEnemy();
    public boolean blueCardEffect(Player player) {
        return false;
    }


}
