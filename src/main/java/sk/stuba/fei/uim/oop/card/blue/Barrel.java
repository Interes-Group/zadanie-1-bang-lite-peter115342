package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;


public class Barrel extends BlueCard{
    private static final String  CARD_NAME = "Barrel";

    public Barrel(PlayArea playArea) {
        super(CARD_NAME, playArea);
    }

    @Override
    public boolean canUseOnEnemy(){
        return false;
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
    public void playCard(Player player, ArrayList<Card> deck){
        super.playCard(player,deck);


    }


}
