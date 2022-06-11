package pl.sda.introduction.hibernate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
public class ProductStats {

    private final BigDecimal max;
    private final BigDecimal min;
    private final BigDecimal avg;
    private final BigDecimal sum;

    public ProductStats(BigDecimal max, BigDecimal min, double avg, BigDecimal sum) {
        this.max = max;
        this.min = min;
        this.avg = BigDecimal.valueOf(avg);
        this.sum = sum;
    }
}
