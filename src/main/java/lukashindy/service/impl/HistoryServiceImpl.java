package lukashindy.service.impl;

import lombok.extern.slf4j.Slf4j;
import lukashindy.model.ConverterForm;
import lukashindy.model.Currency;
import lukashindy.model.CurrencyRate;
import lukashindy.model.History;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.repository.CurrencyRepository;
import lukashindy.repository.HistoryRepository;
import lukashindy.service.interfaces.CurrencyRateService;
import lukashindy.service.interfaces.HistoryService;
import lukashindy.utils.MoneyParsing;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class HistoryServiceImpl implements HistoryService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final HistoryRepository historyRepository;
    private final CurrencyRateService currencyRateService;

    public HistoryServiceImpl(CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository, HistoryRepository historyRepository, CurrencyRateService currencyRateService) {
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.historyRepository = historyRepository;
        this.currencyRateService = currencyRateService;
    }

    public History saveNewConversion(ConverterForm converterForm) throws ParserConfigurationException, SAXException, IOException {

        Currency source = currencyRepository.findCurrencyByCharCode(converterForm.getSourceCharCode());

        Optional<CurrencyRate> checkRates = currencyRateRepository.findByCurrencyIdAndDate(source.getId(), new Date());
        if (checkRates.isEmpty()) {
            Set<CurrencyRate> set = currencyRateService.addRate();
        }

        CurrencyRate sourceRate = currencyRateRepository.findByCurrencyIdAndDate(source.getId(), new Date()).orElse(null);
        Double sum1 = sourceRate.getValue() / source.getNominal();

        Currency target = currencyRepository.findCurrencyByCharCode(converterForm.getTargetCharCode());
        CurrencyRate targetRate = currencyRateRepository.findByCurrencyIdAndDate(target.getId(), new Date()).orElse(null);
        Double sum2 = targetRate.getValue() / target.getNominal();

        Double targetSum = converterForm.getSourceSum() * sum1 / sum2;
        targetSum = MoneyParsing.roundTo4Double(targetSum);

        History history = new History(source, target, converterForm.getSourceSum(), targetSum, new Date());
        log.info(history.toString());
        historyRepository.save(history);

        return historyRepository.findById(history.getId()).orElse(null);
    }

//    public List<History> findAll(String source, String target, Date searchDate) {
//        return historyRepository.findAll(source, target, searchDate);
//    }

    public List<History> findAll(String source, String target) {
        return historyRepository.findAll(source, target);
    }

}
