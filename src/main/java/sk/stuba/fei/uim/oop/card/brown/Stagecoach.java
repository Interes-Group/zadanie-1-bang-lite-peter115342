package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME = "Stagecoach";


    public Stagecoach() {
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
        players[currentPlayer].drawCards(cardDeck);
    }

    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return false;

        } else if (object instanceof Stagecoach) {
            result = true;
        }
        return result;
    }


}
