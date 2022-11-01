package week3.BehavorialPatterns;

public class ExportToJSON implements Visitor{
    @Override
    public void visit(Group group) {
        System.out.println("Writing a group to file, " + group);
    }

    @Override
    public void visit(User user) {
        System.out.println("Writing a user to file, " + user);
    }

    @Override
    public void visit(Asset asset) {
        System.out.println("Writing an asset to file, " + asset);
    }
}
