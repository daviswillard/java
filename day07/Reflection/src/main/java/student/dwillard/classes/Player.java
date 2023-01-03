package student.dwillard.classes;

import java.util.Random;

public class Player {
	private String nickname;
	private String realName;
	private Double apm;
	private Long experience;

	public Player() {
		apm = 120.0;
		realName = "John Smith";
		nickname = "default nickname";
		experience = 0L;
	}

	public Player(String nickname, String realName, Double apm, Long experience) {
		this.nickname = nickname;
		this.realName = realName;
		this.apm = apm;
		this.experience = experience;
	}

	public void train(double hours) {
		apm += hours / 100;
		experience += Math.round(hours);
	}

	public String trashTalk(String target) {
		return target + ", you are worst, delete this game.";
	}

	@Override
	public String toString() {
		return String.format("Player: %s\n with real name: %s.\nHe has %.1f apm and %d hours"
						+ " of experience.", nickname, realName, apm, experience);
	}
}
