package shop.jsconf.bank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.jsconf.bank.domain.account.Account;
import shop.jsconf.bank.domain.account.AccountRepository;
import shop.jsconf.bank.domain.transaction.Transaction;
import shop.jsconf.bank.domain.transaction.TransactionRepository;
import shop.jsconf.bank.dto.transaction.TransactionRespDto.TransactionListRespDto;
import shop.jsconf.bank.handler.ex.CustomApiException;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionListRespDto transactionList(Long userId, Long accountNumber, String gubun, int page) {
        Account accountPS = accountRepository.findByNumber(accountNumber)
                .orElseThrow(() -> new CustomApiException("해당 계좌를 찾을 수 없습니다."));

        accountPS.checkOwner(userId);

        List<Transaction> transactionListPS = transactionRepository.findTransactionList(accountPS.getId(), gubun, page);
        return new TransactionListRespDto(transactionListPS, accountPS);
    }
}
