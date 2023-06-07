package shop.jsconf.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByNumber(Long number);

    // jpa query method
    // select * from account where user_id = :id
    List<Account> findByUser_id(Long id);
}
