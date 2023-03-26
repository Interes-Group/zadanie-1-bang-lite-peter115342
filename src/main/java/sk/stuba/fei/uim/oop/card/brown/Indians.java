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
        int count = 0;
        for (Player player : players) {

            if (count != currentPlayer && player.isLiving()) {
                if (!player.getPlayerCards().contains(new Bang())) {
                    System.out.println(player.getName() + " got attacked by an Indian!");
                    player.takeLife();
                } else {
                    System.out.println(player.getName() + " killed an Indian to protect himself!");
                    player.discardCard(usedDeck, player.getPlayerCards(), player.getPlayerCards().indexOf(new Bang()));
                }
            }
            count++;
        }
    }

    @Override
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
