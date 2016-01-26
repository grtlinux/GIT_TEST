package tain.kr.com.test.generic.v01;

class Generic<T> {
	T radius;
	T area;
	public double get(T radius) {
		double r = 10;
		double val = 2 * 3.14 * r;
		return val;
	}
}

class Box<T> {
	// T stands for "Type"
	private T t;
	
	public void set(T t) { this.t = t; }
	public T get() { return t; }
}

interface Pair<K, V> {
	public K getKey();
	public V getValue();
}

class Util {
	
	public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
	}
}

class OrderedPair<K, V> implements Pair<K, V> {
	private K key;
	private V value;
	
	public OrderedPair() {
		this.key = null;
		this.value = null;
	}
	
	public OrderedPair(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public void setKey(K key) { this.key = key; }
	public void setValue(V value) { this.value = value; }
	public K getKey() { return this.key; }
	public V getValue() { return this.value; }
	
	public static <K,V> boolean compare(Pair<K,V> p1, Pair<K,V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
	}
}

public class GenericTestMain {

	private static boolean flag = true;
	
	@SuppressWarnings("unused")
	private static void test01(String[] args) throws Exception
	{
		if (flag) {
			Box<Integer> box = new Box<Integer>();
			
			//Pair<String, String>  pair0 = new OrderedPair<String, String>();
			
			Pair<String, Integer> pair1 = new OrderedPair<String, Integer>(null, 0);
			
			Pair<String, Integer> pair2 = new OrderedPair<String, Integer>("hello", 23);

			//Pair<String, Integer> pair3 = new OrderedPair<>("hello", 23);  // java 1.6 -> 1.7 or higher
			
			Pair<Integer,String> p1 = new OrderedPair<Integer,String>(1, "apple");
			Pair<Integer,String> p2 = new OrderedPair<Integer,String>(3, "pear");
			boolean same = Util.<Integer,String>compare(p1, p2);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if (flag) {
			test01(args);
		}
	}
}
