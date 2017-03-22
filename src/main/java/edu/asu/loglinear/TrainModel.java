/**TrainModel.java
 * 3:55:55 PM @author Arindam
 */
package edu.asu.loglinear;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import edu.asu.optimization.core.Vector;
import edu.asu.type.MultiSample;

/**
 * @author Arindam
 *
 */
public class TrainModel {
	private boolean smoothing; // if smoothing is needed
	private double step; // step size for gradient descent
	private Double lambda; // regularization
	private Vector v;

	public TrainModel(boolean smoothing, double step, Double lambda) {
		this.smoothing = smoothing;
		this.step = step;
		this.lambda = lambda;
		v = new Vector();
	}

	public void train(String file, String outputFile) throws FileNotFoundException, IOException{
		double threshold = 0.0000001;
		int epoch = 0; 
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(outputFile));

		ObjectInputStream objectinputstream = null;

		try {
			objectinputstream = new ObjectInputStream(new FileInputStream(file));
			@SuppressWarnings("unchecked")
			ArrayList<MultiSample> data = (ArrayList<MultiSample>) objectinputstream.readObject();
			ArrayList<Double> theta = new ArrayList<Double>();
			ArrayList<Double> dtheta = new ArrayList<Double>();
			
			//theta = theta - step_size*dtheta
			int iteration=0;
			double norm = 0.0;
			
			//initialize theta and dtheta to 0
			for(int i=0;i<data.get(0).getFeatureVector(0).size();i++){
				theta.add(0.0);
				dtheta.add(0.0);
			}
			
			do {
				ArrayList<MultiSample> batch = new ArrayList<MultiSample>();
				for(int i=0;i<data.size();i++){
					batch.add(data.get(i));
					if(batch.size()==60|| i==data.size()-1){
						v.add(dtheta, this.batchNegativeGradient(batch, theta));
						batch.clear();
						if(this.smoothing)
							v.mulAdd(dtheta, -1.0*this.lambda, theta);
						norm = v.mulAdd(theta, this.step, dtheta);
						if(norm<=threshold)
							break;
						iteration++;
						System.out.println("Iteration "+ iteration+" : "+ theta);
					}
				}
				Collections.shuffle(data);
				epoch++;
			}while(norm>threshold&&epoch<1200);
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
	private ArrayList<Double> batchNegativeGradient(List<MultiSample> samples, ArrayList<Double> theta){
		ArrayList<Double> ret = negativeGradient(samples.get(0),theta, samples.size());
		for(int i=1;i<samples.size();i++){
			v.add(ret, negativeGradient(samples.get(i),theta, samples.size()));
		}
		
		System.out.println(ret);
		
		return ret;
	}

	private ArrayList<Double> negativeGradient(MultiSample sample, ArrayList<Double> theta, int avg){
		ArrayList<Double> grad = new ArrayList<Double>();
		double z = 0, ec = 0, a=0;
		HashMap<Integer,Double> weightMap = new HashMap<Integer,Double>();

		for(int y = 0; y< sample.getYSize();y++){
			double temp = Math.exp(v.dot(theta, sample.getFeatureVector(y)));;
			z += temp;
			if(sample.getCorrectYs().contains(y)){
				a+=temp;
				
			}
			weightMap.put(y, temp);
		}

		if(a==z){
			System.out.println("a equals z");
			System.out.println(sample.getCorrectYs());
			System.out.println(sample.getYSize());
		}
		
		for(int i=0;i<theta.size();i++){
			ec = 0;
			for(Integer y : sample.getCorrectYs()){
				ec += weightMap.get(y)*sample.getFeatureVector(y).get(i)/a; //empirical count
			}
			
			
			for(int y = 0; y< sample.getYSize();y++){
				//computing empirical count - expected count
				ec -= (sample.getFeatureVector(y).get(i)*
						weightMap.get(y))/z;
			}
			
			if(Double.isNaN(ec)){
				System.out.println("Got NaN!!");
				System.out.println(a);
				System.out.println(z);
				System.out.println(sample.getFeatureVectors());
				System.out.println(theta);
				System.out.println(weightMap);
				System.out.println("a equals z");
				System.out.println(sample.getCorrectYs());
				System.out.println(sample.getYSize());
				System.exit(0);
			}
			grad.add(ec/avg);
		}
		return grad;
	}
}
