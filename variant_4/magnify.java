/*       Данный пример позволяет Вам использовать курсор мыши 
как увеличительное стекло. Просто перемещая указатель по фоновому изображению, 
Вы будете видеть вдвое увеличенное изображение. Точно такой эффект 
можно наблюдать при просмотре картинки через лупу с коэффициентом увеличения равным двум.
Размер увеличительного "стекла" составляет 100х100 пикселов. Значит, область 
экрана, подлежащая увеличению, равна 50х50.
Для того чтобы можно было увеличивать и изображения крайних левых и 
верхних пикселей, имеется сверху и слева полосы окаймления шириной 
50 пикселей. Таким образом, даже точка с координатами (0,0) может быть увеличена. 
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
    
     Image background; // Фоновая картинка
     Color bgColor;  // Цвет фона
  
     boolean done_loading = false;
    
     int windowX = 0; // Размеры квадрата увеличения
     int windowY = 0;
     int Width = 100;
     int Height = 100;
     
        
     int width; // Данные переменные хранят ширину и
     int height; //  высоту фонового изображения 
	

//=====================================================================

	public void init()
 	{
 	   			// контекста
 	   
        try {

            g = getGraphics(); 
            // Получение изображения для фона
            bgColor = new Color(255, 255, 255);
            setBackground(bgColor);

            // Получение изображения для фона
//         background = Toolkit.getDefaultToolkit().getImage("");
            background = getImage(new URL("file:///I:/TemplateCourse/resource/practice_2/variant_4/post1.jpg"));

            width = size().width;
            height = size().height;

            // Создается изображение вне экрана (createImage), а
            // затем выводится на экран функцией drawImage.
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
    erase_window(); // Перед прорисовкой нового окна надо удалить 
 	                  // предыдущее
 	  
 	  windowX = x; // Сохранение новых координат с целью 
 	  windowY = y; //  уничтожения следующего увеличительного окна
 	 
 	 // Обеспечение целостности (предотвращение выхода из окна)
 	  if (windowX > width)
 	  windowX = width;
 	
 	  if (windowY > height)
 	  windowY = height;
 	  	  
 	 // Прорисовка увеличенного изображения
 	  draw_window();
 	  
 	 return true;
 	 } 

//=========================================================

 void draw_window()
 
    {
       Graphics g2;
     g2 = g.create();
     
     // clipRect создает  прямоугольник отсечения с размерами 
     // увеличительного окна
     g2.clipRect(windowX, windowY, Width, Height);
     
     g2.drawImage(background, -windowX-Width/2, -windowY-Height/2, width*2, height*2, null);
     
    //Прорисовка границ "увеличительного стекла"
     g.setColor(Color.red);
     
     g.drawRect(windowX, windowY, Width-1, Height-1);
     setBackground(bgColor);
    }   
//============================================================
 void erase_window() // Удаление предыдущего окна
    		     // и восстановление фона
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
	      // Пока фоновое изображение загружается, параметр
	      // infoflags не равен ALLBITS. Переменная done_loading
	      // равна false, и в строке статуса Вы видите Loading
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
     
