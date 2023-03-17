package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;

public class Missed extends BrownCard {
    private static final String CARD_NAME = "Missed!";

    public Missed(PlayArea playArea) {
        super(CARD_NAME, playArea);
    }

    @Override
    public boolean isPlayable() {
        return false;
    }
    @Override
    public boolean canUseOnPlayer(){
        return false;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return false;
    }
    @Override
    public void playCard(Player player){
        super.playCard(player);
    }
}
