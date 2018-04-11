package com.atguigu.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 订阅方式接收（必须执行的）
 * @author Administrator
 *
 */
public class Comsumer_Topic3 {
	public static void main(String[] args) throws Exception {
		ConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.setClientID("1");
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Topic topic = session.createTopic("offic-topic");
		MessageConsumer consumer = session.createDurableSubscriber(topic, "1");
		// 接收消息
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				// 打印结果
				TextMessage textMessage = (TextMessage) message;
				String text = "";
				try {
					text = textMessage.getText();
				} catch (JMSException e) {
					e.printStackTrace();
				}
				System.out.println("潘长江听到了"+text+"，并说：我要努力学物理！！！");
			}
		});
		// 等待接收消息,让虚拟机处于启动并接收访问的状态，必须写
		System.in.read();
	}
}
