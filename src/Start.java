public class Start {
    static Game game;
    static Frame frame;

    public static void start(){
        game.init();
        frame.updateField();
    }

    public static void main(String[] args) {
        game = new Game();
        frame = new Frame(game);
        start();

    }
}
