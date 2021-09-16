
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

import ScalesProblem.Individual;

public class GeneticAlgo {
	
	
	
	static int popSize = ;
	static int gen = ;

	public static void main(String[] args) {
		
		
		
		ArrayList<Double> data = Data.getData();
		int geneSize=data.size();
		System.out.println(data);

		Population pop = new Population();	
		pop.initialPop(popSize, geneSize);
		
		
		for (int i=0; i<gen; i++) {
			System.out.println("\nGen : "+(i+1));
			//pop.printPopulation();
			pop.getPopFitness(data);
			System.out.println();
			pop.sortPopFit();
			
			pop.getFittest();//getting parents fitness values
		
			pop.crossOver(geneSize);
			
			pop.child1.calFitness(data);
			
			pop.child2.calFitness(data);
		
			pop.mutation(pop.parent1,geneSize);
			
			pop.mutate.calFitness(data);
		
			pop.newPop(data);
			
		}
		
		
		System.out.println("\nFinal gene : ");
		pop.pop[0].printGene();
		System.out.println("\nFinal fitness : "+pop.pop[0].calFitness(data));
	}
}


class Individual {
	
	double fitness=0;
	int [] gene=null;
	
	public Individual (int n) {
		
		Random r = new Random ();
		gene = new int [n];
		for (int i=0; i<n; i++) {
			int geneNo = r.nextInt(100)%2;
			this.gene[i]=geneNo;
		}
	}
	
	public Individual copyIndividual(Individual ind, int genSize) {
		
		
		
	}
	
	public double calFitness (ArrayList<Double> data) {
		
		
	}
	
	public void printGene() {
		
	}
}


class Population {
	
	Individual [] pop = null;
	double [] popFit = null;
	ArrayList<Double> sortedFit = new ArrayList<Double>();
	Individual parent1;
	Individual parent2;
	
	Individual child1;
	Individual child2;
	Individual mutate;
	
	public void initialPop (int popSize, int n) {
		
	
	}
	
	public void printPopulation () {
		
		
		
	}
	
	public void getPopFitness (ArrayList<Double> data) {
		
		
	}
	
	public void sortPopFit () {
		
		
	}
	
	//getting fitness for parent1 and parent2
	public void getFittest () {
		
		
	}
	
public void crossOver(int genSize) {
		
		
	}

public void mutation (Individual parent1, int genSize) {
	
	
}
	

	public void newPop (ArrayList<Double> data) {
		
		Hashtable<Double, Individual> newPop = new Hashtable<Double, Individual>(); //use hashtable to store key:value (fitness value:gene)
		ArrayList<Double> fitList = new ArrayList<Double>(); //use arraylist to store the fitnesses so that we can sort the element
		
		//adding all candidates into a new pool of population
		for (int i=0; i<pop.length; i++) {
			newPop.put(pop[i].calFitness(data), pop[i]);
			fitList.add(pop[i].calFitness(data));
		}
		
		//adding the new children and the mutate into a new pool of population
		newPop.put(child1.calFitness(data),child1);
		fitList.add(child1.calFitness(data));
		newPop.put(child2.calFitness(data),child2);
		fitList.add(child2.calFitness(data));
		newPop.put(mutate.calFitness(data),mutate);
		fitList.add(mutate.calFitness(data));
		
		Collections.sort(fitList);//sort the candidates fitness values
		
		//getting the first 10 candidates for the next generation
		for (int i=0; i<pop.length; i++)
			pop[i]=newPop.get(fitList.get(i));
		
		
	}
}