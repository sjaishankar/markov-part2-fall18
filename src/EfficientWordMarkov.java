import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov{
	private HashMap<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov() {
		super();
		myMap = new HashMap<>();
	}
	
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	
	public ArrayList<String> getFollows(WordGram kgram){
		if(!myMap.containsKey(kgram)) throw new NoSuchElementException(kgram+" not in map");
		return myMap.get(kgram);
	}
	
	public void setTraining(String text) {
		
	}
}
