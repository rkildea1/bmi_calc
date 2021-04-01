/*
 * Author           Ronan Kildea
 */

import javax.swing.JFrame; //the actual frame or popout
import javax.swing.JLabel; //a component which displays text which the user can not edit
import javax.swing.JTextField; //a text field the user can type into
import javax.swing.JButton; //creates a command button
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent; //button actions
import java.awt.event.ActionListener; //button actions
import java.lang.NumberFormatException; //exception handling


public class BmiFrame extends JFrame {

    //declare the fields for the WEIGHTS, HEIGHTS, BUTTONS and RESULTS
    private JLabel welcomeLabel;
    private JLabel enterWeightLabel;
    private JLabel enterHeightLabel;
    private JLabel stonesLabel;
    private JTextField stonesField;
    private JLabel poundsLabel;
    private JTextField poundsField;
    private JLabel kgsLabel;
    private JTextField kgsField;
    private JLabel feetLabel;
    private JTextField feetField;
    private JLabel inchesLabel;
    private JTextField inchesField;
    private JLabel cmLabel;
    private JTextField cmField;
    private JButton calculateButton;
    private JButton restartButton;
    private JTextField bmiResultField;
    private JTextField yourBmiField; //user assitant message


    public BmiFrame() //constructor
    {
        setLayout(null); //turnoff the layout manager

        /***---Create the static text labels---***/
        welcomeLabel = new JLabel("Welcome to the BMI Calculator");
        Font welcomeFont = new Font("Algerian", Font.BOLD, 13);
        welcomeLabel.setFont(welcomeFont);
        enterWeightLabel = new JLabel("Enter a weight in one of the following boxes");
        enterHeightLabel = new JLabel("Enter a height in one of the following boxes");

        /***---create the input fields---***/
        //LABEL_FIELD: stone label and field
        stonesLabel = new JLabel("Stone");
        stonesField = new JTextField("0.00");
        //LABEL_FIELD: pound label and field          
        poundsLabel = new JLabel("Pounds");
        poundsField = new JTextField("0.00");
        //LABEL_FIELD: KG's label and field          
        kgsLabel = new JLabel("or KG's");
        kgsField = new JTextField("0.00");
        //LABEL_FIELD: feet label and field
        feetLabel = new JLabel("Feet");
        feetField = new JTextField("0.00");
        //LABEL_FIELD: inches label and field          
        inchesLabel = new JLabel("Inches");
        inchesField = new JTextField("0.00");
        //LABEL_FIELD: cm's label and field          
        cmLabel = new JLabel("or CM's");
        cmField = new JTextField("0.00");

        /***---create the Buttons---***/
        calculateButton = new JButton("Calculate your BMI");
        restartButton = new JButton("Start again");

        /***---create the Results fields---***/
        bmiResultField = new JTextField(" ");
        yourBmiField = new JTextField(" ");


        /***---create the CONTAINERs for organizing the Fields and Inputs---***/

        //display, format & align the Welcome message
        JPanel weightWelcomeContainer = new JPanel();
        weightWelcomeContainer.setBounds(130, 10, 400, 20); //how far from left, top, width and height
        weightWelcomeContainer.setLayout(new GridLayout(2, 2));
        weightWelcomeContainer.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        weightWelcomeContainer.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
        weightWelcomeContainer.add(welcomeLabel);
        add(weightWelcomeContainer);

        //display, format & align the Enter Weight message
        JPanel weightMessageContainer = new JPanel();
        weightMessageContainer.setBounds(10, 45, 400, 30);
        weightMessageContainer.setLayout(new GridLayout(2, 2));
        weightMessageContainer.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        weightMessageContainer.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);
        weightMessageContainer.add(enterWeightLabel);
        add(weightMessageContainer);

        //display, format & align the 3 weight input fields and labels
        JPanel weightPanel = new JPanel();
        weightPanel.setBounds(10, 65, 400, 30);
        weightPanel.setLayout(new GridLayout(1, 6));
        add(weightPanel);
        weightPanel.add(stonesLabel);
        weightPanel.add(stonesField);
        weightPanel.add(poundsLabel);
        weightPanel.add(poundsField);
        weightPanel.add(kgsLabel);
        weightPanel.add(kgsField);


