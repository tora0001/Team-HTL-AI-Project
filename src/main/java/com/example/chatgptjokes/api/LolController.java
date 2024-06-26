package com.example.chatgptjokes.api;

import com.example.chatgptjokes.dtos.MyResponse;
import com.example.chatgptjokes.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

/**
 * This class handles fetching a joke via the ChatGPT API
 */
@RestController
@RequestMapping("/api/v1/lol")
@CrossOrigin(origins = "*")
public class LolController {

    private final OpenAiService service;

    final static String SYSTEM_MESSAGE = "You are a helpful assistant, that answer questions about League of Legends"+
            "If you are asked questions that are not related to this game. Please tell them that you only answer questions related to League of Legends";

    public LolController(OpenAiService service) {
        this.service = service;
    }

    @GetMapping
    public MyResponse getLoL(@RequestParam String about) {

        return service.makeRequest(about,SYSTEM_MESSAGE);
    }
}
