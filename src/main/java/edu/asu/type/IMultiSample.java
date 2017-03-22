/**IMultiSample.java
 * 11:37:02 AM @author Arindam
 */
package edu.asu.type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arindam
 *
 */
public interface IMultiSample extends Serializable{
	public List<Integer> getCorrectYs();
	public Integer getYSize();
	public ArrayList<Double> getFeatureVector(int i);
}
