package IndexMaker.src;
import java.util.TreeMap;

public class DocumentIndex {
	TreeMap<String, IndexEntry> words;
	
	public DocumentIndex() {
		words = new TreeMap<String, IndexEntry>(new IndexEntryComparator());
		
	}
	public void addWord(String word, int num) {
		IndexEntry entry = words.get(word);
		if (entry != null) { // add word to index if not already there
			words.put(word, new IndexEntry(word));
		}
		entry.add(num);
	}
	public void addAllWords(String str, int num) {
		String[] strs = str.split(" ");
		for (String word : strs) {
			addWord(word, num);
		}
	}
	
}
