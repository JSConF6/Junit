package shop.jsconf.bank.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.jsconf.bank.domain.user.User;
import shop.jsconf.bank.domain.user.UserEnum;

import javax.validation.constraints.NotEmpty;

public class UserRequestDto {
    @Getter
    @Setter
    public static class JoinReqDto {
        // 유효성 검사
        @NotEmpty // null 이거나, 공백일 수 없다.
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;
        @NotEmpty
        private String fullname;

        public User toEntity(BCryptPasswordEncoder passwordEncoder) {
            return User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .fullname(fullname)
                    .role(UserEnum.CUSTOMER)
                    .build();
        }
    }
}
