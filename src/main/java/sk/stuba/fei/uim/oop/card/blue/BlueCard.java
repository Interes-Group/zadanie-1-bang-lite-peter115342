package sk.stuba.fei.uim.oop.card.blue;
import sk.stuba.fei.uim.oop.card.*;


public abstract class BlueCard extends Card {
    public BlueCard(String name) {
        super(name);
        super.cardColor = CardColor.BLUE;
    }
}
