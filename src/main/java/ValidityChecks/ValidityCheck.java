package ValidityChecks;

import Tools.Status;

public interface ValidityCheck{
    <T> Status checkValidity(T data);
    String getName();
}
