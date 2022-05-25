package ValidityChecks;

import Tools.Status;

public class NotNullCheck implements ValidityCheck {
    @Override
    public <T> Status checkValidity(T data) {
        if(data != null){
            return new Status();
        } else{
            return new Status(false, "Data is null");
        }
    }

    @Override
    public String getName() {
        return "NotNullCheck";
    }
}
