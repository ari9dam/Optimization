/**PredictCLM.java
 * 2:04:58 PM @author Arindam
 */
package edu.asu.loglinear;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import edu.asu.optimization.core.Vector;
import edu.asu.type.Sample;

/**
 * @author Arindam
 *
 */
public class PredictCLM {
	private ArrayList<Double> theta;
	private Vector v = new Vector();
	
	public PredictCLM(String file) throws IOException, ClassNotFoundException{	    
		ObjectInputStream objectinputstream = null;
		objectinputstream = new ObjectInputStream(new FileInputStream(file));
		theta = (ArrayList<Double>) objectinputstream.readObject();
		System.out.println(theta);
	}
	
	public int predict(Sample sample){
		int ret = -1;
		double best = -1.0;
		for(int i=0;i<sample.getYSize();i++){
			double score = v.dot(theta, sample.getFeatureVector(i));
			if(score> best){
				best = score;
				ret = i;
			}
		}
		return ret;
	}
}
