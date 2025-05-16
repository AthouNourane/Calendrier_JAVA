package modele;

public class ClientCalendrierDuMois {
    public static void main (String [] Args){
        CalendrierDuMois moisDeMars = new CalendrierDuMois(3, 2025);
        CalendrierDuMois moisDeAvril = new CalendrierDuMois(4, 2025);
        CalendrierDuMois moisDeMai = new CalendrierDuMois(5, 2025);
        CalendrierDuMois moisDeJuin = new CalendrierDuMois(6, 2025);
        System.out.println(moisDeMars);
        System.out.println(moisDeAvril);
        System.out.println(moisDeMai);
        System.out.println(moisDeJuin);
    }
}
