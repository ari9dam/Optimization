/**Sample.java
 * 6:35:49 PM @author Arindam
 */
package edu.asu.type;

import java.util.ArrayList;

/**
 * @author Arindam
 *
 */
public interface ISample {
	public Integer getCorrectY();
	public Integer getYSize();
	public ArrayList<Double> getFeatureVector(int i);
}