        //display, format & align the Enter Height message
        JPanel heightMessageContainer = new JPanel();
        heightMessageContainer.setBounds(10, 120, 400, 30);
        heightMessageContainer.setLayout(new GridLayout(1, 1));
        heightMessageContainer.add(enterHeightLabel);
        add(heightMessageContainer);


        //display, format & align the 3 height input fields and labels
        JPanel heightPanel = new JPanel();
        heightPanel.setBounds(10, 145, 400, 30);
        heightPanel.setLayout(new GridLayout(1, 6));
        add(heightPanel);
        heightPanel.add(feetLabel);
        heightPanel.add(feetField);
        heightPanel.add(inchesLabel);
        heightPanel.add(inchesField);
        heightPanel.add(cmLabel);
        heightPanel.add(cmField);


        //display, format & align the buttons (Calculate/ Start Again)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(10, 185, 400, 50);
        buttonPanel.setLayout(new GridLayout(2, 1));
        add(buttonPanel);
        buttonPanel.add(calculateButton);
        buttonPanel.add(restartButton);

        //Create an instance of buttonhandler to listen for button clicks:
        ButtonHandler handler = new ButtonHandler();
        calculateButton.addActionListener(handler);
        restartButton.addActionListener(handler);

        //display, format & align the Results field
        bmiResultField = new JTextField("Result: 0.00");
        bmiResultField.setBounds(10, 250, 400, 50);
        bmiResultField.setEditable(false); //means it is not editable
        bmiResultField.setBackground(Color.WHITE);
        bmiResultField.setForeground(Color.BLUE);
        Font resultFont = new Font("Algerian", Font.BOLD, 18);
        bmiResultField.setFont(resultFont);
        add(bmiResultField);


