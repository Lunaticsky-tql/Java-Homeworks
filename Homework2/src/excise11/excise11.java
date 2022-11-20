package excise11;


class AllTheColorsOfTheRainbow
{
	int anIntergerRepresentingColors;
	void changeTheHueOfTheColor(int newHue) {
		
		anIntergerRepresentingColors=newHue;
		switch(anIntergerRepresentingColors)
		{
		case 0:System.out.println("red");break;
		case 1:System.out.println("orange");break;
		case 2:System.out.println("yellow");break;
		case 3:System.out.println("green");break;
		case 4:System.out.println("light blue");break;
		case 5:System.out.println("blue");break;
		case 6:System.out.println("purple");break;
		}
		
	}

}
public class excise11 {
	
	public static void main(String[]args) {

		AllTheColorsOfTheRainbow rainbow=new AllTheColorsOfTheRainbow();
		System.out.println(rainbow.anIntergerRepresentingColors);
		rainbow.changeTheHueOfTheColor(5);
		System.out.println();

	}
	
}
