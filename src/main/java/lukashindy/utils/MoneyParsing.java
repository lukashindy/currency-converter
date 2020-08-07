package lukashindy.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoneyParsing {

    public static Double replaceAndParse(String valueString) {
        String replacedValue = valueString.replace(",", ".");
        return Double.parseDouble(replacedValue);
    }

    public static Double roundTo4Double(Double number) {
        return new BigDecimal(number).setScale(4, RoundingMode.HALF_DOWN).doubleValue();
    }

    public static void main(String[] args) {
        String pattern = "MM.dd.yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();
        String todayAsString = df.format(today);
        System.out.println("Today is: " + todayAsString);
    }
}
