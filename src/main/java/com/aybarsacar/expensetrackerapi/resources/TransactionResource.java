package com.aybarsacar.expensetrackerapi.resources;


import com.aybarsacar.expensetrackerapi.domain.Transaction;
import com.aybarsacar.expensetrackerapi.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories/{categoryId}/transactions")
public class TransactionResource {

  @Autowired
  TransactionService transactionService;

  @GetMapping("")
  public ResponseEntity<List<Transaction>> getAllTransactions(HttpServletRequest req,
                                                              @PathVariable("categoryId") Integer categoryId) {

    int userId = (Integer) req.getAttribute("userId");

    List<Transaction> transactions = transactionService.fetchAllTransaction(userId, categoryId);

    return new ResponseEntity<>(transactions, HttpStatus.OK);

  }

  @GetMapping("/{transactionId}")
  public ResponseEntity<Transaction> getTransactionById(HttpServletRequest req,
                                                        @PathVariable("categoryId") Integer categoryId,
                                                        @PathVariable("transactionId") Integer transactionId) {

    int userId = (Integer) req.getAttribute("userId");

    Transaction transaction = transactionService.fetchTransactionById(userId, categoryId, transactionId);

    return new ResponseEntity<>(transaction, HttpStatus.OK);

  }

  @PostMapping("")
  public ResponseEntity<Transaction> addTransaction(HttpServletRequest req,
                                                    @PathVariable("categoryId") Integer categoryId,
                                                    @RequestBody Map<String, Object> transactionMap) {

//    get the user id
    int userId = (Integer) req.getAttribute("userId");

    Double amount = Double.valueOf(transactionMap.get("amount").toString());
    String note = (String) transactionMap.get("note");
    Long transactionDate = (Long) transactionMap.get("transactionDate");

    Transaction transaction = transactionService.addTransaction(userId, categoryId, amount, note, transactionDate);

    return new ResponseEntity<>(transaction, HttpStatus.CREATED);
  }

  @PutMapping("/{transactionId}")
  public ResponseEntity<Map<String, Boolean>> updateTransaction(HttpServletRequest req,
                                                                @PathVariable("categoryId") Integer categoryId,
                                                                @PathVariable("transactionId") Integer transactionId,
                                                                @RequestBody Transaction transaction) {

    //    get the user id
    int userId = (Integer) req.getAttribute("userId");

    transactionService.updateTransaction(userId, categoryId, transactionId, transaction);

    Map<String, Boolean> map = new HashMap<>() {{
      put("success", true);
    }};

    return new ResponseEntity<>(map, HttpStatus.OK);

  }

  @DeleteMapping("/{transactionId}")
  public ResponseEntity<Map<String, Boolean>> deleteTransaction(HttpServletRequest req,
                                                                @PathVariable("categoryId") Integer categoryId,
                                                                @PathVariable("transactionId") Integer transactionId) {

    //    get the user id
    int userId = (Integer) req.getAttribute("userId");

    transactionService.removeTransaction(userId, categoryId, transactionId);

    Map<String, Boolean> map = new HashMap<>() {{
      put("success", true);
    }};

    return new ResponseEntity<>(map, HttpStatus.OK);

  }
}
