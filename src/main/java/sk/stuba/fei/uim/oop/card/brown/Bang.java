package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.blue.Barrel;
import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.bang.BangFlow;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.Random;

public class Bang extends BrownCard {
    private static final String CARD_NAME = "Bang!";
    private  Random rand = new Random();
    public Bang(PlayArea playArea) {
        super(CARD_NAME, playArea);
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
    public void playCard(Player player) {
            super.playCard(player);
            if(player.getPlayerBlueCards() == null){
                shoot(player);
            }
            else {
                if (player.getPlayerCards().contains(new Missed(playArea))) {
                    System.out.println("!!! " + player.getName() + " has used the Missed card");
                } else if (player.getPlayerBlueCards().contains(new Barrel(playArea))) {

                    if (rand.nextInt(4) == 3) {
                        System.out.println("!!! " + player.getName() + "hid behind a barrel! !!!");
                    } else {
                        shoot(player);
                    }
                } else {
                    shoot(player);
                }
            }
    }
    public int useOnPlayer(){
        return ZKlavesnice.readInt("''' Choose who do you want to use this card on! '''");

    }
    public void shoot(Player player) {
        player.takeLife();
        System.out.println("!!! " + player.getName() + " got shot! !!!");
    }
}


