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
    public void playCard(Player player, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck) {
        super.playCard(player, cardDeck, usedDeck);
        if(player.getPlayerBlueCards() == null){
            shoot(player);
        }
        else {
            if (player.getPlayerCards().contains(new Missed())) {
                System.out.println("!!! " + player.getName() + " has used the Missed card !!!");
                player.getPlayerCards().remove(new Missed());
            } else if (player.getPlayerBlueCards().contains(new Barrel())) {

                if (player.getRand().nextInt(4) == 3) {
                    System.out.println("!!! " + player.getName() + "hid behind a barrel! !!!");
                } else {
                    shoot(player);
                }
            } else {
                shoot(player);
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


