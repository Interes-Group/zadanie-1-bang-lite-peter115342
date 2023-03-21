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
    public boolean canUseOnEnemy(){
        return true;
    }
    @Override
    public boolean isPlayable(int cardIndex) {
        return false;
    }
    @Override
    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer){
        super.playCard(players, cardDeck, usedDeck, currentPlayer);
        for (Player player : players) {
            if (!player.getPlayerCards().contains(new Bang())) {
                System.out.println(player.getName() + " got attacked by an Indian!");
                player.takeLife();
                players[currentPlayer].discardCard(usedDeck,players[currentPlayer].getPlayerCards(),players[currentPlayer].getPlayerCards().indexOf(new Bang()));

            }
        }
    }
}
