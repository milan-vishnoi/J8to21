
import java.util.Scanner;

public class EnumExamples {

    public static void main(String args[]) {
        ConditionEnum condition;
        double temperature;
        String action;
        Scanner sc = new Scanner(System.in);
        for (ConditionEnum enumVal : ConditionEnum.values()) {
            System.out.println("Ordinal Value:%d Name:%s".formatted(enumVal.ordinal(), enumVal.name()));

        }

        System.out.println("Enter the temperature in Celsius:");
        temperature = sc.nextDouble();
        sc.close();
        if (temperature < 15) {
            condition = ConditionEnum.COLD;
        } else if (temperature > 15 && temperature < 30) {
            condition = ConditionEnum.WARM;
        } else {
            condition = ConditionEnum.HOT;
        }

        System.out.printf("Temperature:%f Condition:%s Feels like:%s\n", temperature, condition, condition.getText());

        action = switch (condition) {
            case ConditionEnum.HOT:
                yield "Turn on the AC";
            case ConditionEnum.WARM:
                yield "Turn on the fan";
            case ConditionEnum.COLD:
                yield "Turn off the fan/AC";

            // No need of default as we have covered all the cases of enum
        };

        //Can also be written as below
        /* action = switch (condition) {
            case ConditionEnum.HOT ->
                "Turn on the AC";
            case ConditionEnum.WARM ->
                "Turn on the fan";
            case ConditionEnum.COLD -> {
                yield "Turn off the fan/AC";
            }

            // No need of default as we have covered all the cases of enum
        }; */
        System.out.println("Action:" + action);
    }

}
