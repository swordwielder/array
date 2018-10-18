class HomeWork3
{
    static class Node
    {
        int value;
        Node leftChild;
        Node rightChild;
    }

    static Node root;

    static void insert(int value)
    {
        if(root == null)
        {
            root = new Node();
            root.value = value;
            return;
        }
        Node p = root;
        Node q = null;
        while(p != null)
        {
            q = p;
            if(value < p.value)
            {
                p = p.leftChild;
            }
            else
            {
                p = p.rightChild;
            }
        }
        Node x = new Node();
        x.value = value;
        if(value < q.value)
        {
            q.leftChild = x;
        }
        else
        {
            q.rightChild = x;
        }
    }

    static void print(Node x)
    {
        if(x != null)
        {
            print(x.leftChild);
            System.out.println(x.value);
            print(x.rightChild);
        }
    }

    static void print()
    {
        print(root);
    }

    static void clear()
    {
        root = null;
    }

    static int max()
    {
        if (root == null) {
            return -1;
        } else {
            Node x = root;
            while(x.rightChild != null) {
                x = x.rightChild;
            }
            return x.value;
        }
    }

    static int size(Node x)
    {
        if (x != null) {
            return 1+size(x.leftChild)+size(x.rightChild);
        } else 
            return 0;
    }

    static int size()
    {
        return size(root);
    }

    static boolean contains(int value, Node x)
    {
        if (x != null) {
            if (value==x.value)
                return true;
            else{
                if (value < x.value && x.leftChild !=null)
                    return contains(value, x.leftChild);
                else if (value >= x.value && x.rightChild !=null)
                    return contains(value, x.rightChild);
            }
        }
        return false; 
    }
    static boolean contains(int value)
    {
        return contains(value, root);
    }

    public static void main(String[] args)
    {

        insert(3);
        insert(2);
        insert(1);
        insert(6);
        insert(8);
        insert(5);
        insert(9);
        print(root);

        System.out.println("Max is: " + max());
        System.out.println("Size is: " + size());
        for (int i =0; i< 10; i++){
            System.out.println("Contains "+ i + "? " + contains(i));
        }
    }
}
