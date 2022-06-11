package pl.sda.introduction.hibernate;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@ToString
@Setter
@Embeddable
public class Price {

    @Column(name = "price_value")
    private BigDecimal priceValue;

    private String currency;
}
