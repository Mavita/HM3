import java.io.FileNotFoundException;

public class Main  {
		
	public static void main(String[] args){
		
		try{

			String PathNames,Path, NewPath;
			int i=0;
			int number_files=2;
			NewPath = "/Users/mariavirginiarodriguez/workspace/HM3/New_Document.csv";
			PathNames = "/Users/mariavirginiarodriguez/workspace/HM3/Headings.csv";
			Path = "/Users/mariavirginiarodriguez/workspace/HM3/On_Time_On_Time_Performance_2012_"+(i+1)+".csv";
			String[] Paths;
			Paths = new String[number_files];
			
			Reading flight_read = new Reading(Path, PathNames);
			flight_read.Printing_Names();
			
			
			for (i=0; i<number_files; i++){
				Path = "/Users/mariavirginiarodriguez/workspace/HM3/On_Time_On_Time_Performance_2012_"+(i+1)+".csv";
				Paths[i]=Path;
			}
			
			flight_read.Reading_Write(Paths,NewPath,2);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}	
		
	}

}