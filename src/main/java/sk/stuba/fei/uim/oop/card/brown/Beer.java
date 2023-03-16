package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;

public class Beer extends BrownCard {
    private static final String CARD_NAME = "Beer";

    public Beer(PlayArea playArea) {
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
        player.addLife();
        System.out.println("!!! "+player.getName()+" got an extra life! !!!");
    }
}
