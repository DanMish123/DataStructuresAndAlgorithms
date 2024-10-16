class UsmerjenGraf{
    int stVozlisc;
    int[][] graf;

    UsmerjenGraf(int stVozlisc){
        this.stVozlisc = stVozlisc;
        graf = new int[stVozlisc][stVozlisc];
        for (int i = 0; i < stVozlisc; i++) {
            for (int j = 0; j < stVozlisc; j++) {
                if (i == j) graf[i][i] = 0;
                else graf[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    void dodajPovezavo(int vozlisceOd, int vozlisceDo, int cena){
        if (vozlisceOd >= 0 && vozlisceDo >= 0 && vozlisceOd < stVozlisc && vozlisceDo < stVozlisc)
            graf[vozlisceOd][vozlisceDo] = cena;
    }

    void izpisiNajkrajsePoti(int vozlisceOd){
        int[] distances = new int[stVozlisc];
        boolean[] visited = new boolean[stVozlisc];

        for (int i = 0; i < stVozlisc; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distances[vozlisceOd] = 0;

        for (int i = 0; i < stVozlisc - 1; i++) {
            int u = minDistance(distances, visited);
            visited[u] = true;
            for (int v = 0; v < stVozlisc; v++) {
                if (!visited[v] && graf[u][v] != Integer.MAX_VALUE && distances[u] != Integer.MAX_VALUE && distances[u] + graf[u][v] < distances[v]){
                    distances[v] = distances[u] + graf[u][v];
                }
            }

        }

        System.out.println("V ... Cena");
        for (int i = 0; i < stVozlisc; i++) {
            System.out.println(i + " ... " + (distances[i] == Integer.MAX_VALUE ? "Inf" : distances[i]));
        }
    }

    int minDistance(int[] distances, boolean[] visited){
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < stVozlisc; i++) {
            if (distances[i] <= minDistance && !visited[i]){
                minDistance = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }
}


public class DN05 {
    public static void main(String[] args) {
        UsmerjenGraf g = new UsmerjenGraf(4);
        g.dodajPovezavo(0, 1, 1);
        g.dodajPovezavo(0, 3, 2);
        g.dodajPovezavo(1, 2, 3);
        g.dodajPovezavo(3, 2, 1);
        g.izpisiNajkrajsePoti(0);
    }
}
