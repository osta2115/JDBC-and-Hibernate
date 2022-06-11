package pl.sda.introduction.hibernate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class ProductStats {

    private final BigDecimal max;
    private final BigDecimal min;
    private final double avg;
    private final BigDecimal sum;
}
