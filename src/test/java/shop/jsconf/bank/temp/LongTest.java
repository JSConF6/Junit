package shop.jsconf.bank.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongTest {

    @Test
    public void LongTest3() {
        // given
        Long v1 = 1000L;
        Long v2 = 1000L;

        // when

        // then
        assertThat(v1).isEqualTo(v2);
    }

    @Test
    public void longTest2() {
        // given
        Long v1 = 128L;
        Long v2 = 128L;

        // when
        if (v1 == v2) {
            System.out.println("테스트 : 같습니다.");
        }

        // then
    }

    @Test
    @DisplayName("Long 자료형 테스트")
    public void longTest() {
        // given
        Long number1 = 1111L;
        Long number2 = 1111L;
        
        // when
        if (number1.longValue() == number2.longValue()) {
            System.out.println("테스트 : 동일합니다.");
        } else {
            System.out.println("테스트 : 동일하지 않습니다.");
        }

        Long amount1 = 1000L;
        Long amount2 = 1000L;

        if (amount1 < amount2) {
            System.out.println("테스트 : amount1이 작습니다.");
        } else {
            System.out.println("테스트 : amount1이 큽니다.");
        }
        
        // then
    }
}
