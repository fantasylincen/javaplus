package lincen.javame.game.numrubikcube.component;

import java.util.Random;
import java.util.Vector;

import lincen.javame.game.numrubikcube.canvas.PaintToKarelCanvasAble;
import lincen.javame.ui.geometric.Rect;
import lincen.javame.util.Pen;
import lincen.javame.util.RGB;
import lincen.javame.util.Tool;

public class NumRectContainer extends Rect implements PaintToKarelCanvasAble{

	/**
	 * ,
	 */
	private Vector components = new Vector();

	/**
	 * 
	 */
	private int order = 4;

	/**
	 * հλõ
	 */
	private int spacePosition;

	/**
	 * ɫ
	 */
	private RGB rgb = new RGB(70, 70, 70);

	private long timeStart = System.currentTimeMillis();

/*
	public NumRectContainer() {

		final Random r = new Random();
		new Thread(){
			public void run() {
				long sum = 0;
				while(true){

					System.out.println(sum++);
					switch(r.nextInt(4)){
					case 0:
						up();
						break;
					case 1:
						down();
						break;
					case 2:
						left();
						break;
					case 3:
						right();
						break;
					}
				}
			};
		}.start();
	}*/

	public void setRgb(RGB rgb) {
		this.rgb = rgb;
	}

	/**
	 * ý
	 * @return
	 */
	public synchronized int getOrder() {
		return order;
	}

	/**
	 * þĽ, ú, еľοȫ·
	 * @param order
	 */
	public synchronized void setOrder(int order){
		this.order = order;

		//оολ
		reset();
	}

	/**
	 * 򻭲һ
	 * @param comp
	 */
	private synchronized void add(NumRect comp){
		comp.setParent(this);
		this.components.addElement(comp);
	}

	/**
	 * оο
	 */
	public synchronized void reset() {

		//ƳеĶ
		removeAll();

		//ʼһħ
		init();

		//˳
		disruptTheOrder();
		
		timeStart = System.currentTimeMillis();
	}

	/**
	 * Ƴ
	 */
	private synchronized void removeAll(){
		this.components.removeAllElements();
	}

	public synchronized void paint(Pen g) {
		paintBackground(g);
		g.translate(this.getX(), this.getY());

		paintAllComponents(g);
		g.translate(-this.getX(), -this.getY());

	}

	/**
	 * հƶ
	 */
	public synchronized void up(){

		//ÿհϷ
		NumRect numr = getSpaceUp();

		if(numr != null){
			chageSpacePositionAndNumRectPosition(numr);
			numr.move(0, this.getWidth() / this.order);
		}
	}

	/**
	 * հƶ
	 */
	public synchronized void down() {

		//ÿհ·
		NumRect numr = getSpaceDown();

		if(numr != null){
			chageSpacePositionAndNumRectPosition(numr);
			numr.move(0, -this.getWidth() / this.order);
		}
	}

	/**
	 * հ
	 */
	public synchronized void left() {

		//ÿհ󷽵
		NumRect numr = getSpaceLeft();

		if( numr != null){
			chageSpacePositionAndNumRectPosition(numr);
			numr.move(this.getWidth() / this.order, 0);
		}
	}

	/**
	 * հ
	 */
	public synchronized void right() {

		//ÿհ󷽵
		NumRect numr = getSpaceRight();

		if( numr != null){
			chageSpacePositionAndNumRectPosition(numr);
			numr.move(-this.getWidth() / this.order, 0);
		}
	}

	/**
	 * ÿհϷ
	 * @return
	 */
	public NumRect getSpaceUp(){
		return  getNumRectWitchPositionIs(this.spacePosition - this.order);
	}

	/**
	 * ÿհ·
	 * @return
	 */
	public NumRect getSpaceDown(){
		return getNumRectWitchPositionIs(this.spacePosition + this.order);
	}

	/**
	 * ÿհ
	 * @return
	 */
	public NumRect getSpaceLeft(){
		if(this.spacePosition % this.order != 0){
			return getNumRectWitchPositionIs(this.spacePosition - 1);
		} else {
			return null;
		}
	}

	/**
	 * ÿհҷ
	 * @return
	 */
	public NumRect getSpaceRight(){
		if(this.spacePosition % this.order != order - 1){
			return getNumRectWitchPositionIs(this.spacePosition + 1);
		} else {
			return null;
		}
	}

