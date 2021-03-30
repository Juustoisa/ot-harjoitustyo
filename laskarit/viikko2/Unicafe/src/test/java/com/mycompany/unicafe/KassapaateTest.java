package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kassa = new Kassapaate();
    }

   @Test
    public void luodutKorttiJaKassaOlemassa() {
        assertTrue(kortti!=null); 
        assertTrue(kassa!=null); 
    }
    
    @Test
    public void kassassaOikeaMääräRahaa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void uudessaKassassaEiMyyntejä() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisellaVoiSyödäEdullisestiJaMaukkaasti() {
        assertEquals(0, kassa.syoEdullisesti(240));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100240, kassa.kassassaRahaa());
        
        assertEquals(0, kassa.syoMaukkaasti(400));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100640, kassa.kassassaRahaa());
    }
    
    @Test
    public void kunRahaEiRiitäMyyntiäEiTule() {
        assertEquals(230, kassa.syoEdullisesti(230));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
        
        assertEquals(390, kassa.syoMaukkaasti(390));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortillaVoiSyödäEdullisestiJaMaukkaasti() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
        
        assertEquals(true, kassa.syoMaukkaasti(kortti));
        assertEquals(360, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void ilmanSaldoaEiVoiSyödä() {
        kortti = new Maksukortti(200);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
        
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void saldonLataaminenToimii() {
       kassa.lataaRahaaKortille(kortti, 1000);
       assertEquals(2000, kortti.saldo());
       assertEquals(101000, kassa.kassassaRahaa());
       
       kassa.lataaRahaaKortille(kortti, -1000);
       assertEquals(2000, kortti.saldo());
       assertEquals(101000, kassa.kassassaRahaa());
    }
}
