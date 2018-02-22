import java.util.*;

/**
 * shape structure and comparison mechanism
 */
class Poly {
	List<Coordinates> points;
	int arrSize, xArray[], yArray[];
	
	Poly(){
		points = new ArrayList<>();
	}

	//list with reference and end points
	Poly(int refX, int refY, int endX, int endY) {
		points = new ArrayList<>();
		points.add(new Coordinates(refX, refY));
		points.add(new Coordinates(endX, endY));
	}
	
	int[] prepareForPaint(){

		//polygon must have edges + 1 points, since start = end
		arrSize = (points.size() * 2) + 1;
		xArray = new int[arrSize];
		yArray = new int[arrSize];
		
		/*
		 * standard reference point - top left corner
		 * number of entries = number of edges + 1
		 * CLOCKWISE movement for polyline
		 */

		//first, last and the second-last points are equal
		xArray[0] = xArray[arrSize - 1] = xArray[arrSize - 2] = points.get(0).x;
		
		//y for the ref point (start = end) and init hight value
		int lastH = yArray[0] = yArray[arrSize - 1] = points.get(0).y;
		//second-last y is always zero
		yArray[arrSize - 2] = 460;

		//fill remained poly-points
		for(int i = 1; i < (arrSize - 2); i++) {

			//vertical move with two equal x
			xArray[i] = points.get( (i + 1) / 2 ).x;

			//horizontal move only on every second x
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

	//two shapes as input, only one as output
	Poly merge(Poly other){

		//we begin with hight = 0
		int h1 = 460, h2 = 460, i = 0, j = 0, lastMaxH = 460;
		Poly mergedPoly = new Poly();
		
		while(i < points.size() && j < other.points.size()){

			//poly1 comes first
			if(points.get(i).x < other.points.get(j).x) {
				h1 = points.get(i).y;

				//it is upside down, so use min to determine logical max
				if (lastMaxH != Math.min(h1, h2)) {					
					mergedPoly.points.add(new Coordinates(points.get(i).x, Math.min(h1, h2)));
					lastMaxH = Math.min(h1, h2);
				}
				i++;
			}
			//poly2 comes first
			else {
				h2 = other.points.get(j).y;
				if (lastMaxH != Math.min(h1, h2)) {					
					mergedPoly.points.add(new Coordinates(other.points.get(j).x, Math.min(h1, h2)));
					lastMaxH = Math.min(h1, h2);
				}
				j++;
			}
		}

		//add remained points (no conflicts)
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
