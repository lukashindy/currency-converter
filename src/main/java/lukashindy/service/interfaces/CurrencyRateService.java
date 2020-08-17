package lukashindy.service.interfaces;

import lukashindy.model.CurrencyRate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CurrencyRateService {

    Set<CurrencyRate> addCurrencyRate() throws ParserConfigurationException, IOException, SAXException, SQLException;

    List<CurrencyRate> findAll();

    Set<CurrencyRate> addRate() throws IOException, SAXException, ParserConfigurationException;

    List<CurrencyRate> findAllByDateOrderByCurrencyNameAsc(Date date) throws ParserConfigurationException, SAXException, IOException;
}
