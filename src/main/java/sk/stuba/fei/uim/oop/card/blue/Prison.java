package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Prison extends BlueCard {
    private static final String CARD_NAME = "Prison";

    public Prison( ) {
        super(CARD_NAME );
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
    public void playCard(Player player, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck){
        super.playCard(player, cardDeck, usedDeck);

    }

    @Override
    public boolean blueCardEffect(Player player){
        return  player.getRand().nextInt(4) == 3;
    }
}
