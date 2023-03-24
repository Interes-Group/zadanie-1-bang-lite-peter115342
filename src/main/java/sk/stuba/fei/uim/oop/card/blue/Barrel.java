package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.card.brown.Stagecoach;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;


public class Barrel extends BlueCard {
    private static final String CARD_NAME = "Barrel";

    public Barrel() {
        super(CARD_NAME);
    }

    @Override
    public boolean canUseOnEnemy() {
        return false;
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


    }

    @Override
    public boolean blueCardEffect(Player player) {
        return player.getRand().nextInt(4) == 3;
    }

    @Override
    // override to the equals() function, so I can search for objects as a whole instead of a reference
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return result;

        } else if (object instanceof Barrel) {
            result = true;
        }
        return result;
    }

}
