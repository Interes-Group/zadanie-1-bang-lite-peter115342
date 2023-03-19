package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

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
    public boolean canUseOnEnemy(){
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

    @Override
    public boolean blueCardEffect(Player player){
        return  this.rand.nextInt(4) == 3;
    }
}
