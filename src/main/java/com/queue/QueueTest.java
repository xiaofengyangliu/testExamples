package com.queue;

import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {
	public static void main(String[] args) {
		LinkedBlockingQueue<Student> queue = new LinkedBlockingQueue<Student>();
		
		int i = 0;
		while(true){
			i++;
			Student stu = new Student(i);
			try {
				queue.put(stu);
				if((i%4)==0) System.out.println(queue.take().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(bool);
//			if((i%4)==0){
//				System.out.println(queue.poll().toString());
//			}
		}
	}
}
