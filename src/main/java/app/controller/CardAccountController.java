package app.controller;

import app.entity.CardAccount;
import app.service.CardAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/card-accounts")
public class CardAccountController {
    private final CardAccountService cardAccountService;

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CardAccount getCardAccount(@PathVariable long id){
        return cardAccountService.findById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCardAccount(@RequestBody CardAccount cardAccount){
        cardAccountService.save(cardAccount);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCardAccount(@PathVariable long id,@RequestBody CardAccount cardAccount){
        cardAccountService.updateCardAccount(id,cardAccount);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardAccount(@PathVariable long id){
        cardAccountService.delete(id);
    }
}
