package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;

public class Stagecoach extends Card {
    private static final String CARD_NAME = "Stagcoach";

    public Stagecoach(String name) {
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
}
