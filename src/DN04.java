class Node{
    Node left, right;
    int kljuc;
    int count;

    Node(int kljuc){
        this.count = 1;
        this.kljuc = kljuc;
        left = right = null;
    }
}

class DvojiskoDrevo{
    Node root;

    DvojiskoDrevo(){
        root = null;
    }

    public void vstavi(int kljuc){
        root = insert(root, kljuc);
    }

    public Node insert(Node root, int kljuc){
        if (root == null)
            return new Node(kljuc);

        if (kljuc == root.kljuc){
            root.count++;
            return root;
        }

        if (kljuc < root.kljuc)
            root.left = insert(root.left, kljuc);
        else
            root.right = insert(root.right, kljuc);

        return root;
    }

    public void brisi(int kljuc){
        boolean[] deleted = {false};
        root = delete(root, kljuc, deleted);
        System.out.println(deleted[0]);
    }

    public Node delete(Node root, int kljuc, boolean[] deleted){
        if (root == null) {
            return null;
        }

        if (kljuc < root.kljuc) {
            root.left = delete(root.left, kljuc, deleted);
        } else if (kljuc > root.kljuc) {
            root.right = delete(root.right, kljuc, deleted);
        } else {
            deleted[0] = true;
            if (root.count > 1) {
                root.count--;
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                root.kljuc = max(root.left);
                root.left = delete(root.left, root.kljuc, deleted);
            }
        }
        return root;
    }

    public int max(Node root){
        int max = root.kljuc;
        while (root.right != null){
            max = root.right.kljuc;
            root = root.right;
        }
        return max;
    }

    public void najdi(int kljuc){
        StringBuilder sb = new StringBuilder();
        find(root, kljuc, sb);
        if (sb.length() == 0)
            System.out.println("//");
        else System.out.println(sb);
    }

    public void find(Node root, int kljuc, StringBuilder sb){
        if (root == null)
            return;

        sb.append(root.kljuc);
        if (kljuc == root.kljuc)
            return;
        else if (kljuc < root.kljuc) {
            sb.append(" -> ");
            find(root.left, kljuc, sb);
            if (root.left == null)
                sb.append("//");
        }
        else {
            sb.append(" -> ");
            find(root.right, kljuc, sb);
            if (root.right == null)
                sb.append("//");
        }
    }

    public void premiPregledIzpis(){
        if (root == null)
            System.out.println("empty");
        else {
            premi(root);
            System.out.println();
        }
    }

    public void premi(Node root){
        if (root != null){
            System.out.print(root.kljuc + "/" + root.count + " | ");
            premi(root.left);
            premi(root.right);
        }
    }

    public void vmesniPregledIzpis(){
        if (root == null)
            System.out.println("empty");
        else {
            vmesni(root);
            System.out.println();
        }
    }

    public void vmesni(Node root){
        if (root != null) {
            vmesni(root.left);
            System.out.print(root.kljuc + "/" + root.count + " | ");
            vmesni(root.right);
        }
    }


    public void obratniPregledIzpis(){
        if (root == null)
            System.out.println("empty");
        else {
            obratni(root);
            System.out.println();
        }
    }

    public void obratni(Node root){
        if (root != null) {
            obratni(root.left);
            obratni(root.right);
            System.out.print(root.kljuc + "/" + root.count + " | ");
        }
    }

    public void rotacijaLevo(int kljuc){
//        root = rotateLeft(root, kljuc);
    }
//
    public void rotacijaDesno(int kljuc) {
//        root = rotateRight(root, kljuc);
    }

    public Node rotateLeft(Node root, int key){
        if (root == null) {
            return null;
        }

        if (key < root.kljuc) {
            root.left = rotateLeft(root.left, key);
        } else if (key > root.kljuc) {
            root.right = rotateLeft(root.right, key);
        } else {
            Node pivot = root.right;
            if (pivot == null) {
                return root;
            }
            root.right = pivot.left;
            pivot.left = root;
            return pivot;
        }
        return root;
    }

//    public Node rotateRight(Node root, int key){
//        if (root == null) {
//            return null;
//        }
//
//        if (key < root.kljuc) {
//            root.left = rotateRight(root.left, key);
//        } else if (key > root.kljuc) {
//            root.right = rotateRight(root.right, key);
//        } else {
//            Node pivot = root.left;
//            if (pivot == null)
//                return root;
//            root.left = pivot.right;
//            pivot.right = root;
//            return pivot;
//        }
//        return root;
//    }
}

public class DN04 {
    public static void main(String[] args) {

    }
}
