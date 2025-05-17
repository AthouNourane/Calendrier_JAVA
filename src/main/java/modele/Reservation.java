package modele;

public class Reservation implements Comparable<Reservation>{
    private Date dateReservation;
    private PlageHoraire plageHoraireReservation;
    private String intituleReservation;
    private String niveauReservation;

    public Reservation (Date parDate, PlageHoraire parPlageHoraire, String parIntitule, String parNiveau) throws ExceptionReservation {
        if (parIntitule == null)
            throw new ExceptionReservation(0);
        dateReservation = parDate;
        plageHoraireReservation = parPlageHoraire;
        intituleReservation = parIntitule;
        niveauReservation = parNiveau;
    }

    public String toString() {
        return "Réservation " + intituleReservation + " (" + niveauReservation + ") "
                + "le : " + dateReservation + " de " + plageHoraireReservation;
    }

    public Date getDate() {
        return dateReservation;
    }

    public PlageHoraire getHoraire() {
        return plageHoraireReservation;
    }

    public String getIntitule() {
        return intituleReservation;
    }

    public String getNiveau(){
        return niveauReservation;
    }

    @Override
    public int compareTo(Reservation parReservation) {
        if (parReservation.dateReservation.compareTo(dateReservation) == 0)
            return parReservation.plageHoraireReservation.compareTo(plageHoraireReservation);
        return parReservation.dateReservation.compareTo(dateReservation);
    }

    public boolean estValide(){
        return dateReservation.estValide() && plageHoraireReservation.estValide() && intituleReservation != null
                && !intituleReservation.isEmpty() && niveauReservation != null && !niveauReservation.isEmpty();
    }
}
