//package percobaan.pkg1;
import java.util.Scanner;

class matriks
{
	Scanner input = new Scanner(System.in);
	double [][] elmt;
	int kolom;
	int baris;
	matriks(int brs,int kol)
	{
		this.baris=brs;
		this.kolom=kol;
		elmt= new double [brs][kol];
	}
	void isimatriks(matriks M, int baris, int kolom)
	{
		int i,j;
		M.baris = baris;
		M.kolom = kolom;
		for (i=0;i<M.baris;i++)
		{
			for (j=0;j<M.kolom;j++)
			{
				M.elmt[i][j]= input.nextDouble();
			}
		}
	}
	void tulismatriks(matriks M)
	{
		int i,j;
		for (i=0;i<M.baris;i++)
		{
			for (j=0;j<M.kolom;j++)
			{
				System.out.print(M.elmt[i][j]+ " ");
			}
			System.out.println();
		}
	}		
	//matriks cramer (int n)
	matriks transpose (matriks M)
	{
		int j,i;
		matriks MT = new matriks (3,3);
		for (i=0;i<3;i++)
		{
			for (j=0;j<3;j++)
			{
				MT.elmt[i][j]=M.elmt[j][i];
			}
		}
		return MT;
	}
	
	public matriks MatriksMinor(matriks M, int bar, int kol)
	{
		matriks res = new matriks(M.baris-1, M.kolom-1);
		int idxBar = 0;
		for (int i =  0; i < M.baris; i++)
		{
			int idxKol = 0;
			for (int j =  0; j < M.kolom; j++)
			{
				if ((i != bar) && (j != kol)){
					res.elmt[idxBar][idxKol] = M.elmt[i][j];
					idxKol++;
				}
			}
			if (idxKol == M.kolom-1)
				idxBar++;
		}
		return res;
	}

	public matriks MatriksKofaktor(matriks M){
		matriks res = new matriks(M.baris, M.kolom);
		for (int i = 0; i <= M.baris; i++)
		{
			for (int j = 0; j <= M.kolom; j++)
			{
				res.elmt[i][j] = Determinan(M.MatriksMinor(M,i,j));
			}
		}
		return res;
	}

	public matriks MatriksAdjoin(matriks M){
		M = transpose(MatriksKofaktor(M));
		return M;
	}
	
	public double Determinan(matriks M){
		double det = 0;
		int i = 0, j=0;
		if ((M.baris == 1) && (M.kolom == 1))
		{
			return M.elmt[i][j];
		}
		else if ((M.baris == 2) && (M.kolom ==2))
		{
			return (M.elmt[0][0]*M.elmt[1][1]-M.elmt[1][0]*M.elmt[0][1]);
		}
		else
		{
			for (j = 0 ; j < M.kolom ; j++){
				det = det + (Math.pow((-1),(j))*Determinan(MatriksMinor(M,i,j)));
				i++;
			}
			return det;
		}
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
	double InterpolasiPolinom(matriks M, int i)
	{
		return i;
	}
	
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
    public void bacaMatriks(int NB, int NK){
        makeMATRIKS(NB,NK);
        for(NB=0; NB<baris; NB++){
            for(NK=0; NK<kolom; NK++){
                this.matriks[NB][NK] = input.nextDouble();
                
            }
        }
    }
    
    public void tulisMatriks(int NB, int NK){
        for(NB=0; NB<baris; NB++){
            for(NK=0; NK<kolom; NK++){
                if(this.matriks[NB][NK]==(-0.0)){
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
}
