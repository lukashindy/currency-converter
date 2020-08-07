package lukashindy.service.impl;

import lukashindy.model.Currency;
import lukashindy.model.CurrencyRate;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.repository.CurrencyRepository;
import lukashindy.service.interfaces.CurrencyService;
import lukashindy.utils.HelperParse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private String url_request = "http://www.cbr.ru/scripts/XML_daily.asp";
    Set<Currency> currencySet = new HashSet<>();

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    @PostConstruct
    @Order(10)
    public Set<Currency> addSet() throws ParserConfigurationException, IOException, SAXException {

        Currency rub = currencyRepository.findById("RUB").orElse(null);
        if (rub == null) {
            currencySet.add(new Currency(
                    "RUB",
                    "643",
                    "RUB",
                    1,
                    "Российский рубль"
            ));
            currencyRepository.saveAll(currencySet);
        }

        if (currencyRepository.findAll().size() == 0) {

            NodeList nList = HelperParse.nodeList(url_request, "Valute");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    currencySet.add(new Currency(
                            eElement.getAttribute("ID"),
                            eElement.getElementsByTagName("NumCode").item(0).getTextContent(),
                            eElement.getElementsByTagName("CharCode").item(0).getTextContent(),
                            Integer.valueOf(eElement.getElementsByTagName("Nominal").item(0).getTextContent()),
                            eElement.getElementsByTagName("Name").item(0).getTextContent()));
                }
            }
            currencyRepository.saveAll(currencySet);
        }
        return currencySet;
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> currencyList = new ArrayList<>();
        currencyRepository.findAll().iterator().forEachRemaining(currencyList::add);
        return currencyList;
    }
}