        //display, format & align the User Assitant Message 
        yourBmiField = new JTextField(" "); //default to blank
        yourBmiField.setBounds(20, 290, 400, 50);
        yourBmiField.setBackground(null);
        yourBmiField.setBorder(null); // remove border box
        yourBmiField.setEditable(false);
        Font yourBmiFieldFont = new Font("SANS_SERIF", Font.ITALIC, 12);
        yourBmiField.setFont(yourBmiFieldFont);
        add(yourBmiField);

    } // end of the constructor

    private class ButtonHandler implements ActionListener {


        public void actionPerformed(ActionEvent event)



        {
            //create objects with correct types & default values 
            //(app will parse the input to these)            
            double stones = 0.0;
            double pounds = 0.0;
            double kgs = 0.0;
            double feet = 0.0;
            double inches = 0.0;
            double cm = 0.0;
            double result = 0.0;
            String errorMessage = "No Error";
            String resultUnderweight = "You are considered underweight. Please seek medical advice";
            String resultNormalWeight = "You are considered normal (healthy) weight";
            String resultOverweight = "You are considered overweight, please eat healthier";
            String resultObese = "You are considered obese. Please seek medical advice";
            double weightTotal = 0.0;
            double heightTotal = 0.0;

            try {
                //convert the string INPUTS numbers into Doubles
                stones = Double.parseDouble(stonesField.getText());
                pounds = Double.parseDouble(poundsField.getText());
                kgs = Double.parseDouble(kgsField.getText());
                feet = Double.parseDouble(feetField.getText());
                inches = Double.parseDouble(inchesField.getText());
                cm = Double.parseDouble(cmField.getText());
                result = 0.0;
                errorMessage = "No Error";
                weightTotal = 0.0;
                heightTotal = 0.0;
            } catch (NumberFormatException numberFormatException) {

                stonesField.setText("0.00");
                poundsField.setText("0.00");
                kgsField.setText("0.00");
                feetField.setText("0.00");
                cmField.setText("0.00");
                inchesField.setText("0.00");
                String bmiTotal = String.format("Input Error: Non-numbers used");
                bmiResultField.setText(bmiTotal);
                yourBmiField.setForeground(Color.red);
            }

            /***---next, create the Events for each button click---***/
            //reset all fields if Start Again button is clicked
            if (event.getSource() == restartButton)
            //getSource will return you the variable name of the button
            {
                stonesField.setText("0.00");
                poundsField.setText("0.00");
                kgsField.setText("0.00");
                feetField.setText("0.00");
                cmField.setText("0.00");
                inchesField.setText("0.00");
                errorMessage = "Fields cleared. Please enter your weight and height";
                String bmiTotal = String.format("Result: 0.00");
                bmiResultField.setText(bmiTotal);
                yourBmiField.setForeground(Color.blue);
            } //end if you hit the restart button

            /***---Calculate Button Error handling---***/
            //Calculate button Error handling - Impropper values on hitting calculate (all values == 0.00)          
            if (event.getSource() == calculateButton) {

                if ((stones + pounds + kgs + feet + inches + cm == 0.0) ||
                    ((feet < 0.0) || (inches < 0.0) || (cm < 0.0) || //height is negative
                        (stones < 0.0) || (kgs < 0.0) || (pounds < 0.0)) || //weight is negative
                    ((stones < 5.0 && stones > 0.0) || (kgs < 30.0 && kgs > 0.0) || (pounds < 70.0 && pounds > 0.0)) || //weight is unreal
                    ((feet < 2.5 && feet > 0.0) || (inches < 30.0 && inches > 0.0) || (cm < 75.0 && cm > 0.0)) //height is unreal
                ) {
                    {
                        errorMessage = "Error: Enter a weight and height (as positive numbers)";
                        yourBmiField.setForeground(Color.red);
                    }
                }
            }


            //Calculate button Error handling - More than one HEIGHT entered
            if (event.getSource() == calculateButton) {
                if ((feet > 0.0 && inches > 0.0 && cm > 0.0) || //all 3 heights have enteries
                    (feet > 0.0 && inches > 0.0 && cm == 0.0) || //feet and inches have enteries cm dont
                    (feet > 0.0 && inches == 0.0 && cm > 0.0) || //feet and cm have enteries inches dont
                    (feet == 0.0 && inches > 0.0 && cm > 0.0)) //cm and Inches have enteries feed dont
                {
                    errorMessage = "Error: Please enter only one value for Height";
                    yourBmiField.setForeground(Color.red);
                }
            }


            //Calculate button Error handling - More than one WEIGHT entered
            if (event.getSource() == calculateButton) {
                if ((stones > 0.0 && kgs > 0.0 && pounds > 0.0) || //all 3 have enteries
                    (stones > 0.0 && kgs > 0.0 && pounds == 0.0) || //STONE and KG have enteries POUNDS dont
                    (stones > 0.0 && kgs == 0.0 && pounds > 0.0) || //STONE and POUNDS have enteries KG dont
                    (stones == 0.0 && kgs > 0.0 && pounds > 0.0)) //KG and POUNDS have enteries Stones dont
                {
                    errorMessage = "Error: Please enter only one value for Weight";
                    yourBmiField.setForeground(Color.red);
                    yourBmiField.setText(errorMessage);
                }
            }


            //Calculate button Error handling - No WEIGHT entered
            if (stones == 0.0) {
                if (pounds == 0.0) {
                    if (kgs == 0.0) {
                        if (feet + inches + cm != 0.0) {
                            errorMessage = "Error: You need to enter a weight";
                            yourBmiField.setForeground(Color.red);
                        }
                    }
                }
            }


            //Error handling - No HEIGHT entered
            if (feet == 0.0) {
                if (inches == 0.0) {
                    if (cm == 0.0) {
                        if (stones + pounds + kgs != 0.0) {
                            errorMessage = "Error: You need to enter a height";
                            yourBmiField.setForeground(Color.red);
                        }
                    }
                }
            }

            //Error handling - Negative Values entered           
            if ((feet < 0.0) || (inches < 0.0) || (cm < 0.0) ||
                (stones < 0.0) || (kgs < 0.0) || (pounds < 0.0)) {
                errorMessage = "Error: Negative values won't work - please hit \"Start Again\"";
                yourBmiField.setForeground(Color.red);
                yourBmiField.setText(errorMessage);
            } 
            
            else
                //Error handling - Unrealistic Values entered (Weights)       
                if (((stones < 5.0 && stones > 0.0)) || ((kgs < 30.0 && kgs > 0.0)) || ((pounds < 70.0 && pounds > 0.0))) {
                    errorMessage = "Error: Those weights don't look right. please hit \"Start Again\"";
                    yourBmiField.setForeground(Color.red);
                    yourBmiField.setText(errorMessage);
                }

                //            Error handling - Unrealistic Values entered  (Heights)         
            else if (((feet < 2.5 && feet > 0.0)) || ((inches < 30.0 && inches > 0.0)) || ((cm < 75.0 && cm > 0.0))) {
                    errorMessage = "Error: Those heights don't look right. Please start again";
                    yourBmiField.setForeground(Color.red);
                    yourBmiField.setText(errorMessage);
                }
            
            //this displays the error defined in the if's above
            if (errorMessage != "No Error") {
                yourBmiField.setText(errorMessage); //displays the error message
            }




            /* --------------------------___BMI CALCULATION___--------------------------*/

            /* -------__Weight conversion__--------*/
            //Weight Calc: if only POUNDS is used, use POUNDS
            if (kgs == 0) {
                if (stones == 0) {
                    if (pounds != 0) {
                        weightTotal = pounds;
                    }
                }
            }


            //Weight Calc: if only STONE is used, CONVERT to POUNDS
            if (kgs == 0) {
                if (pounds == 0) {
                    if (stones != 0) {
                        weightTotal = stones;
                        weightTotal = weightTotal * 14;
                    }
                }
            }

            //Weight Calc:if only KGS is used, CONVERT to POUNDS             
            if (stones == 0) {
                if (pounds == 0) {
                    if (kgs != 0) {
                        weightTotal = kgs;
                        weightTotal = weightTotal * 2.20462;
                    }
                }
            }


            /* -------__Height conversion__--------*/

            //if only CM is used, use CM
            if (feet == 0) {
                if (inches == 0) {
                    if (cm != 0) {
                        heightTotal = cm;
                    }
                }
            }

            //if only FEET is used, CONVERT to CM
            if (inches == 0) {
                if (cm == 0) {
                    if (feet != 0) {
                        heightTotal = feet;
                        heightTotal = heightTotal * 30.48;
                    }
                }
            }

            //if only INCHES is used, CONVERT to CM            
            if (feet == 0) {
                if (cm == 0) {
                    if (inches != 0) {
                        heightTotal = inches;
                        heightTotal = heightTotal * 2.54;
                    }
                }
            }


            /* -------__BMI Calculation__--------*/

            //BMI = (Weight in Pounds / (Height in inches x Height in inches)) x 703
            heightTotal = heightTotal * 0.393701; // total Height converts to INCHES
            result = ((weightTotal / (heightTotal * heightTotal)) * 703); //calulate BMI based on inputs


            /*-------BMI Calculation print results-------*/
            if (heightTotal > 0) {
                if (weightTotal > 0) {

                    if (result < 10) {
                        String bmiTotal = String.format("Input Error: Please try again");
                        bmiResultField.setText(bmiTotal);
                        //yourBmiField.setText("Please double-click the \"Start Again\" button to re-enter your values");
                        yourBmiField.setText("Are you sure those values are correct? Please hit \"Start Again\"");
                    } else if (result < 18.5) {
                        String bmiTotal = String.format("Result: You're BMI is %,.2f\n", result);
                        bmiResultField.setText(bmiTotal);
                        yourBmiField.setText(resultUnderweight);
                    } //END if(result<18.5)
                    else if (result < 25) {
                        String bmiTotal = String.format("Result: You're BMI is %,.2f\n", result);
                        bmiResultField.setText(bmiTotal);
                        yourBmiField.setText(resultNormalWeight);
                    } //enf if (result<25)
                    else if (result < 30) {
                        String bmiTotal = String.format("Result: You're BMI is %,.2f\n", result);
                        bmiResultField.setText(bmiTotal);
                        yourBmiField.setText(resultOverweight);
                    } //end if (result<30)   
                    else if (result < 70) {
                        String bmiTotal = String.format("Result: You're BMI is %,.2f\n", result);
                        bmiResultField.setText(bmiTotal);
                        yourBmiField.setText(resultObese);
                    } //end if (result<30) 
                    else if (result > 70) {
                        String bmiTotal = String.format("Input Error: Please try again");
                        bmiResultField.setText(bmiTotal);
                        //yourBmiField.setText("Please click the \"Start Again\" button and reenter your values");
                        yourBmiField.setText("Are you sure those values are correct? Please hit \"Start Again\"");
                    } //end if (result<30) 

                } //END if(weightTotal !=0)

            } //end print BMI Result & BMI Comment to the screen     






        } // end of action event   


    } //end of button handler


} //end of BmiFrame
