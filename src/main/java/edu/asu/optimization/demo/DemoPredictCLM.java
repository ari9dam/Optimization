/**DemoPredictCLM.java
 * 3:13:48 PM @author Arindam
 */
package edu.asu.optimization.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import edu.asu.loglinear.PredictCLM;
import edu.asu.loglinear.TrainConditionalLogLinearModel;
import edu.asu.type.Sample;

/**
 * @author Arindam
 *
 */
public class DemoPredictCLM {
	public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException{
		/*ArrayList<Double> theta = new ArrayList<Double>();
		theta.add(5.197603788006235);
		theta.add(1.0272234827348952);
		theta.add(5.260487180163395);
		theta.add(-1.178791377951659);
		
		ArrayList<Double> y1 = new ArrayList<Double>();
		ArrayList<Double> y2 = new ArrayList<Double>();
		ArrayList<Double> y3 = new ArrayList<Double>();
		ArrayList<Double> y4 = new ArrayList<Double>();
		ArrayList<Double> y5 = new ArrayList<Double>();
		ArrayList<Double> y6 = new ArrayList<Double>();
		
		ArrayList<Sample> data = new ArrayList<Sample>();
		ArrayList<ArrayList<Double>> fv = new ArrayList<ArrayList<Double>>();
	
		y1.add(1.0);y1.add(1.0);y1.add(0.0);y1.add(0.0);
		y2.add(1.0);y2.add(0.0);y2.add(0.0);y2.add(1.0);
		y3.add(0.0);y3.add(1.0);y3.add(1.0);y3.add(0.0);
		y4.add(1.0);y4.add(0.0);y4.add(1.0);y4.add(1.0);
		
		fv.add(y1);fv.add(y2);fv.add(y3);fv.add(y4);
		Sample sample = new Sample(null,null,fv);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("demo_clm_model.ser"));
		oos.writeObject(theta);
		oos.close();
		
		PredictCLM predicter = new PredictCLM(new File("demo_clm_model.ser").getAbsolutePath());
		int y = predicter.predict(sample);
		System.out.println(y);
		Sample s1 = new Sample("1", y, sample.getFeatureVectors() );
		
		fv = new ArrayList<ArrayList<Double>>();
		
		y1 = new ArrayList<Double>();
		y2 = new ArrayList<Double>();
		y3 = new ArrayList<Double>();
		y4 = new ArrayList<Double>();
		
		y1.add(1.0);y1.add(1.0);y1.add(0.0);y1.add(0.0);
		y2.add(1.0);y2.add(0.0);y2.add(0.0);y2.add(1.0);
		y3.add(0.0);y3.add(1.0);y3.add(1.0);y3.add(0.0);
		y4.add(1.0);y4.add(0.0);y4.add(1.0);y4.add(1.0);
		y5.add(1.0);y5.add(1.0);y5.add(1.0);y5.add(0.0);
		y6.add(0.0);y6.add(1.0);y6.add(1.0);y6.add(1.0);
		
		fv.add(y1);fv.add(y2);fv.add(y3);fv.add(y4);fv.add(y5);fv.add(y6);
		sample = new Sample(null,null,fv);
		y = predicter.predict(sample);
		System.out.println(y);
		Sample s2 = new Sample("2", y, sample.getFeatureVectors() );
		
		data.add(s1);
		data.add(s2);
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("demo_clm.ser"));
		o.writeObject(data);
		o.close();*/
		
		TrainConditionalLogLinearModel tlm1 = new TrainConditionalLogLinearModel(false,1.0,null);
		TrainConditionalLogLinearModel tlm2 = new TrainConditionalLogLinearModel(true,1.0,0.01);
		tlm1.train("C:\\Users\\Arindam\\Dropbox\\Math Challenge\\sample_questions_training_data.ser",
				"C:\\Users\\Arindam\\Dropbox\\Math Challenge\\sample_questions_model_unsmoothed.ser");
		
	}
}
