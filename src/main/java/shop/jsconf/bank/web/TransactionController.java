package shop.jsconf.bank.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.jsconf.bank.config.auth.LoginUser;
import shop.jsconf.bank.dto.ResponseDto;
import shop.jsconf.bank.dto.transaction.TransactionRespDto;
import shop.jsconf.bank.dto.transaction.TransactionRespDto.TransactionListRespDto;
import shop.jsconf.bank.service.TransactionService;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/s/account/{number}/transaction")
    public ResponseEntity<?> findTransactionList(@PathVariable Long number,
                                                 @RequestParam(value = "gubun", defaultValue = "ALL") String gubun,
                                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @AuthenticationPrincipal LoginUser loginUser) {
        TransactionListRespDto transactionListRespDto = transactionService.transactionList(loginUser.getUser().getId(), number, gubun, page);
        return new ResponseEntity<>(new ResponseDto<>(1, "입출금 목록보기 성공", transactionListRespDto), HttpStatus.OK);
    }
}
