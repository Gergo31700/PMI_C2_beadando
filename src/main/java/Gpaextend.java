import javax.swing.*;

public class Gpaextend {
    JFrame frame = new JFrame();
    JLabel ScholarDisplayer = new JLabel();
    JLabel MeatPlanDisplayer=new JLabel();
    JLabel text =  new JLabel();
    JLabel text2 =  new JLabel();
    int Scolarship = 0;
    double MeatPlan = 2300;
    Gpaextend( double Gpa){



        if (Gpa>=4.05)
        {
                Scolarship = 6500;
                MeatPlan = 2300 / Gpa;


            text.setText("Your Scholarship for the next semester "+Scolarship+" dollars");
            text2.setText("Your Meatplan disscount "+String.valueOf((int)MeatPlan)+" dollars");
        }

        if (Gpa>=3.25 && Gpa<4.00)
        {
            Scolarship = 4500;
            MeatPlan = 2300 / Gpa;


            text.setText("Your Scholarship for the next semester "+Scolarship+" dollars");
            text2.setText("Your Meatplan disscount "+String.valueOf((int)MeatPlan)+" dollars");
        }

        if (Gpa>=3 && Gpa<3.25)
        {
            Scolarship = 4000;
            MeatPlan = 2300 / Gpa;


            text.setText("Your Scholarship for the next semester "+Scolarship+" dollars");
            text2.setText("Your Meatplan disscount "+String.valueOf((int)MeatPlan)+" dollars");
        }
        if (Gpa<3)
        {
            Scolarship = 0;
            MeatPlan = 2300 / Gpa;


            text.setText("Your gpa is to low to get a Scolarship");
            text2.setText("Your Meatplan disscount "+String.valueOf((int)MeatPlan)+" dollars");
        }


        text2.setBounds(10,30,500,200);
        frame.add(text2);
        text.setBounds(10,10,500,200);
        frame.add(text);
        MeatPlanDisplayer.setBounds(30,30,200,200);
        frame.add(MeatPlanDisplayer);
        ScholarDisplayer.setBounds(30,50,200,200);
        frame.add(ScholarDisplayer);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocation(700,300);

        frame.setSize(450,300);
        frame.setVisible(true);

    }
}