	/**
	 * ÿհ
	 * @return
	 */
	public int getSpacePosition(){
		return this.spacePosition;
	}

	/**
	 * жǲȫ
	 * @return
	 */
	public synchronized boolean isAllComponentsInOrder(){
		for (int i = 0; i < components.size(); i++) {
			NumRect nr = (NumRect) components.elementAt(i);
			if(nr.getPosition() != nr.getValue() - 1){
				return false;
			}
		}

		return true;
	}

	/**
	 * Ϊ position 
	 * @param position
	 * @return
	 */
	private synchronized NumRect getNumRectWitchPositionIs(int position){
		for (int i = 0; i < components.size(); i++) {
			NumRect numr = (NumRect) components.elementAt(i);
			if(numr.getPosition() == position){
				return numr;
			}
		}
		return null;
	}

	/**
	 * հ  NumRect 
	 * @param numr
	 */
	private void chageSpacePositionAndNumRectPosition(NumRect numr) {
		this.spacePosition += numr.getPosition();
		numr.setPosition(this.spacePosition - numr.getPosition());
		this.spacePosition -= numr.getPosition();
	}

	/**
	 * ʼһħ
	 */
	private synchronized void init() {

		//order * order - 1  ־ε, ֵΪ1  order * order - 1   
		//Ϊеθֵ
		//ͬʱ趨ɫ
		addRectToContainer();

		this.spacePosition = this.getOrder() * this.getOrder() - 1;

		//ΪôС
		setSizeForEvreyComponent();

		//Ϊ
		setLocationForEvreyComponent();
	}

	/**
	 * ΪôС
	 */
	private synchronized void setSizeForEvreyComponent() {
		final int w = this.getWidth() / this.getOrder();
		for (int i = 0; i < components.size(); i++) {
			((Rect) components.elementAt(i)).setSize(w, w);
		}
	}

	/**
	 * Ϊ
	 */
	private synchronized void setLocationForEvreyComponent() {
		final int w = this.getWidth() / this.getOrder();
		for (int i = 0; i < components.size(); i++) {
			NumRect numr = (NumRect) components.elementAt(i);
			final int x = numr.getPosition() % order * w;
			final int y = numr.getPosition() / order * w;
			numr.setLocation(x, y);
		}
	}

	/**
	 * ˳
	 */
	private synchronized void disruptTheOrder() {
		Random r = new Random();
		for(int i = 0; i < 100; i++){
			switch(r.nextInt(4)){
			case 0:
				up();
				break;
			case 1:
				down();
				break;
			case 2:
				left();
				break;
			case 3:
				right();
				break;
			}
		}
	}

	/**
	 * order * order - 1  ־ε, ֵΪ1  order * order - 1   ֵ
	 */
	private synchronized void addRectToContainer() {
		for(int i = 0; i < this.order * order - 1; i++){
			NumRect numr = new NumRect();
			numr.setValue(i + 1);
			numr.setRgb(RGB.getRandomRGB());
			numr.setPosition(i);
			this.add(numr);
		}
	}


	/**
	 * Ʊ
	 * @param g
	 */
	private synchronized void paintBackground(Pen g) {
		int color = g.getColor();
		rgb.setBrightness(230);
		g.setColor(this.rgb.getColor());
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		Tool.fillBorder(g, this.rgb, this.getX(), this.getY(), this.getWidth(), this.getHeight());


		//ƿ
		fillPitting(g);

		g.setColor(color);
	}

	/**
	 * spacePositionλûһ
	 * @param g
	 */
	private synchronized void fillPitting(Pen g) {
		int width = this.getWidth() / order;
		int height = this.getHeight() / order;

		for(int i = 0;i<order;i++){
			for (int j = 0;j< order; j++){

				int x = j * width + this.getX();
				int y = i * height + this.getY();

				Tool.fillHexagon(
						x - 1, 
						y - 1, 
						width + 2, 
						height + 2, 
						g,
						rgb, 
						190, //
						140, //
						200, //
						130, //
						160,//
						160,//
						210//
				);
			}
		}
	}

	/**
	 * 
	 * @param g
	 */
	private synchronized void paintAllComponents(Pen g) {
		for (int i = 0; i < components.size(); i++) {
			((PaintToKarelCanvasAble)components.elementAt(i)).paint(g);
		}
	}

	public long getTimeUsed() {
		return System.currentTimeMillis() - timeStart;
	}
}
