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
		myWords = text.split("\\s+");
		myMap = new HashMap<>();
		for(int i = 0; i <= myWords.length - myOrder; i++) {
			WordGram wg = new WordGram(myWords, i, myOrder);
			ArrayList<String> temp = new ArrayList<String>();
			if(!myMap.containsKey(wg)) {
				if(i >= myWords.length - myOrder) {
					temp.add(PSEUDO_EOS);
					myMap.put(wg, temp);
					break;
				}
				temp.add(myWords[i+myOrder]);
				myMap.put(wg, temp);
			}
			
			else {
				temp = myMap.get(wg);
				if(i >= myWords.length - myOrder) {
					temp.add(PSEUDO_EOS);
					myMap.put(wg, temp);
					break;
				}
				temp.add(myWords[i+myOrder]);
				myMap.put(wg, temp);
			}
			
		}
	}
}
