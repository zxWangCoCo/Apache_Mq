package com.atguigu.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 定于发布
 * @author Administrator
 *
 */
public class Producer_Topic {

	public static void main(String[] args) throws Exception {
		// 发送队列模式的消息
		//此处地址使用tcp协议访问
		ConnectionFactory connectionFactory = 
				new ActiveMQConnectionFactory("tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//创建一个会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//消息对象,String是消息的名称
		Topic topic = session.createTopic("offic-topic");
		//发送端
		MessageProducer producer = session.createProducer(topic);
		//消息内容
		TextMessage textMessage = session.createTextMessage("为了中华民族的崛起而奋斗");
		//发送
		producer.send(textMessage);
		//关闭
		producer.close();
		session.close();
		connection.close();
	}
}
