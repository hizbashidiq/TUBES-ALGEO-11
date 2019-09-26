//package percobaan.pkg1;
import java.util.Scanner;
import java.io.*;
public class MATRIKS {
    protected double[][] matriks;
    protected int baris;
    protected int kolom;
    Scanner input = new Scanner(System.in);
    
    /* KONSTRUKTOR */
    public void MATRIKS(int i, int j){
        matriks = new double[i][j];
        baris = i;
        kolom = j;
    }
    
    /* SELEKTOR */
    public int getBrs(){
        return this.baris;
    }
    
    public int getKol(){
        return this.kolom;
    }
    
    public double getElmt(int i, int j){
        return this.matriks[i-1][j-1];
    }
    
    public double getDiagonal(int i){
        return this.matriks[i-1][i-1];
    }
    
    
    /* KELOMPOK BACA/TULIS MATRIKS */
    public void bacaMatriks(){
		System.out.print("Masukan jumlah baris matriks: ");
		this.baris = input.nextInt();
		System.out.print("Masukan jumlah kolom matriks: ");
		this.kolom = input.nextInt();
		this.matriks = new double[this.baris][this.kolom];
        for(int NB = 0; NB < baris; NB++){
            for(int NK = 0; NK < kolom; NK++){
                this.matriks[NB][NK] = input.nextDouble();
                
            }
        }
    }
	public void bacaMatriks2(int i, int j){
		this.matriks = new double[i][j];
        for(int NB = 0; NB < i; NB++){
            for(int NK = 0; NK < j; NK++){
                this.matriks[NB][NK] = input.nextDouble();
                
            }
        }
    }
    
    public void tulisMatriks(){
		int NB = this.getBrs();
		int NK = this.getKol();
        for(NB = 0; NB < baris; NB++){
            for(NK = 0; NK < kolom; NK++){
                if(this.matriks[NB][NK] == (-0.0)){
                    this.matriks[NB][NK] = 0.0;
                }
				System.out.print(this.matriks[NB][NK]);
				System.out.print(" ");
            }
           // System.out.println(this.matriks[NB][NK]);
            //if(NB!=baris){
            System.out.println();
		}
	}
    
    /* KELOMPOK PENGUJIAN BARIS */
    public boolean isBrsZero(int i){
        /*Fungsi mengembalikan true jika satu baris 
          semuanya 0 */
        
        /* KAMUS */
        int j;
        
        /* ALGORITMA */
        j = 0;
        while((this.matriks[i][j]==0) && j<this.kolom-1){
            j++;
        }
        if(this.matriks[i][j]==0){
            return true;
        }
        else{
            return false;
        }
    }
    
    /* KELOMPOK OPERASI BARIS PADA MATRIKS */
    public int getLead(int i){
        /*Fungsi mengembalikan indeks ditemukannya lead, yakni angka pertama
          selain 0  pada baris i */
        /* KAMUS */    
        int j;
        boolean cek;
        
        /* ALGORITMA */
        cek = false;
        j = 0;
        while(j<kolom && !cek){
            cek = (this.matriks[i][j]!=0);
            if(this.matriks[i][j]==0){
                j++;
            }
        }
         return j;
    }
    
    public void swap(int i1, int i2){
        //Prosedur men-swap antara baris i1 dengan i2
        /* KAMUS */
        int j;
        
        /* ALGORITMA */
        for(j=0; j<kolom; j++){
            double temp = this.matriks[i1][j];
            this.matriks[i1][j] = this.matriks[i2][j];
            this.matriks[i2][j] = temp;
        }
    }
    
    /* KELOMPOK OPERASI MATRIKS*/
    public void kaliKoef(int i, double x){
        //prosedur mengalikan baris dengan koefisien x
        /* KAMUS */
        int j;
        
        /* ALGORITMA */
        if(i<0 || i>=baris){
            System.out.println("Baris tidak valid");
        }
        else{
            for(j=0; j<kolom; j++){
                this.matriks[i][j] *= x;
            }
        }
    }
    
