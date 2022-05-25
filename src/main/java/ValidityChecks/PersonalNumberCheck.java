package ValidityChecks;

import Tools.Status;
import Tools.Tuple;

public class PersonalNumberCheck implements ValidityCheck{
    @Override
    public <T> Status checkValidity(T data) {
        String personalNumber;
        Tuple<Status, String> parseResult = parsePersonalNumber(data);
        if(parseResult.var1.ok()){
            personalNumber = parseResult.var2;
        } else{
            return parseResult.var1;
        }
        Status monthDayCheck = monthDayCheck(personalNumber);
        if(monthDayCheck.ok()) {
            return checkPersonalNumberCheckSum(personalNumber);
        } else{
            return monthDayCheck;
        }
    }

    private <T> Tuple<Status, String> parsePersonalNumber(T data){
        if(data instanceof String dataString){
            String digits = dataString.replaceAll("[ -]","");
            if(digits.length() == 10){
                return new Tuple<>(new Status(), digits);
            } else if (digits.length() == 12) {
                return new Tuple<>(new Status(), digits.substring(2));
            } else{
                return new Tuple<>(new Status(false, "Received: " + data + ". Could not parse into a " +
                        "personal number as the personal number must contain 10 or 12 digits."), null);
            }
        } else if (data instanceof Long dataLong) {
            String digits = Long.toString(dataLong);
            if(digits.length() <= 10){
                return new Tuple<>(new Status(), String.format("%010d", dataLong));
            } else if (digits.length() == 12) {
                return new Tuple<>(new Status(), digits.substring(2));
            } else{
                return new Tuple<>(new Status(false, "Received: " + data + ". Could not parse into a " +
                        "personal number as the personal number must contain 10 or 12 digits."), null);
            }

        } else if(data instanceof Integer dataInt){
            return new Tuple<>(new Status(), String.format("%010d", dataInt));
        } else{
            return new Tuple<>(new Status(false, "The personal number is an invalid type."), null);
        }
    }

    private Status checkPersonalNumberCheckSum(String personalNumber){
        int[] numbers = new int[10];
        for(int i = 0; i < 10; i++){
            numbers[i] = Integer.parseInt(String.valueOf(personalNumber.charAt(i)));
        }

        int controlNumber = numbers[9];
        int sum = 0;
        for(int i = 0; i < 9; i++){
            sum += getDigitSum(numbers[i] * (2 - i%2));
        }
        sum = sum % 10;
        sum = 10 - sum;
        sum = sum % 10;
        if(sum == controlNumber) {
            return new Status();
        } else{
            return new Status(false, "The control number was calculated to: " + sum +
                    " but the received one was: " + controlNumber);
        }
    }

    private Status monthDayCheck(String personalNumber){
        int month = Integer.parseInt(personalNumber.substring(2, 4));
        int day = Integer.parseInt(personalNumber.substring(4, 6));
        if(!(month >= 1 && month <= 12) || !(day >= 1 && day <= 31)){
            return new Status(false, "Invalid day or month. The day needs to be an integer between 1 and 31 " +
                    "and the month an integer between 1 and 12. Month received: " + month + " Day received: " + day);
        }
        return new Status();
    }

    private int getDigitSum(int number){
        int sum = 0;
        while(number > 0){
            int digit = number % 10;
            sum += digit;
            number /= 10;
        }
        return sum;
    }

    @Override
    public String getName() {
        return "PersonalNumberCheck";
    }
}
