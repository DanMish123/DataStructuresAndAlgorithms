class ZgoscenaTabela{
    int p; int m; int size;
    int c1; int c2; int matches;
    int[] zgoscenaTabela;

    ZgoscenaTabela(int p, int m, int c1, int c2){
        this.p = p;
        this.m = m;
        this.c1 = c1;
        this.c2 = c2;
        zgoscenaTabela = new int[m];
        this.size = 0;
        this.matches = 0;
    }

    public int hash(int k){
        return (k * this.p) % this.m;
    }

    public int rehash(int k, int i){
        return (hash(k) + this.c1 * i + this.c2 * i * i) % this.m;
    }

    public void povecajTabelo(){
        int oldSize = m;
        int[] oldTable = zgoscenaTabela;
        m = 2 * m + 1;
        zgoscenaTabela = new int[m];
        size = 0;
        for (int i = 0; i < oldSize; i++)
            if (oldTable[i] != 0)
                vstavi(oldTable[i]);
    }

    public void vstavi(int kljuc){
        if (najdi(kljuc))
            return;
        int i = 0;
        while (i < m){
            int index = rehash(kljuc, i);
            if (zgoscenaTabela[index] == 0) {
                zgoscenaTabela[index] = kljuc;
                size++;
                return;
            } else {
                matches++;
            }
            i++;
        }
        povecajTabelo();
        vstavi(kljuc);
    }

    public boolean najdi(int kljuc){
        for (int i = 0; i < zgoscenaTabela.length; i++) {
            int index = rehash(kljuc, i);
            if (zgoscenaTabela[index] == kljuc)
                return true;
            if (zgoscenaTabela[index] == 0)
                return false;
            if (i == m) break;
        }
        return false;
    }

    public void brisi(int kljuc){
        if (najdi(kljuc)){
            for (int i = 0; i < zgoscenaTabela.length; i++) {
                int index = rehash(kljuc, i);
                if (zgoscenaTabela[index] == kljuc){
                    zgoscenaTabela[index] = 0;
                    size--;
                } else if (zgoscenaTabela[index] == 0)
                    return;
            }
        }
    }

    public void izpisi(){
        for (int i = 0; i < zgoscenaTabela.length; i++)
            if (zgoscenaTabela[i] != 0)
                System.out.println(i + ": " + zgoscenaTabela[i]);
    }

    public void steviloSovpadanj(){
        System.out.println(matches);
    }

}

public class DN03 {
    public static void main(String[] args) {
        ZgoscenaTabela ht = new ZgoscenaTabela(7, 11, 5, 13);

        ht.vstavi(11); ht.vstavi(12); ht.vstavi(22); ht.vstavi(11); ht.vstavi(28);
        ht.vstavi(17); ht.vstavi(2); ht.vstavi(34); ht.vstavi(77); ht.vstavi(128);

        ht.izpisi();
        System.out.println("--");
        ht.steviloSovpadanj();
    }
}
