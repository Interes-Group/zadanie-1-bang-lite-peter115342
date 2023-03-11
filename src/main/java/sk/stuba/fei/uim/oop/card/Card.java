package sk.stuba.fei.uim.oop.card;
import sk.stuba.fei.uim.oop.player.Player;

public abstract class Card {
    protected String name;

    protected boolean whichColor;

    public Card(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public abstract boolean isPlayable();

    public abstract boolean isPlayable(int cardIndex);

    public void playCard(Player player){
        System.out.println("''' Player "+player.getName() + " has chosen to play: "+ this.name +"! '''");
    }

    public boolean isWhichColor() {
        return whichColor;
    }

    public void setWhichColor(boolean whichColor) {
        this.whichColor = whichColor;
    }
}
