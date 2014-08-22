package edu.holycross.shot.nysi

import edu.harvard.chs.cite.CtsUrn

import au.com.bytecode.opencsv.CSVReader

import static org.junit.Assert.*
import org.junit.Test

class TestBadUrn extends GroovyTestCase {

  String collSource = "testdata/collections-badurn"

   @Test void testBadUrn() {
     ImgTurtleizer ittl = new ImgTurtleizer(collSource)
     assert shouldFail {
       String testttl = ittl.ttlize() 
     }
   }
}
