/*
 * Copyright (C) 2007  Trent Gamblin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Email the author at trentgamblin@yahoo.com
 */

package lincen.javame.util;

import java.util.Random;


/**
 * @author Karel.Lin
 * ɫ
 * returnInt()  ԷظöӦintɫֵ
 */

public class RGB{
	
	private int red;
	private int green;
	private int blue;
	private int brightness = 256;//ֵ Ĭ

	private static Random r = new Random();
	
	/**
	 * һɫ
	 * @param r_
	 * @param g_
	 * @param b_
	 */
	public RGB(int r_, int g_, int b_)	{
		red = r_;
		green = g_;
		blue = b_;
	}

	/**
	 * 
	 * percentʾɫֵ
	 * @param brightness 0 ~ 255
	 */
	public void setBrightness(int brightness){
		this.brightness = brightness;
	}

	/**
	 * ɫֵ
	 * @return
	 */
	public int getBrightness() {
		return brightness;
	}

	/**
	 * ɫֵ, ֵ
	 * @return
	 */
	public int getColor(){
		return 
		getR() * 256 * 256 + 
		getG() * 256 +
		getB();
	}

	/**
	 * @return the r with brightness
	 */
	public final int getR() {
		return red * getBrightness() / 256;
	}

	/**
	 * @return the g with brightness
	 */
	public final int getG() {
		return green * getBrightness() / 256;
	}

	/**
	 * @return the b with brightness
	 */
	public final int getB() {
		return blue * getBrightness() / 256;
	}

	/**
	 * ɫ
	 * @param color
	 */
	public void setColor(int color) {
		
		//׼ȡֵ
		this.red     = color / 0x010000 % 0x000100;
		this.green = color / 0x000100 % 0x000100;
		this.blue   = color / 0x000001 % 0x000100;
	}
	
	/**
	 * һɫ
	 * @return
	 */
	public synchronized static RGB getRandomRGB() {

		int n = r.nextInt(7);
		
		switch(n){
		case 0:
			return new RGB(213, 48, 48);//
		case 1:
			return new RGB(245, 123, 40);//
		case 2:
			return new RGB(248, 215, 100);// 
		case 3:
			return new RGB(50, 210, 70);//
		case 4:
			return new RGB(64, 68, 229);//
		case 5:
			return new RGB(40, 255, 220);// 
		case 6:
			return new RGB(154, 56, 221);//
			
		default :
			return new RGB(0, 0, 0);
		}
	}
	
}
