package src;

import java.util.List;

public class GadsbySynonyms {
	private String thesaurusKey;
	
	public GadsbySynonyms(String thesaurusKey) {
		this.thesaurusKey = thesaurusKey;
	}

	public String gadsby(String text) throws Exception{//	NO PUNCTUATION
		String[] a = text.split(" ");
		text = "";
		for(int i=0; i<a.length; i++){
			a[i] = removeE(a[i]);
			text = text+a[i]+" ";
		}
		return text;
	}
	
	public String removeE(String word){
		if(word.indexOf("e")<0 && word.indexOf("E")<0)
			return word;
		ThesaurusReader tr = new ThesaurusReader(thesaurusKey);
		try{
			List<String> l = tr.getSysnonyms(word);
			for(String s:l){
				if(s.indexOf("e")<0 && s.indexOf("E")<0)
					return s;
			}
			return "CANNOT DO: "+word;
		} catch(Exception e){
			return "NO SYNONYMS: "+word;
		}
	}

	public String getThesaurusKey() {
		return thesaurusKey;
	}

	public void setThesaurusKey(String thesaurusKey) {
		this.thesaurusKey = thesaurusKey;
	}
	
	public static void main(String[] args) throws Exception{
		GadsbySynonyms gs = new GadsbySynonyms("96aa7d7d488903c187b24e05258291f4");
		String in = javax.swing.JOptionPane.showInputDialog("Enter a short sentence:");
		System.out.println(gs.gadsby(in));
	}
}
