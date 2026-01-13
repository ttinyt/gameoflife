

public class Rutenett {
    
    public int antRader;
    public int antKolonner;
    public Celle[][] rutene;

    public Rutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        this.rutene = new Celle[antRader][antKolonner];
    }


    public void lagCelle(int r, int k) {
        if (Math.random() <= 0.3333) { // 1/3 sjanse for å være levende
            Celle celle = new Celle();
            celle.settLevende();
            rutene[r][k] = celle;
        }
        else {
            rutene[r][k] = new Celle(); // Døde celler, er døde ved oppretting
        }
    }
    
    public void fyllMedTilfeldigeCeller() {
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                lagCelle(r, k);
            }
        }
    }

    public Celle hentCelle(int r, int k) {
        if (r < 0 || r >= antRader || k < 0 || k >= antKolonner) {
            return null; 
        }
        return rutene[r][k];
    }

    public void tegnRutenett() { //prøvd å gjøre likt som i eksempelet av utskriften
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }

        for (int r = 0; r < antRader; r++) {
            
            for (int k = 0; k < antKolonner; k++) {
                System.out.print("+---");
            }
                System.out.println("+");

            for (int k = 0; k < antKolonner; k++) {
                System.out.print("| ");
                if (rutene[r][k] != null){
                    char tegn = rutene[r][k].hentStatusTegn();
                    System.out.print(tegn + " ");
                } else {
                    char tegn = ' ';
                    System.out.print(tegn + " ");
                }
            }
            System.out.println("|"); 
        }

        for (int k = 0; k < antKolonner; k++) {
            System.out.print("+---");
        }

        System.out.println("+"); 
    }

    public void settNaboer(int r, int k) {
        Celle celle = hentCelle(r, k);
        if (celle == null) {
            return; 
        }

        for (int naboRad = r - 1; naboRad <= r + 1; naboRad++) {
            for (int naboKol = k - 1; naboKol <= k + 1; naboKol++) {
                if (naboRad != r || naboKol != k) { 
                    Celle nabo = hentCelle(naboRad, naboKol);
                    if (nabo != null) {
                        celle.leggTilNabo(nabo);
                    }
                }
            }
        }
    }

    public void kobleAlleCeller() {
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                settNaboer(r, k);
            }
        }
    }

    public int antallLevende() {
        int antall = 0;
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                if (rutene[r][k] != null && rutene[r][k].erLevende()) {
                    antall++;
                }
            }
        }
        return antall;
    }
}