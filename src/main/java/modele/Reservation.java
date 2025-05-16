package modele;

public class Reservation implements Comparable<Reservation>{
    private Date dateReservation;
    private PlageHoraire plageHoraireReservation;
    private String intituleReservation;

    public Reservation (Date parDate, PlageHoraire parPlageHoraire, String parIntitule) throws ExceptionReservation {
        if (parIntitule == null)
            throw new ExceptionReservation(0);
        dateReservation = parDate;
        plageHoraireReservation = parPlageHoraire;
        intituleReservation = parIntitule;
    }

    public String toString() {
        return "Réservation " + intituleReservation + " le : " + dateReservation + " de " + plageHoraireReservation;
    }

    public Date getDate() {
        return dateReservation;
    }

    public String getString() {
        return intituleReservation;
    }

    @Override
    public int compareTo(Reservation parReservation) {
        if (parReservation.dateReservation.compareTo(dateReservation) == 0)
            return parReservation.plageHoraireReservation.compareTo(plageHoraireReservation);
        return parReservation.dateReservation.compareTo(dateReservation);
    }

    public boolean estValide(){
        return dateReservation.estValide() && plageHoraireReservation.estValide() && intituleReservation != null && intituleReservation.length() != 0;
    }
}
