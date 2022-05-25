package ValidityChecks;

import Tools.Status;

public class CarRegistrationNumberCheck implements ValidityCheck{
    @Override
    public <T> Status checkValidity(T data) {
        if(data instanceof String dataString){
            String regNumber = dataString.replaceAll("[ -]","");
            return checkFormat(regNumber);
        } else{
            return new Status(false, "The car registration number is in an invalid format. " +
                    "Must be represented as a String. Received: " + data);
        }
    }

    private Status checkFormat(String regNumber){
        if(!regNumber.matches("^\\w+$")){
             return new Status(false, "The car registration number does not only consist of letters (a-z) " +
                     "and digits (0-9). Received: " + regNumber);
        }
        if(regNumber.length() != 6){
            return new Status(false, "The car registration number is not exactly 6 characters. " +
                    "Received: " + regNumber);
        }
        if(!regNumber.substring(0,3).matches("\\p{Alpha}{3}") ||
                !regNumber.substring(3, 5).matches("\\d{2}") ||
                !regNumber.substring(5, 6).matches("\\w")){
            return new Status(false, "The car registration number is not on format 3 letters followed " +
                    "by 2 digits and the last character must be either a letter or a digit. Received: " + regNumber);
        }
        return new Status();
    }

    @Override
    public String getName() {
        return "CarRegistrationNumberCheck";
    }
}
