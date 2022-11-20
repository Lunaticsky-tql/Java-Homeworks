package reusing_class;

abstract class Actor {

	protected void act() {
	}
}

class HappyActor extends Actor {

	@Override
	protected void act() {
		System.out.println("Happy actor is acting");
	}
}

class SadActor extends Actor {

	@Override
	protected void act() {
		System.out.println("Sad actor is acting");
	}
}

class Stage {

	Actor actor = new HappyActor();

	public void perform() {
		actor.act();

	}

	public void change() {
		actor = new SadActor();

	}

}

public class StageMode {

	public static void main(String[] args) {
		Stage stage = new Stage();
		stage.perform();
		stage.change();
		stage.perform();
	}
}
