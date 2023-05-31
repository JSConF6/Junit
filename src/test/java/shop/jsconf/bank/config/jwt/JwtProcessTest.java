package shop.jsconf.bank.config.jwt;

import org.junit.jupiter.api.Test;
import shop.jsconf.bank.config.auth.LoginUser;
import shop.jsconf.bank.domain.user.User;
import shop.jsconf.bank.domain.user.UserEnum;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtProcessTest {

    @Test
    public void create_test() throws Exception {
        // given
        User user = User.builder().id(1L).role(UserEnum.ADMIN).build();
        LoginUser loginUser = new LoginUser(user);

        // when
        String jwtToken = JwtProcess.create(loginUser);
        System.out.println("테스트 : " + jwtToken);

        // then
        assertTrue(jwtToken.startsWith(JwtVO.TOKEN_PREFIX));
    }

    @Test
    public void verify_test() throws Exception {
        // given
        String jwtToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rIiwicm9sZSI6IkFETUlOIiwiaWQiOjEsImV4cCI6MTY4NjEzOTk2NX0.L7XTwkuAqlhHY0Ai1B86ue0kvMEDmsGCJECjsLfyAljdqhxGg5OTlwP_nu496aMc_p7pTfKOogu-bQs72qQacw";

        // when

        LoginUser loginUser = JwtProcess.verify(jwtToken);
        System.out.println("테스트 : " + loginUser.getUser().getId());
        System.out.println("테스트 : " + loginUser.getUser().getRole().name());

        // then
        assertThat(loginUser.getUser().getId()).isEqualTo(1L);
        assertThat(loginUser.getUser().getRole()).isEqualTo(UserEnum.ADMIN);
    }
}
