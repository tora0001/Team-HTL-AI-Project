package com.example.chatgptjokes.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummonerIDResponse {
    @JsonProperty("id")
    private String id;

    // Getter and setter for summoner ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
