package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.card.brown.Stagecoach;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";

    public Prison() {
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
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return result;

        } else if (object instanceof Prison) {
            result = true;
        }
        return result;
    }

    public  boolean checkPrisonEffect(Player[] players, int playersCurrent, ArrayList<Card> usedDeck) {
        Card card;
        int cardInd;

        if (players[playersCurrent].getPlayerBlueCards().contains(new Prison())) {
            cardInd = players[playersCurrent].getPlayerBlueCards().indexOf(new Prison());
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if (!card.blueCardEffect(players[playersCurrent])) {
                System.out.println(players[playersCurrent].getName() + " is locked up in a Prison, his turn is skipped!");
                players[playersCurrent].discardCard(usedDeck, players[playersCurrent].getPlayerBlueCards(), cardInd);

                return true;
            }
            System.out.println(players[playersCurrent].getName() + " escaped from the Prison!");
            players[playersCurrent].discardCard(usedDeck, players[playersCurrent].getPlayerBlueCards(), cardInd);

        }
        return false;
    }
}
