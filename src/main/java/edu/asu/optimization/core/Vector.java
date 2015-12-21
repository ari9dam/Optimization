/**Vector.java
 * 2:17:38 PM @author Arindam
 */
package edu.asu.optimization.core;

import java.util.ArrayList;

/**
 * @author Arindam
 *
 */
public class Vector {
	public Double dot(ArrayList<Double> x, ArrayList<Double> y){
		Double ret = 0.0;
		for(int i=0;i< x.size();i++){
			ret+= x.get(i)*y.get(i);
		}
		return ret;
	}
	
	public Double add(ArrayList<Double> x, ArrayList<Double> y){
		double change = 0;
		for(int i=0;i<x.size();i++){
			change+=Math.abs(y.get(i));
			x.set(i, x.get(i)+y.get(i));
		}
		
		return change;
	}
	
	public Double mulAdd(ArrayList<Double> x, double z, ArrayList<Double> y){
		double change = 0;
		for(int i=0;i<x.size();i++){
			change+=Math.abs(z*y.get(i));
			x.set(i, x.get(i)+z*y.get(i));
		}
		
		return change;
	}
}
