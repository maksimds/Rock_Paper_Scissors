import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPan, BottomPan, dataPan, revealPan;
    JButton paperBut, rockBut, scissorBut, quitBut;
    TextArea results;
    JScrollPane pane;
    JLabel victorWin, sysWin, draw, gamesCompleted;

    int paperCount = 0, rockCount=0, scissorCount=0;
    int playerQuantity = 0, sysCount = 0, gameDraw = 0, gamesCompletedTally = 0;

    public RockPaperScissorsFrame() {
        setTitle("The Game of Rock Paper Scissors");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPan = new JPanel();
        mainPan.setLayout(new BorderLayout());
        add(mainPan);

        createBottomPan();
        mainPan.add(BottomPan, BorderLayout.NORTH);

        createrevealPan();
        mainPan.add(revealPan, BorderLayout.CENTER);

        createdataPan();
        mainPan.add(dataPan, BorderLayout.SOUTH);

        setVisible(true);
    }

    private Image fitimage(Image img, int w, int h)
    {
        BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedimage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img, 0, 0,w,h,null);
        g2.dispose();
        return resizedimage;
    }

    private void createBottomPan() {
        BottomPan = new JPanel();
        BottomPan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        BottomPan.setLayout(new GridLayout(2,4));
        paperBut = new JButton("Paper");
        paperBut.setIcon((new ImageIcon(new ImageIcon("/Users/a111/IdeaProjects/Rock_Paper_Scissors/src/paper1.png").getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT))));
        paperBut.addActionListener((ActionEvent ae) ->
        {
            gamesCompletedTally++;
            results.append("You chose Paper\n");
            Game(1);
        });

        rockBut= new JButton("Rock");
        rockBut.setIcon((new ImageIcon(new ImageIcon("/Users/a111/IdeaProjects/Rock_Paper_Scissors/src/rock1.png").getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT))));
        rockBut.addActionListener((ActionEvent ae) ->
        {
            gamesCompletedTally++;
            results.append("You chose Rock\n");
            Game(2);

        });

        scissorBut= new JButton("Scissors");
        scissorBut.setIcon((new ImageIcon(new ImageIcon("/Users/a111/IdeaProjects/Rock_Paper_Scissors/src/scissors1.png").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT))));
        scissorBut.addActionListener((ActionEvent ae) ->
        {
            gamesCompletedTally++;
            results.append("You chose Scissors\n");
            Game(3);
        });

        quitBut = new JButton("Quit");
        quitBut.setIcon(new ImageIcon(new ImageIcon("/Users/a111/IdeaProjects/Rock_Paper_Scissors/src/quit1.png").getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT)));
        quitBut.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 15));
        quitBut.addActionListener((ActionEvent ae) -> System.exit(0));

        BottomPan.add(paperBut);
        BottomPan.add(rockBut);
        BottomPan.add(scissorBut);
        BottomPan.add(quitBut);
    }

    private void createrevealPan() {
        revealPan= new JPanel();
        results = new TextArea(15,50);
        pane = new JScrollPane(results);
        revealPan.add(pane);
    }

    private void createdataPan() {
        dataPan= new JPanel();
        dataPan.setLayout(new GridLayout(2,2));

        victorWin= new JLabel("Player win: " + playerQuantity);
        sysWin= new JLabel("Computer win: " + sysCount);
        draw = new JLabel("Tie: " + gameDraw);
        gamesCompleted = new JLabel("Games Played: " + gamesCompletedTally);

        dataPan.add(victorWin);
        dataPan.add(sysWin);
        dataPan.add(draw);
        dataPan.add(gamesCompleted);
    }
    private void Game(int playerShift) {
        Random rand = new Random();
        int sysShift = rand.nextInt(3) + 1;

        if(sysShift == playerShift) {
            if (sysShift == 1) {
                results.append("The system has Paper\n");
                results.append("It's a draw\n\n");
            }else if(sysShift == 2){
                results.append("The system has Rock\n");
                results.append("It's a draw\n\n");
            }
            gameDraw++;
        }

        else if (sysShift == 1 && playerShift == 2)
        {
            results.append("System has Paper\n");
            results.append("Paper covers Rock\n");
            results.append("System wins\n\n");
            sysCount++;
        }
        else if (sysShift == 1 && playerShift == 3)
        {
            results.append("System has Paper\n");
            results.append("Scissor cuts Paper\n");
            results.append("player wins\n\n");
            playerQuantity++;
        }
        else if (sysShift == 2 && playerShift == 1)
        {
            results.append("System has Rock\n");
            results.append("Paper covers Rock\n");
            results.append("player wins\n\n");
            playerQuantity++;
        }
        else if (sysShift == 2 && playerShift == 3)
        {
            results.append("System has Rock\n");
            results.append("Rock breaks Scissors\n");
            results.append("System wins\n\n");
            sysCount++;
        }
        else if (sysShift == 3 && playerShift == 1)
        {
            results.append("System has Scissor\n");
            results.append("Scissor cuts Paper\n");
            results.append("System wins\n\n");
            sysCount++;
        }
        else
        {
            results.append("System has Scissor\n");
            results.append("Rock breaks Scissors\n");
            results.append("Player wins\n\n");
            playerQuantity++;
        }

        victorWin.setText("Player Win: " + playerQuantity);
        sysWin.setText("Computer Win: " + sysCount);
        draw.setText("Tie: " + gameDraw);
        gamesCompleted.setText("Games Played: " + gamesCompletedTally);

    }


}