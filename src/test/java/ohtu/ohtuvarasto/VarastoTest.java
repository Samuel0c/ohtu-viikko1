package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varastoAlkusaldolla;
    Varasto varastoLiianSuurellaAlkusaldolla;
    Varasto varastoNegTilavuus;
    Varasto varastoNegTilavuusAlkusaldolla;
    Varasto varastoNegAlkusaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varastoAlkusaldolla = new Varasto(15, 4);
        varastoLiianSuurellaAlkusaldolla = new Varasto(10, 15);
        varastoNegTilavuus = new Varasto(-5);
        varastoNegTilavuusAlkusaldolla = new Varasto(-5, 4);
        varastoNegAlkusaldolla = new Varasto(3, -5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonOikeallaAlkusaldolla() {
        assertEquals(4, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kontruktoriEiAsetaNegatiivistaAlkusaldoa() {
        assertEquals(0, varastoNegAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonOikeallaSaldollaKunAlkusaldoLiianSuuri() {
        assertEquals(10, varastoLiianSuurellaAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAntaaTVarastonTilavuudenOikeinNegatiivisellaTilavuudella() {
        assertEquals(0, varastoNegTilavuus.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriAntaaTVarastonTilavuudenOikeinNegatiivisellaTilavuudellaKunAlkusaldoOnAsetettu() {
        assertEquals(0, varastoNegTilavuusAlkusaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikeanMerkkijonon() {
        assertEquals("saldo = 4.0, vielä tilaa 11.0", varastoAlkusaldolla.toString());
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaSaldoaLiikaa() {
        varastoAlkusaldolla.lisaaVarastoon(15);
        assertEquals(15, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaNegatiivinenSaldo() {
        varastoAlkusaldolla.lisaaVarastoon(-5);
        assertEquals(4, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaNegatiivisellaArvolla() {
        varastoAlkusaldolla.otaVarastosta(-5);
        assertEquals(4, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void getSaldoToimiiKunOtetaanSaldoaSuurempiMaara() {
        varastoAlkusaldolla.otaVarastosta(6);
        assertEquals(0, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

}