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
    private  int max = Integer.MAX_VALUE;
    private ArrayList<Card> usedDeck = new ArrayList<Card>();


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
            cardDeck.add(new Barrel() );
        }
        for (int i = 0;i <3;i++){
            cardDeck.add(new Prison() );
        }
        cardDeck.add(new Dynamite());

        for (int i = 0;i <30;i++){
            cardDeck.add(new Bang() );
        }

        for (int i = 0;i <15;i++){
            cardDeck.add(new Missed());
        }
        for (int i = 0;i <8;i++){
            cardDeck.add(new Beer());
        }
        for (int i = 0;i <2;i++){
            cardDeck.add(new CatBalou());
        }
        for (int i = 0;i <4;i++){
            cardDeck.add(new Stagecoach() );
        }
        for (int i = 0;i <2;i++) {
            cardDeck.add(new Indians());
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
                    if(checkPrisonEffect(players,playersCurrent)){
                        continue;
                    }

                    while (true){

                        if (cardDeck.size()-1 <2){
                            moveDeck(usedDeck,cardDeck);
                        }
                        System.out.println("Cards in deck: "+ (cardDeck.size()));
                        System.out.println("Cards in used deck: "+ (usedDeck.size()));

                        printPlayerCard(players[playersCurrent]);
                        char yesOrNo = ZKlavesnice.readChar("Do you want to play a card? (y/n) \n If you choose not to, your turn will end");
                        if (yesOrNo == 'y' ){

                            if(!playTurn(players, playersCurrent, getNumberOfPlayersPlaying())){
                                break;
                            }

                        }
                        else if (yesOrNo == 'n'){
                            break;
                        }
                        else {
                            System.out.println("You need to enter (y/n)!");
                        }
                    }
                    while (players[playersCurrent].getNumberCards() > players[playersCurrent].getLives()-1){
                        players[playersCurrent].discardRandomCard(usedDeck,players[playersCurrent].getPlayerCards());
                    }
                }

                this.playerCounterPlus();

            }
           // this.playerCounterReset();
            checkAlivePlayers(players);
            // break;

        }
        for (int i = 0; i < getNumberOfPlayersPlaying(); i++){
            if (players[i].isLiving()){
                System.out.println(players[i].getName()+" won the game, congrats! ");
            }
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


    private boolean playTurn(Player[] players, int playersCurrent, int numOfPlayers) {
        Card card;
        int playerIndex;
        int cardInd = pickACard(players[playersCurrent]);
        if (cardInd == -1){
            return false;
        }
        card = players[playersCurrent].getPlayerCards().get(cardInd);
        if(card.canUseOnEnemy()){
            while (true) {
                playerIndex = (ZKlavesnice.readInt("Choose who to use this card on:")-1);
                if (playerIndex == playersCurrent) {
                    System.out.println("You cannot use this card on yourself!");
                    continue;
                }
                else if (playerIndex > numOfPlayers|| playerIndex < 1) {
                    System.out.println("Choose from the players that are currently playing!");
                    continue;
                }
                else {
                    break;
                }
            }
            System.out.println("''' Player "+ players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
            card.playCard(players[playerIndex],cardDeck,usedDeck );
            players[playersCurrent].getPlayerCards().remove(card);

        }
        else {
            System.out.println("''' Player "+ players[playersCurrent].getName() + " has chosen to play: " + card.getName() + "! '''");
            card.playCard(players[playersCurrent],cardDeck,usedDeck );
            players[playersCurrent].getPlayerCards().remove(card);
        }
        usedDeck.add(card);
        System.out.println("\n");
        return true;

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

    private int pickACard(Player player){
        while (true) {
            int whichCard = ((ZKlavesnice.readInt("''' Choose which card you want to play! Enter 0 if you want your turn to end. '''")) - 1);
            if (whichCard > player.getNumberCards() || whichCard < -1){
                System.out.println("Choose from the cards that "+ player.getName()+" has on hand!");
                continue;
            }
            if (whichCard > -1) {
                if (!player.getPlayerCards().get(whichCard).isPlayable()) {
                    System.out.println(player.getPlayerCards().get(whichCard).getName() + " Is not playable, please choose a different card");
                    continue;
                }
            }



            return whichCard;

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

    private boolean checkPrisonEffect(Player[] players, int playersCurrent){
        Card card;
        int cardInd;

        if(players[playersCurrent].getPlayerBlueCards().contains(new Prison())){
            cardInd =  players[playersCurrent].getPlayerBlueCards().indexOf(new Prison());
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if( card.blueCardEffect(players[playersCurrent])){
                System.out.println(players[playersCurrent].getName() +" is locked up in a Prison, his turn is skipped!");
                return true;
            }
        }
        return false;
    }
    private void checkDynamiteEffect(Player[] players, int playersCurrent){
        Card card;
        int cardInd;

        if(players[playersCurrent].getPlayerBlueCards().contains(new Dynamite())){
            cardInd =  players[playersCurrent].getPlayerBlueCards().indexOf(new Dynamite());
            card = players[playersCurrent].getPlayerBlueCards().get(cardInd);
            if( card.blueCardEffect(players[playersCurrent])){
                System.out.println("BOOM!\n"+players[playersCurrent].getName() +" got blown up!");
            }
            else{
                if (playersCurrent-1  >= 0){
                    players[playersCurrent-1].getPlayerBlueCards().add(card);
                    players[playersCurrent].getPlayerBlueCards().remove(card);

                }
                else{
                    players[getNumberOfPlayersPlaying()].getPlayerBlueCards().add(card);
                    players[playersCurrent].getPlayerBlueCards().remove(card);
                }


            }

        }

    }











}
