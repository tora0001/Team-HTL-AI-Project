package com.example.chatgptjokes.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SummonerInfoResponse {

    @JsonProperty("tier")
    private String tier;

    @JsonProperty("rank")
    private String rank;


    @JsonProperty("wins")
    private int wins;

    @JsonProperty("losses")
    private int losses;

    public SummonerInfoResponse(String tier, String rank, String summonerName, int wins, int losses) {
        this.tier = tier;
        this.rank = rank;
        this.wins = wins;
        this.losses = losses;
    }

    // Getters and setters
    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}