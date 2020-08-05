package lukashindy.repository;

import lukashindy.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Currency findCurrencyByCharCode(String charCode);
}
