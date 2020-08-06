package lukashindy.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyRateId implements Serializable {

    private Currency currency;
    private Date date;

    public CurrencyRateId(Currency currency, Date date) {
        this.currency = currency;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRateId that = (CurrencyRateId) o;
        return Objects.equals(currency, that.currency) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, date);
    }
}
