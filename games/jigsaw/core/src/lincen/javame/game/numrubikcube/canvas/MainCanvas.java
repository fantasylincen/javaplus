package lincen.javame.game.numrubikcube.canvas;

import java.util.Random;

import org.javaplus.game.common.log.Log;

import lincen.javame.game.numrubikcube.component.NumRect;
import lincen.javame.game.numrubikcube.component.NumRectContainer;
import lincen.javame.game.numrubikcube.component.Star;
import lincen.javame.util.Pen;
import cn.javaplus.jigsaw.define.D;

public class MainCanvas extends KarelCanvas_Old {

	private NumRectContainer numRectContainer = new NumRectContainer();

	private Button reset = new ButtonImpl();

	private Button exit = new ButtonImpl();

	private Button change = new ButtonImpl();
	
	public MainCanvas() {

		Random r = new Random();

		for(int i = 0;i<20;i++){
			this.add(new Star(r.nextInt(240) + 5, r.nextInt( 310) + 5));
		}

		numRectContainer.setSize(this.getWidth() - 50, this.getWidth() - 50);
		numRectContainer.setLocation(this.getWidth() / 2 - numRectContainer.getWidth() / 2, this.getHeight() / 2 - numRectContainer.getHeight() / 2);
		numRectContainer.setOrder(3);
		this.add(numRectContainer);
		
		reset.setText("reset");
		exit.setText("exit");
		change.setText("change");
		reset.setSize(30, 20);
		exit.setSize(30, 20);
		change.setSize(50, 20);
		

		//հ
		final int spaceLength = 10;
		reset.setLocation(spaceLength, this.getHeight() - reset.getHeight() - spaceLength);
		exit.setLocation(this.getWidth() - exit.getWidth() - spaceLength, this.getHeight() - reset.getHeight() - spaceLength);
		change.setLocation(this.getWidth() / 2 - change.getWidth() / 2, this.getHeight() - change.getHeight() - spaceLength);
		
		this.add(reset);
		this.add(exit);
		this.add(change);
	}

	private int getHeight() {
		return D.STAGE_H;
	}

	private int getWidth() {
		return D.STAGE_W;
	}

	public void paintBackground(Pen g) {
//		int color = g.getColor();
//		g.setColor(0x000730);
//		g.fillRect(0, 0, this.getWidth(), this.getHeight());
//		g.setColor(color);
	}

	protected void keyPressed(int keyCode) {
		switch (keyCode) {

		case 50:
			numRectContainer.down();
			break;
		case 52:
			numRectContainer.right();
			break;
		case 54:
			numRectContainer.left();
			break;
		case 56:
			numRectContainer.up();
			break;

		case -1:
			numRectContainer.down();
			break;
		case -3:
			numRectContainer.right();
			break;
		case -4:
			numRectContainer.left();
			break;
		case -2:
			numRectContainer.up();
			break;

		case -6:
			numRectContainer.reset();
			break;
		case -7:
			exit();
			break;
		case -5:
			change();
			break;
		}

		if(numRectContainer.isAllComponentsInOrder()){
			jumpToResultCanvas();
		}
	}

	private void jumpToResultCanvas() {
		Log.d("jumpToResultCanvas");
	}

	private void exit() {
		System.exit(0);
	}

	protected void pointerPressed(int x, int y) {
	
		NumRect spaceLeft = numRectContainer.getSpaceLeft();
		NumRect spaceRight = numRectContainer.getSpaceRight();
		NumRect spaceUp = numRectContainer.getSpaceUp();
		NumRect spaceDown = numRectContainer.getSpaceDown();
	
		if(this.reset.contains(x, y)){
			numRectContainer.reset();
		} else if (this.exit.contains(x, y)){
			exit();
		} else if(this.change.contains(x, y)){
			change();
		} else if(spaceLeft!=null && spaceLeft.contains(x - numRectContainer.getX(), y- numRectContainer.getY())){
			numRectContainer.left();
		} else if(spaceRight!=null && spaceRight.contains(x- numRectContainer.getX(), y- numRectContainer.getY())){
			numRectContainer.right();
		} else if(spaceUp!=null && spaceUp.contains(x- numRectContainer.getX(), y- numRectContainer.getY())){
			numRectContainer.up();
		} else if(spaceDown!=null && spaceDown.contains(x- numRectContainer.getX(), y- numRectContainer.getY())){
			numRectContainer.down();
		}

		if(numRectContainer.isAllComponentsInOrder()){
			jumpToResultCanvas();
		}
	}

	private void change() {
		if(this.numRectContainer.getOrder() == 3){
			this.numRectContainer.setOrder(4);
		} else {
			this.numRectContainer.setOrder(3);
		}
	}

}
