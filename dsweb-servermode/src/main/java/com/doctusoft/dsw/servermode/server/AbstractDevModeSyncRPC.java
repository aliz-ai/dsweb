package com.doctusoft.dsw.servermode.server;

import com.doctusoft.Property;
import com.doctusoft.bean.binding.Bindings;
import com.doctusoft.bean.binding.ValueBinding;
import com.doctusoft.dsw.client.comp.model.ContainerModel;
import com.doctusoft.dsw.client.devmode.SyncRemoteService;
import com.doctusoft.synchronic.core.AsyncCallback;
import com.doctusoft.synchronic.core.ClientLogger;
import com.doctusoft.synchronic.core.ClientModelSynchronizer;
import com.doctusoft.synchronic.core.SimpleSynchronizationServer;
import com.doctusoft.synchronic.core.SynchronizationClient;
import com.doctusoft.synchronic.core.SynchronizationServerForClients;
import com.doctusoft.synchronic.core.message.AttributeChangedSyncMessage;
import com.doctusoft.synchronic.core.message.FullSyncMessage;
import com.doctusoft.synchronic.core.message.MessageFactory;
import com.doctusoft.synchronic.json.JsonParser;
import com.doctusoft.synchronic.json.JsonWriter;
import com.doctusoft.synchronic.serialization.ObjectModelFactory;
import com.google.common.collect.Lists;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.List;
import javax.servlet.ServletException;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDevModeSyncRPC extends RemoteServiceServlet implements SyncRemoteService {
	
    private static Logger log = LoggerFactory.getLogger(AbstractDevModeSyncRPC.class);

    List<String> messages = Lists.newArrayList();
	
	SimpleServer simpleServer = new SimpleServer();

	@Property @Getter @Setter
	private ContainerModel container;

	private ObjectModelFactory objectModelFactory;

	@Override
	public void init() throws ServletException {
		super.init();
		objectModelFactory = getObjectModelFactory();
		container = new ContainerModel();
		// this client is on the server side mirror of the model
		SimpleClient localClient = new SimpleClient(simpleServer, Bindings.on(this).get(AbstractDevModeSyncRPC_._container));
		log.info("dev sync server loaded");
		initApplication(container);
		log.info("application initialized");
		log.info("othercontainer: " + container.getChildren());
		log.info("local client id: " + localClient.synchronizer.getClientId());
	}
	
	public abstract void initApplication(ContainerModel container);
	public abstract ObjectModelFactory getObjectModelFactory();
	
	/**
	 * It's important to synchronize these calls. The synchronization framework is not thread-safe
	 */
	@Override
	public synchronized List<String> sync(List<String> clientMessages) {
		for (String message : clientMessages) {
			AttributeChangedSyncMessage parsedMessage = JsonParser.parse(message, AttributeChangedSyncMessage.class, new MessageFactory());
			simpleServer.broadcastMessage(parsedMessage);
		}
		List<String> serverMessages = Lists.newArrayList(messages);
		messages.clear();
		return serverMessages;
	}
	
	@Override
	public int registerClient() {
		return simpleServer.registerClient(new SynchronizationClient() {
			@Override
			public void listenToAttributeChange(AttributeChangedSyncMessage message) {
				// enqueue to send
				messages.add(JsonWriter.write(message));
				log.info("enqueuing message: " + message);
			}
		});
	}
	
	@Override
	public String fullSync() {
		FullSyncMessage message = simpleServer.synchronizeCompleteObjectModel();
		return JsonWriter.write(message);
	}

	public class SimpleServer {
		
		private final List<SynchronizationClient> clients = Lists.newArrayList();
		private final SimpleSynchronizationServer synchronizer = new SimpleSynchronizationServer();
		
		public Integer registerClient(SynchronizationClient client) {
			clients.add(client);
			return synchronizer.assignNewClientId();
		}
		
		public void broadcastMessage(AttributeChangedSyncMessage message) {
			List<AttributeChangedSyncMessage> messagesToBroadcast = synchronizer.processBroadcastMessage(message);
			for (AttributeChangedSyncMessage messageToBroadcast : messagesToBroadcast) {
				for (SynchronizationClient client : clients) {
					client.listenToAttributeChange(messageToBroadcast);
				}
			}
		}
		
		public FullSyncMessage synchronizeCompleteObjectModel() {
			return synchronizer.synchronizeCompleteObjectModel();
		}
	}
	
	public class SimpleClient {
		
		private ClientModelSynchronizer synchronizer;
	    
		public SimpleClient(final SimpleServer server, ValueBinding<?> modelBinding) {
			
			synchronizer = new ClientModelSynchronizer(objectModelFactory, new ClientLogger() {
				@Override
				public void info(String msg) {
					log.info(msg);
				}
			});
			
			synchronizer.initialize(new SynchronizationServerForClients() {
				
				@Override
				public void synchronizeCompleteObjectModel(AsyncCallback<FullSyncMessage> modelCallback) {
					modelCallback.run(server.synchronizeCompleteObjectModel());
				}
				
				@Override
				public void registerClient(AsyncCallback<Integer> idCallback) {
					idCallback.run(server.registerClient(new SynchronizationClient() {
						@Override
						public void listenToAttributeChange(AttributeChangedSyncMessage message) {
							// simulate the "network" by writing and parsing the message with JSON (that's why a proxy is necessary)
							String json = JsonWriter.write(message);
							AttributeChangedSyncMessage parsedMessage = JsonParser.parse(json, AttributeChangedSyncMessage.class, new MessageFactory());
							synchronizer.listenToAttributeChange(parsedMessage);
						}
					}));
				}
				
				@Override
				public void broadcastMessage(AttributeChangedSyncMessage message) {
					// simulate the "network" by writing and parsing the message with JSON
					String json = JsonWriter.write(message);
					AttributeChangedSyncMessage parsedMessage = JsonParser.parse(json, AttributeChangedSyncMessage.class, new MessageFactory());
					server.broadcastMessage(parsedMessage);
				}
			}, modelBinding);
		}
	}
}
