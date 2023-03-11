package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

public class Barrel extends Card {
    private static final String  CARD_NAME = "Barrel";



    public Barrel(String name) {
        super(name);
    }

    @Override
    public boolean isPlayable() {
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
