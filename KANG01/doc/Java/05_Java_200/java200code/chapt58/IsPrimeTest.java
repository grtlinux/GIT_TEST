public class IsPrimeTest {

	public static void main(String[] args) {
		
		int toInt=10000873;
		boolean isP=true;
		for(int j=2;j<toInt;j++){
			if(toInt%j==0){
				isP=false;
				break;
			}else{
				isP=true;
			}
		}
		if(isP){
			System.out.println(toInt+"�� �Ҽ�");
		}
		else{
			System.out.println(toInt+"�� �Ҽ��� �ƴ�");
		}
		
		printPrime(toInt);
	}
	public static boolean isPrime(int num){
		boolean isP=true;
		for(int j=2;j<num;j++){
			if(num%j==0){
				isP=false;
				break;
			}else{
				isP=true;
			}
		}
		return isP;
	}
	public static void printPrime(int num){
		if(isPrime(num)){
			System.out.println(num+"�� �Ҽ�");
		}
		else{
			System.out.println(num+"�� �Ҽ��� �ƴ�");
		}
	}
}