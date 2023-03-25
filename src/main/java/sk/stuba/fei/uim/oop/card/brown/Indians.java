package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Indians extends BrownCard {
    private static final String CARD_NAME = "Indians";

    public Indians() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean canUseOnEnemy() {
        return true;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return false;
    }

    @Override
    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer) {
        super.playCard(players, cardDeck, usedDeck, currentPlayer);

        for (int i = 0; i < players.length; i++) {
            if (!(i == currentPlayer) && players[currentPlayer].isLiving()) {
                if (!players[i].getPlayerCards().contains(new Bang())) {
                    System.out.println(players[i].getName() + " got attacked by an Indian!");
                    players[i].takeLife();
                } else {
                    System.out.println(players[i].getName() + " killed an Indian to protect himself!");
                    players[i].discardCard(usedDeck, players[i].getPlayerCards(), players[i].getPlayerCards().indexOf(new Bang()));
                }
            }
        }
    }

    @Override
    // override to the equals() function, so I can search for objects as a whole instead of a reference
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return false;

        } else if (object instanceof Indians) {
            result = true;
        }
        return result;
    }
}
