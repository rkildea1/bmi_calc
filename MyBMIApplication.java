/*
 * Author           Ronan Kildea
 */

import javax.swing.JFrame;

public class MyBMIApplication {
    public static void main(String[] args) {
        BmiFrame bmiapp = new BmiFrame();
        bmiapp.setVisible(true);
        bmiapp.setSize(450,400); //sets the size of the box
        bmiapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bmiapp.setTitle("BMI Calculator");
        
    }
}
