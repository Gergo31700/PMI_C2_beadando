import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.text.DecimalFormat;
public class GPA_Calculator implements ActionListener {
    JFrame GpaCalculator = new JFrame();
    JTextField Course1 = new JTextField();
    JTextField Course2 = new JTextField();
    JTextField Course3 = new JTextField();

    JTextField Course1Name = new JTextField();
    JTextField Course2Name = new JTextField();
    JTextField Course3Name = new JTextField();
    JLabel messahelabel = new JLabel("Please enter the grades and  weights");
    JLabel Title = new JLabel("First Semester Gpa Calculator");
    JLabel Weight = new JLabel("Weight");
    JLabel Grade = new JLabel("Grade");

    JButton button = new JButton("Calculate");
    GPA_Calculator(){
        GpaCalculator.setLayout(null);
        GpaCalculator.setSize(300,300);


        Title.setBounds(70,5,200,25);
        Course1.setBounds(240,90,30,30);
        Course2.setBounds(240,120,30,30);
        Course3.setBounds(240,150,30,30);
        Weight.setBounds(10,50,80,50);
        Grade.setBounds(240,50,80,50);
        Course1Name.setBounds(10,90,30,30);
        Course2Name.setBounds(10,120,30,30);
        Course3Name.setBounds(10,150,30,30);
        button.setBounds(80,180,90,30);
        messahelabel.setBounds(50,15,300,80);
        GpaCalculator.setLocation(700,300);
        GpaCalculator.add(Title);
        GpaCalculator.add(Course1);
        GpaCalculator.add(Course2);
        GpaCalculator.add(Course3);
        GpaCalculator.add(Weight);
        GpaCalculator.add(Grade);

        GpaCalculator.add(messahelabel);
        button.addActionListener(this);
        GpaCalculator.add(button);

        GpaCalculator.add(Course1Name);
        GpaCalculator.add(Course2Name);
        GpaCalculator.add(Course3Name);
        GpaCalculator.setVisible(true);
    }

    public  void Calculator(){
        double vegeredmeny;





          try {

//
              vegeredmeny= (  (Double.valueOf(Course1Name.getText())*Double.valueOf((Course1.getText())))   +(Double.valueOf(Course2Name.getText())*Double.valueOf((Course2.getText())))  +   (Double.valueOf(Course3Name.getText())*Double.valueOf((Course3.getText()))) ) /(Double.valueOf(Course1Name.getText())+Double.valueOf(Course2Name.getText())+Double.valueOf(Course3Name.getText()));

              if (  Double.valueOf(Course1.getText())<=5 && Double.valueOf(Course1.getText())>=1    && Double.valueOf(Course2.getText())<=5 && Double.valueOf(Course2.getText())>=1 && Double.valueOf(Course3.getText())<=5 && Double.valueOf(Course3.getText())>=1) {
                  if (vegeredmeny>5)
                  {
                      vegeredmeny=5.00;
                  }
                  messahelabel.setText("Your gpa has beeen calculated " + vegeredmeny);
                  Gpaextend gpaextend = new Gpaextend(vegeredmeny);
              }
              else {

                  messahelabel.setText("<html>The Grade you entered is greater <br/> then 5  or less then 0</html>");
              }

          }

          catch (NumberFormatException e)
          {
                messahelabel.setText("Invaild data/Not enough grades");
          }

    }


    @Override
    public void actionPerformed(ActionEvent e) {


Calculator();

    }
}

