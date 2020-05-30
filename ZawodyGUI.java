package def;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ZawodyGUI extends JFrame{
    private JPanel mainPanel;
    private JLabel mainTitle;
    private JButton selectATournamentButton;
    private JButton manageTeamsRefereesEtcButton;
    private JButton displayListOfTournament;
    private JButton addATournamentButton;
    private JButton exitTheProgramButton;

    public ZawodyGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        //żeby okienko było na środku, a nie w lewym górym rogu
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        addATournamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddTheTournamentFrame addTheTournamentFrame = new AddTheTournamentFrame("HEllo WOrld");
                addTheTournamentFrame.setVisible(true);
                mainPanel.setVisible(false);
                dispose();


            }
        });
        exitTheProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new ZawodyGUI("Zawody");
        frame.setVisible(true);
    }
}
