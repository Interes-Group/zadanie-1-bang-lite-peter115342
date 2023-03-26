package sk.stuba.fei.uim.oop.bang;

import sk.stuba.fei.uim.oop.card.blue.*;
import sk.stuba.fei.uim.oop.card.brown.*;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class BangFlow {
    private static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    private final Player[] players;
    private int playersCurrent;
    private int numberOfPlayers;
    private final ArrayList<Card> cardDeck;
    private final ArrayList<Card> usedDeck = new ArrayList<>();

    public BangFlow() {
        printBang();

        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            numberOfPlayers = ZKlavesnice.readInt("||| Enter number of players (2-4): |||");
            if (numberOfPlayers < 2 || numberOfPlayers > 4) {
                System.out.println("!!! You entered the wrong number! !!!\n Minimum 2 and maximum 4 players can play this game \n");
            }
        }
        cardDeck = new ArrayList<>();
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("||| Enter the name for player " + (i + 1) + ": |||"));
        }


        this.startGame();
    }

    private void startGame() {
        System.out.println(" ''' The GAME HAS STARTED... ''' \n");

        genCards();

        Collections.shuffle(cardDeck);

        drawStartCards();

        while (getNumberOfPlayersPlaying() > 1) {

            checkAlivePlayers();
            this.playerCounterReset();
            while (playersCurrent < numberOfPlayers - 1) {
                if (checkWin()) {
                    return;
                }
                this.playerCounterPlus();

                if (players[playersCurrent].isLiving()) {
                    System.out.println("''' It is " + players[playersCurrent].getName() + "'s turn. '''");
                    new Dynamite().checkDynamiteEffect(players, playersCurrent, usedDeck, numberOfPlayers);
                    if (new Prison().checkPrisonEffect(players, playersCurrent, usedDeck)) {
                        continue;
                    }
                    players[playersCurrent].drawCards(cardDeck);

                    if (checkWin()) {
                        return;
                    }

                    do {

                        if (cardDeck.size() - 1 < 2) {
                            moveDeck();
                        }
                        System.out.println("Cards in deck: " + (cardDeck.size()));
                        System.out.println("Cards in used deck: " + (usedDeck.size()));
                        players[playersCurrent].printPlayerCards();
                        if (checkWin()) {
                            return;
                        }

                    } while (playTurn(playersCurrent, numberOfPlayers));
                    while (players[playersCurrent].getNumberCards() > players[playersCurrent].getLives() - 1) {
                        players[playersCurrent].discardRandomCard(usedDeck, players[playersCurrent].getPlayerCards());
                    }
                }
                if (checkWin()) {
                    return;
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

    private boolean playTurn(int playersCurrent, int numOfPlayers) {
        Card card;
        int playerIndex;

        int cardInd = players[playersCurrent].pickACard();
        if (cardInd == -1) {
            return false;
        }
        card = players[playersCurrent].getPlayerCards().get(cardInd);
        if (card.canUseOnEnemy() && !(card instanceof Indians)) {
            while (true) {
                playerIndex = (ZKlavesnice.readInt("Choose who to use this card on: Or Enter 0 if you want to play a different card.") - 1);
                if (playerIndex == -1) {
                    return true;
                } else if (playerIndex == playersCurrent) {
                    System.out.println("You cannot use this card on yourself!");
                } else if (playerIndex > numOfPlayers - 1 || playerIndex < 0) {
                    System.out.println("Choose from the players that are currently playing!");
                } else if (!players[playerIndex].isLiving()) {
                    System.out.println("This player is dead!");
                } else if (players[playerIndex].getPlayerBlueCards().contains(card)) {
                    System.out.println("You can only lay out one card of the same type!");
                } else if (card instanceof CatBalou) {
                    if (players[playerIndex].getPlayerCards().isEmpty() && players[playerIndex].getPlayerBlueCards().isEmpty()) {
                        System.out.println("You cannot use " + card.getName() + " on " + players[playerIndex].getName() + " as he has no cards at all!");
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }

            System.out.println("''' Player " + players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
            card.playCard(players, cardDeck, usedDeck, playerIndex);
            players[playerIndex].kill();
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


    private boolean checkAlivePlayers() {
        int count = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if (players[i].getDeathFlag()) {
                if (!players[i].isLiving()) {
                    this.usedDeck.addAll(players[i].takeFromHand());
                    players[i].kill();
                    count++;
                }

            }
        }
        return count == numberOfPlayers-1;
    }

    private void moveDeck() {
        cardDeck.addAll(usedDeck);
        usedDeck.clear();
        Collections.shuffle(cardDeck);
    }

    private boolean checkWin() {
        if (checkAlivePlayers()) {
            for (Player player : players) {
                if (player.isLiving()) {
                    System.out.println("Congrats! " + player.getName() + " won the game!");
                }
            }
            return true;
        }
        return false;
    }

    private void printBang() {
        System.out.println(YELLOW_BOLD_BRIGHT + "\n" +
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
    }

    private void genCards() {
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
        for (int i = 0; i < 6; i++) {
            cardDeck.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            cardDeck.add(new Stagecoach());
        }
        for (int i = 0; i < 2; i++) {
            cardDeck.add(new Indians());
        }
    }

    private void drawStartCards() {
        for (Player player : players) {
            player.drawCards(this.cardDeck, 4);
        }
    }
}
