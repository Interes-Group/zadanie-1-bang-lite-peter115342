package sk.stuba.fei.uim.oop.bang;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.card.Card;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class BangFlow {
    private final Player[] players;
    private int playersPlaying;




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
        this.players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers ; i++){
            this.players[i] = new Player(ZKlavesnice.readString("||| Enter the name for player " + (i+1) +": |||"));
        }
        this.startGame();
    }
    private void startGame() {
        System.out.println(" ''' The GAME HAS STARTED... ''' ");
        while (getNumberOfPlayersPlaying() >1){
            Player playerAlive = this.players[this.playersPlaying];
             if (!playerAlive.isLiving()){
                 ArrayList<Card> cardDeck = playerAlive.takeFromHand();

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










}
