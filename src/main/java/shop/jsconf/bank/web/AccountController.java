package shop.jsconf.bank.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.jsconf.bank.config.auth.LoginUser;
import shop.jsconf.bank.dto.ResponseDto;
import shop.jsconf.bank.dto.account.AccountReqDto;
import shop.jsconf.bank.dto.account.AccountReqDto.AccountSaveReqDto;
import shop.jsconf.bank.dto.account.AccountRespDto;
import shop.jsconf.bank.dto.account.AccountRespDto.AccountSaveRespDto;
import shop.jsconf.bank.service.AccountService;

import javax.validation.Valid;

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
}
