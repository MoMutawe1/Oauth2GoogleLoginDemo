package oauth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauth.repository.BookListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@Slf4j
@AllArgsConstructor
public class BookShelfController {

    @Autowired
    BookListRepo repository;

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Computer Science Library");
    }

    @GetMapping("/bookshelf")
    public ResponseEntity<String> checkThisBookForMe(@AuthenticationPrincipal OAuth2User principle){

        String msg =  "Invalid user, unable to process further. Please authenticate yourself";

        if(principle != null) {
            String title = principle.getAttribute("title");
            log.info("Fetching number of copies for the book: {}", title);
            var copy = repository.getBook(title);

            if(copy == null){
                msg = "Sorry, {} is out of stock." + title;
            } else{
                msg = "The book with title "+ title+ " has "+ copy + " copy in our shelf.";
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
