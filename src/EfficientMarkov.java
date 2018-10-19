import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{
	private HashMap<String, ArrayList<String>> myMap;
	
	//Call the constructor from BaseMarkov (With no parameter) to initialize myOrder and myRandom, then initialize the HashMap myMap
	public EfficientMarkov() {
		super();
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	//Call the constructor from BaseMarkov (With parameter order passed) to initialize myOrder and myRandom, then initialize the HashMap myMap
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	/*
	 * Method to retrieve ArrayList of characters that follow a certain key from the text passed in to setTraining
	 * @param key is the key in the HashMap myMap of which the ArrayList that is associated with that key is desired
	 */
	@Override
	public ArrayList<String> getFollows(String key){
		if(!myMap.containsKey(key)) throw new NoSuchElementException(key+" not in map");
		return myMap.get(key);
	}
	/*
	 * setTraining creates a HashMap of keys, which are Strings of whatever length order is, and values, 
	 * which are ArrayLists of all the characters that follow the key
	 * @param text is the complete file/body of text that is the source for the HashMap
	 */
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap = new HashMap<>();
		for(int i = 0; i <= text.length() - myOrder; i++) {
			String str = text.substring(i, i+myOrder);
			ArrayList<String> temp = new ArrayList<String>();
			if(!myMap.containsKey(str)) {
				if(str.length() + i >= text.length()) {
					temp.add(PSEUDO_EOS);
					myMap.put(str, temp);
					break;
				}
				temp.add(myText.substring(i+str.length(), i + str.length() + 1));
				myMap.put(str, temp);
			}
			
			else {
				temp = myMap.get(str);
				if(str.length() + i >= text.length()) {
					temp.add(PSEUDO_EOS);
					myMap.put(str, temp);
					break;
				}
				temp.add(myText.substring(i+str.length(), i + str.length() + 1));
				myMap.put(str, temp);
			}
			
		}
	}
}


