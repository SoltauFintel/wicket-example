package de.mwvb.wicket.example;

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
	private NamenDAO namenDAO = new NamenDAO(); // TODO D.I.
	
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
			namenDAO.save(getModelObject().getName());
		}
	}
	
	private PropertyListView<Namen> buildRows() {
		IModel<List<Namen>> model = new LoadableDetachableModel<List<Namen>>() {
			@Override
			protected List<Namen> load() {
				return namenDAO.getNamen();
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
