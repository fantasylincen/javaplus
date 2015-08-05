package cn.javaplus.game.power.listeners;

import cn.javaplus.game.power.events.MoveEvent;

import cn.mxz.events.Listener;
public class PrintResult implements Listener<MoveEvent> {

	@Override
	public void onEvent(MoveEvent e) {
		int[][] values = e.getValues();
		for (int[] is : values) {
			print(is);
		}
		System.out.println("----------------------------");
		System.out.println();
	}

	private void print(int[] is) {
		for (int i : is) {
			System.out.print(i + "	");
		}
		System.out.println();
	}

}
