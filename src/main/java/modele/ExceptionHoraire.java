package modele;

public class ExceptionHoraire extends Exception {
    private int chCodeErreur;
    public ExceptionHoraire(int parCodeErreur) {
        super();
        chCodeErreur = parCodeErreur;
    }

    public int getCodeErreur(){
        return chCodeErreur;
    }
}