    public void plusBaris(int i1, int i2, double x){
        //prosedur membuat i1 = i1 + x*i2
        /* KAMUS */
        int j;
        
        /* ALGORITMA */
        
            for(j=0; j<kolom; j++){
                this.matriks[i1][j] += this.matriks[i2][j]*x;
            }
        
        
    }
    
    public void sortMatriks(){
        /*prosedur mengurutkan element matriks terurut
          membesar */
        /*prosedur akan swap baris bila getLead(i)>getLead(i+1)
        /* KAMUS */
        int i,j, maxBrs;   //note: maxBrs->awal sebagai inisiasi
        int temp;        // utk pembanding antar baris  
        
        /* ALGORITMA */
        if(baris>1){
            for(i=0; i<baris; i++){
                maxBrs = i;     //inisiasi maxBrs dg indeks i
                for(j=i+1; j<baris; j++){
                    temp = this.getLead(j);
                    if(temp < this.getLead(maxBrs)){
                        maxBrs = j;     //idx maxBrs digantikan j bila kondisi
                                        //memenuhi
                    }  
                }   
                this.swap(i,maxBrs);    //lakukan swap baris
            }
        }   
    }
    
    /* KELOMPOK MAIN OPERATION */
    public void gaussOp(){
        //prosedur melakukan operasi gauss pada matriks
        /* KAMUS */
        int i,j;
        int tempIdxLead;
        double leadKoef,pengali;
        
        /* ALGORITMA */
        this.sortMatriks();
        for(i=0; i<baris-1; i++){
            //Lakukan validasi baris, apakah seluruh elemennya 0 semua
            if(!this.isBrsZero(i)){
                leadKoef = this.matriks[i][this.getLead(i)];
                //Lakukan operasi gauss pada baris dibawahnya
                for(j=i+1; j<baris; j++){
                    if(!this.isBrsZero(j)){
                        pengali = (-1)*this.matriks[j][this.getLead(i)] / leadKoef;
                        this.plusBaris(j, i, pengali);
                    }
                    this.matriks[j][this.getLead(i)] = 0;
                }
            }
        }
        
        for(i=0; i<baris; i++){
            if(!this.isBrsZero(i)){
               leadKoef = this.matriks[i][this.getLead(i)];
               double temp = 1/leadKoef;
               this.kaliKoef(i, temp);
            }
        }
        
    }
    public void gaussJordan(){
        /* KAMUS */
        int i,j;
        
        /* ALGORITMA */
        gaussOp();
        for(i=baris-1; i>0; i--){
            if(!this.isBrsZero(i)){
                for(j=i-1; j>=0; j--){
                    if(!this.isBrsZero(j)){
                        double pengali = (-1)*this.matriks[j][this.getLead(i)];
                        this.plusBaris(j,i,pengali);
                    }
                }
            }
        }
    }    
    
    ///////////////////////////////////////////////
    
	public boolean isSimetris(MATRIKS m){
		boolean Simetris = true;
		if(isSquare(m)){
			for (int i = 0; i < baris; i++){
				for (int j = 0; j < kolom; j++){
					if (this.matriks[i][j] != this.matriks[j][i]){
						Simetris = false;
					}
				}
			}
		} else {
			Simetris = false;
		}
		return Simetris;
	}

	public boolean isSquare(MATRIKS m){
		return(kolom == baris);
	}

	public MATRIKS matriksMinor(MATRIKS m, int b, int k){
		MATRIKS res = new MATRIKS();
 		res.MATRIKS(this.baris-1,this.kolom-1);
		int idxBar = 0;
		if (isSquare(m)){
			for (int i = 0; i < baris; i++){
				int idxKol = 0;
				for (int j =  0; j < kolom; j++){
					if ((i != b) && (j != k)){
						res.matriks[idxBar][idxKol] = this.matriks[i][j];
						++idxKol;
					}
				}
				if (idxKol == this.kolom-1){
				++idxBar;
				}
			}
		} else {
			System.out.println("Ukuran Matriks tidak sesuai");
			res = m;
		}
		return res;
	}
	
