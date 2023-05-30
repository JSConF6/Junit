package shop.jsconf.bank.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.jsconf.bank.domain.user.User;
import shop.jsconf.bank.util.CustomDateUtil;

public class UserResponseDto {

    @Getter
    @Setter
    public static class LoginRespDto {
        private Long id;
        private String username;
        private String createAt;

        public LoginRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.createAt = CustomDateUtil.toStringFormat(user.getCreatedAt());
        }
    }

    @Getter
    @Setter
    @ToString
    public static class JoinRespDto{
        private Long id;
        private String username;
        private String fullname;

        public JoinRespDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.fullname = user.getFullname();
        }
    }
}
