import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFrame;

public class Component extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static boolean isRunning;
	public static Dimension size;
	public static JFrame frame;
	private String name;
	private Image screen;

	private int badID=0;
	
	public static int tickCount;
	//public static Images im;
	public static Button b;
	
	

	public Component() {
		tickCount = 0;
		//im = new Images();
		b = new Button();
		name = "Sign-in System";
		isRunning = false;
		size = new Dimension(900, 650);
		setPreferredSize(size);
		
		/*addMouseListener(new Listener());
		addMouseMotionListener(new Listener());
		addKeyListener(new Listener());*/
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000d / 60;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while (isRunning) {
			screen = createVolatileImage(size.width, size.height);
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				//System.out.println("Ticks: " + ticks + " Frames: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		//b.tick();
		tickCount++;
		badID--;
	}
	
	public void badID() {
		badID=120;
	}
	
	public void render() {
		Graphics g = screen.getGraphics();
		
		//b.render(g);
		
		g = getGraphics();

		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, size.width, size.height, null);
		
		if (badID>0) {
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("INVALID", 69, 69);
		}
		
		g.dispose();
	}

	public void start() {
		isRunning = true;

		frame = new JFrame();
		frame.setSize(900, 650);
		frame.setTitle(name);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.GRAY);
		
		frame.add(this);
		
		frame.validate();
		new Thread(this).start();
		
		Scanner in = new Scanner(System.in);
		while (!Globals.done) {
			try{
				System.out.print("Enter ID: ");
				int ID = in.nextInt(); // do something if this isn't an int
				Student temp = new Student(ID); // student will do it's own database lookups
				System.out.println(temp);
			} catch (IDoutOfRangeException e) {
				badID();
				System.out.println("Out of range");
			} catch (InputMismatchException e) {
				System.out.println("ID too big or contains letters");
				badID();
				in.next(); //this is magic
			}
		}
		
	}

	public static void stop() {
		isRunning = false;
	}

	public static void main(String args[]) {
		new Component().start();
	}

}
