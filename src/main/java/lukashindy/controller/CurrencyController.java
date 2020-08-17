package lukashindy.controller;

import lukashindy.model.ConverterForm;
import lukashindy.model.History;
import lukashindy.repository.CurrencyRateRepository;
import lukashindy.service.interfaces.CurrencyRateService;
import lukashindy.service.interfaces.CurrencyService;
import lukashindy.service.interfaces.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class CurrencyController {

    private final List<History> lastHistories = new ArrayList<>();
    private History returnedHistory;

    private Double targetSum = 0.0;

    private final HistoryService historyService;
    private final CurrencyRateService currencyRateService;

    public CurrencyController(HistoryService historyService, CurrencyRateService currencyRateService) {
        this.historyService = historyService;
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/currencies")
    public String getCurrencies(Model model) throws IOException, SAXException, ParserConfigurationException {
        model.addAttribute("rates", currencyRateService.findAllByDateOrderByCurrencyNameAsc(new Date()));
        return "currency";
    }

    @GetMapping("/converter/form")
    public String getForm(Model model) {
        model.addAttribute("list", currencyRateService.findAll());
        model.addAttribute("history", new ConverterForm());
        model.addAttribute("lastHistories", lastHistories);
        model.addAttribute("targetSum", targetSum);
        model.addAttribute("returnedHistory", returnedHistory);
        return "converter-form";
    }

    @PostMapping("/converter/form")
    public String saveNewConversion(@ModelAttribute("history") ConverterForm converterForm, BindingResult bindingResult) throws IOException, SAXException, ParserConfigurationException {

        if (bindingResult.hasErrors())
            return "converter-form";

        if (lastHistories.size() == 1)
            lastHistories.remove(0);

        returnedHistory = historyService.saveNewConversion(converterForm);
        lastHistories.add(0, returnedHistory);
        targetSum = lastHistories.get(0).getTargetSum();
        return "redirect:/converter/form";
    }

}
