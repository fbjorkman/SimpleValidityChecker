import ValidityChecks.CarRegistrationNumberCheck;
import ValidityChecks.NotNullCheck;
import ValidityChecks.PersonalNumberCheck;

public class Main {
    public static void main(String[] args) {
        ValidityChecker pb = new ValidityChecker("CheckPersonalNumber");
        ValidityChecker rn = new ValidityChecker("CheckRegistrationNumber");
        pb.addChecks(new NotNullCheck());
        pb.addChecks(new PersonalNumberCheck());
        rn.addChecks(new NotNullCheck());
        rn.addChecks(new CarRegistrationNumberCheck());

        pb.runChecks(null);
        pb.runChecks("199504121931");
        pb.runChecks("199504121930");
        pb.runChecks("19950412-1931");
        pb.runChecks("9504121931");
        pb.runChecks(9504121931L);
        pb.runChecks(4121931);
        pb.runChecks(4121935);

        rn.runChecks(null);
        rn.runChecks("uas234");
        rn.runChecks("234asd");
        rn.runChecks("adsnads");
        rn.runChecks("gkm.12");
    }
}
