package sk.stuba.fei.uim.oop.card.brown;

import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class CatBalou extends BrownCard {
    private static final String CARD_NAME = "Cat Balou";

    public CatBalou() {
        super(CARD_NAME);
    }

    @Override
    public boolean isPlayable() {
        return true;
    }

    @Override
    public boolean canUseOnEnemy() {
        return true;
    }

    @Override
    public boolean isPlayable(int cardIndex) {
        return true;
    }

    @Override
    public void playCard(Player player, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck) {
        super.playCard(player, cardDeck, usedDeck);
        if (player.getPlayerCards().isEmpty() && player.getPlayerBlueCards().isEmpty()) {
            System.out.println("You cannot use " + this.getName() + " on " + player.getName() + " as he has no cards!");
        }
        while (true) {
            int whichList = ZKlavesnice.readInt("Enter 1 to discard a card from players hand or \n enter 2 to discard one of his blue cards");
            if (whichList == 1 && !player.getPlayerCards().isEmpty()) {
                player.discardRandomCard(usedDeck, player.getPlayerCards());
                break;
            }
            if (whichList == 2 && !player.getPlayerBlueCards().isEmpty()) {
                player.discardRandomCard(usedDeck, player.getPlayerBlueCards());
                break;
            }
            if (whichList <1 || whichList >2) {
                System.out.println("Please choose out of the two options!");
                continue;
            }
            if (player.getPlayerCards().isEmpty()) {
                System.out.println(player.getName() + " has no blue cards!");
                continue;
            }
            if (player.getPlayerBlueCards().isEmpty()) {
                System.out.println(player.getName()+ " has no  blue cards!");
            }
        }
    }
}
