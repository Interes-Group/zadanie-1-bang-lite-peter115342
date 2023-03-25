package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite() {
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

    }

    @Override
    public boolean blueCardEffect(Player player) {
        if (player.getRand().nextInt(8) == 7) {
            for (int i = 0; i < 3; i++) {
                player.takeLife();
            }
            return true;
        }
        return false;
    }

    @Override
    // override to the equals() function, so I can search for objects as a whole instead of a reference
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return false;

        } else if (object instanceof Dynamite) {
            result = true;
        }
        return result;
    }

    public void checkDynamiteEffect(Player[] players, int playersCurrent,ArrayList<Card> usedDeck, int numberOfPLayersPLaying) {
        Card card;
        int cardInd;

        if (players[playersCurrent].getPlayerBlueCards().contains(new Dynamite())) {
            cardInd = players[playersCurrent].getPlayerBlueCards().indexOf(new Dynamite());
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if (card.blueCardEffect(players[playersCurrent])) {
                System.out.println("BOOM!\n" + players[playersCurrent].getName() + " got blown up!");
                players[playersCurrent].discardCard(usedDeck, players[playersCurrent].getPlayerBlueCards(), cardInd);
            } else {
                int passToPrev = 1;
                while (true) {
                    if (playersCurrent - passToPrev >= 0 && players[playersCurrent - passToPrev].isLiving()) {
                        players[playersCurrent - passToPrev].getPlayerBlueCards().add(card);
                        players[playersCurrent].getPlayerBlueCards().remove(card);
                        break;

                    }
                    if (players[numberOfPLayersPLaying - passToPrev].isLiving()) {
                        players[numberOfPLayersPLaying - passToPrev].getPlayerBlueCards().add(card);
                        players[playersCurrent].getPlayerBlueCards().remove(card);
                        break;
                    }
                    passToPrev++;
                }

            }

        }

    }
}
