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
    public void playCard(Player player, ArrayList<Card> deck){
        super.playCard(player,deck);
    }
}
