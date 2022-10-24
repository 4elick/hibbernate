package app.controller;

import app.dto.CardAccountDTO;
import app.dto.Request;
import app.dto.Response;
import app.entity.CardAccount;
import app.service.CardAccountService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/card-accounts")
public class CardAccountController {
    private final CardAccountService cardAccountService;
    @JsonView(Response.class)
    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CardAccountDTO getCardAccount(@PathVariable long id){
        return cardAccountService.findById(id);
    }
    @JsonView(Request.class)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCardAccount(@RequestBody CardAccountDTO cardAccount){
        cardAccountService.save(cardAccount);
    }
    @JsonView(Request.class)
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCardAccount(@PathVariable long id,@RequestBody CardAccountDTO cardAccount){
        cardAccountService.updateCardAccount(id,cardAccount);
    }

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardAccount(@PathVariable long id){
        cardAccountService.delete(id);
    }
}
