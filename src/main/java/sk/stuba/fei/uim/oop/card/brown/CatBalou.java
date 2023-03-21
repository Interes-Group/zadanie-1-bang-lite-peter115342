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
    public void playCard(Player[] players, ArrayList<Card> cardDeck, ArrayList<Card> usedDeck, int currentPlayer) {
        super.playCard(players, cardDeck, usedDeck, currentPlayer);
        if (players[currentPlayer].getPlayerCards().isEmpty() && players[currentPlayer].getPlayerBlueCards().isEmpty()) {
            System.out.println("You cannot use " + this.getName() + " on " + players[currentPlayer].getName() + " as he has no cards!");
        }
        while (true) {
            int whichList = ZKlavesnice.readInt("Enter 1 to discard a card from players hand or \n enter 2 to discard one of his blue cards");
            if (whichList == 1 && !players[currentPlayer].getPlayerCards().isEmpty()) {
                players[currentPlayer].discardRandomCard(usedDeck, players[currentPlayer].getPlayerCards());
                break;
            }
            if (whichList == 2 && !players[currentPlayer].getPlayerBlueCards().isEmpty()) {
                players[currentPlayer].discardRandomCard(usedDeck, players[currentPlayer].getPlayerBlueCards());
                break;
            }
            if (whichList <1 || whichList >2) {
                System.out.println("Please choose out of the two options!");
                continue;
            }
            if (players[currentPlayer].getPlayerCards().isEmpty()) {
                System.out.println(players[currentPlayer].getName() + " has no blue cards!");
                continue;
            }
            if (players[currentPlayer].getPlayerBlueCards().isEmpty()) {
                System.out.println(players[currentPlayer].getName()+ " has no  blue cards!");
            }
        }
    }
}
