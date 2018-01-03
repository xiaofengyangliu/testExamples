package test;

public class Test {
	public static void main(String[] args) {

		System.out.println("cha /////"+(sum(7.0, 22)-sum(6.0, 22)));
	}
	public static double sum(double value,int dayNum){
		double sum=0;
		for(int i=0;i<dayNum*2;i++){
			if(sum<=100){
				sum=sum+value;
//				System.out.println("次数"+i);
			}else if(sum<=150){
						sum=sum+(value*0.8);
//						System.out.println("0.8次数"+i);
					}else{
						sum=sum+(value*0.5);
//						System.out.println("0.5次数"+i);
					}
		}
		return sum;
	}
}
