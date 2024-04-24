package com.example.chatgptjokes.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummonerpIDResponse {
    @JsonProperty("puuid")
    private String pid;

     // Getter and setter
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
