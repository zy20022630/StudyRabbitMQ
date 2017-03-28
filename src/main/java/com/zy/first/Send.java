package com.zy.first;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 场景1：单发送单接收
 */
public class Send {

	// 队列名称
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws java.io.IOException, TimeoutException {
		/**
		 * 创建连接连接到MabbitMQ
		 */
		ConnectionFactory factory = new ConnectionFactory();

		// 设置MabbitMQ所在主机ip或者主机名
		factory.setHost("192.168.1.157");
		factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setPort(5672);
        
		// 创建一个连接
		Connection connection = factory.newConnection();

		// 创建一个频道
		Channel channel = connection.createChannel();
		
		// 指定一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		// 发送的消息
		String message = "hello RabbitMQ!";
		
		// 往队列中发出一条消息
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		
		System.out.println(" [x] Sent '" + message + "'");
		
		// 关闭频道和连接
		channel.close();
		
		connection.close();
	}

}