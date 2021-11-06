package mqtt_box_demo_01;
import org.eclipse.paho.client.mqttv3.*;
import org.json.*;

import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
public class App{
	public static void main(String[] args) {
		String subTopic = "test/sub";
		String pubTopic = "test/pub";
		int qos = 2;
		String broker = "tcp://broker.hivemq.com:1883";
		String clientId = "test_client";
		MemoryPersistence persistence = new MemoryPersistence();
		
		JSONObject post_dict = new JSONObject();
		
        try {
			post_dict.put("Topic", "Test Iot");
	        post_dict.put("Group" ,"HDC");
	        post_dict.put("Content", "Demo MQTT with hivemq and mqttbox");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			MqttClient client = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			client.setCallback(new OnMessageCallback());
			System.out.println("Connecting to broker: "+broker);
			client.connect(connOpts);
			System.out.println("Connected");
			System.out.println("Publishing message: " + post_dict.toString());
			client.subscribe(subTopic);
			MqttMessage message = new MqttMessage(post_dict.toString().getBytes());
			message.setQos(qos);
			client.publish(pubTopic, message);
			System.out.println("Message published");
			//client.disconnect();
			System.out.println("Disconnected");
			//client.close();
			//System.exit(0);
		}
		catch(MqttException me) {
			me.printStackTrace();
		}
	}
}
