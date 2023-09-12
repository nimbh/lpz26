import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JApplet {
    private String[] colors = {"Красный", "Зеленый", "Синий"};
    private JComboBox<String> colorComboBox;
    private JLabel movingLabel;
    private Timer timer;
    private int x = 0;
    private int dx = 5;
    private Color currentColor = Color.RED;

    public void init() {
        setSize(500, 500);
        setLayout(new FlowLayout());

        colorComboBox = new JComboBox<>(colors);
        colorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) colorComboBox.getSelectedItem();
                if (selectedColor.equals("Красный")) {
                    currentColor = Color.RED;
                } else if (selectedColor.equals("Зеленый")) {
                    currentColor = Color.GREEN;
                } else if (selectedColor.equals("Синий")) {
                    currentColor = Color.BLUE;
                }
                movingLabel.setForeground(currentColor);
            }
        });
        add(colorComboBox);

        movingLabel = new JLabel("Движущаяся строка");
        movingLabel.setForeground(currentColor);
        add(movingLabel);

        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                x += dx;
                if (x <= 0 || x >= getWidth() - movingLabel.getWidth()) {
                    dx = -dx;
                }
                movingLabel.setLocation(x, 100);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Applet Example");
        JApplet applet = new App();
        applet.init();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(applet);
        frame.setSize(500, 500);
        frame.setVisible(true);

        applet.start();
    }
}
