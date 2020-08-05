package lukashindy.utils;

public class MoneyParsing {

    public static Double replaceAndParse(String valueString) {
        String replacedValue = valueString.replace(",", ".");
        return Double.parseDouble(replacedValue);
    }
}
