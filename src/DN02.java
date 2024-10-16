
import java.util.Comparator;
import java.util.Random;
//
//class AlphabeticalComparator implements Comparator<String> {
//    @Override
//    public int compare(String s1, String s2) {
//        int alphabeticalComparison = s1.compareTo(s2);
//
//        return alphabeticalComparison;
//    }
//}

class Occurence{
    int occurences;
    String element;

    Occurence(String element){
        this.element = element;
        this.occurences = 1;
    }

    public void increaseOccurences() {this.occurences++;}
    public void decreaseOccurences() {this.occurences--;}
    public int getOccurences(){return this.occurences;}
}

class TabelaTabel{
    Comparator<String> comparator;
    Occurence[] occurences;
    public String[][] table;

    TabelaTabel(Comparator<String> comparator){
        this.comparator = comparator;
        this.table = new String[0][];
        this.occurences = new Occurence[0];
    }

    int binarySearch(String element, String[] t){
        int left = 0;
        int right = t.length - 1;

        while (left <= right){
            int middle = left + (right - left) / 2;
            String middleElement = t[middle];
            if (middleElement != null) {
                int cmp = comparator.compare(element, middleElement);
                if (cmp == 0)
                    return middle;
                else if (cmp < 0)
                    right = middle - 1;
                else
                    left = middle + 1;
            } else {
                int cmp = comparator.compare(element, "");
                if (cmp == 0)
                    return -1;
                else if (cmp < 0)
                    right = middle - 1;
                else
                    left = middle + 1;
            }
        }
        return -left - 1;
    }

    private String[] merge(String[] t1, String[] t2) {
        String[] result = new String[t1.length + t2.length];
        int indexT1 = 0, indexT2 = 0, indexResult = 0;

        while (indexT1 < t1.length && indexT2 < t2.length) {
            if (comparator.compare(t1[indexT1], t2[indexT2]) < 0)
                result[indexResult++] = t1[indexT1++];
            else result[indexResult++] = t2[indexT2++];
        }

        while (indexT1 < t1.length)
            result[indexResult++] = t1[indexT1++];
        while (indexT2 < t2.length)
            result[indexResult++] = t2[indexT2++];

        return result;
    }

    public int occurenceIndex(String element){
        for (int i = 0; i < occurences.length; i++) {
            if (occurences[i].element.equals(element))
                return i;
        }
        return -1;
    }

    public int findNull(String[] t) {
        for (int i = 0; i < t.length; i++)
            if (t[i] == null)
                return i;
        return -1;
    }

    public boolean vstavi(String element){
        for (int i = 0; i < occurences.length; i++) {
            if (occurences[i].element.equals(element)) {
                occurences[i].increaseOccurences();
                return true;
            }
        }

        Occurence newOccurrence = new Occurence(element);
        Occurence[] newOccurrences = new Occurence[occurences.length + 1];
        System.arraycopy(occurences, 0, newOccurrences, 0, occurences.length);
        newOccurrences[occurences.length] = newOccurrence;
        occurences = newOccurrences;

        String[] temp = {element};
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null || table[i].length == 0) {
                table[i] = temp;
                for (int j = i - 1; j >= 0; j--)
                    table[j] = new String[0];
                return true;
            } else {
                int nullIndex = findNull(table[i]);
                if (nullIndex != -1) {
                    table[i][nullIndex] = element;
                    java.util.Arrays.sort(table[i]);
                    return true;
                } else {
                    temp = merge(temp, table[i]);
                    table[i] = new String[0];
                }
            }
        }

        String[][] newTable = new String[table.length + 1][];
        System.arraycopy(table, 0, newTable, 0, table.length);
        newTable[table.length] = temp;
        table = newTable;
        return true;
    }

    public boolean najdi(String element) {
        for (int i = 0; i < table.length; i++) {
            int index = binarySearch(element, table[i]);
            if (index >= 0 && element != null) {
                System.out.println(true);
                return true;
            }
        }
        System.out.println(false);
        return false;
    }

    public boolean izbrisi(String element) {
        for (int i = 0; i < table.length; i++) {
            int index = binarySearch(element, table[i]);
            if (index >= 0) {
                if (occurences[index].getOccurences() > 1)
                    occurences[index].decreaseOccurences();
                else table[i][index] = null;
                System.out.println(true);
                return true;
            }
        }
        System.out.println(false);
        return false;
    }

    public void izpisi(){
        if (table.length == 0) {
            System.out.println("prazen");
            return;
        }

        int c = 0;
        for (int i = 0; i < table.length; i++) {
            String[] subtable = table[i];
            for (int j = 0; j < subtable.length; j++) {
                if (subtable[j] != null) {
                    c++;
                    break;
                }
            }
        }
        if (c == 0) {
            System.out.println("prazen");
            return;
        }

        for (String[] subtable : table){
            if (subtable.length == 0){
                System.out.print("...");
            } else {
                for (int i = 0; i < subtable.length; i++) {
                    String element = subtable[i];
                    if (element == null) {
                        System.out.print("x");
                    } else {
                        int occurrenceIndex = occurenceIndex(element);
                        int occurrences = this.occurences[occurrenceIndex].getOccurences();
                        System.out.print(element + "/" + occurrences);
                    }
                    if (i < subtable.length - 1)
                        System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}

public class DN02 {
    public static void main(String[] args) {
//        AlphabeticalComparator comparator = new AlphabeticalComparator();
//
//        TabelaTabel tabelaTabel = new TabelaTabel(comparator);
//
//        tabelaTabel.vstavi("abc");
//        tabelaTabel.vstavi("abcd");
//        tabelaTabel.vstavi("dca");
//
//        tabelaTabel.izpisi();
//
//        tabelaTabel.izbrisi("dca");
//
//        tabelaTabel.izpisi();
//
//        tabelaTabel.izbrisi("abcd");
//
//        tabelaTabel.izpisi();
//
//        tabelaTabel.izbrisi("abc");
//        tabelaTabel.izbrisi("dca");
//
//        tabelaTabel.izpisi();
    }
}
