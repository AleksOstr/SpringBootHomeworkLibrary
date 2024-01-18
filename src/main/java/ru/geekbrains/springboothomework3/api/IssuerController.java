package ru.geekbrains.springboothomework3.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboothomework3.api.request.IssueRequest;
import ru.geekbrains.springboothomework3.model.Issue;
import ru.geekbrains.springboothomework3.service.IssuerService;

import javax.naming.OperationNotSupportedException;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController {


    private IssuerService service;

    public IssuerController(IssuerService service) {
        this.service = service;
    }

    //  @PutMapping
//  public void returnBook(long issueId) {
//    // найти в репозитории выдачу и проставить ей returned_at
//  }

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (OperationNotSupportedException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        try {
            Issue issue = service.getIssueById(id);
            return ResponseEntity.status(HttpStatus.OK).body(issue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
