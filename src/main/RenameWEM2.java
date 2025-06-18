package main;
import java.util.HashMap;
import com.opencsv.CSVReader;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.ArrayList;
import java.io.File;

import javax.swing.JOptionPane;

// ---------------------------------------------------------------------------------------
// This 
public class RenameWEM2 {

	public static void main(String[] args) {
		
		System.out.println("Reading CSV using OpenCSV");
		
		long begin = System.currentTimeMillis();

        // Path to our CSV file with complex data
        String csvFile = "WemList.csv";
        
        int compteur = 0;
        
        // Sélection du dossier à traiter
        JFileChooser fileChooser = new JFileChooser("H:\\BG3 Sound Effects\\Multitools\\UnpackedData\\SharedSounds\\Public\\Shared\\Assets");
        fileChooser.setDialogTitle("Sélectionnez le DOSSIER contenant les fichiers .WEM");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("WEM","wem");
        fileChooser.addChoosableFileFilter(filter);
        int userSelection = fileChooser.showOpenDialog(null);
        
        
        if (userSelection == 0) {
        	String dossier = fileChooser.getSelectedFile().getAbsolutePath();
 //       	System.out.println("Dossier = " + dossier);
        	
        	
    		// MAP Creation from CSV File
	        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
	            

	        	
	            // Read each line
	            String[] nextLine;
	            
	    		
	            // --------------------------------------------
	            // MAP Creation from CSV File
	            // --------------------------------------------
	            HashMap<String, String> wemList = new HashMap<String, String>();
	            
	            int j;
	            //System.out.println("\nData read from CSV file:");
	            while ((nextLine = reader.readNext()) != null) {
	               
	                // Création d'un tableau les numeros de wem de la ligne
	                String [] wems = nextLine[2].split(",");
	                j = 1;
	                for (String wem : wems) {
	                	String wemId = wem.trim();
	                	// Add a number at the end of the file name
	                	String newName = nextLine[1] + "-" + j;
	                	wemList.put(wemId, newName);
						j++;
	                }
              
	//                int i = 1;
	//    			for (String[] entry : wemList) {
	//    				System.out.println(i + " - WEM ID: " + entry[0] + " / New Name: " + entry[1]);
	//    				i++;
	//    			}
	            }
	            // -------------------------------------------

	            
	            System.out.println();
                System.out.println("\n--- Final WEM List ---");
                System.out.println("--- List contains " + wemList.size() + " file names");
	            
	            // -------------------------------------------------
	            // Lecture du dossier contenant les WEM
	            File dir  = new File(dossier);
	            File[] liste = dir.listFiles();
	            
	            System.out.println("\n--- WEM files ---");
                System.out.println("--- " + liste.length + " files in directory");
                
	        	// Progress Bar Creation
                ProgressBar bar = new ProgressBar();
	            
                
	            for(File file : liste) {
	            	if (file.isFile())
	            	{ 
	            		String fullName = file.getName();
	            		String[] name = fullName.split("\\.");
	            	
	//             		System.out.format("Nom du fichier : %s\n", fullName); 
	            		if (name.length > 0) {
	//                		System.out.format("Nom sans extension : %s\n", name[0]); 
	            		  if (wemList.containsKey(name[0])) {
	            			  
	//            				  System.out.println("Trouvé -> " + wemName[0] + " = " + name[0] + " wemName = " + wemName[1]);
	            				  String newName = wemList.get(name[0]) + ".wem";
	            				  
	            				  // Rename file
	            				  File newFile = new File(dossier, newName);
	            				  if (file.renameTo(newFile)) {
	                				//System.out.println("Renommé : " + file.getName() + " -> " + newName);
	                				compteur ++;
	                				bar.fill(compteur);
	            				  }
	            			  }
	            		  }
	                
	            	} 
	            	else if(file.isDirectory())
	            	{	
	            		System.out.format("Nom du répertoir: %s%n", file.getName()); 
	            	} 		
	            }
	            bar.close();
	        
	            
	           
	            
	            long end = System.currentTimeMillis();
		        long time = (end - begin) / 1000;
		        System.out.println("Temps de traitement = " + (time / 60) + " min et " + (time%60) + " sec");
		        
		        String message = compteur + " fichiers ont été renommés";
		        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
		        System.exit(0);
	           	        
	
	        } catch (IOException | CsvValidationException e) {
	            System.err.println("Error reading the CSV file: " + e.getMessage());
	            e.printStackTrace();
	            String message = "Fichiers CSV manquant";
		        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
		        System.exit(1);
	            
	        }
	        
	        
	        
		
        }
	}

}
