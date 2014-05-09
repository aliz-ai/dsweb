package com.doctusoft.dsw.client;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com.doctusoft.Property;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.dsw.client.comp.UIObjectFactory;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.devmode.SyncRemoteService;
import com.doctusoft.dsw.client.devmode.SyncRemoteServiceAsync;
import com.doctusoft.dsw.client.gwt.ContainerRenderer;
import com.doctusoft.synchronic.core.ClientLogger;
import com.doctusoft.synchronic.core.ClientModelSynchronizer;
import com.doctusoft.synchronic.core.SynchronizationServerForClients;
import com.doctusoft.synchronic.core.message.AttributeChangedSyncMessage;
import com.doctusoft.synchronic.core.message.FullSyncMessage;
import com.doctusoft.synchronic.core.message.MessageFactory;
import com.doctusoft.synchronic.json.JsonParser;
import com.doctusoft.synchronic.json.JsonWriter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.xedge.jquery.client.JQuery;

public abstract class DevModeSynchronizer {
	
	private SyncRemoteServiceAsync syncAsync = GWT.create(SyncRemoteService.class);

	private ClientModelSynchronizer synchronizer;

	@Property @Getter @Setter
	private ContainerModel container;
	
	/**
	 * This method is invoked when the container content is fetched initially from the sync server
	 * You have to append the rendered content to where you wish in the DOM.
	 */
	public abstract void appendContainer(JQuery renderedContainer);

	public DevModeSynchronizer() {
		container = new ContainerModel();
		synchronizer = new ClientModelSynchronizer(new UIObjectFactory(), new ClientLogger() {
			@Override
			public void info(String msg) {
				GWT.log(msg);
			}
		});
		
		synchronizer.initialize(new SynchronizationServerForClients() {
			
			@Override
			public void synchronizeCompleteObjectModel(final com.doctusoft.synchronic.core.AsyncCallback<FullSyncMessage> modelCallback) {
				syncAsync.fullSync(new AsyncCallback<String>() {
					@Override
					public void onFailure(Throwable caught) {
					}
					@Override
					public void onSuccess(String result) {
						FullSyncMessage parsedMessage = JsonParser.parse(result, FullSyncMessage.class, new MessageFactory());
						modelCallback.run(parsedMessage);
						ContainerRenderer rootRenderer = new ContainerRenderer(container);
						appendContainer(rootRenderer.getWidget());
					}
				});
			}
			
			@Override
			public void registerClient(final com.doctusoft.synchronic.core.AsyncCallback<Integer> idCallback) {
				syncAsync.registerClient(new AsyncCallback<Integer>() {
					@Override
					public void onSuccess(Integer result) {
						GWT.log("remote client id: " + result);
						idCallback.run(result);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
			}
			
			@Override
			public void broadcastMessage(AttributeChangedSyncMessage message) {
				syncAsync.sync(ImmutableList.of(JsonWriter.write(message)), new AsyncCallback<List<String>>() {
					
					@Override
					public void onSuccess(List<String> result) {
						for (String message : result) {
							AttributeChangedSyncMessage parsedMessage = JsonParser.parse(message, AttributeChangedSyncMessage.class, new MessageFactory());
							synchronizer.listenToAttributeChange(parsedMessage);
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {
						
					}
				});
			}
		}, Bindings.on(this).get(DevModeSynchronizer_._container));
		
		syncPartial();
	}

	protected void syncPartial() {
		syncAsync.sync(Lists.<String>newArrayList(), new AsyncCallback<List<String>>() {
			public void onSuccess(List<String> result) {
				for (String jsonString : result) {
					GWT.log("messages from the server: " + result);
					AttributeChangedSyncMessage message = JsonParser.parse(jsonString, AttributeChangedSyncMessage.class, new MessageFactory());
					synchronizer.listenToAttributeChange((AttributeChangedSyncMessage) message);
				}
			}
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
