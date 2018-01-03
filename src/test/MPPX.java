package test;

public class MPPX {
	public static void main(String[] args) {
		int []arry = {1,2,69,8,7,6,9,2,3,6,7,9};
		for(int i = 1;i<arry.length;i++){
			for(int j=0;j<arry.length-i;j++){
				if(arry[j]>arry[j+1]){
					int flag = arry[j+1];
					arry[j+1] = arry[j];
					arry[j]=flag;
				}
			}
		}
		for(int value : arry){
			System.out.println(value);
		}
		
	}
}
