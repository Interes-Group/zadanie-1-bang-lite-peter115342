package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Beer extends BrownCard {
    private static final String CARD_NAME = "Beer";

    public Beer() {
        super(CARD_NAME);
    }


    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean canUseOnEnemy() {
        return false;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return true;
    }

    @Override
    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer) {
        super.playCard(players, cardDeck, usedDeck, currentPlayer);
        players[currentPlayer].addLife();
        System.out.println("!!! " + players[currentPlayer].getName() + " got an extra life! !!!");
    }

    @Override
    // override to the equals() function, so I can search for objects as a whole instead of a reference
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return false;

        } else if (object instanceof Beer) {
            result = true;
        }
        return result;
    }
}
