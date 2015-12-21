/**Sample.java
 * 6:35:49 PM @author Arindam
 */
package edu.asu.type;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Arindam
 *
 */
public interface ISample extends Serializable{
	public Integer getCorrectY();
	public Integer getYSize();
	public ArrayList<Double> getFeatureVector(int i);
}
