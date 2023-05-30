package shop.jsconf.bank.handler.ex;

// 추후에 사용할 예정
public class CustomForbiddenException extends RuntimeException{
    public CustomForbiddenException(String message) {
        super(message);
    }
}
