package sk.stuba.fei.uim.oop.card.blue;

import sk.stuba.fei.uim.oop.card.Card;

public class Prison extends Card {
    private static final String CARD_NAME = "Prison";

    public Prison(String name) {
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
