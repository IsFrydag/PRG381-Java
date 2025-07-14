import javax.swing.JFrame;
import javax.swing.JLabel;

public class welcomepage {

    JFrame frame = new JFrame();
    JLabel welcomLabel = new JLabel("Welcome user!!!");

    WelcomePage(String userID){

        welcomeLabel.setBounds(0,0,200,35);
        welcomeLabel.setFont(new Font(null, PLAIN,25));
        welcomeLabel.setText("Welcome"+userID);

        frame.add(welcomLabel); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    
    }
    
}
