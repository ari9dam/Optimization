/**TrainConditionalLogLinearModel.java
 * 8:08:13 PM @author Arindam
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
public class TrainConditionalLogLinearModel {
	private boolean smoothing;
	private double step;
	private Double lambda;
	private Vector v;
	public TrainConditionalLogLinearModel(boolean smoothing, double step, Double lambda) {
		this.smoothing = smoothing;
		this.step = step;
		this.lambda = lambda;
		v = new Vector();
	}

	public void train(String file, String outputFile) throws FileNotFoundException, IOException{
		
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(outputFile));
	    
		ObjectInputStream objectinputstream = null;
		try {
			objectinputstream = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<Sample> data = (ArrayList<Sample>) objectinputstream.readObject();
			ArrayList<Double> theta = new ArrayList<Double>();
			ArrayList<Double> dtheta = new ArrayList<Double>();
			int iteration=0;
			for(int i=0;i<data.get(0).getFeatureVector(0).size();i++){
				theta.add(0.0);
				dtheta.add(0.0);
			}
			double change = 0.0;
			do {
				boolean isFirst = true;
				for(Sample sample: data){
					if(isFirst){
						dtheta = this.gradient(sample, theta);
						isFirst = false;
					}else
						v.add(dtheta, this.gradient(sample, theta));
				}
				if(this.smoothing)
				v.mulAdd(dtheta, -1.0*this.lambda, theta);
				change = v.mulAdd(theta, this.step, dtheta);
				System.out.println("Iteration "+ ++iteration+" : "+ change);
			}while(change>0.000001);
			oos.writeObject(theta);
			System.out.println(theta);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(objectinputstream != null){
				objectinputstream .close();
			} 
			oos.close();
		}
	}

	private ArrayList<Double> gradient(Sample sample, ArrayList<Double> theta){
		ArrayList<Double> grad = new ArrayList<Double>();
		double z = 0, ec = 0;
		
		for(int y = 0; y< sample.getYSize();y++){
			z += Math.exp(v.dot(theta, sample.getFeatureVector(y)));
		}
		
		for(int i=0;i<theta.size();i++){
			ec = sample.getFeatureVector(sample.getCorrectY()).get(i); //empirical count
			for(int y = 0; y< sample.getYSize();y++){
				// computing empirical count - expected count
				ec -= sample.getFeatureVector(y).get(i)*
						Math.exp(v.dot(theta, sample.getFeatureVector(y)))/z;
			}
			
			grad.add(ec);
		}
		return grad;
	}


}
