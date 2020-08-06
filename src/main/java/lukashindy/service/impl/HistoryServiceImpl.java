package lukashindy.service.impl;

import lombok.extern.slf4j.Slf4j;
import lukashindy.model.ConverterForm;
import lukashindy.model.Currency;
import lukashindy.model.CurrencyRate;
import lukashindy.model.History;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.repository.CurrencyRepository;
import lukashindy.repository.HistoryRepository;
import lukashindy.service.interfaces.HistoryService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class HistoryServiceImpl implements HistoryService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyRateRepository currencyRateRepository;
    private final HistoryRepository historyRepository;

    public HistoryServiceImpl(CurrencyRepository currencyRepository, CurrencyRateRepository currencyRateRepository, HistoryRepository historyRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyRateRepository = currencyRateRepository;
        this.historyRepository = historyRepository;
    }

    public History saveNewConversion(ConverterForm converterForm) {

        Currency source = currencyRepository.findCurrencyByCharCode(converterForm.getSourceCharCode());
        log.info(source.getCharCode());

        CurrencyRate sourceRate = currencyRateRepository.findByCurrencyIdAndDate(source.getId(), new Date()).orElse(null);
        Double sum1 = sourceRate.getValue() / source.getNominal();

        Currency target = currencyRepository.findCurrencyByCharCode(converterForm.getTargetCharCode());
        CurrencyRate targetRate = currencyRateRepository.findByCurrencyIdAndDate(target.getId(), new Date()).orElse(null);
        Double sum2 =  targetRate.getValue() / target.getNominal();
        log.info(target.getCharCode());


        Double targetSum = converterForm.getSourceSum() * sum1 / sum2;
        log.info(String.valueOf(targetSum));


        History history = new History(source, target, converterForm.getSourceSum(), targetSum, new Date());
        historyRepository.save(history);

        History savedHistory = historyRepository.findById(history.getId()).orElse(null);
        System.out.println(savedHistory.getId());
        return savedHistory;
    }
}
