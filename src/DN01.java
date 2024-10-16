import java.util.Random;

class NajvecjePodzaporedje {
    int[] elementi;
    int zacetniIndeks;
    int koncniIndeks;
    int skupnaVsota;

    NajvecjePodzaporedje(int[] elementi){
        this.elementi = elementi;
        this.zacetniIndeks = 0;
        this.koncniIndeks = 0;
        this.skupnaVsota = 0;
    }

    public void izpisiNajvecjePodzaporedje(){
        System.out.printf("%d,%d,%d", zacetniIndeks, koncniIndeks, skupnaVsota);
    }

    public void grobaSila(){
        System.out.println("groba sila");
        int najVsota = (int) Double.NEGATIVE_INFINITY;
        for (int i = 0; i < elementi.length; i++) {
            for (int j = i; j < elementi.length; j++) {
                int vsota = 0;
                for (int k = i; k <= j; k++) {
                    vsota += elementi[k];
                    if (vsota > najVsota || (vsota == najVsota && k - i < koncniIndeks - zacetniIndeks)) {
                        najVsota = vsota;
                        skupnaVsota = najVsota;
                        zacetniIndeks = i;
                        koncniIndeks = j;
                    }
                }
            }
        }
    }

    public void deliVladaj(){
        System.out.println("deli vladaj");
        najvecjeZaporedje(0, elementi.length - 1);
    }


    public void najvecjeZaporedje(int zac, int konec){
        System.out.println("najvecje zaporedje");
        if (zac == konec)
            return;

        int sredina = (zac + konec) / 2;
        najvecjeZaporedje(zac, sredina);
        najvecjeZaporedje(sredina+1, konec);
        najvecjeSredinskoZaporedje(zac, konec, sredina);
    }

    private int[] najvecjeSredinskoZaporedje(int zac, int konec, int sredina){
        System.out.println("najvecje sredinsko");
        int maxLevaVsota = Integer.MIN_VALUE;
        int maxDesnaVsota = Integer.MIN_VALUE;
        zacetniIndeks = sredina;
        koncniIndeks = sredina+1;

        int levaVsota = 0;
        for (int i = sredina; i >= zac; i--) {
            levaVsota += elementi[i];
            if (levaVsota > maxLevaVsota) {
                maxLevaVsota = levaVsota;
                zacetniIndeks = i;
            }
        }
        int desnaVsota = 0;
        for (int i = sredina + 1; i <= konec; i++) {
            desnaVsota += elementi[i];
            if (desnaVsota > maxDesnaVsota) {
                maxDesnaVsota = desnaVsota;
                koncniIndeks = i;
            }
        }
        

        skupnaVsota = maxDesnaVsota + maxLevaVsota;
        return new int[]{zacetniIndeks, koncniIndeks, skupnaVsota};
    }


    public void kadanovAlgoritem(){
        System.out.println("kadanov algoritem");
        int vsota = 0;
        int najVsota = elementi[0];
        int tempZacetek = 0;
        for (int i = 0; i < elementi.length; i++) {
            vsota += elementi[i];
            if (vsota < elementi[i]) {
                vsota = elementi[i];
                tempZacetek = i;
            }
            if (najVsota < vsota || (najVsota == vsota && i - tempZacetek < koncniIndeks - zacetniIndeks)) {
                zacetniIndeks = tempZacetek;
                koncniIndeks = i;
                najVsota = vsota;
                skupnaVsota = najVsota;
            }
        }
    }

}


public class DN01 {

    public static int[] generateRandomArray(int n, int range, int seed){
        int[] array = new int[n];
        Random random = new Random(seed);
        for (int i = 0; i < n; i++)
            array[i] = random.nextInt(range);
        return array;
    }

    public static void main(String[] args) {
//        int[] elementi = new int[]{-20, 36, -14, 65, 9, -57, -5, -72, -69, 9, -17, 20, -58, -72, 55, -99, -50, -37, -54, 96};
//        int[] elementi = new int[]{-1, 2, -5, 2, -1, 2, -2, 1};
//        int[] elementi = new int[]{74, 12, -34, -89, 40, -14, 91, -8, -68, -10, -31, -88, 0, 82, 56, -16, 76, 69, 86, -71};


//        NajvecjePodzaporedje najvecjePodzaporedje = new NajvecjePodzaporedje(elementi);
//        najvecjePodzaporedje.grobaSila();
//        najvecjePodzaporedje.izpisiNajvecjePodzaporedje();
//        NajvecjePodzaporedje najvecjePodzaporedje = new NajvecjePodzaporedje(elementi);
//        najvecjePodzaporedje.kadanovAlgoritem();
//        najvecjePodzaporedje.izpisiNajvecjePodzaporedje();
//        NajvecjePodzaporedje najvecjePodzaporedje = new NajvecjePodzaporedje(elementi);
//        najvecjePodzaporedje.deliVladaj();
//        najvecjePodzaporedje.izpisiNajvecjePodzaporedje();
    }
}
