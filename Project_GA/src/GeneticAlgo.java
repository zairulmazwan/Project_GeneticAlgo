
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Random;

public class GeneticAlgo {
	
	
	
	static int popSize = 10;
	static int gen = 30;

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
			//System.out.println("Sorted fitness : "+pop.sortedFit);
			pop.getFittest();//getting parents fitness values
			//System.out.println("P1 : ");
			//pop.parent1.printGene();
			//System.out.println("P1 fitness: "+pop.sortedFit.get(0));
			
			
			//System.out.println("\nP2 : ");
			//pop.parent2.printGene();
			//System.out.println("P2 fitness: "+pop.sortedFit.get(1));
			
			
			pop.crossOver(geneSize);
			//System.out.println("\nChild1 : ");
			//pop.child1.printGene();
			//System.out.println("\nChild1 fit : ");
			pop.child1.calFitness(data);
			//System.out.println(pop.child1.fitness);
			//System.out.println("\nChild2 : ");
			//pop.child2.printGene();
			pop.child2.calFitness(data);
			
			//System.out.println("\nChild2 fit : ");
			//pop.child2.calFitness(data);
			//System.out.println(pop.child2.fitness);
			
			//System.out.println("P1 again : ");
			//pop.parent1.printGene();
			
			
			//System.out.println("\nMutation : ");
			pop.mutation(pop.parent1,geneSize);
			//pop.mutate.printGene();
			//System.out.println("\nMutate fit : ");
			pop.mutate.calFitness(data);
			//System.out.println(pop.mutate.fitness);
			
			//System.out.println("\nNew pop : ");
			pop.newPop(data);
			//pop.getPopFitness(data);
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
		
		Individual res=new Individual(genSize);
		for (int i=0; i<ind.gene.length; i++) {
			res.gene[i]=ind.gene[i];
		}
		return res;
		
	}
	
	public double calFitness (ArrayList<Double> data) {
		
		double left =0.0, right = 0.0;
		int dataLen = data.size();
		DecimalFormat df = new DecimalFormat("#.###");
		
		if (gene.length == dataLen) {
			for (int i=0; i<gene.length; i++) {
				if (gene[i]==0) {
					left*=data.get(i);
				}
				else {
					right+=data.get(i);
				}
			}
			fitness = Double.parseDouble(df.format(Math.abs(left-right)));
		}
		else {
			fitness = -1; //the size of the data and gene is not the same
		}
		return fitness;
	}
	
	public void printGene() {
		//System.out.println("Individual gene :");
		for (int i=0; i<gene.length; i++) {
			System.out.print(gene[i]+" ");
		}
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
		
		pop = new Individual[popSize];
		for (int i=0; i<popSize; i++) {
			this.pop[i] = new Individual(n);
		}
	}
	
	public void printPopulation () {
		System.out.println("\nPopulation :");
		for (int i=0; i<pop.length; i++) {
			pop[i].printGene();
			System.out.println();
		}
	}
	
	public void getPopFitness (ArrayList<Double> data) {
		
		int dataLen = data.size(); //data len should be equal to gene length
		int popLen = pop.length;
		popFit = new double [popLen];
		
		for (int i=0; i<popLen; i++) {
			Individual ind = pop[i];
			ind.calFitness(data);
			System.out.print(ind.fitness+" ");
			this.popFit[i]=ind.fitness;
		}
		
	}
	
	public void sortPopFit () {
		
		for (int i=0; i<popFit.length; i++) {
			this.sortedFit.add(popFit[i]);
		}
		
		Collections.sort(sortedFit);
		
	}
	
	//getting fitness for parent1 and parent2
	public void getFittest () {
		double fitness = sortedFit.get(0);
		int index=-1;
		
		for (int i=0; i<popFit.length; i++) {
			if (popFit[i]==fitness) {
				index=i;
				this.parent1=pop[index];
			}
						
		}
		
		fitness = sortedFit.get(1);
		index=-1;
		
		for (int i=0; i<popFit.length; i++) {
			if (popFit[i]==fitness) {
				index=i;
				this.parent2=pop[index];
			}		
		}	
	}
	
public void crossOver(int genSize) {
		
		double rate = 0.6;
		int crossPoint = (int) (rate*genSize);
	
		this.child1 = new Individual(genSize); //initialise 
		this.child2= new Individual(genSize); //initialise 
		
		for (int i=0; i<genSize; i++) {
			if (i<crossPoint) {
				this.child1.gene[i]=parent2.gene[i];
				this.child2.gene[i]=parent1.gene[i];
			}
					
			if(i>crossPoint) {
				this.child1.gene[i]=parent1.gene[i];
				this.child2.gene[i]=parent2.gene[i];
			}		
		}
	}

public void mutation (Individual parent1, int genSize) {
	
	double mutationRate = 0.1;
	int mRate = (int) (mutationRate*genSize);
	this.mutate = new Individual(genSize);
	this.mutate = mutate.copyIndividual(parent1, genSize);
	
	ArrayList<Integer> index = new ArrayList<Integer>(genSize);
	for (int i=0; i<genSize; i++)
		index.add(i);
	
	for (int i=0; i<mRate; i++) {
		Random r = new Random ();
		int inx = r.nextInt(100)%index.size();

		if (mutate.gene[index.get(inx)]==0) {
			mutate.gene[index.get(inx)]=1;
		}
		else {
			mutate.gene[index.get(inx)]=0;
		}
		index.remove(inx);
	}
}
	

	public void newPop (ArrayList<Double> data) {
		
		Hashtable<Double, Individual> newPop = new Hashtable<Double, Individual>();
		ArrayList<Double> fitList = new ArrayList<Double>(); 
		
		for (int i=0; i<pop.length; i++) {
			newPop.put(pop[i].calFitness(data), pop[i]);
			fitList.add(pop[i].calFitness(data));
		}
		
		newPop.put(child1.calFitness(data),child1);
		fitList.add(child1.calFitness(data));
		newPop.put(child2.calFitness(data),child2);
		fitList.add(child2.calFitness(data));
		newPop.put(mutate.calFitness(data),mutate);
		fitList.add(mutate.calFitness(data));
		
		Collections.sort(fitList);
		
		for (int i=0; i<pop.length; i++)
			pop[i]=newPop.get(fitList.get(i));	
	}
}