package pcalculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

import com.opencsv.CSVReader;

public class DataPrep {
	DataPrep(){		
	}
	
	void printDoubleArr(double[][] arr,String type){
		int size = arr.length;
		System.out.println("\n" + size + "x" + size + " "+type+" Matrix : ");
		for (double[] eachDouble : arr) {
			for (int i = 0; i < eachDouble.length; i++) {
				System.out.print(eachDouble[i] + " ");
				if (i == eachDouble.length - 1)
					System.out.println();
			}
		}
	}

	double[][] load(String filename) {
		CSVReader reader;
		try {
			reader = new CSVReader(new FileReader(filename));
			String[] row;
			String[] patient = reader.readNext();
			int numPatient = patient.length-1;
			int numGene=0;
			
			ArrayList<double[]> geneList = new ArrayList<double[]>();
			
			System.out.println("	Reading "+numPatient+" patient ID");
			/*
			for (int i = 1; i < patient.length; i++) {											
				System.out.print(patient[i]+" ");				
			}
			*/
			System.out.println("	Finished reading patient ID");
			System.out.println("	Reading gene data");
			while ((row = reader.readNext()) != null) {
				double[] geneData = new double[numPatient];
				//System.out.print(row[0]+" : ");
				for (int i = 1; i < row.length; i++) {										
					geneData[i-1]= Double.parseDouble(row[i]);
					//System.out.print(geneData[i-1]+" ");
				}
				//System.out.println();
				numGene++;
				geneList.add(geneData);				
			}
			System.out.println("	Finished reading "+numGene+" gene data");
			
			double[][] finalGene = new double[numGene][numPatient];			
			for(double[] eachGene : geneList){
				for(int j=0; j<numPatient; j++){					
					//finalGene[i][j]=eachGene[j];
					//System.out.print(eachGene[j]+" ");
				}
				//System.out.println();
			}


			for (int i = 0; i < geneList.size(); i++) {
				double[] eachGene = geneList.get(i);				
				for (int j = 0; j < numPatient; j++) {				
					finalGene[i][j] = eachGene[j];				
				}				
			}
						
			double[][] finalGeneSwitched = switchColRow(finalGene);
			reader.close();
			return finalGeneSwitched;
									
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	double[][] randomLoad(String filename,int numGene) throws FileNotFoundException, IOException{		
        Sampler mySampler = new Sampler();
        List<String> list = mySampler.sampler(numGene,filename);
        int numPatient = (list.get(0).split(",").length)-1;
        //System.out.println("numP : "+numPatient);
        double[][] geneMatrix = new double[numGene][numPatient];
        for(int index = 0; index<list.size(); index++){
            //System.out.print(list.get(index));
            String[] geneData = list.get(index).split(",");
            for(int j=1; j<geneData.length; j++){
            	geneMatrix[index][j-1]=Double.parseDouble(geneData[j]);
            }
        }
        //printDoubleArr(switchColRow(geneMatrix),"Random");
        return switchColRow(geneMatrix);		                        
	}
	
	double[][] switchColRow(double[][] matrix){

		int numRow = matrix.length;
		int numCol = matrix[0].length;
		double[][] finalGeneSwitched = new double[numCol][numRow];
		for(int i=0; i<numCol; i++){
			for(int j=0; j<numRow; j++){
				finalGeneSwitched[i][j]=matrix[j][i];
			}
		}
		return finalGeneSwitched;
	}
	
}