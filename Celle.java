
class Celle {

    boolean levende;
    Celle[] naboer;
    int antNaboer;
    int antLevendeNaboer;

    public Celle() {
        this.levende = false; // død
        this.naboer = new Celle[8];
        this.antNaboer = 0;
        this.antLevendeNaboer = 0;
    }

    public void settDoed() {
        this.levende = false;
    }

    public void settLevende() {
        this.levende = true; // true=levende
    }

    public boolean erLevende() {
        return this.levende;
    }

    public char hentStatusTegn() {
        if (this.levende) {
            return 'O'; // hvis true aka levende
        } else {
            return '.';
        }
    }

    public void leggTilNabo(Celle nabo) {
        if (this.antNaboer < 8) {
            this.naboer[this.antNaboer] = nabo;
            this.antNaboer++;
        }

    }

    public void tellLevendeNaboer() {
        this.antLevendeNaboer = 0; // nullstiller telleren
        for (int i = 0; i < this.naboer.length; i++) { // i som teller for å gå gjennom arrayet av naboceller
            if (this.naboer[i] != null && this.naboer[i].erLevende()) {
                this.antLevendeNaboer++;
            }
        }
    }

    public void oppdaterStatus() { // Ved færre enn to levende naboceller dør cellen (underpopulasjon). //Ved to
                                   // eller tre levende naboceller vil cellen leve videre.// Hvis cellen har mer
                                   // enn tre levende naboceller, vil den dø (overpopulasjon).
        tellLevendeNaboer();
        if (this.levende) {
            if (this.antLevendeNaboer < 2 || this.antLevendeNaboer > 3) {
                settDoed();
            }
        } else { // else aka hvis cellen er død
            if (this.antLevendeNaboer == 3) {
                settLevende();
            }
        }
    }

}