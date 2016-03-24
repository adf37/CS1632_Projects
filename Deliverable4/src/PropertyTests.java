import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class PropertyTests {
			
		//100 randomly sized and randomly filled integer arrays will be tested such that each subsequent value
		//in the array should be equal to or greater than the previous element in the array. If this is not the 
		//case at any point the test will fail.
		@Test
		public void testIncreasingValues() {
			ArrayList<Integer> allSizes = new ArrayList<Integer>();
			//We want to set up a hundred different sized arrays to be used with our property-based testing methods.
			for (int i=0; i<100; i++){
				int size = (int)(Math.random()*1000 + 1);
				if (!allSizes.contains(size) || i==0){
					allSizes.add(size);
				}
				else{
					while(allSizes.contains(size)){
						size = (int)(Math.random()*1000+1);
					}
					allSizes.add(size);
				}
				int [] arr1 = new int[size];
				//fill our random-sized integer array with a random number between 0 and 10,000.
				for (int j=0; j<size; j++){
					int random = (int)(Math.random()*10000);
					arr1[j] = random;
				}
				Arrays.sort(arr1);
				boolean increasing = true;
				for (int z=0; z<arr1.length-1; z++){
					if (arr1[z+1] < arr1[z]){
						fail("Value was not in increasing order");
					}
				}
				assertTrue(increasing);
			}
		}
			
		//100 random-sized and randomly filled integer arrays will be sorted and then tested that each value at each
		//indice is not decreasing in relation to the next indice.
		//Each subsequent value in an indice should be less than or equal to the value of the next indice.
		@Test
		public void testNotDescreasing(){
			ArrayList<Integer> allSizes = new ArrayList<Integer>();
			//We want to set up a hundred different sized arrays to be used with our property-based testing methods.
			for (int i=0; i<100; i++){
				int size = (int)(Math.random()*1000 + 1);
				if (!allSizes.contains(size) || i==0){
					allSizes.add(size);
				}
				else{
					while(allSizes.contains(size)){
						size = (int)(Math.random()*1000+1);
					}
					allSizes.add(size);
				}
				int [] arr1 = new int[size];
				//fill our random-sized integer array with a random number between 0 and 10,000.
				for (int j=0; j<size; j++){
					int random = (int)(Math.random()*10000);
					arr1[j] = random;
				}	
				Arrays.sort(arr1);
				boolean decreasing = false;
				for (int z=0; z<arr1.length-1; z++){
					if(arr1[z] > arr1[z+1]){
						fail("Value should never be decreasing");
					}
				}
				assertFalse(decreasing);
			}
		}
		
		//100 random-sized and randomly filled integer arrays will be sorted and then test that each value at each 
		//indice is the same as the originally filled array. If at any point a value is found in the sorted array that
		//is not in the original array will result in a failed test. 
		@Test
		public void testElementsEqual(){
			ArrayList<Integer> allSizes = new ArrayList<Integer>();
			//We want to set up a hundred different sized arrays to be used with our property-based testing methods.
			for (int i=0; i<100; i++){
				int size = (int)(Math.random()*1000+1);
				if (!allSizes.contains(size) || i==0){
					allSizes.add(size);
				}
				else{
					while(allSizes.contains(size)){
						size = (int)(Math.random()*1000+1);
					}
					allSizes.add(size);
				}
				int [] arr1 = new int[size];
				//fill our random-sized integer array with a random number between 0 and 10,000.
				for (int j=0; j<size; j++){
					int random = (int)(Math.random()*10000);
					arr1[j] = random;
				}
				int [] arr2 = Arrays.copyOf(arr1, arr1.length);
				Arrays.sort(arr2);
				int count = 0;
				for (int a=0; a<arr1.length; a++){
					for (int b=0; b<arr2.length; b++){
						if (arr1[a] == arr2[b]){
							count++;
							break;
						}
					}
				}
				assertEquals(arr1.length, count);
			}
		}
		
		@Test
		//After each of the 100 different random sized arrays are generated and filled with random values we want to check idempotentcy.
		//Two copies of the original array will be made and each copy will be sorted with the Arrays.sort method. Then each indice will
		//be checked to see if they match. If at any point this is not the case then the test fails. 
		public void testIdempotentcy(){
			ArrayList<Integer> allSizes = new ArrayList<Integer>();
			//We want to set up a hundred different sized arrays to be used with our property-based testing methods.
			for (int i=0; i<100; i++){
				int size = (int)(Math.random()*1000+1);
				if (!allSizes.contains(size) || i==0){
					allSizes.add(size);
				}
				else{
					while(allSizes.contains(size)){
						size = (int)(Math.random()*1000+1);
					}
					allSizes.add(size);
				}
				int [] arr1 = new int[size];
				//fill our random-sized integer array with a random number between 0 and 10,000.
				for (int j=0; j<size; j++){
					int random = (int)(Math.random()*10000);
					arr1[j] = random;
				}
				int [] arr2 = Arrays.copyOf(arr1, arr1.length);
				int [] arr3 = Arrays.copyOf(arr1, arr1.length);
				Arrays.sort(arr2);
				Arrays.sort(arr3);
				assertEquals(arr2.length, arr3.length);
				//Check to see that all values are equal for both arrays after they are sorted. If this is not the case then
				//the test fails and the Arrays.sort method does not preserve idempotency.
				for (int a = 0; a<arr2.length; a++){
					if (arr2[a] != arr3[a]){
						fail("Array indice values do not match, arrays are not idempotent!");
					}
				}
				assertTrue(true);
			}
		}
		
		@Test
		//100 different random sized integer arrays will be filled with random integer values. In each iteration the size of the original
		//input array will be checked with a copy of the original array that is sorted with the Arrays.sort method. Both the input and output
		//array should be the same size.
		public void testSameSize(){
			ArrayList<Integer> allSizes = new ArrayList<Integer>();
			//We want to set up a hundred different sized arrays to be used with our property-based testing methods.
			for (int i=0; i<100; i++){
				int size = (int)(Math.random()*1000+1);
				if (!allSizes.contains(size) || i==0){
					allSizes.add(size);
				}
				else{
					while(allSizes.contains(size)){
						size = (int)(Math.random()*1000+1);
					}
					allSizes.add(size);
				}
				int [] arr1 = new int[size];
				//fill our random-sized integer array with a random number between 0 and 10,000.
				for (int j=0; j<size; j++){
					int random = (int)(Math.random()*10000);
					arr1[j] = random;
				}
				int [] arr2 = Arrays.copyOf(arr1, arr1.length);
				Arrays.sort(arr2);
				assertEquals(arr1.length, arr2.length);
			}
		}

}
