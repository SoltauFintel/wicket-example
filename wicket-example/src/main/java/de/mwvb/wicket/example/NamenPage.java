package de.mwvb.wicket.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public class NamenPage extends WebPage {
	private static List<Namen> namenList = new ArrayList<Namen>(); // TODO noch quick-n-dirty
	
	static { // Demodaten anlegen
		Namen n = new Namen();
		n.setName("Joey");
		namenList.add(n);
		n = new Namen();
		n.setName("Willy");
		namenList.add(n);
	}
	
	public NamenPage() {
		Namen namen = new Namen();
		add(new NamenForm(namen));
		add(buildRows());
	}
	
	public class NamenForm extends Form<Namen> {

		public NamenForm(Namen namen) {
			super("form", new CompoundPropertyModel<Namen>(namen));
			add(new TextField<String>("name"));
		}
		
		@Override
		protected void onSubmit() {
			Namen copy = new Namen();
			copy.setName(getModelObject().getName());
			namenList.add(copy);
		}
	}
	
	private PropertyListView<Namen> buildRows() {
		IModel<List<Namen>> model;
		model = new LoadableDetachableModel<List<Namen>>() {
			@Override
			protected List<Namen> load() {
				namenList.sort(new Comparator<Namen>() {
					@Override
					public int compare(Namen a, Namen b) {
						return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
					}
				});
				return namenList;
			}
		};
		return new PropertyListView<Namen>("rows", model) {
			@Override
			protected void populateItem(ListItem<Namen> item) {
				item.add(new Label("name"));
			}
		};
	}
}
