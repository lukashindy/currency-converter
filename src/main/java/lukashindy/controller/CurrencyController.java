package lukashindy.controller;

import lukashindy.model.ConverterForm;
import lukashindy.model.History;
import lukashindy.service.interfaces.CurrencyRateService;
import lukashindy.service.interfaces.CurrencyService;
import lukashindy.service.interfaces.HistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class CurrencyController {

    private List<History> lastHistories = new ArrayList<>();
    private History returnedHistory;

    private final HistoryService historyService;
    private final CurrencyService currencyService;
    private final CurrencyRateService currencyRateService;

    public CurrencyController(HistoryService historyService, CurrencyService currencyService, CurrencyRateService currencyRateService) {
        this.historyService = historyService;
        this.currencyService = currencyService;
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/currencies")
    public String getCurrencies(Model model) {
        model.addAttribute("rates", currencyRateService.findAll());
        return "currency";
    }

    @GetMapping("/converter/form")
    public String getForm(Model model) {
        model.addAttribute("list", currencyRateService.findAll());
        model.addAttribute("history", new ConverterForm());
        model.addAttribute("lastHistories", lastHistories);
        return "converter-form";
    }

    @PostMapping("/converter/form")
    public String saveNewConversion(@ModelAttribute("history") ConverterForm converterForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "converter-form";
        }

        History savedHistory = historyService.saveNewConversion(converterForm);
        returnedHistory = savedHistory;
        lastHistories.add(0, returnedHistory);
        return "redirect:/converter/form";
    }

    @GetMapping("/converter/history")
    public String getHistory(Model model) {
        model.addAttribute("histories", historyService.findAll());
        model.addAttribute("last", returnedHistory);
        return "history";
    }

}
