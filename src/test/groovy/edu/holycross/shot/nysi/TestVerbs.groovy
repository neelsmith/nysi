package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn

import au.com.bytecode.opencsv.CSVReader

import static org.junit.Assert.*
import org.junit.Test

class TestVerbs extends GroovyTestCase {

    String collSource = "testdata/collections"

 

    @Test void testVerbs() {
        ImgTurtleizer ittl = new ImgTurtleizer(collSource)
        String testttl = ittl.ttlize() 

        assert testttl.indexOf("rdf:type") >= 0
        assert testttl.indexOf("rdf:label") >= 0
        assert testttl.indexOf("hmt:path") >= 0
        assert testttl.indexOf("hmt:imageCaptionProperty") >= 0
        assert testttl.indexOf("hmt:imageRightsProperty") >= 0
        
    }

}
