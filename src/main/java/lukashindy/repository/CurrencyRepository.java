package lukashindy.repository;

import lukashindy.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Currency findCurrencyByCharCode(String charCode);

    List<Currency> findAll();
}
