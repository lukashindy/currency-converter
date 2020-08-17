package lukashindy.service.impl;

import lukashindy.model.Currency;
import lukashindy.model.CurrencyRate;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.repository.CurrencyRepository;
import lukashindy.service.interfaces.CurrencyRateService;
import lukashindy.utils.HelperParse;
import lukashindy.utils.MoneyParsing;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Service
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final String url_request = "http://www.cbr.ru/scripts/XML_daily.asp";
    private Set<CurrencyRate> addCurrencyRate = new HashSet<>();

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRepository currencyRepository;

    public CurrencyRateServiceImpl(CurrencyRateRepository currencyRateRepository, CurrencyRepository currencyRepository) {
        this.currencyRateRepository = currencyRateRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    @PostConstruct
    @Order(2)
    public Set<CurrencyRate> addCurrencyRate() throws ParserConfigurationException, IOException, SAXException, SQLException {
        addRouble();
        return addRate();
    }

    @Override
    public List<CurrencyRate> findAll() {
        List<CurrencyRate> currencyRates = new ArrayList<>();
        currencyRateRepository.findAll().iterator().forEachRemaining(currencyRates::add);
        return currencyRates;
    }

    @Transactional
    public Set<CurrencyRate> addRate() throws IOException, SAXException, ParserConfigurationException {

        NodeList nList = HelperParse.nodeList(url_request, "Valute");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                Currency currency = currencyRepository.findById(eElement.getAttribute("ID")).orElse(null);
                addCurrencyRate.add(new CurrencyRate(currency,
                        new Date(),
                        MoneyParsing.replaceAndParse(eElement.getElementsByTagName("Value").item(0).getTextContent())));
            }
        }
        currencyRateRepository.saveAll(addCurrencyRate);
        return addCurrencyRate;
    }

    private void addRouble() {
        Currency rub = currencyRepository.findById("RUB").orElse(null);
        if (rub != null) {
            addCurrencyRate.add(new CurrencyRate(rub, new Date(),1.0));
            currencyRateRepository.saveAll(addCurrencyRate);
        }
    }








    @Override
    public List<CurrencyRate> findAllByDateOrderByCurrencyNameAsc(Date date) throws ParserConfigurationException, SAXException, IOException {

        // Проверим на одной из валют, т.к. добавим в любом случае для всех валют - вдруг, другая дата будет стоять
        Optional<CurrencyRate> checkRate = currencyRateRepository.findByCurrencyIdAndDate("R01235", date);
        if (checkRate.isEmpty()) {
            addCurrencyRate = addRate();
        }
        return currencyRateRepository.findAllByDateOrderByCurrencyNameAsc(date);
    }


}
