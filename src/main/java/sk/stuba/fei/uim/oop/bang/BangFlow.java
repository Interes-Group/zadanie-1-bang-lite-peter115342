package sk.stuba.fei.uim.oop.bang;
import sk.stuba.fei.uim.oop.card.blue.*;
import sk.stuba.fei.uim.oop.card.brown.*;
import sk.stuba.fei.uim.oop.playarea.PlayArea;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.card.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class BangFlow {
    private final Player[] players;
    private int playersCurrent;

    private ArrayList<Card> cardDeck;
    private  int max = Integer.MAX_VALUE;
    private ArrayList<Card> usedDeck = new ArrayList<Card>();
    private PlayArea playArea;

    public BangFlow()  {
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
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2 || numberOfPlayers > 4){
            numberOfPlayers = ZKlavesnice.readInt("||| Enter number of players (2-4): |||");
            if (numberOfPlayers < 2 || numberOfPlayers > 4){
                System.out.println("!!! You entered the wrong number! !!!\n Minimum 2 and maximum 4 players can play this game \n");
            }
        }
        cardDeck = new ArrayList<Card>();
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers ; i++){
            this.players[i] = new Player(ZKlavesnice.readString("||| Enter the name for player " + (i+1) +": |||"));
        }



        this.startGame();
    }
    private void startGame() {
        System.out.println(" ''' The GAME HAS STARTED... ''' \n");

        this.genCards(new Barrel(playArea),2,this.cardDeck);
        this.genCards(new Dynamite(playArea),1,this.cardDeck);
        this.genCards(new Prison(playArea),3,this.cardDeck);
        this.genCards(new Bang(playArea),30,this.cardDeck);
        this.genCards(new Missed(playArea),15,this.cardDeck);
        this.genCards(new Beer(playArea),8,this.cardDeck);
        this.genCards(new CatBalou(playArea),6,this.cardDeck);
        this.genCards(new Stagecoach(playArea),4,this.cardDeck);
        this.genCards(new Indians(playArea),2,this.cardDeck);


        Collections.shuffle(cardDeck);

        for( int i = 0;i < getNumberOfPlayersPlaying();i++){
                for (int j = 0; j <2;j++) {
                    players[i].drawCards(this.cardDeck);
                }
        }
        while (getNumberOfPlayersPlaying() >1){

            checkAlivePlayers(players);
            System.out.println(" ''' The players  playing  are: '''");
            for( int i = 0;i < getNumberOfPlayersPlaying();i++){
                if(!players[i].isLiving()){
                    continue;
                }
                System.out.println("Player "+(i+1)+". "+players[i].getName() );

            }
            this.playerCounterReset();
                while (playersCurrent < getNumberOfPlayersPlaying()){
                    if (players[playersCurrent].isLiving()) {
                        System.out.println("''' It is " + players[playersCurrent].getName() + "'s turn. '''");
                        players[playersCurrent].drawCards(cardDeck);
                        printPlayerCard(players[playersCurrent]);
                        playTurn(players, playersCurrent, getNumberOfPlayersPlaying());
                    }
                this.playerCounterPlus();
            }
            checkAlivePlayers(players);
           // break;

        }

    }                                           

    private int getNumberOfPlayersPlaying(){
        int counter = 0;
        for (Player player : this.players){
            if (player.isLiving()){
                counter++;
            }
        }
        return counter;
    }

    private void playerCounterPlus(){
       this.playersCurrent++;
    }
    private void playerCounterReset(){
        this.playersCurrent = 0;
    }


    private void genCards(Card card, int amount, ArrayList<Card> deck){
        for(int i = 0; i < amount;i++){
            deck.add(card);
        }
    }


    private void playTurn(Player[] players, int playersCurrent, int numOfPlayers) {
        Card card;
        int playerIndex;
        int lastCard;
        card = pickACard(players[playersCurrent]);
        if(card.canUseOnPlayer()){
            while (true) {
                playerIndex = (ZKlavesnice.readInt("Choose who to use this card on:")-1);
                if (playerIndex == playersCurrent) {
                    System.out.println("You cannot use this card on yourself!");
                    continue;
                }
                else if (playerIndex > numOfPlayers) {
                    System.out.println("Choose from the players that are currently playing!");
                    continue;
                }
                else {
                    break;
                }
            }
            card.playCard(players[playerIndex]);
        }
        else {
            card.playCard(players[playersCurrent]);
        }
        System.out.println("''' Player "+ players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
        usedDeck.add(card);
        players[playersCurrent].getPlayerCards().remove(card);
        System.out.println("\n");
        while (players[playersCurrent].getNumberCards() == players[playersCurrent].getLives()){
            lastCard = players[playersCurrent].getPlayerCards().size()-1;
            card = players[playersCurrent].getPlayerCards().get(lastCard);
             players[playersCurrent].getPlayerCards().remove(lastCard);
            usedDeck.add(card);
        }
    }
    private void printPlayerCard(Player player){
        System.out.println(player.getName()+" has "+ player.getLives()+" lives and these cards on hand: ");
        for (int i = 0; i < player.getPlayerCards().size(); i++) {
            if (player.getPlayerCards().get(i).isPlayable()) {
                System.out.println((i + 1) + ". " + player.getPlayerCards().get(i).getName());
            }
            else {
                System.out.println((i + 1) + ". " + player.getPlayerCards().get(i).getName() + "(Not playable)");
            }
        }
    }

    private Card pickACard(Player player){
        while (true) {
            int whichCard = ((ZKlavesnice.readInt("''' Choose which card you want to play! '''")) - 1);
            if (!player.getPlayerCards().get(whichCard).isPlayable()) {
                System.out.println(player.getPlayerCards().get(whichCard).getName() + " Is not playable, please choose a different card");
                continue;
            }


            return player.getPlayerCards().get(whichCard);

        }

    }

    private  void checkAlivePlayers(Player[] players){
        for( int i = 0;i < getNumberOfPlayersPlaying();i++){
            if (!players[i].isLiving()){
                this.usedDeck.addAll(players[i].takeFromHand());
                System.out.println("!!! Player "+ players[i].getName() +"is  done for! !!! ");
            }
        }
    }












}
