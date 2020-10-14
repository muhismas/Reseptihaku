
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reseptihaku {

    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        System.out.print("Mistä luetaan? ");
        String tiedosto = lukija.nextLine();
        Scanner tiedostonLukija;
        System.out.println("\nKomennot:");
        System.out.println("listaa - listaa reseptit\nlopeta - lopettaa ohjelman\n"
                + "hae nimi - hakee reseptiä nimen perusteella\n"
                + "hae keittoaika - hakee reseptiä keittoajan perusteella\n"
                + "hae aine - hakee reseptiä raaka-aineen perusteella");
        try {
            while (true) {
                System.out.print("\nSyötä komento: ");
                String komento = lukija.nextLine();

                if (komento.equals("lopeta")) {
                    break;
                }

                if (komento.equals("listaa")) {

                    tiedostonLukija = new Scanner(new File(tiedosto));

                    System.out.println("\nReseptit:");
                    String rivi = tiedostonLukija.nextLine();
                    System.out.print(rivi);
                    rivi = tiedostonLukija.nextLine();
                    System.out.println(", keittoaika: " + rivi);
                    while (tiedostonLukija.hasNextLine()) {
                        rivi = tiedostonLukija.nextLine();
                        if (rivi.equals("")) {
                            rivi = tiedostonLukija.nextLine();
                            System.out.print(rivi);
                            rivi = tiedostonLukija.nextLine();
                            System.out.println(", keittoaika: " + rivi);
                        }
                    }
                }

                if (komento.equals("hae nimi")) {
                    tiedostonLukija = new Scanner(new File(tiedosto));
                    System.out.print("Mitä haetan? ");
                    String resepti = lukija.nextLine();
                    System.out.println("\nReseptit:");
                    while (tiedostonLukija.hasNextLine()) {
                        String rivi = tiedostonLukija.nextLine();
                        if(rivi.contains(resepti)){
                            rivi += ", keittoaika: "+tiedostonLukija.nextLine();
                            System.out.println(rivi);
                        }
                        while (tiedostonLukija.hasNextLine()) {
                            String rivi2 = tiedostonLukija.nextLine();
                            if (rivi2.equals("")) {
                                break;
                            }
                        }
                    }
                }

                if (komento.equals("hae keittoaika")) {
                    tiedostonLukija = new Scanner(new File(tiedosto));
                    System.out.print("Keittoaika korkeintaan: ");
                    int keittoaika = Integer.valueOf(lukija.nextLine());
                    System.out.println("\nReseptit:");
                    String rivi, rivi2;
                    rivi = tiedostonLukija.nextLine();
                    rivi2 = tiedostonLukija.nextLine();
                    rivi += ", keittoaika: " + rivi2;
                    if (Integer.valueOf(rivi2) <= keittoaika) {
                        System.out.println(rivi);
                    }
                    while (tiedostonLukija.hasNextLine()) {
                        rivi = tiedostonLukija.nextLine();
                        if (rivi.equals("")) {
                            rivi = tiedostonLukija.nextLine();
                            rivi2 = tiedostonLukija.nextLine();
                            rivi += ", keittoaika: " + rivi2;
                            if (Integer.valueOf(rivi2) <= keittoaika) {
                                System.out.println(rivi);
                            }
                        }
                    }
                }

                if (komento.equals("hae aine")) {
                    tiedostonLukija = new Scanner(new File(tiedosto));
                    System.out.print("Mitä raaka-ainetta haetaan:");
                    String raakaAine = lukija.nextLine();
                    System.out.println("\nReseptit:");
                    while (tiedostonLukija.hasNextLine()) {
                        String rivi = tiedostonLukija.nextLine();
                        rivi += ", keittoaika: " + tiedostonLukija.nextLine();

                        while (tiedostonLukija.hasNextLine()) {
                            String rivi2 = tiedostonLukija.nextLine();
                            if (rivi2.equals(raakaAine)) {
                                System.out.println(rivi);
                            }
                            if (rivi2.equals("")) {
                                break;
                            }
                        }
                    }

                }

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reseptihaku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
