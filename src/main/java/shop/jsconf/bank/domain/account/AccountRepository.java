package shop.jsconf.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    // join fetch를 하면 조인해서 객체에 값을 미리 가져올수 있다.
    //    @Query("SELECT ac FROM Account ac JOIN FETCH ac.user u WHERE ac.number = :number")
    Optional<Account> findByNumber(Long number);

    // jpa query method
    // select * from account where user_id = :id
    List<Account> findByUser_id(Long id);
}
