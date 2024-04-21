package com.example.chatgptjokes.api;

import com.example.chatgptjokes.dtos.MyResponse;
import com.example.chatgptjokes.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

/**
 * This class handles fetching a joke via the ChatGPT API
 */
@RestController
@RequestMapping("/api/v1/wow")
@CrossOrigin(origins = "*")
public class WowController {

  private final OpenAiService service;

  /**
   * This contains the message to the ChatGPT API, telling the AI how it should act in regard to the requests it gets.
   */
  final static String SYSTEM_MESSAGE = "You are a helpful assistant, that answer questions about World of Warcraft"+
          "If you are asked questions that are not related to this game. Please tell them that you only answer questions related to World of Warcraft";

  /**
   * The controller called from the browser client.
   * @param service
   */
  public WowController(OpenAiService service) {
    this.service = service;
  }

  /**
   * Handles the request from the browser client.
   * @param about contains the input that ChatGPT uses to make a joke about.
   * @return the response from ChatGPT.
   */
  @GetMapping
  public MyResponse getWoW(@RequestParam String about) {

    return service.makeRequest(about,SYSTEM_MESSAGE);
  }
}
