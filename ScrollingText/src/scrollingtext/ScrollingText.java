package scrollingtext;

/*
Метод run является наверное наиболее интересным. В нем реализован бесконечный цикл,
в котором поочередно рисуются строки внизу служебного изображения, которое плавно
перемещается по экрану. После каждого смещения на один пиксел окно апплета перерисовывается.
Перерисовка окна осуществляется посредством вызова метода paint, который реализован очень просто
- он рисует служебное изображение image на экране.
 */

import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

public class ScrollingText extends JFrame implements Runnable {

    // Создаем класс , который будет потомком класса Applet и реализовывать
// интерфейс Runnable
    private String text;
    // Строка text - это тот текст который будет выводиться на экран
// Конец строки в тексте обозначается символом '|'
    private int delay;
    private Thread motor;
    private Image image;
    // Переменная image будет хранить служебное изображение
    private Graphics gs;
    private Graphics gi;
    //Графический контекст , необходимый для того, чтобы можно было рисовать на
//изображении так же , как и на экране
    private int shiftMax;
    private Dimension dim; // Переменная должна содержать размер окна апплета
    private FontMetrics fm; // В fm должны храниться свойства шрифта

    private ScrollingText() {
        text = "Java|Java Java|Java Java Java|Java Java Java Java|Java Java Java|Java Java|Java";
        delay = 20;
    }

    // Переменная delay позврляет управлять задержкой в методе run , т.е.
// скоростью перемещения текста по экрану
    public static void main(String[] s) {
        SwingUtilities.invokeLater(() -> {
            ScrollingText g = new ScrollingText();
            g.setVisible(true);
            g.init();
            g.start();
        });
    }

    //******************************************************
//* Метод init ( первоначальная инициализация апплета )*
//******************************************************
    private void init() {
        setSize(200, 200);
        setBackground(Color.white); //Устанавливаем цвет фона

        dim = size(); //Запоминаем размер окна апплета

        gs = getGraphics(); //Получаем графический контекст окна апплета

        image = createImage(dim.width, dim.height); //Создаем служебное
        //изображение ...
        gi = image.getGraphics(); //..и получаем графический контекст изображения

        gi.setFont(new Font("Helvetica", Font.PLAIN, 20)); // Устанавливаем шрифт

        fm = gi.getFontMetrics(); // запоминаем метрику шрифта

        shiftMax = fm.getHeight(); // запоминаем высоту шрифта

        gi.setColor(Color.blue); // устанавливаем цвет чернил

    }

    //**********************************************
//*Метод start ( инициализация объекта Thread )*
//**********************************************
    private void start() {

        motor = new Thread(this); // Создаем объект
        motor.start();

    }

    //******************************
//*Метод run ( запуск апплета )*
//******************************
    public void run() {
        motor.setPriority(Thread.MIN_PRIORITY); // Устанавливаем приоритет длz нашего процесса ( min )
        while (true) { // Начало бесконечного цикла
            String s;
            StringTokenizer st = new StringTokenizer(text, "|");
            int shift;
            while (st.hasMoreTokens()) { // Достаем из переменной text подстроки ,
                // разделенные символом '|' до тех пор ,
                // пока не достанем все
                s = st.nextToken();
                shift = 0;
                int x = (dim.width - fm.stringWidth(s)) / 2; // Центрируем выводимую
                // строку
                gi.drawString(s, x, dim.height - 5); // и рисуем внизу изображения image
                while (shift < shiftMax) { // Прокручиваем изображение, пока  внизу
                    // не освободится место для следующей
                    // строки
                    gi.copyArea(0, 0, dim.width, dim.height, 0, -1);
                    paint(gs);                 // Перерисовка экрана
                    shift++;
                    try {
                        Thread.sleep(1000 / delay); // пытаемся остановиться на 1000/delay
                        // милисекунд
                    } catch (InterruptedException e) {
                    }
                    ;

                }
            }
            // После того , как все строки выведены на экран , прокручиваем
            // картинку до тех пор , пока весь текст не выйдет за экран,
            // и повторяем цикл заново

            shift = shiftMax;
            while (shift <= dim.height) {
                gi.copyArea(0, 0, dim.width, dim.height, 0, -1);
                paint(gs);
                shift++;
                try {
                    Thread.sleep(1000 / delay);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    //***********************************
//Метод paint ( перерисовка экрана )*
//***********************************
    public void paint(Graphics g) {
        super.paintComponents(super.getGraphics());
        g.drawImage(image, 0, 0, Color.white, this);//Рисуем image на экране
    }

    public void update(Graphics g) {
        paint(g);
    }
}


