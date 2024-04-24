package com.example.chatgptjokes.service;

import com.example.chatgptjokes.dtos.SummonerIDResponse;
import com.example.chatgptjokes.dtos.SummonerInfoResponse;
import com.example.chatgptjokes.dtos.SummonerpIDResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
@Service
public class SummonerSearchService {

    public static final Logger logger = LoggerFactory.getLogger(SummonerSearchService.class);
    private String API_KEY = "";

    public String getpID_URL = "https://europe.api.riotgames.com/riot/account/v1/accounts/by-riot-id/";

    public String getID_URL = "https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/";

    public String getInfo_URL = "https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/";

    private WebClient client;

    public SummonerSearchService() {
        this.client = WebClient.create();
    }
    //Use this constructor for testing, to inject a mock client
    public SummonerSearchService(WebClient client) {
        this.client = client;
    }


    public List<SummonerInfoResponse> getSummonerInfo(String summonerName) {
        try {
            System.out.println(summonerName);
            // Step 1: Get summoner pID
            SummonerpIDResponse summonerpIDResponse = getSummonerpID(summonerName);
            String summonerpID = summonerpIDResponse.getPid();
            System.out.println(summonerpID);

            // Step 2: Use summoner pID to fetch more information, the id
            SummonerIDResponse summonerIDResponse = getSummonerID(summonerpID);
            String summonerID = summonerIDResponse.getId();
            System.out.println(summonerID);


            // Step 3: Use summoner id to get rank
            String url = getInfo_URL + summonerID;

            SummonerInfoResponse[] summonerInfoArray = client.get()
                    .uri(url)
                    .header("X-Riot-Token", API_KEY)
                    .retrieve()
                    .bodyToMono(SummonerInfoResponse[].class)
                    .block();

            // Convert the array to a List
            List<SummonerInfoResponse> summonerInfoList = Arrays.asList(summonerInfoArray);
            System.out.println(summonerInfoList);
            return summonerInfoList;
        } catch (WebClientResponseException e) {
            // Exception handling
            logger.error("Error response status code: {}", e.getRawStatusCode());
            logger.error("Error response body: {}", e.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error - Failed to fetch summoner information");
        } catch (Exception e) {
            // Exception handling
            logger.error("Exception", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error - Failed to fetch summoner information");
        }
    }


    public SummonerIDResponse getSummonerID(String summonerpID) {
        try {

            String summonerURL = getID_URL + summonerpID;
            String url = UriComponentsBuilder.fromHttpUrl(summonerURL)
                    .queryParam("api_key", API_KEY)
                    .toUriString();

            SummonerIDResponse summonerID = client.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(SummonerIDResponse.class)
                    .block();

            return summonerID;

        } catch (WebClientResponseException e) {
            logger.error("Error response status code: {}", e.getRawStatusCode());
            logger.error("Error response body: {}", e.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error - Failed to fetch summoner information");
        } catch (Exception e) {
            logger.error("Exception", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error - Failed to fetch summoner information");
        }
    }
    public SummonerpIDResponse getSummonerpID(String summonerName) {
        try {

            String summonerURL = getpID_URL + summonerName;
            String url = UriComponentsBuilder.fromHttpUrl(summonerURL)
                    .queryParam("api_key", API_KEY)
                    .toUriString();

            SummonerpIDResponse summonerInfo = client.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(SummonerpIDResponse.class)
                    .block();

            return summonerInfo;

        } catch (WebClientResponseException e) {
            logger.error("Error response status code: {}", e.getRawStatusCode());
            logger.error("Error response body: {}", e.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error - Failed to fetch summoner information");
        } catch (Exception e) {
            logger.error("Exception", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error - Failed to fetch summoner information");
        }
    }
}