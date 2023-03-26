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
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null) {
            return false;

        } else if (object instanceof Dynamite) {
            result = true;
        }
        return result;
    }

    public void checkDynamiteEffect(Player[] players, int playerCurrent,ArrayList<Card> usedDeck, int numberOfPLayersPLaying) {
        Card card;
        int cardInd;

        if (players[playerCurrent].getPlayerBlueCards().contains(new Dynamite())) {
            cardInd = players[playerCurrent].getPlayerBlueCards().indexOf(new Dynamite());
            card = players[playerCurrent].getPlayerBlueCards().get(cardInd);
            if (card.blueCardEffect(players[playerCurrent])) {
                System.out.println("BOOM!\n" + players[playerCurrent].getName() + " got blown up!");
                players[playerCurrent].kill();
                players[playerCurrent].discardCard(usedDeck, players[playerCurrent].getPlayerBlueCards(), cardInd);
            } else {
                int passToPrev = 1;
                while (true) {
                    if (playerCurrent - passToPrev >= 0 && players[playerCurrent - passToPrev].isLiving()) {
                        System.out.println( players[playerCurrent].getName() + " passed on the dynamite!");
                        players[playerCurrent - passToPrev].getPlayerBlueCards().add(card);
                        players[playerCurrent].getPlayerBlueCards().remove(card);
                        break;

                    }
                    if (players[numberOfPLayersPLaying - passToPrev].isLiving()) {
                        System.out.println( players[playerCurrent].getName() + " passed on the dynamite!");
                        players[numberOfPLayersPLaying - passToPrev].getPlayerBlueCards().add(card);
                        players[playerCurrent].getPlayerBlueCards().remove(card);
                        break;
                    }
                    passToPrev++;
                }

            }

        }

    }
}
