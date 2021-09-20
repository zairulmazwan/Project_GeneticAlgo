
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import ScalesProblem.Population;


public class Data {

	public static void main(String[] args) {
		
		ArrayList<Double> data = createData(10);
		System.out.println(data);
		
		//writeData(data);
		
	}
	
	public static ArrayList<Double> createData (int n) {
		
		Random r = new Random();
		int min = 1; //change this value for a specific range
		int max = 25; //change this value for a specific range
		DecimalFormat df = new DecimalFormat("#.###");
		ArrayList<Double> data = new ArrayList<Double>(n);
		
		for (int i=0; i<n; i++) {
			double rd = (max-min)*r.nextDouble()+min;
			rd = Double.parseDouble(df.format(rd));
			//System.out.println(rd);
			data.add(rd);
		}
		
		return data;
		
	}
	
	public static void writeData (ArrayList<Double> data) {
		
		String fileName = "C:\\Users\\zairu\\Sheffield Hallam University\\Sept 21-22\\ADS\\Assignment Materials\\weights2.csv"; 
		
		
		try {
			
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			Iterator<Double> i=data.iterator();
			
			while (i.hasNext()) {
				bw.write(i.next().toString());
				bw.write("\n");
			}
			
			bw.close();
			fw.close();
			
		}
		catch(Exception e) {
			System.err.println("Error writing data to a file");
		}
	}
	
	public static ArrayList<Double> getData (){
		ArrayList<Double> data = new ArrayList<Double>();
		String fileName = "C:\\Users\\zairu\\Sheffield Hallam University\\Sept 21-22\\ADS\\Assignment Materials\\weights.csv"; 
		
		try {
			FileReader fr = new FileReader (fileName);
			BufferedReader br = new BufferedReader(fr);
			
			String line = null;
			
			while ((line=br.readLine())!=null) {
			
				String [] temp = line.split(",");
				for (int j=0; j<temp.length; j++) {
					data.add(Double.parseDouble(temp[j]));
				}
				
			}
		}
		catch (Exception e) {
			System.err.println("Error reading file");
		}
		
		return data;
	}
	
	
public static void writeRes (ArrayList<ArrayList<Double>> data, int gen) {
		
		String fileName = "C:\\Users\\zairu\\Sheffield Hallam University\\Sept 21-22\\ADS\\Assignment Materials\\Gen_"+gen+".csv"; 
		
		
		try {
			
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i=0; i<data.size(); i++) {
				for (int j=0; j<data.get(i).size(); j++) {
					bw.write(data.get(i).get(j).toString());
					bw.write(",");
				}
				bw.write("\n");
			}
			
			bw.close();
			fw.close();
			
		}
		catch(Exception e) {
			System.err.println("Error writing data to a file");
		}
	}


public static void writeChromosomes (Population chromosomes) {
	
		String filename = "C:\\Users\\zairu\\Sheffield Hallam University\\Sept 21-22\\ADS\\Assignment Materials\\ChromosomesRes.txt";
		
		try {
			FileWriter  fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i=0; i<chromosomes.pop.length; i++) {
				//bw.write(chromosomes[i].gene.toString());
				for (int j=0; j<chromosomes.pop[i].gene.length; j++) {
					int gene = chromosomes.pop[i].gene[j];
					bw.write(Integer.toString(gene));
					bw.write(" ");
					System.out.print(gene);
				}
				System.out.println();
				bw.write("\n");
			}
			
			bw.close();
			fw.close();
		}
		catch(Exception e) {
			System.err.println("Error writing data to a file");
			}
	}
}
