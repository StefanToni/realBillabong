package AI;

public class Tree {
    Nodee root;

    public Tree() {
        root = new Nodee();
    }

    public Tree(Nodee root) {
        this.root = root;
    }

    public Nodee getRoot() {
        return root;
    }

    public void setRoot(Nodee root) {
        this.root = root;
    }

    public void addChild(Nodee parent, Nodee child) {
        parent.getChildArray().add(child);
    }

}