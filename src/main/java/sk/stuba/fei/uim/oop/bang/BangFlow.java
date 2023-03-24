package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.card.blue.*;
import sk.stuba.fei.uim.oop.card.brown.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class BangFlow {
    private final Player[] players;
    private int playersCurrent;

    private ArrayList<Card> cardDeck;
    private int max = Integer.MAX_VALUE;
    private ArrayList<Card> usedDeck = new ArrayList<Card>();
    private int numberOfPlayers = 0;
    private boolean winFlag = false;

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public ArrayList<Card> getUsedDeck() {
        return usedDeck;
    }

    public BangFlow() {
        System.out.println("\n" +
                " ▄▄▄▄▄▄▄▄▄▄   ▄▄▄▄▄▄▄▄▄▄▄  ▄▄        ▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄ \n" +
                "▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░░▌      ▐░▌▐░░░░░░░░░░░▌▐░▌\n" +
                "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌░▌     ▐░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░▌\n" +
                "▐░▌       ▐░▌▐░▌       ▐░▌▐░▌▐░▌    ▐░▌▐░▌          ▐░▌\n" +
                "▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄█░▌▐░▌ ▐░▌   ▐░▌▐░▌ ▄▄▄▄▄▄▄▄ ▐░▌\n" +
                "▐░░░░░░░░░░▌ ▐░░░░░░░░░░░▌▐░▌  ▐░▌  ▐░▌▐░▌▐░░░░░░░░▌▐░▌\n" +
                "▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀█░▌▐░▌   ▐░▌ ▐░▌▐░▌ ▀▀▀▀▀▀█░▌▐░▌\n" +
                "▐░▌       ▐░▌▐░▌       ▐░▌▐░▌    ▐░▌▐░▌▐░▌       ▐░▌ ▀ \n" +
                "▐░█▄▄▄▄▄▄▄█░▌▐░▌       ▐░▌▐░▌     ▐░▐░▌▐░█▄▄▄▄▄▄▄█░▌ ▄ \n" +
                "▐░░░░░░░░░░▌ ▐░▌       ▐░▌▐░▌      ▐░░▌▐░░░░░░░░░░░▌▐░▌\n" +
                " ▀▀▀▀▀▀▀▀▀▀   ▀         ▀  ▀        ▀▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀ \n");
        System.out.println("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n");

        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            numberOfPlayers = ZKlavesnice.readInt("||| Enter number of players (2-4): |||");
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.println("!!! You entered the wrong number! !!!\n Minimum 2 and maximum 4 players can play this game \n");
            }
        }
        cardDeck = new ArrayList<Card>();
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("||| Enter the name for player " + (i + 1) + ": |||"));
        }


        this.startGame();
    }

    private void startGame() {
        System.out.println(" ''' The GAME HAS STARTED... ''' \n");

        for (int i = 0; i < 2; i++) {
            cardDeck.add(new Barrel());
        }
        for (int i = 0; i < 3; i++) {
            cardDeck.add(new Prison());
        }
        cardDeck.add(new Dynamite());

        for (int i = 0; i < 30; i++) {
            cardDeck.add(new Bang());
        }

        for (int i = 0; i < 15; i++) {
            cardDeck.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            cardDeck.add(new Beer());
        }
        for (int i = 0; i < 2; i++) {
            cardDeck.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            cardDeck.add(new Stagecoach());
        }
        for (int i = 0; i < 2; i++) {
            cardDeck.add(new Indians());
        }


        Collections.shuffle(cardDeck);

        for (int i = 0; i < getNumberOfPlayersPlaying(); i++) {
            players[i].drawCards(this.cardDeck, 4);

        }
        while (getNumberOfPlayersPlaying() > 1) {

            checkAlivePlayers(players);
            System.out.println(" ''' The players  playing  are: '''");
            for (int i = 0; i < getNumberOfPlayersPlaying(); i++) {
                if (!players[i].isLiving()) {
                    continue;
                }
                System.out.println("Player " + (i + 1) + ". " + players[i].getName());

            }
            this.playerCounterReset();
            while (playersCurrent < getNumberOfPlayersPlaying() - 1) {
                if (winFlag) {
                    break;
                }
                this.playerCounterPlus();

                if (players[playersCurrent].isLiving()) {
                    System.out.println("''' It is " + players[playersCurrent].getName() + "'s turn. '''");
                    players[playersCurrent].drawCards(cardDeck);
                    checkDynamiteEffect(players, playersCurrent);
                    if (checkPrisonEffect(players, playersCurrent)) {
                        continue;
                    }
                    if (!checkAlivePlayers(players)) {
                        winFlag = true;
                        break;
                    }

                    do {

                        if (cardDeck.size() - 1 < 2) {
                            moveDeck(usedDeck, cardDeck);
                        }
                        System.out.println("Cards in deck: " + (cardDeck.size()));
                        System.out.println("Cards in used deck: " + (usedDeck.size()));

                        players[playersCurrent].printPlayerCards();
                        if (!checkAlivePlayers(players)) {
                            winFlag = true;
                            break;
                        }

                    } while (playTurn(players, playersCurrent, getNumberOfPlayersPlaying()));
                    while (players[playersCurrent].getNumberCards() > players[playersCurrent].getLives() - 1) {
                        players[playersCurrent].discardRandomCard(usedDeck, players[playersCurrent].getPlayerCards());
                    }
                }


            }
            // this.playerCounterReset();
            // break;
            if (!winFlag) {
                if (!checkAlivePlayers(players)) {
                    winFlag = true;
                    break;
                }
            }
        }

    }

    private int getNumberOfPlayersPlaying() {
        int counter = 0;
        for (Player player : this.players) {
            if (player.isLiving()) {
                counter++;
            }
        }
        return counter;
    }

    private void playerCounterPlus() {
        this.playersCurrent++;
    }

    private void playerCounterReset() {
        this.playersCurrent = -1;
    }

    private boolean playTurn(Player[] players, int playersCurrent, int numOfPlayers) {
        Card card;
        int playerIndex;
        int cardInd = players[playersCurrent].pickACard();
        if (cardInd == -1) {
            return false;
        }
        card = players[playersCurrent].getPlayerCards().get(cardInd);
        if (card.canUseOnEnemy() && !(card instanceof Indians)) {
            while (true) {
                playerIndex = (ZKlavesnice.readInt("Choose who to use this card on:") - 1);
                if (playerIndex == playersCurrent) {
                    System.out.println("You cannot use this card on yourself!");
                    continue;
                } else if (playerIndex > numOfPlayers - 1 || playerIndex < 0) {
                    System.out.println("Choose from the players that are currently playing!");
                    continue;
                } else if (!players[playerIndex].isLiving()) {
                    System.out.println("This player is dead!");
                } else if (players[playerIndex].getPlayerBlueCards().contains(card)) {
                    System.out.println("You can only lay out one card of the same type!");
                } else {
                    break;
                }
            }
            System.out.println("''' Player " + players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
            card.playCard(players, cardDeck, usedDeck, playerIndex);

            players[playersCurrent].getPlayerCards().remove(card);
        } else {
            if (players[playersCurrent].getPlayerBlueCards().contains(card)) {
                System.out.println("You can only lay out one card of the same type!");
                return true;
            }
            System.out.println("''' Player " + players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
            card.playCard(players, cardDeck, usedDeck, playersCurrent);

            players[playersCurrent].getPlayerCards().remove(card);

        }
        if (card.getCardColor() != CardColor.BLUE) {
            usedDeck.add(card);
        }
        System.out.println("\n");
        return true;

    }


    private boolean checkAlivePlayers(Player[] players) {
        int count = 0;
        for (int i = 0; i < getNumberOfPlayersPlaying(); i++) {
            if (!players[i].isLiving()) {
                count++;
                this.usedDeck.addAll(players[i].takeFromHand());
                System.out.println("!!!   " + players[i].getName() + " is  dead! !!! ");
            }
            if (count == getNumberOfPlayersPlaying() - 1) {
                for (int j = 0; j < getNumberOfPlayersPlaying(); j++)
                    if (players[j].isLiving()) {
                        System.out.println(players[j].getName() + " won the game, congrats! ");
                        return false;
                    }
            }
        }
        return true;
    }

    private void moveDeck(ArrayList<Card> deckSrc, ArrayList<Card> deckDest) {
        deckDest.addAll(deckSrc);
        deckSrc.clear();
        Collections.shuffle(deckDest);
    }

    private boolean checkPrisonEffect(Player[] players, int playersCurrent) {
        Card card;
        int cardInd;

        if (players[playersCurrent].getPlayerBlueCards().contains(new Prison())) {
            cardInd = players[playersCurrent].getPlayerBlueCards().indexOf(new Prison());
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if (card.blueCardEffect(players[playersCurrent])) {
                System.out.println(players[playersCurrent].getName() + " is locked up in a Prison, his turn is skipped!");
                players[playersCurrent].discardCard(usedDeck, players[playersCurrent].getPlayerBlueCards(), cardInd);

                return true;
            }
            System.out.println(players[playersCurrent].getName() + " escaped from the Prison!");
            players[playersCurrent].discardCard(usedDeck, players[playersCurrent].getPlayerBlueCards(), cardInd);

        }
        return false;
    }

    private void checkDynamiteEffect(Player[] players, int playersCurrent) {
        Card card;
        int cardInd;

        if (players[playersCurrent].getPlayerBlueCards().contains(new Dynamite())) {
            cardInd = players[playersCurrent].getPlayerBlueCards().indexOf(new Dynamite());
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if (card.blueCardEffect(players[playersCurrent])) {
                System.out.println("BOOM!\n" + players[playersCurrent].getName() + " got blown up!");
                players[playersCurrent].discardCard(usedDeck, players[playersCurrent].getPlayerBlueCards(), cardInd);
            } else {
                if (playersCurrent - 1 >= 0) {
                    players[playersCurrent - 1].getPlayerBlueCards().add(card);
                    players[playersCurrent].getPlayerBlueCards().remove(card);

                } else {
                    players[getNumberOfPlayersPlaying() - 1].getPlayerBlueCards().add(card);
                    players[playersCurrent].getPlayerBlueCards().remove(card);
                }


            }

        }

    }
}
