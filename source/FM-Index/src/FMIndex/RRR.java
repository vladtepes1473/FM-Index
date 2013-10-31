package FMIndex;


/*
 * @maja,matea
 * kao sto reko, mislim da je bolje da ova struktura
 * sadrzi bitset i da samo ona obavlja pretrazivanje
 * 
 * Ali me sad muci jedna stvar, a to je konstruktor
 * Sto mu predati?
 * Ako mu predamo bitset, on mora ponovo prolazit kroz
 * bit set da izracuna pojavljivanja za pretince i nadpretince
 * 
 * Ako mu predamo String (tj. stringwrapper)i pivota, onda on moze
 * sam izgradivati bitset i odma racunati broj jedinica 
 * (time bi trebali dobiti manju slozenost!!!!), ali mi to s
 * objektno orjentirane strane ne drzi bas vodu (zasto bi ta struktura
 * trebala znati kako mora gradit bitset).
 * 
 *Ja sam vise za ovu drugu opciju, ali eto, zanima me vase misljenje.
 * 
 */
public class RRR implements BitLookup {

	public RRR(Character pivot, StringWrapper string){
		
	}

	@Override
	public int rank(Integer position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
