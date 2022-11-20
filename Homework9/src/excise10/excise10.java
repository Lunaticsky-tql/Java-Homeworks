package excise10;

interface Instrument{
	void adjust();
}
interface Playable{
	void play(Note n);
}
class Wind implements Instrument,Playable{
	@Override
	public void play(Note n) {
		System.out.println(this+".play()"+n);
	}
	@Override
	public String toString() {
		return "Wind";
	}
	@Override
	public void adjust() {
		System.out.println(this+".adjust()");
		
	}
}
class Percussion implements Instrument,Playable{
	@Override
	public void play(Note n) {
		System.out.println(this+".play()"+n);
	}
	@Override
	public String toString() {
		return "Percussion";
	}
	@Override
	public void adjust() {
		System.out.println(this+".adjust()");
		
	}
}
class Stringed implements Instrument,Playable{
	@Override
	public void play(Note n) {
		System.out.println(this+".play()"+n);
	}
	@Override
	public String toString() {
		return "Stringed";
	}
	@Override
	public void adjust() {
		System.out.println(this+".adjust()");
		
	}
}
class Brass extends Wind{
	@Override
	public String toString() {

		return "Brass";
	}
}
class Woodwind extends Wind{
	@Override
	public String toString() {

		return "Woodwind";
	}
}
public class excise10 {

	static void tune(Playable p)
	{
		p.play(Note.MIDDLE_C);
	}
	static void tuneall(Playable[] a) {
		for(Playable p:a)
			tune(p);
	}

	public static void main(String[] args) {
		Playable[] orchestra= {
				new Wind(),
				new Percussion(),
				new Stringed(),
				new Brass(),
				new Woodwind()
				};
		tuneall(orchestra);
	}
}
