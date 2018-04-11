package com.atguigu.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
/**
 * 队列发布
 * @author Administrator
 *
 */
public class Producer {

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
		Queue queue = session.createQueue("officequene");
		//发送端
		MessageProducer producer = session.createProducer(queue);
		//消息内容
		TextMessage textMessage = session.createTextMessage("你是二货吗");
		//发送
		producer.send(textMessage);
		//关闭
		producer.close();
		session.close();
		connection.close();
	}
}
