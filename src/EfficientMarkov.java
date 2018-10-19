import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov{
	private HashMap<String, ArrayList<String>> myMap;
	
	public EfficientMarkov() {
		super();
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	
	@Override
	public ArrayList<String> getFollows(String key){
		if(!myMap.containsKey(key)) throw new NoSuchElementException(key+" not in map");
		return myMap.get(key);
	}
	
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap = new HashMap<String, ArrayList<String>>();
		for(int i = 0; i < text.length() - myOrder; i++) {
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
