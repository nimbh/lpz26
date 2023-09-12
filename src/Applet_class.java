import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Applet_class extends JPanel implements ActionListener {
    private int centerX;
    private int centerY;
    private int angle;

    public Applet_class() {
        centerX = 200;
        centerY = 200;
        angle = 0;
        setBackground(Color.WHITE);

        // Создаем таймер для периодического обновления анимации
        javax.swing.Timer timer = new javax.swing.Timer(10, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Создаем массив с координатами вершин четырехугольника
        int[] xPoints = {centerX - 50, centerX + 50, centerX + 50, centerX - 50};
        int[] yPoints = {centerY - 50, centerY - 50, centerY + 50, centerY + 50};

        // Поворачиваем координаты вершин четырехугольника
        double angleRadians = Math.toRadians(angle);
        for (int i = 0; i < xPoints.length; i++) {
            int newX = (int) ((xPoints[i] - centerX) * Math.cos(angleRadians) -
                    (yPoints[i] - centerY) * Math.sin(angleRadians) + centerX);
            int newY = (int) ((xPoints[i] - centerX) * Math.sin(angleRadians) +
                    (yPoints[i] - centerY) * Math.cos(angleRadians) + centerY);
            xPoints[i] = newX;
            yPoints[i] = newY;
        }

        // Закрашиваем четырехугольник синим цветом
        g.setColor(Color.CYAN);
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public void actionPerformed(ActionEvent e) {
        // Увеличиваем угол поворота
        angle += 1;

        // Перерисовываем панель
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotating Quadrilateral");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Applet_class());
        frame.setVisible(true);
    }
}

