package lukashindy.service.impl;

import lukashindy.model.Currency;
import lukashindy.repository.CurrencyRepository;
import lukashindy.service.interfaces.CurrencyService;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private String url_request = "http://www.cbr.ru/scripts/XML_daily.asp";

    private final CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Set<Currency> addSet() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new URL(url_request).openStream());
        document.getDocumentElement().normalize();

        NodeList nList = document.getElementsByTagName("Valute");

        Set<Currency> currencySet = new HashSet<>();

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
        return currencySet;
    }
}
