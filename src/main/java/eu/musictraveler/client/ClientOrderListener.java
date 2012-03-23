package eu.musictraveler.client;

import eu.musictraveler.company.model.ClientOrder;

 
public interface ClientOrderListener
{
	void onMessage(ClientOrder message);
}
