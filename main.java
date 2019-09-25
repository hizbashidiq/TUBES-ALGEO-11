import java.util.Scanner;
class main
{
	public static void main(String args[])
	{
		matriks M = new matriks(1,1);
		int n, menu,submenu, baris, kolom, i;
		Scanner input = new Scanner(System.in);
		menu = 0;
		while (menu!=7)
		{
			M.Menu();
			menu = input.nextInt();
			switch(menu)
			{
				case 1: 
					M.Submenu();
					submenu = input.nextInt();
					switch (submenu)
						{
							case 1:System.out.println("Determinan dari matriks tersebut adalah ");break;
							case 2:System.out.println("Determinan dari matriks tersebut adalah ");break;
							case 3:System.out.println("Determinan dari matriks tersebut adalah ");break;
							case 4:System.out.println("Determinan dari matriks tersebut adalah ");break;
						}
					break;
				case 2:
					M.Submenu();
					submenu = input.nextInt();
					System.out.print("Membuat matriks persegi dengan index: ");
					n = input.nextInt();
					matriks M2 = new matriks(n,n);
					System.out.println("Isi matriks:");
					M2.isimatriks(M2,n,n);
					switch (submenu)
					{
						case 1:System.out.println("Determinan dari matriks tersebut adalah "+M2.Determinan(M2));break;
						case 2:System.out.println("Determinan dari matriks tersebut adalah "+M2.Determinan(M2));break;
						case 3:System.out.println("Determinan dari matriks tersebut adalah "+M2.Determinan(M2));break;
						case 4:System.out.println("Determinan dari matriks tersebut adalah "+M2.Determinan(M2));break;
					}
					break;
				case 3:
					M.Submenu();
					submenu = input.nextInt();
					switch (submenu)
						{
							case 1:System.out.println("Determinan dari matriks tersebut adalah ");break;
							case 2:System.out.println("Determinan dari matriks tersebut adalah ");break;
							case 3:System.out.println("Determinan dari matriks tersebut adalah ");break;
							case 4:System.out.println("Determinan dari matriks tersebut adalah ");break;
						}
					break;
				case 4: 
					System.out.println("Membuat matriks dengan ukuran: ");
					System.out.print("baris: ");
					baris = input.nextInt();
					System.out.print("kolom: ");
					kolom = input.nextInt();
					matriks M4 = new matriks(baris, kolom);
					System.out.println("Isi matriks:");
					M4.isimatriks(M4,baris,kolom);
					M4.tulismatriks(M4.MatriksKofaktor(M4));
					break;
				case 5: 
					System.out.println("Membuat matriks dengan ukuran: ");
					System.out.print("baris: ");
					baris = input.nextInt();
					System.out.print("kolom: ");
					kolom = input.nextInt();
					matriks M5 = new matriks(baris, kolom);
					System.out.println("Isi matriks:");
					M5.isimatriks(M5,baris,kolom);
					M5.tulismatriks(M5.MatriksAdjoin(M5));
					break;
				case 6: 
					System.out.print("Membuat matriks dengan ukuran: ");
					System.out.print("baris: ");
					baris = input.nextInt();
					System.out.print("kolom: ");
					kolom = input.nextInt();
					matriks M6 = new matriks(baris, kolom);
					System.out.println("Isi matriks:");
					M6.isimatriks(M6,baris,kolom);
					for (i=0;i<baris;i++)
					{
						System.out.println(M6.InterpolasiPolinom(M6,i));
					}
					break;
				case 7: System.out.println("7");break;
			}
		}
	}
}
