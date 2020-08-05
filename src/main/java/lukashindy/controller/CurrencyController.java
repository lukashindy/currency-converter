package lukashindy.controller;

import lukashindy.service.interfaces.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public String getCurrencies(Model model) throws IOException, SAXException, ParserConfigurationException {
        model.addAttribute("list", currencyService.addSet());
        return "currency";
    }
}