	public MATRIKS matriksKofaktor(MATRIKS m){	
		MATRIKS minor = new MATRIKS();
		MATRIKS kofaktor = new MATRIKS();
		kofaktor.MATRIKS(this.baris, this.kolom);
		double determinan;
		if (isSquare(m)){
			for (int i = 0; i < baris; i++){
				for (int j =  0; j < kolom; j++){								
					minor = m.matriksMinor(m,i,j);
					determinan = Math.pow(-1,i+j)*minor.determinan(minor);
					kofaktor.matriks[i][j] = determinan;
				}
			}
		} else {
			System.out.println("Ukuran Matriks tidak sesuai");
			kofaktor = m;
		}
		return kofaktor;
	}
	
	public double determinan2(MATRIKS m){
		return ((m.matriks[0][0]*m.matriks[1][1]) - (m.matriks[1][0]*m.matriks[0][1]));
	}
	
	public double determinan(MATRIKS m){
		double det = 0;
		MATRIKS minor = new MATRIKS();
		if(isSquare(m)){
			if ((baris == 1) && (kolom == 1)){
				return m.matriks[0][0];
			}
			else {
				for (int i = 0; i < baris; i++){
					minor = m.matriksMinor(m,i,0);
					det += (Math.pow(-1, i))*(m.matriks[i][0])*(minor.determinan(minor));									
				}
			}
		} else {
			System.out.println("Ukuran Matriks tidak sesuai");
		}
		return det;
	}
		public MATRIKS transpose (MATRIKS m)
	{
		int j,i;
		MATRIKS MT = new MATRIKS();
		MT.MATRIKS(m.baris, m.kolom);
		for (i=0;i<m.baris;i++)
		{
			for (j=0;j<m.kolom;j++)
			{
				MT.matriks[i][j]=m.matriks[j][i];
			}
		}
		return MT;
	}
	public MATRIKS matriksAdjoin(MATRIKS m)
	{
		MATRIKS MT = new MATRIKS();
		MT.MATRIKS(m.baris, m.kolom);
		MT = m.transpose(m.matriksKofaktor(m));
		return MT;
	}
		void Menu()
	{
		System.out.println("MENU");
		System.out.println("1. Sistem Persamaan Linier");
		System.out.println("2. Determinan");
		System.out.println("3. Matriks Balikan");
		System.out.println("4. Matriks Kofaktor");
		System.out.println("5. Matriks Adjoin");
		System.out.println("6. Interpolasi Polinom");
		System.out.println("7. Keluar");
		System.out.print("Pilih menu yang Anda inginkan: ");
	}
	void Submenu()
	{
		System.out.println("1. Metode Eliminasi Gauss");
		System.out.println("2. Metode Eliminasi Gauss-Jordan");
		System.out.println("3. Metode Matriks Balikan");
		System.out.println("4. Kaidah Cramer");
		System.out.print("Pilih sub-menu yang Anda inginkan: ");
	}
	double InterpolasiPolinom(MATRIKS M, int i)
	{
		return i;
	}
	
