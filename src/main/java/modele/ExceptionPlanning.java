package modele;

public class ExceptionPlanning extends Exception{
    private int chCodeErreur;
    public ExceptionPlanning(int parCodeErreur){
        super();
        chCodeErreur = parCodeErreur;
    }
    public int getCodeErreur(){
        return chCodeErreur;
    }
}
