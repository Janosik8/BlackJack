package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BlackjackGame {
    Player player;
    Player dealer;
    Deck deck;
    String winner;
    JFrame frame;
    JPanel panel, controls;
    JButton hit, stay;
    int boardWidthHeight = 600;

    BlackjackGame(){
        StartGame();
        frame = new JFrame("Black Jack");
        panel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                try {
                    ArrayList<Card>  dealerCards = dealer.getMyCards();

                   //checking if the back card can be shown
                    if(stay.isEnabled()) {
                        Image hiddenCard = new ImageIcon(getClass().getResource("/cards/BACK.png")).getImage();
                        g.drawImage(hiddenCard, 20, 20, 110, 154, null);
                    }else{
                        Image image = new ImageIcon(getClass().getResource("/cards/"+dealerCards.get(0)+".png")).getImage();
                        g.drawImage(image, 20, 20, 110, 154, null);
                    }

                    //drawing dealers cards
                    int i = 0;
                    for(Card card : dealerCards){
                        Image image = new ImageIcon(getClass().getResource("/cards/"+card.toString()+".png")).getImage();
                        if(i !=0) {
                            g.drawImage(image,20 + (5+110)*i, 20,  110, 154, null);
                        }
                        i++;
                    }

                    //drawing player cards
                    i = 0;
                    for(Card card : player.getMyCards()){
                        Image image = new ImageIcon(getClass().getResource("/cards/"+card.toString()+".png")).getImage();
                            g.drawImage(image,20 + 115*i, 360,  110, 154, null);
                        i++;
                    }
                }catch (Exception e ){
                    e.printStackTrace();
                }

            }
        };

        controls = new JPanel();
        hit = new JButton("HIT");
        stay = new JButton("STAY");

        frame.setVisible(true);
        frame.setSize(boardWidthHeight, boardWidthHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(53,101,87));
        frame.add(panel);

        hit.setFocusable(false);
        stay.setFocusable(false);
        controls.add(hit);
        controls.add(stay);
        frame.add(controls, BorderLayout.SOUTH);

        //button logic
        hit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               player.addCard(deck.getSome());
               panel.repaint();
               if(player.total > 21){
                   endGame();
               }
            }
        });

        stay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame();
            }
        });
    }


    private void StartGame(){
        //vars
        player = new Player();
        dealer = new Player();
        deck = new Deck();

        player.addCard(deck.getSome());
        player.addCard(deck.getSome());
        dealer.addCard(deck.getSome());
        dealer.addCard(deck.getSome());

//logika konsolowa
//        //printowanie pierwszego losowania
//        printInfo();
//
//        //zapytanie usera co dalej robić
//        int wybor = getUserDecision();
//
//
//        //wybory gracza
//        while(wybor == 1) {
//            player.addCard(deck.getSome());
//            printInfo();
//            wybor = getUserDecision();
//        }
//
//        //logika dealera
//        while(dealer.total < 17){
//            dealer.addCard(deck.getSome());
//        }
//
//        //ewaluacja wyników
//        evaluate();
//        printInfo();
//        System.out.println("The winner is: "+ winner);
    }
//
//
//    private void printInfo(){
//        System.out.println("Player Cards: ");
//        player.PrintMyCards();
//
//        System.out.println("\nDealer Cards: ");
//        dealer.PrintMyCards();
//    }
//
//console logic
//    private int getUserDecision(){
//        int wybor;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Co chcesz zrobić?: 1 - Hit, 2 - Stay");
//
//        while(true){
//            try {
//                wybor = scanner.nextInt();
//                if(wybor == 1 || wybor == 2){break;}
//                System.out.println("Daj dobry input");
//            } catch (Exception e) {
//                System.out.println("Daj dobry input");
//                scanner.nextLine();
//            }
//
//        }
//        return wybor;
//    }


    private void evaluate(){
        while(dealer.total < 17){
            dealer.addCard(deck.getSome());
        }
       panel.repaint();

        if(player.total == 21){
           winner = "Player";
        }else if(dealer.total == 21){
           winner = "Dealer";
        }else if(player.total > 21){
           winner = "Dealer";
        }else if(dealer.total > 21){
           winner = "Player";
        }else if(player.total > dealer.total ){
           winner = "Player";
        }else if(player.total < dealer.total){
           winner = "Dealer";
        }else winner = "Draw";
    }


    private void  endGame(){
        hit.setEnabled(false);
        stay.setEnabled(false);
        evaluate();
        frame.repaint();

        if(Objects.equals(winner, "Player")) {
            JOptionPane.showMessageDialog(frame, "You won!");
        }else{
            JOptionPane.showMessageDialog(frame, "You Lost ;(");
        }


        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the frame after 3 seconds
                frame.dispose();
            }
        });
    }

}

