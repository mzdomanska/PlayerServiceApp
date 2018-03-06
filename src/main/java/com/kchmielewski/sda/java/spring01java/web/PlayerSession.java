package com.kchmielewski.sda.java.spring01java.web;

import com.kchmielewski.sda.java.spring01java.model.Player;

public class PlayerSession {

    private int counter;
    private Player recentylAddedPlayer;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Player getRecentylAddedPlayer() {
        return recentylAddedPlayer;
    }

    public void setRecentylAddedPlayer(Player recentylAddedPlayer) {
        this.recentylAddedPlayer = recentylAddedPlayer;
    }
}
