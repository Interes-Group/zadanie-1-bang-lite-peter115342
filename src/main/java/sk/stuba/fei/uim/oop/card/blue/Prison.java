package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";

    public Prison(PlayArea playArea) {
        super(CARD_NAME, playArea);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }
    @Override
    public boolean canUseOnPlayer(){
        return true;
    }
    @Override
    public boolean isPlayable(int cardIndex) {
        return true;
    }
    @Override
    public void playCard(Player player){
        super.playCard(player);

    }
}