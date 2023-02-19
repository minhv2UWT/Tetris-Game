import model.Board;

public class SandBox {

    public static void main(final String[] theArgs) {
        Board b = new Board();
        b.newGame();
        System.out.println(b);

        b.step();
        System.out.println(b);
        b.rotateCW();
        System.out.println(b);
        b.rotateCW();
        System.out.println(b);
        b.rotateCW();
        System.out.println(b);
        b.rotateCW();
        System.out.println(b);
        b.drop();
        System.out.println(b);

    }

}
