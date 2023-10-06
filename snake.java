//1
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class fdemo extends JFrame
{
	fdemo()
	{
		setTitle("SNAKE GAME");
		JPdemo jp;
		jp=new JPdemo();
		add(jp);
	}
}
//2
class JPdemo extends JPanel implements ActionListener, KeyListener
{
	Font f1=new Font("",Font.BOLD,50);
	int x[]=new int [100];
	int y[]=new int [100];
	int dots=5;
	int r11=0,r22=0;
	int points=0;
	
	int s=0;
	boolean space=false;
	ImageIcon img1,img2,img3,img4,img5;
	Image body,food,head,gameover,snakeside;
	boolean sg=false,go=false,m=true;
	boolean right=true,left=false,up=false,down=false;
	JButton b1;
	
	Timer t=new Timer(200,this);
	
//3
//starting position of snake
	JPdemo()
	{
		x[0]=200;
		y[0]=80;
		
		x[1]=160;
		y[1]=80;
		
		x[2]=120;
		y[2]=80;
		
		x[3]=80;
		y[3]=80;
		
		x[4]=40;
		y[4]=80;
		
		setBackground(Color.green);
		// image snake body blue color
		img1=new ImageIcon("kkk.png");
		body=img1.getImage();
		// image snake food red color
		img2=new ImageIcon("qq.png");
		food=img2.getImage();
		// image snake face right side
		img3=new ImageIcon("facefright.png");
		head=img3.getImage();
		//snake game over gif
		img4=new ImageIcon("snakego.gif");
		gameover=img4.getImage();
		//right hand side gif 
		img5=new ImageIcon("snakes.gif");
		snakeside=img5.getImage();
		
		// reset button
		setLayout(null);
		b1=new JButton("RESET");
		b1.setSize(100,35);
		b1.setLocation(1170,200);
		b1.setBackground(Color.yellow);
		b1.setFocusable(false);
		add(b1);
		b1.addActionListener(this);
		
		t.start();	
		
		addKeyListener(this);
		setFocusable(true);
		randomdemo();
	}

	//4
	// generate random position for food
	void randomdemo()
	{
		r11=(int)Math.round(Math.random()*20+1);
		r11=r11*40;
		r22=(int)Math.round(Math.random()*14+1);
		r22=r22*40;
	}
		
	//5
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// g.fillRect(15,20,1100,720);
		// g.fillRect(1150,20,150,100);
	// back boundry
		g.fillRect(15,20,1100,555);
		g.fillRect(1150,20,150,100);
		g.setColor(Color.black);
		g.setColor(Color.yellow);
		g.setFont(f1);
		g.drawString(""+points,1210,90);
		g.drawImage(food,r11,r22,this);
		g.drawImage(snakeside,1140,300,this);
		
		
		for(int i=0;i<dots;i++)
		{
			if(i==0)
			{
			g.drawImage(head,x[i],y[i],this);
			}
			else
			g.drawImage(body,x[i],y[i],this);
		}
		if(go)
		{
			g.drawImage(gameover,350,200,this);
			g.drawString("GAME OVER BUDDY:(",300,150);
			g.drawString("YOUR SCORE = "+points,350,550);
		}
		if(m)
		{
			g.drawString("press SPACE for play pause",300,150);
		}
	}
	
	//6
	public void actionPerformed(ActionEvent e)
	{	
		if(e.getSource()==b1)
		{
			x[0]=200;
			y[0]=80;
			
			x[1]=160;
			y[1]=80;
			
			x[2]=120;
			y[2]=80;
			
			x[3]=80;
			y[3]=80;
			
			x[4]=40;
			y[4]=80;
			
			dots =5;
			
			sg=false;
			go=false;
			m=true;
			right=true;
			left=false;
			up=false;
			down=false;
			img3=new ImageIcon("facefright.png");
			head=img3.getImage();
			points=0;
		}
		
		for(int i=dots;i>0;i--)
		{
			if((i>1)&&(x[0]==x[i])&&(y[0]==y[i]))
			{
				sg=false;
				go=true;
			}
			// game over boundry
			if(x[0]<=0||y[0]<=20||x[0]>=1050||y[0]>=550)
			{
				sg=false;
				go=true;
				
			}
			if(x[0]==0)
			{
				x[0]=1150;
				
			}
		}
		
		if(x[0]==r11&&y[0]==r22)
		{
			dots++;
			points++;	
			randomdemo();
		}
		
		if(sg)
		{
			for(int i=dots;i>0;i--)
			{
				x[i]=x[i-1];
				y[i]=y[i-1];
			}
			
			if(right)
			{
				x[0]=x[0]+40;
			}
			
			if(left)
			{
				x[0]=x[0]-40;
			}
			
			if(up)
			{
				y[0]=y[0]-40;
			}
			if(down)
			{
				y[0]=y[0]+40;
			}
		}
		repaint();
	}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
	    	sg=!sg;	
			m=false;			
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			// if condition are used to stop oppsite direction left to right and from up to down vices versa 
			if(left!=true)
			{	
				// s is use in switch case to change the face according to direction		
				s=1;
				right=true;
				left=false;
				up=false;
				down=false;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(right!=true)
			{
				s=2;
				right=false;
				left=true;
				up=false;
				down=false;	
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			if(down!=true)
			{
				s=3;
				right=false;
				left=false;
				up=true;
				down=false;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			if(up!=true)
			{				
				s=4;
				right=false;
				left=false;
				up=false;
				down=true;
			}
		}
		switch(s)
		{
			case 1:
			img3=new ImageIcon("facefright.png");
			break;
			case 2:
			img3=new ImageIcon("facefleft.png");
			break;
			case 3:
			img3=new ImageIcon("facefup.png");
			break;
			case 4:
			img3=new ImageIcon("facefdown.png");
			break;
		}
		head=img3.getImage();
		repaint();
			
	}
	public void keyTyped(KeyEvent e){}
}
class snake
{
	public static void main(String ar[])
	{
		fdemo f=new fdemo();
		f.setVisible(true);
		f.setSize(1350,650);
		f.setLocation(10,30);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	}
}
