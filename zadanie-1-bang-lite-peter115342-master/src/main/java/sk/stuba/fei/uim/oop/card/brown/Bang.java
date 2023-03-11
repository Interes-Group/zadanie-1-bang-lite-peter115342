package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang!";

    public Bang(String name) {
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
