package app.controller;

import app.entity.Card;
import app.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card-accounts/{accountId}/cards")
public class CardController {
    private final CardService cardService;

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Card getCard(@PathVariable long id){
        return cardService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCard(@RequestBody Card card){
        cardService.save(card);
    }

    @PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCard(@PathVariable long id,@RequestBody Card card){
        cardService.update(id,card);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable long id){
        cardService.delete(id);
    }
}
