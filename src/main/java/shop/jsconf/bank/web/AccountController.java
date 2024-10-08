package shop.jsconf.bank.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.jsconf.bank.config.auth.LoginUser;
import shop.jsconf.bank.dto.ResponseDto;
import shop.jsconf.bank.dto.account.AccountReqDto;
import shop.jsconf.bank.dto.account.AccountReqDto.AccountSaveReqDto;
import shop.jsconf.bank.dto.account.AccountRespDto;
import shop.jsconf.bank.dto.account.AccountRespDto.AccountListRespDto;
import shop.jsconf.bank.dto.account.AccountRespDto.AccountSaveRespDto;
import shop.jsconf.bank.handler.ex.CustomForbiddenException;
import shop.jsconf.bank.service.AccountService;

import javax.validation.Valid;

import static shop.jsconf.bank.dto.account.AccountReqDto.*;
import static shop.jsconf.bank.dto.account.AccountRespDto.*;
import static shop.jsconf.bank.service.AccountService.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/s/account")
    public ResponseEntity<?> saveAccount(@RequestBody @Valid AccountSaveReqDto accountSaveReqDto,
                                         BindingResult bindingResult, @AuthenticationPrincipal LoginUser loginUser) {
        AccountSaveRespDto accountSaveRespDto = accountService.accountCreate(accountSaveReqDto, loginUser.getUser().getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "계좌등록 성공", accountSaveRespDto), HttpStatus.CREATED);
    }

    // 인증이 필요하고, account 테이블에 1번 row를 주세요
    // cos로 로그인 했는데, cos의 id가 2번이에요
    // 권한처리 때문에 선호하지 않는다.
    // 인증이 필요하고, account 테이블 데이터 다 주세요
    // 인증이 필요하고, account 테이블에 login한 유저의 계좌만 주세요
    @GetMapping("/s/account/login-user")
    public ResponseEntity<?> findUserAccount(@AuthenticationPrincipal LoginUser loginUser) {
        AccountListRespDto accountListRespDto = accountService.userAccountList(loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "유저별 계좌목록 보기 성공", accountListRespDto), HttpStatus.OK);
    }

    @DeleteMapping("/s/account/{number}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long number, @AuthenticationPrincipal LoginUser loginUser) {
        accountService.deleteAccount(number, loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "계좌 삭제 완료", null), HttpStatus.OK);
    }

    @PostMapping("/account/deposit")
    public ResponseEntity<?> depositAccount(@RequestBody @Valid AccountDepositReqDto accountDepositReqDto, BindingResult bindingResult) {
        AccountDepositRespDto accountDepositRespDto = accountService.depositAccount(accountDepositReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "계좌 입금 완료", accountDepositRespDto), HttpStatus.CREATED);
    }

    @PostMapping("/s/account/withdraw")
    public ResponseEntity<?> withdrawAccount(@RequestBody @Valid AccountWithdrawReqDto accountWithdrawReqDto,
     BindingResult bindingResult,
     @AuthenticationPrincipal LoginUser loginUser) {
        AccountWithdrawRespDto accountWithdrawRespDto = accountService.withdrawAccount(accountWithdrawReqDto, loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "계좌 출금 완료", accountWithdrawRespDto), HttpStatus.CREATED);
    }

    @PostMapping("/s/account/transfer")
    public ResponseEntity<?> transferAccount(@RequestBody @Valid AccountTransferReqDto accountTransferReqDto,
                                             BindingResult bindingResult,
                                             @AuthenticationPrincipal LoginUser loginUser) {
        AccountTransferRespDto accountTransferRespDto = accountService.transferAccount(accountTransferReqDto, loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "계좌 이체 완료", accountTransferRespDto), HttpStatus.CREATED);
    }
}
