import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    private Game game;
    private JLabel[][] labelsField;

    public Frame(Game game){
        this.game = game;
        initGUI();
    }

    private void initGUI(){
        this.setTitle("2048");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Чтобы программа заканчивалась по нажатию на крестик
        this.setResizable(false); // Запрет на изменение размера окна
        this.setSize(450, 450);
        this.setLayout(new GridLayout(game.size, game.size));
        labelsField = new JLabel[game.size][game.size];
        for(int i = 0; i < game.size; i++){
            for(int j = 0; j < game.size; j++){
                labelsField[i][j] = new JLabel();
                labelsField[i][j].setForeground(Color.white);
                labelsField[i][j].setHorizontalAlignment(JLabel.CENTER);
                this.add(labelsField[i][j]);
            }
        }
        updateField();
        this.setVisible(true);
    }

    public void updateField(){
        for(int i = 0; i < game.size; i++){
            for(int j = 0; j < game.size; j++){
                int value = game.field[i][j];
                labelsField[i][j].setIcon(new ImageIcon("./img/" + Integer.toString(value) + ".png"));
            }
        }
    }
}
