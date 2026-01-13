
public class Verden {
    public Rutenett rutenett;
    public int genNr;

    public Verden(int antRader, int antKolonner) {
        this.rutenett = new Rutenett(antRader, antKolonner);
        this.genNr = 0;
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    public void tegn() {
        System.out.println("Generasjon nr " + genNr + ":");
        rutenett.tegnRutenett();
        System.out.println("Antall levende celler: " + rutenett.antallLevende());
    }

    public void oppdatering() {
        for (int r = 0; r < rutenett.antRader; r++) {
            for (int k = 0; k < rutenett.antKolonner; k++) {
                Celle celle = rutenett.hentCelle(r, k);
                celle.tellLevendeNaboer();
            }
        }

        for (int r = 0; r < rutenett.antRader; r++) {
            for (int k = 0; k < rutenett.antKolonner; k++) {
                Celle celle = rutenett.hentCelle(r, k);
                celle.oppdaterStatus();
            }
        }
        genNr++;
    }
}

