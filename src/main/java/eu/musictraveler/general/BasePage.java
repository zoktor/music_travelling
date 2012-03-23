package eu.musictraveler.general;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.google.code.jqwicket.ui.notifier.NotifierWebMarkupContainer;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;
	protected NotifierWebMarkupContainer notifier;

	public BasePage() {
		notifier = new NotifierWebMarkupContainer("notifier");
		add(notifier);
	}

	public class LinkPanel<T> extends Panel {
		private static final long serialVersionUID = 1L;

		/**
		 * @param id
		 *            component id
		 * @param model
		 *            model for contact
		 */
		public LinkPanel(String id, IModel<T> model, Link<T> link) {
			super(id, model);
			add(link);
		}
	}
}