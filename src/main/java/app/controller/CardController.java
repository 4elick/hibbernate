package app.controller;

import app.dto.CardDTO;
import app.dto.Request;
import app.dto.Response;
import app.entity.Card;
import app.service.CardService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card-accounts/{accountId}/cards")
public class CardController {
    private final CardService cardService;

    @JsonView(Request.class)
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CardDTO getCard(@PathVariable long id){
        return cardService.findById(id);
    }

    @JsonView(Response.class)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody CardDTO card){
        cardService.save(card);
    }

    @JsonView(Response.class)
    @PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCard(@PathVariable long id,@RequestBody CardDTO card){
        cardService.update(id,card);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable long id){
        cardService.delete(id);
    }
}
