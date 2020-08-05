package lukashindy.service.interfaces;

import lukashindy.model.Currency;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

public interface CurrencyService {

    Set<Currency> addSet() throws ParserConfigurationException, IOException, SAXException;
}
