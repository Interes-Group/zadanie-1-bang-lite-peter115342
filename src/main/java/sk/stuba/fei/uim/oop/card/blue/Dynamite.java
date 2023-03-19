package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public class Dynamite extends BlueCard {
    private static final String CARD_NAME = "Dynamite";

    public Dynamite() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean canUseOnEnemy() {
        return false;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return true;
    }

    @Override
    public void playCard(Player player, ArrayList<Card> deck) {
        super.playCard(player, deck);

    }

    @Override
    public boolean blueCardEffect(Player player) {
        if (this.rand.nextInt(8) == 7) {
            for (int i = 0; i < 3;i++) {
                player.takeLife();

            }
            return true;
        }
        return false;
    }
}
