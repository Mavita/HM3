import java.io.*;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Arrays;

public class Reading  {
	
	int cols_1,cols_2;
	String[] ColumnData,ColumnNames;
	int[] Variables_index;
	Hashtable <String,Integer> Heading_index;
	
	public Reading(){
		//System.out.println("Please send a filename.");
	}
	public Reading(String Data, String Names) throws FileNotFoundException {		

		File fData, fNames;
		fData = new File (Data);
		fNames = new File (Names);
		
		if(fData.exists() & fNames.exists()){
		
			Scanner inFileData = new Scanner(fData);
			String line_data;
			String[] tokens_data;
			
			//Storing the columns
			line_data = inFileData.nextLine();
			tokens_data = line_data.split(",");
			cols_1 = tokens_data.length;
			int c;
	
			ColumnData = new String[tokens_data.length];
			for (c=0; c<cols_1; c++){
				ColumnData[c] = tokens_data[c];
			}
			
			Scanner inFileNames = new Scanner(fNames);
			String line_names;
			String[] tokens_names;
			
			//Storing the columns
			line_names = inFileNames.nextLine();
			tokens_names = line_names.split(",");
			cols_2 = tokens_names.length;
			int j;
		
			ColumnNames = new String[tokens_names.length];
			for (j=0; j<cols_2; j++){
				ColumnNames[j] = tokens_names[j];
			}
			
		int i,k;
		Variables_index = new int [cols_2];
		Heading_index = new Hashtable <String,Integer>();
		
		//	String name, data;
			
			for (i=0; i<cols_2; i++){//Get the name that I want
				String name = ColumnNames[i];
				String name1 = name.toLowerCase();
				for (k=0; k<cols_1; k++){
					String data = ColumnData[k];
					String data1 = data.toLowerCase();
					int result = name1.compareTo(data1);
					if(result == 0){
						Variables_index[i]=k;
						Heading_index.put(name,k);
					}
				}
			}	
		}
	}
	public void Printing_Names(){
		//Prints the headings
		int i;
		for (i=0; i<cols_2; i++){
			System.out.println(ColumnNames[i]);
		}
	}
	public void Printing_Index(){
		//Prints the headings
		int i;
		for (i=0; i<cols_2; i++){
			System.out.println(Variables_index[i]);
		}
	}
	public void Reading_Write(String[] filesNameRead, String fileNameWrite, int number_files) throws FileNotFoundException{
		
        try {
        	
        	String line = null;
        	
            // FileReader reads text files in the default encoding.
            FileWriter fileWriter = new FileWriter(fileNameWrite);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            // Write Headings
	    	 String headings_fancy_line = Arrays.toString(ColumnNames);
	    	 headings_fancy_line = headings_fancy_line+",";
	    	 headings_fancy_line = headings_fancy_line.replaceAll("\\[","").replaceAll("\\]","");
	    	 bufferedWriter.write(headings_fancy_line);
	    	 bufferedWriter.newLine();
            
            //Loop through documents
            int j;
            for (j=0; j<number_files; j++){
          
            	FileReader fileReader = new FileReader(filesNameRead[j]);	
            	BufferedReader bufferedReader = new BufferedReader(fileReader);
             
            	int i;
            	
	             // Reads the Headings 
	             line = bufferedReader.readLine();
	             
			     while((line = bufferedReader.readLine()) != null) {
	              
			    	 String[] splitted_line = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			    	 String[] fancy_line;
			    	 fancy_line = new String[cols_2];
			    	 for (i=0; i<cols_2; i++){
			    		 fancy_line[i]=splitted_line[Variables_index[i]];
			    	 }
			    	 String final_fancy_line = Arrays.toString(fancy_line);
			    	 final_fancy_line = final_fancy_line+",";
			    	 final_fancy_line = final_fancy_line.replaceAll("\\[","").replaceAll("\\]","");
			    	 bufferedWriter.write(final_fancy_line);
			    	 bufferedWriter.newLine();
			     }
			     bufferedReader.close();	           
              }
           bufferedWriter.close();   
        }
	
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		filesNameRead[0] + "'");				
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + filesNameRead[0] + "'");					
        }
    }
	
}