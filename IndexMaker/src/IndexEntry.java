package IndexMaker.src;
import java.util.TreeSet;

public class IndexEntry {

	String word;
	String wordFormatted; // Decide if going to pass formatted word, would not allow me to split string on whitespace
	TreeSet<Integer> indices;
	
	public IndexEntry(String word) {
		this.word = word;
		indices = new TreeSet<Integer>();
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		return word;
	}
}
