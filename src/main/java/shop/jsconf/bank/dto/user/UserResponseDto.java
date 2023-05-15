package shop.jsconf.bank.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.jsconf.bank.domain.user.User;

public class UserResponseDto {

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