	public MATRIKS bacaFileMatriks(String namaFile) {
        File file;
        MATRIKS m = new MATRIKS();
        int Nbaris = 0;
        int Nkolom = 0;
        int NEL = 0;
        int i, j;
        
        try {
            file = new File(namaFile);
            Scanner bacaBaris = new Scanner(file);
			while(bacaBaris.hasNextLine()){
				Nbaris++;
				String line = bacaBaris.nextLine();
                Scanner bacaKolom = new Scanner(line);
				while (bacaKolom.hasNextDouble()) {
					NEL++;
                    Double f = bacaKolom.nextDouble();
                }
			}
			Nkolom = NEL/Nbaris;
			System.out.println(Nbaris + " " + NEL/Nbaris);
			Scanner bacaDouble = new Scanner(file);
			m.MATRIKS(Nbaris,Nkolom);
			for (i = 0 ; i < Nbaris; i++){
				for (j = 0 ; j < Nkolom; j++){
					Double nilai = bacaDouble.nextDouble();
					m.matriks[i][j] = nilai;
				}
			}
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return m;
    }
	public void	SPLCrammer()
	{	
		int i,j,n,a;
		double DetUtama;
		n = 0;
		Scanner input = new Scanner(System.in);
		MATRIKS M = new MATRIKS();
		MATRIKS hasil = new MATRIKS();
		System.out.println("Masukkan matriks koefisien: ");
		M.bacaMatriks();
		double [] det = new double[M.baris];
		System.out.println("Masukkan matriks hasil: ");
		hasil.bacaMatriks2(M.baris,1);
		for (j=0;j<M.kolom;j++)
		{
			MATRIKS M3 = new MATRIKS();
			M3.MATRIKS(M.baris,M.kolom);

			for (n=0;n<M.baris;n++)
			{
				for (i=0;i<M.baris;i++)
				{
					for (j=0;j<M.kolom;j++)
					{
						M3.matriks[i][j]=M.matriks[i][j];
					}
				}
				for (i=0;i<M.baris;i++)
				{
					for (j=0;j<M.kolom;j++)
					{
						if (j==n)
						{
							M3.matriks[i][j]=hasil.matriks[i][0];
							det[i] = M3.determinan(M3);
						}
					}
				}
				M3.tulisMatriks();
			}	
		}
		for (i=0;i<M.baris;i++)
		{
			System.out.println("a["+(i+1)+"]="+det[i]/M.determinan(M));
		}
	}
	
	public void saveFile(MATRIKS m, String namafile){
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(namafile);
			for (int i = 0; i<m.baris; i++){
				for(int j = 0; j<m.kolom; j++){
					writer.print(matriks[i][j] + " ");
				} writer.println();
			}
		}
		catch (FileNotFoundException e){
			System.out.println("Error: " + e.getMessage());			
		} finally {
			try{
				if (writer != null)
					writer.close(); 
			} catch (Exception e){
				System.out.println("Could not close writer");
			}
		}
	}
	
	public MATRIKS matriksSegitigaBawah(MATRIKS m){
		double kali;
		MATRIKS temp = new MATRIKS();
		temp.MATRIKS(m.baris, m.kolom);
		
		for (int i = 0; i < temp.baris; i++){
			for (int j = 0; j < temp.kolom; j++){ 
				temp.matriks[i][j] = m.matriks[i][j];
			}
		}
		
		for (int k = 0; k < temp.baris; k++){
			for (int l = 0; l < temp.kolom; l++){
				kali = temp.matriks[k][k];
				m.matriks[k][l] *= kali;
			}
		}
		
		return m;
	}
	
	public double determinanGauss(MATRIKS m){
		double det = 1;
		gaussOp();
		if (isSquare(m)){
			m = m.matriksSegitigaBawah(m);
			for (int i = 0; i < m.baris; i++){
				det *= m.matriks[i][i];
			}
		} else
			System.out.println("Ukuran Matriks tidak sesuai");
		return det;
	}
	public MATRIKS InverseAdDet(MATRIKS m)
	{
		int i;
		if (m.determinan(m)==0)
		{
			System.out.println("Matriks Singular");
			return m;
		}
		else
		{
			MATRIKS MAdj = m.matriksAdjoin(m);
			for (i=0;i<m.baris;i++)
			{
				MAdj.kaliKoef(i,1/m.determinan(m));
			}
			return MAdj;
		}
	}
	
		
	public void makeIdentitas(int n){
		for (int i = 0; i < n ; i++){
			for (int j = 0; j < n ; j++){
				if (i == j)
					this.matriks[i][j] = 1;
				else
					this.matriks[i][j] = 0;
			}
		}
	}
	
