package mqtt_box_demo_01;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class OnMessageCallback implements MqttCallback {
	public void connectionLost(Throwable cause) {
		System.out.println("Disconnect, you can reconnect");
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception{
		System.out.println("Reeceived message topic: "+topic);
		System.out.println("Received message Qos: "+ message.getQos());
		System.out.println("Received message content: "+new String(message.getPayload()));
		
	}
	
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("DeliveryComplete-----"+token.isComplete());
	}
}
