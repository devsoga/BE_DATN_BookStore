package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.enties.ProductQuestionEntity;
import com.devsoga.BookStore_V2.enties.ProductAnswerEntity;
import com.devsoga.BookStore_V2.services.QAService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/qa")
public class QAController {

    @Autowired
    private QAService qaService;

    @PostMapping("/question")
    public ResponseEntity<ProductQuestionEntity> createQuestion(@RequestBody ProductQuestionEntity req) {
        ProductQuestionEntity created = qaService.createQuestion(req);
        return ResponseEntity.created(URI.create("/v1/api/qa/question/" + created.getQuestionCode())).body(created);
    }

    @GetMapping("/question/{questionCode}")
    public ResponseEntity<ProductQuestionEntity> getQuestion(@PathVariable String questionCode) {
        Optional<ProductQuestionEntity> q = qaService.findQuestionByCode(questionCode);
        return q.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/questions/product/{productCode}")
    public ResponseEntity<List<ProductQuestionEntity>> listQuestions(@PathVariable String productCode) {
        return ResponseEntity.ok(qaService.findQuestionsByProduct(productCode));
    }

    @DeleteMapping("/question/{questionCode}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable String questionCode) {
        qaService.deleteQuestion(questionCode);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/answer")
    public ResponseEntity<ProductAnswerEntity> createAnswer(@RequestBody ProductAnswerEntity req) {
        ProductAnswerEntity created = qaService.createAnswer(req);
        return ResponseEntity.created(URI.create("/v1/api/qa/answer/" + created.getAnswerCode())).body(created);
    }

    @GetMapping("/answer/{answerCode}")
    public ResponseEntity<ProductAnswerEntity> getAnswer(@PathVariable String answerCode) {
        Optional<ProductAnswerEntity> a = qaService.findAnswerByCode(answerCode);
        return a.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/answers/question/{questionCode}")
    public ResponseEntity<List<ProductAnswerEntity>> listAnswers(@PathVariable String questionCode) {
        return ResponseEntity.ok(qaService.findAnswersByQuestion(questionCode));
    }

    @DeleteMapping("/answer/{answerCode}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable String answerCode) {
        qaService.deleteAnswer(answerCode);
        return ResponseEntity.noContent().build();
    }
}
