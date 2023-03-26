package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.card.blue.Barrel;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Bang extends BrownCard {
    private static final String CARD_NAME = "Bang!";

    public Bang() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return true;
    }


    @Override
    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer) {
        super.playCard(players, cardDeck, usedDeck, currentPlayer);
        if (players[currentPlayer].getPlayerBlueCards() == null) {
            shoot(players[currentPlayer]);
        } else {
            if (players[currentPlayer].getPlayerBlueCards().contains(new Barrel())) {
                if (players[currentPlayer].getPlayerBlueCards().get(players[currentPlayer].getPlayerBlueCards().indexOf(new Barrel())).blueCardEffect(players[currentPlayer])) {
                    System.out.println("!!! " + players[currentPlayer].getName() + " hid behind a barrel! !!!");
                } else {
                    shoot(players[currentPlayer]);
                }
            } else if (players[currentPlayer].getPlayerCards().contains(new Missed())) {
                System.out.println("!!! " + players[currentPlayer].getName() + " has used the Missed card !!!");
                players[currentPlayer].discardCard(usedDeck, players[currentPlayer].getPlayerCards(), players[currentPlayer].getPlayerCards().indexOf(new Missed()));
            } else {
                shoot(players[currentPlayer]);
            }
        }
    }

    private void shoot(Player player) {
        player.takeLife();
        System.out.println("!!! " + player.getName() + " got shot! !!!");
    }

    @Override
    public boolean canUseOnEnemy() {
        return true;
    }


    @Override
    // override to the equals() function, so I can search for objects as a whole instead of a reference
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return false;

        } else if (object instanceof Bang) {
            result = true;
        }
        return result;
    }
}


