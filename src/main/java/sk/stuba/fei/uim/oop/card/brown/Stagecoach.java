package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.bang.BangFlow;
import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Stagecoach extends BrownCard {
    private static final String CARD_NAME = "Stagecoach";



    public Stagecoach(PlayArea playArea) {
        super(CARD_NAME, playArea);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }


    @Override
    public boolean canUseOnEnemy(){
        return false;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return true;
    }
    @Override
    public void playCard(Player player, ArrayList<Card> deck){
        super.playCard(player,deck);
        player.drawCards(deck);
    }




}
