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
