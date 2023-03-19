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

    private  char yesOrNo  = '\n';

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public ArrayList<Card> getUsedDeck() {
        return usedDeck;
    }

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

        for (int i = 0;i <2;i++){
            cardDeck.add(new Barrel(playArea) );
        }
        for (int i = 0;i <3;i++){
            cardDeck.add(new Prison(playArea) );
        }
        cardDeck.add(new Dynamite(playArea));

        for (int i = 0;i <30;i++){
            cardDeck.add(new Bang(playArea) );
        }

        for (int i = 0;i <15;i++){
            cardDeck.add(new Missed(playArea));
        }
        for (int i = 0;i <8;i++){
            cardDeck.add(new Beer(playArea));
        }
        for (int i = 0;i <2;i++){
            cardDeck.add(new CatBalou(playArea));
        }
        for (int i = 0;i <4;i++){
            cardDeck.add(new Stagecoach(playArea) );
        }
        for (int i = 0;i <2;i++) {
            cardDeck.add(new Indians(playArea));
        }


        Collections.shuffle(cardDeck);

        for( int i = 0;i < getNumberOfPlayersPlaying();i++){
            players[i].drawCards(this.cardDeck, 4);

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
                    checkDynamiteEffect(players,playersCurrent);
                    checkPrisonEffect(players,playersCurrent);

                    while (true){
                        if (cardDeck.isEmpty()){
                            moveDeck(usedDeck,cardDeck);
                        }
                        printPlayerCard(players[playersCurrent]);
                        yesOrNo = ZKlavesnice.readChar("Do you want to play a card? (y/n) \n If you choose not to, you turn will end");
                        if (yesOrNo == 'y' ){

                            playTurn(players, playersCurrent, getNumberOfPlayersPlaying());
                        }
                        else if (yesOrNo == 'n'){
                            break;
                        }
                        else {
                            System.out.println("You need to enter (y/n)!");
                        }
                    }
                }
                while (players[playersCurrent].getNumberCards() == players[playersCurrent].getLives()){
                    players[playersCurrent].discardLastCard(usedDeck);
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
        if(card.canUseOnEnemy()){
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
            card.playCard(players[playerIndex],cardDeck);
            players[playersCurrent].getPlayerCards().remove(card);
        }
        else {
            card.playCard(players[playersCurrent],cardDeck);
            players[playersCurrent].getPlayerCards().remove(card);
        }
        System.out.println("''' Player "+ players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
        usedDeck.add(card);
        System.out.println("\n");

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
        if(!player.getPlayerBlueCards().isEmpty()) {
            System.out.println(player.getName() + " has  these blue cards on the table: ");

            for (int i = 0; i < player.getPlayerBlueCards().size(); i++) {
                System.out.println((i + 1) + ". " + player.getPlayerBlueCards().get(i).getName());
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

    private void moveDeck(ArrayList<Card> deckSrc,ArrayList<Card> deckDest ){
        deckDest.addAll(deckSrc);
        deckSrc.clear();
        Collections.shuffle(deckDest);
    }

    private void checkPrisonEffect(Player[] players, int playersCurrent){
        Card card;
        int cardInd;

        if(players[playersCurrent].getPlayerBlueCards().contains(new Prison(playArea))){
            cardInd =  players[playersCurrent].getPlayerBlueCards().indexOf(new Prison(playArea));
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if( card.blueCardEffect(players[playersCurrent])){
                System.out.println(players[playersCurrent].getName() +" is locked up in a Prison, his turn is skipped!");
                if (playersCurrent+1 <= getNumberOfPlayersPlaying()){
                    playerCounterPlus();
                }
                else{
                    playerCounterReset();
                }
            }
        }

    }
    private void checkDynamiteEffect(Player[] players, int playersCurrent){
        Card card;
        int cardInd;

        if(players[playersCurrent].getPlayerBlueCards().contains(new Dynamite(playArea))){
            cardInd =  players[playersCurrent].getPlayerBlueCards().indexOf(new Dynamite(playArea));
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if( card.blueCardEffect(players[playersCurrent])){
                System.out.println("BOOM!\n"+players[playersCurrent].getName() +" got blown up!");
            }
            else{
                if (playersCurrent+1 < getNumberOfPlayersPlaying()){
                    players[playersCurrent+1].getPlayerBlueCards().add(card);
                    players[playersCurrent].getPlayerBlueCards().remove(card);

                }
                else{
                    players[0].getPlayerBlueCards().add(card);
                    players[playersCurrent].getPlayerBlueCards().remove(card);
                }


            }

        }

    }











}
