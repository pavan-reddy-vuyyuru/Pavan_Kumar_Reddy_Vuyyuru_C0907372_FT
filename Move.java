import java.util.ArrayList;

class SingleObject {
	private String name;

	public SingleObject(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
class Box{
	ArrayList<Object> contents;
	public int boxNumber;
	public Box(int noOfItems, int boxNumber){
		this.contents = new ArrayList<>(noOfItems);
		this.boxNumber = boxNumber;
	}
	public void addItem(Object item) {
		contents.add(item);
		//System.out.println("Item added....");
		//final commit
	}
	public int find(String itemName){
		//System.out.println("Dummy return from here");
		for (Object item : contents) {
			if (item instanceof SingleObject) {
				if (((SingleObject) item).getName().equals(itemName)) {
					return boxNumber;
				}
			} else if (item instanceof Box) {
				int result = ((Box) item).find(itemName);
				if (result != -1) {
					return result;
				}
			}
		}
		return -1;
		//return 5;

	}

	public void displayContents() {
		//System.out.println("Lets check control coming here");
		for (Object item : contents) {
			if (item instanceof SingleObject) {
				System.out.println(((SingleObject) item).getName() + " ");
			} else if (item instanceof Box) {
				((Box) item).displayContents();
			}
		}
	}

}
class Move {
	/* *************************************** */
	// write your code here
	private final ArrayList<Box> boxes;
	public Move(int numBoxes) {
		boxes = new ArrayList<>(numBoxes);
	}
	private void addBox(Box box) {
		boxes.add(box);
	}
	public void print() {
		System.out.println("The objects of my move are:");
		for (Box box : boxes) {
			box.displayContents();
		}
	}
	public int find(String item) {
		for (Box box : boxes) {
			int result = box.find(item);
			if (result != -1) {
				return result;
			}
		}
		return -1;
	}
	/* *************************************** */



	public static void main(String[] args) {
		// We create a move that will hold 2 main boxes
		Move move = new Move(2);

		/*
		 * We create and then fill 3 boxes
		 * Arguments of the constructor of Box:
		 * argument 1: number of items (simple items/objects or box) that the box can hold
		 * argument 2: box number
		 */

		// box 1 contains scissors
		Box box1 = new Box(1, 1);
		box1.addItem(new SingleObject("scissors"));

		// box 2 contains one book
		Box box2 = new Box(1, 2);
		box2.addItem(new SingleObject("book"));

		// box 3 contains one compass
		// and one box containing one scarf
		Box box3 = new Box(2, 3);
		box3.addItem(new SingleObject("compass"));
		Box box4 = new Box(1, 4);
		box4.addItem(new SingleObject("scarf"));
		box3.addItem(box4);

		// We add the three boxes to the first box of move - see below
		Box box5 = new Box(3, 5);
		box5.addItem(box1);
		box5.addItem(box2);
		box5.addItem(box3);

		// We add one box containing 3 objects to move
		Box box6 = new Box(3, 6);
		box6.addItem(new SingleObject("pencils"));
		box6.addItem(new SingleObject("pens"));
		box6.addItem(new SingleObject("rubber"));

		// We add the two most external boxes to the move
		move.addBox(box5);
		move.addBox(box6);

		// We print all the contents of the move
		move.print();

		// We print the number of the outermost cardboard containing the item "scarf"
		System.out.println("The scarf is in the cardboard number " + move.find("scarf"));
	}




}