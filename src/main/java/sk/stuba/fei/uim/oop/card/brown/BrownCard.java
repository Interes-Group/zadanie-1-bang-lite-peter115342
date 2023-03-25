package sk.stuba.fei.uim.oop.card.brown;
import sk.stuba.fei.uim.oop.card.*;

public abstract class BrownCard extends Card {
    public BrownCard(String name) {
        super(name);
        super.cardColor = CardColor.BROWN;
    }



}
