package shop.jsconf.bank.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import shop.jsconf.bank.domain.account.Account;
import shop.jsconf.bank.domain.account.AccountRepository;
import shop.jsconf.bank.domain.user.User;
import shop.jsconf.bank.domain.user.UserRepository;

@Configuration
public class DummyDevInit extends DummyObject{

    @Profile("dev") // prod 모드에서는 실행되면 안된다.
    @Bean
    CommandLineRunner init(UserRepository userRepository, AccountRepository accountRepository) {
        return (args) -> {
          // 서버 실행시에 무조건 실핸된다.
            User ssar = userRepository.save(newUser("ssar", "쌀"));
            User cos = userRepository.save(newUser("cos", "코스"));
            Account ssarAccount1 = accountRepository.save(newAccount(1111L, ssar));
            Account cosAccount1 = accountRepository.save(newAccount(2222L, cos));
        };
    }
}
