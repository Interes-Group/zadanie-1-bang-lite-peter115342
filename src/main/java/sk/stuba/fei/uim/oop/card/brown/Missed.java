package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Missed extends BrownCard {
    private static final String CARD_NAME = "Missed!";

    public Missed() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable() {
        return false;
    }
    @Override
    public boolean canUseOnEnemy(){
        return false;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return false;
    }
    @Override
    public void playCard(Player player, ArrayList<Card> deck){
        super.playCard(player,deck);
    }
}