	public MATRIKS concatMatriks(MATRIKS M1, MATRIKS M2){
		MATRIKS res = new MATRIKS();
		res.MATRIKS(M1.baris , M1.kolom + M2.kolom);
		if (M1.baris == M2.baris){
			for (int i = 0; i < M1.baris; i++){
				for (int j = 0; j < M1.kolom; j++){
					res.matriks[i][j] = M1.matriks[i][j];
				}
			}
			
			for (int i = 0; i < M1.baris; i++){
				for (int j = M1.kolom; j < M1.kolom + M2.kolom; j++){
					res.matriks[i][j] = M2.matriks[i][j-M1.kolom];
				}
			}
			return res;
		} else {
			System.out.println("Gagal, Baris kedua matriks tidak sama!");
			return this;
		}
	}
	
	public MATRIKS cropMatriks(int first, int last){
		if ((first >= 0 && first < this.kolom) && (last >= 0 && last < this.kolom) && (first <= last)){
			MATRIKS crop = new MATRIKS();
			crop.MATRIKS(this.baris ,last-first+1);
			for (int i = 0; i < this.baris; i++){
				int Kolom = 0;
				for (int j = first; j <= last; j++){
					crop.matriks[i][Kolom] = this.matriks[i][j];
					Kolom++;
				}
			}
			return crop;
		} else {
			System.out.println("Gagal, indeks tidak valid!");
			return this;
		}
	}

	public MATRIKS matriksInversGaussJordan(MATRIKS m){
		MATRIKS i = new MATRIKS();
		i.MATRIKS(m.baris, m.baris);
		i.makeIdentitas(m.baris);
		
		MATRIKS temp = new MATRIKS();
		temp.MATRIKS(m.baris, (m.kolom)+(i.kolom));
		temp = temp.concatMatriks(m,i);
		
		temp.gaussJordan();
		
		m = temp.cropMatriks((m.kolom), (m.kolom) + (i.kolom)-1);
		return m;
	}

	public MATRIKS SPLGaussJordan(MATRIKS Koef, MATRIKS Hasil){
		MATRIKS temp = new MATRIKS();
		temp.MATRIKS(Koef.baris, (Koef.kolom)+1);
		temp = temp.concatMatriks(Koef,Hasil);
		
		temp.gaussJordan();
		
		MATRIKS res = new MATRIKS();
		res.MATRIKS((Hasil.baris),1);
		res = temp.cropMatriks((Koef.kolom),(Koef.kolom));
		return res;
	}
	MATRIKS kaliMATRIKS(MATRIKS M1,MATRIKS M2)
	{
		int i,j,k;
		MATRIKS MH = new MATRIKS();
		MH.MATRIKS(M1.baris,M2.kolom);
		for (i=0;i<M1.baris;i++)
		{
			for (j=0;j<M2.kolom;j++)
			{
				for (k=0;k<M1.kolom;k++)
				{
					MH.matriks[i][j]+=(M1.matriks[i][k]*M2.matriks[k][j]);
				}
			}
		}
		return MH;
	}
	void SPLInvers()
	{
		int i,j;
		MATRIKS A = new MATRIKS();
		MATRIKS B = new MATRIKS();
		Scanner input = new Scanner(System.in);
		System.out.println("Masukkan matriks koefisien: ");
		A.bacaMatriks();
		B.MATRIKS(A.baris,1);
		double[] hasil = new double[A.baris];
		for (i=0;i<A.baris;i++)
		{
			{
				B.matriks[i][0]=input.nextDouble();
			}
		}
		A.tulisMatriks();
		B.tulisMatriks();
		MATRIKS MI = A.InverseAdDet(A);
		MATRIKS MH = MI.kaliMATRIKS(MI,B);
		for (i=0;i<A.baris;i++)
		{
			hasil[i]=MH.matriks[i][0];
			System.out.println("hasil["+(i+1)+"]: "+hasil[i]);
		}
	}
}
