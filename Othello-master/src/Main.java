import Controller.MainController;
import View.Window;

public class Main {
    public static void main(String[] args){
        MainController c = new MainController();
        Window w = new Window();
     // Window w1 = new Window();
        c.start(w);

    }

}
