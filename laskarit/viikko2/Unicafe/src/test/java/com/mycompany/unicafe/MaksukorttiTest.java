package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinTulostusToimii() {
        assertEquals("saldo: 0.10", kortti.toString()); 
    }
    
    @Test
    public void kortinTulostusSentitToimii() {
        kortti = new Maksukortti(8);
        assertEquals("saldo: 0.08", kortti.toString()); 
    }
    
    @Test
    public void kortinAlkusaldoOikein() {
        assertEquals(10, kortti.saldo());      
    }
    
    @Test
    public void kortinLatausToimiiOikein() {
        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());      
    }
    
    @Test
    public void negatiivinenLatausEiToimi() {
        kortti.lataaRahaa(-5);
        assertEquals(10, kortti.saldo());      
    }
    
    @Test
    public void rahaaVoiOttaaJosSitäOn() {
        assertEquals(true, kortti.otaRahaa(5));      
    }
    
    @Test
    public void rahanOttaminenVähentääSaldoa() {
        kortti.otaRahaa(5);
        assertEquals(5,kortti.saldo());      
    }
    
    @Test
    public void rahaaEiVoiOttaaYliSaldon() {
        assertEquals(false, kortti.otaRahaa(15));      
    }
    
}
