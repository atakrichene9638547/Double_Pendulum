package pendule;
import java.awt.* ;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Fenestra extends JFrame implements Runnable, MouseListener { 
	 private final int height =600;
	 private final int width =470;
	 private final int h= height/3;
	 private double r1 = 100;
	 private double r2 =100;
	 private double m1 = 16;
	 private double m2 =16;
	 private double a1 =Math.PI/4;
	 private double a2 = -Math.PI/8;
	// private double px2 = -1;
	// private double py2= -1;
	 
	 private Thread thread;
	 private boolean running = false;
	 private double a1_v = 0;
	 private double a2_v = 0;
	 private double a1_a = 0.001;
	 private double a2_a = -0.0001;
	 private final double g = 1;
	 private ArrayList<Shape> graphic = new ArrayList() ;
	 
	 public double getA1() {
			return a1 ; 
		}
	 public double getA2() {
			return a2 ; 
		}
	 
	 
	 
	 public double getR1() {
		return r1 ; 
	}

	 
	 public void setA1(int a1) {
			if (a1<=0) this.a1=0;
			this.a1 = a1;
		}
	 public void setA2(int a2) {
			if (a2<=0) this.a2=0;
			this.a2 = a2;
		}
	 
	public void setR1(int r1) {
		if (r1<=0) this.r1=100;
		this.r1 = r1;
	}


	public double getR2() {
		return r2;
	}


	public void setR2(int r2) {
		if (r2<=0) this.r2=100;
		this.r2 = r2;
	}

	public double getM1() {
		return m1;
	}

	public void setM1(int m1) {
		if (m1<=0) this.r1=10;
		this.m1 = m1;
	}

	public double getM2() {
		return m2;
	}

	public void setM2(int m2) {
		if (m2<=0) this.m2=10;
		this.m2 = m2;
	}

	private JSlider slider = new JSlider(0,300,100); //r1
	
	private JSlider slider_1 = new JSlider(0,300,100);//r2
	
	private JSlider slider_2 = new JSlider(4,40,16); //m1
	
	private JSlider slider_3 = new JSlider(4,40,16); //m2
	
	private JSlider slider_4 = new JSlider();
	
	private JSlider slider_5 = new JSlider();
	
	private JSlider slider_6 = new JSlider();
	
	Image img = Toolkit.getDefaultToolkit().getImage("image\\\\pp.jpg");
	
	public Fenestra() /*throws FileNotFoundException, IOException*/  {
		//this.setLayout(new BorderLayout());
		
		 setBounds(250,250,width,height);
		 setVisible(true);
		 setTitle("Double Pendule Simulation");
		 JPanel pan =new JPanel() {
	         @Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(img, 0, 0, null);
	         }
	      } ;
		 this.setContentPane(pan);
		 pan.setBounds(0, 0, width, height);;
	      //pack();
	      setVisible(true);
		 
		
			JButton btnNewButton_1 = new JButton("start");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					start();
				}
			});
			
			JButton btnNewButton = new JButton("stop");
			btnNewButton.addActionListener(new ActionListener() {
				

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					stop();
					
				}	
			});
			
			
			GroupLayout groupLayout = new GroupLayout(this.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addComponent(slider_5, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(slider_6, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
								.addComponent(slider_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(slider_2, 0, 0, Short.MAX_VALUE)
							.addComponent(slider, 0, 0, Short.MAX_VALUE)
							.addComponent(slider_1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
						.addComponent(btnNewButton_1)
						.addGap(18)
						.addComponent(btnNewButton)
						.addContainerGap())
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addContainerGap(456, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(slider_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(slider_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(slider_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnNewButton)
									.addComponent(btnNewButton_1))
								.addGap(23))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(slider_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(slider_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(slider_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
			);
			this.getContentPane().setLayout(groupLayout);
			
		// start() ; 
		 
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
	 }
	public void paint(Graphics g){
		//Container cont = new Container();
		double x1 =(r1 * Math.sin(a1) ) + (width/2) ;
		 double y1= r1* Math.cos(a1) + h ;
		double x2= x1 + r2 * Math.sin(a2) ;
		 double y2 = y1 + r2 * Math.cos(a2) ;
		 
		 
		   Graphics2D g2 = (Graphics2D) g ; 
		   Shape toit= new Line2D.Double(0, h, width,h);
		   Shape masse1 = new Ellipse2D.Double(x1-(m1/2), y1-(m1/2),m1,m1) ;
		   Shape l1= new Line2D.Double(width/2, h, x1, y1);
		   Shape l2 = new Line2D.Double(x1,y1,x2,y2);
		   Shape masse2 = new Ellipse2D.Double(x2-(m2/2), y2-(m2/2),m2,m2);
		     
		      graphic.add(masse1);
			  graphic.add(masse2);
			  graphic.add(l2);
			  graphic.add(l1);
			  graphic.add(toit);
			  super.paint(g);
			  g2.setStroke(new BasicStroke(2.0f));
			  g2.draw(l2);
			  g2.draw(l1);
			  g2.draw(toit);
			  g2.setColor(Color.red);
			  g2.fill(masse1);
			  g2.draw(masse1);
			  g2.fill(masse2);
			  
			  g2.draw(masse2);
			 
		
		}
	
	public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			a1+=a1_v;
			a2+=a2_v;
			a1_v+=a1_a;
			a2_v+=a2_a;
			
			calcAcc() ;
			
			
			graphic.clear();
			repaint();
			//System.out.println("aa");
			
		}
		
	}
	public void calcAcc() {
		double num1 = -g * (2 *m1 +m2)* Math.sin(a1);
		double num2 = - m2 * g * Math.sin(a1-2*a2) ;
		double num3 = -2* Math.sin(a1-a2) *m2 *(Math.pow(a2_v, 2) *r2 + Math.pow(a1_v, 2) * r1 *Math.cos(a1-a2));
		double den = r1 * (2*m1+m2-m2* Math.cos(2*a1 - 2*a2)) ;
		a1_a = (num1+num2+num3)/den ;
		double num4 = 2* Math.sin(a1-a2)*(Math.pow(a1_v, 2)*r1*(m1+m2)+g*(m1+m2)*Math.cos(a1)+Math.pow(a2_v, 2)*r2*m2*Math.cos(a1-a2));
		double den2 = r2*(2*m1+m2-m2*Math.cos(2*a1-2*a2));
		a2_a= num4/den2 ;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
