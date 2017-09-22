import java.io.*;
import java.util.*;


public class readFile{
   public String openFileAndReadFromFile(){
	    	// Return string list for return values
	   		String returnString = null;
	   		// And the counters 
	   		Integer foodCounter=0;
	   		Integer computerCounter=0;
	   		Integer toolCounter=0;
	   		Integer gunCounter=0;
	   		Integer ammoCounter=0;
	   		
	   		int indexValue = 0;
	   		char indexValues;
	   		int counter=0;
	   		
		    BufferedReader TLVFile = null;
	        StringTokenizer st = null ;
	        String tlvString = new String();
			try {
				// Try to open the TLV file from the specified location
				TLVFile = new BufferedReader(new FileReader("C:\\Temp\\ProductData.tlv"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        List<String>dataArray = new ArrayList<String>() ;
	        String dataRow = null;
			try {
				dataRow = TLVFile.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Read first line.

			// Read the data to stringtokenizer
	        while (dataRow != null){
	            st = new StringTokenizer(dataRow,"\t");
	            while(st.hasMoreElements()){
	                dataArray.add(st.nextElement().toString());
	            }
	            for (String item:dataArray) { 
	                //System.out.print(item + "  ");
	                counter++;
	                // When the string dataArray has been finalized?
	                if(counter>27)
	                {
	                	tlvString += item.toString();
	                }
	                // The loop is ready to be opened
	               if(counter==37)
	               {
	    		   	   // 1st If the data has length and not in EOF - element has some data still
	    		   	   if(tlvString.length()>1)		
	    					{
	    			   	   // Get the zero significant byte
	    			   	   char at = tlvString.charAt(0);
	    			   	   // And turn it to integer
	    			   	   int value = (int) at;
	    			   	   
	    			   	   	// Loop until EO of String (buffer) - are there data?
	    		   		   	for(int i=0; i<tlvString.length(); i++)
	    		   		   	{
	    		   		   		// Get the significant character
	    						at = tlvString.charAt(i);
	    						// And turn it to value
	    						value = (int)at;

	    						String output = "";
	    						// Switch to significant character
	    						switch(value)
	    						{
	    							// STX
	    						case 2:
	    								indexValues = tlvString.charAt(i+1); 
	    								indexValue = (int)indexValues;
	    								// STX & EOT
	    								if(value==2 & indexValue==4)
	    								{
	    									// Food - Apple
			    							//output = tlvString.substring(i+2, i+2+indexValue);
		    								//System.out.println(output);
			    							break;
	    								}
	    								indexValues = tlvString.charAt(i-1);
	    								indexValue = (int) indexValues;
	    								if(value==2 & indexValue==14)
	    								{
	    									// Food - Apple
			    							//output = tlvString.substring(i+2, i+7);
		    								//System.out.println(output);
	    									break;
	    								}
	    								//output = tlvString.substring(i+2, i+7);
	    								//System.out.println(output);
	    								break;
	    							// ETX
	    						case 3:
	    							indexValues = tlvString.charAt(i); 
	    							indexValue = (int)indexValues;

	    							// Saws
	    							indexValues = tlvString.charAt(i+1);
	    							indexValue = (int)indexValues;
	    							if(value==3 & indexValues==3)
	    							{
	    							// Saw
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("saw"))
	    								{
	    									toolCounter++;
	    								}
	    								break;
	    							}
	    							if(value==3 & indexValues==4)
	    							{
	    							// Food
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("apple"))
	    								{
	    									foodCounter++;
	    								}
	    								else if(output.matches("nail"))
	    								{
	    									toolCounter++;
	    								}
	    								break;
	    							}
	    							if(value == 3 & indexValues==5 )
	    							{
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("steak")||output.matches("apple"))
	    								{
	    									foodCounter++;
	    								}
	    								else if(output.matches("mouse")||output.matches("desktop")||output.matches("keyborad"))
	    								{
	    									computerCounter++;
	    								}
	    								else if(output.matches("drill"))
	    								{
	    									toolCounter++;
	    								}
	    								else if(output.matches("revolver")||output.matches("rifle"))
	    								{
	    									gunCounter++;
	    								}
	    								else if(output.matches("bullet")||output.matches("shell"))
	    								{
	    									ammoCounter++;
	    								}
	    								break;
	    							}
	    							// Computers & tomato
	    							if(value==3 & indexValues==6)
									{
	    								indexValues = tlvString.charAt(i);
		    							indexValue = (int)indexValues;
	    								if(value==3 & indexValue==6)
	    								{
	    									// computers
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("desktop"))
	    								{
		    								computerCounter++;
	    								}
	    								break;
	    								}
	    								else
	    								{
	    									//tomato
		    								output = tlvString.substring((i+2), (i+5+indexValues));
		    								System.out.println(output);
		    								if(output.matches("tomato"))
		    								{
			    								foodCounter++;
		    								}
		    								else if(output.matches("laptop"))
		    								{
		    									computerCounter++;
		    								}
		    								else if(output.matches("keyboard"))
		    								{
		    									computerCounter++;
		    								}
		    								else if(output.matches("hammer"))
		    								{
		    									toolCounter++;
		    								}
		    								else if(output.matches("revolver")||output.matches("pistol"))
		    								{
		    									gunCounter++;
		    								}
		    								else if(output.matches("bullet"))
		    								{
		    									ammoCounter++;
		    								}
		    								break;	
	    								}
									}
	    							// Lettuce
	    							if(value==3 & indexValues==7)
	    							{
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("lettuce"))
	    								{
		    								foodCounter++;
		    							}
	    								else if(output.matches("desktop"))
	    								{
	    									computerCounter++;
	    								}
	    								break;	    								
	    							}
	    							// Computers
	    							if(value==3 & indexValues==8)
	    							{
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("keyboard"))
	    								{
		    								computerCounter++;
	    								}
	    								else if(output.matches("revolver"))
	    								{
	    									gunCounter++;
	    								}

	    								break;	    								
	    							}	  
	    							if(value == 3 & indexValues==12)
	    							{
	    								output = tlvString.substring((i+2), (i+2+indexValues));
	    								System.out.println(output);
	    								if(output.matches("nail"))
	    								{
		    								toolCounter++;
	    								}
	    								break;
	    							}
	    							break;
	    							
	    							// SO
	    						case 14: 
	    							// Saws
	    							// For nails and saws
	    							indexValues = tlvString.charAt(i+1);
	    							indexValue = (int)indexValues;
	    							if(value == 14 & indexValue==14)
	    							{
	    								output = tlvString.substring((i+5), (i+10));
	    								System.out.println(output);
	    								if(output.matches("nail"))
	    								{
		    								toolCounter++;
	    								}
	    								break;
	    							}
	    							else
	    							{
	    								// Saw check
	    								indexValues = tlvString.charAt(i+1);
	    								indexValue = (int) indexValues;
	    								if(value==14 & indexValue == 2)
	    								{
	    									// bullets
	    									char indexValue2 = tlvString.charAt(2);
	    									int indexValues2 = (int) indexValue2;
	    									if(value==14 & indexValues2 == 103)
	    									{
	    										output = tlvString.substring((i+9), (i+15));
	    										System.out.println(output);
	    										if(output.matches("bullet")||output.matches("shell"))
	    	    								{
	    	    									ammoCounter++;
	    	    								}
	    										break;
	    									}
	    									else if(value==14 & indexValues2==4) //tomatos
	    									{
	    										//output = tlvString.substring((i+3), (i+3+valueBullets));
	    										//System.out.println(output);
	    										break;	
	    									}
	    									else
	    									{
	    										output = tlvString.substring((i+9), (i+15));
	    										System.out.println(output);
	    										//toolCounter++;
	    										break;
	    									}
	    								}
	    								else
	    								{
	    								output = tlvString.substring((i+9), (i+15));
	    								System.out.println(output);
	    								break;
	    								}
	    							}
	    							// SI
	    						case 15: 
	    							//output = tlvString.substring((i+24), (i+31));
	    							//System.out.println(output);
	    							break;
	    							// DC3	
	    						case 19:
	    							char computers = tlvString.charAt(i+1);
	    							int valueComp = (int)computers;
	    							if(value == 19 & valueComp==2)
	    							{
	    								//output = tlvString.substring((i+2), (i+8+valueComp));
	    								//System.out.println(output);
	    								break;
	    							}
	    							break;
	    							// DC4
	    						case 20:
	    							computers = tlvString.charAt(i+1);
	    							valueComp = (int)computers;
	    							if(value == 20 & valueComp==2)
	    							{
	    								//output = tlvString.substring((i+2), (i+8+valueComp));
	    								//System.out.println(output);
	    								break;
	    							}
	    							break;
	    							// NAK
	    						case 21:
	    							computers = tlvString.charAt(i+1);
	    							valueComp = (int)computers;
	    							if(value == 21 & valueComp==2)
	    							{
	    								//output = tlvString.substring((i+2), (i+8+valueComp));
	    								//System.out.println(output);
	    								break;
	    							}
	    							break;
	    						default:
	    							break;
	    						}
	    		   		   	}
	    		   		   	tlvString = null;
	    				}
	               }
	            }
	           // System.out.println(); // Print the data line.
	            try {
					dataRow = TLVFile.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // Read next line of data.
	        }
	        // Close the file once all data has been read.
	        try {
	        	TLVFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    // End the printout with a blank line.
    //System.out.println();
   	String foods = null;
   	String computers = null;
   	String tools = null;
   	String ammos = null;
   	String guns = null;

   	foods = foodCounter.toString();
   	computers = computerCounter.toString();
   	tools = toolCounter.toString();
   	ammos = ammoCounter.toString();
   	guns = gunCounter.toString();
   	// Print the list 
   	System.out.println("Food:" + foods + " " + "Computers:" + computers + " " + "Tools:" + tools + " " + "Ammo:" + ammos + " " + "Guns:" + guns);
   	// And deliver the list to main program to be delivered to warehouses
   	return returnString = foods + " " + computers +  " " + tools + " " + ammos + " " + guns;
   } //main()
} // TSVRead