package lukashindy.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MoneyParsing {

    public static Double replaceAndParse(String valueString) {
        String replacedValue = valueString.replace(",", ".");
        return Double.parseDouble(replacedValue);
    }

    public static Double roundTo4Double(Double number) {
        return new BigDecimal(number).setScale(4, RoundingMode.HALF_DOWN).doubleValue();
    }

    public static void main(String[] args) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date searchDate = format.parse("02-05-2020");
        System.out.println(searchDate);
    }
}
