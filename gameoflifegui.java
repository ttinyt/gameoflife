import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameoflifegui {
    private static Verden verden;
    private static JButton[][] celler;
    private static Timer timer;

    public static void main (String[] args) {
        try { 
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
        } catch (Exception e) { System.exit(1); }

        JFrame vindu = new JFrame("Game of Life");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        verden = new Verden(8, 12); 
        celler = new JButton[verden.rutenett.antRader][verden.rutenett.antKolonner];


        JPanel toppPanel = new JPanel();
        JLabel genNrL = new JLabel("Generasjon nr: 0");
        toppPanel.add(genNrL);
        toppPanel.add(Box.createRigidArea(new Dimension(0, 10))); //satt mellomrom slik at det ser penere og mindre tett ut
        JLabel levendeL = new JLabel("Antall levende celler: ");
        toppPanel.add(levendeL);
        vindu.add(toppPanel, BorderLayout.NORTH);


        JPanel panel = new JPanel(new GridLayout(verden.rutenett.antRader, verden.rutenett.antKolonner));
        vindu.add(panel, BorderLayout.CENTER);

        for (int r = 0; r < verden.rutenett.antRader; r++) {
            for (int k = 0; k < verden.rutenett.antKolonner; k++) {
                JButton button = new JButton();

                button.setPreferredSize(new Dimension(47, 47));
                button.setBackground(Color.WHITE);

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (button.getBackground() == Color.WHITE) {
                            button.setBackground(Color.PINK); //rosa når vi trykker og setter til levende
                            button.setText("\u2665");
                        } else {
                            button.setBackground(Color.WHITE);
                            button.setText("");
                        }
                    }
                });
                panel.add(button);
                celler[r][k] = button;
            }
        }

        JPanel kontrollPanel = new JPanel();
        vindu.add(kontrollPanel, BorderLayout.SOUTH);



        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer == null || !timer.isRunning()) {   //starter timer
                    timer = new Timer(2000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            verden.oppdatering();
                            int levende = verden.rutenett.antallLevende(); 
                            genNrL.setText("Generasjon nr: " + verden.genNr);
                            levendeL.setText("Antall levende celler: " + levende);
                            
                            for (int r = 0; r < verden.rutenett.antRader; r++) {
                                for (int k = 0; k < verden.rutenett.antKolonner; k++) {
                                    Celle celle = verden.rutenett.hentCelle(r, k);
                                    if (celle.erLevende()) {
                                        celler[r][k].setBackground(Color.PINK);
                                        celler[r][k].setText("\u2665");
                                    } else {
                                        celler[r][k].setBackground(Color.WHITE);
                                        celler[r][k].setText("");
                                    }
                                }
                            }
                        }
                    });
                    timer.start();
                }
            }
        });
        kontrollPanel.add(start);

        JButton avslutt = new JButton("Avslutt");
        avslutt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        kontrollPanel.add(avslutt);

        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
}