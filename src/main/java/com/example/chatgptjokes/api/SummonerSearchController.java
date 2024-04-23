package com.example.chatgptjokes.api;


import com.example.chatgptjokes.dtos.SummonerInfoResponse;
import com.example.chatgptjokes.service.SummonerSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/summoner")
@CrossOrigin(origins = "*")
public class SummonerSearchController {

    private final SummonerSearchService service;
    public SummonerSearchController(SummonerSearchService service) {
        this.service = service;
    }
    @GetMapping
    public List<SummonerInfoResponse> getSummoner(@RequestParam String name) {

        return service.getSummonerInfo(name);
    }

}
