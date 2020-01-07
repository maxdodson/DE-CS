public class TestClass {
	
	public static void main(String[] args) {
		ProductionLine pLine = new ProductionLine();
		
		Disk[] disks = new Disk[15];
		for (int i=0; i<disks.length; i++) {
			disks[i] = new Disk((int)(Math.random() * 10) + 1); // Generate Disks of random radius (1-10)
			pLine.addDisk(disks[i]);
			System.out.println(disks[i]);
		}
		
		System.out.println("---------------------\n");
		
		pLine.process(); // Process all the Disks
		
		Tower out = pLine.removeTower();
		while (out != null) { // Remove each Tower and print its contents
			System.out.println(out); 
			out = pLine.removeTower();
		}
		
	}
}