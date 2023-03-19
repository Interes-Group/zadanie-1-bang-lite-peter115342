package sk.stuba.fei.uim.oop.card.blue;


import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.card.*;

import java.util.ArrayList;

public  abstract class BlueCard extends Card {
    public BlueCard(String name, PlayArea playArea) {
        super(name, playArea);
        super.cardColor = CardColor.BLUE;
    }





}
