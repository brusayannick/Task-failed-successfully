package lightShow.tutorial.animated_objects.animation02_nightsky.simple;

import java.util.Random;

import ledControl.BoardController;

public class MovingCloud {
	
	private final int[] innerColor	= new int[]{0, 0, 95};
	private final int[] outerColor	= new int[]{0, 0, 55};
	private final Random rng;

	private BoardController controller;
	private double xpos, ypos;
	private double speedHorizontal;
	
	// speed should be set to positive values small than 1
	public MovingCloud(BoardController controller){
		this.controller	= controller;
		rng	= new Random();
		this.xpos		= rng.nextInt(controller.getWidth());
		this.ypos		= rng.nextInt(controller.getHeight() - 1);
		this.speedHorizontal	= rng.nextDouble() / 5. + 0.1;
	}
	
	public void draw(){
		int x = (int) xpos;
		int y = (int) ypos;
		// inner pixels
		controller.setColor(x + 1, y + 1, innerColor);
		controller.setColor(x + 2, y + 1, innerColor);
		// outer pixels
		controller.setColor(x + 1, y, outerColor);
		controller.setColor(x + 2, y, outerColor);
		controller.setColor(x, y + 1, outerColor);
		controller.setColor(x + 3, y + 1, outerColor);
	}
	
	// Drawing pixels with this helping method, makes it easier to do the real drawing in the draw method, because the
	// tests whether the pixel coordinates are still on the board, are handled here and don't need to be repeatedly
	// written for every pixel drawn.
	
	public void update(){
		// move cloud
		xpos += speedHorizontal;
		
		// if it has left the board on the right side restart it on the left side with new speed and height
		if (xpos > controller.getWidth()){
			xpos = -3;
			this.ypos		= rng.nextInt(controller.getHeight() - 1);
			this.speedHorizontal	= rng.nextDouble() / 5. + 0.1;
		}
	}
}
