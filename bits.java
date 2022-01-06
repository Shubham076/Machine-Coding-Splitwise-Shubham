import java.util.*;
class bits {
	static String max;
	static void swap(char[] arr, int i, int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	static void maxAfterkSwaps(char[] arr, int k, int i){
		if(k == 0){
			String cur = new String(arr);
			if(cur.compareTo(max) > 0){
				max = cur;
			}
			return;
		}
		char lmax = arr[i];
		for(int j = i + 1; j < arr.length; j++){
			if(arr[j] > lmax){
				lmax = arr[j];
			}
		}
		for(int j = i; j < arr.length; j++){
			if(arr[j] == lmax){
				swap(arr, i, j);
				maxAfterkSwaps(arr, k - 1, i + 1);
				swap(arr, i, j);
			}
		}
	}
	public static void main(String[] args) {
		String s = "3435335";
		int k = 3;
		max = s;
		maxAfterkSwaps(s.toCharArray(), k, 0);
		System.out.println(max);
	}
}