import java.util.*;

class Poly {
	List<Coordinates> points;
	int arrSize, xArray[], yArray[];
	
	Poly(){
		points = new ArrayList<>();
	}
	
	Poly(int refX, int refY, int endX, int endY) {
		points = new ArrayList<>();
		points.add(new Coordinates(refX, refY));
		points.add(new Coordinates(endX, endY));
	}
	
	int[] prepareForPaint(){
		arrSize = (points.size() * 2) + 1;
		
		xArray = new int[arrSize];
		yArray = new int[arrSize];
		
		/*
		 * standard reference point - top left corner
		 * number of entries = number of edges + 1
		 * clockwise movement for polyline
		 */

		//first, last and the second-last points are equally set
		xArray[0] = xArray[arrSize - 1] = xArray[arrSize - 2] = points.get(0).x;
		
		//y-coordinates for most left vertical
		int lastH = yArray[0] = yArray[arrSize - 1] = points.get(0).y;
		yArray[arrSize - 2] = 460;
		
		for(int i = 1; i < (arrSize - 2); i++) {
			xArray[i] = points.get( (i + 1) / 2 ).x;
			
			if(i % 2 == 1) 
				yArray[i] = lastH;
			else {
				yArray[i] = points.get( (i + 1) / 2 ).y;
				lastH = points.get( (i + 1) / 2 ).y;
			}				
		}		
		return xArray;
	}
	
	int[] getYarray(){
		return yArray;
	}
	
	Poly merge(Poly other){
		int h1 = 460, h2 = 460, i = 0, j = 0, lastMaxH = 460;
		Poly mergedPoly = new Poly();
		
		while(i < points.size() && j < other.points.size()){
			if(points.get(i).x < other.points.get(j).x) {
				h1 = points.get(i).y;				
				if (lastMaxH != Math.min(h1, h2)) {					
					mergedPoly.points.add(new Coordinates(points.get(i).x, Math.min(h1, h2)));
					lastMaxH = Math.min(h1, h2);
				}
				i++;
			}
			else {
				h2 = other.points.get(j).y;
				if (lastMaxH != Math.min(h1, h2)) {					
					mergedPoly.points.add(new Coordinates(other.points.get(j).x, Math.min(h1, h2)));
					lastMaxH = Math.min(h1, h2);
				}
				j++;
			}
		}
		
		while(i < points.size()){
			mergedPoly.points.add(points.get(i));
			i++;
		}
		
		while(j < other.points.size()){
			mergedPoly.points.add(other.points.get(j));
			j++;
		}
		
		return mergedPoly;
	}

	@Override
	public String toString() {
		return "Poly [points=" + points + ", arrSize=" + arrSize + ", xArray=" + Arrays.toString(xArray) + ", yArray="
				+ Arrays.toString(yArray) + "]";
	}
}
