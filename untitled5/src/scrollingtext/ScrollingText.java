package scrollingtext;

/*
��⮤ run ���� ����୮� �������� ������. � ��� ॠ������� ��᪮���� 横�, � ���஬ ����।�� ������� ��ப� ����� �㦥����� ����ࠦ����, ���஥ ������ ��६�頥��� �� �࠭�. ��᫥ ������� ᬥ饭�� �� ���� ���ᥫ ���� ������ ����ᮢ뢠����. ����ᮢ�� ���� �����⢫���� ���।�⢮� �맮�� ��⮤� paint, ����� ॠ������� �祭� ���� - �� ���� �㦥���� ����ࠦ���� image �� �࠭�.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ScrollingText extends JFrame implements Runnable {

    // ������� ����� , ����� �㤥� ��⮬��� ����� Applet � ॠ�����뢠��
// ����䥩� Runnable
    String text = "Java|Java Java|Java Java Java|Java Java Java Java|Java Java Java|Java Java|Java";
    // ��ப� text - �� �� ⥪�� ����� �㤥� �뢮������ �� �࠭
// ����� ��ப� � ⥪�� ������砥��� ᨬ����� '|'
    int delay = 20;

    // ��६����� delay ������� �ࠢ���� ����প�� � ��⮤� run , �.�.
// ᪮����� ��६�饭�� ⥪�� �� �࠭�
    public static void main(String[] s) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                ScrollingText g = new ScrollingText();
                g.setVisible(true);
                g.init();
                g.start();
            }
        });
    }

    Thread motor;
    Image image;
    // ��६����� image �㤥� �࠭��� �㦥���� ����ࠦ����
    Graphics gs;
    Graphics gi;
    //����᪨� ���⥪�� , ����室��� ��� ⮣�, �⮡� ����� �뫮 �ᮢ��� ��
//����ࠦ���� ⠪ �� , ��� � �� �࠭�
    int shiftMax, shift;
    Dimension dim; // ��६����� ������ ᮤ�ঠ�� ࠧ��� ���� ������
    FontMetrics fm; // � fm ������ �࠭����� ᢮��⢠ ����

    //******************************************************
//* ��⮤ init ( ��ࢮ��砫쭠� ���樠������ ������ )*
//******************************************************
    public void init() {
        setSize(200, 200);
        setBackground(Color.white); //��⠭�������� 梥� 䮭�

        dim = size(); //���������� ࠧ��� ���� ������

        gs = getGraphics(); //����砥� ����᪨� ���⥪�� ���� ������

        image = createImage(dim.width, dim.height); //������� �㦥����
        //����ࠦ���� ...
        gi = image.getGraphics(); //..� ����砥� ����᪨� ���⥪�� ����ࠦ����

        gi.setFont(new Font("Helvetica", Font.PLAIN, 20)); // ��⠭�������� ����

        fm = gi.getFontMetrics(); // ���������� ���ਪ� ����

        shiftMax = fm.getHeight(); // ���������� ����� ����

        gi.setColor(Color.blue); // ��⠭�������� 梥� �୨�

    }

    //**********************************************
//*��⮤ start ( ���樠������ ��ꥪ� Thread )*
//**********************************************
    public void start() {

        motor = new Thread(this); // ������� ��ꥪ�
        motor.start();

    }

    //****************************************************
//*��⮤ stop ( �㦨� ��� ��⠭���� ࠡ��� ������ )*
//****************************************************
    public void stop() {

        motor.stop();
    }

    //******************************
//*��⮤ run ( ����� ������ )*
//******************************
    public void run() {

        motor.setPriority(Thread.MIN_PRIORITY); // ��⠭�������� �ਮ��� ���
        // ��襣� ����� ( min )
        // �� ���� �ਬ�� ,
        // ��祬 ��� �����
        while (true) { // ��砫� ��᪮��筮�� 横��
            String s;
            StringTokenizer st = new StringTokenizer(text, "|");
            while (st.hasMoreTokens()) { // ���⠥� �� ��६����� text �����ப� ,
                // ࠧ������� ᨬ����� '|' �� �� ��� ,
                // ���� �� ���⠭�� ��
                s = st.nextToken();
                shift = 0;
                int x = (dim.width - fm.stringWidth(s)) / 2; // ������㥬 �뢮�����
                // ��ப�
                gi.drawString(s, x, dim.height - 5); // � ��㥬 ����� ����ࠦ���� image
                while (shift < shiftMax) { // �ப��稢��� ����ࠦ����, ����  �����
                    // �� �᢮������� ���� ��� ᫥���饩
                    // ��ப�
                    gi.copyArea(0, 0, dim.width, dim.height, 0, -1);
                    paint(gs);                 // ����ᮢ�� �࠭�
                    shift++;
                    try {
                        Thread.sleep(1000 / delay); // ��⠥��� ��⠭������� �� 1000/delay
                        // ����ᥪ㭤
                    } catch (InterruptedException e) {
                    }
                    ;

                }
            }
            // ��᫥ ⮣� , ��� �� ��ப� �뢥���� �� �࠭ , �ப��稢���
            // ���⨭�� �� �� ��� , ���� ���� ⥪�� �� �멤�� �� �࠭,
            // � �����塞 横� ������

            shift = shiftMax;
            while (shift <= dim.height) {
                gi.copyArea(0, 0, dim.width, dim.height, 0, -1);
                paint(gs);
                shift++;
                try {
                    Thread.sleep(1000 / delay);
                } catch (InterruptedException e) {
                }
                ;

            }
        }
    }

    //***********************************
//��⮤ paint ( ����ᮢ�� �࠭� )*
//***********************************
    public void paint(Graphics g) {

        super.paintComponents(super.getGraphics());
        g.drawImage(image, 0, 0, Color.white, this);//���㥬 image �� �࠭�

    }

    public void update(Graphics g) {

        paint(g);

    }
}


