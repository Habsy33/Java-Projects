import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    boolean hasDecimal = false;

    // above are attributes of our program that we will need.
    //its best to know what fields and values you need when starting  a project
    // so that all that's left is the logic and exception handling


    Calculator(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        // at this point in the code we will quickly run the main method
        // to make sure the code is working as we go

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        //the text field is the box at the top of the calc that shows the selected
        // nummbers for calculation- as well as their calculated result

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");
        negButton = new JButton("(-)");

        //we add the labels to the buttons so they show on the calc GUI


        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        //we add all the buttons to an array that we created earlier

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);

            // there are 9 function buttons
            // for each button that is selected, the action listener
            // will record which was selected
            // and the function is selected for that number(s)
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);

            //for each element less than 10 (numbers on a calc)
            // any element of the numbers array that is chosen
            // will be converted to a number inside the textfield
            // and the action listener is there to turn the elements
            // into numbers that can be calculated
            // set our chosen font to the numbers
            // set focusable to false (disables wierd box around numbers)
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // here we set bounds for the function buttons
        // negative button is set to the bottom row of the calc
        // same for delete button and clear button

        // please note the functionality of the operations
        // hasn't been implemented up to this part

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // we set bounds for the entire number pad and number functions
        // negative button, delete and clear are not included in the
        // code block above

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);

        // the order in which we put the number buttons is how
        // they will dislay
        // i.e. top row underneath textfield will be 1, 2, 3 then an add button
        // then new line 4, 5, 6 and a subtract button and so on...
        // since our numberpad is 4x4

        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);

        frame.setVisible(true);

        //last bit of code in this block is to make sure
        // all the stuff we've added to our frame is visible
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        //this is the only part of the code that creates
        // an object of the Calculator class
        // it is used to run the GUI
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
            //this first method is actually overriden from the original
            // action listener interface

            //for each element less than 10, this block of code will;
            // store the number based on it's position in the numbers array
            // and set the textfield to reflect that number
        }
        if (e.getSource() == decButton) {
            if (!hasDecimal) {
                textfield.setText(textfield.getText().concat("."));
                hasDecimal = true;  // Set the flag to true after adding a decimal point
            }
            //if the user chooses a function button like decimal
            // there will be a check done to make sure the textfield does not
            //already have a decimal
            // then it will reflect that decimal to the textfield
            //by putting a . after the numbers

            //please note
            // if the textfield already has a decimal.
            // the following code in the block won't be executed
            
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
            hasDecimal = false;  // Reset the flag when starting a new number
        }
        //if the user chooses add button
        // the number already within the textfield
        //will be saved as number 1
        // its operator saved as +
        // and the textfield will be emptied to allow space for a new number

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
            hasDecimal = false;  // Reset the flag when starting a new number
        }
        // if the user chooses the subtract button
        // the number chosen already within the textfield
        // will be saved as number 1
        // its operator saved as -
        //and the textfield will be empited to allow space for a new number

        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = 'x';
            textfield.setText("");
            hasDecimal = false;  // Reset the flag when starting a new number
        }
        //if the user chooses the multiplication button
        //the number in the textfield gets saved as number 1
        // operater set to x
        // and the textfield is empited to allow space for new number (2)

        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
            hasDecimal = false;  // Reset the flag when starting a new number
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case'+':
                    result = num1 + num2;
                    break;
                case'-':
                    result = num1 - num2;
                    break;
                case'x':
                    result = num1 * num2;
                    break;
                case'/':
                    result = num1 / num2;
                    break;
            }
            // if user selects equal button
            // then number 2 will be retrieved from the textfield
            // and a switch statement allows different cases to be executed
            // case 1: in the if statements above- user selects num 1 uses + operator and num2
            // case 2: in the if statements above- user selects num 1 uses - operator and num2
            // case 3: in the if statements above- user selects num 1 uses * operator and num2
            // case 4: in the if statements above- user selects num 1 uses / operator and num2

            textfield.setText(String.valueOf(result));
            num1 = result;
            hasDecimal = false;  // Reset the flag for the next calculation
        }
        // the textfield gets updated with the result of the calculation
        // the num1 variable is updated with the result of num1 and num2
        // reset the hasDecimal boolean to allow user to use decimal for another
        // calculation

        if (e.getSource() == clrButton) {
            textfield.setText("");
            hasDecimal = false;  // Reset the flag when clearing the text field
        }
        // user selects clear button
        // empty the texfield
        // set the hasDecimal boolean to false so the user can use the deicmal function again



        if (e.getSource() == delButton) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
            // if user chooses delete button
            //the textfield is emptied
            // for each character one by one (not all at once like clear)

            if (!textfield.getText().contains(".")) {
                hasDecimal = false;  // Reset the flag if no decimal point is present
            }
            //if textfield does not have .
            // set the hasDecimal boolean to false so the user can use it
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
        //if user chooses negative button
        // get the numbers in the textfield
        // multiply by -1 to make the number negative
        // and set the textfield to that updated number
    }
}