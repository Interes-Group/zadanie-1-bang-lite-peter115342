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
        if(players[currentPlayer].getPlayerBlueCards() == null){
            shoot(players[currentPlayer]);
        }
        else {
            if (players[currentPlayer].getPlayerCards().contains(new Missed())) {
                System.out.println("!!! " + players[currentPlayer].getName() + " has used the Missed card !!!");
                players[currentPlayer].discardCard(usedDeck,players[currentPlayer].getPlayerCards(),players[currentPlayer].getPlayerCards().indexOf(new Missed()));
                //players[currentPlayer].getPlayerCards().remove(new Missed());
            } else if (players[currentPlayer].getPlayerBlueCards().contains(new Barrel())) {

                if (players[currentPlayer].getRand().nextInt(4) == 3) {
                    System.out.println("!!! " + players[currentPlayer].getName() + "hid behind a barrel! !!!");
                } else {
                    shoot(players[currentPlayer]);
                }
            } else {
                shoot(players[currentPlayer]);
            }
        }
    }
    @Override
    public boolean canUseOnEnemy(){
        return true;
    }
    public void shoot(Player player) {
        player.takeLife();
        System.out.println("!!! " + player.getName() + " got shot! !!!");
    }
}


