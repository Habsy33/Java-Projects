import javax.swing.*;

public class GameFrame extends JFrame {
    //frame classes always need to extend JFrame to have access to JFrame methods

    GameFrame(){


        this.add(new GamePanel());
        // GamePanel panel = new GamePanel(); code above is the same as
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        //makes our window open in middle of window

        //the code found within this class creates the frame for our snake game


    }
}
