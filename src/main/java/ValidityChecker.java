import Tools.Status;
import ValidityChecks.ValidityCheck;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ValidityChecker {
    private final Logger LOGGER;
    private final ArrayList<ValidityCheck> validityChecks;
    private final String jobName;

    public ValidityChecker(String jobName) {
        LOGGER = Logger.getLogger(ValidityChecker.class);
        PropertyConfigurator.configure("log4j.properties");
        validityChecks = new ArrayList<>();
        this.jobName = jobName;
    }

    public void addChecks(ValidityCheck check){
        validityChecks.add(check);
    }

    public <T> void runChecks(T data){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        for(ValidityCheck check : validityChecks){
            Status status = check.checkValidity(data);
            if(!status.ok()){
                LOGGER.info(dtf.format(LocalDateTime.now()) + " | Job: " + jobName + " | Data: " + data +
                        " | Failed check: " + check.getName() + " | Error message: " + status.getErrorMessage());
            }
        }
    }
}
