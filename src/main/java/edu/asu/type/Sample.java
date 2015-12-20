/**Sample.java
 * 7:56:35 PM @author Arindam
 */
package edu.asu.type;

import java.util.ArrayList;

/**
 * @author Arindam
 *
 */
public class Sample implements ISample{
	private Integer id;
	private Integer correctY;
	private Integer ySize;
	ArrayList<ArrayList<Double>> featureVectors;
	
	
	public Sample(int id, int correctY, int ySize, ArrayList<ArrayList<Double>> featureVectors) {
		this.id = id;
		this.correctY = correctY;
		this.ySize = ySize;
		this.featureVectors = featureVectors;
	}


	public Integer getCorrectY() {
		return this.correctY;
	}


	public Integer getYSize() {
		return this.ySize;
	}


	public ArrayList<Double> getFeatureVector(int i) {
		return this.featureVectors.get(i);
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
