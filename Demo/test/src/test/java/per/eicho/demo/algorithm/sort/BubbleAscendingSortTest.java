package per.eicho.demo.algorithm.sort;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class BubbleAscendingSortTest {
	
	BubbleAscendingSort sort;
	
	@Before
	public void init() {
		sort = new BubbleAscendingSort();
	}	

	@Test
	public void testDoSort() {
		Random random = new Random(); 
		for (int i = 0; i < 10; i++) { // 10回
			final int randomLength = random.nextInt(10000) + 1;
			final int[] input = new int[randomLength];
			
			for (int j = 0; j < randomLength; j++) {
				input[j] = random.nextInt();
			}
			
			final int[] inputCopy = Arrays.copyOf(input, input.length);
			Arrays.sort(inputCopy);
			final int[] expected = inputCopy;
			
			sort.doSort(input);
			final int[] actual = input;
			
			assertArrayEquals(expected, actual);
		}		
	}

}
