package to.joe.timer;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Timer t = new Timer("Test Timer");
		
		Scanner s = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			String input = s.nextLine();
			if (input.equalsIgnoreCase("start")) {
				System.out.println(t.startTimer());
			} else if (input.equalsIgnoreCase("stop")) {
				t.stopTimer();
			} else if (input.equalsIgnoreCase("up")) {
				t.setDirection(Direction.UP);
			} else if (input.equalsIgnoreCase("down")) {
				t.setDirection(Direction.DOWN);
			} else if (input.equalsIgnoreCase("exit")) {
				exit = true;
				t.stopTimer();
			} else if (input.equalsIgnoreCase("set")) {
				t.setTime(s.nextInt());
			} else if (input.equalsIgnoreCase("speed")) {
				t.changeRate(s.nextInt());
			} else if (input.equals("ts")) {
				System.out.println(t);
			}
		}
		s.close();
	}

}
