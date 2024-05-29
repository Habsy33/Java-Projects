import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    //the GamePanel class needs to be a subclass of the JPanel, to have access to its methods
    // we implement the ActionListener interface which includes the action performed method

    boolean paused = false;
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    // above are our declaration of variables and attributes that we'll need to make the game work

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();


    }
    //first method here is the Game Panel method
    //this simply sets all the sizing and configurations we need
    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();


    }
    // the startGame method is used to simply start the game
    //it starts as soon as the class is called in the SnakeGame.java code
    // timer is used as a control to end the game

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

    }
    //paintComponent method uses the Graphics class as a parameter
    // super keyword here is used to access the parent classes paintComponent method

    public void draw(Graphics g) {

        if(running) {

            //the draw method is used to show the game
            //the snake, apple and so on is shown via the code in this block


            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

                // counter starts at 0, for each element of the counter that is less
                // than the screen height / unit size (box size)- draw a horizontal line. And draw a vertical line
                // after each loop increment the counter
                // basically using for loop to draw grids

            }
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            //we set the color and shape of the apple

            for (int i = 0; i < bodyParts; i++) {
                //for i being 0 and less than the number of the snakes bodyparts':

                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    //when the snake's body part is at 0, i.e. at the head:
                    //make the color of that part green

                } else {
                    g.setColor(new Color(45, 180, 0));

                    if (bodyParts > 20){
                        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
                        //added my own extra feature- if user gets a score higher than 15 the snake changes color

                    }
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }

            }


            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score " + applesEaten)) /2, g.getFont().getSize());

            //the avoe code is for the text
            // we set the color of the text to red, then the font styles and sizing
            // then we set the text string to Score, the score stays at the top of our GUI
            // keep it in the centre set x to centre
            // using a calculation: the width of the gui - The width of the string / 2

        }

        else{
            gameOver(g);
        }
        // we have an else block here, which relates to the former part of the code
        //which is actually all included within an if block
        // if running is not equals to true, the else block is triggered and gameOver method is executed

    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    //newApple method makes an apple appear in a new location after it has been taken by the "snake"
    public void move(){
        for (int i = bodyParts; i>0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
            //the move method:
            //the counter is set the value of bodyparts the snake has
            // as long as the snake bodyparts is more than 0 the counter will decrease
            // this is so that the snake can move forward and the rest of its body follows
        }
        switch(direction){
            case'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

        //using switch and cases
        //we can create a case U for up, which means the y axis of the snake is decreased in relation to the grids
        // D for down means that the y axis of the snake is increased in relation to the grids
        // L for left means that the x - axis of the snake is decreased in relation to the grids
        // R for right means that the x - axis of the snake is increased in relation to the grids

    }

    public void checkApple(){
        if((x[0] ==appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();

        }

        //the checkApple method checks that the apple is still on the screen
        //if the apple is not on the screen, then the snake has eaten it
        // the snake gets longer
        // and the apples eaten score gets higher
        // and the newApple function is ran

    }

    public void checkCollisions() {
        //checks if head collides with body
        for (int i=bodyParts; i>0; i--){
            if((x[0] == x[i]) && (y[0] == y[i])){
                running = false;

            }
            // for the counter which is equal to the bodyparts of the snake
            // the counter needs to be greater than 0
            //if the snake collides with itself, then running is set to false
            // and game over is triggered

            // and decrements after each loop
        }
        //checks if head touches left border
        if(x[0] < 0){
            running = false;
        }

        //checks if head touches right border
        if (x[0] > SCREEN_WIDTH){
            running = false;
        }
        //checks if head touches top border
        if (y[0] < 0){
            running = false;
        }
        //checks if head touches bottom border
        if (y[0] >SCREEN_HEIGHT){
            running = false;
        }

        if(!running){
            timer.stop();
        }
        //if the snake running is set to false
        //then timer stops and the game over

    }

    public void gameOver(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score " + applesEaten)) /2, g.getFont().getSize());

        //gameOver method
        //we set the color of the game over screen and other things like the font etc
        // then we use a similar calculation to earlier to put the game over in the middle of the screen

        //Gameover text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        //above line used to line up text in center of screen

        g.drawString("Game Over!", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) /2, SCREEN_HEIGHT / 2 );
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !paused) {
            move();
            checkApple();
            checkCollisions();

            //if the game is running and not currently paused
            // then the snakeGame's move, checkApple, and checkCollisions method are ran

        }

        repaint();

        //repaint method is called is used if not running and paused / not paused



    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';

                    }

                    //key adapter is an important part of the game
                    // keyPressed method, uses techniques to get key input
                    // filters left, right, up and down button
                    //we will set those keys to movements for our game
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L'){
                        direction = 'R';

                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';

                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U'){
                        direction = 'D';

                    }
                    break;

                case KeyEvent.VK_P:
                    paused = !paused;
                    break;

                    //pause button is triggered if p key
                // toggles through paused and unpause

            }

        }
    }

    //above code block is an inner class

}
