package Tools;

public class Status {
    private boolean ok;
    private String errorMessage;

    public Status(){
        this.ok = true;
    }
    public Status(boolean ok, String msg){
        this.ok = ok;
        this.errorMessage = msg;
    }

    public boolean ok(){
        return ok;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
}
