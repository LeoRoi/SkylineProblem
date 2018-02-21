import java.util.Comparator;

class coordComparator implements Comparator<Coordinates>  {
	@Override
	public int compare(Coordinates o1, Coordinates o2) {
		return o1.x - o2.x;
	}
}