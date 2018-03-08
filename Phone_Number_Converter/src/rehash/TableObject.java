package rehash;

/*
Project2 CS 421
Devin Lee and Nathan Cartwright 
*The purpose of this program is to take in a phone number excluding the leading 1 from user 
and based on a large list of words determine if a word or words can be made from the given phone number and print that phone number in word format instead.
*We use an object that has two parameters a key which is just a number and a value which is a linkedlist of words that will correlate to the key.
With this object we can link a key to a given list of words and from that can pull words based on a given key.
 */



import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class TableObject {
	// initializing the values of the object
	long key;
	LinkedList<String> value;

	// creating the object
	public TableObject() {
		key = -1;
		value = new LinkedList<String>();

	}

	// method that determines what the object will take in
	public TableObject(long k, String l) {
		key = k;
		value = new LinkedList<String>();
		value.add(l);
	}

	// method to add values
	public void addValue(String l) {
		value.add(l);
	}

	// method to create a new key
	public void newKey(long l) {
		key = l;
	}

	// method to retrieve a key
	public long getKey() {
		return key;
	}

	// method to retrieve a value
	public LinkedList<String> getValue() {
		return value;
	}

	// method for getting a specific index based on inputed key, tablesize and a
	public static long hash(long key) {
		double a = .618033;
		double h = (int) (m * (key * a % 1));
		return (long) h;

	}

	public static long tableSize() {
		return 0;

	}

	// takes the phone number and returns the 7 digit number
	public static long convert7(long n) {

		String m = Long.toString(n);
		String l = m.substring(3, 10);
		long k = Long.parseLong(l);
		return k;

	}

	// takes the phone number and returns the 4 digit number
	public static long convert4(long n) {
		String m = Long.toString(n);
		String l = m.substring(6, 10);
		long k = Long.parseLong(l);
		return k;

	}

	// takes the phone number and returns the exchange
	public static long convert3(long n) {
		String m = Long.toString(n);
		String l = m.substring(3, 6);
		long k = Long.parseLong(l);
		return k;

	}

	// takes the phone number and returns the area code
	public static long convertarea(long n) {
		String m = Long.toString(n);
		String l = m.substring(0, 3);
		long k = Long.parseLong(l);
		return k;

	}

	// converting the numbers to words
	public static long convert(String word) {
		long number = 0;

		for (int i = 0; i < word.length(); i++) {
			switch (word.charAt(i)) {
			case 'a':
			case 'b':
			case 'c':
				number *= 10;
				number += 2;
				break;
			case 'd':
			case 'e':
			case 'f':
				number *= 10;
				number += 3;
				break;
			case 'g':
			case 'h':
			case 'i':
				number *= 10;
				number += 4;
				break;
			case 'j':
			case 'k':
			case 'l':
				number *= 10;
				number += 5;
				break;
			case 'm':
			case 'n':
			case 'o':
				number *= 10;
				number += 6;
				break;
			case 'p':
			case 'q':
			case 'r':
			case 's':
				number *= 10;
				number += 7;
				break;
			case 't':
			case 'u':
			case 'v':
				number *= 10;
				number += 8;
				break;
			case 'w':
			case 'x':
			case 'y':
			case 'z':
				number *= 10;
				number += 9;
				break;
			}
		}
		return number;
	}

	// takes all the words of length 10,7,4,3 from validWords.txt and puts them
	// in a new arraylist
	public static ArrayList<String> getLength(int n, int m, int k, int i) {
		ArrayList<String> length = new ArrayList<String>();
		for (int j = 0; j < validWords.size(); j++) {

			if (validWords.get(j).length() == n || validWords.get(j).length() == m || validWords.get(j).length() == k
					|| validWords.get(j).length() == i) {

				length.add(validWords.get(j));

			}

		}
		return length;
	}

	// get the next prime that is closest to twice the size of n
	static long nextPrime(long n) {
		n = n * 2;
		BigInteger b = new BigInteger(String.valueOf(n));
		return Long.parseLong(b.nextProbablePrime().toString());
	}

	// initialize the validWords arraylist
	static ArrayList<String> validWords = new ArrayList<String>();

	// static table size
	static long m = 10007;

	// method that creates the table object
	public static TableObject[] rehash(ArrayList<Long> list, ArrayList<String> getLength) {
		double a = .618033;
		// set initial size of table to m
		TableObject[] hashTable = new TableObject[(int) m];

		// fill all the spaces with key = -1 and value = empty arraylist
		for (int k = 0; k < m; k++) {
			hashTable[k] = new TableObject(-1, "");
		}

		// go through the list of numbers
		for (int i = 0; i < list.size(); i++) {
			int index = (int) (m * (list.get(i) * a % 1));
			// if key is -1 and does not = the number obtained from list
			if (hashTable[index].getKey() == -1 && hashTable[index].getKey() != list.get(i))
				// make the key at that index the number from list and add the
				// value from the list of words
				hashTable[index] = new TableObject(list.get(i), getLength.get(i));
			// if the key obtained is the same as the number than add the extra
			// word to value
			else if (hashTable[index].getKey() == list.get(i))
				hashTable[index].addValue(getLength(10, 7, 4, 3).get(i));
			// if the key is neither -1 or the same number than linear probe to
			// the next key that = -1
			else if (hashTable[index].getKey() != list.get(i) && hashTable[index].getKey() != -1) {
				if (index < m) {
					// increment indexes till a spot is found
					for (int k = 1; k < m; k++) {
						// if the next spot = that number than just add value of
						// the word
						if (hashTable[index + k].getKey() == list.get(i)) {
							hashTable[index + k].addValue(getLength(10, 7, 4, 3).get(i));
							break;
						}
						// if the next key = -1 than add the new key and new
						// value
						if (hashTable[index + k].getKey() == -1) {
							hashTable[index + k] = new TableObject(list.get(i), getLength.get(i));
							break;
						}
					}
				}

			}

		}
		// return the table
		return hashTable;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	// main
	public static void main(String[] args) throws FileNotFoundException {

		String fileName = "validwords.txt"; // get a file name from the user

		File file = new File(fileName);

		if (file.exists()) // check that the file exists

		{

			Scanner inFile = new Scanner(file);

			while (inFile.hasNext()) {

				validWords.add(inFile.nextLine()); // read the next line

			}

			inFile.close(); // close the text file;

		}
		// scanner for number input
		Scanner get = new Scanner(System.in);
		System.out.println("Please enter a phone number excluding the leading 1: ");
		long phonenumber = get.nextLong();
		// arraylist for all numbers of length 10,7,4,3 taken from
		// validwords.txt and converted to words
		ArrayList<Long> list = new ArrayList<Long>();
		for (int i = 0; i < getLength(10, 7, 4, 3).size(); i++) {
			list.add(convert(getLength(10, 7, 4, 3).get(i)));
		}
		// count the number of spots used in the table = 4057
		// int count = 0;
		//
		// for (int p = 0; p < m; p++) {
		// if(rehash(list, getLength(10,7,4,3))[p].getKey() ==-1){
		//
		// count++;
		// }
		//
		// }
		//
		// System.out.println(count);

		// print the indexes and values of the entire table
		// for (int p = 0; p < m; p++) {
		// System.out.printf("Index [%d]: " + "Key: "+rehash(list,
		// getLength(10,7,4,3))[p].getKey() + " Value: " +rehash(list,
		// getLength(10,7,4,3))[p].getValue()+ "\n", p);
		// }

		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Direct Search O(1)(Good)
		// searching for words of length 10
		if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(phonenumber)].getKey() == phonenumber))
			System.out.println("[1]-" + rehash(list, getLength(10, 7, 4, 3))[(int) hash(phonenumber)].getValue());

		// searching for words of length 7
		else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert7(phonenumber))]
				.getKey() == convert7(phonenumber)))
			System.out.println("[1]-" + "[" + convertarea(phonenumber) + "]-"
					+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert7(phonenumber))].getValue());

		// check for all cases including words of length 3 and 4
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// has both words of length 3 and 4
		else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))]
				.getKey() == convert3(phonenumber))
				&& (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))]
						.getKey() == convert4(phonenumber)))
			System.out.println("[1]-" + "[" + convertarea(phonenumber) + "]-"
					+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))].getValue() + "-"
					+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))].getValue());
		// has words of length 4 but none of length 3
		else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))]
				.getKey() != convert3(phonenumber))
				&& (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))]
						.getKey() == convert4(phonenumber)))
			System.out.println("[1]-" + "[" + convertarea(phonenumber) + "]-" + "[" + convert3(phonenumber) + "]" + "-"
					+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))].getValue());
		// has words of length 3 but none of length 4
		else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))]
				.getKey() == convert3(phonenumber))
				&& (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))]
						.getKey() != convert4(phonenumber)))
			System.out.println("[1]-" + "[" + convertarea(phonenumber) + "]-"
					+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))].getValue() + "-["
					+ convert4(phonenumber) + "]");

		// if none of the conditions are met check if linear probing was done
		// and if no key is still found than no word exists for that number

		else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(phonenumber)].getKey() != phonenumber
				|| (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert7(phonenumber))]
						.getKey() != convert7(phonenumber))
				|| rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))]
						.getKey() != convert3(phonenumber))
				|| rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber))]
						.getKey() == convert3(phonenumber)
				|| rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))]
						.getKey() == convert4(phonenumber)
				|| (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber))]
						.getKey() != convert4(phonenumber))) {
			// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			// linear check each key after to find any linear probed values
			// repeat of above if statements but with an incrementing index
			for (int i = 1; i < 20; i++) {
				
				
			
				if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(phonenumber) + i].getKey() == phonenumber))
					System.out.println(
							"1-" + rehash(list, getLength(10, 7, 4, 3))[(int) hash(phonenumber) + i].getValue());

				else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert7(phonenumber)) + i]
						.getKey() == convert7(phonenumber)))
					System.out.println("1-" + "[" + convertarea(phonenumber) + "]-"
							+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert7(phonenumber)) + i].getValue());

				else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber)) + i]
						.getKey() == convert3(phonenumber))
						&& (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber)) + i]
								.getKey() == convert4(phonenumber)))
					System.out.println("1-" + "[" + convertarea(phonenumber) + "]-"
							+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber)) + i].getValue()
							+ "-"
							+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber)) + i].getValue());

				else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber)) + i]
						.getKey() != convert3(phonenumber))
						&& (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber)) + i]
								.getKey() == convert4(phonenumber)))
					System.out.println("1-" + "[" + convertarea(phonenumber) + "]-" + "[" + convert3(phonenumber) + "]"
							+ "-"
							+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber)) + i].getValue());

				else if ((rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber)) + i]
						.getKey() == convert3(phonenumber))
						&& (rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert4(phonenumber)) + i]
								.getKey() != convert4(phonenumber)))
					System.out.println("1-" + "[" + convertarea(phonenumber) + "]-"
							+ rehash(list, getLength(10, 7, 4, 3))[(int) hash(convert3(phonenumber)) + i].getValue()
							+ "-[" + convert4(phonenumber) + "]");
			
			}
			
				System.out.printf("There was no words to be made. Here is your number: [1]-[%d]-[%d]-[%d]",
						convertarea(phonenumber), convert3(phonenumber), convert4(phonenumber));
			
		} else
			System.out.printf("There was no words to be made. Here is your number: [1]-[%d]-[%d]-[%d]",
					convertarea(phonenumber), convert3(phonenumber), convert4(phonenumber));
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// if no word is found print back number given

		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		get.close();
	}
}
