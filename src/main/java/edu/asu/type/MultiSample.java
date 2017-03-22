/**MultiSample.java
 * 11:37:44 AM @author Arindam
 */
package edu.asu.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arindam
 *
 */
public class MultiSample implements IMultiSample {

	private String id;
	private List<Integer> correctYs;
	private Integer ySize;
	private ArrayList<ArrayList<Double>> featureVectors;
	
	
	public MultiSample(String id, List<Integer> correctYs, ArrayList<ArrayList<Double>> featureVectors) {
		this.id = id;
		this.correctYs = correctYs;
		this.ySize = featureVectors.size();
		this.featureVectors = featureVectors;
	}


	public List<Integer> getCorrectYs() {
		return this.correctYs;
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
	public String getId() {
		return id;
	}


	/**
	 * @return
	 */
	public ArrayList<ArrayList<Double>> getFeatureVectors() {
		
		return this.featureVectors;
	}

}
