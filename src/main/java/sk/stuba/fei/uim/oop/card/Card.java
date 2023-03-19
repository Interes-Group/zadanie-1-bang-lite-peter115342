package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.playarea.PlayArea;

import java.util.ArrayList;
import java.util.Random;

public abstract class Card {
    protected String name;
    protected PlayArea playArea;
    protected CardColor cardColor;
    protected Random rand = new Random();
    public Card(String name, PlayArea playArea) {
        this.playArea = playArea;
        this.name = name;
    }

    public Random getRand() {
        return rand;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isPlayable();

    public abstract boolean isPlayable(int cardIndex);


    public CardColor getCardColor() {
        return cardColor;
    }

    public void playCard(Player player,ArrayList<Card> deck) {
        if (this.getCardColor().equals(CardColor.BLUE)) {
            player.getPlayerBlueCards().add(this);
        }
        player.getPlayerCards().remove(this);
    }
    public abstract boolean canUseOnEnemy();
    public void moveCard(ArrayList<Card> source, ArrayList<Card> dest) {
        int tempIndex;
        if (source.contains(this)) {
            tempIndex = source.size() - 1;
            dest.add(this);
            source.remove(tempIndex);
        }
    }


    @Override                                   // override to the equals() function, so I can search for objects as a whole instead of a reference
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Card method = (Card) object;
            if (this.name == method.getName()) {
                result = true;
            }
        }
        return result;
    }

    public boolean blueCardEffect(Player player){
        return false;
    }
}
