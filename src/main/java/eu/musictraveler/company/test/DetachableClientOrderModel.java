package eu.musictraveler.company.test;

import org.apache.wicket.model.LoadableDetachableModel;

import eu.musictraveler.company.model.ClientOrder;

public class DetachableClientOrderModel extends
		LoadableDetachableModel<ClientOrder> {
	private static final long serialVersionUID = 1L;
	private final Integer id;
	private ClientOrder c;

	public DetachableClientOrderModel(ClientOrder c) {
		this(c.getClorId());
		this.c = c;
	}

	public DetachableClientOrderModel(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof DetachableClientOrderModel) {
			DetachableClientOrderModel other = (DetachableClientOrderModel) obj;
			return other.id == id;
		}
		return false;
	}

	@Override
	protected ClientOrder load() {
		return c;
	}
}