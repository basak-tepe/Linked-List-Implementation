import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Project1{
	public static void main(String[] args) throws IOException {
		
		/**
		 * @author Basak Tepe, Student ID:2020400117
		 * @since Date: 10.21.2022 submission 
		 */
		
		
		//I used a constructor that forms a structure like an irl factory line.
		//contains only one empty holder.
		
		FactoryImpl factoryLine = new FactoryImpl();
		

		//file reading info
	
		String readFile =args[0];
					
		//file writing info
		
		String writeFile = args[1];
		BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
		
	
		
		String methodName =null;
		String[] commands =null;
		
		
		try {
			Scanner scanner = new Scanner(new File(readFile));
			while(scanner.hasNextLine()) {
				String methodLine = scanner.nextLine();
				commands = methodLine.split(" ");
				methodName = commands[0]; //char sequence of input corresponds to method names.
				
				
				//By using switch, we are checking method names.
				
				switch(methodName) {
				
				case "AF": 
					int id = Integer.parseInt(commands[1]);
					int val = Integer.parseInt(commands[2]);
					Product newProduct = new Product(id, val);
					factoryLine.addFirst(newProduct);
					break;
					
				
				case "AL": 
					Product newAL = new Product(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
					factoryLine.addLast(newAL);
					break;
					
				case "A": 
					try {
						
						int i = Integer.parseInt(commands[1]);
						Product newA = new Product(Integer.parseInt(commands[2]), Integer.parseInt(commands[3]));
						factoryLine.add(i, newA);
					}
					
					catch(IndexOutOfBoundsException e){
					
						writer.write("Index out of bounds.");
						writer.newLine();
						
					}
					break;
					
				case "RF": 
					
					try{
						String removedFirst = factoryLine.removeFirst().toString();
						
						writer.write(removedFirst);
						writer.newLine();
					}
					catch(NoSuchElementException e){
						
						writer.write("Factory is empty.");
						writer.newLine();
						
					}
					break;
					
				case "RL": 
					
					try {
						String removedLast = factoryLine.removeLast().toString();
						
						writer.write(removedLast);
						writer.newLine();
					}
					
					catch(NoSuchElementException e){
						
						writer.write("Factory is empty.");	
						writer.newLine();
					}
					
					break;
					
				case "RI": 
					
					try {
						int index = Integer.parseInt(commands[1]);
						String removedByIndex = factoryLine.removeIndex(index).toString();
						
						writer.write(removedByIndex);
						writer.newLine();
						
					}
					catch(IndexOutOfBoundsException e) {
						
						writer.write("Index out of bounds.");
						writer.newLine();
					}
					break;
					
				case "RP": 
					try {
						int valRP = Integer.parseInt(commands[1]);
						String removedByVal = factoryLine.removeProduct(valRP).toString();
						
						writer.write(removedByVal);
						writer.newLine();
						
					}
					catch(NoSuchElementException e) {
						
						writer.write("Product not found.");
						writer.newLine();
					}
					break;
					
					
				case "F": 
					try {
						int productID = Integer.parseInt(commands[1]);
						String found = factoryLine.find(productID).toString();
						
						writer.write(found);
						writer.newLine();
						
					}
					catch(NoSuchElementException e){
						
						writer.write("Product not found.");
						writer.newLine();
						
					}
					
					break;
					
				case "G": 
					try {
						int indexGet = Integer.parseInt(commands[1]);
						String gotten = factoryLine.get(indexGet).toString();
						
						writer.write(gotten);
						writer.newLine();
					}
					catch(IndexOutOfBoundsException e){
						
						writer.write("Index out of bounds.");
						writer.newLine();
					}
					break;
					
				case "U":
					try {
						int updateID = Integer.parseInt(commands[1]);
						int updateVal = Integer.parseInt(commands[2]);
						String toBeUpdated = factoryLine.update(updateID, updateVal).toString();
						
						writer.write(toBeUpdated);
						writer.newLine();
					}	
					catch(NoSuchElementException e){
						
						writer.write("Product not found.");
						writer.newLine();
					}
					break;
					
				case "FD":
					
					String removed = Integer.toString(factoryLine.filterDuplicates());
					
					writer.write(removed);		
					writer.newLine();
					break;
					
				case "R":
					
					factoryLine.reverse();
					String reverseFactory = factoryLine.toString();
					
					writer.write(reverseFactory);
					writer.newLine();
					break;
					
				case "P": 
					

					String factory = factoryLine.toString();
					
					writer.write(factory);
					writer.newLine();
					break;
				
				}
			
			}
			scanner.close();
			writer.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File cannot be found.");
		}
		
		
	}
	


}