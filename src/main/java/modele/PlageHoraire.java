package modele;

public class PlageHoraire implements Comparable<PlageHoraire>{
    private static final int DUREE_MINIMUM = 30;
    private Horaire chHoraireDebut;
    private Horaire chHoraireFin;

    public PlageHoraire (Horaire parHoraireDebut, Horaire parHoraireFin) throws ExceptionHoraire {
        if (parHoraireDebut.toMinutes() > parHoraireFin.toMinutes())
            throw new ExceptionHoraire(0);
        chHoraireDebut = parHoraireDebut;
        chHoraireFin = parHoraireFin;
    }

    public Horaire getChHoraireDebut(){
        return chHoraireDebut;
    }

    public Horaire getChHoraireFin(){
        return chHoraireFin;
    }

    /**
     * Renvoie true lorsque la plage horaire (this) est valide.
     * @return boolean
     */
    public boolean estValide () {
        return chHoraireFin.toMinutes() - chHoraireDebut.toMinutes() >= DUREE_MINIMUM;
    }

    /**
     * Renvoie la durée en minute de la plage horaire (this).
     * @return int
     */
    public int duree () {
        return chHoraireFin.toMinutes() - chHoraireDebut.toMinutes();
    }

    public int compareTo (PlageHoraire parPlageComparaison) {
        if (parPlageComparaison.chHoraireDebut.toMinutes() > this.chHoraireFin.toMinutes()) return -1;
        else if (this.chHoraireDebut.toMinutes() > parPlageComparaison.chHoraireFin.toMinutes()) return 1;
        else return 0;
        }


    public String toString () {
        return chHoraireDebut + " à " + chHoraireFin;
    }
}
