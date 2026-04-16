
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleExamples {

    public static void main(String[] args) {

        Locale engUs = new Locale("en", "US"); // deprecated since Java 19
        Locale hindi = Locale.of("hi"); // New way of creating Locale since Java 19
        Locale currentLocale = Locale.getDefault();

        System.out.println("Hindi Locale:" + hindi);
        System.out.println("American English Locale:" + engUs);
        System.out.println("Default Locale: " + currentLocale);

        double price = 200000;

        System.out.println("-----\nHindi Locale\n-----");
        NumberFormat hindiCurrencyFormat = NumberFormat.getCurrencyInstance(hindi);
        NumberFormat hindiNumericFormat = NumberFormat.getNumberInstance(hindi);
        NumberFormat hindiPercentageFormat = NumberFormat.getPercentInstance(hindi);
        NumberFormat hindiCompactFormat = NumberFormat.getCompactNumberInstance();

        System.out.println("Hindi Currency:" + hindiCurrencyFormat.format(price));
        System.out.println("Hindi Numeric Format:" + hindiNumericFormat.format(price));
        System.out.println("Hindi Percentage Format: " + hindiPercentageFormat.format(0.2));
        System.out.println("Hindi Compact Number Format: " + hindiCompactFormat.format(price));

        System.out.println("-----\nUS English Locale\n-----");
        NumberFormat usEngCurrencyFormat = NumberFormat.getCurrencyInstance(engUs);
        NumberFormat usEngNumericFormat = NumberFormat.getNumberInstance(engUs);
        NumberFormat usEngPercentageFormat = NumberFormat.getPercentInstance(engUs);
        NumberFormat usEngCompactFormat = NumberFormat.getCompactNumberInstance(engUs, NumberFormat.Style.SHORT);

        System.out.println("US English Currency:" + usEngCurrencyFormat.format(price));
        System.out.println("US English Numeric Format:" + usEngNumericFormat.format(price));
        System.out.println("US English Percentage Format: " + usEngPercentageFormat.format(0.2));
        System.out.println("US English Compact Number Format: " + usEngCompactFormat.format(2000000));

        System.out.println("-----\nParsing the values\n-----");

        try {
            System.out.println("Parse Value for Hindi Numeric Format:" + hindiNumericFormat.parse("85,00,000"));
            System.out.println("Parse Value for US English Currency:" + usEngCurrencyFormat.parse("$850,000"));
            System.out.println("Parse Value for Hindi Percentage Format:" + hindiPercentageFormat.parse("85%"));
        } catch (ParseException e) {
            System.err.println("Some exception occured:" + e.getMessage());
        }

        System.out.println("----\nFormatting Date\n----");
        LocalDate date = LocalDate.of(2023, Month.MARCH, 11);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy", hindi); // YYYY is for ISO week date
        String formattedDate = dtf.format(date); // can also be written as date.format(dtf)
        System.out.println("Formatted Date:" + formattedDate);

        LocalDate parsedDate = LocalDate.parse("11-03-2023", dtf);
        System.out.println("Parsed Date:" + parsedDate);

        System.out.println("----\nTranslation using Resource Bundle\n----");
        ResourceBundle engIndBundle = ResourceBundle.getBundle("resources.messages", Locale.getDefault());
        System.out.println("Default Locale(" + Locale.getDefault() + ") Greetings:" + engIndBundle.getString("greetings"));
        System.out.println("Default Locale(" + Locale.getDefault() + ") Welcome:" + engIndBundle.getString("welcome.message"));

        ResourceBundle hindiBundle = ResourceBundle.getBundle("resources.messages", hindi);
        System.out.println("Hindi Locale(" + hindi + ") Greetings:" + hindiBundle.getString("greetings"));
        System.out.println("Hindi Locale(" + hindi + ") Welcome:" + hindiBundle.getString("welcome.message"));
        System.out.println("Hindi Locale(" + hindi + ") Greetings of Hour without substitution:" + hindiBundle.getString("greetings.ofHours"));

        String pattern = hindiBundle.getString("greetings.ofHours");
        System.out.println("Hindi Locale(" + hindi + ") Greetings of Hour without substitution:" + MessageFormat.format(pattern, "Good", "Morning"));
    }

}
