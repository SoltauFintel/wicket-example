package de.mwvb.wicket.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NamenDAO {
	private static List<Namen> namenList = new ArrayList<Namen>(); // TODO noch quick-n-dirty
	
	static { // Demodaten anlegen
		Namen n = new Namen();
		n.setName("Joey");
		namenList.add(n);
		n = new Namen();
		n.setName("Willy");
		namenList.add(n);
	}

	public List<Namen> getNamen() {
		namenList.sort(new Comparator<Namen>() {
			@Override
			public int compare(Namen a, Namen b) {
				return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
			}
		});
		return namenList;
	}

	public void save(String name) {
		Namen neu = new Namen();
		neu.setName(name);
		namenList.add(neu);
	}
}
