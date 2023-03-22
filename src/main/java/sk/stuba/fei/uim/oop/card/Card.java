package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;

public abstract class Card {
    protected String name;
    protected CardColor cardColor;

    public Card(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public abstract boolean isPlayable();

    public abstract boolean isPlayable(int cardIndex);


    public CardColor getCardColor() {
        return cardColor;
    }

    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer) {
        if (this.getCardColor().equals(CardColor.BLUE)) {
            players[currentPlayer].getPlayerBlueCards().add(this);
        }
        //players[currentPlayer].getPlayerCards().remove(this);
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
        boolean result =   false;
        if(object==null ){
            return result;

        }else if (!(object instanceof Card)  ){
            return result;
        }
        else {
        Card card = (Card) object;
        return this.name.equals(card.name);
        }

    }

    public boolean blueCardEffect(Player player){
        return false;
    }


}
