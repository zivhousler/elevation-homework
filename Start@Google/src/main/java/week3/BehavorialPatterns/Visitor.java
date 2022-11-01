package week3.BehavorialPatterns;

public interface Visitor {
    void visit(Group group);
    void visit(User user);
    void visit(Asset asset);
}
