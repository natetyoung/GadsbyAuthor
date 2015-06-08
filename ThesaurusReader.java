package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ThesaurusReader {
	private String key;
	
	public ThesaurusReader(String key) {
		this.key = key;
	}

	public List<String> getSynonyms(String word, String partOfSpeech) throws Exception{
		List<String> l = getThesaurusList(word);
		for(int i=l.size()-1; i>=0; i--){
			if(!l.get(i).substring(0,partOfSpeech.length()).equalsIgnoreCase(partOfSpeech)
					|| !l.get(i).substring(l.get(i).indexOf("|")+1, l.get(i).indexOf("|")+4).equals("syn"))
				l.remove(i);
		}
		for(int i=0; i<l.size(); i++){
			l.set(i,l.get(i).substring(partOfSpeech.length()+5));
		}
		return l;
	}
	
	public List<String> getSysnonyms(String word) throws Exception{
		List<String> l = getThesaurusList(word);
		for(int i=l.size()-1; i>=0; i--){
			if(!(l.get(i).substring(l.get(i).indexOf("|")+1, l.get(i).indexOf("|")+4).equals("syn"))
					||l.get(i).substring(l.get(i).indexOf("|")+1, l.get(i).indexOf("|")+4).equals("sim"))
				l.remove(i);
		}
		for(int i=0; i<l.size(); i++){
			l.set(i,l.get(i).substring(l.get(i).indexOf("|")+5));
		}
		return l;
	}
	
	private List<String> getThesaurusList(String word) throws Exception{
		URL bhtURL = new URL("http://words.bighugelabs.com/api/2/"+key+"/"+word+"/");
		BufferedReader r = new BufferedReader(new InputStreamReader(bhtURL.openStream()));
		List<String> l = new ArrayList<String>();
		boolean end = false;
		while(!end){
			l.add(r.readLine());
			end = l.get(l.size()-1)==null;
		}
		r.close();
		l.remove(l.size()-1);
		return l;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
