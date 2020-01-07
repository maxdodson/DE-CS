public class TestClass {
	
	public static void main(String[] args) {
		ProductionLine pLine = new ProductionLine();
		
		Disk[] disks = new Disk[15];
		for (int i=0; i<disks.length; i++) {
			disks[i] = new Disk((int)(Math.random() * 8) + 1); // Generate Disks of random radius
			pLine.addDisk(disks[i]);
		}
		
		pLine.process();
		
		Tower out = pLine.removeTower();
		while (out != null) {
			System.out.println(out);
			out = pLine.removeTower();
		}
		
	}
}