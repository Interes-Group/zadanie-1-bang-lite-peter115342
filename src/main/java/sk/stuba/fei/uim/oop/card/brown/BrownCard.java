package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.card.*;

public abstract class BrownCard extends Card {
    public BrownCard(String name, PlayArea playArea) {
        super(name, playArea);
        super.cardColor = CardColor.BROWN;
    }



}
