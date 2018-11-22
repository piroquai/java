/*       ����� �ਬ�� �������� ��� �ᯮ�짮���� ����� ��� 
��� 㢥���⥫쭮� �⥪��. ���� ��६��� 㪠��⥫� �� 䮭����� ����ࠦ����, 
�� �㤥� ������ ����� 㢥��祭��� ����ࠦ����. ��筮 ⠪�� ��䥪� 
����� ������ �� ��ᬮ�� ���⨭�� �१ ��� � �����樥�⮬ 㢥��祭�� ࠢ�� ���.
������ 㢥���⥫쭮�� "�⥪��" ��⠢��� 100�100 ���ᥫ��. �����, ������� 
�࠭�, ��������� 㢥��祭��, ࠢ�� 50�50.
��� ⮣� �⮡� ����� �뫮 㢥��稢��� � ����ࠦ���� �ࠩ��� ����� � 
���孨� ���ᥫ��, ������� ᢥ��� � ᫥�� ������ ���������� �ਭ�� 
50 ���ᥫ��. ����� ��ࠧ��, ���� �窠 � ���न��⠬� (0,0) ����� ���� 㢥��祭�. 
 */

package mag;

import java.applet.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;



public class magnify1 extends Applet 
  
  {
    
     Graphics g;
    
     Image background; // ������� ���⨭��
     Color bgColor;  // ���� 䮭�
  
     boolean done_loading = false;
    
     int windowX = 0; // ������� ������ 㢥��祭��
     int windowY = 0;
     int Width = 100;
     int Height = 100;
     
        
     int width; // ����� ��६���� �࠭�� �ਭ� �
     int height; //  ����� 䮭����� ����ࠦ���� 
	

//=====================================================================

	public void init()
 	{
 	   			// ���⥪��
 	   
        try {

            g = getGraphics(); 
            // ����祭�� ����ࠦ���� ��� 䮭�
            bgColor = new Color(255, 255, 255);
            setBackground(bgColor);

            // ����祭�� ����ࠦ���� ��� 䮭�
//         background = Toolkit.getDefaultToolkit().getImage("");
            background = getImage(new URL("file:///I:/TemplateCourse/resource/practice_2/variant_4/post1.jpg"));

            width = size().width;
            height = size().height;

            // ��������� ����ࠦ���� ��� �࠭� (createImage), �
            // ��⥬ �뢮����� �� �࠭ �㭪樥� drawImage.
            Image offScrImage = createImage(width, height);
            Graphics offScrGC = offScrImage.getGraphics();
            offScrGC.drawImage(background, 0, 0, this);
        } catch (MalformedURLException ex) {
            Logger.getLogger(magnify1.class.getName()).log(Level.SEVERE, null, ex);
        }
	} 
 

	


//========================================================
public boolean mouseMove(Event evt, int x, int y)
	{  
    erase_window(); // ��। ���ᮢ��� ������ ���� ���� 㤠���� 
 	                  // �।��饥
 	  
 	  windowX = x; // ���࠭���� ����� ���न��� � 楫�� 
 	  windowY = y; //  㭨�⮦���� ᫥���饣� 㢥���⥫쭮�� ����
 	 
 	 // ���ᯥ祭�� 楫��⭮�� (�।���饭�� ��室� �� ����)
 	  if (windowX > width)
 	  windowX = width;
 	
 	  if (windowY > height)
 	  windowY = height;
 	  	  
 	 // ���ᮢ�� 㢥��祭���� ����ࠦ����
 	  draw_window();
 	  
 	 return true;
 	 } 

//=========================================================

 void draw_window()
 
    {
       Graphics g2;
     g2 = g.create();
     
     // clipRect ᮧ����  ��אַ㣮�쭨� ���祭�� � ࠧ��ࠬ� 
     // 㢥���⥫쭮�� ����
     g2.clipRect(windowX, windowY, Width, Height);
     
     g2.drawImage(background, -windowX-Width/2, -windowY-Height/2, width*2, height*2, null);
     
    //���ᮢ�� �࠭�� "㢥���⥫쭮�� �⥪��"
     g.setColor(Color.red);
     
     g.drawRect(windowX, windowY, Width-1, Height-1);
     setBackground(bgColor);
    }   
//============================================================
 void erase_window() // �������� �।��饣� ����
    		     // � ����⠭������� 䮭�
     {
     Graphics g2;
     g2 = g.create();
     
     g2.clipRect(windowX, windowY, Width, Height);
     
     g2.drawImage(background, Width/2, Height/2,  null);
     
     if ( windowX < Width/2)
       {
        g.setColor(Color.white);
        g.fillRect( 0, 0, Width/2, height + Height);
       }
      
      if ( windowY < Height/2)
       {
        g.setColor(Color.white);
        g.fillRect( 0, 0, width + Width, Height/2);
       }  
     
     if ( windowX > (width - Width/2))
       {
        g.setColor(Color.white);
        g.fillRect( width + Width/2, 0, Width/2, height + Height);
       }
      
      if ( windowY > (height - Height))
       {
        g.setColor(Color.white);
        g.fillRect( 0, height + Height/2, width + Width, Height/2);
       } 
       
      } 
       

//======================================================================
public boolean imageUpdate ( Image img, int infoflags, int x, int y,
					int w, int h)
	   {
	      // ���� 䮭���� ����ࠦ���� ����㦠����, ��ࠬ���
	      // infoflags �� ࠢ�� ALLBITS. ��६����� done_loading
	      // ࠢ�� false, � � ��ப� ����� �� ����� Loading
	       if (infoflags == ALLBITS)
	        {
	          
	          resize(background.getWidth(this) + Width, background.getHeight(this) + Height);
	          done_loading = true;
	          repaint();
	          
	          return false;
	        }
	      else 
	          return true;
	   }
	   
	   
//======================================================

public void paint (Graphics _g)
	  
     {
	     if (!done_loading)
	       showStatus ("Loading");
	  else     
	{
	showStatus ("OK");
	g.drawImage (background, Width/2, Height/2, this);
        draw_window();
         }
      } 
   
 	   
//==============================================
 }   
     
